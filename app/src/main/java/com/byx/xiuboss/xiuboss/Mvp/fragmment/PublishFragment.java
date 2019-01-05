package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.SwichActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashAdapter;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashFragmentAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DisplayUtil;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.lzy.okhttputils.model.RequestParams;
import com.zhy.autolayout.AutoLinearLayout;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishFragment extends BaseFragment {


    @BindView(R.id.mStoreName)
    TextView mStoreName;
    @BindView(R.id.mChangeStoreIcon)
    ImageView mChangeStoreIcon;
    @BindView(R.id.mAllIcome)
    TextView mAllIcome;
    @BindView(R.id.mBackPropt)
    TextView mBackPropt;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.mTabLayout)
    SlidingTabLayout mSlideTabLayout;

    Unbinder unbinder;


    private ArrayList<BaseFragment>mFragments = new ArrayList<>();
    private ArrayList<String> mTabTitles = new ArrayList<>();
    private BackCashFragmentAdapter mAdapter;


    public PublishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publish, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView();
        initData();
        return view;
    }

    private void initData() {
        ((MainActivity)getActivity()).showDialog();
        ((MainActivity)getActivity()).getEmptyView().setOnClickListener(v -> {
            initData();
        });
        requestIndexData();
    }

    private void initView() {
        mChangeStoreIcon.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SwichActivity.class);
            String sid = SPUtils.getInstance(getActivity()).getString("sid");
            intent.putExtra("id", sid);
            startActivityForResult(intent,0x111);
            getActivity().overridePendingTransition(R.anim.bottom_in,R.anim.bottom_silent);
            /*初始化Fragment的数据*/
            ((AllSpeadCashFragment)mFragments.get(0)).initRequest();
            ((BackCashFragment)mFragments.get(1)).initRequest();
        });
        mFragments.add(new AllSpeadCashFragment());
        mFragments.add(new BackCashFragment());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void requestIndexData() {

        Map<String,String>parmas = new HashMap<>();
        String sid = SPUtils.getInstance(getActivity()).getString("sid");
        parmas.put("source","android");
        parmas.put("sid",sid);
        //parmas.put("token","49e1tBbQn-uRAuUxFCIyVby5klNeYZ1UIKkUmfuWAzbXyox4lb9heQ");

        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.INDEXDATA_URL, parmas, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                ((MainActivity)getActivity()).cancelDialog();
                StoreInfo info = new Gson().fromJson(json, StoreInfo.class);
                if (info.getCode() == 2000){
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
        mStoreName.setText(infoBean.getStoreName());
        mAllIcome.setText("￥"+ (TextUtils.isEmpty(infoBean.getTotalIncome())?"0":infoBean.getTotalIncome()));
        mBackPropt.setText("其中休休返现收入占比 "+(TextUtils.isEmpty(infoBean.getTotalReturnRatio())?"0":infoBean.getTotalReturnRatio()));
        mTabTitles.clear();
        mTabTitles.add("全部("+(TextUtils.isEmpty(infoBean.getOrderCount())?"0":infoBean.getOrderCount())+")");
        mTabTitles.add("返现("+(TextUtils.isEmpty(infoBean.getReturnOrderCount())?"0":infoBean.getReturnOrderCount())+")");
        mAdapter = new BackCashFragmentAdapter(getChildFragmentManager(), mTabTitles,mFragments);
        mViewPager.setAdapter(mAdapter);
        mSlideTabLayout.setViewPager(mViewPager);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x111){
            if (resultCode == RESULT_OK){
                initData();
            }
        }
    }
}
