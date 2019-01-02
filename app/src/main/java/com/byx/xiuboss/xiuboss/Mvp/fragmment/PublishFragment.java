package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.graphics.Color;
import android.os.Bundle;
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

import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashFragmentAdapter;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    @BindViews({R.id.mTextView01, R.id.mTextView02})
    TextView[] mTvDetials;
    @BindViews({R.id.mView01, R.id.mView02})
    View[] mViews;

    @BindViews({R.id.mLayout01, R.id.mLayout02})
    AutoLinearLayout[] mLayout;
    Unbinder unbinder;

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
        return view;
    }

    private void initView() {

        BackCashFragmentAdapter mAdapter = new BackCashFragmentAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addFm(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mTextView01, R.id.mTextView02})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mTextView01:
                addFm(0);
                break;
            case R.id.mTextView02:
                addFm(1);
                break;
        }

    }

    public void addFm(int position){
        for(int i=0;i<mTvDetials.length;i++){
            if (position == i){
                mViews[i].setVisibility(View.VISIBLE);
                mTvDetials[i].setTextColor(Color.parseColor("#FDE3B6"));
            }else{
               mViews[i].setVisibility(View.INVISIBLE);
                mTvDetials[i].setTextColor(Color.parseColor("#F2C3B7"));
            }
        }
        mViewPager.setCurrentItem(position,false);
    }
}
