package com.dyy.tsp.evgb.gateway.tcu.vo;

import com.dyy.tsp.evgb.gateway.protocol.entity.PlatformLogin;

/**
 * 平台登入
 */
@SuppressWarnings("all")
public class PlatformLoginVo extends PlatformLogin {

    private String vin;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
