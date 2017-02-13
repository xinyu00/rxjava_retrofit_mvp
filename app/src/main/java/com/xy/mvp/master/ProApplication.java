package com.xy.mvp.master;

import android.app.Application;
import android.content.Context;

/**
 * Created by 陈新宇 on 2017/2/13.
 */

public class ProApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
