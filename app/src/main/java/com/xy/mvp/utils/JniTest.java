package com.xy.mvp.utils;

/**
 * anthor:Created by tianchen on 2017/2/17.
 * email:963181974@qq.com
 */

public class JniTest {
    static {
        System.loadLibrary("native-lib");
    }
   public native static String getStockMarketCode(String code);
}
