package com.byx.xiuboss.xiuboss.Mvp.fragmment;

import android.support.v4.app.Fragment;

/**
 * Created by night_slight on 2018/11/14.
 */

public abstract class OrderBaseFragment extends Fragment {
    public boolean isVisible;
    public boolean isPrepared;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    /**
     * 可见
     */
    protected void onVisible() {
        whetherLazyLoad();
    }
    /**
     * 不可见
     */
    protected void onInvisible() {
        stopLoad();//停止耗时的操作
    }
    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected void whetherLazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        lazyLoad();
        /*仿制数据重复加载*/
        /*isVisible = false;
        isPrepared = false;*/
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isPrepared = false;
        isPrepared = false;
    }

    /**
     * 懒加载，联网等操作
     */
    public abstract void lazyLoad();

    /**
     * 停止耗时的操作，计时器，动画等
     */
    public void stopLoad(){

    }
}
