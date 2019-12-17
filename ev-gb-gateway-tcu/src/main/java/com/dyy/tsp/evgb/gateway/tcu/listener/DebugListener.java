package com.dyy.tsp.evgb.gateway.tcu.listener;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.tcu.websocket.DebugWebSocketHandler;
import com.dyy.tsp.kafka.asyn.TaskPool;
import com.dyy.tsp.kafka.listener.AbstractListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 在线监控监听器
 * 推送监控车辆实时信息到Websocket控制台
 */
@SuppressWarnings("all")
public class DebugListener extends AbstractListener<String, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugListener.class);

    private DebugWebSocketHandler debugWebSocketHandler;

    @PostConstruct
    public void init(){
        debugWebSocketHandler = context.getBean(DebugWebSocketHandler.class);
    }

    @Override
    protected void listen(String message) {
        EvGBProtocol protocol = JSONObject.parseObject(message, EvGBProtocol.class);
        TaskPool.getInstance().submitTask(()->{
            if(debugWebSocketHandler.getDevices().get(protocol.getVin())!=null){
                debugWebSocketHandler.sendMessage(protocol.getVin(), new TextMessage(message));
            }
        });
    }

    @PreDestroy
    public void destroy(){
        setRunning(false);
    }
}
