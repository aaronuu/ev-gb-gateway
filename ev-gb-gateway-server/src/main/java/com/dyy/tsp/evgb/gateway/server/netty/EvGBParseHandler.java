package com.dyy.tsp.evgb.gateway.server.netty;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.evgb.gateway.protocol.parse.AbstractParseHandler;
import com.dyy.tsp.evgb.gateway.server.enumtype.GatewayCoreType;
import com.dyy.tsp.netty.common.IStatus;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Service;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.CommandType;

/**
 * 数据解析处理器
 * created by dyy
 */
@SuppressWarnings("all")
@Service
public class EvGBParseHandler extends AbstractParseHandler {

    /**
     * 解析Tbox的上行JSON报文方法
     * @param commandType
     * @param byteBuf
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject parseUpJson(CommandType commandType, ByteBuf byteBuf) throws BusinessException {
        IStatus status = GatewayCoreType.valuesOf(commandType.getId()).getStatus();
        return status == null ? null : (JSONObject) JSONObject.toJSON(status.decode(byteBuf));
    }
}
