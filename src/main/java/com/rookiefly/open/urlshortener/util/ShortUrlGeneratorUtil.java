package com.rookiefly.open.urlshortener.util;

import java.util.Random;

/**
 * Generator ShortUrl
 * 数据库中需要保存长链与短链之间的关系，通过访问短链跳转到长链接
 */
public class ShortUrlGeneratorUtil {

    public static void main(String[] args) {
        String sLongUrl = "https://rookiefly.github.io";
        System.out.println(shortUrl(sLongUrl));
    }

    public static String shortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "rookiefly";
        // 要使用生成 URL 的字符
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };
        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = (EncryptUtil.encodeByMD5(key + url));
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        //得到4组短链接字符串
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照8位一组16进制与0x3FFFFFFF进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用long型来转换，因为Inteper.parseInt()只能处理31位,首位为符号位,如果不用long，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            //循环获得每组6位的字符串
            for (int j = 0; j < 6; j++) {
                // 把得到的值与0x0000003D进行位与运算，取得字符数组chars 索引(具体需要看chars数组的长度  以防下标溢出，注意起点为0)
                long index = 0x0000003D & lHexLong;
                //把取得的字符相加
                outChars += chars[(int) index];
                //每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        //4组短链接字符串随机选一个
        int seed = new Random().nextInt(4);
        return resUrl[seed];
    }
}