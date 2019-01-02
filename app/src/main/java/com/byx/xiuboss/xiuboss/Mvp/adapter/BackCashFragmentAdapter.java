package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.byx.xiuboss.xiuboss.Mvp.fragmment.BackCashFragment;
import com.byx.xiuboss.xiuboss.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by night_slight on 2018/12/13.
 */

public class BackCashFragmentAdapter extends FragmentPagerAdapter {

    List<String> mTabTitles;
    ArrayList<BaseFragment>mFragments;
    public BackCashFragmentAdapter(FragmentManager fm, ArrayList<String> mTabTitles, ArrayList<BaseFragment> mFragments) {
        super(fm);
        this.mTabTitles = mTabTitles;
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);
    }
}
