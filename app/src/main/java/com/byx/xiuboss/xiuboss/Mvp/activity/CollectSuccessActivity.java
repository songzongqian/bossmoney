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

public class CollectSuccessActivity extends BaseActivity {

    private ImageView btnBack;
    private TextView tvCollectMoney;
    private Button btnCheck;
    private String moneyCount;
    private TextView tvTitle;
    private Button btnCheckBill;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectsuccess);
        moneyCount = getIntent().getStringExtra("moneyCount");
        initView();
    }

    private void initView() {
        btnBack = findViewById(R.id.title_back_image);
        tvTitle = findViewById(R.id.title_text);
        tvCollectMoney = findViewById(R.id.Account_money);
        btnCheck = findViewById(R.id.button);
        btnCheckBill = findViewById(R.id.btn_bill);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvTitle.setText("收款结果");
        tvCollectMoney.setText("￥"+moneyCount);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CollectSuccessActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });

        btnCheckBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                /**跳转到第二个fragment,点击查看*/
               /* Intent intent = new Intent(CollectSuccessActivity.this, DetailsActivity.class);
                startActivity(intent);*/
            }
        });



    }
}
