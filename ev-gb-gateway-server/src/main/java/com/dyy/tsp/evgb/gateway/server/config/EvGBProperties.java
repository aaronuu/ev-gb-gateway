package com.dyy.tsp.evgb.gateway.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("all")
@Data
@ConfigurationProperties(prefix = EvGBProperties.PREFIX)
public class EvGBProperties {

    public static final String PREFIX = "evgb";

    //TCP网关端口
    private Integer port = 8111;

    //心跳空闲检查时间90秒
    private Integer timeout = 30;

    //三次握手队列最大长度
    private Integer soBackLog = 4096;

    //三次握手队列最大长度
    private Integer workLoopCount = 4;

    //指令下发请求
    private String commandRequestTopic = "dyy_command_request_data";

    //指令下发响应
    private String commandResponseTopic = "dyy_command_response_data";

    //在线监控
    private String debugTopic = "dyy_debug_data";

    //dispatcher处理
    private String dispatcherTopic = "dyy_dispatcher_data";

    //转发国标/地标
    private String evgbTopic = "dyy_evgb_data";

    private Integer maxFrameLength = 65556;

    private Integer lengthFieldOffset = 22;

    private Integer lengthFieldLength = 2;

    private Integer lengthAdjustment = 1;

    private Integer initialBytesToStrip = 0 ;

    private Boolean failFast = Boolean.TRUE;

}
