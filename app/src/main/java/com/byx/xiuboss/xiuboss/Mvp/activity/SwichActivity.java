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
import com.lzy.okhttputils.callback.JsonCallBack;
import com.lzy.okhttputils.model.RequestParams;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class SwichActivity extends BaseActivity {

    private ImageView mHeadBack;
    private TextView titleText;
    private RecyclerView swichRecycler;
    private SwichAdapter adapter;
    private List<SwichBean.DataBean> data;
    private String id_dian;
    private List<SwichBean.DataBean> mSwichList = new ArrayList<>();

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
        //Map<String,String> params= new HashMap<>();
        RequestParams params = new RequestParams();
        params.put("id", id);

        com.lzy.okhttputils.OkHttpUtils.post(AppUrl.SWITCH_SHOP_URL).params(params).execute(new JsonCallBack<String>() {
            @Override
            public void onResponse(String json) {
                Gson gson = new Gson();
                SwichBean swichBean = gson.fromJson(json, SwichBean.class);
                if (swichBean.getCode() == 2000) {
                    mSwichList.clear();
                    mSwichList.addAll(swichBean.getData());
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void initView() {
        id_dian = getIntent().getStringExtra("id");
        mHeadBack = findViewById(R.id.mCloseIcon);
        titleText = findViewById(R.id.head_title);
        titleText.setText("切换店铺");
        swichRecycler = (RecyclerView) findViewById(R.id.swich_recycler);

        mHeadBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.bottom_silent, R.anim.bottom_out);
            }
        });
        swichRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SwichAdapter(id_dian, mSwichList, SwichActivity.this, SwichActivity.this);
        swichRecycler.setAdapter(adapter);
        adapter.setListener((position, dataBean) -> {
            for (int i = 0; i < mSwichList.size(); i++) {
                if (i == position) {
                    mSwichList.get(i).setSelect(true);
                } else {
                    mSwichList.get(i).setSelect(false);
                }
            }
            adapter.notifyDataSetChanged();

            SharedPreferences share = getSharedPreferences("login_sucess", MODE_PRIVATE);
            SharedPreferences.Editor edit = share.edit();
            edit.putString("sid", dataBean.getId());
            /**
             * 添加修改店铺手机号码字段
             */
            String telephone = dataBean.getTelephone();
            edit.putString("payMobile", telephone);
            edit.putString("homeTitle", dataBean.getTitle());
            System.out.println("切换后存储手机号" + telephone);
            edit.commit();
            EventBus.getDefault().post(dataBean.getId());
            setResult(RESULT_OK);
            finish();
            overridePendingTransition(R.anim.bottom_silent, R.anim.bottom_out);
        });

    }


}
