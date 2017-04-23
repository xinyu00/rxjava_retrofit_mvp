package com.xy.mvp.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xy.mvp.R;
import com.xy.mvp.base.AppManager;

/**
 * anthor:Created by tianchen on 2017/4/21.
 * email:963181974@qq.com
 */

public class MessageDialog extends Dialog implements View.OnClickListener {
    private static MessageDialog msgDialog;
    private TextView tvInfo;
    private BackListener backListener;
    public static MessageDialog getInstance() {
        if (msgDialog == null) {
            synchronized (MessageDialog.class) {
                if (msgDialog == null) {
                    msgDialog = new MessageDialog();
                }
            }
        }
        return msgDialog;
    }

    private MessageDialog() {
        this(AppManager.getAppManager().currentActivity(), R.style.hintdialog);
    }

    private MessageDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public void showDialog(String msg) {
        this.showDialog(msg, null);
    }

    public void showDialog(String msg, BackListener backListener) {
        this.backListener = backListener;
        if (getContent() != null) {
            setDialog();
            tvInfo.setText(msg);
        }
    }

    private void setDialog() {
        if (msgDialog == null) {
            msgDialog = new MessageDialog();
        }
        msgDialog.show();
        msgDialog.setCancelable(false);
        msgDialog.setContentView(getDialogView());
    }

    private View getDialogView() {
        WindowManager wm = (WindowManager) getContent().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int minWidth = (int) (width * 0.7);
        int minHeight = (int) (height * 0.3);
        View view = LayoutInflater.from(getContent()).inflate(R.layout.dialog_hint, null);
        view.setMinimumWidth(minWidth);
        view.setMinimumHeight(minHeight);
//        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvInfo = (TextView) view.findViewById(R.id.iv_content);
        TextView tvBack = (TextView) view.findViewById(R.id.tv_back);
        tvBack.setOnClickListener(this);
        return view;
    }

    public void finishDialog() {
        if (msgDialog != null) {
            msgDialog.dismiss();
            msgDialog = null;
        }
    }

    private Activity getContent(){
        return AppManager.getAppManager().currentActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                if (msgDialog != null) {
                    msgDialog.dismiss();
                    msgDialog = null;
                }
                if (backListener != null) {
                    backListener.doBack();
                    backListener = null;
                }
                break;
        }
    }

    public interface BackListener {
        void doBack();
    }
}
