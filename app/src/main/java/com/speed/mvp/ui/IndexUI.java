package com.speed.mvp.ui;

import com.speed.mvp.R;
import com.speed.mvp.base.BaseActivity;

/**
 * 主界面
 */
public class IndexUI extends BaseActivity {

    @Override
    public void initView() {
        secondClickFinish();
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }

}
