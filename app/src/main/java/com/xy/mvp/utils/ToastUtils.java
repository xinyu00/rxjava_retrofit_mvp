package com.xy.mvp.utils;


import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;
import com.xy.mvp.base.AppManager;
import static com.xy.mvp.BuildConfig.DEBUG;

/**
 * Toast统一管理类
 */
public class ToastUtils {
    //Toast弹出标识
    private static boolean isShow = true;

    private ToastUtils() {
        throw new UnsupportedOperationException("toastutils cannot be new");
    }

    private static boolean getIsShow() {
        return isShow;
    }

    public static void setIsShow(boolean flag) {
        isShow = flag;
    }

    private static Activity getActivity() {
        return AppManager.getAppManager().currentActivity();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(CharSequence message) {
        if (getIsShow())
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 短时间显示Toast
     */
    public static void showShort(int message) {
        if (getIsShow())
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(CharSequence message) {
        if (getIsShow())
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(int message) {
        if (getIsShow())
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(CharSequence message, int duration) {
        if (getIsShow())
            Toast.makeText(getActivity(), message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(int message, int duration) {
        if (getIsShow())
            Toast.makeText(getActivity(), message, duration).show();
    }

    public static void errorShow(CharSequence message) {
        if (DEBUG)
            showShort(message);
    }

    /**
     * 自定义显示位置 居中
     */
    public static void centerShow(CharSequence message) {
        if (getIsShow()) {
            Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

}