package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Application.JgApplication;
import com.byx.xiuboss.xiuboss.R;


public class MyerweimaActivity extends AppCompatActivity {


    private ImageView imgBack;
    private TextView textView2;
    private ImageView dEWM;
    private ImageView skm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myerweima);
        initView();
        Glide.with(JgApplication.context).load("http://qr.liantu.com/api.php?logo=https://www.ourdaidai.com/img/WechatIMG21.png&text=https://www.ourdaidai.com/app/index.php?i=2%26c=entry%26ctrl=wmall%26ac=store%26op=goods%26do=mobile%26m=we7_wmall%26sid=98").into(dEWM);
        Glide.with(JgApplication.context).load("http://qr.liantu.com/api.php?logo=https://www.ourdaidai.com/img/WechatIMG21.png&text=https://www.ourdaidai.com/app/index.php?i=2%26c=entry%26ctrl=wmall%26ac=store%26op=paybill%26do=mobile%26m=we7_wmall%26sid=98%26outsider=1").into(skm);
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        textView2 = (TextView) findViewById(R.id.textView2);
        dEWM = (ImageView) findViewById(R.id.dEWM);
        skm = (ImageView) findViewById(R.id.skm);
    }
}
