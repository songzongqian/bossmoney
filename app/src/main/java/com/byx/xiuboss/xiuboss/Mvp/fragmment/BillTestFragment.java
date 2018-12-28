package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.BillTestFirstBean;
import com.byx.xiuboss.xiuboss.Bean.BillTestSecondBean;
import com.byx.xiuboss.xiuboss.Mvp.activity.CollectDetailActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.TodayMoneyActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BillFirstAdapter;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BillSecondAdapter;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.NetUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

public class BillTestFragment extends BaseFragment {
    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.wechat_showpup)
    ImageView wechatShowpup;
    @BindView(R.id.rl_save)
    RelativeLayout rlSave;
    @BindView(R.id.bill_first)
    TextView billFirst;
    @BindView(R.id.recyclerOne)
    RecyclerView recyclerOne;
    @BindView(R.id.button_three)
    Button buttonThree;
    @BindView(R.id.bill_second)
    TextView billSecond;
    @BindView(R.id.recyclerTwo)
    RecyclerView recyclerTwo;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_myUI)
    LinearLayout llMyUI;
    @BindView(R.id.iv_internet)
    ImageView ivInternet;
    @BindView(R.id.tv_internet)
    TextView tvInternet;
    @BindView(R.id.rl_empty_internet)
    RelativeLayout rlEmptyInternet;
    Unbinder unbinder;
    private String sid;
    private BillSecondAdapter billSecondAdapter;
    private RequestParams secondParams;
    private int pageIndexFirst = 0;
    private int pageIndexSecond = 0;

    private List<BillTestSecondBean.DataBean> dataSecondList;

    //重写fragment


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_billtest, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        initData();
        return inflate;
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    //初始化布局
    private void initView() {
        titleText.setText("账单");
        rlBack.setVisibility(View.GONE);
        rlSave.setVisibility(View.GONE);
        recyclerTwo.setLayoutManager(new LinearLayoutManager(getActivity()));
    }





    //开始加载数据
    private void initData() {
       if(NetUtils.isConnected(getActivity())){
           SharedPreferences  loginSucess = getActivity().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
           sid = loginSucess.getString("sid", "");
           System.out.println("切换后的账单页面的sid"+sid);
           getSecond();
           getFirst();

           //设置RecyclerView 样式
           recyclerOne.setLayoutManager(new LinearLayoutManager(getActivity()));

           //刷新
           refreshLayout.setOnRefreshListener(new OnRefreshListener(){
               @Override
               public void onRefresh(RefreshLayout refreshLayout) {
                   if(billSecondAdapter!=null){
                       billSecondAdapter.notifyDataSetChanged();
                   }
                   refreshLayout.finishRefresh();
               }
           });
           //加载更多
           refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
               @Override
               public void onLoadmore(RefreshLayout refreshLayout) {
                   getSecondMore();
                   refreshLayout.finishLoadmore();
               }
           });



       }else{
           //无网络状态

       }


    }


    //获取第一个recycler的数据
    private void getFirst() {
        pageIndexFirst = 0;
        System.out.println("getFirst执行了");
        RequestParams firstParams= new RequestParams();
        firstParams.put("sid",sid);
        firstParams.put("start", pageIndexFirst +"");
        OkHttpUtils.post(AppUrl.TodayMoney_URL).params(firstParams).execute(new MyJsonCallBack<BillTestFirstBean>() {
            @Override
            public void onResponse(BillTestFirstBean billTestFirstBean) {
                if( billTestFirstBean!=null && billTestFirstBean.getCode()==2000){
                    if(billTestFirstBean.getData().size()==0){
                        System.out.println("进入没有数据页面");
                        //没有数据1、隐藏第一个recyclerView,2、显示textView
                        billFirst.setVisibility(View.VISIBLE);
                        recyclerOne.setVisibility(View.GONE);
                        buttonThree.setVisibility(View.GONE);
                    }else{
                        List<BillTestFirstBean.DataBean> dataList = billTestFirstBean.getData();
                        int size = dataList.size();
                        System.out.println("list的长度"+size);
                        System.out.println("进入有数据页面");
                        billFirst.setVisibility(View.GONE);
                        recyclerOne.setVisibility(View.VISIBLE);
                        if(dataList.size()<5){
                            buttonThree.setVisibility(View.GONE);
                        }else{
                            dataList=dataList.subList(0,5);
                            buttonThree.setVisibility(View.VISIBLE);
                        }
                        BillFirstAdapter  billFirstAdapter =new BillFirstAdapter(getActivity(),dataList);
                        recyclerOne.setAdapter(billFirstAdapter);
                        //创建适配器
                    }
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
                System.out.println("执行了onError");
            }
        });


    }

    //获取第二个recycler的数据
    private void getSecond() {
        pageIndexSecond = 0;
        secondParams = new RequestParams();
        secondParams.put("sid",sid);
        secondParams.put("start",pageIndexSecond+"");
        OkHttpUtils.post(AppUrl.EVERYDAY_URL).params(secondParams).execute(new MyJsonCallBack<BillTestSecondBean>() {

            @Override
            public void onResponse(BillTestSecondBean billTestSecondBean) {
                if(billTestSecondBean!=null && billTestSecondBean.getCode()==2000){
                    if(dataSecondList!=null){
                        dataSecondList.clear();
                    }
                    dataSecondList = billTestSecondBean.getData();
                    billSecondAdapter = new BillSecondAdapter(getActivity(), dataSecondList,sid);
                    recyclerTwo.setAdapter(billSecondAdapter);
                    billSecondAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });
        
    }

    //获取第二个加载更多的网络请求
    private void getSecondMore() {
        secondParams.clear();
        pageIndexSecond+=5;
        secondParams = new RequestParams();
        secondParams.put("sid",sid);
        secondParams.put("start",pageIndexSecond+"");
        OkHttpUtils.post(AppUrl.EVERYDAY_URL).params(secondParams).execute(new MyJsonCallBack<BillTestSecondBean>() {

            @Override
            public void onResponse(BillTestSecondBean billTestSecondBean) {
                if(billTestSecondBean!=null && billTestSecondBean.getCode()==2000){
                    if(billTestSecondBean.getData().size()==0){

                    }else{
                        dataSecondList.addAll(billTestSecondBean.getData());
                       // billSecondAdapter.setData(dataSecondList);
                        billSecondAdapter.notifyDataSetChanged();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.button_three, R.id.bill_second})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_three:
      Intent intent=new Intent(getActivity(),TodayMoneyActivity.class);
      getActivity().startActivity(intent);
                break;
            case R.id.bill_second:
                break;
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){

        }else{
            initData();
        }
    }
}
