package com.dyy.tsp.evgb.gateway.tcu.websocket;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.evgb.gateway.protocol.entity.CommandDownRequest;
import com.dyy.tsp.redis.handler.RedisHandler;
import com.dyy.tsp.websocket.handler.AbstractWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

/**
 * 网关WebSocket监控处理类
 * created by dyy
 */
@SuppressWarnings("all")
@Service("debugWebSocketHandler")
public class DebugWebSocketHandler extends AbstractWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugWebSocketHandler.class);

    private static RedisHandler redisHandler;

    private static final String CLOSE_DEBUG = "CLOSE_DEBUG";

    private static final String OPEN_DEBUG = "OPEN_DEBUG";

    private static final String SRM_COMMAND_REQUEST = "dyy_command_request_data";

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        String uniqueId = session.getUri().toString().split("uniqueId=")[1];
        CommandDownRequest request = new CommandDownRequest(uniqueId, OPEN_DEBUG);
        redisHandler.pushMessage(SRM_COMMAND_REQUEST, JSONObject.toJSONString(request));
        LOGGER.info("{} send gateway open debug",uniqueId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        super.afterConnectionClosed(session,closeStatus);
        String uniqueId = session.getUri().toString().split("uniqueId=")[1];
        //通知网关停止监控
        CommandDownRequest request = new CommandDownRequest(uniqueId, CLOSE_DEBUG);
        redisHandler.pushMessage(SRM_COMMAND_REQUEST, JSONObject.toJSONString(request));
        LOGGER.info("{} send gateway close debug",uniqueId);
    }

    @Autowired
    public void setRedisHandler(RedisHandler redisHandler) {
        this.redisHandler = redisHandler;
    }

}
