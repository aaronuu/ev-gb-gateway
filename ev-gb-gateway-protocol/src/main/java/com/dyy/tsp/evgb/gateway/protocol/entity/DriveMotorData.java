package com.dyy.tsp.evgb.gateway.protocol.entity;

import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.netty.common.IStatus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;

import java.nio.ByteOrder;

/**
 * 驱动电机数据
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class DriveMotorData implements IStatus {

    /**
     * 序号
     */
    private Short num;

    /**
     * 驱动电机状态
     */
    private Short status;

    /**
     * 驱动电机控制器温度
     */
    private Short controllerTemperature;

    /**
     * 驱动电机转速
     */
    private Integer speed;

    /**
     * 驱动电机转矩
     */
    private Integer torque;

    /**
     * 驱动电机温度
     */
    private Short temperature;

    /**
     * 驱动电机控制器输入电压
     */
    private Integer controllerInputVoltage;

    /**
     * 驱动电机控制器直流母线电流
     */
    private Integer controllerDcBusbarCurrent;

    @Override
    public DriveMotorData decode(ByteBuf byteBuf) throws BusinessException {
        DriveMotorData driveMotorData = new DriveMotorData();
        driveMotorData.setNum(byteBuf.readUnsignedByte());
        driveMotorData.setStatus(byteBuf.readUnsignedByte());
        driveMotorData.setControllerTemperature(byteBuf.readUnsignedByte());
        driveMotorData.setSpeed(byteBuf.readUnsignedShort());
        driveMotorData.setTorque(byteBuf.readUnsignedShort());
        driveMotorData.setTemperature(byteBuf.readUnsignedByte());
        driveMotorData.setControllerInputVoltage(byteBuf.readUnsignedShort());
        driveMotorData.setControllerDcBusbarCurrent(byteBuf.readUnsignedShort());
        return driveMotorData;
    }

    @Override
    public ByteBuf encode() throws BusinessException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.writeByte(num);
        buffer.writeByte(status);
        buffer.writeByte(controllerTemperature);
        buffer.writeShort(speed);
        buffer.writeShort(torque);
        buffer.writeByte(temperature);
        buffer.writeShort(controllerInputVoltage);
        buffer.writeShort(controllerDcBusbarCurrent);
        return buffer;
    }

}
