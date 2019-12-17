package com.dyy.tsp.evgb.gateway.tcu.netty;

import com.dyy.tsp.evgb.gateway.protocol.decode.EvGBNettyDecode;
import com.dyy.tsp.evgb.gateway.tcu.config.TcuProperties;
import com.dyy.tsp.netty.client.AbstractNettyClient;
import com.dyy.tsp.redis.handler.RedisHandler;
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

/**
 * TCU模拟终端
 * created by dyy
 */
@SuppressWarnings("all")
public class TcuNettyClient extends AbstractNettyClient {

    public TcuNettyClient(String ip, int port) {
        super(ip, port);
    }

    @Value("${logging.level.io.netty}")
    private String nettyLog;

    @Autowired
    private TcuProperties tcuProperties;

    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private TcuParseHandler tcuParseHandler;

    @Autowired
    private TcuNettyHandler tcuNettyHandler;

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
                        new LengthFieldBasedFrameDecoder(tcuProperties.getMaxFrameLength(), tcuProperties.getLengthFieldOffset()
                                , tcuProperties.getLengthFieldLength(), tcuProperties.getLengthAdjustment()
                                , tcuProperties.getInitialBytesToStrip(), tcuProperties.getFailFast()),
                        new EvGBNettyDecode(tcuParseHandler,Boolean.FALSE),
                        new IdleStateHandler(tcuProperties.getTimeout(),tcuProperties.getTimeout(), 0),
                        tcuNettyHandler
                );
            }
        };
    }

    @PostConstruct
    @Override
    public void connect() {
        super.connect();
    }

}
