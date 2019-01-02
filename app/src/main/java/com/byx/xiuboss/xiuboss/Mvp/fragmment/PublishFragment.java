package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashFragmentAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DisplayUtil;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.lzy.okhttputils.model.RequestParams;
import com.zhy.autolayout.AutoLinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
            intent.putExtra("id", /*sid*/"111");
            getActivity().startActivityForResult(intent,0x111);
            getActivity().overridePendingTransition(R.anim.bottom_in,R.anim.bottom_silent);
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

        //Map<String,String>parmas = new HashMap<>();
        //OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.INDEXDATA_URL,parmas,null);
        ((MainActivity)getActivity()).cancelDialog();
        mTabTitles.add("全部(173)");
        mTabTitles.add("返现(23)");


        /*StoreInfo info = new StoreInfo();
        StoreInfo.DataBean data = info.getData();
        setIndexData(data);*/

    }

    /*设置数据*/
    private void setIndexData(StoreInfo.DataBean infoBean) {
        mStoreName.setText(infoBean.getStoreName());
        mAllIcome.setText(infoBean.getTotalIncome());
        mBackPropt.setText("其中休休返现收入占比 "+infoBean.getTotalReturnRatio());
        mTabTitles.clear();
        mTabTitles.add("全部("+infoBean.getOrderCount()+")");
        mTabTitles.add("返现("+infoBean.getReturnOrderCount()+")");

        BackCashFragmentAdapter mAdapter = new BackCashFragmentAdapter(getChildFragmentManager(), mTabTitles,mFragments);
        ((AllSpeadCashFragment)mFragments.get(0)).setSpeadCashList(infoBean.getOrderList());
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
