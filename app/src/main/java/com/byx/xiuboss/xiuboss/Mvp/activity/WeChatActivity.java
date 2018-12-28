package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.byx.xiuboss.xiuboss.Bean.WeChatBean;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.ImageToGallery;
import com.byx.xiuboss.xiuboss.Utils.UrlToBitmap;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestParams;

import java.util.HashMap;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.wechat.Wechat;
import okhttp3.Call;
import okhttp3.Response;


public class WeChatActivity extends BaseActivity {


    private String extra;
    private ImageView imageView;
    private RelativeLayout rlMyUI;
    private Bitmap bmp;
    private ImageView ivTest;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat);
        imageView = findViewById(R.id.wechat_two);
        rlMyUI = findViewById(R.id.rl_myUI);
        ivTest = findViewById(R.id.imageView6);
        ivTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = loadBitmapFromView(rlMyUI);
                if (bitmap != null) {
                    ImageToGallery.saveImageToGallery(WeChatActivity.this, bitmap);
                }
            }
        });
        initData();
        //shareWeChat();
    }

    private void shareWeChat() {
        if (bitmap == null) {
            bitmap = loadBitmapFromView(rlMyUI);
        }
        //ImageToGallery.saveImageToGallery(WeChatActivity.this, bitmap);
        ShareParams shareParams = new ShareParams();
        shareParams.setShareType(Platform.SHARE_IMAGE);
        shareParams.setImageData(bitmap);
        JShareInterface.share(Wechat.Name, shareParams, new PlatActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                System.out.println("微信分享成功");

            }

            @Override
            public void onError(Platform platform, int i, int i1, Throwable throwable) {
                System.out.println("微信分享失败");

            }

            @Override
            public void onCancel(Platform platform, int i) {
                System.out.println("微信分享取消");
            }
        });
    }


    private void initData() {
        Intent intent = getIntent();
        extra = intent.getStringExtra("sid");
        RequestParams params = new RequestParams();
        params.put("sid", extra);
        OkHttpUtils.post(AppUrl.QRCODE_URL).params(params).execute(new MyJsonCallBack<WeChatBean>() {

            @Override
            public void onResponse(WeChatBean weChatBean) {
                if (weChatBean != null && weChatBean.getCode() == 2000) {
                    RequestOptions options = new RequestOptions();
                    options.centerCrop().placeholder(R.mipmap.defaults).error(R.mipmap.defaults);
                    //Glide.with(WeChatActivity.this).load(weChatBean.getData()).apply(options).into(imageView);
                    Glide.with(WeChatActivity.this).load(weChatBean.getData()).apply(options).into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                            imageView.setImageDrawable(drawable);
                            imageView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    shareWeChat();
                                }
                            }, 1000);
                        }
                    });
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });

    }


    public Bitmap loadBitmapFromView(View view) {
        // View view = LayoutInflater.from(WeChatActivity.this).inflate(R.layout.activity_we_chat, null);
        if (view == null) {
            return null;
        }
        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;  //以要素为单位
        int height = metrics.heightPixels;
        view.setDrawingCacheEnabled(true);
        //调用下面这个方法非常重要，如果没有调用这个方法，得到的bitmap为null
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        //这个方法也非常重要，设置布局的尺寸和位置
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        //获得绘图缓存中的Bitmap
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        return view.getDrawingCache();
    }


}
