package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.FileCacheUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.wechat_showpup)
    ImageView wechatShowpup;
    @BindView(R.id.rl_save)
    RelativeLayout rlSave;
    @BindView(R.id.rl_clearDisk)
    RelativeLayout rlClearDisk;
    @BindView(R.id.clearBtn)
    TextView clearBtn;
    @BindView(R.id.rl_update)
    RelativeLayout rlUpdate;
    @BindView(R.id.button_exit)
    Button buttonExit;
    @BindView(R.id.tv_cacheSize)
    TextView tvCacheSize;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        titleText.setText("设置");
        initData();
        File file = new File("/data/data/com.byx.xiuboss.xiuboss/files");
        try {
            String cacheSize = FileCacheUtil.getCacheSize(file);
            String substring = cacheSize.substring(0, cacheSize.indexOf(3));
            tvCacheSize.setText(substring + "k");

            Log.e("-----file-----", substring);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化版本号
    private void initData() {
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String version = packInfo.versionName;
            tvVersion.setText("V"+version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }


    @OnClick({R.id.title_back_image, R.id.rl_clearDisk, R.id.rl_update, R.id.button_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_image:
                finish();
                break;
            case R.id.rl_clearDisk:
                FileCacheUtil.cleanInternalCache(getApplication());
                ToastUtil.shortToast(this, "清除成功");
                break;
            case R.id.rl_update:

                break;
            case R.id.button_exit:
                JMessageClient.logout();
                SharedPreferences loginSuccess = this.getSharedPreferences("login_sucess", MODE_PRIVATE);
                loginSuccess.edit().putBoolean("isLogin",false).commit();
                Intent logoutIntent = new Intent(this, NewLoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                break;
        }
    }
}
