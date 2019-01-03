package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.RewardBean;
import com.byx.xiuboss.xiuboss.Bean.RewardInfo;
import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.BalanceActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.PayCodeActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.TipsActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.WeChatActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.RewardAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.RewritePopwindow;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.google.gson.Gson;
import com.lzy.okhttputils.model.RequestParams;
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
    Unbinder unbinder;

    private int page = 1;
    private int size = 10;
    private List<RewardInfo.DataBean.ShareTasksBean> mRewardList = new ArrayList<>();
    private RewardAdapter mRewardAdapter;
    private RewritePopwindow popuContent;
    private View mPopupView;
    private ImageView mPopupClose;
    private TextView mPopupContent;
    private TextView mPopupMoney;
    private Button mPopupRecord;
    private View mSharePopupView;
    private RewritePopwindow mSharePopupWindow;
    private RelativeLayout mPopupShareWeChat;
    private RelativeLayout mPopupShareFriend;
    private Button mPopupShareCancel;

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

        mRewardAdapter.setOnItemClickListener((type,position, rdst) -> {
            if (type == 0x111){ //item的点击事件

            }else if (type == 0x112){ //点击邀请的事件
                showShareWindow();
            }
        });
        setOnClick();
    }

    private void setOnClick() {
        mReceiveScore.setOnClickListener(v -> {
            showPopupWindow();
        });
    }

    private void initData() {
        ((MainActivity) getActivity()).showDialog();
        ((MainActivity) getActivity()).getEmptyView().setOnClickListener(v -> {
            initData();
        });
        requestCashData();
        initShowWindow();
        initSharePopupWindow();
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
                RewardInfo info = new Gson().fromJson(json, RewardInfo.class);
                if (info.getCode() == 2000) {
                    setRewardInfo(info.getData());
                    if (page == 1) {
                        mRewardList.clear();
                    }
                    List<RewardInfo.DataBean.ShareTasksBean> shareTasks = info.getData().getShareTasks();
                    if (info.getCode() == 2000) {
                        info.getData();
                        mRewardList.addAll(info.getData().getShareTasks());
                        mRewardAdapter.notifyDataSetChanged();
                    }
                    if (mRewardList.size() == 0) {
                        // mEmptyView.setVisibility(View.VISIBLE);
                    } else {
                        // mEmptyView.setVisibility(View.GONE);
                        mNestedScrollView.scrollTo(0, 0);
                    }
                }
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });

    }

    public void setRewardInfo(RewardInfo.DataBean dataBean) {

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
        mCurScore.setText(" " + dataBean.getCreditScore());

        mGapScore.setText(" " + (endX - curX) + " "); //还差多少分
        mNextGrade.setText(" x" + dataBean.getNextStepTitle());

        mCheckInTo.setText("入驻即奖励" + dataBean.getShareReturnCash() + "元");

    }

    private void initShowWindow() {

        if (mPopupView == null){
            mPopupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_success, null, false);
            popuContent = new RewritePopwindow(getActivity(), mPopupView);
            mPopupClose = mPopupView.findViewById(R.id.icon_close);
            mPopupContent = mPopupView.findViewById(R.id.textView30);
            mPopupMoney = mPopupView.findViewById(R.id.popup_money);
            mPopupRecord = mPopupView.findViewById(R.id.btn_record);
            popuContent.setAnimationStyle(R.style.popwin_scale);
        }
        mPopupClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuContent.dismiss();
            }
        });
        mPopupRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuContent.dismiss();
                Intent intent = new Intent(getActivity(), BalanceActivity.class);
                getActivity().startActivity(intent);
            }
        });


    }
    public void showPopupWindow(){
        if (popuContent!=null){
            View decorView = getActivity().getWindow().getDecorView();
            popuContent.showAtLocation(decorView, Gravity.CENTER, 0, 0);
        }
    }
    public void showShareWindow(){
        if (mSharePopupView != null){
            View rootview = getActivity().getWindow().getDecorView();
            mSharePopupWindow.showAtLocation(rootview,  Gravity.BOTTOM, 0, 0);
        }
    }

    private void initSharePopupWindow() {
        if (mSharePopupView == null){
            mSharePopupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_share, null, false);
            mSharePopupWindow = new RewritePopwindow(getActivity(), mSharePopupView);
            mPopupShareWeChat = mSharePopupView.findViewById(R.id.rl_weChat);
            mPopupShareFriend = mSharePopupView.findViewById(R.id.rl_weFriend);
            mPopupShareCancel = mSharePopupView.findViewById(R.id.cancel);
        }
        mPopupShareWeChat.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(),WeChatActivity.class);
            intent.putExtra("sid","");
            startActivity(intent);
        });

        mPopupShareFriend.setOnClickListener(v -> {
            Toast.makeText(getActivity(),"朋友圈",Toast.LENGTH_LONG).show();
        });

        mPopupShareCancel.setOnClickListener(v -> {
            if (mSharePopupWindow != null) {
                mSharePopupWindow.dismiss();
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
