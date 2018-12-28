package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnLineServiceActivity extends BaseActivity {
    String serviceUrl = "https://cschat-ccs.aliyun.com/index.htm?tntInstId=_0RUJWJc&scene=SCE00000138";
    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.wechat_showpup)
    ImageView wechatShowpup;
    @BindView(R.id.rl_save)
    RelativeLayout rlSave;
    @BindView(R.id.onLineWebView)
    WebView onLineWebView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        ButterKnife.bind(this);
        titleText.setText("在线客服");
        loadWebView();



    }


    private void loadWebView() {
        onLineWebView.getSettings().setJavaScriptEnabled(true);
        onLineWebView.getSettings().setDomStorageEnabled(true);// 打开本地缓存提供JS调用,至关重要
        onLineWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);// 实现8倍缓存
        onLineWebView.getSettings().setAllowFileAccess(true);
        onLineWebView.setWebChromeClient(new WebChromeClient());
        onLineWebView.addJavascriptInterface(new JavaAction(), "android");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            onLineWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        onLineWebView.loadUrl(serviceUrl);
        onLineWebView.setWebViewClient(new WebViewClient());
    }

    @OnClick({R.id.rl_back, R.id.title_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.title_text:
                break;
        }
    }

    public class JavaAction {
        @JavascriptInterface
        public void goBack() {
            finish();
        }

    }
}
