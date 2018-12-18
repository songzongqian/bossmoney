package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.byx.xiuboss.xiuboss.Bean.ReCoinBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.Mvp.activity.OrderActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.DealAdapter;
import com.byx.xiuboss.xiuboss.Mvp.adapter.ReCoinAdapter;
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
 * 其他
 */
@SuppressLint("ValidFragment")
public class DealFragment extends OrderBaseFragment {


    private int position;
    private String title;
    private View mView;
    private int start = 0;

    RecyclerView mRecyclerView;
    SmartRefreshLayout refreshLayout;
    RelativeLayout empty;
    private boolean isRefreshData = false;

    private LayoutInflater inflater;
    private ArrayList<DealBean.DataBean> mList = new ArrayList<>();
    private DealAdapter mAdapter;

    public static DealFragment getDealFragment(int position, String title) {
        DealFragment fragment = new DealFragment();
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

        mAdapter = new DealAdapter(mList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        setEmptyIsHind(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                isRefreshData = false;
                start += 20;
                if (title.equals("处理中")) {
                    initData(4, 7);
                } else if (title.equals("配送中")) {
                    initData(4, 4);
                }
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                start = 0;
                isRefreshData = true;
                if (title.equals("处理中")) {
                    initData(4, 7);
                } else if (title.equals("配送中")) {
                    initData(4, 4);
                }
                refreshLayout.finishRefresh();
            }
        });
        setOnItemClick();
    }

    private void setOnItemClick() {
        mAdapter.setOnItemClickListener(new DealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, DealBean.DataBean categorysBean) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("id", categorysBean.getId());
                startActivity(intent);
            }

            @Override
            public void onBossPhoneClick(String phone) {
                //站长电话
                showPopup(phone);
            }

            @Override
            public void onMeSendClick(int type,int postion, DealBean.DataBean categorysBean) {
                //ToastUtil.shortToast(getActivity(), "老板亲自上阵了");
                if (type==1){
                    showMineSendPopup(categorysBean);
                }else if (type ==0){
                    showSendComplete(categorysBean);
                }

            }
        });
    }

    /*弹出PopupWindow*/
    private void showPopup(final String phoneStr) {
        View pView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_bossshow, null);
        final RewritePopwindow rPop = new RewritePopwindow(getActivity(), pView);
        ImageView close = pView.findViewById(R.id.close);
        TextView phoneTv = pView.findViewById(R.id.bossPhone);
        TextView confim = pView.findViewById(R.id.confim);
        phoneTv.setText(phoneStr);
        rPop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        close.setOnClickListener(view -> {
            rPop.dismiss();
        });
        confim.setOnClickListener(view -> {
            rPop.dismiss();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + phoneStr);
            intent.setData(data);
            getActivity().startActivity(intent);

        });

    }

    private void showMineSendPopup(final DealBean.DataBean categorysBean) {
        View pView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_bosssendorder, null);
        final RewritePopwindow rPop = new RewritePopwindow(getActivity(), pView);
        TextView cancelView = pView.findViewById(R.id.order_cancel);
        TextView confimView = pView.findViewById(R.id.order_confim);

        rPop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);

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
                requestMineSendSend(4,7,categorysBean);
            }
        });

    }

    private void showSendComplete(final DealBean.DataBean categorysBean) {
        View pView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_bosssendorder, null);
        final RewritePopwindow rPop = new RewritePopwindow(getActivity(), pView);
        TextView order_tipTitle = pView.findViewById(R.id.order_tipTitle);
        TextView cancelView = pView.findViewById(R.id.order_cancel);
        TextView confimView = pView.findViewById(R.id.order_confim);
        order_tipTitle.setText("确认客户已经接单？");

        rPop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);

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
                requestMineSendSend(4,4,categorysBean);
            }
        });

    }

    private void initData(int status, int delivever_staus) {

        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");
        String path = AppUrl.ORDER_LIST + "?sid=" + sid + "&status=" + status + "&delivery_status=" + delivever_staus + "&start=" + start;

        OkHttpUtils.getInstance().getDataAsynFromUi(path, new OkHttpUtils.UserNetCall() {

            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                DealBean reCoinBean = gson.fromJson(json, DealBean.class);
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();

                if (reCoinBean != null && reCoinBean.getData() != null) {
                    if (isRefreshData) {
                        mList.clear();
                    }
                    mList.addAll(reCoinBean.getData());
                    mAdapter.notifyDataSetChanged();
                    if (mList.size() > 0) {
                        setEmptyIsHind(true);
                    } else {
                        setEmptyIsHind(false);
                    }
                } else {
                    setEmptyIsHind(false);
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


    private void requestMineSendSend(int status, int delivery_status, DealBean.DataBean categorysBean) {

        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");
        Map<String,String>params = new HashMap<>();
        params.put("sid",sid);
        params.put("id",categorysBean.getId());
        params.put("status",String.valueOf(status));
        params.put("delivery_status",String.valueOf(delivery_status));

        //String path = AppUrl.ORDER_COMFIM + "?sid=" + sid + "&status=" + status + "&delivery_status=" + delivever_staus + "&start=" + start;
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.ORDER_COMFIM, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                ConfimOrderBean orderBean = gson.fromJson(json, ConfimOrderBean.class);
                if (orderBean!=null && orderBean.getStatus()==1){
                    isRefreshData = true;
                    if (status==4 && delivery_status == 7){
                        ToastUtil.shortToast(getActivity(),"订单已配送");
                        initData(4,7);
                    }else{
                        ToastUtil.shortToast(getActivity(),"订单已完成");
                        initData(4,4);
                    }

                }
            }
            @Override
            public void failed(Call call, IOException e) {
                ToastUtil.shortToast(getActivity(),"接单处理失败");
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
        if (title.equals("处理中")) {
            initData(4, 7);
        } else if (title.equals("配送中")) {
            initData(4, 4);
        }
    }

}
