package com.dyy.tsp.evgb.gateway.server.netty;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.evgb.gateway.protocol.common.CommonCache;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.protocol.util.HelperKeyUtil;
import com.dyy.tsp.evgb.gateway.server.handler.BusinessHandler;
import com.dyy.tsp.netty.common.IProtocol;
import com.dyy.tsp.netty.tcp.handler.AbstractNettyHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Netty核心处理器
 * created by dyy
 */
@SuppressWarnings("all")
@Service
@ChannelHandler.Sharable
public class EvGBNettyHandler extends AbstractNettyHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(EvGBNettyHandler.class);

    @Autowired
    private BusinessHandler businessHandler;

    @Override
    public void doLogic(ChannelHandlerContext ctx, IProtocol protocol) {
        EvGBProtocol evGBProtocol = (EvGBProtocol)protocol;
        String message = JSONObject.toJSONString(protocol);
        LOGGER.debug("parse protocol:{}", message);
        if(!evGBProtocol.getBcc() || !evGBProtocol.getBegin()){
            LOGGER.warn("{} invalid data packet",evGBProtocol.getVin());
            return;
        }
        businessHandler.doBusiness(evGBProtocol,ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
        Channel channel = ctx.channel();
        String vin = CommonCache.channelVinMap.remove(channel);
        if(StringUtils.isNotBlank(vin)){
            CommonCache.vinChannelMap.remove(vin);
            CommonCache.vehicleCacheMap.remove(HelperKeyUtil.getKey(vin));
        }
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx,cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        Channel channel = ctx.channel();
        if(evt instanceof IdleStateEvent){
            IdleState state = ((IdleStateEvent)evt).state();
            if(state == IdleState.READER_IDLE){
                channel.close();
            }
        }
    }
}
