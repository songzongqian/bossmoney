package com.byx.xiuboss.xiuboss.Mvp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.CancelMode;
import com.byx.xiuboss.xiuboss.Bean.ConfimOrderBean;
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.Mvp.adapter.CancelAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.RewritePopwindow;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


public class CancelOrderActivity extends BaseActivity {

    private ArrayList<CancelMode>mList = new ArrayList<>();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.cancel)
    RelativeLayout cancel;
    @BindView(R.id.mBack)
    ImageView mBack;
    private CancelAdapter adapter;
    private String id;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        uid = extras.getString("uid");

        initData();
        initView();
    }

    private void initData() {
        CancelMode mode1 = new CancelMode("用户信息不符",true);
        CancelMode mode2 = new CancelMode("商品已经售完",false);
        CancelMode mode3 = new CancelMode("商家已经打烊",false);
        CancelMode mode4 = new CancelMode("超出配送范围",false);
        CancelMode mode5 = new CancelMode("商家现在太忙",false);
        CancelMode mode6 = new CancelMode("用户申请取消",false);
        CancelMode mode7 = new CancelMode("配送出现问题",false);
        CancelMode mode8 = new CancelMode("不满足起送要求",false);
        mList.add(mode1);
        mList.add(mode2);
        mList.add(mode3);
        mList.add(mode4);
        mList.add(mode5);
        mList.add(mode6);
        mList.add(mode7);
        mList.add(mode8);
    }

    private void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        adapter = new CancelAdapter(mList,this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CancelAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, CancelMode mode) {
                for(int i=0;i<mList.size();i++){
                    if (mList.get(i).equals(mode)){
                        mode.setSelect(true);
                    }else{
                        mList.get(i).setSelect(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        cancel.setOnClickListener(v -> {
            String reason = null;
            for(int i=0;i<mList.size();i++){
                if (mList.get(i).isSelect()){
                    reason = mList.get(i).getTitle();
                    break;
                }
            }
            showPopup(reason);
        });
        mBack.setOnClickListener(view->{
            finish();
        });
    }

    /*弹出PopupWindow*/
    private void showPopup(String reason){
        View pView = LayoutInflater.from(this).inflate(R.layout.popup_cancledialgo_item,null);
        final RewritePopwindow rPop = new RewritePopwindow(this,pView);
        TextView cancelView = pView.findViewById(R.id.order_cancel);
        TextView confimView = pView.findViewById(R.id.order_confim);
        rPop.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER,0,0);
        cancelView.setOnClickListener(view ->{
            rPop.dismiss();
        } );
        confimView.setOnClickListener(view -> {
            rPop.dismiss();
            cancelOrder(reason);
        });

    }

    /*请求取消订单*/
    public void cancelOrder(String content){

        SharedPreferences share = this.getSharedPreferences("login_sucess", this.MODE_PRIVATE);
        String sid = share.getString("sid", "");

        Map<String,String> params = new HashMap<>();
        params.put("sid",sid);
        params.put("id",id);
        params.put("uid",uid);
        params.put("sefund_content",content);
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.ORDER_CANCEL, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                ConfimOrderBean orderBean = gson.fromJson(json, ConfimOrderBean.class);
                if (orderBean!=null && orderBean.getCode().equals("2000")){
                    //isRefreshData = true;
                    //initData(1,0);
                    ToastUtil.shortToast(CancelOrderActivity.this,"订单已取消");
                    onActivityFinish();
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                ToastUtil.shortToast(CancelOrderActivity.this,"取消订单失败");
            }
        });
    }
    public void onActivityFinish(){
        setResult(0x111);
        finish();
    }
}
