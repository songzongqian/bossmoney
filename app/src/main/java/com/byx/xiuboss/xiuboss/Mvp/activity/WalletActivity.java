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

public class WalletActivity extends BaseActivity {
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
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.button_balance)
    Button buttonBalance;
    @BindView(R.id.rl_tiXian)
    RelativeLayout rlTiXian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        setStatusBar(true);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initData() {
        String amount = getIntent().getStringExtra("amount");
        tvBalance.setText(amount);
    }

    private void initView() {
        titleText.setText("钱包");
        rlSave.setVisibility(View.GONE);


    }

    @OnClick({R.id.rl_back, R.id.button_balance, R.id.rl_tiXian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.button_balance:
                Intent intent=new Intent(this,BalanceWebActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_tiXian:
                //提现记录
                Intent intent1=new Intent(WalletActivity.this,WithDrawActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
