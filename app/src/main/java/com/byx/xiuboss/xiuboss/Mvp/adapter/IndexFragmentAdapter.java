package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.byx.xiuboss.xiuboss.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by night_slight on 2019/1/11.
 */

public class IndexFragmentAdapter extends FragmentPagerAdapter{
    private List<BaseFragment> fragments;
    private FragmentManager fm;
    private ArrayList<String> mTabTitles;

    public IndexFragmentAdapter(FragmentManager fm, ArrayList<String> mTabTitles, List<BaseFragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments= fragments;
        this.mTabTitles = mTabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < getCount(); i++) {//通过遍历清除所有缓存
            final long itemId = getItemId(i);
            //得到缓存fragment的名字
            String name = makeFragmentName(container.getId(), itemId);
            //通过fragment名字找到该对象
            BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(name);
            if (fragment != null) {
                //移除之前的fragment
                ft.remove(fragment);
            }
        }
        //重新添加新的fragment:最后记得commit
        ft.add(container.getId(), getItem(position)).attach(getItem(position)).commit();
        return getItem(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /**
     * 得到缓存fragment的名字
     * @param viewId
     * @param id
     * @return
     */
    private String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TextUtils.isEmpty(mTabTitles.get(position))?" ":mTabTitles.get(position);

    }
}
