package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alibaba.idst.nls.internal.connector.websockets.WebSocketClient;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.TrustManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView webView;
    /*思考思考试试看*/
    //private String url = "https://www.ourdaidai.com/app/index.php?i=2&c=entry&ctrl=manage&ac=goods&op=index&do=mobile&m=we7_wmall";
    private String url = AppUrl.ORDER_WEBURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        webView = findViewById(R.id.webView);
        String id = getIntent() ==null ? null : getIntent().getStringExtra("id");
        this.url  = url+ id;

        syncCookie();
        webViewSetting();

    }

    private void webViewSetting() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);//主要是这句加载html5
        webSettings.setJavaScriptEnabled(true);//启用js
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAllowContentAccess(true);
        webView.setWebChromeClient(new WebChromeClient());//这行最好不要丢掉
        webView.addJavascriptInterface(new AndroidJs(),"ajs");
        /**
         * 启用mixed content    android 5.0以上默认不支持Mixed Content
         * 5.0以上允许加载http和https混合的页面(5.0以下默认允许，5.0+默认禁止)
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //该方法解决的问题是打开浏览器不调用系统浏览器，直接用webview打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.equals("https://www.ourdaidai.com/app/index.php?i=2&c=entry&ctrl=manage&ac=order&op=takeout&ta=list&do=mobile&m=we7_wmall")){
                    OrderActivity.this.finish();
                }else{
                    view.loadUrl(url);
                }
                return true;
            }
        });
        webView.loadUrl(url);

    }

    private void syncCookie() {
        SharedPreferences login_sucess =this.getSharedPreferences("login_sucess", MODE_PRIVATE);
        String sid = login_sucess.getString("sid", "");
        String myCookie=login_sucess.getString("mycookie","");

        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, "0122___mg_sid="+sid);		//你想设置的参数
        cookieManager.setCookie(url, "0122_we7_wmall_manager_session_2="+myCookie);				//你想设置的参数

        String test  = cookieManager.getCookie(url);			//这里可以获取你的cookie看一下是什么样子的，其实就是拼接起来的string
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.flush();
            cookieManager.setAcceptThirdPartyCookies(webView,true);
        } else {
            CookieSyncManager.getInstance().sync();
        }
        String cookie = cookieManager.getCookie(url);
        Log.d("tag", "syncCookie: ===="+cookie);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("ansen","是否有上一个页面:"+webView.canGoBack());
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK){//点击返回按钮的时候判断有没有上一页
            webView.goBack(); // goBack()表示返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        webView.destroy();
        webView=null;
    }
    public class AndroidJs extends Object{
        @JavascriptInterface
        void onFinish(){
            OrderActivity.this.finish();
        }
    }
}
