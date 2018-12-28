package com.byx.xiuboss.xiuboss.Mvp.fragmment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Application.JgApplication;
import com.byx.xiuboss.xiuboss.Bean.LoginBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.view.ClearWriteEditText;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.RexUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestParams;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Response;

public class PwdLoadFragment extends BaseFragment {

    @BindView(R.id.login_userName)
    ClearWriteEditText loginUserName;
    @BindView(R.id.user_line)
    View userLine;
    @BindView(R.id.login_passWord)
    ClearWriteEditText loginPassWord;
    @BindView(R.id.ll_name_psw)
    LinearLayout llNamePsw;
    @BindView(R.id.btn_login_gray)
    Button btnLoginGray;
    @BindView(R.id.btn_login_light)
    Button btnLoginLight;
    Unbinder unbinder;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    private String userName;
    private String passWord;
    private Set<String> pushTag = new HashSet<>();
    private SharedPreferences share;
    private String version;
    private int flagPoint = 0;

    /**
     * 密码登录的页面
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_pwd_login, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initData();
        return inflate;
    }

    private void initData() {


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_login_gray, R.id.btn_login_light,R.id.iv_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_gray:
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
            case R.id.btn_login_light:
                userName = loginUserName.getText().toString().trim();
                passWord = loginPassWord.getText().toString().trim();
                if (TextUtils.isEmpty(userName) || RexUtils.isMobileNO(userName) == false) {
                    Toast.makeText(getActivity(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                //手机号和密码不能为空
                if (!TextUtils.isEmpty(userName) && RexUtils.isMobileNO(userName) && !TextUtils.isEmpty(passWord)) {
                    PackageManager packageManager = getActivity().getPackageManager();
                    try {
                        PackageInfo packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
                        version = packInfo.versionName;
                        System.out.println("当前的版本号" + version);
                    } catch (PackageManager.NameNotFoundException e){
                        e.printStackTrace();
                    }
                    RequestParams requestParams = new RequestParams();
                    requestParams.put("mobile", userName);
                    requestParams.put("password", passWord);
                    requestParams.put("source", "android");
                    requestParams.put("version", version);

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
                                edit.putString("userId", userName);
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
                               /* JMessageClient.login(userName, userName, new BasicCallback() {
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

                            } else if (loginBean != null && loginBean.getCode() == -1) {
                                ToastUtil.shortToast(getActivity(), "密码错误");
                            }

                        }

                        @Override
                        public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                            super.onError(call, response, e);
                            ToastUtil.shortToast(getActivity(), "服务器错误，请稍后重试");
                        }
                    });

                }
                break;
        }
    }

    @OnClick(R.id.iv_eye)
    public void onViewClicked() {
    }
}
