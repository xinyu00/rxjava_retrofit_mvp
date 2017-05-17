package com.xy.mvp.ui;

import com.xy.mvp.R;
import com.xy.mvp.base.BaseActivity;

/**
 * 主界面
 */
public class IndexUI extends BaseActivity {

    @Override
    public void initView() {
        setTopColor(R.color.colorAccent);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_index;
    }

}
