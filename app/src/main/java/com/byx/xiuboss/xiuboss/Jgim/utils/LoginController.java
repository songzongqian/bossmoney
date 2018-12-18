package com.byx.xiuboss.xiuboss.Jgim.utils;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Application.JgApplication;
import com.byx.xiuboss.xiuboss.Bean.LoginBean;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.LoginActivity;
import com.byx.xiuboss.xiuboss.Mvp.net.Okhttps;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestParams;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/7/24.
 */

public class LoginController implements View.OnClickListener {
    private String url = "https://www.ourdaidai.com/CI/index.php/Login/login";
    private int state;
    private LoginActivity mContext;
    private String part = "1";
    private String mycoolie;
    private Dialog dialog;
    private List<LoginBean.DataBean.SidBean> msid;
    private SharedPreferences share;
    private SharedPreferences.Editor edit;
    private String mycookie;
    private Map<String, String> mMap = new HashMap<>();
    private Set<String> tags = new HashSet<>();

    public LoginController(LoginActivity loginActivity) {
        this.mContext = loginActivity;
    }

    private boolean isContainChinese(String str) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private boolean whatStartWith(String str) {
        Pattern pattern = Pattern.compile("^([A-Za-z]|[0-9])");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private boolean whatContain(String str) {
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z][a-zA-Z0-9_\\-@\\.]{3,127}$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //登陆验证
                final String userId   = mContext.getUserId();
                final String password = mContext.getPassword();
                if (TextUtils.isEmpty(userId)) {
                    ToastUtil.shortToast(mContext, "用户名不能为空");
                    mContext.mLogin_userName.setShakeAnimation();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.shortToast(mContext, "密码不能为空");
                    mContext.mLogin_passWord.setShakeAnimation();
                    return;
                }
                if (userId.length() < 4 || userId.length() > 128) {
                    mContext.mLogin_userName.setShakeAnimation();
                    ToastUtil.shortToast(mContext, "用户名为4-128位字符");
                    return;
                }
                if (password.length() < 4 || password.length() > 128) {
                    mContext.mLogin_userName.setShakeAnimation();
                    ToastUtil.shortToast(mContext, "密码为4-128位字符");
                    return;
                }
                if (isContainChinese(userId)) {
                    mContext.mLogin_userName.setShakeAnimation();
                    ToastUtil.shortToast(mContext, "用户名不支持中文");
                    return;
                }
                if (!whatStartWith(userId)) {
                    mContext.mLogin_userName.setShakeAnimation();
                    ToastUtil.shortToast(mContext, "用户名以字母或者数字开头");
                    return;
                }
                if (!whatContain(userId)) {
                    mContext.mLogin_userName.setShakeAnimation();
                    ToastUtil.shortToast(mContext, "只能含有: 数字 字母 下划线 . - @");
                    return;
                }

                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog = DialogCreator.createLoadingDialog(mContext, mContext.getString(R.string.login_hint));
                        dialog.show();
                    }
                });

                //登陆页面

                RequestParams params=new RequestParams();
                params.put("mobile",userId);
                params.put("password",password);
                OkHttpUtils.post(AppUrl.LOGIN_URL).params(params).execute(new MyJsonCallBack<LoginBean>() {
                    @Override
                    public void onResponse(LoginBean loginBean) {
                        if(loginBean!=null && loginBean.getCode()==2000 ){
                            List<LoginBean.DataBean.SidBean> sid = loginBean.getData().getSid();
                            String sid1 = sid.get(0).getSid();
                            tags.add(sid1);
                            String avatar = loginBean.getData().getAvatar();
                            state = loginBean.getCode();
                            String mycookie = loginBean.getData().getMycookie();
                            share = mContext.getSharedPreferences("login_sucess", mContext.MODE_PRIVATE);
                            edit = share.edit();
                            edit.putString("sid", sid1);
                            edit.putString("mycookie", mycookie);
                            edit.putString("id", loginBean.getData().getId());
                            edit.putString("mobile", loginBean.getData().getMobile());
                            edit.putString("headIcon", avatar);
                            edit.putString("payMobile", sid.get(0).getManagerMobile());
                            Log.e("boss", "managerMobile号码" + sid.get(0).getManagerMobile());
                            edit.putString("managerMobile", sid.get(0).getManagerMobile());
                            edit.putString("homeTitle", sid.get(0).getTitle());
                            edit.putString("userId", userId);
                            edit.putString("password",password);
                            edit.putBoolean("isLogin",true);
                            edit.commit();

                            //填充极光推送的相关信息
                            JMessageClient.login(userId, userId, new BasicCallback(){
                                @Override
                                public void gotResult(int responseCode, String responseMessage) {
                                    dialog.dismiss();
                                    if (responseCode == 0) {
                                        SharePreferenceManager.setCachedPsw(password);
                                        UserInfo myInfo = JMessageClient.getMyInfo();
                                        File avatarFile = myInfo.getAvatarFile();
                                        //登陆成功,如果用户有头像就把头像存起来,没有就设置null
                                        if (avatarFile != null) {
                                            SharePreferenceManager.setCachedAvatarPath(avatarFile.getAbsolutePath());
                                        } else {
                                            SharePreferenceManager.setCachedAvatarPath(null);
                                        }
                                        String username = myInfo.getUserName();
                                        String appKey = myInfo.getAppKey();
                                        mContext.goToActivity(mContext, MainActivity.class);
                                        JPushInterface.setTags(mContext, 0, tags);
                                        ToastUtil.shortToast(mContext, "登陆成功");
                                        mContext.finish();
                                    }
                                }

                            });

                        }else{
                            ToastUtil.shortToast(mContext, "账号密码错误");
                            dialog.dismiss();
                        }

                    }

                    @Override
                    public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onError(call, response, e);
                        ToastUtil.shortToast(mContext, "服务器错误,请稍后重试");
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.login_register:

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setIcon(R.drawable.logios);
                builder.setTitle("商户入驻");
                builder.setMessage("入驻本平台需联系本公司客服,确定给客服打电话吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0710-3780521"));
                        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        mContext.startActivity(intent2);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //   dialog.dismiss();
                    }
                });
                builder.show();
                break;
        }
    }
}
