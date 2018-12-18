package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Mvp.adapter.TipsAdapter;
import com.byx.xiuboss.xiuboss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        ButterKnife.bind(this);
        titleText.setText("奖励规则");
        initAdapter();
    }

    private void initAdapter() {
        TipsAdapter adapter = new TipsAdapter(grddeList, moneyList, rewardList, TipsActivity.this);
        lv.setAdapter(adapter);
    }




    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}
