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
 * 燃料电池数据
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class FuelCellData implements IStatus {

    /**
     * 燃料电池电压
     */
    private Integer voltage;

    /**
     * 燃料电池电流
     */
    private Integer current;

    /**
     * 燃料消耗率
     */
    private Integer fuelConsumption;

    /**
     * 燃料电池温度探针总数
     */
    private Integer temperatureProbeCount;

    /**
     * 探针温度值
     */
    private List<Short> probeTemperatures;

    /**
     * 氢系统中最高温度
     */
    private Integer hydrogenSystemMaxTemperature;

    /**
     * 氢系统中最高温度探针代号
     */
    private Short hydrogenSystemTemperatureProbeNum;

    /**
     * 氢气最高浓度
     */
    private Integer hydrogenSystemMaxConcentration;

    /**
     * 氢气最高浓度探针代号
     */
    private Short hydrogenSystemConcentrationProbeNum;

    /**
     * 氢气最高压力
     */
    private Integer hydrogenSystemMaxPressure;

    /**
     * 氢气最高压力探针代号
     */
    private Short hydrogenSystemPressureProbeNum;

    /**
     * 高压DC-DC状态
     */
    private Short dcStatus;

    @Override
    public FuelCellData decode(ByteBuf byteBuf) throws BusinessException {
        FuelCellData fuelCellData = new FuelCellData();
        fuelCellData.setVoltage(byteBuf.readUnsignedShort());
        fuelCellData.setCurrent(byteBuf.readUnsignedShort());
        fuelCellData.setFuelConsumption(byteBuf.readUnsignedShort());
        fuelCellData.setTemperatureProbeCount(byteBuf.readUnsignedShort());
        List<Short> temperatureList = new ArrayList<Short>(fuelCellData.getTemperatureProbeCount());
        if(fuelCellData.getTemperatureProbeCount()>0){
            for (int i = 0; i < fuelCellData.getTemperatureProbeCount(); i++) {
                temperatureList.add(byteBuf.readUnsignedByte());
            }
        }
        fuelCellData.setProbeTemperatures(temperatureList);
        fuelCellData.setHydrogenSystemMaxTemperature(byteBuf.readUnsignedShort());
        fuelCellData.setHydrogenSystemTemperatureProbeNum(byteBuf.readUnsignedByte());
        fuelCellData.setHydrogenSystemMaxConcentration(byteBuf.readUnsignedShort());
        fuelCellData.setHydrogenSystemConcentrationProbeNum(byteBuf.readUnsignedByte());
        fuelCellData.setHydrogenSystemMaxPressure(byteBuf.readUnsignedShort());
        fuelCellData.setHydrogenSystemPressureProbeNum(byteBuf.readUnsignedByte());
        fuelCellData.setDcStatus(byteBuf.readUnsignedByte());
        return fuelCellData;
    }

    @Override
    public ByteBuf encode() throws BusinessException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.writeShort(voltage);
        buffer.writeShort(current);
        buffer.writeShort(fuelConsumption);
        buffer.writeShort(temperatureProbeCount);
        if(temperatureProbeCount >0 && probeTemperatures.size()>0){
            for (int i = 0; i <probeTemperatures.size(); i++) {
                buffer.writeByte(probeTemperatures.get(i));
            }
        }
        buffer.writeShort(hydrogenSystemMaxTemperature);
        buffer.writeByte(hydrogenSystemTemperatureProbeNum);
        buffer.writeShort(hydrogenSystemMaxConcentration);
        buffer.writeByte(hydrogenSystemConcentrationProbeNum);
        buffer.writeShort(hydrogenSystemMaxPressure);
        buffer.writeByte(hydrogenSystemPressureProbeNum);
        buffer.writeByte(dcStatus);
        return buffer;
    }
}
