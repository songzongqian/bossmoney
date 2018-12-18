package com.byx.xiuboss.xiuboss.Mvp.activity.businessinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Mvp.activity.BaseActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.BussinessActivity;
import com.byx.xiuboss.xiuboss.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StorePhotoActivity extends BaseActivity {
    @BindView(R.id.title_back_image)
    ImageView titleBackImage;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.rl_finish)
    RelativeLayout rlFinish;
    @BindView(R.id.title_top)
    TextView titleTop;
    @BindView(R.id.iv_storeHead)
    ImageView ivStoreHead;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.title_bottom)
    TextView titleBottom;
    @BindView(R.id.storeId)
    TextView storeId;
    @BindView(R.id.iv_store)
    ImageView ivStore;
    @BindView(R.id.peopleId)
    TextView peopleId;
    @BindView(R.id.iv_people)
    ImageView ivPeople;
    @BindView(R.id.iv_businessLicense)
    ImageView ivBusinessLicense;
    @BindView(R.id.ll_two)
    LinearLayout llTwo;
    @BindView(R.id.rl_one)
    RelativeLayout rlOne;
    @BindView(R.id.iv_frontFace)
    ImageView ivFrontFace;
    @BindView(R.id.ll_three)
    LinearLayout llThree;
    @BindView(R.id.iv_behindFace)
    ImageView ivBehindFace;
    @BindView(R.id.ll_four)
    LinearLayout llFour;
    @BindView(R.id.rl_license)
    RelativeLayout rlLicense;
    @BindView(R.id.rl_person)
    RelativeLayout rlPerson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.title_back_image, R.id.rl_finish, R.id.ll_one, R.id.ll_two, R.id.ll_three, R.id.ll_four,R.id.rl_license,R.id.rl_person})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_image:
                finish();
                break;
            case R.id.rl_finish:
                //点击完成按钮
                Intent intent = new Intent(StorePhotoActivity.this, BussinessActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_one:
                PictureSelector.create(StorePhotoActivity.this).openGallery(PictureMimeType.ofImage()).maxSelectNum(1)
                        .selectionMode(PictureConfig.SINGLE).previewImage(true).compress(true).forResult(100);
                break;
            case R.id.ll_two:
                PictureSelector.create(StorePhotoActivity.this).openGallery(PictureMimeType.ofImage()).maxSelectNum(1)
                        .selectionMode(PictureConfig.SINGLE).previewImage(true).compress(true).forResult(200);
                break;
            case R.id.ll_three:
                PictureSelector.create(StorePhotoActivity.this).openGallery(PictureMimeType.ofImage()).maxSelectNum(1)
                        .selectionMode(PictureConfig.SINGLE).previewImage(true).compress(true).forResult(300);
                break;
            case R.id.ll_four:
                PictureSelector.create(StorePhotoActivity.this).openGallery(PictureMimeType.ofImage()).maxSelectNum(1)
                        .selectionMode(PictureConfig.SINGLE).previewImage(true).compress(true).forResult(400);
                break;

            case R.id.rl_license:
                //点击上传营业执照
                ivStore.setBackgroundResource(R.mipmap.my_icon_selected);
                ivPeople.setBackgroundResource(R.mipmap.my_icon_unselected);
                llTwo.setVisibility(View.VISIBLE);
                llThree.setVisibility(View.GONE);
                llFour.setVisibility(View.GONE);
                break;
            case R.id.rl_person:
                //点击上传身份证
                ivPeople.setBackgroundResource(R.mipmap.my_icon_selected);
                ivStore.setBackgroundResource(R.mipmap.my_icon_unselected);
                llTwo.setVisibility(View.GONE);
                llThree.setVisibility(View.VISIBLE);
                llFour.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    String storeHeadPath = selectList.get(0).getCompressPath();
                    Uri uri = Uri.fromFile(new File(storeHeadPath));
                    ivStoreHead.setImageURI(uri);
                    break;
                case 200:
                    List<LocalMedia> linceList = PictureSelector.obtainMultipleResult(data);
                    String BusinessLicense = linceList.get(0).getCompressPath();
                    Uri licenseUri = Uri.fromFile(new File(BusinessLicense));
                    ivBusinessLicense.setImageURI(licenseUri);
                    break;
                case 300:
                    List<LocalMedia> frontList = PictureSelector.obtainMultipleResult(data);
                    String frontPath = frontList.get(0).getCompressPath();
                    Uri frontUri = Uri.fromFile(new File(frontPath));
                    ivFrontFace.setImageURI(frontUri);
                    break;
                case 400:
                    List<LocalMedia> behindList = PictureSelector.obtainMultipleResult(data);
                    String behindPath = behindList.get(0).getCompressPath();
                    Uri behindUri = Uri.fromFile(new File(behindPath));
                    ivBehindFace.setImageURI(behindUri);
                    break;

            }

        }
    }


}
