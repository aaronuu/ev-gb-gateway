package com.dyy.tsp.evgb.gateway.server.handler;

import com.dyy.tsp.evgb.gateway.protocol.common.CommonCache;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 按需在线监控
 * created by dyy
 */
@SuppressWarnings("all")
@Service
public class DebugHandler {

    @Autowired
    private ForwardHandler forwardHandler;

    public void debugger(EvGBProtocol protocol) {
        if(null != CommonCache.debugVinMap.get(protocol.getVin())){
            forwardHandler.sendToDebug(protocol);
        }
    }
}
