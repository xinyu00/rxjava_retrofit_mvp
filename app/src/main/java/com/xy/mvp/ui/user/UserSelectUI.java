package com.xy.mvp.ui.user;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import com.xy.mvp.R;
import com.xy.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UserSelectUI extends BaseActivity {

    @BindView(R.id.iv_content)
    ImageView iv_content;

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
//        Fresco.initialize(this);
//        Glide.with(this)
//                .load(R.mipmap.ic_launcher)
//                .into(iv_content);
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
//                intent.setClass(this, LoginUI.class);
                startActivity(intent);
                break;
            case R.id.bt_new_user:
                intent.setClass(this, RegisterUI.class);
                startActivity(intent);
                break;
        }
    }
}
