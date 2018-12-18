package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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

    private ImageView titleBackImage;
    private TextView titleText;
    private RecyclerView swichRecycler;
    private SwichAdapter adapter;
    private List<SwichBean.DataBean> data;
    private String id_dian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swich);
        initView();
        initData();
    }

    private void initData() {
        SharedPreferences login_sucess = getSharedPreferences("login_sucess", MODE_PRIVATE);
        String id = login_sucess.getString("id", "");
        Map<String,String> tags= new HashMap<>();
        tags.put("id",id);
        OkHttpUtils.getInstance().postDataAsynToNet(AppUrl.SWITCH_SHOP_URL, tags, new OkHttpUtils.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                SwichBean swichBean = gson.fromJson(string, SwichBean.class);
                data = swichBean.getData();
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        adapter = new SwichAdapter(id_dian,data,SwichActivity.this,SwichActivity.this);
                        swichRecycler.setAdapter(adapter);
                        adapter.setListener(new SwichAdapter.onListener(){
                            @Override
                            public void OnListener(int i){
                                finish();
                            }
                        });
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
        titleBackImage = (ImageView) findViewById(R.id.title_back_image);
        titleText = (TextView) findViewById(R.id.title_text);
        titleText.setText("切换店铺");
        swichRecycler = (RecyclerView) findViewById(R.id.swich_recycler);

        titleBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swichRecycler.setLayoutManager(new LinearLayoutManager(this));
    }


}
