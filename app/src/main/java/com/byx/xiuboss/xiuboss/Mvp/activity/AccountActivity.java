package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.MyBalanceBean;
import com.byx.xiuboss.xiuboss.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends BaseActivity {


    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.examine_putforward)
    TextView examinePutforward;
    @BindView(R.id.examine_Accountmoney)
    TextView examineAccountmoney;
    @BindView(R.id.examine_button)
    Button examineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        titleText.setText("账单详情");
        MyBalanceBean.DataBeanX.DataBean dataBean = (MyBalanceBean.DataBeanX.DataBean) getIntent().getSerializableExtra("account");
        examinePutforward.setText(dataBean.getRegisterTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        long lt = new Long(dataBean.getAddtime());
        String format = simpleDateFormat.format(new Date(lt * 1000));

        examineAccountmoney.setText("预计到账时间: " + format);
        Log.e("zhangdan", format);
    }

    @OnClick({R.id.title_back_image, R.id.examine_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_image:
                finish();
                break;
            case R.id.examine_button:
                finish();
                break;
        }
    }
}
