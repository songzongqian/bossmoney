package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.byx.xiuboss.xiuboss.Bean.CollectBean;
import com.byx.xiuboss.xiuboss.Bean.WeChatBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.Contast;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.ImageToGallery;
import com.byx.xiuboss.xiuboss.Utils.ImgUtils;
import com.byx.xiuboss.xiuboss.Utils.PermissionHelper;
import com.byx.xiuboss.xiuboss.Utils.PermissionInterface;
import com.byx.xiuboss.xiuboss.Utils.UrlToBitmap;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestParams;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.wechat.Wechat;
import okhttp3.Call;
import okhttp3.Response;

public class PayCodeActivity extends BaseActivity implements PermissionInterface {

    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    // 请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;
    private PermissionHelper mPermissionHelper;
    private WeChatBean weChatBean;

    private String sidNumber;
    WeChatBean middleBean;
    private int REQUEST_CODE_SCAN = 111;
    private RelativeLayout rlBack;
    private TextView topTitle;
    private ImageView imgQrcode;
    private Button btnSave;
    private RelativeLayout rlBind;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chater);
        sidNumber = getIntent().getStringExtra("sid");
        initView();
        mPermissionHelper = new PermissionHelper(PayCodeActivity.this, this);
        initData();
    }


    private void initView() {
        RelativeLayout rlBack = findViewById(R.id.rl_back);
        topTitle = findViewById(R.id.title_text);
        imgQrcode = findViewById(R.id.iv_weChat);
        btnSave = findViewById(R.id.btn_save);
        rlBind = findViewById(R.id.rl_bind);
        rlBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        topTitle.setText("我的收款二维码");
        RequestParams payParams=new RequestParams();
        payParams.put("sid", sidNumber);
        OkHttpUtils.post(AppUrl.QRCODE_URL).params(payParams).execute(new MyJsonCallBack<WeChatBean>() {
            @Override
            public void onResponse(WeChatBean weChatBean) {

                if(weChatBean!= null && weChatBean.getCode()==2000){
                    middleBean= weChatBean;
                    //加载二维码图片
                    RequestOptions options = new RequestOptions();
                    options.centerCrop()
                            .placeholder(R.mipmap.defaults)
                            .error(R.mipmap.defaults);
                    Glide.with(PayCodeActivity.this).load(weChatBean.getData()).apply(options).into(imgQrcode);
                    //保存相册
                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view){
                            mPermissionHelper.requestPermissions();
                            /**
                             * 把图像保存到相册
                             */
                            Bitmap bitmap = UrlToBitmap.returnBitMap(middleBean.getData());
                            if(bitmap!=null){
                            ImageToGallery.saveImageToGallery(PayCodeActivity.this, bitmap);
                            }
                        }
                    });



                    //扫码绑定店铺
                    rlBind.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view){
                            //调起摄像头开始扫描数据扫一扫
                            AndPermission.with(PayCodeActivity.this)
                                    .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                                    .onGranted(new Action() {
                                        @Override
                                        public void onAction(List<String> permissions) {
                                            Intent intent = new Intent(PayCodeActivity.this, CaptureActivity.class);
                                            /*ZxingConfig是配置类
                                             *可以设置是否显示底部布局，闪光灯，相册，
                                             * 是否播放提示音  震动
                                             * 设置扫描框颜色等
                                             * 也可以不传这个参数
                                             * */
                                            ZxingConfig config = new ZxingConfig();
                                            config.setPlayBeep(true);//是否播放扫描声音 默认为true
                                            config.setShake(true);//是否震动  默认为true
                                            config.setShowbottomLayout(true);
                                            intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                                            startActivityForResult(intent, REQUEST_CODE_SCAN);
                                        }
                                    })
                                    .onDenied(new Action() {
                                        @Override
                                        public void onAction(List<String> permissions) {
                                            Uri packageURI = Uri.parse("package:" + PayCodeActivity.this.getPackageName());
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            Toast.makeText(PayCodeActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                                        }}).start();



                        }
                    });

                }else{
                    //二维码显示占位图片
                    Glide.with(PayCodeActivity.this).load(R.mipmap.default_image).into(imgQrcode);
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
                Glide.with(PayCodeActivity.this).load(R.mipmap.default_image).into(imgQrcode);
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
                System.out.println("扫描二维码的内容"+content);
                String newContent = content.substring(0,content.length()-10);
                System.out.println("截取后获取的内容"+newContent);
                if(newContent.equals(Contast.QRCODE_URL)){
                    new AlertDialog.Builder(PayCodeActivity.this)
                            .setTitle("提示")
                            .setMessage("是否绑定该二维码为当前店铺的收款码？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, int which) {
                                    RequestParams requestParams = new RequestParams();
                                    requestParams.put("sid",sidNumber);
                                    requestParams.put("type","app");
                                    OkHttpUtils.get(content).params(requestParams).execute(new MyJsonCallBack<String>() {

                                        @Override
                                        public void onResponse(String json) {
                                            System.out.println("服务器返回的绑定json值"+json);
                                            if(json.startsWith("{")){
                                                ToastUtil.shortToast(PayCodeActivity.this, "绑定成功");
                                                dialog.dismiss();
                                            }else{
                                                ToastUtil.shortToast(PayCodeActivity.this, "绑定失败");
                                                dialog.dismiss();
                                            }
                                        }

                                        @Override
                                        public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                                            super.onError(call, response, e);
                                        }
                                    });


                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                            .create()
                            .show();


                }else{
                    Toast.makeText(PayCodeActivity.this,"暂不支持此二维码",Toast.LENGTH_LONG).show();
                }

            }
        }

    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }

    @Override
    public int getPermissionsRequestCode() {
        return 10000;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    public void requestPermissionsSuccess() {

    }

    @Override
    public void requestPermissionsFail() {
        finish();
    }


}
