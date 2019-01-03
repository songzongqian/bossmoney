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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompletedActivity extends BaseActivity {

    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.wechat_showpup)
    ImageView wechatShowpup;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.textView38)
    TextView textView38;
    @BindView(R.id.Put_forward)
    TextView PutForward;
    @BindView(R.id.Account_money)
    TextView AccountMoney;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        titleText.setText("账单详情");
        MyBalanceBean.DataBean dataBean = (MyBalanceBean.DataBean) getIntent().getSerializableExtra("completed");
        PutForward.setText(dataBean.getAddtime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        long lt = new Long(dataBean.getAddtime());
        String format = simpleDateFormat.format(new Date(lt*1000));

        AccountMoney.setText("到账时间: "+format);
        Log.e("zhangdan",format);

    }

    @OnClick({R.id.title_back_image, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_image:
                finish();
                break;
            case R.id.button:
                finish();
                break;
        }
    }
}
