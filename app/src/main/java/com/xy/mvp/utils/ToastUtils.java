package com.xy.mvp.utils;


import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import static com.xy.mvp.BuildConfig.DEBUG;

/**
 * Toast统一管理类
 */
public class ToastUtils {
    //Toast弹出标识
    private static boolean isShow = true;
    private static Toast toast;

    private ToastUtils() {
        throw new UnsupportedOperationException("toastutils cannot be new");
    }

    private static boolean getIsShow() {
        return isShow;
    }

    public static void setIsShow(boolean flag) {
        isShow = flag;
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, CharSequence message) {
        if (getIsShow()) {
            cancel();
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private static void cancel() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }


    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, int message) {
        if (getIsShow()) {
            cancel();
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, CharSequence message) {
        if (getIsShow()) {
            cancel();
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, int message) {
        if (getIsShow()) {
            cancel();
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (getIsShow()) {
            cancel();
            toast = Toast.makeText(context.getApplicationContext(), message, duration);
            toast.show();
        }
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, int message, int duration) {
        if (getIsShow()) {
            cancel();
            toast = Toast.makeText(context.getApplicationContext(), message, duration);
            toast.show();
        }
    }

    /**
     * 错误提示 （可用于debug模式下网络请求错误提示，replease模式下自动关闭）
     */
    public static void errorShow(Context context, CharSequence message) {
        if (DEBUG) {
            showShort(context.getApplicationContext(), message);
        }
    }

    /**
     * 自定义显示位置 居中
     */
    public static void centerShow(Context context, CharSequence message) {
        if (getIsShow()) {
            cancel();
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}