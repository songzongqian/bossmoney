package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashFragmentAdapter;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.AllSpeadCashFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.BackCashFragment;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DateTimeUtils;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

public class SettRecordActivity extends BaseActivity {

    @BindView(R.id.head_back)
    RelativeLayout mBack;
    @BindView(R.id.head_title)
    TextView mTitle;

    @BindView(R.id.mAllIcome)
    TextView mAllIcome;
    @BindView(R.id.mBackPropt)
    TextView mBackPropt;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.mTabLayout)
    SlidingTabLayout mSlideTabLayout;
    @BindView(R.id.mDateTime)
    TextView mDateTime;

    Unbinder unbinder;


    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private ArrayList<String> mTabTitles = new ArrayList<>();
    private String resultTime;
    private String dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sett_record);
        unbinder = ButterKnife.bind(this);
        dateTime = getIntent().getStringExtra("DateTime");

        setStatusBar(true);
        initView();
        initData();
    }

    private void initData() {
        showDialog();
        getEmptyView().setOnClickListener(v -> {
            initData();
        });
        requestIndexData();
    }

    private void initView() {
        mBack.setOnClickListener(v -> {
            finish();
        });
        mTitle.setText("收款记录");
        mFragments.add(new AllSpeadCashFragment());
        mFragments.add(new BackCashFragment());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    public void requestIndexData() {

        Map<String, String> parmas = new HashMap<>();
        String sid = SPUtils.getInstance(this).getString("sid");
        parmas.put("source", "android");
        parmas.put("sid", sid);
        parmas.put("date", dateTime);
        //parmas.put("token","49e1tBbQn-uRAuUxFCIyVby5klNeYZ1UIKkUmfuWAzbXyox4lb9heQ");

        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.INDEXDATA_URL, parmas, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                cancelDialog();
                StoreInfo info = new Gson().fromJson(json, StoreInfo.class);
                if (info.getCode() == 2000) {
                    setIndexData(info.getData());
                }
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });


    }

    /*设置数据*/
    private void setIndexData(StoreInfo.DataBean infoBean) {
        //mStoreName.setText(infoBean.getStoreName());
        if (!TextUtils.isEmpty(dateTime)) {
            resultTime = DateTimeUtils.judteTime(dateTime, this);
        }
        mDateTime.setText(resultTime+"");
        mAllIcome.setText("￥" + (TextUtils.isEmpty(infoBean.getTotalIncome()) ? "0" : infoBean.getTotalIncome()));
        mBackPropt.setText("其中休休返现收入占比 " + (TextUtils.isEmpty(infoBean.getTotalReturnRatio()) ? "0" : infoBean.getTotalReturnRatio()));
        mTabTitles.clear();
        mTabTitles.add("全部(" + (TextUtils.isEmpty(infoBean.getOrderCount()) ? "0" : infoBean.getOrderCount()) + ")");
        mTabTitles.add("返现(" + (TextUtils.isEmpty(infoBean.getReturnOrderCount()) ? "0" : infoBean.getReturnOrderCount()) + ")");
        mViewPager.removeAllViews();
        BackCashFragmentAdapter mAdapter = new BackCashFragmentAdapter(getSupportFragmentManager(), mTabTitles, mFragments);
        mViewPager.setAdapter(mAdapter);
        mSlideTabLayout.setViewPager(mViewPager);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x111) {
            if (resultCode == RESULT_OK) {
                initData();
            }
        }
    }

}
