package com.dyy.tsp.evgb.gateway.tcu.vo;

import com.dyy.tsp.evgb.gateway.protocol.entity.VehicleLogout;

@SuppressWarnings("all")
public class VehicleLogoutVo extends VehicleLogout {

    private String vin;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
