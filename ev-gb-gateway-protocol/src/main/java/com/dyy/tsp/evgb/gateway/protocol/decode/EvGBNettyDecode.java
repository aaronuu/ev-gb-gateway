package com.dyy.tsp.evgb.gateway.protocol.decode;

import com.alibaba.fastjson.JSONObject;
import com.dyy.tsp.evgb.gateway.protocol.entity.DataBody;
import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.protocol.parse.IParse;
import com.dyy.tsp.netty.tcp.decode.AbstractNettyDecode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Netty核心解码器
 * created by dyy
 */
@SuppressWarnings("all")
public class EvGBNettyDecode extends AbstractNettyDecode<EvGBProtocol> {

    /**
     * 解析数据单元
     */
    private IParse parse;

    /**
     * 配置解析上行或者下行
     */
    protected Boolean flag;

    private EvGBProtocol producer = new EvGBProtocol();

    @Override
    protected EvGBProtocol decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        EvGBProtocol protocol = producer.decode(byteBuf);
        DataBody body = protocol.getBody();
        if(body!=null && protocol.getLength()>0){
            JSONObject json = null;
            if(this.getFlag()){
                json = parse.parseUpJson(protocol.getCommandType(), body.getByteBuf());
            }else{
                json = parse.parseDownJson(protocol.getCommandType(),body.getByteBuf());
            }
            body.setJson(json);
        }
        protocol.setBody(body);
        return protocol;
    }

    public EvGBNettyDecode(IParse parse, Boolean flag) {
        this.parse = parse;
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }
}
