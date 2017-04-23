package com.xy.mvp.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xy.mvp.dagger.module.ActivityModule;
import com.xy.mvp.utils.AdapterUtil;
import com.xy.mvp.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * anthor:Created by tianchen on 2017/3/27.
 * email:963181974@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    private LinearLayout ll_content;
    private ViewGroup content;
    private View top;
    private LinearLayout.LayoutParams topparams;
    protected AppManager appManager;
    // 返回按钮按下时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;
    private Unbinder unbinder;
    private boolean flag;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ll_content = new LinearLayout(this);
        //设置当前Activity布局填充满屏幕
        ll_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ll_content.setOrientation(LinearLayout.VERTICAL);   //竖直布局
        setContentView(ll_content);     //Activity填充布局
        flag = true;

        //Activity管理
        appManager = AppManager.getAppManager();
        appManager.addActivity(this);
        //初始化布局
        initView();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onResume();
        if (flag){
            //设置沉浸式标题栏
            setSonView();
            //初始化数据
            initData();
            flag = false;
        }
    }

    /**
     * 设置ll_content的子布局
     */
    private void setSonView(){
        setImmersion();
        //获取子类布局
        content = (ViewGroup) LayoutInflater.from(this).inflate(getLayoutId(), null);
        content.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        //添加主体布局
        ll_content.addView(content);
        //初始化ButterKnife注解框架
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 设置沉浸式布局
     */
    private void setImmersion(){
        // 判断SDK的Api是否大于19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置沉浸式标题栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //添加标题栏view
            top = new View(this);
            topparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AdapterUtil.dp2px(25, this));
            top.setLayoutParams(topparams);
            top.setBackgroundColor(Color.BLUE);
            ll_content.addView(top);
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
     * 设置沉浸式标题栏颜色
     * @param color
     */
    public void setTopColor(@ColorInt int color) {
        top.setBackgroundColor(color);
    }

    /**
     * 沉浸式时，设置标题栏布局是否隐藏
     * @param choose  0:隐藏标题栏 1:移出标题栏
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
     * 设置当前Activity二次点击退出整个APP
     */
    public void secondClickFinish() {
        if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
            currentBackPressedTime = System.currentTimeMillis();
            ToastUtils.showShort("再按一次返回键退出程序");
        } else {
            // 退出
            appManager.finishAllActivity();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
