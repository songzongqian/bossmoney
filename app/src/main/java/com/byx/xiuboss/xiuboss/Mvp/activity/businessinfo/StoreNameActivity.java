package com.byx.xiuboss.xiuboss.Mvp.activity.businessinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Mvp.activity.BaseActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.BussinessActivity;
import com.byx.xiuboss.xiuboss.Mvp.view.ClearWriteEditText;
import com.byx.xiuboss.xiuboss.R;
import butterknife.ButterKnife;


public class StoreNameActivity extends BaseActivity implements View.OnClickListener{

    private ImageView btnBack;
    private TextView titleName;
    private RelativeLayout rlFinish;
    private TextView tvFinish;
    private ClearWriteEditText editText;
    private String storeContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storename);
        initView();
        //获取原始值
    }

    private void initView() {
        btnBack = findViewById(R.id.title_back_image);
        titleName = findViewById(R.id.title_text);
        rlFinish = findViewById(R.id.rl_finish);
        tvFinish = findViewById(R.id.tv_finish);
        editText = findViewById(R.id.et_storeName);
        btnBack.setOnClickListener(this);
        rlFinish.setOnClickListener(this);
        titleName.setText("修改店铺名称");

        //检测文字变化
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if(editText.getText().length()==0){
                    tvFinish.setTextColor(getResources().getColor(R.color.textColor_editor));
                }else{
                    tvFinish.setTextColor(getResources().getColor(R.color.textColor_finish));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_back_image:
                finish();
                break;
            case R.id.rl_finish:
                //点击完成
                storeContent = editText.getText().toString().trim();
                if(storeContent.isEmpty()){
                    rlFinish.setEnabled(false);
                }else{
                    Intent intent=new Intent(StoreNameActivity.this, BussinessActivity.class);
                    startActivity(intent);
                }
                break;
        }

    }
}
