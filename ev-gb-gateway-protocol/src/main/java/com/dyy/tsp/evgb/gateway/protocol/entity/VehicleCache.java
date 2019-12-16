package com.dyy.tsp.evgb.gateway.protocol.entity;

import lombok.Data;

/**
 * 车辆缓存
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class VehicleCache {

    //是否车辆登入
    private Boolean login;

    //最后一次登入时间
    private String lastLoginTime;

    //最后一次登入流水号
    private Integer lastLoginSerialNum;

    //最后一次登出时间
    private String lastLogoutTime;

    //最后一次登出流水号
    private Integer lastLogoutSerialNum;
}
