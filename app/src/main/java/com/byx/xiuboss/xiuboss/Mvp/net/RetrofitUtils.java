package com.byx.xiuboss.xiuboss.Mvp.net;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;


public class RetrofitUtils {

    private Retrofit retrofit;
    private static RetrofitUtils retrofitUtils;

    public RetrofitUtils() {
        initRetrofit();
    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApiService.Login_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitApiService getServer() {
        return retrofit.create(RetrofitApiService.class);
    }
}
