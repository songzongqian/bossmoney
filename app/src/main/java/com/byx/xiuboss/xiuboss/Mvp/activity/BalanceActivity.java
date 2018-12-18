package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.MyBalanceBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.SharePreferenceManager;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BalanceAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class BalanceActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 账户余额页面
     */

    private TextView balance;
    private Button buttonBalance;
    private RecyclerView recycler;
    private ImageView backBtn;
    private SmartRefreshLayout smartRefreshLayout;
    private SharedPreferences share;
    private String sid;
    private Map<String,String> map=new HashMap<>();
    private List<MyBalanceBean.DataBeanX.DataBean> lance;
    private int page=1;
    private BalanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        initView();
        initData();
    }
    private void initData() {
        lance= new ArrayList<>();
        map.put("sid",sid);
        map.put("page",page+"");
        OkHttpUtils.getInstance().postDataAsynToNet(AppUrl.ACCOUNT_BALANCE, map, new OkHttpUtils.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("balance",string);
                getData(string);
            }
            @Override
            public void failed(Call call, IOException e) {


            }
        });
    }

    private void initView() {
        balance = (TextView) findViewById(R.id.balance);
        buttonBalance = (Button) findViewById(R.id.button_balance);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        backBtn = (ImageView) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);

        buttonBalance.setOnClickListener(this);
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout);
        //获取Sid
        share = getSharedPreferences("login_sucess", MODE_PRIVATE);
        sid = share.getString("sid", "");
        //设置RecyclerView 样式
        recycler.setLayoutManager(new LinearLayoutManager(BalanceActivity.this));
        //刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener(){

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                adapter.notifyDataSetChanged();
                smartRefreshLayout.finishRefresh();
            }
        });
        //加载更多
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
                obtainDataTwo();
                smartRefreshLayout.finishLoadmore();
            }
        });
    }
    private void getData(String s){
        Gson gson=new Gson();
        final MyBalanceBean myBalanceBean = gson.fromJson(s, MyBalanceBean.class);
        lance = myBalanceBean.getData().getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                balance.setText(myBalanceBean.getData().getAmount());
                adapter = new BalanceAdapter(lance,BalanceActivity.this);
                recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void obtainDataTwo(){
        //加载更多请求
        map.clear();
        page+=1;
        map.put("sid",sid);
        map.put("page",page+"");
        OkHttpUtils.getInstance().postDataAsynToNet(AppUrl.ACCOUNT_BALANCE, map, new OkHttpUtils.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        MyBalanceBean myBalanceBean = gson.fromJson(string, MyBalanceBean.class);
                        if (myBalanceBean.getData().getData()==null || myBalanceBean.getData().getData().size()==0){
                            Toast.makeText(BalanceActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                        }else{
                            lance.addAll( myBalanceBean.getData().getData());
                            adapter.notifyDataSetChanged();
                        }
                    }
                });


            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.button_balance:
                Intent intent=new Intent(this,BalanceWebActivity.class);
                startActivity(intent);
                break;
        }
    }
}
