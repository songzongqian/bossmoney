package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.SwichBean;
import com.byx.xiuboss.xiuboss.Mvp.adapter.SwichAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class SwichActivity extends BaseActivity {

    private RelativeLayout mHeadBack;
    private TextView titleText;
    private RecyclerView swichRecycler;
    private SwichAdapter adapter;
    private List<SwichBean.DataBean> data;
    private String id_dian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swich);
        setStatusBar(true);
        initView();
        initData();
    }

    private void initData() {
        requestStoreData();
    }

    private void requestStoreData() {

        SharedPreferences login_sucess = getSharedPreferences("login_sucess", MODE_PRIVATE);
        String id = login_sucess.getString("id", "");
        Map<String,String> tags= new HashMap<>();
        tags.put("id",id);

        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.SWITCH_SHOP_URL, tags, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                Gson gson=new Gson();
                SwichBean swichBean = gson.fromJson(json, SwichBean.class);
                data = swichBean.getData();
                adapter = new SwichAdapter(id_dian,data,SwichActivity.this,SwichActivity.this);
                swichRecycler.setAdapter(adapter);
                adapter.setListener(new SwichAdapter.onListener(){
                    @Override
                    public void OnListener(int i){
                        finish();
                        overridePendingTransition(R.anim.bottom_silent,R.anim.bottom_out);
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });

    }

    private void initView() {
        id_dian = getIntent().getStringExtra("id");
        mHeadBack = findViewById(R.id.head_back);
        titleText = findViewById(R.id.head_title);
        titleText.setText("切换店铺");
        swichRecycler = (RecyclerView) findViewById(R.id.swich_recycler);

        mHeadBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.bottom_silent,R.anim.bottom_out);
            }
        });
        swichRecycler.setLayoutManager(new LinearLayoutManager(this));
    }


}
