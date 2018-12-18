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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactActivity extends BaseActivity {
    /**
     * 商家联系方式
     */
    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.rl_finish)
    RelativeLayout rlFinish;
    @BindView(R.id.et_storeName)
    ClearWriteEditText etContact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        etContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if(etContact.getText().length()==0){
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

    @OnClick({R.id.title_back_image, R.id.rl_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_image:
                finish();
                break;
            case R.id.rl_finish:
                //点击完成按钮
                String contactContent=etContact.getText().toString().trim();
                if(contactContent.isEmpty()){
                    rlFinish.setEnabled(false);
                }else{
                    Intent intent=new Intent(ContactActivity.this, BussinessActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
