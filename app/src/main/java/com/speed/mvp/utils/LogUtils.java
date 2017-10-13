package com.speed.mvp.utils;

import android.util.Log;

import static com.speed.mvp.BuildConfig.DEBUG;

/**
 * log管理类
 */
public class LogUtils {
    /**
     * 是否打印输出
     */
    private static boolean isPrint = false;

    /**
     * 关闭或打开d（蓝色）输出
     */
    private static boolean isPrint_d = true;

    /**
     * 关闭或打开e（红色）输出
     */
    private static boolean isPrint_e = true;

    /**
     * 关闭或打开w（黄色）输出
     */
    private static boolean isPrint_w = true;

    /**
     * 关闭或打开i（绿色）输出
     */
    private static boolean isPrint_i = true;

    /**
     * 关闭或打开v（黑色）输出
     */
    private static boolean isPrint_v = true;

    /**
     * Log输出的前部分Tag
     */
    private static String TAG = "APP";

    /**
     * 使用自定义TAG打印（颜色是蓝色）
     */
    public static void d(String tag, String content) {
        if (DEBUG && isPrint_d) {
            Log.d(TAG + "-" + tag, content);
        }
    }

    /**
     * 使用默认TAG打印（颜色是蓝色）
     */
    public static void d(String content) {
        if (DEBUG && isPrint_d) {
            Log.d(TAG, content);
        }
    }

    /**
     * 使用自定义TAG打印（颜色为黑色）
     */
    public static void v(String tag, String content) {
        if (DEBUG && isPrint_v) {
            Log.v(TAG + "-" + tag, content);
        }
    }

    /**
     * 使用默认TAG打印（颜色为黑色）
     */
    public static void v(String content) {
        if (DEBUG && isPrint_v) {
            Log.v(TAG, content);
        }
    }

    /**
     * 使用自定义TAG打印（颜色为黄色）
     */
    public static void w(String tag, String content) {
        if (DEBUG && isPrint_w) {
            Log.w(TAG + "-" + tag, content);
        }
    }

    /**
     * 使用默认TAG打印（颜色为黄色）
     */
    public static void w(String content) {
        if (isPrint && isPrint_w) {
            Log.w(TAG, content);
        }
    }

    /**
     * 使用自定义TAG打印（颜色为红色）
     */
    public static void e(String tag, String content) {
        if (DEBUG && isPrint_e) {
            Log.e(TAG + "-" + tag, content);
        }
    }

    /**
     * 使用默认TAG打印（颜色为红色）
     */
    public static void e(String content) {
        if (DEBUG && isPrint_e) {
            Log.e(TAG, content);
        }
    }

    /**
     * 使用自定义TAG打印（颜色为绿色）
     */
    public static void i(String tag, String content) {
        if (DEBUG && isPrint_i) {
            Log.i(TAG + "-" + tag, content);
        }
    }

    /**
     * 使用默认TAG打印（颜色为绿色）
     */
    public static void i(String content) {
        if (isPrint && isPrint_i) {
            Log.i(TAG, content);
        }
    }

    /**
     * 打印长log
     */
    public static void longE(String tag, String content) {
        if (DEBUG && isPrint_d) {
            int p = 2000;
            long length = content.length();
            if (length <= p)
                Log.e(tag, content);
            else {
                while (content.length() > p) {
                    String logContent = content.substring(0, p);
                    content = content.replace(logContent, "");
                    Log.e(tag, logContent);
                }
                Log.e(tag, content);
            }
        }
    }
}
