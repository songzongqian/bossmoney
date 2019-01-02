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

import com.byx.xiuboss.xiuboss.Mvp.activity.TipsActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.RewardAdapter;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
        //RewardAdapter mRewardAdapter = new RewardAdapter(null,getActivity());
        //mRecyclerView.setAdapter(mRewardAdapter);
    }

    private void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
