package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.byx.xiuboss.xiuboss.Mvp.fragmment.ConfimFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.DealFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.ExtractlFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.OrderBaseFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.OrderDetialFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.ReCoinFragment;
import com.byx.xiuboss.xiuboss.Mvp.fragmment.WaitConfimFragment;
import com.byx.xiuboss.xiuboss.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by night_slight on 2018/11/12.
 */

public class OrderDetialAdapter extends FragmentStatePagerAdapter{
    private OrderBaseFragment mCurrentFragment;
    private ArrayList<String>mTitles;
    public OrderDetialAdapter(FragmentManager fm, ArrayList<String> mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){//待确认
            //return ConfimFragment.getConfimFragment(position,mTitles.get(position));
            return WaitConfimFragment.getConfimFragment(position,mTitles.get(position));
        }else if (position==1){  //待自提
            return ExtractlFragment.getExtractFragment(position,mTitles.get(position));
        }else if (position == 4){ //已完成
            return OrderDetialFragment.getOrderFragment(position,mTitles.get(position));
        }else if (position == 5){  //退款单
            return ReCoinFragment.getReCoinFragment(position,mTitles.get(position));
        }else{
            return DealFragment.getDealFragment(position,mTitles.get(position));
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        OrderBaseFragment fragment = (OrderBaseFragment) super.instantiateItem(container,position);
        //return super.instantiateItem(container, position);
        return fragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (OrderBaseFragment) object;
        super.setPrimaryItem(container, position, object);

    }
    public OrderBaseFragment getmCurrentFragment(){
        return mCurrentFragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
