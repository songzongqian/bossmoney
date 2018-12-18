package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.OrderDetialAdapter;
import com.byx.xiuboss.xiuboss.NetUrl.Contast;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DisplayUtil;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;
import q.rorbin.badgeview.QBadgeView;


import static com.luck.picture.lib.tools.ScreenUtils.dip2px;

/**
 * A simple {@link Fragment} subclass.
 */
public class Orderragment extends BaseFragment {


    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    Unbinder unbinder;
    private ArrayList<String> mTitles = new ArrayList<>();
    private OrderDetialAdapter mPageAdapter;
    private QBadgeView qBadgeView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_orderragment, container, false);
        unbinder = ButterKnife.bind(this, mView);

        initView();
        return mView;
    }

    private void initView() {
        mTitles.add("待确认");
        mTitles.add("待自提");
        mTitles.add("处理中");
        mTitles.add("配送中");
        mTitles.add("已完成");
        mTitles.add("退款单");
        for (int i = 0; i < mTitles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
        }
        mPageAdapter = new OrderDetialAdapter(getChildFragmentManager(), mTitles);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        setUpTabBadge();
        reflex(mTabLayout);
    }

    private void setUpTabBadge() {
        // 1. 最简单
        qBadgeView = new QBadgeView(getActivity());
        View childAt = mTabLayout.getChildAt(0);
        qBadgeView.bindTarget(((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(0));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtil.dp2px(10), DensityUtil.dp2px(10));
        qBadgeView.setLayoutParams(params);
        qBadgeView.setBadgeTextSize(8, true);
        qBadgeView.setBadgeBackgroundColor(Color.parseColor("#FF2842"));
        qBadgeView.setBadgeNumber(0);

    }

    public void setCurrentFragment(int position) {
        mViewPager.setCurrentItem(position, true);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*设置Tablayout的下划线宽度*/
    public void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    int dp10 = DisplayUtil.dip2px(tabLayout.getContext(), 10);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /*写一个方法用来控制小圆点的显示与隐藏*/
    public void setBadgeNum(int num) {
        if (qBadgeView != null) {
            qBadgeView.setBadgeNumber(num);
            ((MainActivity) getActivity()).onEvent(0x111);
        }
    }

    public void setRequestData() {
        if (mPageAdapter != null && mPageAdapter.getmCurrentFragment() != null) {
            if (mPageAdapter.getmCurrentFragment() instanceof WaitConfimFragment) {
                WaitConfimFragment waitConfimFragment = (WaitConfimFragment) mPageAdapter.getmCurrentFragment();
                if (!waitConfimFragment.isHidden()) {
                    waitConfimFragment.lazyLoad();
                }
            }
        }
    }
}
