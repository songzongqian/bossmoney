package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.ReceipeInfo;
import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.Mvp.adapter.CollRecordeAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.google.gson.Gson;
import com.lzy.okhttputils.callback.JsonCallBack;
import com.lzy.okhttputils.model.RequestParams;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/*收款记录*/

public class CollRecordeActivity extends BaseActivity {

    @BindView(R.id.mEmptyView)
    LinearLayout mEmptyView;

    @BindView(R.id.head_back)
    RelativeLayout mHeadBack;
    @BindView(R.id.head_title)
    TextView mHeadTitle;
    @BindView(R.id.head_save)
    RelativeLayout mHeadSave;
    @BindView(R.id.mIcon)
    ImageView mIcon;
    @BindView(R.id.mName)
    TextView mName;
    @BindView(R.id.mSpeedMoney)
    TextView mSpeedMoney;
    @BindView(R.id.mBackMoney)
    TextView mBackMoney;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private int page = 1;
    private int size = 10;
    private List<ReceipeInfo.DataBean.OrderListBean> mOrderList = new ArrayList<>();
    private CollRecordeAdapter mRecordeAdapter;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coll_recorde);
        ButterKnife.bind(this);
        uid = getIntent().getStringExtra("uid");
        setStatusBar(true);
        initView();
        initData();
    }

    private void initView() {

        mHeadTitle.setText("收款记录");
        mHeadBack.setOnClickListener(v -> {
            finish();
        });

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        mRecordeAdapter = new CollRecordeAdapter(mOrderList, this);
        mRecyclerView.setAdapter(mRecordeAdapter);

        mRecordeAdapter.setOnItemClickListener((position, orderBean) -> {

        });
    }

    private void initData() {
        requestRecordeData();

    }

    public void requestRecordeData() {
        String sid = SPUtils.getInstance(this).getString("sid");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date(System.currentTimeMillis()));
        //Map<String,String> params = new HashMap<>();
        RequestParams params = new RequestParams();
        params.put("source", "android");
        params.put("sid", sid);
        params.put("date", date);
        params.put("openId", uid);
        params.put("startPos", String.valueOf(page));
        params.put("step", String.valueOf(size));

        com.lzy.okhttputils.OkHttpUtils.post(AppUrl.ORDERLIST_URL).params(params).execute(new JsonCallBack<String>() {
            @Override
            public void onResponse(String json) {
                if (json != null) {
                    ReceipeInfo info = new Gson().fromJson(json, ReceipeInfo.class);

                    if (info.getCode() == 2000) {
                        setRecipeInfo(info.getData());
                        if (page == 1) {
                            mOrderList.clear();
                        }
                        if (info.getCode() == 2000) {
                            mOrderList.addAll(info.getData().getOrderList());
                            mRecordeAdapter.notifyDataSetChanged();
                        }
                        if (mOrderList.size() == 0) {
                            mEmptyView.setVisibility(View.VISIBLE);
                        } else {
                            mEmptyView.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });

    }

    private void setRecipeInfo(ReceipeInfo.DataBean data) {

        Glide.with(this).load(data.getCustomerAvatar()).into(mIcon);
        mName.setText(data.getCustomerName());
        mSpeedMoney.setText("￥ " + data.getTotalIncome());
        mBackMoney.setText("共返现￥" + data.getReturnOrderTotal());
    }
}
