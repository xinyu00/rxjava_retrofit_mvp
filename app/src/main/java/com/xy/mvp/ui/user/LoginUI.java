package com.xy.mvp.ui.user;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xy.mvp.R;
import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.dagger.component.DaggerActivityComponent;
import com.xy.mvp.presenter.user.LoginUIPresenter;

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
        setTopColor(R.color.colorAccent);
    }

    //按钮点击
    public void login(View view) {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        boolean checkUserInfo = checkUserInfo(username, password);
        if (checkUserInfo) {
            dialog.show();
            presenter.login(username, password);
        } else {
            Toast.makeText(LoginUI.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(LoginUI.this, "欢迎回来：" + mUsername.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void failed() {
        dialog.dismiss();
        Toast.makeText(LoginUI.this, "用户名或密码输入有误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onBackPressed() {
        secondClickFinish();
    }
}
