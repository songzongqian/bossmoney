package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.ToDayBean;
import com.byx.xiuboss.xiuboss.Mvp.adapter.ToDayAdapter;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.lzy.okhttputils.model.RequestParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class TodayMoneyActivity extends BaseActivity {
    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.today_recycler)
    RecyclerView todayRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.empty)
    LinearLayout empty;
    private int start = 0;
    private SharedPreferences sp;
    private List<ToDayBean.DataBean> toDayList;
    private ToDayAdapter adapter;
    private String sid;
    private String stat_day;
    private List<ToDayBean.DataBean> toDayBeanData;
    private RequestParams todayParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_money);
        ButterKnife.bind(this);
        sp = getSharedPreferences("login_sucess", MODE_PRIVATE);
        initData();
    }

    private void initData() {
        titleText.setText("今日收入明细");
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                adapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
            }
        });

        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
                obtainDataTwo();
                adapter.notifyDataSetChanged();
                refreshLayout.finishLoadmore();
            }
        });
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH) + 1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        stat_day = year + "" + month + day + "";
        sid = sp.getString("sid", "");
        todayParams = new RequestParams();
        todayParams.put("sid", sid);
       // map.put("stat_day", stat_day);
        todayParams.put("start", start + "0");
        com.lzy.okhttputils.OkHttpUtils.post(AppUrl.TODAYBILL_URL).params(todayParams).execute(new MyJsonCallBack<ToDayBean>() {
            @Override
            public void onResponse(ToDayBean toDayBean) {
                if(toDayBean!=null &&toDayBean.getCode()==2000){
                    //请求成功
                    toDayBeanData = toDayBean.getData();
                    if(toDayBeanData ==null || toDayBeanData.size()==0){
                        //无数据
                        refreshLayout.setVisibility(View.GONE);
                        empty.setVisibility(View.VISIBLE);
                    }else if(toDayBeanData !=null && toDayBeanData.size()>0){
                        refreshLayout.setVisibility(View.VISIBLE);
                        empty.setVisibility(View.GONE);
                        adapter = new ToDayAdapter(toDayBeanData, TodayMoneyActivity.this);
                        todayRecycler.setLayoutManager(new LinearLayoutManager(TodayMoneyActivity.this));
                        todayRecycler.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });

    }


    @OnClick(R.id.title_back_image)
    public void onViewClicked() {
        finish();
    }

    public void obtainDataTwo() {
        todayParams.clear();
        start+=10;
        todayParams.put("sid", sid);
        todayParams.put("start", start + "");
        com.lzy.okhttputils.OkHttpUtils.post(AppUrl.TODAYBILL_URL).params(todayParams).execute(new MyJsonCallBack<ToDayBean>() {
            @Override
            public void onResponse(ToDayBean toDayBean) {
                if(toDayBean!=null &&toDayBean.getCode()==2000){
                    //请求成功
                    List<ToDayBean.DataBean> list = toDayBean.getData();
                    if(list ==null || list.size()==0){

                    }else{
                        toDayBeanData.addAll(list);
                        adapter.notifyDataSetChanged();
                    }


                }
            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });
    }

}
