package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

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

    public String TAG = getClass().getSimpleName();
    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useThemestatusBarColor = false;
    //是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useStatusBarColor = true;

    protected BaseFragment mCurFragment;
    private BaseActivity mContext;
    public boolean isDestoryed = false;//页面是否被销毁
    private View mDialogView;
    private LinearLayout mProgressView;
    private LinearLayout mEmptyView;

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

    protected void setStatusBar(boolean IsTransparent) {
        /*IsTransparent，true 表示透明，false 表示不透明*/
        useThemestatusBarColor = !IsTransparent;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));//设置状态栏背景色
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /*==============开始=用来网络第一此加载失败时显示=====================*/
    private View createV(Activity a) {
        if (mDialogView== null){
            // 1.找到activity根部的ViewGroup，类型都为FrameLayout。
            FrameLayout rootContainer = (FrameLayout) a.findViewById(android.R.id.content);//固定写法，返回根视图
            //2.初始化控件显示的位置
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
            lp.gravity = Gravity.CENTER;
            /*设置显示的视图*/
            mDialogView = View.inflate(this, R.layout.load_progress, null);
            mProgressView = mDialogView.findViewById(R.id.mProgressView);
            mEmptyView = mDialogView.findViewById(R.id.mEmptyView);
            //4.将控件加到根节点下
            rootContainer.addView(mDialogView);
        }
        mEmptyView.setVisibility(View.GONE);
        mProgressView.setVisibility(View.VISIBLE);
        return mDialogView;
    }

    public void showDialog() {
        createV(this);
        handler.sendEmptyMessageDelayed(200,2000);
    }

    public void cancelDialog() {
        if (mDialogView != null) {
            mDialogView.setVisibility(View.GONE);
        }
    }

    public View getEmptyView() {
        return mDialogView;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==200){
                changeToEmpty();
            }
        }
    };
    private void changeToEmpty() {
        if (mDialogView!=null){
            mProgressView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    /*----------结束---------------*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestoryed = true;
    }
}
