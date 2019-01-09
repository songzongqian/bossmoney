package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.RewritePopwindow;
import com.byx.xiuboss.xiuboss.Utils.ShareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.wechat.Wechat;
import cn.jiguang.share.wechat.WechatMoments;

public class SharedActivity extends BaseActivity {

    @BindView(R.id.mWebView)
    WebView mWebView;

    private View mSharePopupView;
    private RewritePopwindow mSharePopupWindow;
    private RelativeLayout mPopupShareWeChat;
    private RelativeLayout mPopupShareFriend;
    private TextView mPopupShareCancel;
    private String share_type;

    private Handler handler = new Handler(Looper.getMainLooper());
    private String openKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        ButterKnife.bind(this);
        share_type = getIntent().getStringExtra("share_type");
        openKey = getIntent().getStringExtra("openKey");
        initView();
        initDate();
    }

    private void initView() {
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.addJavascriptInterface(new JsUseAppMethod(), "jsUseAppMethod");

        mWebView.loadUrl("https://dev.ourdaidai.com/lizhenhu/share_for_invite/index.html?share_type="+share_type);

    }

    private void initDate() {

    }


    public class JsUseAppMethod extends Object {

        @JavascriptInterface
        public void shareWithTitleDescriptionImageURL(String title, String description, String image, String url) {
            Log.i(TAG, "shareWithTitleDescriptionImageURL: "+title+" "+description+" "+image+" "+url);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    initSharePopupWindow(title,description,image,url);
                }
            });


        }
    }

    private void initSharePopupWindow(String title, String description, String image, String url) {
        if (mSharePopupView == null) {
            mSharePopupView = LayoutInflater.from(this).inflate(R.layout.popup_share, null, false);
            mSharePopupWindow = new RewritePopwindow(this, mSharePopupView);
            mPopupShareWeChat = mSharePopupView.findViewById(R.id.rl_weChat);
            mPopupShareFriend = mSharePopupView.findViewById(R.id.rl_weFriend);
            mPopupShareCancel = mSharePopupView.findViewById(R.id.cancel);
        }
        mPopupShareWeChat.setOnClickListener(v -> {
            /*Intent intent = new Intent(getActivity(), WeChatActivity.class);
            intent.putExtra("sid", "");
            startActivity(intent);*/
            toShare(Wechat.Name,title,description,image,url);
        });

        mPopupShareFriend.setOnClickListener(v -> {
            //Toast.makeText(getActivity(), "朋友圈", Toast.LENGTH_LONG).show();
            toShare(WechatMoments.Name,title,description,image,url);
        });

        mPopupShareCancel.setOnClickListener(v -> {
            if (mSharePopupWindow != null) {
                mSharePopupWindow.dismiss();
            }
        });
        if (mSharePopupView != null) {
            View rootview = getWindow().getDecorView();
            mSharePopupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        }

    }

    private void toShare(String shareName,String title, String description, String image, String url) {
        ShareParams params = new ShareParams();
        params.setShareType(Platform.SHARE_WEBPAGE);
        params.setTitle(title);
        openKey = openKey+"@@"+ (System.currentTimeMillis()/1000);
        String pathUrl = url+"&openKey="+openKey;
        params.setUrl(pathUrl);
        params.setVenueDescription(description);
        if (!TextUtils.isEmpty(image)){
            params.setImagePath(image);
        }

        ShareUtils.share(shareName, params, new ShareUtils.OnShareListener() {
            @Override
            public void onShareSuccess() {
                ToastUtil.shortToast(SharedActivity.this, "分享成功");
                if (mSharePopupWindow != null) {
                    mSharePopupWindow.dismiss();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (mSharePopupWindow!=null){
            mSharePopupWindow.dismiss();
        }
        super.onDestroy();

    }
}
