package com.byx.xiuboss.xiuboss.Mvp.fragmment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Application.JgApplication;
import com.byx.xiuboss.xiuboss.Bean.HomeBean;
import com.byx.xiuboss.xiuboss.Bean.NewPersonBean;
import com.byx.xiuboss.xiuboss.Bean.RewardBean;
import com.byx.xiuboss.xiuboss.Mvp.activity.BalanceActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.ManageActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.PayCodeActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.ReceivablesActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.TipsActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.TodayMoneyActivity;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.NetUtils;
import com.byx.xiuboss.xiuboss.Utils.TTSUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestParams;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.wechat.Wechat;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import okhttp3.Call;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class IndexFragment extends BaseFragment {


    @BindView(R.id.home_title)
    TextView homeTitle;
    @BindView(R.id.tv_moneyTotal)
    TextView tvMoneyTotal;
    @BindView(R.id.tv_moneyTakeOut)
    TextView tvMoneyTakeOut;
    @BindView(R.id.tv_moneyPay)
    TextView tvMoneyPay;
    @BindView(R.id.textView18)
    TextView textView18;
    @BindView(R.id.ll_shouKuan)
    LinearLayout llShouKuan;
    @BindView(R.id.ll_good)
    LinearLayout llGood;
    @BindView(R.id.ll_wallet)
    LinearLayout llWallet;
    @BindView(R.id.ll_receipt_code)
    LinearLayout llReceiptCode;
    @BindView(R.id.Totalamount)
    TextView Totalamount;
    @BindView(R.id.Totalamount_money)
    TextView TotalamountMoney;
    @BindView(R.id.yesterday_money)
    TextView yesterdayMoney;
    @BindView(R.id.relative_Totalamount)
    LinearLayout relativeTotalamount;
    @BindView(R.id.textView29)
    TextView textView29;
    @BindView(R.id.home_icon_tips)
    ImageView homeIconTips;
    @BindView(R.id.rl_tip)
    RelativeLayout rlTip;
    @BindView(R.id.tv_shouKuanLJ)
    TextView tvShouKuanLJ;
    @BindView(R.id.dengji_current)
    TextView dengjiCurrent;
    @BindView(R.id.tv_JiangLiCurrent)
    TextView tvJiangLiCurrent;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.tv_dangQian_give)
    TextView tvDangQianGive;
    @BindView(R.id.ll_currentJL)
    LinearLayout llCurrentJL;
    @BindView(R.id.tv_next_Give)
    TextView tvNextGive;
    @BindView(R.id.ll_nextJL)
    LinearLayout llNextJL;
    @BindView(R.id.min_money)
    TextView minMoney;
    @BindView(R.id.middle_money)
    TextView middleMoney;
    @BindView(R.id.max_money)
    TextView maxMoney;
    @BindView(R.id.progressBarHorizontal)
    ProgressBar progressBarHorizontal;
    @BindView(R.id.first_Level)
    ImageView firstLevel;
    @BindView(R.id.middle_Level)
    ImageView middleLevel;
    @BindView(R.id.last_Level)
    ImageView lastLevel;
    @BindView(R.id.tv_OneLevel)
    TextView tvOneLevel;
    @BindView(R.id.tv_TwoLevel)
    TextView tvTwoLevel;
    @BindView(R.id.tv_ThreeLevel)
    TextView tvThreeLevel;
    @BindView(R.id.tv_xiayi)
    TextView tvXiayi;
    @BindView(R.id.nextLevel)
    TextView nextLevel;
    @BindView(R.id.haicha)
    TextView haicha;
    @BindView(R.id.difference_money)
    TextView differenceMoney;
    @BindView(R.id.tv_jiangli)
    TextView tvJiangli;
    @BindView(R.id.reward_money)
    TextView rewardMoney;
    @BindView(R.id.tv_yuan)
    TextView tvYuan;
    @BindView(R.id.tv_maxLevel)
    TextView tvMaxLevel;
    @BindView(R.id.button_reward_two)
    CountdownView buttonRewardTwo;
    @BindView(R.id.ll_count)
    LinearLayout llCount;
    @BindView(R.id.button_three)
    Button buttonThree;
    @BindView(R.id.textView35)
    TextView textView35;
    @BindView(R.id.rl_tiXian)
    RelativeLayout rlTiXian;
    @BindView(R.id.home_max_money)
    TextView homeMaxMoney;
    @BindView(R.id.progressBarHorizontal_two)
    ProgressBar progressBarHorizontalTwo;
    @BindView(R.id.Service_Charge_money)
    TextView ServiceChargeMoney;
    @BindView(R.id.iv_internet)
    ImageView ivInternet;
    @BindView(R.id.tv_internet)
    TextView tvInternet;
    @BindView(R.id.rl_empty_internet)
    RelativeLayout rlEmptyInternet;
    @BindView(R.id.ll_myUI)
    AutoLinearLayout llMyUI;
    Unbinder unbinder;
    @BindView(R.id.total_Fan)
    TextView totalFan;
    @BindView(R.id.customer_Dai)
    TextView customerDai;
    @BindView(R.id.iv_good)
    ImageView ivGood;
    @BindView(R.id.rl_newPerson)
    RelativeLayout rlNewPerson;
    @BindView(R.id.gif_light)
    ImageView gifLight;
    @BindView(R.id.rl_reward)
    RelativeLayout rlReward;
    private SharedPreferences loginSucess;
    HomeBean middleBean;
    private View inflate;
    private String sid;
    private double progress;
    private int pro;
    private float shoukuanFee;
    private float todayFee;
    private Set<String> tags = new HashSet<>();
    private String id;
    int flag = 0;
    private TTSUtils instance;
    private String popuContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_index, container, false);
        loginSucess = getActivity().getSharedPreferences("login_sucess", MODE_PRIVATE);
        unbinder = ButterKnife.bind(this, inflate);
        initData();
        return this.inflate;
    }

    //重新进入页面加载数据
    @Override
    public void onResume() {
        super.onResume();
        checkVersion();
        initData();
        checkPush();
    }

    //检测版本号
    private void checkVersion() {


    }


    //检测用户是否注册成功了极光推送，再次帮用户注册
    private void checkPush() {
            sid = loginSucess.getString("sid", "");
            tags.add(sid);
            JPushInterface.setTags(getActivity(), 0, tags);
            JPushInterface.resumePush(JgApplication.context);
    }

    private void initData() {
        if (NetUtils.isConnected(getActivity())) {
            //有网络
            rlEmptyInternet.setVisibility(View.GONE);
            llMyUI.setVisibility(View.VISIBLE);
            sid = loginSucess.getString("sid", "");
            id = loginSucess.getString("id", "");
            String title = loginSucess.getString("title", "");
            homeTitle.setText(title);
            RequestParams params = new RequestParams();
            params.put("sid", sid);
            OkHttpUtils.post(AppUrl.HOME_URL).params(params).execute(new MyJsonCallBack<HomeBean>() {

                @Override
                public void onResponse(HomeBean homeBean) {
                    if (homeBean != null && homeBean.getCode() == 2000) {
                        middleBean = homeBean;
                        Log.e("boss", "首页数据请求成功" + homeBean.getCode());
                        //tvMoneyTotal.setText(homeBean.getData().getFee().getOrderTotalFee());
                        //tvMoneyTakeOut.setText(homeBean.getData().getFee().getOrderTotalFee());
                        // tvMoneyPay.setText(homeBean.getData().getFee().getPayBillTotalFee());
                        String payBillFinalFee = homeBean.getData().getFee().getPayBillFinalFee();
                        if(TextUtils.isEmpty(payBillFinalFee)){
                            totalFan.setText("0.00");
                        }else{
                            totalFan.setText(homeBean.getData().getFee().getPayBillFinalFee());
                        }

                        String customer = homeBean.getData().getFee().getCustomer();
                        if(TextUtils.isEmpty(customer)){
                            customerDai.setText("0");

                        }else{
                            customerDai.setText(homeBean.getData().getFee().getCustomer());
                        }



                        Totalamount.setText("今日实际收入共" + homeBean.getData().getTodaySum() + "笔");
                        TotalamountMoney.setText(homeBean.getData().getTodayFee() + "元");
                        yesterdayMoney.setText("昨日实时：" + homeBean.getData().getYesterFee() + "元");

                        HomeBean.DataBean.AmountBean amount = homeBean.getData().getAmount();
                        tvShouKuanLJ.setText(amount.getAmountFee() + "元");

                        homeTitle.setText(loginSucess.getString("homeTitle", ""));
                        Double xyz = Double.parseDouble(amount.getAmount());


                        tvJiangLiCurrent.setText(homeBean.getData().getCurrentGrade().getF4() + "元");
                        //设置倒计时逻辑
                        //获取最新接口获取的签到时间
                        long serverSignTime = new Long(homeBean.getData().getSigntime());
                        long serverTime = serverSignTime * 1000 + 86400000;
                        System.out.println("服务器返回的时间" + serverTime);

                        //获取系统当前时间
                        long sytemTime = System.currentTimeMillis();
                        System.out.println("系统当前时间" + serverTime);

                        //领取时间未到，显示倒计时
                        if (sytemTime < serverTime && xyz > 200) {
                            rlReward.setVisibility(View.GONE);
                            buttonThree.setVisibility(View.GONE);
                            llCount.setVisibility(View.VISIBLE);
                            buttonRewardTwo.setVisibility(View.VISIBLE);
                            long chaValue = serverTime - sytemTime;
                            buttonRewardTwo.start(chaValue);
                        } else if (xyz < 200) {
                            rlReward.setVisibility(View.GONE);
                            llCount.setVisibility(View.GONE);
                            buttonThree.setVisibility(View.VISIBLE);
                            buttonThree.setText("等级不足");
                        } else if (sytemTime >= serverTime && xyz >= 200){
                            //可以重新领取
                            if (flag == 0) {
                                TTSUtils instance = TTSUtils.getInstance();
                                instance.init();
                                instance.speak("您有新的奖励可以领取");
                                flag = 1;
                            } else {

                            }
                            llCount.setVisibility(View.GONE);
                            buttonThree.setVisibility(View.GONE);
                            rlReward.setVisibility(View.VISIBLE);
                        }

                        //设置等级
                        String id = homeBean.getData().getCurrentGrade().getId();//当前级别是0，id为空
                        if (id == null) {
                            //等级为0时
                            minMoney.setText("0");
                            middleMoney.setText("200");
                            maxMoney.setText("500");
                            dengjiCurrent.setText("Lv" + "0");
                            tvOneLevel.setText("Lv" + "0");
                            tvTwoLevel.setText("Lv" + "1");
                            tvThreeLevel.setText("Lv" + "2");
                            tvDangQianGive.setText(homeBean.getData().getNextGrade().getF4() + "元");//设置V1级别的奖励
                            firstLevel.setBackgroundResource(R.mipmap.index_new_current);
                            middleLevel.setBackgroundResource(R.mipmap.index_icon_next);
                            lastLevel.setBackgroundResource(R.mipmap.index_icon_next);
                            llNextJL.setVisibility(View.GONE);

                            double perScore = Double.parseDouble("0");//上一等级需要的金额
                            int perMax = (int) perScore;
                            System.out.println("perMax的值" + perMax);

                            double currentScore = Double.parseDouble("200");//当前等级需要的金额
                            int currentMax = (int) currentScore;
                            System.out.println("currentMax的值" + currentMax);


                            double nextScore = Double.parseDouble("500");//下一等级需要的金额
                            int nextMax = (int) nextScore;
                            System.out.println("nextMax的值" + nextMax);

                            String currentAmount = middleBean.getData().getAmount().getAmount();//当前收款余额累计
                            double currentI2 = Double.parseDouble(currentAmount);//当前收款余额累计

                            if (currentI2 <= currentScore) {
                                progress = (currentI2 - perScore) * ((nextScore - currentScore) / (currentScore - perScore)) / ((nextScore - currentScore) * 2) * 100;
                                pro = (int) progress;
                            } else {
                                progress = ((nextScore - currentScore) + (currentI2 - currentScore)) / ((nextScore - currentScore) * 2) * 100;
                                pro = (int) progress;
                            }
                            progressBarHorizontal.setProgress(pro);

                        } else {
                            dengjiCurrent.setText("Lv" + homeBean.getData().getCurrentGrade().getId());
                            if (id.equals("22")) {
                                //22级
                                firstLevel.setBackgroundResource(R.mipmap.index_icon_finish);
                                middleLevel.setBackgroundResource(R.mipmap.index_new_current);
                                lastLevel.setBackgroundResource(R.mipmap.index_icon_next);
                                tvDangQianGive.setText(homeBean.getData().getCurrentGrade().getF4() + "元");//当前级别奖励

                                tvOneLevel.setText("Lv" + homeBean.getData().getPrevGrade().getId());
                                tvTwoLevel.setText("Lv" + homeBean.getData().getCurrentGrade().getId());
                                tvThreeLevel.setText("该等级暂未开放");
                                minMoney.setText(homeBean.getData().getPrevGrade().getF2() + "");
                                middleMoney.setText(homeBean.getData().getCurrentGrade().getF2() + "");
                                maxMoney.setText("");
                                //不显示23级
                                llNextJL.setVisibility(View.GONE);
                                maxMoney.setText("");

                                //设置22级等级进度条
                                progressBarHorizontal.setProgress(50);

                            } else {
                                //1-21级
                                System.out.println("进入1-21级的范围");
                                firstLevel.setBackgroundResource(R.mipmap.index_icon_finish);
                                middleLevel.setBackgroundResource(R.mipmap.index_new_current);
                                lastLevel.setBackgroundResource(R.mipmap.index_icon_next);
                                minMoney.setText(homeBean.getData().getPrevGrade().getF2() + "");
                                middleMoney.setText(homeBean.getData().getCurrentGrade().getF2() + "");
                                maxMoney.setText(homeBean.getData().getNextGrade().getF2() + "");
                                llCurrentJL.setVisibility(View.VISIBLE);
                                llNextJL.setVisibility(View.VISIBLE);
                                tvDangQianGive.setText(homeBean.getData().getCurrentGrade().getF4() + "元");//当前级别奖励
                                tvNextGive.setText(homeBean.getData().getNextGrade().getF4());//下个等级奖励
                                tvOneLevel.setText("Lv" + homeBean.getData().getPrevGrade().getId());
                                tvTwoLevel.setText("Lv" + homeBean.getData().getCurrentGrade().getId());
                                tvThreeLevel.setText("Lv" + homeBean.getData().getNextGrade().getId());


                                double perScore = Double.parseDouble(homeBean.getData().getPrevGrade().getF2());//上一等级需要的金额
                                int perMax = (int) perScore;
                                System.out.println("perMax的值" + perMax);

                                double currentScore = Double.parseDouble(homeBean.getData().getCurrentGrade().getF2());//当前等级需要的金额
                                int currentMax = (int) currentScore;
                                System.out.println("currentMax的值" + currentMax);


                                double nextScore = Double.parseDouble(homeBean.getData().getNextGrade().getF2());//下一等级需要的金额
                                int nextMax = (int) nextScore;
                                System.out.println("nextMax的值" + nextMax);

                                String currentAmount = middleBean.getData().getAmount().getAmount();//当前收款余额累计
                                double currentI2 = Double.parseDouble(currentAmount);//当前收款余额累计

                                if (currentI2 <= currentScore) {
                                    progress = (currentI2 - perScore) * ((nextScore - currentScore) / (currentScore - perScore)) / ((nextScore - currentScore) * 2) * 100;
                                    pro = (int) progress;
                                } else {
                                    progress = ((nextScore - currentScore) + (currentI2 - currentScore)) / ((nextScore - currentScore) * 2) * 100;
                                    pro = (int) progress;
                                }
                                progressBarHorizontal.setProgress(pro);
                            }
                        }


                        if ("22".equals(id)) {
                            tvXiayi.setVisibility(View.GONE);
                            nextLevel.setVisibility(View.GONE);
                            haicha.setVisibility(View.GONE);
                            differenceMoney.setVisibility(View.GONE);
                            tvJiangli.setVisibility(View.GONE);
                            rewardMoney.setVisibility(View.GONE);
                            tvYuan.setVisibility(View.GONE);
                            tvMaxLevel.setVisibility(View.VISIBLE);
                        } else {
                            tvXiayi.setVisibility(View.VISIBLE);
                            nextLevel.setVisibility(View.VISIBLE);
                            haicha.setVisibility(View.VISIBLE);
                            differenceMoney.setVisibility(View.VISIBLE);
                            tvJiangli.setVisibility(View.VISIBLE);
                            rewardMoney.setVisibility(View.VISIBLE);
                            tvYuan.setVisibility(View.VISIBLE);
                            tvMaxLevel.setVisibility(View.GONE);
                            nextLevel.setText("Lv" + homeBean.getData().getNextGrade().getId());
                            differenceMoney.setText(homeBean.getData().getNextDifference());
                            rewardMoney.setText(homeBean.getData().getNextGrade().getF4());
                        }


                        String pop_up_status = homeBean.getData().getPop_up_status();
                        String pop_up_text = homeBean.getData().getPop_up_text();
                        String note = homeBean.getData().getPop_up_settles();
                        String versionNumber = loginSucess.getString("versionName", "");
                        if(pop_up_status!=null){
                            if (pop_up_status.equals("1")) {
                                //弹窗
                                if (pop_up_text.equals(versionNumber)) {
                                    //版本号一致

                                } else {
                                    //版本号不一致
                                    loginSucess.edit().putString("versionName", pop_up_text).commit();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setTitle("休休小提示")
                                            .setMessage(note)
                                            .setCancelable(false)
                                            .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }

                            } else if (pop_up_status.equals("2")) {

                            }

                        }else{

                        }



                        //新手奖励判断
                        String storeBonus = homeBean.getData().getStoreBonus();
                        if(storeBonus!=null){
                            if (storeBonus.equals("1")) {
                                //商户是新商家
                                rlNewPerson.setVisibility(View.VISIBLE);
                                //如果是新手，就写新手奖励高斯模糊
                                rlNewPerson.setAlpha(0.58f);
                                //imageView做动画
                                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.newscale);
                                ivGood.startAnimation(animation);//开始动画
                            } else {
                                rlNewPerson.setVisibility(View.GONE);
                            }

                        }else{

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
                    }

                }

                @Override
                public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                    super.onError(call, response, e);
                    rlEmptyInternet.setVisibility(View.VISIBLE);
                    llMyUI.setVisibility(View.GONE);
                    tvInternet.setText("服务器错误，请稍后重试");
                    rlEmptyInternet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initData();
                        }
                    });
                }
            });

        } else {
            //无网络
            rlEmptyInternet.setVisibility(View.VISIBLE);
            llMyUI.setVisibility(View.GONE);
            tvInternet.setText("咦？怎么木有网了呢~");
            rlEmptyInternet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initData();
                }
            });
        }
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


    /**
     * 设置进度条比例
     */

    private int gteProgressBar() {
        String max = middleBean.getData().getNextGrade().getF2();
        String min = middleBean.getData().getPrevGrade().getF2();
        String amount = middleBean.getData().getAmount().getAmount();//当前收款余额累计
        double i2 = Double.parseDouble(amount);//当前收款余额累计
        int i = Integer.parseInt(max); //最大值
        int i1 = Integer.parseInt(min); //最小值
        double i3 = (i2 - i1) / (i - i1);
        Log.e("-i2--", i2 + "");
        Log.e("-i--", i + "");
        Log.e("-i1--", i1 + "");
        Log.e("i3", i3 + "");
        float v = (float) i3 * 100f;
        return (int) v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_shouKuan, R.id.ll_good, R.id.ll_wallet, R.id.ll_receipt_code, R.id.relative_Totalamount, R.id.rl_tip, R.id.rl_reward, R.id.rl_tiXian, R.id.iv_good})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shouKuan:
                //收款页面
                Intent intent = new Intent(getActivity(), ReceivablesActivity.class);
                intent.putExtra("sidNumber", sid);
                startActivity(intent);
                break;
            case R.id.ll_good:
                //商品
                Intent intent1 = new Intent(getActivity(), ManageActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_wallet:
                //钱包
                //Intent intent2 = new Intent(getActivity(), BalanceActivity.class);
                //startActivity(intent2);
                //测试分享页面
                //showSharePopuWindow();
                if (instance == null) {
                    instance = TTSUtils.getInstance();
                    instance.init();
                    instance.speak("您有新的奖励可以领取");
                } else {

                    instance.speak("您有新的奖励可以领取");
                }


                break;
            case R.id.ll_receipt_code:
                //收款码
                Intent intent3 = new Intent(getActivity(), PayCodeActivity.class);
                intent3.putExtra("sid", sid);
                startActivity(intent3);
                break;
            case R.id.relative_Totalamount:
                //今日收款详情
                Intent intent4 = new Intent(getActivity(), TodayMoneyActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_tip:
                //等级奖励提示
                Intent intent5 = new Intent(getActivity(), TipsActivity.class);
                startActivity(intent5);
                break;
            case R.id.rl_reward:
                //领取奖励
                if (NetUtils.isConnected(getActivity())) {
                    String id = loginSucess.getString("id", "");
                    RequestParams requestParams = new RequestParams();
                    requestParams.put("sid", sid);
                    requestParams.put("clerk_id", id);
                    requestParams.put("money", middleBean.getData().getCurrentGrade().getF4());
                    String getMoney=middleBean.getData().getCurrentGrade().getF4();
                    showOldWindow(requestParams,getMoney); //播放声音,显示动画
                } else {
                    llMyUI.setVisibility(View.GONE);
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
            case R.id.rl_tiXian:
                //提现手续费提示
                break;

            //点击新人领取礼包
            case R.id.iv_good:
                //1、去掉蒙版模糊
                rlNewPerson.setVisibility(View.GONE);
                ivGood.clearAnimation();
                //做动画显示popuWindow
                showNewWindow();
                break;
        }
    }


    //显示旧版本的签到逻辑
    private void showOldWindow(RequestParams params,String money){
        /**
         * 追加领取奖励来源，暂定字段名 app : android
         */
        OkHttpUtils.post(AppUrl.SIGN_IN_URL).params(params).execute(new MyJsonCallBack<RewardBean>() {
            @Override
            public void onResponse(RewardBean rewardBean) {
                if (rewardBean != null && rewardBean.getCode()==2000) {
                    String moneyType = rewardBean.getData().getMoneytype();
                    if(moneyType!=null){
                        if(moneyType.equals("credit")){
                            //余额到账
                            popuContent = "已经存入您的账户余额";

                        }else if(moneyType.equals("wechat")){
                            //微信到账
                            popuContent = "已经提现到您的微信账户";

                        }
                    }
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
                        PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                        ImageView close = inflate.findViewById(R.id.icon_close);
                        TextView tvContent= inflate.findViewById(R.id.textView30);
                        TextView popupMoney = inflate.findViewById(R.id.popup_money);
                        Button btnRecord = inflate.findViewById(R.id.btn_record);
                        popupMoney.setText(money+ "元");
                        tvContent.setText(popuContent);
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


                        //判断用户领取奖励的3种情况
                          String type = rewardBean.getData().getType();
                       // String type = "exceed";
                        if (type != null) {
                            System.out.println("返回的数据类型" + type);
                            if (type.equals("exceed")) {

                                //提现已经超过20000
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("微信提现额度已用完")
                                        .setMessage(rewardBean.getMessage())
                                        .setCancelable(false)
                                        .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert = builder.create();
                                alert.show();
                            } else if (type.equals("binding")) {
                                //用户未绑定微信

                                String mobile = loginSucess.getString("mobile", "");
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("您尚未绑定微信")
                                        .setMessage(rewardBean.getMessage())
                                        .setCancelable(false)
                                        .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).setNegativeButton("联系商务经理", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL);
                                        Uri data = Uri.parse("tel:" + mobile);
                                        intent.setData(data);
                                        startActivity(intent);
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            } else if (type.equals("errormessage")) {
                                //微信姓名不对称
                                String mobile = loginSucess.getString("mobile", "");
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("您绑定微信的真实姓名与微信账户信息不符")
                                        .setMessage(rewardBean.getMessage())
                                        .setCancelable(false)
                                        .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).setNegativeButton("联系商务经理", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL);
                                        Uri data = Uri.parse("tel:" + mobile);
                                        intent.setData(data);
                                        startActivity(intent);

                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }else if(type.equals("sendnumerror")){
                                //超出次数限制
                                String mobile = loginSucess.getString("mobile", "");
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("提示")
                                        .setMessage(rewardBean.getMessage())
                                        .setCancelable(false)
                                        .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).setNegativeButton("联系商务经理", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL);
                                        Uri data = Uri.parse("tel:" + mobile);
                                        intent.setData(data);
                                        startActivity(intent);

                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }else if(type.equals("publicerror")){

                                String mobile = loginSucess.getString("mobile", "");
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("提示")
                                        .setMessage(rewardBean.getMessage())
                                        .setCancelable(false)
                                        .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).setNegativeButton("联系商务经理", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(Intent.ACTION_DIAL);
                                        Uri data = Uri.parse("tel:" + mobile);
                                        intent.setData(data);
                                        startActivity(intent);

                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }else{

                            }
                        }
                        //显示时间
                        buttonRewardTwo.start(86400000);
                        buttonRewardTwo.setEnabled(false);
                        rlReward.setVisibility(View.GONE);
                        llCount.setVisibility(View.VISIBLE);
                        buttonThree.setVisibility(View.GONE);
                    } else if (rewardBean.getMessage().equals("领取时间未到")) {
                        rlReward.setVisibility(View.GONE);
                        buttonThree.setVisibility(View.GONE);
                        llCount.setVisibility(View.VISIBLE);
                        //开始模拟倒计时24小时,获取当前时间为签到时间
                        buttonRewardTwo.start(86400000);
                        System.out.println("服务器返回领取时间未到");
                    }
                }



            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
                //服务器返回错误
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

    }


    /**
     * 显示带有分享页面的签到窗口
     */
    private void getPopupData() {
        String id = loginSucess.getString("id", "");
        RequestParams requestParams = new RequestParams();
        requestParams.put("sid", sid);
        requestParams.put("clerk_id", id);
        requestParams.put("money", middleBean.getData().getCurrentGrade().getF4() + "");
        OkHttpUtils.post(AppUrl.SIGN_IN_URL).params(requestParams).execute(new MyJsonCallBack<RewardBean>() {

            @Override
            public void onResponse(RewardBean rewardBean) {
                if (rewardBean != null && rewardBean.getCode() == 2000) {
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

                    View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popup_index_share, null, false);
                    PopupWindow window = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    RelativeLayout rlClose = inflate.findViewById(R.id.rl_closeWindow);
                    TextView giveMoney = inflate.findViewById(R.id.tv_giveMoney);
                    TextView shareMoney = inflate.findViewById(R.id.tv_shareSecondMoney);
                    TextView joinMoney = inflate.findViewById(R.id.tv_shareThirdMoney);
                    Button shareButton = inflate.findViewById(R.id.shareButton);
                    giveMoney.setText(middleBean.getData().getCurrentGrade().getF4() + "元");
                    rlClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            window.dismiss();

                        }
                    });

                    //分享页面
                    shareButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            window.dismiss();
                            showSharePopuWindow();
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
                    rlReward.setVisibility(View.GONE);
                    llCount.setVisibility(View.VISIBLE);
                    buttonThree.setVisibility(View.GONE);

                } else {
                    //不是2000的返回值
                }
            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });
    }


    //显示分享选项
    private void showSharePopuWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_share, null, false);
        PopupWindow shareWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        RelativeLayout weChat = contentView.findViewById(R.id.rl_weChat);
        RelativeLayout weFriend = contentView.findViewById(R.id.rl_weFriend);

        weChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "微信", Toast.LENGTH_LONG).show();
                ShareParams shareParams = new ShareParams();
                shareParams.setShareType(Platform.SHARE_TEXT);
                shareParams.setText("快来休休团购注册会员吧");
                JShareInterface.share(Wechat.Name, shareParams, new PlatActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        System.out.println("微信分享成功");


                    }

                    @Override
                    public void onError(Platform platform, int i, int i1, Throwable throwable) {
                        System.out.println("微信分享失败");

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        System.out.println("微信分享取消");
                    }
                });

            }
        });

        weFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "朋友圈", Toast.LENGTH_LONG).show();
            }
        });

        Button cancel = contentView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shareWindow != null) {
                    shareWindow.dismiss();
                }
            }
        });

        backgroundAlpha(0.5f);
        shareWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        shareWindow.setBackgroundDrawable(new BitmapDrawable());
        shareWindow.setOutsideTouchable(true);
        shareWindow.setTouchable(true);
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_index, null);
        shareWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    //设置窗口背景
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    //显示新人礼包的window
    private void showNewWindow() {
        RequestParams params = new RequestParams();
        params.put("sid", sid);
        params.put("clerk_id", id);
        OkHttpUtils.post(AppUrl.newPerson).params(params).execute(new MyJsonCallBack<NewPersonBean>() {

            @Override
            public void onResponse(NewPersonBean newPersonBean) {
                if (newPersonBean != null && newPersonBean.getCode() == 2000) {
                    System.out.println("新人鼓励金请求成功,已经到账");
                    gifLight.setVisibility(View.VISIBLE);
                    Animation operatingAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.tips);
                    LinearInterpolator lin = new LinearInterpolator();
                    operatingAnim.setInterpolator(lin);
                    if (operatingAnim != null) {
                        gifLight.startAnimation(operatingAnim);
                    }

                    View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_person, null, false);
                    PopupWindow shareWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                    TextView tv_giveMoney = contentView.findViewById(R.id.tv_giveMoney);
                    TextView firstText = contentView.findViewById(R.id.tv_shareSecondMoney);
                    TextView secondText = contentView.findViewById(R.id.tv_shareThirdMoney);
                    Button getMoney = contentView.findViewById(R.id.shareButton);
                    ImageView ivClose = contentView.findViewById(R.id.close);

                    getMoney.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            shareWindow.dismiss();
                            gifLight.clearAnimation();
                            gifLight.setVisibility(View.GONE);
                            String id = loginSucess.getString("id", "");
                            RequestParams requestParams = new RequestParams();
                            requestParams.put("sid", sid);
                            requestParams.put("clerk_id", id);
                            requestParams.put("money", 2.06+"");
                            showOldWindow(requestParams,"2.06"); //播放声音,显示动画
                        }
                    });


                    ivClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            shareWindow.dismiss();
                            gifLight.clearAnimation();
                            gifLight.setVisibility(View.GONE);
                            initData();
                        }
                    });


                    shareWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            backgroundAlpha(1.0f);
                            gifLight.clearAnimation();
                            gifLight.setVisibility(View.GONE);
                        }
                    });
                    backgroundAlpha(0.5f);
                    shareWindow.setBackgroundDrawable(new BitmapDrawable());
                    shareWindow.setOutsideTouchable(true);
                    shareWindow.setTouchable(true);
                    shareWindow.showAtLocation(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_index, null), Gravity.CENTER, 0, 0);
                }


            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });

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


    @OnClick(R.id.rl_reward)
    public void onViewClicked() {
    }
}
