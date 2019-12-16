package com.dyy.tsp.evgb.gateway.protocol.entity;

import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.evgb.gateway.protocol.common.Constants;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.*;
import com.dyy.tsp.evgb.gateway.protocol.util.ByteUtil;
import com.dyy.tsp.netty.common.IStatus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import java.nio.ByteOrder;

/**
 * 位置数据
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class LocationData implements IStatus {

    /**
     * 定位状态 解析出是否有效定位 经纬度类型
     */
    private Short status;

    /**
     * 是否有效定位
     */
    private Boolean valid;

    /**
     * 经度类型
     */
    private LongitudeType longitudeType;

    /**
     * 纬度类型
     */
    private LatitudeType latitudeType;

    /**
     * 经度
     */
    private Long longitude;

    /**
     * 纬度
     */
    private Long latitude;

    @Override
    public LocationData decode(ByteBuf byteBuf) throws BusinessException {
        LocationData locationData = new LocationData();
        locationData.setStatus(byteBuf.readUnsignedByte());
        locationData.setLongitude(byteBuf.readUnsignedInt());
        locationData.setLatitude(byteBuf.readUnsignedInt());
        char[] chars = ByteUtil.to32BinaryString(locationData.getStatus()).toCharArray();
        locationData.setValid(Constants.CHAR_48 == (chars[chars.length-1]));
        locationData.setLatitudeType(LatitudeType.valuesOfChar(chars[chars.length-2]));
        locationData.setLongitudeType(LongitudeType.valuesOfChar(chars[chars.length-3]));
        return locationData;
    }

    @Override
    public ByteBuf encode() throws BusinessException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.writeByte(status);
        buffer.writeInt(longitude.intValue());
        buffer.writeInt(latitude.intValue());
        return buffer;
    }
}
