package com.xy.mvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xy.mvp.presenter.api.Api;
import com.xy.mvp.presenter.api.HostType;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * anthor:Created by tianchen on 2017/3/27.
 * email:963181974@qq.com
 */

public class NetWorkUtils {
    /**
     * 检查网络是否可用
     */
    public static boolean isNetConnected(Context context) {
        boolean i = false;
        NetworkInfo localNetworkInfo = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
        return false;
    }

    /**
     * 检测wifi是否连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测GPRS是否连接
     */
    public static boolean isGPRSConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断网址是否有效
     */
    public static boolean isLinkAvailable(String link) {
        Pattern pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        return matcher.matches();
    }

    public static void getIPAddress(final AddressIp addressIp) {
        Api.getDefault(HostType.TYPE1,Constant.TAOBAOURL)
                .getIp("myip")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String s) {
                        JSONObject jsonObject = JSON.parseObject(s);
                        JSONObject data = jsonObject.getJSONObject("data");
                        String ip = data.getString("ip");
                        addressIp.getIp(ip);
                    }
                });
    }
    public interface AddressIp{
        void getIp(String ip);
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }
}
