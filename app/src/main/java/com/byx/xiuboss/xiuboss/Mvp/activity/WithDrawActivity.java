package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.MyBalanceBean;
import com.byx.xiuboss.xiuboss.Mvp.adapter.BalanceAdapter;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.GetHeaderPwd;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestHeaders;
import com.lzy.okhttputils.model.RequestParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class WithDrawActivity extends BaseActivity {
    /**
     * 新版提现记录的activity
     */
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
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private int page=1;
    private String sid;
    private BalanceAdapter adapter;
    private RequestParams requestParams;
    private List<MyBalanceBean.DataBean> lance;
    Map<String,String> headerMap=new HashMap<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        setStatusBar(true);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        titleText.setText("提现记录");
        rlSave.setVisibility(View.GONE);
        //获取Sid
        SharedPreferences share = getSharedPreferences("login_sucess", MODE_PRIVATE);
        sid = share.getString("sid", "");
        //设置RecyclerView 样式
        recycler.setLayoutManager(new LinearLayoutManager(WithDrawActivity.this));
        //刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener(){

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                adapter.notifyDataSetChanged();
                smartRefreshLayout.finishRefresh();
            }
        });
        //加载更多
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
               // refreshMore();
                smartRefreshLayout.finishLoadmore();
            }
        });
    }

    private void refreshMore() {
        requestParams.clear();
        page+=1;
        requestParams.put("sid",sid);
        requestParams.put("startPos",page+"");
        requestParams.put("source","android");
        requestParams.put("step",10+"");
        requestParams.put("debug",1+"");
        OkHttpUtils.post(AppUrl.CASHRECORD_URL).params(requestParams).execute(new MyJsonCallBack<MyBalanceBean>() {

            @Override
            public void onResponse(MyBalanceBean myBalanceBean) {
                if(myBalanceBean!=null && myBalanceBean.getCode()==2000){
                    if(myBalanceBean.getData()!=null &&myBalanceBean.getData().size()==0){
                        Toast.makeText(WithDrawActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }else{
                        lance.addAll( myBalanceBean.getData());
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e){
                super.onError(call, response, e);
            }
        });


    }

    private void initData() {
        if(headerMap!=null){
            headerMap.clear();
        }

        String timeFlag = GetHeaderPwd.getTimeFlag();
        headerMap.put("sid",sid);
        headerMap.put("startPos",page+"");
        headerMap.put("source","android");
        headerMap.put("step",10+"");
        headerMap.put("t",timeFlag);

        String[] array={"sid","startPos","source","step","t"};
        String md5 = GetHeaderPwd.getMd5(headerMap, array,timeFlag);

        RequestHeaders headers=new RequestHeaders();
        headers.put("sign",md5);
        headers.put("appid","148");
        requestParams = new RequestParams();
        requestParams.put("sid",sid);
        requestParams.put("startPos",page+"");
        requestParams.put("source","android");
        requestParams.put("step",10+"");
        requestParams.put("t",timeFlag);

        OkHttpUtils.post(AppUrl.CASHRECORD_URL).params(requestParams).headers(headers).execute(new MyJsonCallBack<MyBalanceBean>() {
            @Override
            public void onResponse(MyBalanceBean myBalanceBean) {
                if(myBalanceBean!=null && myBalanceBean.getCode()==2000){
                    lance = myBalanceBean.getData();
                    adapter = new BalanceAdapter(lance,WithDrawActivity.this);
                    recycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                super.onError(call, response, e);
                e.printStackTrace();
            }
        });


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
