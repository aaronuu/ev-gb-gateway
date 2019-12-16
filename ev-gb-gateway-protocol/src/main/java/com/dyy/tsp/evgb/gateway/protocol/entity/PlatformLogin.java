package com.dyy.tsp.evgb.gateway.protocol.entity;

import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.evgb.gateway.protocol.common.Constants;
import com.dyy.tsp.evgb.gateway.protocol.enumtype.EncryptionType;
import com.dyy.tsp.netty.common.IStatus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * 平台登入
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class PlatformLogin implements IStatus {

    private static final BeanTime producer = new BeanTime();

    //平台登入时间
    private BeanTime beanTime;

    //平台登入流水号
    private Integer serialNum;

    //平台登入用户名 12位
    private String userName;

    //平台登入用户名 20位
    private String password;

    //加密方式
    private EncryptionType encryptionType;

    @Override
    public PlatformLogin decode(ByteBuf byteBuf) throws BusinessException {
        PlatformLogin platformLogin = new PlatformLogin();
        BeanTime beanTime = producer.decode(byteBuf);
        platformLogin.setBeanTime(beanTime);
        platformLogin.setSerialNum(byteBuf.readUnsignedShort());
        platformLogin.setUserName(byteBuf.readSlice(12).toString(Charset.forName(Constants.UTF_8)));
        platformLogin.setPassword(byteBuf.readSlice(20).toString(Charset.forName(Constants.UTF_8)));
        platformLogin.setEncryptionType(EncryptionType.valuesOf(byteBuf.readUnsignedByte()));
        return platformLogin;
    }

    @Override
    public ByteBuf encode() throws BusinessException {
        if(userName.length()!=12){
            throw new BusinessException("userName length must be 12");
        }
        if(password.length()!=20){
            throw new BusinessException("password length must be 20");
        }
        ByteBuf buffer = Unpooled.buffer();
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.writeBytes(beanTime.encode());
        buffer.writeShort(serialNum);
        buffer.writeBytes(userName.getBytes(Charset.forName(Constants.UTF_8)));
        buffer.writeBytes(password.getBytes(Charset.forName(Constants.UTF_8)));
        buffer.writeByte(encryptionType.getId());
        return buffer;
    }
}
