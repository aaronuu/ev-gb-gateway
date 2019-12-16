package com.dyy.tsp.evgb.gateway.protocol.util;

import com.dyy.tsp.evgb.gateway.protocol.enumtype.CachePrefixEnum;

/**
 * created dy dyy
 */
@SuppressWarnings("all")
public class HelperKeyUtil {

    /**
     * 生成内存中车辆缓存固定规则Key
     * @param vin
     * @return
     */
    public static String getKey(String vin) {
        StringBuilder sb = new StringBuilder();
        sb.append(CachePrefixEnum.VEHICLE_CACHE.getPrefix());
        sb.append(vin);
        return sb.toString();
    }
}
