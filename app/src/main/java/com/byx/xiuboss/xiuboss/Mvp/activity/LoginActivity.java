package com.byx.xiuboss.xiuboss.Mvp.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Application.JgApplication;
import com.byx.xiuboss.xiuboss.Bean.LoginBean;
import com.byx.xiuboss.xiuboss.Bean.MsgCodeBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.view.ClearWriteEditText;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.Base64Utils;
import com.byx.xiuboss.xiuboss.Utils.GetHeaderPwd;
import com.byx.xiuboss.xiuboss.Utils.RexUtils;
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
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Response;


/**
 * 1月份用户登录页面
 */
public class LoginActivity extends BaseActivity {
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
    @BindView(R.id.login_userName)
    ClearWriteEditText loginUserName;
    @BindView(R.id.rl_userName)
    RelativeLayout rlUserName;
    @BindView(R.id.login_passWord)
    ClearWriteEditText loginPassWord;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.rl_Password)
    RelativeLayout rlPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_getCode)
    Button btnGetCode;
    @BindView(R.id.tv_loginTip)
    TextView tvLoginTip;
    @BindView(R.id.rl_loginTip)
    RelativeLayout rlLoginTip;
    int flag=0;
    private int flagPoint = 0;
    private String passWord;
    private String version;
    private Set<String> pushTag = new HashSet<>();
    private String userName;
    Map<String,String> headerMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indexlogin);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        setStatusBar(true);
        titleText.setVisibility(View.INVISIBLE);
        rlPassword.setVisibility(View.INVISIBLE);
        tvLoginTip.setText("密码登录");
        loginTitle.setText("验证码登录");
        btnGetCode.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.INVISIBLE);
        btnGetCode.setAlpha(0.4f);

        //当把用户名删除后头像要换成默认的
        loginUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (loginUserName.getText().length() == 0 ) {
                    btnGetCode.setEnabled(false);
                    btnGetCode.setAlpha(0.4f);

                } else {
                    btnGetCode.setEnabled(true);
                    btnGetCode.setAlpha(1.0f);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        loginPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (loginPassWord.getText().length() == 0 ) {
                    btnLogin.setEnabled(false);
                    btnLogin.setAlpha(0.4f);

                } else {
                    btnLogin.setEnabled(true);
                    btnLogin.setAlpha(1.0f);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initData() {


    }

    @OnClick({R.id.rl_back, R.id.iv_eye, R.id.btn_login, R.id.btn_getCode, R.id.rl_loginTip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_eye:

                if(flagPoint==0){
                    //显示明文
                    passWord = loginPassWord.getText().toString().trim();
                    if(TextUtils.isEmpty(passWord)){
                        ivEye.setBackgroundResource(R.mipmap.login_icon_hide);
                        flagPoint=1;
                    }else{
                        loginPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        loginPassWord.setSelection(passWord.length());
                        ivEye.setBackgroundResource(R.mipmap.login_icon_hide);
                        flagPoint=1;
                    }
                }else if(flagPoint==1){
                    //显示密文
                    passWord = loginPassWord.getText().toString().trim();
                    if(TextUtils.isEmpty(passWord)){
                        ivEye.setBackgroundResource(R.mipmap.login_icon_show);
                        flagPoint=0;
                    }else{
                        loginPassWord.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        loginPassWord.setSelection(passWord.length());
                        ivEye.setBackgroundResource(R.mipmap.login_icon_show);
                        flagPoint=0;
                    }
                }

                break;
            case R.id.btn_login:
                //点击密码登录
                userName = loginUserName.getText().toString().trim();
                passWord = loginPassWord.getText().toString().trim();
                if (TextUtils.isEmpty(userName) || RexUtils.isMobileNO(userName) == false) {
                    Toast.makeText(LoginActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(passWord)){
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                //手机号和密码不能为空
                if (!TextUtils.isEmpty(userName) && RexUtils.isMobileNO(userName) && !TextUtils.isEmpty(passWord)) {
                    PackageManager packageManager =LoginActivity.this.getPackageManager();
                    try {
                        PackageInfo packInfo = packageManager.getPackageInfo(LoginActivity.this.getPackageName(), 0);
                        version = packInfo.versionName;
                        System.out.println("当前的版本号" + version);
                    } catch (PackageManager.NameNotFoundException e){
                        e.printStackTrace();
                    }


                    if(headerMap!=null){
                        headerMap.clear();
                    }
                    String md5Pwd = Base64Utils.MD5(passWord);
                    String timeFlag = GetHeaderPwd.getTimeFlag();
                    headerMap.put("mobile",userName);
                    headerMap.put("password",passWord);
                    headerMap.put("source","android");
                    headerMap.put("version",version);
                    headerMap.put("t",timeFlag);

                    String[] array={"mobile","password","source","version","t"};
                    String md5 = GetHeaderPwd.getMd5(headerMap, array,timeFlag);

                    RequestHeaders headers=new RequestHeaders();
                    headers.put("sign",md5);
                    headers.put("appid","148");


                    RequestParams requestParams = new RequestParams();
                    requestParams.put("mobile", userName);
                    requestParams.put("password", passWord);
                    requestParams.put("source", "android");
                    requestParams.put("version", version);
                    requestParams.put("t",timeFlag);

                    OkHttpUtils.post(AppUrl.LOGINNEW_URL).params(requestParams).headers(headers).execute(new MyJsonCallBack<LoginBean>() {
                        @Override
                        public void onResponse(LoginBean loginBean) {
                          if (loginBean != null && loginBean.getCode() == 2000) {
                                SharedPreferences share = LoginActivity.this.getSharedPreferences("login_sucess", LoginActivity.this.MODE_PRIVATE);
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
                                edit.putString("userId", userName);
                                edit.putBoolean("isLogin", true);
                                edit.putString("openKey",sid.get(0).getOpenKey());
                                edit.commit();


                                //开始新的推送注册方式
                                JPushInterface.setTags(LoginActivity.this, 0, pushTag);
                                JPushInterface.resumePush(JgApplication.context);
                                ToastUtil.shortToast(LoginActivity.this, "登陆成功");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();




                            } else if (loginBean != null && loginBean.getCode() == -1) {
                                ToastUtil.shortToast(LoginActivity.this, "密码错误");
                            }

                        }

                        @Override
                        public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                            super.onError(call, response, e);
                            ToastUtil.shortToast(LoginActivity.this, "密码错误");
                        }
                    });

                }

                break;
            case R.id.btn_getCode:
                //点击获取验证码
               String  mobile = loginUserName.getText().toString().trim();
                if (!TextUtils.isEmpty(mobile) && RexUtils.isMobileNO(mobile)){
                    //输入手机号码
                    RequestParams params = new RequestParams();
                    params.put("mobile", mobile);
                    OkHttpUtils.post(AppUrl.GET_LOGINCODE).params(params).execute(new MyJsonCallBack<MsgCodeBean>() {

                        @Override
                        public void onResponse(MsgCodeBean msgCodeBean) {
                            if (msgCodeBean != null && msgCodeBean.getCode() == 2000){
                                Intent intent=new Intent(LoginActivity.this,PwdLoginActivity.class);
                                intent.putExtra("mobile",mobile);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                            super.onError(call, response, e);
                        }
                    });

                }else{
                    Toast.makeText(LoginActivity.this, "请输入正确的手机号码", Toast.LENGTH_LONG).show();
                 }
                break;
            case R.id.rl_loginTip:
                //切换登录方式
                if(flag==0){
                    //切换到密码登录
                    tvLoginTip.setText("验证码登录");
                    loginTitle.setText("密码登录");
                    rlPassword.setVisibility(View.VISIBLE);
                    btnGetCode.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    flag=1;
                }else if(flag==1){
                    //切换到验证码登录
                    flag=0;
                    tvLoginTip.setText("密码登录");
                    loginTitle.setText("验证码登录");
                    rlPassword.setVisibility(View.INVISIBLE);
                    btnGetCode.setVisibility(View.VISIBLE);
                    btnLogin.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }
}
