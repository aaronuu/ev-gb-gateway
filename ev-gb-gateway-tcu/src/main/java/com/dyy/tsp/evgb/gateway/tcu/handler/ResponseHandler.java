package com.dyy.tsp.evgb.gateway.tcu.handler;

import com.dyy.tsp.evgb.gateway.protocol.entity.BeanTime;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.CommandType;
import com.dyy.tsp.evgb.gateway.protocol.handler.AbstractBusinessHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@SuppressWarnings("all")
@Service
public class ResponseHandler extends AbstractBusinessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseHandler.class);

    @Override
    public void doBusiness(EvGBProtocol protrocol, Channel channel) {
        if(protrocol.getCommandType()!= CommandType.HEARTBEAT){
            BeanTime beanTime = protrocol.getBody().getJson().toJavaObject(BeanTime.class);
            LOGGER.debug("{} {} {} 响应{}",protrocol.getVin(),beanTime.formatTime(),protrocol.getCommandType().getDesc(),protrocol.getResponseType().getDesc());
        }else{
            LOGGER.debug("{} {} 响应{}",protrocol.getVin(),protrocol.getCommandType().getDesc(),protrocol.getResponseType().getDesc());
        }
    }

}
