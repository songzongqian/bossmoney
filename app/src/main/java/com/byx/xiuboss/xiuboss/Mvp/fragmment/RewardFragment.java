package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.RewardInfo;
import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.Mvp.activity.TipsActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.RewardAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.google.gson.Gson;
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
    Unbinder unbinder;

    private int page;
    private int size;
    private List<RewardInfo.DataBean.ShareTasksBean>mRewardList = new ArrayList<>();
    private RewardAdapter mRewardAdapter;

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

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRewardAdapter = new RewardAdapter(mRewardList,getActivity());
        mRecyclerView.setAdapter(mRewardAdapter);

        mRewardAdapter.setOnItemClickListener((position, rdst) -> {

        });
    }

    private void initData() {
        requestCashData();
    }

    public void requestCashData(){

        String sid = SPUtils.getInstance(getActivity()).getString("sid");

        Map<String,String> params = new HashMap<>();
        params.put("source","android");
        params.put("sid",sid);
        params.put("startPos",String.valueOf(page));
        params.put("step",String.valueOf(size));
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.GETCASH_URL, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                RewardInfo info = new Gson().fromJson(json, RewardInfo.class);
                if (page == 1){
                    mRewardList.clear();
                }
                List<RewardInfo.DataBean.ShareTasksBean> shareTasks = info.getData().getShareTasks();
                if (info.getCode() == 2000){
                    info.getData();
                    mRewardList.addAll(info.getData().getShareTasks());
                    mRewardAdapter.notifyDataSetChanged();
                }
                if (mRewardList.size() == 0){
                   // mEmptyView.setVisibility(View.VISIBLE);
                }else{
                   // mEmptyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });

    }
    public void setRewardInfo(RewardInfo.DataBean dataBean){

        mLeveGap.setText(dataBean.getStepTitle());
        mGradeMoney.setText(dataBean.getReturnCash());
        mNextGradeOne.setText(dataBean.getNextStepTitle());
        mNextGradeTwo.setText(dataBean.getNextReturnCash());
        mCurLeverScore.setText(dataBean.getCreditScore());
        mNextLeverScore.setText(dataBean.getNextCreditScore());
        mCurLever.setText(dataBean.getStepTitle());
        mNextLever.setText(dataBean.getNextStepTitle());
        mCurScore.setText(dataBean.getCreditScore());
        mGapScore.setText("0"); //还差多少分
        mNextGrade.setText(dataBean.getNextStepTitle());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
