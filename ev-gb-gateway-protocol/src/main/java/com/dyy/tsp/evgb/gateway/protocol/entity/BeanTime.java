package com.dyy.tsp.evgb.gateway.protocol.entity;

import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.netty.common.IStatus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import java.util.Calendar;

/**
 * 时间抽象
 * 遵守国家标准
 * created by dyy
 */
@SuppressWarnings("all")
@Data
public class BeanTime implements IStatus {

    private short year;

    private short month;

    private short day;

    private short hours;

    private short minutes;

    private short seconds;

    public BeanTime() {
    }

    public BeanTime(short year, short month, short day, short hours, short minutes, short seconds) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }


    public BeanTime(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        this.year = Short.valueOf(year.substring(year.length() - 2));
        this.month = (short) (calendar.get(Calendar.MONTH) + 1);
        this.day = (short) calendar.get(Calendar.DAY_OF_MONTH);
        ;
        this.hours = (short) calendar.get(Calendar.HOUR_OF_DAY);
        ;
        this.minutes = (short) calendar.get(Calendar.MINUTE);
        ;
        this.seconds = (short) calendar.get(Calendar.SECOND);
        ;
    }
    public String formatTime() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("%02d", this.year))
                .append(String.format("%02d", this.month))
                .append(String.format("%02d", this.day))
                .append(String.format("%02d", this.hours))
                .append(String.format("%02d", this.minutes))
                .append(String.format("%02d", this.seconds));
        return stringBuffer.toString();
    }

    public void beanTimeByDay(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        this.year = (short) calendar.get(Calendar.YEAR);
        this.month = (short) (calendar.get(Calendar.MONTH) + 1);
        this.day = (short) calendar.get(Calendar.DAY_OF_MONTH);

    }
    public String formatTimeByDay() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("%04d", this.year))
                    .append(String.format("%02d", this.month))
                    .append(String.format("%02d", this.day));
        return stringBuffer.toString();
    }

    @Override
    public BeanTime decode(ByteBuf byteBuf) throws BusinessException {
        BeanTime beanTime = new BeanTime();
        beanTime.setYear(byteBuf.readUnsignedByte());
        beanTime.setMonth(byteBuf.readUnsignedByte());
        beanTime.setDay(byteBuf.readUnsignedByte());
        beanTime.setHours(byteBuf.readUnsignedByte());
        beanTime.setMinutes(byteBuf.readUnsignedByte());
        beanTime.setSeconds(byteBuf.readUnsignedByte());
        return beanTime;
    }

    @Override
    public ByteBuf encode() throws BusinessException {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(year);
        byteBuf.writeByte(month);
        byteBuf.writeByte(day);
        byteBuf.writeByte(hours);
        byteBuf.writeByte(minutes);
        byteBuf.writeByte(seconds);
        return byteBuf;
    }
}
