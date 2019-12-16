package com.dyy.tsp.evgb.gateway.protocol.util;

/**
 * 字节操作类
 * created by dyy
 */
@SuppressWarnings("all")
public class ByteUtil {

    private final static char[] digits = {'0', '1'};

    /**
     * 获取一个字节的bit数组
     * @param value
     * @return
     */
    public static byte[] getByteArray(byte value) {
        byte[] byteArr = new byte[8]; //一个字节八位
        for (int i = 7; i > 0; i--) {
            byteArr[i] = (byte) (value & 1); //获取最低位
            value = (byte) (value >> 1); //每次右移一位
        }
        return byteArr;
    }

    /**
     * 把byte转为字符串的bit
     * @param b
     * @return
     */
    public static String byteToBitString(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

    /**
     * 将int型整数转成32位的2进制形式
     * @param num
     * @return String
     */
    public static String to32BinaryString(int num) {
        char[] buf = new char[32];
        int pos = 32;
        int mask = 1;
        do {
            buf[--pos] = digits[num & mask];
            num >>>= 1;
        }
        while (pos > 0);
        return new String(buf, pos, 32);
    }

}
