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
 * 子系统电压数据
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class SubsystemVoltageData implements IStatus {

    /**
     * 子系统号
     */
    private Short num;

    /**
     * 电压
     */
    private Integer voltage;

    /**
     * 电流
     */
    private Integer current;

    /**
     * 单体电池个数
     */
    private Integer cellCount;

    /**
     * 本帧起始电池序号
     */
    private Integer batteryNumber;

    /**
     * 本帧单体电池总数
     */
    private Short batteryConnt;

    /**
     * 单体电池电压列表
     */
    private List<Integer> cellVoltages;


    @Override
    public SubsystemVoltageData decode(ByteBuf byteBuf) throws BusinessException {
        SubsystemVoltageData subsystemVoltageData = new SubsystemVoltageData();
        subsystemVoltageData.setNum(byteBuf.readUnsignedByte());
        subsystemVoltageData.setVoltage(byteBuf.readUnsignedShort());
        subsystemVoltageData.setCurrent(byteBuf.readUnsignedShort());
        subsystemVoltageData.setCellCount(byteBuf.readUnsignedShort());
        subsystemVoltageData.setBatteryNumber(byteBuf.readUnsignedShort());
        short i = byteBuf.readUnsignedByte();
        subsystemVoltageData.setBatteryConnt(i);
        if(i>0){
            List<Integer> list =new ArrayList();
            for (int j = 0; j < i; j++) {
                int i1 = byteBuf.readUnsignedShort();
                list.add(i1);
            }
            subsystemVoltageData.setCellVoltages(list);
        }
        return subsystemVoltageData;
    }

    @Override
    public ByteBuf encode() throws BusinessException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.writeByte(num);
        buffer.writeShort(voltage);
        buffer.writeShort(current);
        buffer.writeShort(cellCount);
        buffer.writeShort(batteryNumber);
        buffer.writeByte(batteryConnt);
        for (int i = 0; i < batteryConnt; i++) {
            buffer.writeShort(cellVoltages.get(i));
        }
        return buffer;
    }
}
