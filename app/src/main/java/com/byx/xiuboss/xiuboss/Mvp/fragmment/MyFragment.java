package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.MyFragmentBean;
import com.byx.xiuboss.xiuboss.Bean.SwitchBean;
import com.byx.xiuboss.xiuboss.Jpush.MyReceiver;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.BalanceActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.HelpActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.OnLineServiceActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.SettingActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.SwichActivity;
import com.byx.xiuboss.xiuboss.Mvp.view.CommonPopupWindow;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.NetUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestParams;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;


public class MyFragment extends BaseFragment implements View.OnClickListener, CommonPopupWindow.ViewInterface {

    Unbinder unbinder;
    private ImageView settings;
    private TextView title;
    private TextView mobile;
    private TextView adMinistrators;
    private CircleImageView imageView;
    private TextView Accountbalance;
    private ImageView buttonImage;
    private Switch isOpen;
    private ImageView buttonImageTwo;
    private Button switchBtn;
    private RelativeLayout Accountbalance_btn;
    private RelativeLayout relativeLayout_btn;
    private SharedPreferences sharedPreferences;
    private Map<String, String> tags = new HashMap<>();
    private Intent intent;
    private CommonPopupWindow popupWindow;
    private String mobile1;
    private String managerMobile1;
    private String id;
    private String sid;
    private Activity activity;
    private PopupWindow window;
    private MyReceiver receiver;
    private CircleImageView avator;
    private Switch storeWork;
    private LinearLayout myUI;
    private RelativeLayout rlEmpty;
    private ImageView ivInternet;
    private TextView tvInternet;
    private RelativeLayout rlHelp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        sharedPreferences = getActivity().getSharedPreferences("login_sucess", getActivity().MODE_PRIVATE);
        initView(view);
        initData();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    //处理事件逻辑
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEventBus(String object) {
        if (object.equals(sharedPreferences.getString("sid", ""))) {
            initData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        if (NetUtils.isConnected(getActivity())) {
            myUI.setVisibility(View.VISIBLE);
            rlEmpty.setVisibility(View.GONE);
            sid = sharedPreferences.getString("sid", "");
            id = sharedPreferences.getString("id", "");
            mobile1 = sharedPreferences.getString("mobile", "");
            managerMobile1 = sharedPreferences.getString("managerMobile", "");
            Log.e("---------id--------", id);
            Log.e("-----sid-----", sid);
            Log.e("-----mobile-----", mobile1);
            RequestParams params = new RequestParams();
            params.put("sid", sid);
            params.put("mobile", mobile1);
            OkHttpUtils.post(AppUrl.MYPAGE_URL).params(params).execute(new MyJsonCallBack<MyFragmentBean>() {
                @Override
                public void onResponse(MyFragmentBean myFragmentBean) {
                    if (myFragmentBean != null && myFragmentBean.getCode() == 2000) {
                        title.setText(sharedPreferences.getString("homeTitle", ""));
                        mobile.setText(myFragmentBean.getData().getMobile());
                        adMinistrators.setText(myFragmentBean.getData().getUsername() + " |" + myFragmentBean.getData().getRole());
                        Glide.with(getActivity()).load(myFragmentBean.getData().getLogo()).into(imageView);
                        Accountbalance.setText(myFragmentBean.getData().getAmount());
                        String broadcast = myFragmentBean.getData().getBroadcast();
                        if (broadcast.equals("1")) {
                            isOpen.setChecked(true);
                        } else if (broadcast.equals("0")) {
                            isOpen.setChecked(false);
                        }
                        String isInBusiness = myFragmentBean.getData().getIs_in_business();
                        if (isInBusiness.equals("1")) {
                            storeWork.setChecked(true);
                        } else if (isInBusiness.equals("0")) {
                            storeWork.setChecked(false);
                        }

                    } else {

                    }
                }

                @Override
                public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                    super.onError(call, response, e);
                    //服务器错误
                    myUI.setVisibility(View.GONE);
                    rlEmpty.setVisibility(View.VISIBLE);
                    tvInternet.setText("服务器错误，请点击重试~");
                    rlEmpty.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            initData();
                        }
                    });

                }
            });
        } else {
            //无网络
            myUI.setVisibility(View.GONE);
            rlEmpty.setVisibility(View.VISIBLE);
            tvInternet.setText("咦？怎么木有网了呢~");
            rlEmpty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initData();
                }
            });
        }
    }

    private void initView(View view) {
        settings = (ImageView) view.findViewById(R.id.settings);
        title = (TextView) view.findViewById(R.id.title);
        mobile = (TextView) view.findViewById(R.id.mobile);
        adMinistrators = (TextView) view.findViewById(R.id.adMinistrators);
        imageView = (CircleImageView) view.findViewById(R.id.imageView);
        Accountbalance = (TextView) view.findViewById(R.id.Accountbalance);
        Accountbalance_btn = (RelativeLayout) view.findViewById(R.id.Accountbalance_btn);
        relativeLayout_btn = (RelativeLayout) view.findViewById(R.id.relativeLayout_btn);
        buttonImage = (ImageView) view.findViewById(R.id.button_image);
        isOpen = (Switch) view.findViewById(R.id.isOpen);
        buttonImageTwo = (ImageView) view.findViewById(R.id.button_image_two);
        switchBtn = (Button) view.findViewById(R.id.switch_btn);
        storeWork = view.findViewById(R.id.store_open);
        avator = view.findViewById(R.id.imageView);
        myUI = view.findViewById(R.id.ll_myUI);
        rlEmpty = view.findViewById(R.id.rl_empty_internet);
        ivInternet = view.findViewById(R.id.iv_internet);
        tvInternet = view.findViewById(R.id.tv_internet);
        rlHelp = view.findViewById(R.id.rl_help);

        RelativeLayout mOrderCompLayout = view.findViewById(R.id.orderCompLayout);
        RelativeLayout mOrderInviteLayout = view.findViewById(R.id.orderInviteLayout);
        TextView mOrderCompRed = view.findViewById(R.id.orderCompRed);
        TextView mOrderInviteRed = view.findViewById(R.id.orderInviteRed);


        activity = new MainActivity();
        Accountbalance_btn.setOnClickListener(this);
        relativeLayout_btn.setOnClickListener(this);
        switchBtn.setOnClickListener(this);
        settings.setOnClickListener(this);
        isOpen.setOnClickListener(this);
        storeWork.setOnClickListener(this);
        rlHelp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings:
                //设置
                intent = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.Accountbalance_btn:
                //账户余额
                intent = new Intent(getActivity(), BalanceActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.relativeLayout_btn:

                showPopupWindow();
                backgroundAlpha(0.5f);
                window.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        backgroundAlpha(1.0f);
                    }
                });
                //客服电话
                break;
            case R.id.switch_btn:
                intent = new Intent(getActivity(), SwichActivity.class);
                intent.putExtra("id", sid);
                getActivity().startActivity(intent);
                //切换店铺
                break;
            //收款播报
            case R.id.isOpen:
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
            //营业状态
            case R.id.store_open:
                storeWork.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            //开启营业状态
                            RequestParams requestParams = new RequestParams();
                            requestParams.put("sid", sid);
                            requestParams.put("is_in_business", "1");
                            OkHttpUtils.post(AppUrl.STORE_MANAGER).params(requestParams).execute(new MyJsonCallBack<SwitchBean>() {
                                @Override
                                public void onResponse(SwitchBean switchBean) {
                                    if (switchBean.getCode() == 2000) {
                                        Log.i("szq", "打开店铺成功");
                                    }
                                }
                            });

                        } else {
                            //关闭店铺
                            RequestParams requestParams = new RequestParams();
                            requestParams.put("sid", sid);
                            requestParams.put("is_in_business", "0");
                            OkHttpUtils.post(AppUrl.STORE_MANAGER).params(requestParams).execute(new MyJsonCallBack<SwitchBean>() {
                                @Override
                                public void onResponse(SwitchBean switchBean) {
                                    if (switchBean.getCode() == 2000) {
                                        Log.i("szq", "关闭店铺成功");
                                    }
                                }
                            });
                        }
                    }
                });
                break;

            case R.id.rl_help:
                //商家帮助中心
                Intent intent=new Intent(getActivity(),HelpActivity.class);
                getActivity().startActivity(intent);
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
        RelativeLayout rlOnline=contentView.findViewById(R.id.rl_Online);
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
                Intent intent=new Intent(getActivity(),OnLineServiceActivity.class);
                getActivity().startActivity(intent);
            }
        });
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my, null), Gravity.BOTTOM, 0, 0);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call(managerMobile1);
                } else {
                    Toast.makeText(activity, "请手动开启打电话权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2000:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call(mobile1);
                } else {
                    Toast.makeText(activity, "请手动开启打电话权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.popup_phone:
                RelativeLayout manager = view.findViewById(R.id.manager);
                TextView mobile = view.findViewById(R.id.mobile);
                TextView managerMobile = view.findViewById(R.id.managerMobile);
                final TextView managerName = view.findViewById(R.id.manager_name);
                final TextView customerServiceName = view.findViewById(R.id.customerservice_name);
                RelativeLayout Customerservice = view.findViewById(R.id.Customerservice);
                Button cancel = view.findViewById(R.id.cancel);
                mobile.setText("0710-3780521");
                managerMobile.setText(managerMobile1);
                manager.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        call(managerMobile1);
                        showCallPopupWindow(managerName.getText().toString(), managerMobile1);

                    }
                });
                Customerservice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCallPopupWindow(customerServiceName.getText().toString(), "0710-3780521");
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                    }
                });
                break;
        }
    }

    private void showCallPopupWindow(String name, String call) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_call, null, false);
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //fragment失去焦点

        } else {
            initData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}