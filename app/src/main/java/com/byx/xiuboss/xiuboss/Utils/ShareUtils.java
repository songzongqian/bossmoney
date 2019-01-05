package com.byx.xiuboss.xiuboss.Utils;

import android.util.Log;

import java.util.HashMap;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;

/**
 * Created by night_slight on 2019/1/4.
 */

public class ShareUtils {
    private OnShareListener mShareListener;
    public static void share(String name, ShareParams params,OnShareListener mShareListener){

        JShareInterface.share(name, params, new PlatActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                mShareListener.onShareSuccess();
            }

            @Override
            public void onError(Platform platform, int i, int i1, Throwable throwable) {
                Log.i("JSHARE",platform.getName()+"  i:"+i+" i1:"+i1+" throwable:"+throwable);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.i("JSHARE",platform.getName()+"  i:"+i+" i1:");
            }
        });

    }

    public interface OnShareListener{
        void onShareSuccess();
    }
}
