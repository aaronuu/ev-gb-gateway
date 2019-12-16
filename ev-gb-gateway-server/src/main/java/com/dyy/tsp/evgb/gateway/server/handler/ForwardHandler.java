package com.dyy.tsp.evgb.gateway.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.server.config.EvGBProperties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private KafkaProducer kafkaProducer;

    @Autowired
    private EvGBProperties evGBProperties;

    /**
     * 转发数据到Debug
     * @param protocol
     */
    public void sendToDebug(EvGBProtocol protocol){
        protocol.setGatewayForwardTime(System.currentTimeMillis());
        String message = JSONObject.toJSONString(protocol);
        kafkaProducer.send(new ProducerRecord(evGBProperties.getDebugTopic(),message), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e!=null){
                    LOGGER.error("sendToDebug error:",e);
                }else{
                    LOGGER.debug("sendToDebug {}",message);
                }
            }
        });
    }

    /**
     * 转发数据到Dispatcher
     * @param protocol
     */
    public void sendToDispatcher(EvGBProtocol protocol){
        protocol.setGatewayForwardTime(System.currentTimeMillis());
        String message = JSONObject.toJSONString(protocol);
        kafkaProducer.send(new ProducerRecord(evGBProperties.getDispatcherTopic(), message), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e!=null){
                    LOGGER.error("sendToDispatcher error:",e);
                }else{
                    LOGGER.debug("sendToDispatcher {}",message);
                }
            }
        });
    }

}
