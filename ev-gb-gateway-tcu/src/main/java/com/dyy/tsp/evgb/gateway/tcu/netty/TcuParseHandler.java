package com.dyy.tsp.evgb.gateway.tcu.netty;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.CommandType;
import com.dyy.tsp.evgb.gateway.protocol.parse.AbstractParseHandler;
import com.dyy.tsp.evgb.gateway.tcu.enumtype.TcuCoreType;
import com.dyy.tsp.netty.common.IStatus;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Service;

/**
 * 数据解析处理器
 * created by dyy
 */
@SuppressWarnings("all")
@Service
public class TcuParseHandler extends AbstractParseHandler {

    /**
     * 解析Tbox的上行JSON报文方法
     * @param commandType
     * @param byteBuf
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject parseDownJson(CommandType commandType, ByteBuf byteBuf) throws BusinessException {
        IStatus status = TcuCoreType.valuesOf(commandType.getId()).getStatus();
        return status == null ? null : (JSONObject) JSONObject.toJSON(status.decode(byteBuf));
    }
}