package com.speed.mvp.dagger.component;

import android.app.Activity;

import com.speed.mvp.base.BaseActivity;
import com.speed.mvp.dagger.PerActivity;
import com.speed.mvp.dagger.module.ActivityModule;
import com.speed.mvp.ui.user.LoginUI;
import com.speed.mvp.ui.user.RegisterUI;

import dagger.Component;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

@PerActivity
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
    void inject(RegisterUI activity);
    void inject(LoginUI activity);
    void inject(BaseActivity activity);
}
