package com.xy.mvp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 通过SharedPreferences存取数据的工具类
 */
public class SpUtils {
	private static SharedPreferences sharedPreferences;
	private static String config = "pangu";

	/**
	 * 存储String数据
	 */
	public static String putString(Context context, String Key, String Value) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(config, Context.MODE_PRIVATE);
		}
		sharedPreferences.edit().putString(Key, Value).commit();
		return Key;
	}

	/**
	 * 拿取String数据
	 */
	public static String getString(Context context, String Key, String defvalue) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(config, Context.MODE_PRIVATE);
		}
		return sharedPreferences.getString(Key, defvalue);
	}

	// 保存boolean值
	public static void putBoolean(Context context, String key, boolean value) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(config, Context.MODE_PRIVATE);
		}
		sharedPreferences.edit().putBoolean(key, value).commit();
	}

	// 取boolean值
	public static boolean getBoolean(Context context, String key,
									 boolean defValue) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(config, Context.MODE_PRIVATE);
		}
		return sharedPreferences.getBoolean(key, defValue);
	}

	// 保存int值
	public static void putInt(Context context, String key, int value) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(config, Context.MODE_PRIVATE);
		}
		sharedPreferences.edit().putInt(key, value).commit();
	}

	// 取int值
	public static int getInt(Context context, String key, int defValue) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(config, Context.MODE_PRIVATE);
		}
		return sharedPreferences.getInt(key, defValue);
	}
}