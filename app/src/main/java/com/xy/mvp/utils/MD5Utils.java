package com.xy.mvp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * anthor:Created by tianchen on 2017/3/27.
 * email:963181974@qq.com
 */

public class MD5Utils {
    public static String getEncryption(String originString){
        String result = "";
        if (originString != null) {
            try {
                // 指定加密的方式为MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 进行加密运算
                byte bytes[] = md.digest(originString.getBytes());
                for (byte aByte : bytes) {
                    // 将整数转换成十六进制形式的字符串 这里与0xff进行与运算的原因是保证转换结果为32位
                    String str = Integer.toHexString(aByte & 0xFF);
                    if (str.length() == 1) {
                        str += "F";
                    }
                    result += str;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

