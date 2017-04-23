package com.xy.mvp.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.xy.mvp.BuildConfig;
import com.xy.mvp.utils.LogUtil;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

public class AndroidApplication extends Application {
    private static Context content;

    @Override
    public void onCreate() {
        super.onCreate();
        this.content = getApplicationContext();
        initEnv();
    }

    public static Context getInstance() {
        return content;
    }

    private void initEnv() {
        LogUtil.logInit(BuildConfig.DEBUG);
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

}
