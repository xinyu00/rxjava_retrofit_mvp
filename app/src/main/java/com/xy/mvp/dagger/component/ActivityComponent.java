package com.xy.mvp.dagger.component;

import android.app.Activity;

import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.dagger.PerActivity;
import com.xy.mvp.dagger.module.ActivityModule;
import com.xy.mvp.ui.user.RegisterUI;

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
    void inject(BaseActivity activity);
}
