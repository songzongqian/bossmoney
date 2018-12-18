package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.ConfimOrderBean;
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.Mvp.activity.CancelOrderActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.OrderActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.ComfimAdapter;
import com.byx.xiuboss.xiuboss.Mvp.adapter.DealAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.RewritePopwindow;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * 待确认
 */
@SuppressLint("ValidFragment")
public class ConfimFragment extends OrderBaseFragment {


    private int position;
    private String title;
    private View mView;
    private boolean isRefreshData = true;

    RecyclerView mRecyclerView;
    SmartRefreshLayout refreshLayout;
    RelativeLayout empty;

    private LayoutInflater inflater;
    private ArrayList<DealBean.DataBean> mList = new ArrayList<>();
    private ComfimAdapter mAdapter;
    private int start = 0;

    public static ConfimFragment getConfimFragment(int position, String title) {
        ConfimFragment fragment = new ConfimFragment();
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
        this.inflater = LayoutInflater.from(getActivity());
        mView = inflater.inflate(R.layout.fragment_deal, container, false);
        EventBus.getDefault().register(this);
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


        mAdapter = new ComfimAdapter(mList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        setEmptyIsHind(false);
        setOnClick();
    }

    private void setOnClick() {
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                start+=20;
                isRefreshData = false;
                initData(1,0);
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
        mAdapter.setOnItemClickListener(new ComfimAdapter.OnItemClickListener() {
            @Override
            public void onItemConfimClick(int position, DealBean.DataBean categorysBean) {
                if (categorysBean.getOrder_type() != null && categorysBean.getOrder_type().equals("1")){
                    /*配送订单*/
                    showPopup(1,categorysBean);
                }else{
                    /*自提订单*/
                    showPopup(0,categorysBean);
                }

            }

            @Override
            public void onItemCancelClick(int position, DealBean.DataBean categorysBean) {
                Intent intent = new Intent(getActivity(), CancelOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",categorysBean.getId());
                bundle.putString("uid",categorysBean.getUid());
                intent.putExtras(bundle);
                startActivityForResult(intent,0x111);
                //cancelOrder(categorysBean);
            }

            @Override
            public void onItemClick(int position, DealBean.DataBean categorysBean) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("id",categorysBean.getId());
                startActivity(intent);
            }
        });
    }
    /*弹出PopupWindow*/
    private void showPopup(int type,final DealBean.DataBean categorysBean){
        View pView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_order_item,null);
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
                confimOrder(type,categorysBean);
            }
        });

    }

    /*请求确认订单*/
    public void confimOrder(int type, DealBean.DataBean categorysBean){
        //Toast.makeText(getActivity(),"你点击了确认订单按钮",Toast.LENGTH_SHORT).show();

        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");

        Map<String,String>params = new HashMap<>();
        params.put("status","1");
        params.put("delivery_status","0");
        params.put("id",categorysBean.getId());
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.ORDER_COMFIM, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                ConfimOrderBean orderBean = gson.fromJson(json, ConfimOrderBean.class);
                if (orderBean!=null && orderBean.getStatus()==1){
                    isRefreshData = true;
                    initData(1,0);
                    ToastUtil.shortToast(getActivity(),"订单已接收");
                }
            }
            @Override
            public void failed(Call call, IOException e) {
                ToastUtil.shortToast(getActivity(),"接单接收失败");
            }
        });
    }

    /*请求网络数据*/
    private void initData(final int status, int delivever_staus) {

        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");
        String path = AppUrl.ORDER_LIST+"?sid="+sid+"&status="+status+"&delivery_status="+delivever_staus+"&start="+start;

        OkHttpUtils.getInstance().getDataAsynFromUi(path, new OkHttpUtils.UserNetCall() {

            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                DealBean reCoinBean = gson.fromJson(json, DealBean.class);
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                if (reCoinBean != null && reCoinBean.getData() != null) {
                    if (isRefreshData){
                        mList.clear();
                    }
                    mList.addAll(reCoinBean.getData());
                    mAdapter.notifyDataSetChanged();
                    SPUtils.getInstance(getActivity()).put("orderNum",mList.size());
                    ((Orderragment)getParentFragment()).setBadgeNum(mList.size());
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
        if (title.equals("待确认")) {
            initData(1, 0);
        } /*else if (title.equals("处理中")) {
            initData(4, 7);
        } else if (title.equals("配送中")) {
            initData(4, 4);
        }*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==0x111){
            if (resultCode == 0x111){
                isRefreshData = true;
                initData(1,0);
            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(int event){
        if (event == 0x111){
            if (this.isResumed()){
                lazyLoad();
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
