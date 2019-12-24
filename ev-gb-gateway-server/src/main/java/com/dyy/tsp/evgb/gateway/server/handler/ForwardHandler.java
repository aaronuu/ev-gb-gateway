package com.dyy.tsp.evgb.gateway.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.server.config.EvGBProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 数据转发处理器
 * created by dyy
 */
@Service
@SuppressWarnings("all")
public class ForwardHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForwardHandler.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private EvGBProperties evGBProperties;

    /**
     * 转发数据到Debug
     * @param protocol
     */
    public void sendToDebug(EvGBProtocol protocol){
        protocol.setGatewayForwardTime(System.currentTimeMillis());
        String message = JSONObject.toJSONString(protocol);
        kafkaTemplate.send(evGBProperties.getDebugTopic(),message);
    }

    /**
     * 转发数据到Dispatcher
     * @param protocol
     */
    public void sendToDispatcher(EvGBProtocol protocol){
        protocol.setGatewayForwardTime(System.currentTimeMillis());
        String message = JSONObject.toJSONString(protocol);
        kafkaTemplate.send(evGBProperties.getDispatcherTopic(),message);
    }

}
