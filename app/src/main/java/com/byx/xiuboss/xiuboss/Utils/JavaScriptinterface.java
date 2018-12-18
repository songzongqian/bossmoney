package com.byx.xiuboss.xiuboss.Utils;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Mvp.activity.BalanceWebActivity;

/**
 * Created by wangwenjie001 on 2018/10/7.
 */

public class JavaScriptinterface {
    Context context;
    public JavaScriptinterface(Context c) {
        context= c;
    }
    /**
     * 与js交互时用到的方法，在js里直接调用的
     */
    @JavascriptInterface
    private void closeWeb() {
        BalanceWebActivity.balanceWebActivity.finish();
    }
}
