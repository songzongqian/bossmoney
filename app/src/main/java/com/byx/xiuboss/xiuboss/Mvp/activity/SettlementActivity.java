package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.SettleInfo;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.Constant;
import com.byx.xiuboss.xiuboss.Utils.RewritePopwindow;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.JsonCallBack;
import com.lzy.okhttputils.model.RequestParams;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettlementActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout mBack;
    @BindView(R.id.title_text)
    TextView mHeadTitle;
    @BindView(R.id.mTotalMoney)
    TextView mTotalMoney;
    @BindView(R.id.mBackMoney)
    TextView mBackMoney;
    @BindView(R.id.mResultMoney)
    TextView mResultMoney;
    @BindView(R.id.mReceiptLayout)
    AutoLinearLayout mReceiptLayout;
    @BindView(R.id.mConstact)
    TextView mConstact;
    private String dateTime;
    private String mobile;
    private RewritePopwindow mConstantPopupWindow;
    private TextView mPopupMobile;
    private TextView mPopupCancel;
    private TextView mPopupConfim;
    private String mySid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        ButterKnife.bind(this);
        setStatusBar(true);
        initView();
        initData();
    }

    private void initView() {
        mHeadTitle.setText("结算详情");
        Bundle bundle = getIntent().getExtras();
        mySid = bundle.getString("sid");
        //20181120
        dateTime = bundle.getString("stat_day");

        mBack.setOnClickListener(v -> {
            finish();
        });

        mConstact.setOnClickListener(v -> {
            initSharePopupWindow();
        });

        mReceiptLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettRecordActivity.class);
            intent.putExtra("dateTime", dateTime);
            startActivity(intent);
        });

    }

    private void initData() {
        showDialog();
        getEmptyView().setOnClickListener(v -> {
            initData();
        });
        requestSetAccounts();
    }

    public void requestSetAccounts() {
        //String sid = SPUtils.getInstance(this).getString(Constant.SID);
        RequestParams params = new RequestParams();
        params.put("source", "android");
        params.put("sid",mySid );
        params.put("date", dateTime);
        OkHttpUtils.post(AppUrl.RESULTSETTLE_URL).params(params).execute(new JsonCallBack<SettleInfo>() {
            @Override
            public void onResponse(SettleInfo settleInfo) {
                cancelDialog();
                if (settleInfo.getCode() == 2000) {
                    SettleInfo.DataBean data = settleInfo.getData();
                    setSettleInfo(data);
                }
            }
        });

    }

    public void setSettleInfo(SettleInfo.DataBean settleInfo) {
        mobile = settleInfo.getMobile();
        mTotalMoney.setText("￥ " + (TextUtils.isEmpty(settleInfo.getTotalIncome()) ? "0" : settleInfo.getTotalIncome()));
        mBackMoney.setText("-￥ " + (TextUtils.isEmpty(settleInfo.getReturnOrderTotal()) ? "0" : settleInfo.getReturnOrderTotal()));
        mResultMoney.setText("￥ " + (TextUtils.isEmpty(settleInfo.getActualIncome()) ? "0" : settleInfo.getActualIncome()));

    }

    private void initSharePopupWindow() {
        if (mConstantPopupWindow == null) {
            View mSharePopupView = LayoutInflater.from(this).inflate(R.layout.constant_mobile, null, false);
            mConstantPopupWindow = new RewritePopwindow(this, mSharePopupView);
            mPopupMobile = mSharePopupView.findViewById(R.id.mMobile);
            mPopupCancel = mSharePopupView.findViewById(R.id.mCancel);
            mPopupConfim = mSharePopupView.findViewById(R.id.mConfim);
        }
        mPopupMobile.setText("Tel - " + mobile);

        mPopupConfim.setOnClickListener(v -> {
            if (mConstantPopupWindow != null) {
                mConstantPopupWindow.dismiss();
            }
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + mobile);
            intent.setData(data);
            startActivity(intent);
        });

        mPopupCancel.setOnClickListener(v -> {
            if (mConstantPopupWindow != null) {
                mConstantPopupWindow.dismiss();
            }
        });
        if (mConstantPopupWindow != null) {
            View decorView = getWindow().getDecorView();
            mConstantPopupWindow.showAtLocation(decorView, Gravity.CENTER, 0, 0);
        }

    }
}
