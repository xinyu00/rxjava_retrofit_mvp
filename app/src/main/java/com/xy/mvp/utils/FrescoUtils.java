package com.xy.mvp.utils;

import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Created by chenxy on 2017/6/23.
 */

public class FrescoUtils {
    /**
     * 初始化Fresco
     * @param context ApplicationContext
     */
    public static void initFresco(Context context) {
        //初始化OkhttpClient
        OkHttpClient okHttpClient = new OkHttpClient();
        //内存缓存配置
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                ConfigConstants.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                Integer.MAX_VALUE,                     // Max entries in the cache
                ConfigConstants.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                Integer.MAX_VALUE,                     // Max length of eviction queue
                Integer.MAX_VALUE);                    // Max cache entry size

        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(context.getApplicationContext(), okHttpClient)
                .setBitmapMemoryCacheParamsSupplier(            //设置内存缓存
                        new Supplier<MemoryCacheParams>() {
                            public MemoryCacheParams get() {
                                return bitmapCacheParams;
                            }
                        })
                .setMainDiskCacheConfig(                        //设置磁盘缓存
                        DiskCacheConfig.newBuilder(context.getApplicationContext())
                                .setBaseDirectoryPath(new File(FileUtils.getAppFile() + "/image/")) // 注意Android运行时权限。
                                .setMaxCacheSize(ConfigConstants.MAX_DISK_CACHE_SIZE)
                                .build()
                )
                .build();
        Fresco.initialize(context.getApplicationContext(), config);     //初始化Fresco
    }

    /**
     * 清除Fresco缓存
     */
    public static void clearCache() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();
        imagePipeline.clearDiskCaches();
        imagePipeline.clearMemoryCaches();
    }
}
