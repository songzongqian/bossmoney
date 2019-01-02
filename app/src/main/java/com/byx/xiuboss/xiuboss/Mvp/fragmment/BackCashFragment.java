package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashAdapter;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackCashFragment extends BaseFragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mEmptyView)
    AutoRelativeLayout mEmptyView;
    Unbinder unbinder;
    private View mView;
    private int requestType;

    public static BackCashFragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("requestType", position);
        BackCashFragment fragment = new BackCashFragment();
        fragment.setArguments(bundle);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_back_cash, container, false);
        unbinder = ButterKnife.bind(this, mView);
        requestType = getArguments().getInt("requestType");
        initView();
        initData();
        return mView;
    }

    private void initData() {

        if (requestType == 0){ //全部

        }else{//返现

        }
        setBackCashData();
    }

    private void initView() {

    }

    public void setBackCashData(){
        //BackCashAdapter mCashAdapter = new BackCashAdapter(null,getActivity());
        //mRecyclerView.setAdapter(mCashAdapter);
        mEmptyView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
