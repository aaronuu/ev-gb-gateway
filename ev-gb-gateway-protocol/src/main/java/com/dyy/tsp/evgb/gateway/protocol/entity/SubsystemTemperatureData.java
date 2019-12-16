package com.dyy.tsp.evgb.gateway.protocol.entity;

import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.netty.common.IStatus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * 子系统温度数据
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class SubsystemTemperatureData implements IStatus {

    /**
     * 子系统号
     */
    private Short num;

    /**
     * 温度探针个数
     */
    private Integer temperatureProbeCount;

    /**
     * 探针温度值列表
     */
    private List<Short> probetemperatures;


    @Override
    public SubsystemTemperatureData decode(ByteBuf byteBuf) throws BusinessException {
        SubsystemTemperatureData subsystemTemperatureData = new SubsystemTemperatureData();
        subsystemTemperatureData.setNum(byteBuf.readUnsignedByte());
        int i1 = byteBuf.readUnsignedShort();
        subsystemTemperatureData.setTemperatureProbeCount(i1);
        if(i1>0){
            List<Short> list =new ArrayList<>();
            for (int i = 0; i <i1 ; i++) {
                short i2 = byteBuf.readUnsignedByte();
                list.add(i2);
            }
            subsystemTemperatureData.setProbetemperatures(list);
        }
        return subsystemTemperatureData;
    }

    @Override
    public ByteBuf encode() throws BusinessException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.writeByte(num);
        buffer.writeShort(temperatureProbeCount);
        if(temperatureProbeCount>0 && probetemperatures.size()>0){
            for (int i = 0; i <probetemperatures.size(); i++) {
                buffer.writeByte(probetemperatures.get(i));
            }
        }
        return buffer;
    }
}
