package com.xy.mvp.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xy.mvp.R;
import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.dagger.component.DaggerLoginUIComponent;
import com.xy.mvp.dagger.module.LoginUIModule;
import com.xy.mvp.master.AppManager;
import com.xy.mvp.presenter.LoginUIPresenter;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * 登陆页面
 */
public class LoginUI extends BaseActivity {

    @InjectView(R.id.username)
    EditText mUsername;
    @InjectView(R.id.password)
    EditText mPassword;
    private ProgressDialog dialog;

    @Inject
    LoginUIPresenter presenter;

    @Override
    public void initData() {
        dialog = new ProgressDialog(this);
        setTopColor(Color.BLUE);
        //使用dagger2
        DaggerLoginUIComponent component = (DaggerLoginUIComponent) DaggerLoginUIComponent.builder().loginUIModule(new LoginUIModule(this)).build();
        component.in(this);
    }

    //按钮点击
    public void login(View view) {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        AppManager.getActivity(this.getClass());
        boolean checkUserInfo = checkUserInfo(username, password);
        if (checkUserInfo) {
            dialog.show();
            presenter.login(username, password);
        } else {
            Toast.makeText(LoginUI.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkUserInfo(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
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
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        secondClickFinish();
    }
}
