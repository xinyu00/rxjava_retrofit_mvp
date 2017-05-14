package com.xy.mvp.presenter;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.dagger.PerActivity;
import com.xy.mvp.utils.AdapterUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * anthor:Created by tianchen on 2017/5/14.
 * email:963181974@qq.com
 * BaseActivity的Presenter层
 */
@PerActivity
public class BaseActivityPresenter {

    private BaseActivity activity;
    //Activity布局
    private LinearLayout ll_content;
    //权限请求默认标识
    public int REQUEST_CODE_PERMISSION = 0x0009;
    //沉浸式标题头
    private View top;
    @Inject
    public BaseActivityPresenter(BaseActivity activity) {
        this.activity = activity;
    }

    /**
     * 初始化布局
     */
    public void setContentView() {
        ll_content = new LinearLayout(activity);
        //设置当前Activity布局填充满屏幕
        ll_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ll_content.setOrientation(LinearLayout.VERTICAL);   //竖直布局
        activity.setContentView(ll_content);     //Activity填充布局
    }

    /**
     * 设置ll_content的子布局
     */
    public void setSonView() {
        setImmersion();
        //获取子类布局
        ViewGroup content = (ViewGroup) LayoutInflater.from(activity).inflate(activity.getLayoutId(), null);
        content.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //添加主体布局
        ll_content.addView(content);

    }

    /**
     * 设置沉浸式布局
     */
    private void setImmersion() {
        // 判断SDK的Api是否大于19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置沉浸式标题栏
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //添加标题栏view
            top = new View(activity);
            LinearLayout.LayoutParams topparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AdapterUtil.dp2px(25, activity));
            top.setLayoutParams(topparams);
            top.setBackgroundColor(Color.BLUE);
            ll_content.addView(top);
        }
    }

    /**
     * 沉浸式时，设置标题栏布局是否隐藏
     *
     * @param choose 0:隐藏标题栏 1:移出标题栏
     */
    public void setTopShow(int choose) {
        if (top == null) {
            return;
        }
        switch (choose) {
            case 0:
                top.setVisibility(View.GONE);
                break;
            case 1:
                ll_content.removeView(top);
                break;
        }
    }

    /**
     * 设置沉浸式标题栏颜色
     *
     * @param color 颜色值
     */
    public void setTopColor(@ColorInt int color) {
        top.setBackgroundColor(color);
    }

    /**
     * 确认所有的权限是否都已授权
     */
    public boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测所有的权限是否都已授权
     */
    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     */
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }

    /**
     * 请求权限
     *
     * @param permissions 请求的权限
     * @param requestCode 请求权限的请求码
     */
    @TargetApi(value = Build.VERSION_CODES.M)
    public void requestPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;
        if (checkPermissions(permissions)) {
            activity.permissionSuccess(REQUEST_CODE_PERMISSION);
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(activity, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    /**
     * 显示提示对话框
     */
    public void showTipsDialog() {
        new AlertDialog.Builder(activity)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }


    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }

}
