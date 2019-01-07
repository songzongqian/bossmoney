package com.byx.xiuboss.xiuboss.Mvp.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectSuccessActivity extends BaseActivity {


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
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.btn_bill)
    Button btnBill;
    private String moneyCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectsuccess);
        ButterKnife.bind(this);
        moneyCount = getIntent().getStringExtra("moneyCount");
        initView();
    }

    private void initView() {
        titleText.setText("扫码收款");
        tvMoney.setText(moneyCount);


    }

    @OnClick({R.id.rl_back, R.id.btn_bill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_bill:
                Intent intent= new Intent(CollectSuccessActivity.this,ReceivablesActivity.class);
                startActivity(intent);
                break;
        }
    }
}
