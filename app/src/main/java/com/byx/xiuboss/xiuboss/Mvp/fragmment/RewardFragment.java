package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.byx.xiuboss.xiuboss.Bean.NewPersonBean;
import com.byx.xiuboss.xiuboss.Bean.RewardBean;
import com.byx.xiuboss.xiuboss.Bean.RewardInfo;
import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.PayCodeActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.TipsActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.WeChatActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.WithDrawActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.RewardAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.Constant;
import com.byx.xiuboss.xiuboss.Utils.RewritePopwindow;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.Utils.ShareUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.google.gson.Gson;
import com.lzy.okhttputils.callback.JsonCallBack;
import com.lzy.okhttputils.model.RequestParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.wechat.Wechat;
import cn.jiguang.share.wechat.WechatMoments;
import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardFragment extends BaseFragment {


    @BindView(R.id.mLeveGap)
    TextView mLeveGap;
    @BindView(R.id.mLeveGapIcon)
    ImageView mLeveGapIcon;
    @BindView(R.id.mGradeMoney)
    TextView mGradeMoney;
    @BindView(R.id.mNextGradeOne)
    TextView mNextGradeOne;
    @BindView(R.id.mNextGradeTwo)
    TextView mNextGradeTwo;
    @BindView(R.id.mCurLeverScore)
    TextView mCurLeverScore;
    @BindView(R.id.mNextLeverScore)
    TextView mNextLeverScore;
    @BindView(R.id.mProgressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.mCurLever)
    TextView mCurLever;
    @BindView(R.id.mNextLever)
    TextView mNextLever;
    @BindView(R.id.mCurScore)
    TextView mCurScore;
    @BindView(R.id.mGapScore)
    TextView mGapScore;
    @BindView(R.id.mNextGrade)
    TextView mNextGrade;
    @BindView(R.id.mReceiveScore)
    TextView mReceiveScore;
    @BindView(R.id.mInvateFriend)
    AutoLinearLayout mInvateFriend;
    @BindView(R.id.mChangeBatch)
    TextView mChangeBatch;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mCheckInTo)
    TextView mCheckInTo;
    @BindView(R.id.mNestedScrollView)
    NestedScrollView mNestedScrollView;
    /*@BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;*/
    @BindView(R.id.mEmptyView)
    LinearLayout mEmptyView;
    @BindView(R.id.gifNewsPerson)
    ImageView gifNewsPerson;
    @BindView(R.id.mTimeLayout)
    LinearLayout mTimeLayout;
    @BindView(R.id.mCountDownTime)
    CountdownView mCountDownTimer;

    Unbinder unbinder;

    private int page = 1;
    private int size = 5;
    private List<RewardInfo.DataBean.ShareTasksBean> mRewardList = new ArrayList<>();
    private RewardAdapter mRewardAdapter;
    private RewritePopwindow popuReceiveGrade;
    private View mPopupView;
    private ImageView mPopupClose;
    private TextView mPopupContent;
    private TextView mPopupMoney;
    private Button mPopupRecord;
    private View mSharePopupView;
    private RewritePopwindow mSharePopupWindow;
    private RelativeLayout mPopupShareWeChat;
    private RelativeLayout mPopupShareFriend;
    private TextView mPopupShareCancel;
    private String popuContent;
    private RewardInfo.DataBean rewardInfo;

    public RewardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reward, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView();
        initData();
        showNewWindow();
        return view;
    }

    private void initView() {

        mLeveGapIcon.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TipsActivity.class);
            startActivity(intent);
        });

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRewardAdapter = new RewardAdapter(mRewardList, getActivity());
        mRecyclerView.setAdapter(mRewardAdapter);

        mRewardAdapter.setOnItemClickListener((type, position, rdst) -> {
            if (type == 0x111) { //item的点击事件

            } else if (type == 0x112) { //点击邀请的事件
                initSharePopupWindow();
            }
        });
        /*smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (page > 1) {
                page = 1;
            }
            initData();
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page++;
            initData();
        });*/
        setOnClick();

    }

    private void setOnClick() {
        mReceiveScore.setOnClickListener(v -> {
            String id = SPUtils.getInstance(getActivity()).getString("id");
            String sid = SPUtils.getInstance(getActivity()).getString(Constant.SID);
            RequestParams requestParams = new RequestParams();
            requestParams.put("sid", sid);
            requestParams.put("clerk_id", id);

            String getMoney = rewardInfo.getReturnCash();
            requestParams.put("money", getMoney);
            receiveReward(requestParams, getMoney);
        });
        mChangeBatch.setOnClickListener(v -> {
            if (page > 30) {
                page = 1;
            }
            page++;
            initData();

        });
    }

    private void initData() {
        ((MainActivity) getActivity()).showDialog();
        ((MainActivity) getActivity()).getEmptyView().setOnClickListener(v -> {
            initData();
        });
        requestCashData();

    }

    public void requestCashData() {

        String sid = SPUtils.getInstance(getActivity()).getString("sid");

        Map<String, String> params = new HashMap<>();
        params.put("source", "android");
        params.put("sid", sid);
        params.put("startPos", String.valueOf(page));
        params.put("step", String.valueOf(size));
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.GETCASH_URL, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                ((MainActivity) getActivity()).cancelDialog();
                /*smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadMore();*/
                RewardInfo info = new Gson().fromJson(json, RewardInfo.class);
                if (info != null && info.getCode() == 2000) {
                    setRewardInfo(info.getData());
                    List<RewardInfo.DataBean.ShareTasksBean> shareTasks = info.getData().getShareTasks();
                    if (shareTasks.size() > 0) {
                        mRewardList.clear();
                        mRewardList.addAll(info.getData().getShareTasks());
                        mRewardAdapter.notifyDataSetChanged();
                    }else{
                        ToastUtil.shortToast(getActivity(),"没有其他商户了");
                    }
                    if (mRewardList.size() == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                    } else {
                        mEmptyView.setVisibility(View.GONE);
                        if (page == 1) {
                            mNestedScrollView.scrollTo(0, 0);
                        }
                    }
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                /*smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadMore();*/
            }
        });

    }

    public void setRewardInfo(RewardInfo.DataBean dataBean) {
        rewardInfo = dataBean;
        if (TextUtils.equals(dataBean.getReciveStatus(), "1")) { //待领取
            mTimeLayout.setVisibility(View.GONE);
            mReceiveScore.setVisibility(View.VISIBLE);
        } else {  //已领取  显示倒计时
            long lastTime = Long.parseLong((TextUtils.isEmpty(dataBean.getNextGetCashTime()) ? "0" : dataBean.getNextGetCashTime()));
            long remainTime = System.currentTimeMillis() - (lastTime * 1000);
            mTimeLayout.setVisibility(View.VISIBLE);
            mReceiveScore.setVisibility(View.GONE);
            long time = (lastTime * 1000 + (24 * 3600 * 1000)) - System.currentTimeMillis();
            mCountDownTimer.start(time);
            mCountDownTimer.setOnCountdownEndListener((cdt) -> {
                initData();
            });
        }

        /*long lastTime = Long.parseLong((TextUtils.isEmpty(dataBean.getNextGetCashTime()) ? "0" : dataBean.getNextGetCashTime()));
        long remainTime = System.currentTimeMillis() - (lastTime * 1000);
        if (remainTime>(24*3600*1000)){
            mTimeLayout.setVisibility(View.GONE);
            mReceiveScore.setVisibility(View.VISIBLE);
        }else{
            mTimeLayout.setVisibility(View.VISIBLE);
            mReceiveScore.setVisibility(View.GONE);
            long time = (lastTime * 1000+(24*3600*1000)) - System.currentTimeMillis();
            mCountDownTimer.start(time);
            mCountDownTimer.setOnCountdownEndListener((cdt)->{
                initData();
            });
        }*/
        //获取进度值 25 50 25
        int curX = Integer.parseInt(dataBean.getCurScore());
        int startX = Integer.parseInt(dataBean.getCreditScore());
        int endX = Integer.parseInt(dataBean.getNextCreditScore());
        int progress = (int) (25 + 47 * ((curX - startX) / ((float) (endX - startX))));

        mProgressBar.setMax(100);
        mProgressBar.setProgress(progress);

        mLeveGap.setText("x" + dataBean.getStepTitle() + " 奖励");

        SpannableStringBuilder retureCash = new SpannableStringBuilder(dataBean.getReturnCash() + "元");
        retureCash.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(18)), retureCash.length() - 1, retureCash.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mGradeMoney.setText(retureCash);

        mNextGradeOne.setText(" x" + dataBean.getNextStepTitle());

        SpannableStringBuilder nextRetureCash = new SpannableStringBuilder(dataBean.getNextReturnCash() + "元");
        nextRetureCash.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(10)), nextRetureCash.length() - 1, nextRetureCash.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mNextGradeTwo.setText(" " + nextRetureCash);

        mCurLeverScore.setText(dataBean.getCreditScore());
        mNextLeverScore.setText(dataBean.getNextCreditScore());

        mCurLever.setText("x" + dataBean.getStepTitle());
        mNextLever.setText("x" + dataBean.getNextStepTitle());
        mCurScore.setText(" " + dataBean.getCurScore());

        mGapScore.setText(" " + (endX - curX) + " "); //还差多少分
        mNextGrade.setText(" x" + dataBean.getNextStepTitle());

        mCheckInTo.setText("入驻即奖励" + dataBean.getShareReturnCash() + "元");

    }

    private void initShowWindow(String money, String content) {

        if (mPopupView == null) {
            mPopupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_success, null, false);
            popuReceiveGrade = new RewritePopwindow(getActivity(), mPopupView);
            mPopupClose = mPopupView.findViewById(R.id.icon_close);
            mPopupContent = mPopupView.findViewById(R.id.textView30);
            mPopupMoney = mPopupView.findViewById(R.id.popup_money);
            mPopupRecord = mPopupView.findViewById(R.id.btn_record);
            popuReceiveGrade.setAnimationStyle(R.style.popwin_scale);
        }
        mPopupContent.setText(content);
        mPopupMoney.setText(money + "元");

        mPopupClose.setOnClickListener(v -> {
            popuReceiveGrade.dismiss();
        });
        mPopupRecord.setOnClickListener(v -> {
            popuReceiveGrade.dismiss();
            Intent intent = new Intent(getActivity(), WithDrawActivity.class);
            getActivity().startActivity(intent);
            initData();
        });
        if (popuReceiveGrade != null) {
            View decorView = getActivity().getWindow().getDecorView();
            popuReceiveGrade.showAtLocation(decorView, Gravity.CENTER, 0, 0);
        }

    }

    private void initSharePopupWindow() {
        if (mSharePopupView == null) {
            mSharePopupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_share, null, false);
            mSharePopupWindow = new RewritePopwindow(getActivity(), mSharePopupView);
            mPopupShareWeChat = mSharePopupView.findViewById(R.id.rl_weChat);
            mPopupShareFriend = mSharePopupView.findViewById(R.id.rl_weFriend);
            mPopupShareCancel = mSharePopupView.findViewById(R.id.cancel);
        }
        mPopupShareWeChat.setOnClickListener(v -> {
            /*Intent intent = new Intent(getActivity(), WeChatActivity.class);
            intent.putExtra("sid", "");
            startActivity(intent);*/
            toShare(Wechat.Name);
        });

        mPopupShareFriend.setOnClickListener(v -> {
            //Toast.makeText(getActivity(), "朋友圈", Toast.LENGTH_LONG).show();
            toShare(WechatMoments.Name);
        });

        mPopupShareCancel.setOnClickListener(v -> {
            if (mSharePopupWindow != null) {
                mSharePopupWindow.dismiss();
            }
        });
        if (mSharePopupView != null) {
            View rootview = getActivity().getWindow().getDecorView();
            mSharePopupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        }

    }

    private void initNewsPerson(String sid, String id) {
        View newPserOnView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_person, null, false);
        RewritePopwindow newsPersonWindow = new RewritePopwindow(getActivity(), newPserOnView);

        TextView tv_giveMoney = newPserOnView.findViewById(R.id.tv_giveMoney);
        TextView firstText = newPserOnView.findViewById(R.id.tv_shareSecondMoney);
        TextView secondText = newPserOnView.findViewById(R.id.tv_shareThirdMoney);
        Button getMoney = newPserOnView.findViewById(R.id.shareButton);
        ImageView ivClose = newPserOnView.findViewById(R.id.close);

        getMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsPersonWindow.dismiss();
                gifNewsPerson.clearAnimation();
                gifNewsPerson.setVisibility(View.GONE);
                RequestParams requestParams = new RequestParams();
                requestParams.put("sid", sid);
                requestParams.put("clerk_id", id);
                requestParams.put("money", 2.06 + "");
                receiveReward(requestParams, "2.06"); //播放声音,显示动画
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsPersonWindow.dismiss();
                gifNewsPerson.clearAnimation();
                gifNewsPerson.setVisibility(View.GONE);
                initData();
            }
        });
        View decorView = getActivity().getWindow().getDecorView();
        newsPersonWindow.showAtLocation(decorView, Gravity.CENTER, 0, 0);
    }

    //显示新人礼包的window
    private void showNewWindow() {
        String sid = SPUtils.getInstance(getActivity()).getString(Constant.SID);
        String id = SPUtils.getInstance(getActivity()).getString("id");
        RequestParams params = new RequestParams();
        params.put("sid", sid);
        params.put("clerk_id", id);
        com.lzy.okhttputils.OkHttpUtils.post(AppUrl.newPerson).params(params).execute(new MyJsonCallBack<NewPersonBean>() {
            @Override
            public void onResponse(NewPersonBean newPersonBean) {
                if (newPersonBean != null && newPersonBean.getCode() == 2000) {
                    System.out.println("新人鼓励金请求成功,已经到账");
                    gifNewsPerson.setVisibility(View.VISIBLE);
                    Animation operatingAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.tips);
                    LinearInterpolator lin = new LinearInterpolator();
                    operatingAnim.setInterpolator(lin);
                    if (operatingAnim != null) {
                        gifNewsPerson.startAnimation(operatingAnim);
                    }
                    initNewsPerson(sid, id);
                }
            }
        });

    }

    public void receiveReward(RequestParams params, String money) {

        com.lzy.okhttputils.OkHttpUtils.post(AppUrl.SIGN_IN_URL).params(params).execute(new JsonCallBack<RewardBean>() {
            @Override
            public void onResponse(RewardBean rewardBean) {
                if (rewardBean != null && rewardBean.getCode() == 2000) {
                    String moneyType = rewardBean.getData().getMoneytype();
                    if (moneyType != null) {
                        if (moneyType.equals("credit")) {
                            //余额到账
                            popuContent = "已经存入您的账户余额";

                        } else if (moneyType.equals("wechat")) {
                            //微信到账
                            popuContent = "已经提现到您的微信账户";
                        }
                        playMedia();
                        initShowWindow(money, popuContent); //显示PopupWindow
                        String type = rewardBean.getData().getType();
                        receiveType(type, rewardBean);
                    }
                }
            }
        });
    }

    private void receiveType(String type, RewardBean rewardBean) {
        //判断用户领取奖励的3种情况
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

                String mobile = SPUtils.getInstance(getActivity()).getString("mobile");
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
                String mobile = SPUtils.getInstance(getActivity()).getString("mobile");
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
            } else if (type.equals("sendnumerror")) {
                //超出次数限制
                String mobile = SPUtils.getInstance(getActivity()).getString("mobile");
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
            } else if (type.equals("publicerror")) {

                String mobile = SPUtils.getInstance(getActivity()).getString("mobile");
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
            }
        }
    }

    private void playMedia() {
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
    }

    private void toShare(String name){

        ShareParams params = new ShareParams();
        params.setShareType(Platform.SHARE_WEBPAGE);
        params.setTitle("休休有钱");
        params.setUrl("http://www.baidu.com");
        params.setComment("我是用来测试的案例");
        ShareUtils.share(name, params, new ShareUtils.OnShareListener() {
            @Override
            public void onShareSuccess() {
                ToastUtil.shortToast(getActivity(),"分享成功");
                if (mSharePopupWindow!=null){
                    mSharePopupWindow.dismiss();
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            page = 1;
            initData();
        }
    }
}
