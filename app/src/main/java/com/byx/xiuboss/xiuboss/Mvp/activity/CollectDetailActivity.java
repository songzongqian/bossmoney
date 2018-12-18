package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.XiangQingBean;
import com.byx.xiuboss.xiuboss.Mvp.adapter.CollectDetailAdapter;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class CollectDetailActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.wechat_showpup)
    ImageView wechatShowpup;
    @BindView(R.id.rl_save)
    RelativeLayout rlSave;
    @BindView(R.id.today_recycler)
    RecyclerView todayRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int start=0;
    private String sid;
    private String billDay;
    private CollectDetailAdapter collectDetailAdapter;
    private RequestParams params;
    private List<XiangQingBean.DataBean> dataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectdeatail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        sid = bundle.getString("sid");
        billDay = bundle.getString("stat_day"); //20181120
        System.out.println("新页面接收到的日期"+billDay);
        String month = billDay.substring(4, 6);
        String day = billDay.substring(6, 8);
        titleText.setText(month+"月"+day+"日"+"" + "收款明细");

        //设置RecyclerView 样式
        todayRecycler.setLayoutManager(new LinearLayoutManager(CollectDetailActivity.this));
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener(){
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if(collectDetailAdapter!=null){
                    collectDetailAdapter.notifyDataSetChanged();
                }
                refreshLayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
                refreshData();
                refreshLayout.finishLoadmore();
            }
        });

    }



    //第一次请求网络
    private void initData() {
        params = new RequestParams();
        params.put("sid",sid);
        params.put("stat_day",billDay);
        params.put("start",start+"");
        OkHttpUtils.post(AppUrl.TodayMoney_URL).params(params).execute(new MyJsonCallBack<XiangQingBean>() {
            @Override
            public void onResponse(XiangQingBean xiangQingBean) {
                if(xiangQingBean!=null && xiangQingBean.getCode()==2000){
                    dataList = xiangQingBean.getData();
                    collectDetailAdapter = new CollectDetailAdapter(CollectDetailActivity.this, dataList);
                    todayRecycler.setAdapter(collectDetailAdapter);
                    collectDetailAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });
    }


    private void refreshData() {
        params.clear();
        start+=10;
        params.put("sid",sid);
        params.put("stat_day",billDay);
        params.put("start",start+"");
        OkHttpUtils.post(AppUrl.TodayMoney_URL).params(params).execute(new MyJsonCallBack<XiangQingBean>() {
            @Override
            public void onResponse(XiangQingBean xiangQingBean) {
                if(xiangQingBean!=null && xiangQingBean.getCode()==2000){
                    //请求成功
                    if(xiangQingBean.getMessage().equals("请求成功")){
                        dataList.addAll(xiangQingBean.getData());
                        collectDetailAdapter.notifyDataSetChanged();
                    }else if(xiangQingBean.getMessage().equals("没有数据")){


                    }


                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.wechat_showpup})
    public void onViewClicked(View view){
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.wechat_showpup:
                break;
        }
    }
}
