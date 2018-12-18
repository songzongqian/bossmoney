package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.R;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

public class WelComeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        initData();
    }
    private void initData() {
        //检测账号是否登陆
        SharedPreferences loginSuccess = this.getSharedPreferences("login_sucess", MODE_PRIVATE);
        boolean isLogin = loginSuccess.getBoolean("isLogin", false);
        if(isLogin==true){
            goToMainActivity();
        }else{
            goToRegisterAndLoginActivity();
        }
    }

    private void goToMainActivity() {
        startActivity(new Intent(WelComeActivity.this, MainActivity.class));
        finish();
    }

    private void goToRegisterAndLoginActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelComeActivity.this, Login_RegisterActivity.class));
                finish();
            }
        }, 1500);

    }
}
