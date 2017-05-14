package com.xy.mvp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.xy.mvp.dagger.component.DaggerActivityComponent;
import com.xy.mvp.dagger.module.ActivityModule;
import com.xy.mvp.presenter.BaseActivityPresenter;
import com.xy.mvp.utils.LogUtil;
import com.xy.mvp.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * anthor:Created by tianchen on 2017/3/27.
 * email:963181974@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    // 返回按钮按下时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;
    //ButterKnife
    private Unbinder unbinder;
    //onStart方法实现只执行一次标识
    private boolean onStartFlag;
    //Log标识
    private final String TAG = "MPermissions";
    @Inject
    protected BaseActivityPresenter baseActivityPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onStartFlag = true;
        //初始化baseActivityPresenter层
        DaggerActivityComponent.builder()
                .activityModule(getActivityModule())
                .build()
                .inject(this);

        //初始化Activity默认布局
        baseActivityPresenter.setContentView();
        //Activity管理
        AppManager.getAppManager().addActivity(this);

        //初始化布局
        initView();
    }

    /**
     * 请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == baseActivityPresenter.REQUEST_CODE_PERMISSION) {
            if (baseActivityPresenter.verifyPermissions(grantResults)) {
                permissionSuccess(baseActivityPresenter.REQUEST_CODE_PERMISSION);
            } else {
                permissionFail(baseActivityPresenter.REQUEST_CODE_PERMISSION);
                baseActivityPresenter.showTipsDialog();
            }
        }
    }

    /**
     * 获取权限成功
     *
     * @param requestCode 获取权限成功后返回的code值
     */
    public void permissionSuccess(int requestCode) {
        LogUtil.e(TAG, "获取权限成功=" + requestCode);
    }

    /**
     * 权限获取失败
     *
     * @param requestCode 获取权限失败后返回的code值
     */
    public void permissionFail(int requestCode) {
        LogUtil.e(TAG, "获取权限失败=" + requestCode);
    }

    /**
     * 获取ActivityModule
     *
     * @return dagger2 module层
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onStart() {
        super.onResume();
        if (onStartFlag) {
            //设置沉浸式标题栏
            baseActivityPresenter.setSonView();
            //初始化ButterKnife注解框架
            unbinder = ButterKnife.bind(this);
            //初始化数据
            initData();
            onStartFlag = false;
        }
    }

    /**
     * 默认Activity返回键退出Activity
     */
    @Override
    public void onBackPressed() {
        AppManager.getAppManager().finishActivity();
    }

    /**
     * 保留方法 (初始化布局,目前ButterKnife实现)
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 用于获取布局的id
     *
     * @return 布局id
     */
    public abstract int getLayoutId();

    /**
     * 设置当前Activity二次点击退出整个APP
     */
    public void secondClickFinish() {
        if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
            currentBackPressedTime = System.currentTimeMillis();
            ToastUtils.showShort("再按一次返回键退出程序");
        } else {
            // 退出
            ToastUtils.setIsShow(false);
            AppManager.getAppManager().AppExit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
