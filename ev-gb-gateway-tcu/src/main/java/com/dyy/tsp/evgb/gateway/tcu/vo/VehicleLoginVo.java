package com.dyy.tsp.evgb.gateway.tcu.vo;

import com.dyy.tsp.evgb.gateway.protocol.entity.VehicleLogin;

@SuppressWarnings("all")
public class VehicleLoginVo extends VehicleLogin {

    private String vin;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
