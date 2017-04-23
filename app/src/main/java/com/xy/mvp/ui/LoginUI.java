package com.xy.mvp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xy.mvp.R;
import com.xy.mvp.base.AppManager;
import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.presenter.LoginUIPresenter;
import com.xy.mvp.view.MessageDialog;

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
        setTopColor(Color.BLUE);
        //使用dagger2
        MessageDialog.getInstance().showDialog("测试1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MessageDialog.getInstance().showDialog("测试2", new MessageDialog.BackListener() {
                            @Override
                            public void doBack() {
                                startActivity(new Intent(LoginUI.this, NavigateUI.class));
                            }
                        });
                    }
                });
            }
        }).start();
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
