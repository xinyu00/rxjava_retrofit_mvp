package com.xy.mvp.utils;

import android.content.Context;

/**
 * anthor:Created by tianchen on 2017/3/28.
 * email:963181974@qq.com
 * 适配工具类
 */

public class AdapterUtil {
    /**
     * dp转换px
     *
     * @param dp    dp值
     * @param context   上下文
     * @return  转换后的px值
     */
    public static int dp2px(int dp, Context context) {
        // px = dp * 密度比
        float density = context.getResources().getDisplayMetrics().density; // 0.75 1 1.5 2
        return (int) (dp * density + 0.5f);// 4舍5入
    }
}
