package com.xy.mvp.master;

import android.app.Application;

/**
 * Created by 陈新宇 on 2017/2/13.
 */

public class ProApplication extends Application {
    private static ProApplication instance;
    public static ProApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
