package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.Level;
import com.byx.xiuboss.xiuboss.Mvp.adapter.LevelAdapter;
import com.byx.xiuboss.xiuboss.Mvp.adapter.TipsAdapter;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.JsonCallBack;
import com.lzy.okhttputils.model.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class TipsActivity extends BaseActivity {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.textView37)
    TextView textView37;
    private List<Level.DataBean> mLevelList = new ArrayList<>();

    private String[] grddeList = {"Lv1", "Lv2", "Lv3", "Lv4", "Lv5", "Lv6", "Lv7",
            "Lv8", "Lv9", "Lv10", "Lv11", "Lv12", "Lv13", "Lv14",
            "Lv15", "Lv16", "Lv17", "Lv18", "Lv19", "Lv20", "Lv21", "Lv22"};
    private String[] moneyList = {"200元", "500元", "1000元", "1500元", "2000元",
            "3000元", "5000元", "7500元", "10000元", "12500元", "15000元",
            "20000元", "25000元", "30000元", "40000元", "50000元", "60000元",
            "70000元", "80000元", "90000元", "100000元", "150000元"};
    private String[] rewardList = {"0.3元", "0.46元", "0.96元", "1.50元", "2.06元",
            "3.18元", "5.45元", "8.40元", "11.50元", "14.88元", "18.30元",
            "25.00元", "32.25元", "39.90元", "54.80元", "70.50元", "87.00元",
            "102.90元", "118.40元", "134.10元", "150.00元", "229.50元"};
    private LevelAdapter levelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        ButterKnife.bind(this);
        titleText.setText("奖励规则");
        initAdapter();
        initData();
    }

    private void initData() {
        showDialog();
        getEmptyView().setOnClickListener(v -> {
            initData();
        });
        requestStep();

    }

    private void initAdapter() {
        //TipsAdapter adapter = new TipsAdapter(grddeList, moneyList, rewardList, TipsActivity.this);
        //lv.setAdapter(adapter);
        levelAdapter = new LevelAdapter(mLevelList, this);
        lv.setAdapter(levelAdapter);

    }

    public void requestStep() {

        String sid = SPUtils.getInstance(this).getString("sid");
        RequestParams params = new RequestParams();
        params.put("source", "android");
        params.put("sid", sid);
        params.put("debug", "1");


        OkHttpUtils.post(AppUrl.RETURNCASH_URL).params(params).execute(new JsonCallBack<Level>() {
            @Override
            public void onResponse(Level level) {
                cancelDialog();
                if (level.getCode() == 2000) {
                    mLevelList.addAll(level.getData());
                    levelAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
                cancelDialog();
            }
        });

    }


    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}
