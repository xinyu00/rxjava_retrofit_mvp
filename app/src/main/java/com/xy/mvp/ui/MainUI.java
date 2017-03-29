package com.xy.mvp.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xy.mvp.R;
import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.dagger.component.DaggerMainUIComponent;
import com.xy.mvp.dagger.module.MainUIModule;
import com.xy.mvp.master.AppManager;
import com.xy.mvp.presenter.MainUIPresenter;

import org.reactivestreams.Subscriber;

import javax.inject.Inject;

import butterknife.InjectView;
import io.reactivex.Flowable;

public class MainUI extends BaseActivity {

    @InjectView(R.id.username)
    EditText mUsername;
    @InjectView(R.id.password)
    EditText mPassword;
    private ProgressDialog dialog;

    @Inject
    MainUIPresenter presenter;

    @Override
    public void initData() {
        dialog = new ProgressDialog(this);
        setTopColor(Color.BLUE);
        //使用dagger2
        DaggerMainUIComponent component = (DaggerMainUIComponent) DaggerMainUIComponent.builder().mainUIModule(new MainUIModule(this)).build();
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
//            presenter.login(username, password);
        } else {
            Toast.makeText(MainUI.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 此方法内部实现为异步，用于登陆数据操作
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回值为被观察者
     */
    private Flowable<String> login2(final String username, final String password) {

        Flowable<String> flowable = new Flowable<String>() {        // 创建被观察者对象，进行异步操作
            String loginmessage;        // 用于Flowable保存携带的String参数

            // 实际订阅体
            @Override
            protected void subscribeActual(Subscriber<? super String> s) {
                if ("admin".equals(username) && "123".equals(password)) {
                    loginmessage = "登陆成功";
                } else {
                    loginmessage = "登陆失败";
                }
                try {
                    Thread.sleep(3000);     // 休息3秒
                    s.onNext(loginmessage); // 发送消息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return flowable;    //将被观察者作为返回值
    }

    private boolean checkUserInfo(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void success() {
        dialog.dismiss();
        Toast.makeText(MainUI.this, "欢迎回来：" + mUsername.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void failed() {
        dialog.dismiss();
        Toast.makeText(MainUI.this, "用户名或密码输入有误", Toast.LENGTH_SHORT).show();
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
