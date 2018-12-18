package com.byx.xiuboss.xiuboss.Mvp.model;







import com.byx.xiuboss.xiuboss.Mvp.net.RetrofitApiService;
import com.byx.xiuboss.xiuboss.Mvp.net.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class AfbModel {

    private RetrofitApiService retrofitApiService;

    public AfbModel() {
        this.retrofitApiService = RetrofitUtils.getInstance().getServer();
    }

    //登录
    public Observable<ResponseBody> login(String url, Map<String, Object> params) {
        return retrofitApiService.login(url, params);
    }

}
