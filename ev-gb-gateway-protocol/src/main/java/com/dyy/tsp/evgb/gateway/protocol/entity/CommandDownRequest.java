package com.dyy.tsp.evgb.gateway.protocol.entity;

import lombok.Data;

/**
 * 指令下发请求
 */
@SuppressWarnings("all")
@Data
public class CommandDownRequest {

    /**
     * 车架号
     */
    private String vin;

    /**
     * 指令类型
     */
    private String command;

    /**
     * 指令请求时间
     */
    private Long time;

    /**
     * 指令请求流水号
     */
    private Integer serialNum;

    public CommandDownRequest() {
    }

    public CommandDownRequest(String vin, String command) {
        this.vin = vin;
        this.command = command;
    }
}
