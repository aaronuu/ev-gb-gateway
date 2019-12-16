package com.dyy.tsp.evgb.gateway.protocol.handler;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.evgb.gateway.protocol.entity.BeanTime;
import com.dyy.tsp.evgb.gateway.protocol.entity.DataBody;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.ResponseType;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现顶层方法，子类重写对应方法
 * 公用抽取
 * created by dyy
 */
@SuppressWarnings("all")
public abstract class AbstractBusinessHandler implements IHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBusinessHandler.class);

    @Override
    public Boolean checkLogin(JSONObject redisCache) {
        return redisCache != null ? redisCache.getBoolean("login") : Boolean.FALSE;
    }

    /**
     * 公用业务处理方法
     * @param protrocol
     * @param channel
     */
    @Override
    public abstract void doBusiness(EvGBProtocol protrocol, Channel channel);

    /**
     * EVGB要求  通用响应
     * 适用车辆登入 车辆登出 平台登入 平台登出 实时/补发信息上报 终端校时
     * 只需要返回数据采集时间
     * @param responseType
     * @param protrocol
     * @param beanTime
     * @param channel
     */
    @Override
    public void doCommonResponse(ResponseType responseType, EvGBProtocol protrocol, BeanTime beanTime, Channel channel) {
        protrocol.setBody(new DataBody(beanTime.encode()));
        protrocol.setResponseType(responseType);
        channel.eventLoop().execute(()->{
            channel.writeAndFlush(protrocol.encode());
        });
        LOGGER.debug("{} {} 响应{}",protrocol.getVin(),protrocol.getCommandType().getDesc(),protrocol.getResponseType().getDesc());
    }

    /**
     * 心跳响应
     * @param responseType
     * @param protrocol
     * @param beanTime
     * @param channel
     */
    @Override
    public void doHeartResponse(ResponseType responseType, EvGBProtocol protrocol, Channel channel) {
        protrocol.setResponseType(responseType);
        channel.eventLoop().execute(()->{
            channel.writeAndFlush(protrocol.encode());
        });
        LOGGER.debug("{} {} 响应{}",protrocol.getVin(),protrocol.getCommandType().getDesc(),protrocol.getResponseType().getDesc());
    }
}
