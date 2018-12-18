package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.TurnoverData;
import com.byx.xiuboss.xiuboss.Bean.TurnoverTwoData;
import com.byx.xiuboss.xiuboss.Mvp.activity.CollectDetailActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.DetailsActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.TurnoverAdapter;
import com.byx.xiuboss.xiuboss.Mvp.adapter.TurnoverTwoAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillFragment extends BaseFragment {

    private ImageView titleBackImage;
    private TextView titleText;
    private TextView billText;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView recycler_two;
    private static final String url_one = "https://www.ourdaidai.com/CI/index.php/Store/todayMoney";
    private static final String url_two = "https://www.ourdaidai.com/CI/index.php/Store/storeMoneySum";
    private SharedPreferences share;
    private List<TurnoverData.DataBean> listData = new ArrayList<>();
    private List<TurnoverTwoData.DataBean> data= new ArrayList<>();
    private TurnoverAdapter mAdapter_item;
    private TurnoverTwoAdapter mAdapter_item_two;
    private View footView;
    private String sid;
    private int pageIndex = 0;
    private TurnoverData data1;
    private View inflate;
    private boolean isGetData = false;
    private Map<String, String> params;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_bill, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }


    private void initData() {
        System.out.println("initData"+"执行了");
        if(listData!=null){
            listData.clear();
        }

        if(data!=null){
            data.clear();
        }
        share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        sid = share.getString("sid", "");
        mAdapter_item_two = new TurnoverTwoAdapter(getActivity(), data);
        recycler_two.setAdapter(mAdapter_item_two);
        obtainDataOne();
        obtainDataThree();

        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if(mAdapter_item!=null){
                    mAdapter_item.notifyDataSetChanged();
                }
                refreshLayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
                obtainDataTwo();
                refreshLayout.finishLoadmore();
            }
        });

        mAdapter_item_two.setListener(new TurnoverTwoAdapter.onListener() {
            @Override
            public void OnListener(int i) {
                Intent intent = new Intent(getContext(), CollectDetailActivity.class);
                Bundle bundle= new Bundle();
                bundle.putString("sid",sid);
                bundle.putString("stat_day",data.get(i).getStat_day());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }

    private void initView(View inflate) {
        titleBackImage = (ImageView) inflate.findViewById(R.id.title_back_image);
        titleText = (TextView) inflate.findViewById(R.id.title_text);
        billText = (TextView) inflate.findViewById(R.id.bill_text);
        refreshLayout = (SmartRefreshLayout) inflate.findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_wang);
        footView = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_foot_item, null);
        recycler_two = footView.findViewById(R.id.recycler_two);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recycler_two.setLayoutManager(new LinearLayoutManager(getActivity()));
        titleBackImage.setVisibility(View.GONE);
        titleText.setText("账单");
    }

    private void obtainDataOne() {
        //请求列表一的数据
        Map<String, String> params = new HashMap<>();
        params.put("sid", sid);
        params.put("start", pageIndex +"");
        OkHttpUtils.getInstance().postDataAsynToNet(AppUrl.TodayMoney_URL, params, new OkHttpUtils.MyNetCall() {
            @Override
            public void success(Call call, final Response response) throws IOException {
                final String message = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("第一个接口的请求数据", message);
                        Gson gson = new Gson();
                        data1 = gson.fromJson(message, TurnoverData.class);
                        if (data1.getMessage().equals("没有营业额")){
                            billText.setVisibility(View.VISIBLE);
                        }else{
                            billText.setVisibility(View.GONE);
                            listData = data1.getData();
                        }

                        // mAdapter_item.setData(listData);
                        mAdapter_item = new TurnoverAdapter(getActivity(), listData,data1);
                        recyclerView.setAdapter(mAdapter_item);
                        mAdapter_item.addFooterView(footView);

                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    public void obtainDataTwo() {
        //第二个列表加载更多请求
        params.clear();
        pageIndex+=1;
        params.put("sid", sid);
        params.put("start", pageIndex+""+5);
        OkHttpUtils.getInstance().postDataAsynToNet(AppUrl.EVERYDAY_URL, params, new OkHttpUtils.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.e("----shuju-----", string);
                Gson gson = new Gson();
                final TurnoverTwoData turnoverTwoData = gson.fromJson(string, TurnoverTwoData.class);
                final List<TurnoverTwoData.DataBean> list = turnoverTwoData.getData();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (turnoverTwoData.getMessage().equals("没有数据")){

                        } else {
                            data.addAll(list);
                            mAdapter_item_two.setData(data);
                            mAdapter_item_two.notifyDataSetChanged();
                        }
                    }
                });

            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    private void obtainDataThree() {
        //请求列表二的数据
        params = new HashMap<>();
        params.put("sid", sid);
        params.put("page",pageIndex+"");
        OkHttpUtils.getInstance().postDataAsynToNet(AppUrl.EVERYDAY_URL, params, new OkHttpUtils.MyNetCall() {
            @Override
            public void success(Call call, final Response response) throws IOException {
                final String message = response.body().string();
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        Log.e("第二个接口的请求数据", message);
                        Gson gson = new Gson();
                        TurnoverTwoData turnoverTwoData = gson.fromJson(message, TurnoverTwoData.class);
                        data = turnoverTwoData.getData();
                        mAdapter_item_two.setData(data);
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){

        }else{
            initData();
        }
    }
}
