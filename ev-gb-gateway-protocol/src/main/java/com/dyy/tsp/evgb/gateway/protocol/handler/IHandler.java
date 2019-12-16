package com.dyy.tsp.evgb.gateway.protocol.handler;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.evgb.gateway.protocol.entity.BeanTime;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.ResponseType;
import io.netty.channel.Channel;

/**
 * 业务处理类
 * created by dyy
 */
@SuppressWarnings("all")
public interface IHandler {

    Boolean checkLogin(JSONObject redisCache);

    void doBusiness(EvGBProtocol evGBProtocol, Channel channel);

    void doCommonResponse(ResponseType responseType, EvGBProtocol protrocol, BeanTime beanTime, Channel channel);

    void doHeartResponse(ResponseType responseType, EvGBProtocol protrocol, Channel channel);
}
