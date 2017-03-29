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

import com.xy.mvp.utils.AdapterUtil;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ll_content = new LinearLayout(this);
        llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll_content.setLayoutParams(llParams);
        ll_content.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll_content);
        content = (ViewGroup) LayoutInflater.from(this).inflate(getLayoutId(), null);

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

        ButterKnife.inject(this);
        //初始化布局
        initView();
        //初始化数据
        initData();
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
     * @return  布局id
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
}
