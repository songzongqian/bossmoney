package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Mvp.activity.businessinfo.ContactActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.businessinfo.StoreAddressActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.businessinfo.StoreNameActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.businessinfo.StorePhotoActivity;
import com.byx.xiuboss.xiuboss.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BussinessActivity extends BaseActivity {
    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.wechat_showpup)
    ImageView wechatShowpup;
    @BindView(R.id.tv_storeName)
    TextView tvStoreName;
    @BindView(R.id.tv_storeNameContent)
    TextView tvStoreNameContent;
    @BindView(R.id.button_image_one)
    ImageView buttonImageOne;
    @BindView(R.id.rl_storeName)
    RelativeLayout rlStoreName;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_contactContent)
    TextView tvContactContent;
    @BindView(R.id.button_image_two)
    ImageView buttonImageTwo;
    @BindView(R.id.rl_contact)
    RelativeLayout rlContact;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_addressContent)
    TextView tvAddressContent;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.tv_photo)
    TextView tvPhoto;
    @BindView(R.id.tv_photoContent)
    TextView tvPhotoContent;
    @BindView(R.id.rl_photo)
    RelativeLayout rlPhoto;
    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.tv_workContent)
    TextView tvWorkContent;
    @BindView(R.id.rl_workTime)
    RelativeLayout rlWorkTime;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.tv_categoryContent)
    TextView tvCategoryContent;
    @BindView(R.id.rl_category)
    RelativeLayout rlCategory;
    @BindView(R.id.tv_cashBack)
    TextView tvCashBack;
    @BindView(R.id.tv_cashBackContent)
    TextView tvCashBackContent;
    @BindView(R.id.rl_cashBack)
    RelativeLayout rlCashBack;
    @BindView(R.id.post_btn)
    Button postBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessinfo);
        ButterKnife.bind(this);
        titleText.setText("商家信息");
    }

    @OnClick({R.id.title_back_image, R.id.rl_storeName, R.id.rl_contact, R.id.rl_address, R.id.rl_photo, R.id.rl_workTime, R.id.rl_category, R.id.rl_cashBack, R.id.post_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_image:
              finish();
                break;
            case R.id.rl_storeName:
                //修改店铺名称
                Intent intent= new Intent(BussinessActivity.this, StoreNameActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_contact:
                //修改联系方式
                Intent intent1= new Intent(BussinessActivity.this, ContactActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_address:
                //修改门店地址
                Intent intent2= new Intent(BussinessActivity.this, StoreAddressActivity.class);
                startActivity(intent2);

                break;
            case R.id.rl_photo:
                //上传证件照
                Intent intent3= new Intent(BussinessActivity.this, StorePhotoActivity.class);
                startActivity(intent3);

                break;
            case R.id.rl_workTime:
                //营业时间
                break;
            case R.id.rl_category:
                //经营品类
                break;
            case R.id.rl_cashBack:
                //返现比例
                break;
            case R.id.post_btn:
                //提交审核
                break;
        }
    }
}
