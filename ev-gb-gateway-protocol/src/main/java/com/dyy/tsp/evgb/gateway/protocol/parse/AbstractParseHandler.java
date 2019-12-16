package com.dyy.tsp.evgb.gateway.protocol.parse;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.CommandType;
import io.netty.buffer.ByteBuf;

/**
 * 实现顶层方法，子类重写对应方法
 * created by dyy
 */
@SuppressWarnings("all")
public class AbstractParseHandler implements IParse{

    @Override
    public JSONObject parseUpJson(CommandType commandType, ByteBuf body) throws BusinessException {
        return null;
    }

    @Override
    public JSONObject parseDownJson(CommandType CommandType, ByteBuf body) throws BusinessException {
        return null;
    }
}
