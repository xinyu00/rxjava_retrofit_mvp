package com.xy.mvp.ui.user;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.widget.EditText;

import com.xy.mvp.R;
import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.dagger.component.DaggerActivityComponent;
import com.xy.mvp.presenter.user.RegisterUIPresenter;
import com.xy.mvp.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册页
 */
public class RegisterUI extends BaseActivity{
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;

    private ProgressDialog dialog;

    @Inject
    RegisterUIPresenter presenter;

    @Override
    public void initView() {
        dialog = new ProgressDialog(this);
    }

    @Override
    public void initData() {
        initInject();
    }

    private void initInject() {
        DaggerActivityComponent.builder()
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    public void success(String msg) {
        dialog.dismiss();
        ToastUtils.showShort(this,"尊敬的"+et_phone.getText().toString() + "用户,"+msg);
    }

    private boolean checkUserInfo(String username, String password) {
        return !TextUtils.isEmpty(username) || !TextUtils.isEmpty(password);
    }

    public void failed(String msg) {
        dialog.dismiss();
        ToastUtils.errorShow(this,msg);
    }

    @OnClick(R.id.bt_register)
    public void onClick(){
        String phone = et_phone.getText().toString();
        String password = et_password.getText().toString();
        boolean checkUserInfo = checkUserInfo(phone, password);
        if (checkUserInfo) {
            dialog.show();
            presenter.register(et_phone.getText().toString(),et_password.getText().toString());
        } else {
            ToastUtils.showShort(this,"手机号或密码不能为空");
        }

    }
}
