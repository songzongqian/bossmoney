package com.byx.xiuboss.xiuboss.Mvp.activity;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.Level;
import com.byx.xiuboss.xiuboss.Mvp.adapter.LevelAdapter;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.GetHeaderPwd;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.JsonCallBack;
import com.lzy.okhttputils.model.RequestHeaders;
import com.lzy.okhttputils.model.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class TipsActivity extends BaseActivity {
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
    @BindView(R.id.reward_rules)
    ImageView rewardRules;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.tv_left_bottom)
    TextView tvLeftBottom;
    @BindView(R.id.tv_title_bottom)
    TextView tvTitleBottom;
    @BindView(R.id.tv_right_bottom)
    TextView tvRightBottom;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @BindView(R.id.lv)
    ListView lv;

    private List<Level.DataBean> mLevelList = new ArrayList<>();
    private LevelAdapter levelAdapter;
    Map<String,String> headerMap=new HashMap<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        ButterKnife.bind(this);
        setStatusBar(true);
        initView();
        initAdapter();
        initData();
    }



    private void initAdapter() {
        levelAdapter = new LevelAdapter(mLevelList, this);
        lv.setAdapter(levelAdapter);
    }

    private void initData() {
        String sid = SPUtils.getInstance(this).getString("sid");

        if(headerMap!=null){
            headerMap.clear();
        }

        String timeFlag = GetHeaderPwd.getTimeFlag();
        headerMap.put("sid",sid);
        headerMap.put("source","android");
        headerMap.put("t",timeFlag);

        String[] array={"sid","source","t"};
        String md5 = GetHeaderPwd.getMd5(headerMap,array,timeFlag);

        RequestHeaders headers=new RequestHeaders();
        headers.put("sign",md5);
        headers.put("appid","148");


        RequestParams params = new RequestParams();
        params.put("source", "android");
        params.put("sid", sid);
        params.put("t", timeFlag);


        OkHttpUtils.post(AppUrl.RETURNCASH_URL).params(params).headers(headers).execute(new JsonCallBack<Level>() {
            @Override
            public void onResponse(Level level) {
                cancelDialog();
                if (level.getCode() == 2000) {
                    mLevelList.addAll(level.getData());
                    levelAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
            }
        });
    }

    private void initView() {
        titleText.setText("奖励规则");
        rlSave.setVisibility(View.GONE);
        AssetManager mgr = getAssets();
//根据路径得到Typeface
        Typeface tfMao = Typeface.createFromAsset(mgr, "fonts/PingFang Regular.otf");

        Typeface tf = Typeface.createFromAsset(mgr, "fonts/Arial Black.TTF");
//设置字体
        tvLeft.setTypeface(tf);
        tvRight.setTypeface(tf);
        tvTop.setTypeface(tfMao);
        tvTitle.setTypeface(tfMao);

        tvLeftBottom.setTypeface(tf);
        tvRightBottom.setTypeface(tf);
        tvTitleBottom.setTypeface(tfMao);
        tvBottom.setTypeface(tfMao);


    }

    @OnClick({R.id.rl_back, R.id.title_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.title_text:
                break;
        }
    }
}
