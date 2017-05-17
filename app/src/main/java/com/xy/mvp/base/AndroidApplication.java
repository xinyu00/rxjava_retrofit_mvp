package com.xy.mvp.base;

import android.app.Application;
import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.leakcanary.LeakCanary;
import com.xy.mvp.BuildConfig;
import com.xy.mvp.utils.FileUtils;

import java.io.File;

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

    /**
     * 初始化运行环境
     */
    private void initEnv() {
        Fresco.initialize(this);
        Fresco.initialize(this, ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(this)
                                .setBaseDirectoryPath(new File(FileUtils.getAppFile()+"image")) // 注意Android运行时权限。
                                .build()
                )
                .build()
        );
        //LeakCanary内存泄露检测
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

}
