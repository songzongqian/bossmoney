package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.CancelMode;
import com.byx.xiuboss.xiuboss.Bean.ReCoinBean;
import com.byx.xiuboss.xiuboss.Bean.ResumeBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.Mvp.adapter.CancelAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class RecoinResonActivity extends BaseActivity {

    private ArrayList<CancelMode> mList = new ArrayList<>();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.commit)
    RelativeLayout commit;
    @BindView(R.id.mBack)
    ImageView mBack;
    @BindView(R.id.input_layout)
    AutoLinearLayout inputLayout;
    @BindView(R.id.input_resume)
    EditText inputText;
    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;
    private CancelAdapter adapter;
    private String id;
    private String resultReson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoin_reson);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        initData();
        initView();
        inputLayout.setVisibility(View.GONE);
    }

    private void initData() {
        CancelMode mode1 = new CancelMode("已和用户电话沟通", true);
        CancelMode mode2 = new CancelMode("菜单做好正在配送", false);
        CancelMode mode3 = new CancelMode("质量服务没有问题", false);
        CancelMode mode4 = new CancelMode("用户已收餐", false);
        CancelMode mode5 = new CancelMode("其他", false);

        mList.add(mode1);
        mList.add(mode2);
        mList.add(mode3);
        mList.add(mode4);
        mList.add(mode5);
    }

    private void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        adapter = new CancelAdapter(mList, this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CancelAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, CancelMode mode) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).equals(mode)) {
                        mode.setSelect(true);
                    } else {
                        mList.get(i).setSelect(false);
                    }
                }
                if (position == mList.size() - 1) {
                    inputLayout.setVisibility(View.VISIBLE);

                } else {
                    inputLayout.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
            }
        });

        inputText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int height = DensityUtil.dp2px(500);
                mScrollView.smoothScrollTo(0, height);

                return false;
            }
        });

        setOnClick();
    }

    private void setOnClick() {
        commit.setOnClickListener(v -> {
            if (inputLayout.getVisibility() == View.VISIBLE) {
                resultReson = inputText.getText().toString();
            }
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).isSelect()) {
                    if (i == mList.size()-1){
                        break;
                    }else{
                        resultReson = mList.get(i).getTitle();
                    }
                }
            }
            /*执行提交操作*/
            requestReson(id,resultReson);
        });
        mBack.setOnClickListener(view -> {
            finish();
        });

    }

    private void requestReson(String id,String reason) {
        SharedPreferences share = this.getSharedPreferences("login_sucess", this.MODE_PRIVATE);
        String sid = share.getString("sid", "");

        Map<String,String>params = new HashMap<>();
        params.put("sid",sid);
        params.put("id",id);
        params.put("type","refund");
        params.put("content",reason);

        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.ORDER_CMRR, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                ResumeBean resumeBean = gson.fromJson(json, ResumeBean.class);
                if (resumeBean.getCode() == 2000){
                    ToastUtil.shortToast(RecoinResonActivity.this,"订单已拒绝");
                    setResult(0x111);
                    finish();
                }
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });

    }
}
