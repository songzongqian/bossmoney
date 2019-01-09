package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Application.JgApplication;
import com.byx.xiuboss.xiuboss.Bean.LoginBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.Base64Utils;
import com.byx.xiuboss.xiuboss.Utils.GetHeaderPwd;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestHeaders;
import com.lzy.okhttputils.model.RequestParams;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cloudist.cc.library.view.PasswordInputView;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Response;

public class PwdLoginActivity extends BaseActivity {
    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.wechat_showpup)
    ImageView wechatShowpup;
    @BindView(R.id.rl_save)
    RelativeLayout rlSave;
    @BindView(R.id.login_Title)
    TextView loginTitle;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.password_inputView)
    PasswordInputView passwordInputView;
    @BindView(R.id.tvSecond)
    TextView tvSecond;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private String version;
    private String mobile;
    private Set<String> pushTag = new HashSet<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msglogin);
        ButterKnife.bind(this);
        mobile = getIntent().getStringExtra("mobile");
        initView();
        initData();
    }

    private void initView() {
        titleText.setText("");
        rlSave.setVisibility(View.GONE);
        tvPhone.setText(mobile);

    }

    private void initData() {

        countDownTimer.start();
        passwordInputView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = passwordInputView.getText().toString();
                String inputCode = s.toString();
                if (inputCode.length() == 6) {
                    MsgLogin(inputCode);
                } else {

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long time = millisUntilFinished / 1000;
            tvSecond.setText(time + "");
        }

        @Override
        public void onFinish() {
            if (llBottom!=null){
                llBottom.setVisibility(View.GONE);
            }
        }
    };


    //获取验证码后的验证码登录
    private void MsgLogin(String code) {
        PackageManager packageManager = PwdLoginActivity.this.getPackageManager();
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(PwdLoginActivity.this.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }




        RequestParams requestParams = new RequestParams();
        requestParams.put("mobile", mobile);
        requestParams.put("sms", code);
        requestParams.put("source", "android");
        requestParams.put("version", version);


        OkHttpUtils.post(AppUrl.LOGIN_URL).params(requestParams).execute(new MyJsonCallBack<LoginBean>() {

            @Override
            public void onResponse(LoginBean loginBean) {
                if (loginBean != null && loginBean.getCode() == 2000) {
                    SharedPreferences share = PwdLoginActivity.this.getSharedPreferences("login_sucess", PwdLoginActivity.this.MODE_PRIVATE);
                    //获取第一个店铺的sid
                    List<LoginBean.DataBean.SidBean> sid = loginBean.getData().getSid();
                    String firstSid = loginBean.getData().getSid().get(0).getSid();
                    pushTag.add(firstSid);
                    String mycookie = loginBean.getData().getMycookie();
                    SharedPreferences.Editor edit = share.edit();
                    edit.putString("sid", firstSid);
                    edit.putString("mycookie", mycookie);
                    edit.putString("id", loginBean.getData().getId());
                    edit.putString("mobile", loginBean.getData().getMobile());
                    edit.putString("payMobile", sid.get(0).getManagerMobile());
                    Log.e("boss", "managerMobile号码" + sid.get(0).getManagerMobile());
                    edit.putString("managerMobile", sid.get(0).getManagerMobile());
                    edit.putString("homeTitle", sid.get(0).getTitle());
                    edit.putString("userId", mobile);
                    edit.putString("openKey",sid.get(0).getOpenKey());
                    edit.putBoolean("isLogin", true);
                    edit.commit();

                    //开始新的推送注册方式
                    JPushInterface.setTags(PwdLoginActivity.this, 0, pushTag);
                    JPushInterface.resumePush(JgApplication.context);
                    ToastUtil.shortToast(PwdLoginActivity.this, "登陆成功");
                    Intent intent = new Intent(PwdLoginActivity.this, MainActivity.class);
                    startActivity(intent);


                    //填充极光推送的相关信息
                   /* JMessageClient.login(mobile, mobile, new BasicCallback() {
                        @Override
                        public void gotResult(int responseCode, String responseMessage) {
                            if (responseCode == 0) {
                                UserInfo myInfo = JMessageClient.getMyInfo();
                                String username = myInfo.getUserName();
                                String appKey = myInfo.getAppKey();
                                JPushInterface.setTags(getActivity(), 0, pushTag);
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                getActivity().startActivity(intent);
                                ToastUtil.shortToast(getActivity(), "登陆成功");
                                getActivity().finish();
                            }
                        }

                    });*/


                } else {
                    ToastUtil.shortToast(PwdLoginActivity.this, "登录失败");
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
                ToastUtil.shortToast(PwdLoginActivity.this, "服务器错误，请稍后重试");
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.title_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.title_text:
                break;
        }
    }
}
