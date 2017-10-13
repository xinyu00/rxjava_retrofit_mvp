package com.speed.mvp.utils;

import com.facebook.common.util.ByteConstants;

/**
 * Created by 陈新宇 on 2017/6/14.
 * App配置常量
 */

public class ConfigConstants {
    //最大内存使用率
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    //最大磁盘缓存大小
    public static final int MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB;
    //最大内存缓存大小
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
}
