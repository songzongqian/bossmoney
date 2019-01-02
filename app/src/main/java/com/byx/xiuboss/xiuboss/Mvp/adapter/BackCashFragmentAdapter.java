package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.byx.xiuboss.xiuboss.Mvp.fragmment.BackCashFragment;
import com.byx.xiuboss.xiuboss.base.BaseFragment;

import java.util.List;

/**
 * Created by night_slight on 2018/12/13.
 */

public class BackCashFragmentAdapter extends FragmentPagerAdapter {

   // List<BaseFragment> mList;

    public BackCashFragmentAdapter(FragmentManager fm) {
        super(fm);
        //this.mList = mList;
    }

    @Override
    public Fragment getItem(int position) {
        return /*mList.get(position)*/new BackCashFragment();
    }

    @Override
    public int getCount() {
        return 2 /*mList.size()*/;
    }
}
