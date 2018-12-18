package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.ImageToGallery;
import com.byx.xiuboss.xiuboss.Utils.ImgUtils;
import com.byx.xiuboss.xiuboss.Utils.JavaScriptinterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;

public class BalanceWebActivity extends AppCompatActivity {
   public static BalanceWebActivity balanceWebActivity;
    @BindView(R.id.balance_web)
    WebView balanceWeb;
    private String webUrl = "https://dev.ourdaidai.com/lizhenhu/android_app/withdraw.html";
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_web);
        ButterKnife.bind(this);
        initData();
    }



    @SuppressLint("JavascriptInterface")
    private void initData() {
        SharedPreferences login_sucess = getSharedPreferences("login_sucess", MODE_PRIVATE);
        final String sid = login_sucess.getString("sid", "");
        final String id =  login_sucess.getString("id", "");
        final String mobile = login_sucess.getString("payMobile", "");
        final Map<String, String> webMap = new HashMap<>();
        webMap.put("clerk_id", id);
        webMap.put("sid", sid);
        webMap.put("mobile", mobile);

        try {
            jsonObject = new JSONObject(webMap.toString());
        Log.e("----json-----", String.valueOf(jsonObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        balanceWeb.getSettings().setJavaScriptEnabled(true);
        balanceWeb.getSettings().setDomStorageEnabled(true);// 打开本地缓存提供JS调用,至关重要
        balanceWeb.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);// 实现8倍缓存
        balanceWeb.getSettings().setAllowFileAccess(true);
        balanceWeb.getSettings().setAppCacheEnabled(true);
        String appCachePath = getApplication().getCacheDir().getAbsolutePath();
        balanceWeb.getSettings().setAppCachePath(appCachePath);
        balanceWeb.getSettings().setDatabaseEnabled(true);
        balanceWeb.setWebChromeClient(new WebChromeClient());
        balanceWeb.addJavascriptInterface(new JavaAction(), "android");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            balanceWeb.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        Log.e("---webMap--",webMap.toString());
        balanceWeb.loadUrl(AppUrl.H5_URL);
       /* {"sid"+":"+"111","mobile"+":"+"18034103322","tclerk_id"+":"+"22"}*/

       balanceWeb.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                String userAgent = "shixinzhang";
                String js = "window.localStorage.setItem('withdrawUserInfo','" +String.valueOf(jsonObject) + "');";
                String jsUrl = "javascript:(function({ var localStorage = window.localStorage;                    localStorage.setItem('withdrawUserInfo','" + String.valueOf(jsonObject) + "')                })()";
                System.out.println("加载完毕jsUrl"+jsUrl);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    view.evaluateJavascript(js, null);
                } else {
                    view.loadUrl(jsUrl);
                    view.reload();
                }
            }
        });






        /**
         * 监听用户长按屏幕事件
         */
        balanceWeb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view){
                BalanceWebActivity.this.getWindow().getDecorView().setDrawingCacheEnabled(true);
                Bitmap bmp=BalanceWebActivity.this.getWindow().getDecorView().getDrawingCache();
                ImageToGallery.saveImageToGallery(BalanceWebActivity.this,bmp);
                return true;
            }
        });
    }


    public class JavaAction{
        @JavascriptInterface
        public void goBack(){
            finish();
        }

    }
}
