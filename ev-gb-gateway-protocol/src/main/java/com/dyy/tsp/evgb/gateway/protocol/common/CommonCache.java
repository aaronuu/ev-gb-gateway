package com.dyy.tsp.evgb.gateway.protocol.common;

import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.protocol.entity.VehicleCache;
import io.netty.channel.Channel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 公用内存
 * created by dyy
 */
@SuppressWarnings("all")
public class CommonCache {

    /**
     * 车架号作为key Channel作为Value
     */
    public static Map<String, Channel> vinChannelMap = new ConcurrentHashMap<>();

    /**
     * Channel作为key 车架号作为value
     */
    public static Map<Channel,String> channelVinMap = new ConcurrentHashMap<>();

    /**
     * 车辆信息缓存，配合Redis的设备缓存使用
     */
    public static Map<String, VehicleCache> vehicleCacheMap = new ConcurrentHashMap<>();

    /**
     * 内存合包
     */
    public static Map<String, List<EvGBProtocol>> mergeList = new ConcurrentHashMap<>();

    /**
     * Debug在线监控
     */
    public static Map<String,String> debugVinMap =new ConcurrentHashMap();

}
