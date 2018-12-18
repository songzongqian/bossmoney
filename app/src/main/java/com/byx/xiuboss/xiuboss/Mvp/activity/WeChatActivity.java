package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.WeChatBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.ImageToGallery;
import com.byx.xiuboss.xiuboss.Utils.ImgUtils;
import com.byx.xiuboss.xiuboss.Utils.PermissionHelper;
import com.byx.xiuboss.xiuboss.Utils.PermissionInterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class WeChatActivity extends BaseActivity implements PermissionInterface {

    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.wechat_two)
    ImageView wechat;
    @BindView(R.id.wechat_showpup)
    ImageView wechatShowpup;
    @BindView(R.id.Preservation_two)
    RelativeLayout PreservationTwo;
    @BindView(R.id.rl_save)
    RelativeLayout rlSave;
    @BindView(R.id.imageView6)
    ImageView imageView6;


    private String url = "https://www.ourdaidai.com/CI/index.php/StoreMy/qrcode";
    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    // 请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;
    private PermissionHelper mPermissionHelper;
    private WeChatBean weChatBean;
    private String extra;
    private PopupWindow window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat);
        ButterKnife.bind(this);
        mPermissionHelper = new PermissionHelper(WeChatActivity.this, this);
        initData();
    }


    private void initData() {
        wechatShowpup.setVisibility(View.VISIBLE);
        titleText.setText("我的收款码");
        Intent intent = getIntent();
        extra = intent.getStringExtra("sid");
        Map<String, String> map = new HashMap<>();
        map.put("sid", extra);
        OkHttpUtils.getInstance().postDataAsynToNet(url, map, new OkHttpUtils.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                getData(response.body().string());
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });

    }


    @OnClick({R.id.title_back_image, R.id.wechat_showpup, R.id.rl_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_image:
                finish();
                break;
            case R.id.wechat_showpup:
                showPopupWindow();
                break;
                //扩大点击事件的范围
            case R.id.rl_save:
                showPopupWindow();
                break;

        }
    }

    public void getData(String data) {
        Gson gson = new Gson();
        weChatBean = gson.fromJson(data, WeChatBean.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(WeChatActivity.this).load(weChatBean.getData()).into(wechat);
            }
        });
    }

    /**
     * View截图,截取某个布局的图片
     *
     * @param
     * @return
     */
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
        return view.getDrawingCache();
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

    private void showPopupWindow() {
        View inflate = LayoutInflater.from(WeChatActivity.this).inflate(R.layout.wechat_popupwindow, null, false);
        window = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        TextView Preservation = inflate.findViewById(R.id.Preservation);
        TextView dismis = inflate.findViewById(R.id.dismis);

        Preservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPermissionHelper.requestPermissions();
                        WeChatActivity.this.getWindow().getDecorView().setDrawingCacheEnabled(true);
                        Bitmap bmp=WeChatActivity.this.getWindow().getDecorView().getDrawingCache();
                        ImageToGallery.saveImageToGallery(WeChatActivity.this,bmp);
                        window.dismiss();
                    }
                });

            }
        });

        dismis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        backgroundAlpha(0.5f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(LayoutInflater.from(WeChatActivity.this).inflate(R.layout.activity_we_chat, null), Gravity.BOTTOM, 0, 0);
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


}
