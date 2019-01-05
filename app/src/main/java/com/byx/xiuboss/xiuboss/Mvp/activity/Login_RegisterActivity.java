package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.byx.xiuboss.xiuboss.Mvp.adapter.SplashAdapter;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.ScollLinearLayoutManager;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;


/**
 * 登录，入驻申请页面
 */

public class Login_RegisterActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView homeRecycler;
    private Button btn_Login;
    private Button btn_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__register);
        //检测账号是否登陆
        initView();
        checkPermission();
    }


    /**
     * 检测用户权限
     */
    private void checkPermission() {
        Acp.getInstance(this)
                .request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.RECORD_AUDIO
                                , Manifest.permission.CALL_PHONE
                                ,Manifest.permission.CAMERA
                                ,Manifest.permission.ACCESS_COARSE_LOCATION)
                        .setDeniedMessage("这些都是必要的权限")
                        .build(), new AcpListener(){
                    @Override
                    public void onGranted(){

                    }

                    @Override
                    public void onDenied(List<String> permissions) {

                    }
                });

    }

    private void initView() {
        homeRecycler = findViewById(R.id.home_recycler);
        btn_Login  = findViewById(R.id.btn_Login);
        btn_Register = findViewById(R.id.btn_Register);
        btn_Login.setOnClickListener(this);
        btn_Register.setOnClickListener(this);
        btn_Register.getBackground().setAlpha(178);//0~255透明度值
        homeRecycler.setAdapter(new SplashAdapter(Login_RegisterActivity.this));
        homeRecycler.setLayoutManager(new ScollLinearLayoutManager(Login_RegisterActivity.this));
        homeRecycler.smoothScrollToPosition(Integer.MAX_VALUE / 2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Login:
              /*  new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Login_RegisterActivity.this, NewLoginActivity.class);
                        startActivity(intent);
                    }
                }, 1000);*/
                Intent intent = new Intent(Login_RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Register:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.logios);
                builder.setTitle("商户入驻");
                builder.setMessage("入驻本平台需联系本公司客服,确定给客服打电话吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0710-3780521"));
                        if (ActivityCompat.checkSelfPermission(Login_RegisterActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intent2);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
                break;
        }
    }
}
