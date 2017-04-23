package com.xy.mvp.ui;

import android.content.Intent;
import android.widget.Button;

import com.xy.mvp.R;
import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.utils.LogUtil;
import com.xy.mvp.utils.NetWorkUtils;

import butterknife.OnClick;

public class UserSelectUI extends BaseActivity {

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        LogUtil.e("IP地址", NetWorkUtils.getIPAddress(this));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_select;
    }

    @OnClick({R.id.bt_login, R.id.bt_new_user})
    public void onClick(Button button) {
        Intent intent = new Intent();
        switch (button.getId()) {
            case R.id.bt_login:
                intent.setClass(this, LoginUI.class);
                startActivity(intent);
                break;
            case R.id.bt_new_user:
                intent.setClass(this, RegisterUI.class);
                startActivity(intent);
                break;
        }
    }

}
