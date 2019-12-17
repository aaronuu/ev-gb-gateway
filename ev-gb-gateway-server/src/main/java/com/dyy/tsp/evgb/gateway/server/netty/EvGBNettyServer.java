package com.dyy.tsp.evgb.gateway.server.netty;

import com.dyy.tsp.evgb.gateway.protocol.decode.EvGBNettyDecode;
import com.dyy.tsp.evgb.gateway.server.config.EvGBProperties;
import com.dyy.tsp.netty.server.AbstractNettyServer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * created by dyy
 * 基于GB32960实现数据接入网关
 */
@SuppressWarnings("all")
public class EvGBNettyServer extends AbstractNettyServer {

    @Autowired
    private EvGBProperties evGBProperties;

    @Autowired
    private EvGBNettyHandler evGBNettyHandler;

    @Autowired
    private EvGBParseHandler evGBParseHandler;

    @Value("${logging.level.io.netty}")
    private String nettyLog;

    @Override
    public ChannelInitializer<SocketChannel> getChannelInitializer() {
        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                //开启DEBUG Netty内部日志
                if(StringUtils.equalsIgnoreCase(LogLevel.DEBUG.name(),nettyLog)){
                    pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
                }
                pipeline.addLast(
                        new LengthFieldBasedFrameDecoder(evGBProperties.getMaxFrameLength(), evGBProperties.getLengthFieldOffset()
                                , evGBProperties.getLengthFieldLength(), evGBProperties.getLengthAdjustment()
                                , evGBProperties.getInitialBytesToStrip(), evGBProperties.getFailFast()),
                        new EvGBNettyDecode(evGBParseHandler,Boolean.TRUE),
                        new IdleStateHandler(evGBProperties.getTimeout(),evGBProperties.getTimeout(), 0),
                        evGBNettyHandler
                );
            }
        };
    }

    @PostConstruct
    @Override
    public void start() {
        super.start();
    }

    @PreDestroy
    @Override
    public void stop() {
        super.stop();
    }
}
