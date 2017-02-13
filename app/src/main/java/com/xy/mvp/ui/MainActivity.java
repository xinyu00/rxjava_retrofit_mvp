package com.xy.mvp.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xy.mvp.R;
import com.xy.mvp.dagger.component.DaggerMainActivityComponent;
import com.xy.mvp.dagger.module.MainActivityModule;
import com.xy.mvp.presenter.MainActivityPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.username)
    EditText mUsername;
    @InjectView(R.id.password)
    EditText mPassword;
    private ProgressDialog dialog;

    @Inject
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        dialog = new ProgressDialog(this);

        presenter = new MainActivityPresenter(this);

        DaggerMainActivityComponent component = (DaggerMainActivityComponent) DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).build();
        component.in(this);
    }

    //按钮点击
    public void login(View view) {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        boolean checkUserInfo = checkUserInfo(username, password);
        if (checkUserInfo){
            dialog.show();
            presenter.login(username,password);
        }else {
            Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkUserInfo(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "欢迎回来：" + mUsername.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void failed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "用户名或密码输入有误", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
