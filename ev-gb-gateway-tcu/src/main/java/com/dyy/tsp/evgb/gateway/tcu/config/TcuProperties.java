package com.dyy.tsp.evgb.gateway.tcu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("all")
@Data
@ConfigurationProperties(prefix = TcuProperties.PREFIX)
public class TcuProperties {

    public static final String PREFIX = "tcu";

    //服务端域名
    private String serverHost = "ev-gb-gateway-server";

    //服务端端口
    private Integer serverPort = 8111;

    private Integer reconnectMaxNum = Integer.MAX_VALUE;

    //心跳频率
    private Integer timeout = 4;

    //默认心跳VIN
    private String heartVin = "TCU_HEART_VIN_123";

    //网关Redis订阅发布Topic
    private String commandRequestTopic = "dyy_command_request_data";

    //协议配置
    private Integer maxFrameLength = 65556;
    private Integer lengthFieldOffset = 22;
    private Integer lengthFieldLength = 2;
    private Integer lengthAdjustment = 1;
    private Integer initialBytesToStrip = 0 ;
    private Boolean failFast = Boolean.TRUE;

}
