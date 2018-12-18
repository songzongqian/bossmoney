package com.byx.xiuboss.xiuboss.Mvp.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitApiService {

    //登陆
    String Login_URL = "";
    @POST()
    Observable<ResponseBody> login(@Url String url, @QueryMap Map<String, Object> params);
}
