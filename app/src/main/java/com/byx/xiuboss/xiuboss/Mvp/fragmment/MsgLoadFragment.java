package com.byx.xiuboss.xiuboss.Mvp.fragmment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.byx.xiuboss.xiuboss.Utils.RexUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestHeaders;
import com.lzy.okhttputils.model.RequestParams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import okhttp3.Call;
import okhttp3.Response;

public class MsgLoadFragment extends BaseFragment {

    @BindView(R.id.login_mobile)
    ClearWriteEditText loginMobile;
    @BindView(R.id.login_checkCode)
    ClearWriteEditText loginCheckCode;
    @BindView(R.id.getCode)
    RelativeLayout getCode;
    @BindView(R.id.ll_name_psw)
    LinearLayout llNamePsw;
    @BindView(R.id.btn_login_gray)
    Button btnLoginGray;
    @BindView(R.id.btn_login_light)
    Button btnLoginLight;
    Unbinder unbinder;
    @BindView(R.id.tv_Code)
    TextView tvCode;
    private String mobile;
    private String smCode;
    private Set<String> pushTag = new HashSet<>();
    private SharedPreferences share;
    private String version;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_msg_login, container, false);
        initData();
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    private void initData() {


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.getCode, R.id.btn_login_gray, R.id.btn_login_light})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getCode:
                mobile = loginMobile.getText().toString().trim();
                if (!TextUtils.isEmpty(mobile) && RexUtils.isMobileNO(mobile)) {
                    //请求验证码接口
                    countDownTimer.start();
                    RequestParams params = new RequestParams();
                    params.put("mobile", mobile);
                    OkHttpUtils.post(AppUrl.GET_LOGINCODE).params(params).execute(new MyJsonCallBack<MsgCodeBean>() {

                        @Override
                        public void onResponse(MsgCodeBean msgCodeBean) {
                            if (msgCodeBean != null && msgCodeBean.getCode() == 2000) {

                            }
                        }

                        @Override
                        public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                            super.onError(call, response, e);
                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_login_gray:
                break;
            case R.id.btn_login_light:
                mobile = loginMobile.getText().toString().trim();
                smCode = loginCheckCode.getText().toString().trim();

                if (!TextUtils.isEmpty(mobile) && RexUtils.isMobileNO(mobile) && !TextUtils.isEmpty(smCode)) {
                    MsgLogin();
                } else {
                    Toast.makeText(getActivity(), "手机号或验证码不能为空", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }


    //获取验证码后的验证码登录
    private void MsgLogin() {
        PackageManager packageManager = getActivity().getPackageManager();
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }




        RequestHeaders  headers=new RequestHeaders();

        RequestParams requestParams = new RequestParams();
        requestParams.put("mobile", mobile);
        requestParams.put("sms", smCode);
        requestParams.put("source","android");
        requestParams.put("version",version);
        OkHttpUtils.post(AppUrl.LOGIN_URL).params(requestParams).execute(new MyJsonCallBack<LoginBean>() {

            @Override
            public void onResponse(LoginBean loginBean) {
                if (loginBean != null && loginBean.getCode() == 2000) {
                    share = getActivity().getSharedPreferences("login_sucess", getActivity().MODE_PRIVATE);
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
                    edit.putBoolean("isLogin", true);
                    edit.commit();

                    //开始新的推送注册方式
                    JPushInterface.setTags(getActivity(), 0, pushTag);
                    JPushInterface.resumePush(JgApplication.context);
                    ToastUtil.shortToast(getActivity(), "登陆成功");
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();


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
                    ToastUtil.shortToast(getActivity(), "登录失败");
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
                ToastUtil.shortToast(getActivity(), "服务器错误，请稍后重试");
            }
        });
    }


    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long time = millisUntilFinished / 1000;
            tvCode.setText(time + "秒后重新获取");
            getCode.setClickable(false);
        }

        @Override
        public void onFinish() {
            if(tvCode!=null){
                tvCode.setText("获取验证码");
            }else{

            }

            if(getCode!=null){
                getCode.setClickable(true);
            }else{

            }


        }
    };

    @Override
    public void onDestroy() {
        if(countDownTimer!=null){
            countDownTimer.cancel();
            countDownTimer.onFinish();
            countDownTimer=null;
            super.onDestroy();
        }
    }
}
