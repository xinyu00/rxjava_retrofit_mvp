package com.xy.mvp.utils;

import android.util.Log;

/**
 * log管理类
 */
public class LogUtil {
	/**
	 * 是否打印输出
	 */
	public static boolean isPrint = true;

	/**
	 * 关闭或打开d（蓝色）输出
	 */
	public static boolean isPrint_d = true;

	/**
	 * 关闭或打开e（红色）输出
	 */
	public static boolean isPrint_e = true;

	/**
	 * 关闭或打开w（黄色）输出
	 */
	public static boolean isPrint_w = true;

	/**
	 * 关闭或打开i（绿色）输出
	 */
	public static boolean isPrint_i = true;

	/**
	 * 关闭或打开v（黑色）输出
	 */
	public static boolean isPrint_v = true;

	/**
	 * Log输出的前部分Tag
	 */
	public static String TAG = "XJ";

	/**
	 * 使用自定义TAG打印（颜色是蓝色）
	 * 
	 * @param tag
	 * @param content
	 */
	public static void d(String tag, String content) {
		if (isPrint && isPrint_d) {
			Log.d(TAG + "-" + tag, content);
		}
	}

	/**
	 * 使用默认TAG打印（颜色是蓝色）
	 * 
	 * @param content
	 */
	public static void d(String content) {
		if (isPrint && isPrint_d) {
			Log.d(TAG, content);
		}
	}

	/**
	 * 使用自定义TAG打印（颜色为黑色）
	 * 
	 * @param tag
	 * @param content
	 */
	public static void v(String tag, String content) {
		if (isPrint && isPrint_v) {
			Log.v(TAG + "-" + tag, content);
		}
	}

	/**
	 * 使用默认TAG打印（颜色为黑色）
	 * 
	 * @param content
	 */
	public static void v(String content) {
		if (isPrint && isPrint_v) {
			Log.v(TAG, content);
		}
	}

	/**
	 * 使用自定义TAG打印（颜色为黄色）
	 * 
	 * @param tag
	 * @param content
	 */
	public static void w(String tag, String content) {
		if (isPrint && isPrint_w) {
			Log.w(TAG + "-" + tag, content);
		}
	}

	/**
	 * 使用默认TAG打印（颜色为黄色）
	 * 
	 * @param content
	 */
	public static void w(String content) {
		if (isPrint && isPrint_w) {
			Log.w(TAG, content);
		}
	}

	/**
	 * 使用自定义TAG打印（颜色为红色）
	 * 
	 * @param tag
	 * @param content
	 */
	public static void e(String tag, String content) {
		if (isPrint && isPrint_e) {
			Log.e(TAG + "-" + tag, content);
		}
	}

	/**
	 * 使用默认TAG打印（颜色为红色）
	 * 
	 * @param content
	 */
	public static void e(String content) {
		if (isPrint && isPrint_e) {
			Log.e(TAG, content);
		}
	}

	/**
	 * 使用自定义TAG打印（颜色为绿色）
	 * 
	 * @param tag
	 * @param content
	 */
	public static void i(String tag, String content) {
		if (isPrint && isPrint_i) {
			Log.i(TAG + "-" + tag, content);
		}
	}

	/**
	 * 使用默认TAG打印（颜色为绿色）
	 * 
	 * @param content
	 */
	public static void i(String content) {
		if (isPrint && isPrint_i) {
			Log.i(TAG, content);
		}
	}
	/**
	 * 	打印长log
	 */
	public static void logE(String tag, String content) {
		if (isPrint && isPrint_d) {
			int p = 2000;
			long length = content.length();
			if (length < p || length == p)
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
