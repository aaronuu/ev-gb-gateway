package com.dyy.tsp.evgb.gateway.protocol.entity;

import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.netty.common.IStatus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import java.nio.ByteOrder;

/**
 * 发动机数据
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class EngineData implements IStatus {

    /**
     * 发动机状态
     */
    private Short status;

    /**
     * 曲轴转速
     */
    private Integer crankshaftSpeed;

    /**
     * 燃料消耗率
     */
    private Integer fuelConsumption;

    @Override
    public EngineData decode(ByteBuf byteBuf) throws BusinessException {
        EngineData engineData = new EngineData();
        engineData.setStatus(byteBuf.readUnsignedByte());
        engineData.setCrankshaftSpeed(byteBuf.readUnsignedShort());
        engineData.setFuelConsumption(byteBuf.readUnsignedShort());
        return engineData;
    }

    @Override
    public ByteBuf encode() throws BusinessException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.writeByte(status);
        buffer.writeShort(crankshaftSpeed);
        buffer.writeShort(fuelConsumption);
        return buffer;
    }

}
