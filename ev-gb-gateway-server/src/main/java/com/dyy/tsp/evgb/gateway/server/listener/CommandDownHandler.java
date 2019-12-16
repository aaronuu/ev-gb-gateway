package com.dyy.tsp.evgb.gateway.server.listener;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.evgb.gateway.protocol.common.CommonCache;
import com.dyy.tsp.evgb.gateway.protocol.entity.CommandDownRequest;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.CommandDownHelperType;
import com.dyy.tsp.evgb.gateway.protocol.util.HelperKeyUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 指令下发处理器
 * created by dyy
 */
@Service
@SuppressWarnings("all")
public class CommandDownHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandDownHandler.class);

    /**
     * 处理网关接收到所有指令
     * 关闭监控
     * 开启监控
     * 清除内存
     * 远控指令下发
     * @param message
     */
    public void doBusiness(String message,String topic){
        LOGGER.debug("receive command request {}",message);
        CommandDownRequest request = JSONObject.parseObject(message, CommandDownRequest.class);
        if(CommandDownHelperType.CLOSE_DEBUG.name().equals(request.getCommand())){
            CommonCache.debugVinMap.remove(request.getVin());
            LOGGER.debug("{} webSocket console close",request.getVin());
        }else if(CommandDownHelperType.OPEN_DEBUG.name().equals(request.getCommand())){
            CommonCache.debugVinMap.put(request.getVin(),request.getCommand());
            LOGGER.debug("{} webSocket console open",request.getVin());
        }else if(CommandDownHelperType.CLEAR_CAHCE.name().equals(request.getCommand())){
            CommonCache.vehicleCacheMap.remove(HelperKeyUtil.getKey(request.getVin()));
        }else {
            Channel channel = CommonCache.vinChannelMap.get(request.getVin());
            this.sendCommand(channel,request);
        }
    }

    /**
     * 网关下发对应指令给终端
     * @param channel
     * @param request
     */
    private void sendCommand(Channel channel, CommandDownRequest request) {
        //TODO
    }

}
