package com.dyy.tsp.evgb.gateway.tcu.vo;

import com.dyy.tsp.evgb.gateway.protocol.entity.PlatformLogout;

/**
 * 平台登出
 */
@SuppressWarnings("all")
public class PlatformLogoutVo  extends PlatformLogout {

    private String vin;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
