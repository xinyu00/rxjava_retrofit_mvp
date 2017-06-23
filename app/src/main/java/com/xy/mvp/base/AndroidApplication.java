package com.xy.mvp.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.xy.mvp.BuildConfig;
import com.xy.mvp.utils.FrescoUtils;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

public class AndroidApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);
        initEnv();
    }

    public static Context getAppContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * 初始化运行环境
     */
    private void initEnv() {
        //初始化Fresco
        FrescoUtils.initFresco(this);
        //LeakCanary内存泄露检测
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

}
