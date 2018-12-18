package com.byx.xiuboss.xiuboss.NetUrl;

import android.support.annotation.Nullable;
import android.util.Log;


import com.lzy.okhttputils.callback.JsonCallBack;
import com.lzy.okhttputils.request.BaseRequest;

import okhttp3.Call;
import okhttp3.Response;


public abstract class MyJsonCallBack<T> extends JsonCallBack<T> {

    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        Log.d("InfoPage", "--------parseNetworkResponse----------" + response.toString());
        System.out.println("parseNetworkResponse");
        return super.parseNetworkResponse(response);
    }

    @Override
    public void onAfter(@Nullable T t, Call call, Response response, @Nullable Exception e) {
        System.out.println("onAfter");
    }

    @Override
    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
        System.out.println("upProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);
    }

    @Override
    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
        System.out.println("downloadProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);
    }

    @Override
    public void onError(Call call, @Nullable Response response, @Nullable Exception e){
        System.out.println("onError");
        super.onError(call, response, e);
        if (e != null) {
            Log.d("InfoPage", "--------e.toString()----------" + e.toString());
        }
        if (response != null) {
            Log.d("InfoPage", "--------response----------" + response.toString());
        }
        if (call != null) {
            Log.d("InfoPage", "--------call----------" + call.isCanceled());
        }

    }

    @Override
    public void onBefore(BaseRequest request) {
        System.out.println("onBefore");
        // 追加参数
//        request.params("aaa", "111")//
//                .params("bbb", "222")//
//                .params("ccc", "333")//
//                .headers("xxx", "444")//
//                .headers("yyy", "555")//
//                .headers("zzz", "666");
        super.onBefore(request);
    }
}
