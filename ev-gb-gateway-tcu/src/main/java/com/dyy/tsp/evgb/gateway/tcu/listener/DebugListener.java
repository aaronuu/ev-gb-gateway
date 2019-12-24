package com.dyy.tsp.evgb.gateway.tcu.listener;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.common.asyn.TaskPool;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.tcu.websocket.DebugWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

/**
 * 在线监控监听器
 * 推送监控车辆实时信息到Websocket控制台
 * created by dyy
 */
@SuppressWarnings("all")
@Component
public class DebugListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugListener.class);

    @Autowired
    private DebugWebSocketHandler debugWebSocketHandler;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}")
    public void listen(String message) {
        TaskPool.getInstance().submit(()->{
            EvGBProtocol protocol = JSONObject.parseObject(message, EvGBProtocol.class);
            if(debugWebSocketHandler.getDevices().get(protocol.getVin())!=null){
                debugWebSocketHandler.sendMessage(protocol.getVin(), new TextMessage(message));
            }
        });
        LOGGER.debug("线程池中线程数："+TaskPool.getInstance().getPoolSize()+"，队列中等待执行的任务数目："+ TaskPool.getInstance().getQueue().size()+"，已执行完的任务数目："+TaskPool.getInstance().getCompletedTaskCount());
    }

}
