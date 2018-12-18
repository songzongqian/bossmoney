package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ASUS on 2018/7/23.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmList;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragmList = fragments;
    }

    @Override
    public Fragment getItem(int index) {
        // TODO Auto-generated method stub
        return mFragmList.get(index);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragmList.size();
    }

}
