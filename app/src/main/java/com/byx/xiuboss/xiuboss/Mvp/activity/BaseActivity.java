package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;

import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by wangwenjie001 on 2018/9/13.
 */

public abstract class BaseActivity extends AutoLayoutActivity {
    protected int mWidth;
    protected int mHeight;
    protected float mDensity;
    protected int mDensityDpi;
    protected int mAvatarSize;
    protected float mRatio;

    protected BaseFragment mCurFragment;
    private BaseActivity mContext;
    public boolean isDestoryed = false;//页面是否被销毁

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;
        mDensityDpi = dm.densityDpi;
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
        mRatio = Math.min((float) mWidth / 720, (float) mHeight / 1280);
        mAvatarSize = (int) (50 * mDensity);
    }

    public void goToActivity(Context context, Class toActivity) {
        Intent intent = new Intent(context, toActivity);
        startActivity(intent);
    }

    public void addFm(int bodyId, BaseFragment fm) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fm.isAdded())
            fragmentTransaction.show(fm);
        else
            fragmentTransaction.add(bodyId, fm);
        if (!mContext.isDestoryed) {
            fragmentTransaction.commit();
        }
        mCurFragment = fm;
    }

    public void goToFmNoAnim(int bodyId, BaseFragment to) {
        if (mCurFragment != to) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过

                fragmentTransaction.hide(mCurFragment).add(bodyId, to);
                if (!mContext.isDestoryed) {
                    fragmentTransaction.commit(); // 隐藏当前的fragment，add下一个到Activity中
                }
            } else {
                fragmentTransaction.hide(mCurFragment).show(to);
                if (!mContext.isDestoryed) {
                    fragmentTransaction.commit(); // 隐藏当前的fragment，显示下一个
                }
            }
            mCurFragment = to;
        }
    }

    public void goToFm(int bodyId, BaseFragment to) {
        goToFm(bodyId, to, false);
    }

    public void goToFm(int bodyId, BaseFragment to, boolean isBack) {
        if (mCurFragment != to) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (isBack) {
                fragmentTransaction.setCustomAnimations(
                        R.anim.slip_in_from_left, R.anim.slip_out_to_right);
            } else {
                fragmentTransaction.setCustomAnimations(
                        R.anim.slip_in_from_right, R.anim.slip_out_to_left);
            }
            if (!to.isAdded()) {
                fragmentTransaction.hide(mCurFragment).add(bodyId, to);
                // 先判断是否被add过
                if (!mContext.isDestoryed) {
                    fragmentTransaction.commit(); // 隐藏当前的fragment，add下一个到Activity中
                }
            } else {
                fragmentTransaction.hide(mCurFragment).show(to);
                if (!mContext.isDestoryed) {
                    fragmentTransaction.commit(); // 隐藏当前的fragment，显示下一个
                }
            }
            mCurFragment = to;
        }
    }

    public void switchToFm(int bodyId, BaseFragment to) {
        if (mCurFragment != to) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //mCurFragment.pause();
            if (!to.isAdded()) {
                fragmentTransaction.hide(mCurFragment).add(bodyId, to);
                // 先判断是否被add过
                if (!mContext.isDestoryed) {
                    fragmentTransaction.commit(); // 隐藏当前的fragment，add下一个到Activity中
                }
            } else {
                fragmentTransaction.hide(mCurFragment).show(to);
                if (!mContext.isDestoryed) {
                    fragmentTransaction.commit(); // 隐藏当前的fragment，显示下一个
                }
            }
            mCurFragment = to;
        }
    }

    public void backToFm(int bodyId, BaseFragment to) {
        goToFm(bodyId, to, true);
    }

    public void destoryFragment(BaseFragment[] frameFragments) {
        if (frameFragments != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            for (BaseFragment frameFragment : frameFragments)
                fragmentTransaction.remove(frameFragment);
            fragmentTransaction.commitNow();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestoryed = true;
    }
}
