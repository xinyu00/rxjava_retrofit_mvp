package com.xy.mvp.ui;

import com.xy.mvp.R;
import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.view.MessageDialog;

/**
 * 指引导航页
 */
public class NavigateUI extends BaseActivity {

    @Override
    public void initView() {

    }


    @Override
    public void initData() {
        MessageDialog.getInstance().showDialog("测试3");
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_navigate;
    }


}
