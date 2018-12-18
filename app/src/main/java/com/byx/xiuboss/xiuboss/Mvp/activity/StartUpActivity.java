package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.R;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

public class StartUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        initData();
    }

    private void initData() {
        //判断极光中是否包含用户信息
        UserInfo myInfo = JMessageClient.getMyInfo();
        if (myInfo== null){
            goToRegisterAndLoginActivity();
        }else{
            goToMainActivity();
        }
    }

    private void goToMainActivity() {
        startActivity(new Intent(StartUpActivity.this, MainActivity.class));
        finish();
    }

    private void goToRegisterAndLoginActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartUpActivity.this, Login_RegisterActivity.class));//跳登录页
                finish();
            }
        }, 1500);

    }
}
