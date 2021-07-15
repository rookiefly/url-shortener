package com.rookiefly.open.urlshortener.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class EncryptUtil {

    /**
     * 十六进制下数字到字符的映射数组
     */
    private static final String[] hexDigits = {"0", "1", "2", "3",
            "4", "5", "6", "7",
            "8", "9", "A", "B",
            "C", "D", "E", "F"};

    /**
     * 验证输入的密码是否正确
     *
     * @param password    真正的密码（加密后的真密码）
     * @param inputString 输入的字符串
     * @return 验证结果，boolean类型
     */
    public static boolean authenticatePassword(String password, String inputString) {
        return password.equals(encodeByMD5(inputString));
    }

    /**
     * 对字符串进行MD5编码
     */
    public static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] results = md5.digest(originString.getBytes());
                return byteArrayToHexString(results);
            } catch (Exception e) {
                log.error("encodeByMD5 error", e);
            }
        }
        return null;
    }

    /**
     * 轮换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte value : b) {
            resultSb.append(byteToHexString(value));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}