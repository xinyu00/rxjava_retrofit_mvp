package com.xy.mvp.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * anthor:Created by tianchen on 2017/5/17.
 * email:963181974@qq.com
 * 文件工具类
 */

public class FileUtils {
    private static String TAG = "FileUtils";
    private static String DEFAULT_ENCODE = "UTF-8";
    private static String appPath = "/xyapp/";

    /**
     * 获取App文件目录
     */
    public static String getAppFilePath() {
        return getInnerSDCardPath() + appPath;
    }

    /**
     * 获取App文件目录
     */
    public static File getAppFile() {
        return new File(getInnerSDCardPath() + appPath);
    }

    /**
     * 创建App文件目录
     */
    public static void createAppFile() {
        String path = getInnerSDCardPath();
        createDirectory(path + appPath);
        createDirectory(path + appPath + "log/");
        createDirectory(path + appPath + "image/");
    }

    /**
     * 判断内置sd卡是否可用
     *
     * @return true 能 false 不能
     */
    private static boolean getSDcardIsExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取内置SD卡路径
     */
    private static String getInnerSDCardPath() {
        if (getSDcardIsExist()) {
            return Environment.getExternalStorageDirectory().getPath();
        } else {
            LogUtils.e(TAG, "无法获取内置sd卡");
            return null;
        }
    }

    /**
     * 获取内置SD卡路径
     */
    public static File getInnerSDCardFile() {
        if (getSDcardIsExist()) {
            return Environment.getExternalStorageDirectory();
        } else {
            LogUtils.e(TAG, "无法获取内置sd卡");
            return null;
        }
    }

    /**
     * 获取文件路径
     */
    public static File getFilePath() {
        if (getSDcardIsExist()) {
            return Environment.getExternalStorageDirectory();
        } else {
            LogUtils.e(TAG, "无法获取内置sd卡");
            return null;
        }
    }

    /**
     * 创建目录
     */

    public static void createDirectory(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        try {
            // 获得文件对象
            File f = new File(path);
            if (!f.exists()) {
                // 如果路径不存在,则创建
                boolean flag = f.mkdirs();
                LogUtils.e(TAG, "createDirectory中mkdirs方法执行:" + flag);
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "创建目录错误.path=" + path + "\n" + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 新建文件.
     *
     * @param path 文件路径
     */
    public static void createFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        try {
            // 获得文件对象
            File f = new File(path);
            if (f.exists()) {
                return;
            }
            // 如果路径不存在,则创建
            if (!f.getParentFile().exists()) {
                boolean flag = f.getParentFile().mkdirs();
                LogUtils.e(TAG, "createFile中mkdirs方法执行:" + flag);
            }
            boolean flag = f.createNewFile();
            LogUtils.e(TAG, "createFile中createNewFile方法执行:" + flag);
        } catch (Exception e) {
            LogUtils.e(TAG, "创建文件错误.path=" + path + "\n" + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 保存文件(文件不存在会自动创建).
     *
     * @param path    文件路径
     * @param content 文件内容
     */
    public static void saveFile(String path, String content) {
        saveFile(path, content, DEFAULT_ENCODE);
    }

    /**
     * 保存文件(文件不存在会自动创建).
     *
     * @param path     文件路径
     * @param content  文件内容
     * @param encoding 编码(UTF-8/gb2312/...)
     */
    public static void saveFile(String path, String content, String encoding) {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bw = null;
        try {
            // 获得文件对象
            File f = new File(path);
            // 默认编码UTF-8
            encoding = (TextUtils.isEmpty(encoding)) ? DEFAULT_ENCODE : encoding;
            // 如果路径不存在,则创建
            if (!f.getParentFile().exists()) {
                boolean flag = f.getParentFile().mkdirs();
                LogUtils.e(TAG, "mkdirs方法执行" + flag);
            }
            // 开始保存文件
            fileOutputStream = new FileOutputStream(path);
            bw = new BufferedOutputStream(fileOutputStream);
            bw.write(content.getBytes(encoding));
        } catch (Exception e) {
            LogUtils.e(TAG, "保存文件错误.path=" + path + ",content=" + content + ",encoding=" + "\n" + e.toString());
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
