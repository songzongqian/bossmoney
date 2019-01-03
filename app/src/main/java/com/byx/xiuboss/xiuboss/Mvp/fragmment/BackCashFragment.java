package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.Mvp.activity.CollRecordeActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class BackCashFragment extends BaseFragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mEmptyView)
    AutoRelativeLayout mEmptyView;
    Unbinder unbinder;
    private View mView;

    private int page = 1;
    private int size = 10;
    private List<StoreInfo.DataBean.OrderListBean> mCashList = new ArrayList<>();
    private BackCashAdapter mCashAdapter;


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

        requestCashData();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mCashAdapter = new BackCashAdapter(mCashList,getActivity());
        mRecyclerView.setAdapter(mCashAdapter);

        mCashAdapter.setOnItemClickListener((position, sorb) -> {
            Intent intent = new Intent(getActivity(), CollRecordeActivity.class);
            startActivity(intent);
        });
    }

    public void requestCashData(){

        String sid = SPUtils.getInstance(getActivity()).getString("sid");
        Map<String,String> params = new HashMap<>();
        params.put("source","android");
        params.put("sid",sid);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date(System.currentTimeMillis()));
        params.put("date",date);
        params.put("orderType","2");
        params.put("startPos",String.valueOf(page));
        params.put("step",String.valueOf(size));
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.INDEXDATA_URL, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                StoreInfo info = new Gson().fromJson(json, StoreInfo.class);
                if (page == 1){
                    mCashList.clear();
                }

                if (info.getCode() == 2000){
                    mCashList.addAll(info.getData().getOrderList());
                    mCashAdapter.notifyDataSetChanged();
                }
                if (mCashList.size() == 0){
                    mEmptyView.setVisibility(View.VISIBLE);
                }else{
                    mEmptyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
