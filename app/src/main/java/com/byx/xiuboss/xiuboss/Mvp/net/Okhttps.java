package com.byx.xiuboss.xiuboss.Mvp.net;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ASUS on 2018/8/1.
 */

public class Okhttps {
    private static Okhttps utils;
    private OkHttpClient client;
    private Okhttps(){
        client = new OkHttpClient();
    }
    public static synchronized Okhttps getInstance(){
        //加锁
        if (utils==null)
            utils = new Okhttps();
        return utils;
        //解锁
    }
    public void sendGet(String url, Callback callback){
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public void sendPost(String url, Callback callback, String mobile, String password, String part){
        FormBody.Builder f = new FormBody.Builder();
        f.add("mobile",mobile);
        f.add("password",password);
        f.add("part",part);
        RequestBody build = f.build();

        Request request = new Request.Builder().url(url)
                .post(build).build();
        Call call = client.newCall(request);
        call.enqueue(callback);}

}
