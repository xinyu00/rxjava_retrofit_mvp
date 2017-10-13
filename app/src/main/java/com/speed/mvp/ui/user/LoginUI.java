package com.speed.mvp.ui.user;

import android.app.ProgressDialog;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.speed.mvp.R;
import com.speed.mvp.base.BaseActivity;
import com.speed.mvp.dagger.component.DaggerActivityComponent;
import com.speed.mvp.presenter.user.LoginUIPresenter;
import com.speed.mvp.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 登陆页
 */
public class LoginUI extends BaseActivity {

    @BindView(R.id.et_username)
    EditText mUsername;
    @BindView(R.id.et_password)
    EditText mPassword;
    private ProgressDialog dialog;

    @Inject
    LoginUIPresenter presenter;

    @Override
    public void initData() {
        dialog = new ProgressDialog(this);
        initInject();
    }

    int i = 0;

    //按钮点击
    public void login(View view) {
        ToastUtils.errorShow(this, "吐司" + (++i));
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        boolean checkUserInfo = checkUserInfo(username, password);
        if (checkUserInfo) {
            dialog.show();
            presenter.login(username, password);
        } else {
            ToastUtils.showShort(this, "用户名或密码不能为空" + (++i));
        }
    }

    private void initInject() {
        DaggerActivityComponent.builder()
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    private boolean checkUserInfo(String username, String password) {
        return !TextUtils.isEmpty(username) || !TextUtils.isEmpty(password);
    }

    public void success() {
        dialog.dismiss();
        ToastUtils.showShort(this, "欢迎回来：" + mUsername.getText().toString());
    }

    public void failed() {
        dialog.dismiss();
        ToastUtils.showShort(this, "用户名或密码输入有误");
    }

    @Override
    public void initView() {
        setTopColor(ContextCompat.getColor(this, R.color.button_nor));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
//
//    @Override
//    public void onBackPressed() {
//        secondClickFinish();
//    }
}
