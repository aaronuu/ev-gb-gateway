package com.dyy.tsp.evgb.gateway.tcu.error;

public enum TcuError {

    ERROR_NAME("code123","业务异常测试");

    private String code;
    private String desc;
    private String namespace = "EV-GB-GATEWAY-TCU";

    TcuError(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
