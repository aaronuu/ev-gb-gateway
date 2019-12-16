package com.dyy.tsp.evgb.gateway.protocol.parse;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.CommandType;
import io.netty.buffer.ByteBuf;

/**
 * 数据解析顶层接口
 * created by dyy
 */
@SuppressWarnings("all")
public interface IParse {

    JSONObject parseUpJson(CommandType commandType, ByteBuf body) throws BusinessException;

    JSONObject parseDownJson(CommandType CommandType, ByteBuf body) throws BusinessException;

}
