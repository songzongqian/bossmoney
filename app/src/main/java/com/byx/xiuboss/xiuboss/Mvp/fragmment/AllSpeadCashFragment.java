package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashAdapter;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllSpeadCashFragment extends BaseFragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mEmptyView)
    AutoRelativeLayout mEmptyView;
    Unbinder unbinder;
    private View mView;
    private List<StoreInfo.DataBean.OrderListBean>mCashList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_back_cash, container, false);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        initData();
        return mView;
    }

    private void initData() {
        setBackCashData();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        BackCashAdapter mCashAdapter = new BackCashAdapter(mCashList,getActivity());
        mRecyclerView.setAdapter(mCashAdapter);

    }

    public void setBackCashData(){
        mEmptyView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setSpeadCashList(List<StoreInfo.DataBean.OrderListBean> orderList) {
        mCashList.clear();
        mCashList.addAll(orderList);
    }
}
