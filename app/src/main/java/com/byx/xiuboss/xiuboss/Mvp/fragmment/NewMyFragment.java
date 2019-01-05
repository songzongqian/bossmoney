package com.byx.xiuboss.xiuboss.Mvp.fragmment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;


import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.byx.xiuboss.xiuboss.Bean.MyFragmentBean;
import com.byx.xiuboss.xiuboss.Bean.RatioBean;
import com.byx.xiuboss.xiuboss.Bean.SwitchBean;
import com.byx.xiuboss.xiuboss.Mvp.activity.HelpActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.OnLineServiceActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.SettingActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.WalletActivity;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.GetHeaderPwd;
import com.byx.xiuboss.xiuboss.Utils.NetUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestHeaders;
import com.lzy.okhttputils.model.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;
import retrofit2.http.PATCH;

public class NewMyFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_shopName)
    TextView tvShopName;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.identity)
    TextView identity;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_fanXian)
    TextView tvFanXian;
    @BindView(R.id.tv_biLi)
    TextView tvBiLi;
    @BindView(R.id.button_image_two)
    ImageView buttonImageTwo;
    @BindView(R.id.relativeLayout_btn)
    RelativeLayout relativeLayoutBtn;
    @BindView(R.id.rl_wallet)
    RelativeLayout rlWallet;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.isOpen)
    Switch isOpen;
    @BindView(R.id.rl_voice)
    RelativeLayout rlVoice;
    @BindView(R.id.rl_serviceCenter)
    RelativeLayout rlServiceCenter;
    @BindView(R.id.line_two)
    View lineTwo;
    @BindView(R.id.rl_question)
    RelativeLayout rlQuestion;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    @BindView(R.id.ll_myUI)
    LinearLayout llMyUI;
    @BindView(R.id.iv_internet)
    ImageView ivInternet;
    @BindView(R.id.tv_internet)
    TextView tvInternet;
    @BindView(R.id.rl_empty_internet)
    RelativeLayout rlEmptyInternet;
    Unbinder unbinder;
    private SharedPreferences sharedPreferences;
    private String sid;
    private String mobile1;
    private String managerMobile1;
    private PopupWindow window;
    final List<String> mOptionsItems = new ArrayList<>();
    MyFragmentBean middleBean;
    Map<String,String> headerMap=new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_newmy, container, false);
        sharedPreferences = getActivity().getSharedPreferences("login_sucess", getActivity().MODE_PRIVATE);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        initData();
        return inflate;
    }


    private void initView() {
        tvTitle.setText("我的");
        line.setAlpha(0.1f);
        lineTwo.setAlpha(0.1f);

    }


    private void initData() {
        if (NetUtils.isConnected(getActivity())) {
            rlEmptyInternet.setVisibility(View.GONE);
            llMyUI.setVisibility(View.VISIBLE);
            sid = sharedPreferences.getString("sid", "");
            mobile1 = sharedPreferences.getString("mobile", "");
            managerMobile1 = sharedPreferences.getString("managerMobile", "");


            String timeFlag = GetHeaderPwd.getTimeFlag();
            headerMap.put("sid",sid);
            headerMap.put("mobile",mobile1);
            headerMap.put("t",timeFlag);

            String[] array={"sid","mobile","t"};
            String md5 = GetHeaderPwd.getMd5(headerMap, array,timeFlag);

            RequestHeaders headers= new RequestHeaders();
            headers.put("sign",md5);
            headers.put("appid","148");

            RequestParams params = new RequestParams();
            params.put("sid", sid);
            params.put("mobile", mobile1);
            params.put("t",timeFlag);
           // params.put("debug","1");
            OkHttpUtils.post(AppUrl.NEWMY_URL).headers(headers).params(params).execute(new MyJsonCallBack<MyFragmentBean>() {

                @Override
                public void onResponse(MyFragmentBean myFragmentBean) {
                    System.out.println(myFragmentBean);
                    if (myFragmentBean != null && myFragmentBean.getCode() == 2000) {
                        middleBean= myFragmentBean;
                        tvShopName.setText(myFragmentBean.getData().getTitle());
                        tvBiLi.setText(myFragmentBean.getData().getReturnratio()+"%");//返现比例
                        RequestOptions requestOptions = new RequestOptions()
                                .error(R.mipmap.my_icon_portrait_s)
                                .diskCacheStrategy(DiskCacheStrategy.ALL);
                        Glide.with(getActivity()).load(myFragmentBean.getData().getLogo()).apply(requestOptions).into(ivAvatar);
                        userName.setText(myFragmentBean.getData().getUsername());
                        identity.setText(myFragmentBean.getData().getRole());
                    } else {

                    }
                }

                @Override
                public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                    super.onError(call, response, e);
                    //服务器错误
                    llMyUI.setVisibility(View.GONE);
                    rlEmptyInternet.setVisibility(View.VISIBLE);
                    tvInternet.setText("服务器错误，请点击重试~");
                    rlEmptyInternet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            initData();
                        }
                    });
                }
            });


        } else {
            rlEmptyInternet.setVisibility(View.VISIBLE);
            tvInternet.setText("怎么没有网络了呢~");
            rlEmptyInternet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initData();
                }
            });

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.relativeLayout_btn,R.id.rl_wallet, R.id.isOpen, R.id.rl_voice, R.id.rl_serviceCenter, R.id.rl_question, R.id.rl_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relativeLayout_btn:
                //返现比例
                if(mOptionsItems!=null){
                    mOptionsItems.clear();
                }

                for(int k=1;k<=100;k++){
                    mOptionsItems.add(k+"");
                }

                OptionsPickerView pvOptions = new  OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        String tx = mOptionsItems.get(options1);
                        //上传比例到服务器
                        RequestParams requestParams=new RequestParams();
                        requestParams.put("sid",sid);
                        requestParams.put("setKey","cashRatio");
                        requestParams.put("setValue",tx);
                        requestParams.put("source","android");
                        requestParams.put("debug","1");
                        OkHttpUtils.post(AppUrl.CASHRADIO_URL).params(requestParams).execute(new MyJsonCallBack<RatioBean>() {

                            @Override
                            public void onResponse(RatioBean ratioBean) {
                                if(ratioBean!=null && ratioBean.getCode()==2000){
                                    tvBiLi.setText(tx+"%");
                                }


                            }

                            @Override
                            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                                super.onError(call, response, e);
                                e.printStackTrace();
                            }
                        });



                    }
                }).build();
                pvOptions.setPicker(mOptionsItems);
                pvOptions.show();


                break;
            case R.id.rl_wallet:
                //钱包
                Intent walletIntent = new Intent(getActivity(), WalletActivity.class);
                walletIntent.putExtra("amount",middleBean.getData().getAmount());
                getActivity().startActivity(walletIntent);
                break;
            case R.id.isOpen:
                //收款播报
                isOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            //选中
                            RequestParams requestParams = new RequestParams();
                            requestParams.put("sid", sid);
                            requestParams.put("broadcast", "1");
                            OkHttpUtils.post(AppUrl.OPEN_SWITCH).params(requestParams).execute(new MyJsonCallBack<SwitchBean>() {
                                @Override
                                public void onResponse(SwitchBean switchBean) {
                                    if (switchBean.getCode() == 2000) {
                                        Log.i("szq", "用户开启播报成功");
                                    }
                                }

                                @Override
                                public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                                    super.onError(call, response, e);
                                }
                            });

                        } else {
                            //未选中
                            RequestParams requestParams = new RequestParams();
                            requestParams.put("sid", sid);
                            requestParams.put("broadcast", "0");
                            OkHttpUtils.post(AppUrl.OPEN_SWITCH).params(requestParams).execute(new MyJsonCallBack<SwitchBean>() {
                                @Override
                                public void onResponse(SwitchBean switchBean) {
                                    if (switchBean.getCode() == 2000) {
                                        Log.i("szq", "用户关闭播报成功");
                                    }
                                }

                                @Override
                                public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                                    super.onError(call, response, e);
                                }
                            });

                        }
                    }
                });
                break;
            case R.id.rl_serviceCenter:
                //客服中心
                showPopupWindow();
                backgroundAlpha(0.5f);
                window.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        backgroundAlpha(1.0f);
                    }
                });
                break;
            case R.id.rl_question:
                //常见问题
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_setting:
                //设置
                Intent settingIntent = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(settingIntent);
                break;
        }
    }


    private void showPopupWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_phone, null, false);
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        RelativeLayout manager = contentView.findViewById(R.id.manager);
        TextView mobile = contentView.findViewById(R.id.mobile);
        TextView managerMobile = contentView.findViewById(R.id.managerMobile);
        RelativeLayout Customerservice = contentView.findViewById(R.id.Customerservice);
        final TextView managerName = contentView.findViewById(R.id.manager_name);
        final TextView customerServiceName = contentView.findViewById(R.id.customerservice_name);
        RelativeLayout rlOnline = contentView.findViewById(R.id.rl_Online);
        Button cancel = contentView.findViewById(R.id.cancel);
        mobile.setText("0710-3780521");
        managerMobile.setText(managerMobile1);
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                showCallPopupWindow(managerName.getText().toString(), managerMobile1);

            }
        });
        Customerservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                showCallPopupWindow(customerServiceName.getText().toString(), "0710-3780521");

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window != null) {
                    window.dismiss();
                }
            }
        });

        rlOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到在线客服页面
                if (window != null) {
                    window.dismiss();
                }
                Intent intent = new Intent(getActivity(), OnLineServiceActivity.class);
                getActivity().startActivity(intent);
            }
        });
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my, null), Gravity.BOTTOM, 0, 0);

    }


    private void showCallPopupWindow(String name, String call) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_call, null, false);
        PopupWindow window = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView callName = contentView.findViewById(R.id.call_name);
        final TextView callNumber = contentView.findViewById(R.id.call_number);
        TextView callDial = contentView.findViewById(R.id.call_dial);
        TextView callCancel = contentView.findViewById(R.id.call_cancel);
        callName.setText(name);
        callNumber.setText(call);
        callDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用兼容库就无需判断系统版本
                if (Build.VERSION.SDK_INT >= 22) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 2000);
                    } else {

                        call(callNumber.getText().toString());

                    }
                } else {
                    call(callNumber.getText().toString());

                }
            }
        });
        callCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();

            }
        });
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        backgroundAlpha(0.5f);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my, null), Gravity.CENTER, 0, 0);
    }


    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * 调用拨号界面	 * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        getActivity().startActivity(intent);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //Fragment失去焦点
        } else {
            initData();
        }
    }


}
