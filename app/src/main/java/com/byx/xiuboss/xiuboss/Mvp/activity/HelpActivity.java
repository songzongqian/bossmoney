package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends BaseActivity {


    @BindView(R.id.helpWebView)
    WebView helpWebView;
    private WebView helpView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        helpView = findViewById(R.id.helpWebView);
        loadWebView();
    }

    private void loadWebView() {
        helpView.getSettings().setJavaScriptEnabled(true);
        helpView.getSettings().setDomStorageEnabled(true);// 打开本地缓存提供JS调用,至关重要
        helpView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);// 实现8倍缓存
        helpView.getSettings().setAllowFileAccess(true);
        helpView.setWebChromeClient(new WebChromeClient());
        helpView.addJavascriptInterface(new JavaAction(), "android");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            helpView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        helpView.loadUrl(AppUrl.HELP_URL);
        helpView.setWebViewClient(new WebViewClient());
    }

    public class JavaAction{
        @JavascriptInterface
        public void goBack(){
            finish();
        }

    }

}
