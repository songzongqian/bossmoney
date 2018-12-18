package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.HomeBean;
import com.byx.xiuboss.xiuboss.Bean.RewardBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.Mvp.activity.BalanceActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.ManageActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.PayCodeActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.ReceivablesActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.TipsActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.TodayMoneyActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.WithDrawTipActivity;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.Contast;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.NetUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.lzy.okhttputils.model.RequestParams;
import com.yzq.zxinglibrary.common.Constant;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;
import okhttp3.Call;
import okhttp3.Response;
import pl.droidsonroids.gif.GifImageView;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.home_title)
    TextView home_title;
    @BindView(R.id.Totalamount)
    TextView Totalamount;
    @BindView(R.id.Totalamount_money)
    TextView TotalamountMoney;
    @BindView(R.id.yesterday_money)
    TextView yesterdayMoney;
    @BindView(R.id.textView29)
    TextView textView29;
    @BindView(R.id.home_icon_tips)
    ImageView homeIconTips;
    @BindView(R.id.balance_money)
    TextView balanceMoney;
    @BindView(R.id.grade_money)
    TextView gradeMoney;
    @BindView(R.id.progressBarHorizontal)
    ProgressBar progressBarHorizontal;
    @BindView(R.id.backward_money)
    TextView backwardMoney;
    @BindView(R.id.button_reward)
    Button buttonReward;
    @BindView(R.id.mid_money)
    TextView midMoney;
    @BindView(R.id.max_money)
    TextView maxMoney;
    @BindView(R.id.textView35)
    TextView textView35;
    @BindView(R.id.home_max_money)
    TextView homeMaxMoney;
    @BindView(R.id.progressBarHorizontal_two)
    ProgressBar progressBarHorizontalTwo;
    @BindView(R.id.Service_Charge_money)
    TextView ServiceChargeMoney;
    Unbinder unbinder;
    @BindView(R.id.dengji)
    TextView dengji;
    @BindView(R.id.difference_money)
    TextView differenceMoney;
    @BindView(R.id.reward_money)
    TextView rewardMoney;
    @BindView(R.id.button_reward_two)
    CountdownView buttonRewardTwo;
    @BindView(R.id.relative_Totalamount)
    RelativeLayout relativeTotalamount;
    @BindView(R.id.receivables)
    LinearLayout receivables;
    @BindView(R.id.business)
    LinearLayout business;
    @BindView(R.id.wallet)
    LinearLayout wallet;
    @BindView(R.id.receipt_code)
    LinearLayout receiptCode;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.ll_count)
    LinearLayout ll_btn2;
    @BindView(R.id.button_three)
    Button buttonThree;
    @BindView(R.id.gif_index)
    GifImageView indexGif;
    @BindView(R.id.rl_tip)
    RelativeLayout rlTip;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_internet)
    TextView tvInternet;
    @BindView(R.id.rl_empty_internet)
    RelativeLayout rlEmptyInternet;
    @BindView(R.id.textView18)
    TextView textView18;
    @BindView(R.id.iv_internet)
    ImageView ivInternet;
    @BindView(R.id.tv_xiayi)
    TextView tvXiayi;
    @BindView(R.id.haicha)
    TextView haicha;
    @BindView(R.id.tv_jiangli)
    TextView tvJiangli;
    @BindView(R.id.tv_yuan)
    TextView tvYuan;
    @BindView(R.id.tv_maxLevel)
    TextView tvMaxLevel;
    @BindView(R.id.rl_tiXian)
    RelativeLayout rlTiXian;
    @BindView(R.id.total_fee)
    TextView totalFee;
    @BindView(R.id.customer)
    TextView customer;


    private String title;
    private String sid;
    private HomeBean homeBean;
    private View inflate;
    private View popup;
    private PopupWindow window;
    private SharedPreferences login_sucess;
    private PopupWindow window1;
    private Intent intent;
    private int REQUEST_CODE_SCAN = 111;
    private CountDownTimer countDownTimer;
    private float shoukuanFee;
    private float todayFee;
    HomeBean middleBean;
    private static final int MSG_SUCCESS = 0;//获取图片成功的标识

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //在这里实现ui更新的效果
            switch (msg.what) {
                case MSG_SUCCESS:
                    indexGif.setVisibility(View.GONE);
                    break;
            }

        }
    };
    private String currentId;


    public HomeFragment() {

    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_home, container, false);
        popup = LayoutInflater.from(getActivity()).inflate(R.layout.popup_reward, null);
        login_sucess = getActivity().getSharedPreferences("login_sucess", MODE_PRIVATE);
        unbinder = ButterKnife.bind(this, this.inflate);
        initData();
        return this.inflate;
    }

    /*请求网络数据*/
    private void initData() {
        if (NetUtils.isConnected(getActivity())) {
            //有网络
            rlEmptyInternet.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            sid = login_sucess.getString("sid", "");
            title = login_sucess.getString("title", "");
            home_title.setText(title);
            RequestParams params = new RequestParams();
            params.put("sid", sid);
            com.lzy.okhttputils.OkHttpUtils.post(AppUrl.HOME_URL).params(params).execute(new MyJsonCallBack<HomeBean>() {
                @Override
                public void onResponse(HomeBean homeBean) {
                    if (homeBean != null && homeBean.getCode() == 2000) {
                        middleBean = homeBean;
                        Log.e("boss", "首页数据请求成功");

                        //保持原版本，显示返现总收入和带来的顾客
                        totalFee.setText(homeBean.getData().getFee().getPayBillTotalFee());
                        customer.setText(homeBean.getData().getFee().getPayBillCustomer());


                        Totalamount.setText("今日收款" + homeBean.getData().getTodaySum() + "笔,合计");
                        TotalamountMoney.setText(homeBean.getData().getTodayFee() + "元");
                        yesterdayMoney.setText("昨日实时：" + homeBean.getData().getYesterFee() + "元");
                        HomeBean.DataBean.AmountBean amount = homeBean.getData().getAmount();
                        balanceMoney.setText(amount.getAmountFee() + "元");
                        midMoney.setText(homeBean.getData().getCurrentGrade().getF2());
                        maxMoney.setText(homeBean.getData().getNextGrade().getF2());
                        home_title.setText(login_sucess.getString("homeTitle", ""));
                        String id = homeBean.getData().getCurrentGrade().getId();
                        Double xyz = Double.parseDouble(amount.getAmount());


                        //获取最新接口获取的签到时间
                        long serverSignTime = new Long(homeBean.getData().getSigntime());
                        long serverTime = serverSignTime * 1000 + 86400000;
                        System.out.println("服务器返回的时间" + serverTime);

                        //获取系统当前时间
                        long sytemTime = System.currentTimeMillis();
                        System.out.println("系统当前时间" + serverTime);

                        //领取时间未到，显示倒计时
                        if (sytemTime < serverTime && xyz > 200) {
                            buttonReward.setVisibility(View.GONE);
                            buttonThree.setVisibility(View.GONE);
                            ll_btn2.setVisibility(View.VISIBLE);
                            buttonRewardTwo.setVisibility(View.VISIBLE);
                            long chaValue = serverTime - sytemTime;
                            buttonRewardTwo.start(chaValue);
                        } else if (xyz < 200) {
                            buttonReward.setVisibility(View.GONE);
                            ll_btn2.setVisibility(View.GONE);
                            buttonThree.setVisibility(View.VISIBLE);
                            buttonThree.setText("等级不足");
                        } else if (sytemTime >= serverTime && xyz >= 200) {
                            //可以重新领取
                            ll_btn2.setVisibility(View.GONE);
                            buttonThree.setVisibility(View.GONE);
                            buttonReward.setVisibility(View.VISIBLE);
                            buttonReward.setText("领取奖励");
                        }
                        //设置用户等级
                        if (id == null) {
                            dengji.setText("Lv" + "0");
                            currentId = "0";
                        } else {
                            currentId = homeBean.getData().getCurrentGrade().getId();
                            dengji.setText("Lv" + homeBean.getData().getCurrentGrade().getId());
                            System.out.println("当前等级是" + currentId);
                        }

                        System.out.println("当前等级是" + currentId);
                        if ("22".equals(currentId)) {
                            tvXiayi.setVisibility(View.GONE);
                            backwardMoney.setVisibility(View.GONE);
                            haicha.setVisibility(View.GONE);
                            differenceMoney.setVisibility(View.GONE);
                            tvJiangli.setVisibility(View.GONE);
                            rewardMoney.setVisibility(View.GONE);
                            tvYuan.setVisibility(View.GONE);
                            tvMaxLevel.setVisibility(View.VISIBLE);
                            gradeMoney.setText(homeBean.getData().getCurrentGrade().getF4());
                        } else {
                            tvXiayi.setVisibility(View.VISIBLE);
                            backwardMoney.setVisibility(View.VISIBLE);
                            haicha.setVisibility(View.VISIBLE);
                            differenceMoney.setVisibility(View.VISIBLE);
                            tvJiangli.setVisibility(View.VISIBLE);
                            rewardMoney.setVisibility(View.VISIBLE);
                            tvYuan.setVisibility(View.VISIBLE);
                            tvMaxLevel.setVisibility(View.GONE);
                            gradeMoney.setText(homeBean.getData().getCurrentGrade().getF4());
                            backwardMoney.setText("Lv" + homeBean.getData().getNextGrade().getId());
                            differenceMoney.setText(homeBean.getData().getNextDifference());
                            rewardMoney.setText(homeBean.getData().getNextGrade().getF4());
                        }


                        /**
                         * 剩余免手续费额度
                         */
                        //可免收手续费总额度
                        String shoukuan_Fee = homeBean.getData().getShoukuan_fee().replaceAll(",", "");
                        shoukuanFee = Float.parseFloat(shoukuan_Fee);

                        //获得今日收款总额
                        String today_Fee = homeBean.getData().getTodayFee().replaceAll(",", "");
                        todayFee = Float.parseFloat(today_Fee);


                        if (todayFee < shoukuanFee) {
                            float remain = shoukuanFee - todayFee;
                            ServiceChargeMoney.setText(Float.toString(remain));
                            //设置提现进度条
                            progressBarHorizontalTwo.setProgress(getProgressBarTwo());
                        } else if (todayFee >= shoukuanFee) {
                            ServiceChargeMoney.setText("0");
                            progressBarHorizontalTwo.setProgress(100);
                        }
                        // ServiceChargeMoney.setText(homeBean.getData().getMonthFee());设置等级进度条
                        progressBarHorizontal.setProgress(getProgressBar());
                    }

                }

                @Override
                public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                    super.onError(call, response, e);
                    //服务器返回错误
                    scrollView.setVisibility(View.GONE);
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
            //无网络状态
            scrollView.setVisibility(View.GONE);
            rlEmptyInternet.setVisibility(View.VISIBLE);
            tvInternet.setText("咦？怎么木有网了呢~");
            rlEmptyInternet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

    @OnClick({R.id.home_icon_tips, R.id.button_reward, R.id.relative_Totalamount, R.id.receivables, R.id.business, R.id.wallet, R.id.receipt_code, R.id.rl_tip, R.id.rl_tiXian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //领取奖励
            case R.id.button_reward:
                if (NetUtils.isConnected(getActivity())) {
                    getPopupData(); //播放声音,显示动画
                } else {
                    scrollView.setVisibility(View.GONE);
                    rlEmptyInternet.setVisibility(View.VISIBLE);
                    tvInternet.setText("咦？怎么木有网了呢~");
                    rlEmptyInternet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            initData();
                        }
                    });
                }

                break;
            //首页今日收款详情
            case R.id.relative_Totalamount:
                intent = new Intent(getActivity(), TodayMoneyActivity.class);
                startActivity(intent);
                break;
            //1、收款
            case R.id.receivables:
                intent = new Intent(getActivity(), ReceivablesActivity.class);
                intent.putExtra("sidNumber", sid);
                startActivity(intent);
                break;
            //2、商品管理页面
            case R.id.business:
                //测试打开店铺管理系统
                Intent intent = new Intent(getActivity(), ManageActivity.class);
                startActivity(intent);
                break;
            //3、钱包（账户余额）
            case R.id.wallet:
                intent = new Intent(getActivity(), BalanceActivity.class);
                startActivity(intent);
                break;
            //4、收款码
            case R.id.receipt_code:
                intent = new Intent(getActivity(), PayCodeActivity.class);
                intent.putExtra("sid", sid);
                startActivity(intent);
                break;
            //扩大等级规则点击范围
            case R.id.rl_tip:
                intent = new Intent(getActivity(), TipsActivity.class);
                startActivity(intent);
                break;
            //提现额度提示
            case R.id.rl_tiXian:
                Intent withDrawIntent = new Intent(getActivity(), WithDrawTipActivity.class);
                startActivity(withDrawIntent);
                break;
        }
    }


    //等级进度条
    private int getProgressBar() {
        String max = maxMoney.getText().toString();
        String min = midMoney.getText().toString();
        String amount = middleBean.getData().getAmount().getAmount();
        double i2 = Double.parseDouble(amount);
        int i = Integer.parseInt(max);
        int i1 = Integer.parseInt(min);
        double i3 = (i2 - i1) / (i - i1);
        Log.e("-i2--", i2 + "");
        Log.e("-i--", i + "");
        Log.e("-i1--", i1 + "");
        Log.e("i3", i3 + "");
        float v = (float) i3 * 100f;
        return (int) v;
    }


    /**
     * 收取提现手续费的地方
     *
     * @return
     */
    private int getProgressBarTwo() {
        int total = (int) shoukuanFee;
        int hasUse = (int) todayFee;
        int remainValue = total - hasUse;
        float i2 = (1000 - remainValue) / 1000f;
        Log.e("-i2--", total + "");
        Log.e("-i1--", hasUse + "");
        float v = i2 * 100f;
        return (int) v;
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    private void getPopupData() {
        /**
         * 追加领取奖励来源，暂定字段名 app : android
         */
        String id = login_sucess.getString("id", "");
        RequestParams requestParams = new RequestParams();
        requestParams.put("sid", sid);
        requestParams.put("clerk_id", id);
        requestParams.put("money", middleBean.getData().getCurrentGrade().getF4());
        com.lzy.okhttputils.OkHttpUtils.post(AppUrl.SIGN_IN_URL).params(requestParams).execute(new MyJsonCallBack<RewardBean>() {
            @Override
            public void onResponse(RewardBean rewardBean) {
                if (rewardBean != null) {
                    if (rewardBean.getCode() == 2000) {
                        final MediaPlayer player = MediaPlayer.create(getActivity(), R.raw.ring);
                        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        player.start();
                        player.setVolume(1f, 1f);
                        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                //监听播放完毕
                                player.stop();
                            }
                        });
                        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popup_success, null, false);
                        window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                        ImageView close = inflate.findViewById(R.id.icon_close);
                        TextView popupMoney = inflate.findViewById(R.id.popup_money);
                        Button btnRecord = inflate.findViewById(R.id.btn_record);
                        popupMoney.setText(middleBean.getData().getCurrentGrade().getF4() + "元");
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                window.dismiss();
                            }
                        });
                        btnRecord.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                window.dismiss();
                                Intent intent = new Intent(getActivity(), BalanceActivity.class);
                                getActivity().startActivity(intent);
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
                        window.setAnimationStyle(R.style.popwin_scale);
                        window.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_index, null), Gravity.CENTER, 0, 0);

                        //显示时间

                        long currentTime = System.currentTimeMillis();
                        System.out.println("系统当前时间" + currentTime);
                        long finalValue = (86400000 + currentTime);
                        buttonRewardTwo.start(finalValue);
                        buttonRewardTwo.setEnabled(false);
                        buttonReward.setVisibility(View.GONE);
                        ll_btn2.setVisibility(View.VISIBLE);
                        buttonThree.setVisibility(View.GONE);

                    } else if (rewardBean.getCode() == -1) {
                        buttonReward.setVisibility(View.GONE);
                        buttonThree.setVisibility(View.GONE);
                        ll_btn2.setVisibility(View.VISIBLE);
                        //开始模拟倒计时24小时,获取当前时间为签到时间
                        buttonRewardTwo.start(86400000);
                        System.out.println("服务器返回领取时间未到");
                    }
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
                //服务器返回错误
                scrollView.setVisibility(View.GONE);
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
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                final String content = data.getStringExtra(Constant.CODED_CONTENT);
                Log.e("szq", "商户二维码返回的url" + content);
                String newContent = content.substring(0, content.length() - 10);
                if (newContent.equals(Contast.QRCODE_URL)) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setMessage("是否绑定该二维码为当前店铺的收款码？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, int which) {
                                    OkHttpUtils.getInstance().getDataAsynFromNet(content + "&sid=" + sid + "&type=app", new OkHttpUtils.MyNetCall() {
                                        @Override
                                        public void success(Call call, Response response) throws IOException {
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ToastUtil.shortToast(getActivity(), "绑定成功");
                                                }
                                            });

                                            dialog.dismiss();

                                        }

                                        @Override
                                        public void failed(Call call, IOException e) {

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

                } else {
                    Toast.makeText(getActivity(), "暂不支持此二维码", Toast.LENGTH_LONG).show();
                }

            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
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
