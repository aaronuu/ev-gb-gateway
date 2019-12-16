package com.dyy.tsp.evgb.gateway.protocol.entity;

import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.netty.common.IStatus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import java.nio.ByteOrder;

/**
 * 整车数据
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class VehicleData implements IStatus {

    /**
     * 车辆状态
     */
    private Short vehicleStatus;

    /**
     * 充电状态
     */
    private Short chargeStatus;

    /**
     * 运行模式
     */
    private Short operationMode;

    /**
     * 车速
     */
    private Integer speed;

    /**
     * 累计里程
     */
    private Long mileage;

    /**
     * 总电压
     */
    private Integer totalVoltage;

    /**
     * 总电流
     */
    private Integer totalCurrent;

    /**
     * SOC电量
     */
    private Short soc;

    /**
     * DC_DC状态
     */
    private Short dcStatus;

    /**
     * 挡位
     */
    private Short gears;

    /**
     * 绝缘电阻
     */
    private Integer insulationResistance;

    /**
     * 加速行程值
     */
    private Short accelerationValue;

    /**
     * 制动踏板状态
     */
    private Short brakePedalCondition;

    @Override
    public VehicleData decode(ByteBuf byteBuf) throws BusinessException {
        VehicleData vehicleData = new VehicleData();
        //车辆状态
        vehicleData.setVehicleStatus(byteBuf.readUnsignedByte());
        //充电状态
        vehicleData.setChargeStatus(byteBuf.readUnsignedByte());
        //运行模式
        vehicleData.setOperationMode(byteBuf.readUnsignedByte());
        //车速
        vehicleData.setSpeed(byteBuf.readUnsignedShort());
        //累计里程
        vehicleData.setMileage(byteBuf.readUnsignedInt());
        //总电压
        vehicleData.setTotalVoltage(byteBuf.readUnsignedShort());
        //总电流
        vehicleData.setTotalCurrent(byteBuf.readUnsignedShort());
        //SOC电量
        vehicleData.setSoc(byteBuf.readUnsignedByte());
        //DC-DC状态
        vehicleData.setDcStatus(byteBuf.readUnsignedByte());
        //档位
        vehicleData.setGears(byteBuf.readUnsignedByte());
        //绝缘电阻
        vehicleData.setInsulationResistance(byteBuf.readUnsignedShort());
        //加速行程值
        vehicleData.setAccelerationValue(byteBuf.readUnsignedByte());
        //制动踏板状态
        vehicleData.setBrakePedalCondition(byteBuf.readUnsignedByte());
        return vehicleData;
    }

    @Override
    public ByteBuf encode() throws BusinessException{
        ByteBuf buffer = Unpooled.buffer();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.writeByte(vehicleStatus);
        buffer.writeByte(chargeStatus);
        buffer.writeByte(operationMode);
        buffer.writeShort(speed);
        buffer.writeInt(mileage.intValue());
        buffer.writeShort(totalVoltage);
        buffer.writeShort(totalCurrent);
        buffer.writeByte(soc);
        buffer.writeByte(dcStatus);
        buffer.writeByte(gears);
        buffer.writeShort(insulationResistance);
        buffer.writeByte(accelerationValue);
        buffer.writeByte(accelerationValue);
        return buffer;
    }
}
