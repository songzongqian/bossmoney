package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Mvp.adapter.CollRecordeAdapter;
import com.byx.xiuboss.xiuboss.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollRecordeActivity extends BaseActivity {

    @BindView(R.id.head_back)
    RelativeLayout mHeadBack;
    @BindView(R.id.head_title)
    TextView mHeadTitle;
    @BindView(R.id.head_save)
    RelativeLayout mHeadSave;
    @BindView(R.id.mIcon)
    ImageView mIcon;
    @BindView(R.id.mName)
    TextView mName;
    @BindView(R.id.mSpeedMoney)
    TextView mSpeedMoney;
    @BindView(R.id.mBackMoney)
    TextView mBackMoney;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coll_recorde);
        ButterKnife.bind(this);
        setStatusBar(true);
        initView();
        initData();
    }

    private void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);

        CollRecordeAdapter recordeAdapter = new CollRecordeAdapter(null,this);
        mRecyclerView.setAdapter(recordeAdapter);

    }

    private void initData() {

    }
}
