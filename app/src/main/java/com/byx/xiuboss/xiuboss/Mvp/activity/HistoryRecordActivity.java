package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.BillTestSecondBean;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BillSecondAdapter;
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

public class HistoryRecordActivity extends BaseActivity {
    /**
     * 收款明细新版
     */
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
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private int pageIndexSecond = 0;
    private String sid;
    private List<BillTestSecondBean.DataBean> dataSecondList;
    private BillSecondAdapter billSecondAdapter;
    private RequestParams secondParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_record);
        setStatusBar(true);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        titleText.setText("收款明细");
        rlSave.setVisibility(View.GONE);
        //获取Sid
        SharedPreferences share = getSharedPreferences("login_sucess", MODE_PRIVATE);
        sid = share.getString("sid", "");
        //设置RecyclerView 样式
        recycler.setLayoutManager(new LinearLayoutManager(HistoryRecordActivity.this));
        //刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener(){

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                billSecondAdapter.notifyDataSetChanged();
                smartRefreshLayout.finishRefresh();
            }
        });
        //加载更多
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
                refreshMore();
                smartRefreshLayout.finishLoadmore();
            }
        });

    }




    private void initData() {
        secondParams = new RequestParams();
        secondParams.put("sid",sid);
        secondParams.put("start",pageIndexSecond+"");
        OkHttpUtils.post(AppUrl.EVERYDAY_URL).params(secondParams).execute(new MyJsonCallBack<BillTestSecondBean>() {

            @Override
            public void onResponse(BillTestSecondBean billTestSecondBean) {
                if (billTestSecondBean != null && billTestSecondBean.getCode() == 2000) {
                    dataSecondList = billTestSecondBean.getData();
                    billSecondAdapter = new BillSecondAdapter(HistoryRecordActivity.this, dataSecondList, sid);
                    recycler.setAdapter(billSecondAdapter);
                    billSecondAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });


    }



    //加载更多数据
    private void refreshMore() {
        secondParams.clear();
        pageIndexSecond+=5;
        secondParams.put("sid",sid);
        secondParams.put("start",pageIndexSecond+"");
        OkHttpUtils.post(AppUrl.EVERYDAY_URL).params(secondParams).execute(new MyJsonCallBack<BillTestSecondBean>() {

            @Override
            public void onResponse(BillTestSecondBean billTestSecondBean) {
                if(billTestSecondBean!=null && billTestSecondBean.getCode()==2000){
                    if(billTestSecondBean.getData().size()==0){

                    }else{
                        dataSecondList.addAll(billTestSecondBean.getData());
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

    @OnClick({R.id.rl_back, R.id.title_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.title_text:
                break;
        }
    }
}
