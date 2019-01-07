package com.byx.xiuboss.xiuboss.Mvp.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.CollectBean;
import com.byx.xiuboss.xiuboss.Mvp.view.ClearWriteEditText;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestParams;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class ReceivablesActivity extends BaseActivity {
    private ClearWriteEditText editText;
    private int REQUEST_CODE_SCAN = 111;
    private Button btnSureBottom;
    private String sidNumber;
    private String collectAmout;
    private SharedPreferences loginSucess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivables);
        loginSucess = this.getSharedPreferences("login_sucess", MODE_PRIVATE);
        sidNumber = loginSucess.getString("sid", "");
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        editText.setText("");
    }

    private void initView() {
        setStatusBar(true);
        RelativeLayout rlBack=findViewById(R.id.rl_back);
        TextView tvTitle=findViewById(R.id.title_text);
        editText = findViewById(R.id.et_money);
        btnSureBottom = findViewById(R.id.btn_sure);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        tvTitle.setText("扫码收款");
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        btnSureBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectAmout = editText.getText().toString().trim();
                if(collectAmout.isEmpty()){
                    Toast.makeText(ReceivablesActivity.this,"收款金额不能为空",Toast.LENGTH_LONG).show();
                }else{
                    //调起摄像头开始扫描数据扫一扫
                        AndPermission.with(ReceivablesActivity.this)
                                .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                                .onGranted(new Action() {
                                    @Override
                                    public void onAction(List<String> permissions) {
                                        Intent intent = new Intent(ReceivablesActivity.this, CaptureActivity.class);
                                        /*ZxingConfig是配置类
                                         *可以设置是否显示底部布局，闪光灯，相册，
                                         * 是否播放提示音  震动
                                         * 设置扫描框颜色等
                                         * 也可以不传这个参数
                                         * */
                                        ZxingConfig config = new ZxingConfig();
                                        config.setPlayBeep(true);//是否播放扫描声音 默认为true
                                        config.setShake(true);//是否震动  默认为true
                                        config.setShowbottomLayout(false);
                                        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                                        startActivityForResult(intent, REQUEST_CODE_SCAN);
                                    }
                                })
                                .onDenied(new Action() {
                                    @Override
                                    public void onAction(List<String> permissions) {
                                        Uri packageURI = Uri.parse("package:" + ReceivablesActivity.this.getPackageName());
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        Toast.makeText(ReceivablesActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                                    }}).start();
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                final String content = data.getStringExtra(Constant.CODED_CONTENT);
                System.out.println("扫描的内容"+content);
                RequestParams postParams = new RequestParams();
                postParams.put("sid",sidNumber);
                postParams.put("money",collectAmout);
                postParams.put("number",content);
                OkHttpUtils.post(AppUrl.COLLECT_URL).params(postParams).execute(new MyJsonCallBack<CollectBean>() {

                    @Override
                    public void onResponse(CollectBean collectBean) {
                        if(collectBean!=null){
                            if(collectBean.getCode()==2000){
                                //收款成功,跳转成功页面
                                Intent intent= new Intent(ReceivablesActivity.this,CollectSuccessActivity.class);
                                intent.putExtra("moneyCount",collectAmout);
                                startActivity(intent);
                            }
                        }

                    }

                    @Override
                    public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onError(call, response, e);
                    }
                });




            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
