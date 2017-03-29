package com.xy.mvp.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xy.mvp.master.AppManager;
import com.xy.mvp.utils.AdapterUtil;
import com.xy.mvp.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * anthor:Created by tianchen on 2017/3/27.
 * email:963181974@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity {
    private LinearLayout ll_content;
    private ViewGroup content;
    private View top;
    private LinearLayout.LayoutParams llParams;
    private LinearLayout.LayoutParams topparams;
    protected AppManager appManager;
    // 返回按钮按下时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ll_content = new LinearLayout(this);
        llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll_content.setLayoutParams(llParams);
        ll_content.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll_content);
        content = (ViewGroup) LayoutInflater.from(this).inflate(getLayoutId(), null);
        appManager = AppManager.getAppManager();
        // 设置沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            top = new View(this);
            topparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AdapterUtil.dp2px(25, this));
            top.setLayoutParams(topparams);
            top.setBackgroundColor(Color.BLUE);
            ll_content.addView(top);
        }
        ll_content.addView(content);
        appManager.addActivity(this);
        ButterKnife.inject(this);
        //初始化布局
        initView();
        //初始化数据
        initData();
    }

    @Override
    public void onBackPressed() {
        AppManager.getAppManager().finishActivity();
    }

    /**
     * 保留方法 (初始化布局)
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

    public void setTopColor(@ColorInt int color) {
        top.setBackgroundColor(color);
    }

    public void setTopShow(int choose) {
        switch (choose) {
            case 0:
                top.setVisibility(View.GONE);
                break;
            case 1:
                ll_content.removeView(top);
                break;
        }
    }

    public void secondClickFinish() {
        if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
            currentBackPressedTime = System.currentTimeMillis();
            ToastUtils.showShort("再按一次返回键退出程序");
        } else {
            // 退出
            appManager.finishAllActivity();
        }
    }
}
