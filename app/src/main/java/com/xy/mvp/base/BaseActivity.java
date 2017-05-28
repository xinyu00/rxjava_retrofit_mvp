package com.xy.mvp.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xy.mvp.dagger.component.DaggerActivityComponent;
import com.xy.mvp.dagger.module.ActivityModule;
import com.xy.mvp.presenter.BaseActivityPresenter;
import com.xy.mvp.utils.AppCompatUtils;
import com.xy.mvp.utils.LogUtils;
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
    //Activity布局
    private LinearLayout ll_content;
    //沉浸式标题头
    public View top;
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
        setContentView();
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
     * 设置沉浸式头布局
     */
    public View setImmersion() {
        // 判断SDK的Api是否大于19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            top = new View(this);
            //设置沉浸式标题栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            LinearLayout.LayoutParams topparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AppCompatUtils.dp2px(25, this));
            top.setLayoutParams(topparams);
            top.setBackgroundColor(0x21baf5);
        }
        return top;
    }

    /**
     * 沉浸式时，设置标题栏布局是否隐藏
     *
     * @param choose 0:隐藏标题栏 1:移出标题栏
     */
    public void setTopShow(int choose) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            switch (choose) {
                case 0:
                    top.setVisibility(View.GONE);
                    break;
                case 1:
                    top.setVisibility(View.VISIBLE);
                    break;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar !=null){
                actionBar.hide();
            }
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 初始化布局
     */
    public void setContentView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ll_content = new LinearLayout(this);
            //设置当前Activity布局填充满屏幕
            ll_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            ll_content.setOrientation(LinearLayout.VERTICAL);   //竖直布局
            setContentView(ll_content);     //Activity填充布局
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setContentView(getLayoutId());
        } else {
            setContentView(getLayoutId());
        }
    }

    /**
     * 设置ll_content的子布局
     */
    public void setSonView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ll_content.addView(setImmersion());
            //获取子类布局
            ViewGroup content = (ViewGroup) LayoutInflater.from(this).inflate(getLayoutId(), null);
            content.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //添加主体布局
            ll_content.addView(content);
        }
    }

    /**
     * 获取权限成功
     *
     * @param requestCode 获取权限成功后返回的code值
     */
    public void permissionSuccess(int requestCode) {
        LogUtils.e(TAG, "获取权限成功=" + requestCode);
    }

    /**
     * 权限获取失败
     *
     * @param requestCode 获取权限失败后返回的code值
     */
    public void permissionFail(int requestCode) {
        LogUtils.e(TAG, "获取权限失败=" + requestCode);
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
            setSonView();
            //初始化ButterKnife注解框架
            unbinder = ButterKnife.bind(this);
            //初始化数据
            initData();
            onStartFlag = false;
        }
    }

    /**
     * 设置top颜色
     */
    public void setTopColor(int color) {
        if (top !=null){
            top.setBackgroundResource(color);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(color);
            }
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
