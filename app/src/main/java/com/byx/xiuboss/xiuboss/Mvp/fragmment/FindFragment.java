package com.byx.xiuboss.xiuboss.Mvp.fragmment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.FindBean;
import com.byx.xiuboss.xiuboss.Mvp.activity.HistoryRecordActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.LoginActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.PayCodeActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.ReceivablesActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.FindAdapter;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.NetUrl.MyJsonCallBack;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.GetHeaderPwd;
import com.byx.xiuboss.xiuboss.Utils.NetUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.RequestHeaders;
import com.lzy.okhttputils.model.RequestParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class FindFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_history)
    RelativeLayout rlHistory;
    @BindView(R.id.rl_scan)
    RelativeLayout rlScan;
    @BindView(R.id.rl_payCode)
    RelativeLayout rlPayCode;
    Unbinder unbinder;
    @BindView(R.id.iv_internet)
    ImageView ivInternet;
    @BindView(R.id.tv_internet)
    TextView tvInternet;
    @BindView(R.id.rl_empty_internet)
    RelativeLayout rlEmptyInternet;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Map<String,String> headerMap = new HashMap<>();
    private String sid;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_finder, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        initData();
        return inflate;
    }


    private void initView() {
        tvTitle.setText("发现");
        line.setAlpha(0.1f);
        if (NetUtils.isConnected(getActivity())) {
            rlEmptyInternet.setVisibility(View.GONE);
        } else {
            rlEmptyInternet.setVisibility(View.VISIBLE);
        }
    }


    private void initData() {
        //设置RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layoutManager);
        if (NetUtils.isConnected(getActivity())) {
            rlEmptyInternet.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
            SharedPreferences share = getActivity().getSharedPreferences("login_sucess", MODE_PRIVATE);
            sid = share.getString("sid", "");




            RequestParams requestParams = new RequestParams();
            requestParams.put("source", "android");
            requestParams.put("sid", sid);
            OkHttpUtils.post(AppUrl.FIND_URL).params(requestParams).execute(new MyJsonCallBack<FindBean>() {

                @Override
                public void onResponse(FindBean findBean) {
                    if (findBean != null && findBean.getCode() == 2000) {
                        List<FindBean.DataBean> dataList = findBean.getData();
                        if (dataList != null && dataList.size() > 0) {
                            FindAdapter findAdapter = new FindAdapter(dataList, getActivity());
                            recycler.setAdapter(findAdapter);
                        }

                    } else if(findBean.getCode()==4001) {
                        Intent intent=new Intent(getActivity(),LoginActivity.class);
                        startActivity(intent);
                    }

                }

                @Override
                public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                    super.onError(call, response, e);
                    recycler.setVisibility(View.GONE);
                    rlEmptyInternet.setVisibility(View.VISIBLE);
                    tvInternet.setText("服务器错误，请稍后重试");
                    rlEmptyInternet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initData();
                        }
                    });


                }
            });

        } else {
            recycler.setVisibility(View.GONE);
            rlEmptyInternet.setVisibility(View.VISIBLE);
            tvInternet.setText("怎么木有网络了呢~~");
            rlEmptyInternet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initData();
                }
            });

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_history, R.id.rl_scan, R.id.rl_payCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_history:
                Intent historyIntent = new Intent(getActivity(), HistoryRecordActivity.class);
                startActivity(historyIntent);
                break;
            case R.id.rl_scan:
                //扫码收款
                Intent intent = new Intent(getActivity(), ReceivablesActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_payCode:
                //收款码
                Intent intent3 = new Intent(getActivity(), PayCodeActivity.class);
                intent3.putExtra("sid", sid);
                startActivity(intent3);
                break;
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //Fragment失去焦点
        } else {
            initData();
        }
    }
}
