package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.OrderBean;
import com.byx.xiuboss.xiuboss.Mvp.activity.OrderActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.ExpandListAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * 已完成
 */
@SuppressLint("ValidFragment")
public class OrderDetialFragment extends OrderBaseFragment {


    private int position;
    private String title;
    private int start =0;
    private ArrayList<OrderBean.DataBeanX> mList = new ArrayList<>();


    @BindView(R.id.detial_orderMore)
    AutoLinearLayout mOrderMore;

    ExpandableListView mExpandableListView;

    private RelativeLayout extract_Empty;
    private ExpandListAdapter listAdapter;
    private boolean isRefreshData = true;
    SmartRefreshLayout refreshLayout;

    public static OrderDetialFragment getOrderFragment(int position, String title) {
        OrderDetialFragment fragment = new OrderDetialFragment();
        Bundle bundle = new Bundle(position);
        bundle.putInt("position", position);
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
            title = bundle.getString("title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_order_detial, container, false);
        ButterKnife.bind(mView);


        mExpandableListView = mView.findViewById(R.id.expand_ListView);
        extract_Empty = mView.findViewById(R.id.extract_Empty);
        refreshLayout = mView.findViewById(R.id.smartRefreshLayout);
        initView();
        isPrepared = true;
        whetherLazyLoad();
        return mView;
    }

    private void initView() {
        listAdapter = new ExpandListAdapter(getActivity(), mList);
        mExpandableListView.setAdapter(listAdapter);
        extract_Empty.setVisibility(View.VISIBLE);
        mExpandableListView.setVisibility(View.GONE);

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                isRefreshData = false;
                start+=5;
                initData(5,5);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isRefreshData = true;
                start = 0;
                initData(5,5);
                refreshLayout.finishRefresh();
            }
        });
        setOnitemClickListener();
    }

    private void setOnitemClickListener() {

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                OrderBean.DataBeanX.DataBean dataBean = mList.get(groupPosition).getData().get(childPosition);
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("id",dataBean.getId());
                startActivity(intent);
                return true;
            }
        });

    }

    private void initData(int status, int delivever_staus) {

        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        /*params.put("sid", "111");*/
        params.put("status", String.valueOf(status));
        params.put("deliveryer_status", String.valueOf(delivever_staus));
        params.put("start", String.valueOf(start));
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.ORDER_GROUP, params, new OkHttpUtils.UserNetCall() {

            @Override
            public void success(Call call, String json) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                Gson gson = new Gson();
                OrderBean orderBean = gson.fromJson(json, OrderBean.class);


                if (orderBean != null && orderBean.getData() != null) {
                    if (isRefreshData){
                        mList.clear();
                    }
                    mList.addAll(orderBean.getData());
                    listAdapter.notifyDataSetChanged();
                    if (mList.size()>0){
                        setEmptyIsHind(true);
                    }else{
                        setEmptyIsHind(false);
                    }
                    /*extract_Empty.setVisibility(View.GONE);
                    mExpandableListView.setVisibility(View.VISIBLE);*/
                } else {
                    if (start==0){
                       /* extract_Empty.setVisibility(View.VISIBLE);
                        mExpandableListView.setVisibility(View.GONE);*/
                        setEmptyIsHind(false);
                    }

                }

            }

            @Override
            public void failed(Call call, IOException e) {
                Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
                setEmptyIsHind(false);
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }
        });
    }

    public void setEmptyIsHind(boolean isHind) {
        if (isHind) {
            extract_Empty.setVisibility(View.GONE);
            mExpandableListView.setVisibility(View.VISIBLE);

        } else {
            extract_Empty.setVisibility(View.VISIBLE);
            mExpandableListView.setVisibility(View.GONE);
        }

    }

    @Override
    public void lazyLoad() {
        isRefreshData = true;
        initData(5, 5);
    }

}

