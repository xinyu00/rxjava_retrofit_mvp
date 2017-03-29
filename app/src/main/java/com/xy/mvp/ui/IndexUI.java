package com.xy.mvp.ui;

import android.graphics.Color;

import com.xy.mvp.R;
import com.xy.mvp.base.BaseActivity;

public class IndexUI extends BaseActivity {

    @Override
    public void initView() {
        setTopColor(Color.GREEN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    public void initData() {

    }

}
