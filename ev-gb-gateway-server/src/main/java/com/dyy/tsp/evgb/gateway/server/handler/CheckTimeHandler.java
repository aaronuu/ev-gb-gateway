package com.dyy.tsp.evgb.gateway.server.handler;

import com.dyy.tsp.evgb.gateway.protocol.entity.BeanTime;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.ResponseType;
import com.dyy.tsp.evgb.gateway.protocol.handler.AbstractBusinessHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 终端校时处理器。
 * 需要给终端响应
 * created by dyy
 */
@Service
@SuppressWarnings("all")
public class CheckTimeHandler extends AbstractBusinessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckTimeHandler.class);

    /**
     * 响应当前服务器时间戳
     * @param protocol
     * @param channel
     */
    @Override
    public void doBusiness(EvGBProtocol protocol, Channel channel) {
        doCommonResponse(ResponseType.SUCCESS,protocol,new BeanTime(System.currentTimeMillis()),channel);
    }

}
