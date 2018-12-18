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
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.Bean.ExtractBean;
import com.byx.xiuboss.xiuboss.Bean.ReCoinBean;
import com.byx.xiuboss.xiuboss.Bean.ResumeBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.Mvp.activity.CancelOrderActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.OrderActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.RecoinResonActivity;
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
 * 退款单
 */
@SuppressLint("ValidFragment")
public class ReCoinFragment extends OrderBaseFragment {


    private int position;
    private String title;
    private View mView;

    RecyclerView mRecyclerView;
    SmartRefreshLayout refreshLayout;
    RelativeLayout empty;
    private boolean isRefreshData = false;
    private int start = 0;

    private LayoutInflater inflater;
    private ArrayList<ReCoinBean.DataBean> mList = new ArrayList<>();
    private ReCoinAdapter mAdapter;

    public static ReCoinFragment getReCoinFragment(int position, String title) {
        ReCoinFragment fragment = new ReCoinFragment();
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
        mView = inflater.inflate(R.layout.fragment_recoin, container, false);
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

        mAdapter = new ReCoinAdapter(mList, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        setEmptyIsHind(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                isRefreshData = false;
                start += 20;
                initData(7, 7);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isRefreshData = true;
                start = 0;
                initData(7, 7);
                refreshLayout.finishRefresh();
            }
        });
        setOnItemClickListener();
    }

    private void setOnItemClickListener() {
        mAdapter.setOnItemClickListener(new ReCoinAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ReCoinBean.DataBean categorysBean) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("id", categorysBean.getId());
                startActivity(intent);
            }

            @Override
            public void onItemConfim(int position, ReCoinBean.DataBean categorysBean) {
                //同意订单
                showPopup(categorysBean);
            }

            @Override
            public void onItemResume(int position, ReCoinBean.DataBean categorysBean) {
                //取消订单
                showResumePopup(categorysBean);
            }
        });
    }

    /*弹出PopupWindow*/
    private void showResumePopup(final ReCoinBean.DataBean categorysBean) {
        View pView = LayoutInflater.from(getActivity()).inflate(R.layout.recoin_resumeorder_item, null);
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
                Intent intent = new Intent(getActivity(), RecoinResonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", categorysBean.getId());
                intent.putExtras(bundle);
                startActivityForResult(intent, 0x111);
            }
        });

    }

    private void showPopup(final ReCoinBean.DataBean categorysBean) {
        View pView = LayoutInflater.from(getActivity()).inflate(R.layout.recoin_order_item, null);
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
                agreenReson(categorysBean.getId(), "");
            }
        });

    }

    private void initData(final int status, int delivever_staus) {


        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");
        String path = AppUrl.ORDER_LIST + "?sid=" + sid + "&status=" + status + "&delivery_status=" + delivever_staus + "&start=" + start;

        OkHttpUtils.getInstance().getDataAsynFromUi(path, new OkHttpUtils.UserNetCall() {

            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                ReCoinBean reCoinBean = gson.fromJson(json, ReCoinBean.class);
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
                    if (start == 0) {
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

    private void agreenReson(String id, String reason) {
        SharedPreferences share = getActivity().getSharedPreferences("login_sucess", getActivity().MODE_PRIVATE);
        String sid = share.getString("sid", "");

        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("id", id);
        params.put("type", "agree");
        params.put("content", reason);

        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.ORDER_CMRR, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                ResumeBean resumeBean = gson.fromJson(json, ResumeBean.class);
                if (resumeBean.getCode() == 2000) {
                    ToastUtil.shortToast(getActivity(), "你已同意退款");
                    isRefreshData = true;
                    initData(7, 7);
                }
            }

            @Override
            public void failed(Call call, IOException e) {

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
        initData(7, 7);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x111) {
            if (resultCode == 0x111) {
                isRefreshData = true;
                initData(7, 7);
            }

        }
    }


}
