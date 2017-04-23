package com.xy.mvp;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class TestUI extends Activity {
    private static final String TAG = "TestUI";
    private int param = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.e(TAG, "onCreate called.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            Log.e(TAG, "onStart called.");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart called.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause called.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop called.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestory called.");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("param", param);
        Log.e(TAG, "onSaveInstanceState called. put param: " + param);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        param = savedInstanceState.getInt("param");
        Log.e(TAG, "onRestoreInstanceState called. get param: " + param);
        super.onRestoreInstanceState(savedInstanceState);
    }

    //当指定了android:configChanges="orientation"后,方向改变时onConfigurationChanged被调用
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "onConfigurationChanged called.");
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                setContentView(R.layout.activity_test);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                setContentView(R.layout.activity_test);
                break;
        }
    }
}
