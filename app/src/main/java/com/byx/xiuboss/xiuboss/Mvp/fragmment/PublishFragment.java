package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
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

import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.SwichActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashAdapter;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BackCashFragmentAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DisplayUtil;
import com.byx.xiuboss.xiuboss.Utils.GetHeaderPwd;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.lzy.okhttputils.model.RequestHeaders;
import com.lzy.okhttputils.model.RequestParams;
import com.zhy.autolayout.AutoLinearLayout;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

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
    @BindView(R.id.mTabLayout)
    SlidingTabLayout mSlideTabLayout;

    Unbinder unbinder;


    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private ArrayList<String> mTabTitles = new ArrayList<>();
    private BackCashFragmentAdapter mAdapter;
    Map<String, String> headerMap = new HashMap<>();


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
        initData();
        return view;
    }

    private void initData() {
        ((MainActivity) getActivity()).showDialog();
        ((MainActivity) getActivity()).getEmptyView().setOnClickListener(v -> {
            initData();
        });
        requestIndexData();
    }

    private void initView() {
        mChangeStoreIcon.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SwichActivity.class);
            String sid = SPUtils.getInstance(getActivity()).getString("sid");
            intent.putExtra("id", sid);
            startActivityForResult(intent, 0x111);
            getActivity().overridePendingTransition(R.anim.bottom_in, R.anim.bottom_silent);
            /*初始化Fragment的数据*/
            ((AllSpeadCashFragment) mFragments.get(0)).initRequest();
            ((BackCashFragment) mFragments.get(1)).initRequest();
        });
        mFragments.add(new AllSpeadCashFragment());
        mFragments.add(new BackCashFragment());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void requestIndexData() {
        SharedPreferences loginSucess = getActivity().getSharedPreferences("login_sucess", MODE_PRIVATE);

        String sid = SPUtils.getInstance(getActivity()).getString("sid");
        RequestParams requestParams = new RequestParams();
        requestParams.put("source", "android");
        requestParams.put("sid", sid);
        com.lzy.okhttputils.OkHttpUtils.post(AppUrl.INDEXDATA_URL).params(requestParams).execute(new MyJsonCallBack<StoreInfo>() {

            @Override
            public void onResponse(StoreInfo storeInfo) {
                ((MainActivity) getActivity()).cancelDialog();
                if (storeInfo != null && storeInfo.getCode() == 2000) {
                    System.out.println("数据请求成功");
                    StoreInfo.DataBean infoBean = storeInfo.getData();
                    mStoreName.setText(infoBean.getStoreName());
                    mAllIcome.setText("￥" + (TextUtils.isEmpty(infoBean.getTotalIncome()) ? "0" : infoBean.getTotalIncome()));
                    mBackPropt.setText("其中休休返现收入占比 " + (TextUtils.isEmpty(infoBean.getTotalReturnRatio()) ? "0" : infoBean.getTotalReturnRatio()));
                    mTabTitles.clear();
                    mTabTitles.add("全部(" + (TextUtils.isEmpty(infoBean.getOrderCount()) ? "0" : infoBean.getOrderCount()) + ")");
                    mTabTitles.add("返现(" + (TextUtils.isEmpty(infoBean.getReturnOrderCount()) ? "0" : infoBean.getReturnOrderCount()) + ")");
                    mAdapter = new BackCashFragmentAdapter(getChildFragmentManager(), mTabTitles, mFragments);
                    mViewPager.setAdapter(mAdapter);
                    mSlideTabLayout.setViewPager(mViewPager);

                    String pop_up_status = infoBean.getPop_up_status();//1
                    String pop_up_text = infoBean.getPop_up_text();//1.1.2
                    String note = infoBean.getPop_up_settles(); //文本内容

                    String versionNumber = loginSucess.getString("versionName", "");
                    if(pop_up_status!=null){
                        if(pop_up_status.equals("1")){
                            //
                            //弹窗
                            if (pop_up_text.equals(versionNumber)) {
                                //版本号一致

                            } else {
                                //版本号不一致
                                loginSucess.edit().putString("versionName", pop_up_text).commit();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("休休小提示")
                                        .setMessage(note)
                                        .setCancelable(false)
                                        .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        }else if(pop_up_status.equals("2")){

                        }
                    }





                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x111) {
            if (resultCode == RESULT_OK) {
                initData();
            }
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //Fragment失去焦点
        } else {
            initData();
        }
    }
}
