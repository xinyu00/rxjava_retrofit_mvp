package com.speed.mvp.ui.user;

import android.Manifest;
import android.content.Intent;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;
import com.speed.mvp.R;
import com.speed.mvp.base.BaseActivity;
import com.speed.mvp.utils.FileUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class UserSelectUI extends BaseActivity {

    @BindView(R.id.iv_content)
    SimpleDraweeView iv_content;

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        baseActivityPresenter.requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},6666);
//        iv_content.setImageResource(R.mipmap.ic_launcher);
        iv_content.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        FileUtils.createAppFile();
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
