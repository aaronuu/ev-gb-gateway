package com.dyy.tsp.evgb.gateway.tcu.vo;

import com.dyy.tsp.evgb.gateway.protocol.entity.RealTimeData;

/**
 * 实时数据上报
 */
@SuppressWarnings("all")
public class RealTimeDataVo extends RealTimeData {

    private String vin;

    private String commandType;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }
}
