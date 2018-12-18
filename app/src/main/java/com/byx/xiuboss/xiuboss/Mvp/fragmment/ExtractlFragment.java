package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.ConfimOrderBean;
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.Bean.ExtractBean;
import com.byx.xiuboss.xiuboss.Bean.OrderBean;
import com.byx.xiuboss.xiuboss.Bean.ReCoinBean;
import com.byx.xiuboss.xiuboss.Bean.ResumeBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.Mvp.activity.OrderActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.DealAdapter;
import com.byx.xiuboss.xiuboss.Mvp.adapter.ExtractAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.RewritePopwindow;
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

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * 待自提
 */
@SuppressLint("ValidFragment")
public class ExtractlFragment extends OrderBaseFragment {

    private int position;
    private String title;
    private View mView;
    private int start = 0;
    private boolean isRefreshData = true;

    RecyclerView mRecyclerView;
    SmartRefreshLayout refreshLayout;
    RelativeLayout empty;

    private LayoutInflater inflater;
    private ArrayList<ExtractBean.DataBean> mList = new ArrayList<>();
    private ExtractAdapter mAdapter;

    public static ExtractlFragment getExtractFragment(int position, String title) {
        ExtractlFragment fragment = new ExtractlFragment();
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
        mView = inflater.inflate(R.layout.fragment_extract, container, false);
        this.inflater = LayoutInflater.from(getActivity());

        initView();
        isPrepared = true;
        whetherLazyLoad();
        return mView;
    }

    private void initView() {
        mRecyclerView = mView.findViewById(R.id.recoin_recycler);
        refreshLayout = mView.findViewById(R.id.smartRefreshLayout);
        empty = mView.findViewById(R.id.empty);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        mAdapter = new ExtractAdapter(mList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        setEmptyIsHind(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                isRefreshData = false;
                start += 20;
                initData(1, 0);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isRefreshData = true;
                start = 0;
                initData(1,0);
                refreshLayout.finishRefresh();
            }
        });
        setOnItemClick();
    }

    private void setOnItemClick() {
        mAdapter.setOnItemClickListener(new ExtractAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ExtractBean.DataBean categorysBean) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("id",categorysBean.getId());
                startActivity(intent);
            }

            @Override
            public void onConfimClick(int position, ExtractBean.DataBean categorysBean) {
                showPopup(categorysBean);
            }
        });
    }

    /*弹出PopupWindow*/
    private void showPopup(final ExtractBean.DataBean categorysBean){
        View pView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_extractorder_item,null);
        final RewritePopwindow rPop = new RewritePopwindow(getActivity(),pView);
        TextView cancelView = pView.findViewById(R.id.order_cancel);
        TextView confimView = pView.findViewById(R.id.order_confim);

        rPop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER,0,0);

        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rPop.dismiss();
            }
        });
        confimView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rPop.dismiss();
               confimOrder(categorysBean.getId());
            }
        });

    }

    private void initData(final int status, int delivever_staus) {

        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        /*params.put("status", String.valueOf(status));
        params.put("deliveryer_status", String.valueOf(delivever_staus));*/
        params.put("start", String.valueOf(start));
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.ORDER_MEGAVE, params, new OkHttpUtils.UserNetCall() {

                @Override
                public void success(Call call, String json) {
                    Gson gson = new Gson();
                    ExtractBean reCoinBean = gson.fromJson(json, ExtractBean.class);
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadMore();

                    if (reCoinBean != null && reCoinBean.getData() != null) {
                        if (isRefreshData){
                            mList.clear();
                        }
                        mList.addAll(reCoinBean.getData());
                        mAdapter.notifyDataSetChanged();
                        if (mList.size()>0){
                            setEmptyIsHind(true);
                        }else{
                            setEmptyIsHind(false);
                        }
                    } else {
                        if (start==0){
                            setEmptyIsHind(false);
                        }
                    }
                }

                @Override
                public void failed(Call call, IOException e) {
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadMore();
                    setEmptyIsHind(false);
                    Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
                }
            });
    }

    /*请求确认订单*/
    public void confimOrder(String id){
        //Toast.makeText(getActivity(),"你点击了确认订单按钮",Toast.LENGTH_SHORT).show();

        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");

        Map<String,String>params = new HashMap<>();
        params.put("status","4");
        params.put("delivery_status","4");
        params.put("id",id);
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.ORDER_COMFIM, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                ResumeBean bean = gson.fromJson(json, ResumeBean.class);
                if (bean!=null && bean.getStatus() ==1){
                    isRefreshData = true;
                    initData(1,0);
                    ToastUtil.shortToast(getActivity(),"客户已自提");
                }
            }
            @Override
            public void failed(Call call, IOException e) {
                ToastUtil.shortToast(getActivity(),"接单接收失败");
            }
        });
    }

    public void setEmptyIsHind(boolean isHind) {
        if (isHind) {
            empty.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

        } else {
            empty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }

    }


    @Override
    public void lazyLoad() {
        isRefreshData = true;
        initData(1, 0);
    }

}
