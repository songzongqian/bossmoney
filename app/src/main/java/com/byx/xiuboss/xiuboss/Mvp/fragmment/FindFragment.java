package com.byx.xiuboss.xiuboss.Mvp.fragmment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Mvp.activity.PayCodeActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.ReceivablesActivity;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.NetUtils;
import com.byx.xiuboss.xiuboss.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_finder, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_history, R.id.rl_scan, R.id.rl_payCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_history:
                break;
            case R.id.rl_scan:
                Intent intent = new Intent(getActivity(), ReceivablesActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.rl_payCode:
                Intent intent3 = new Intent(getActivity(), PayCodeActivity.class);
                intent3.putExtra("sid", "111");
                startActivity(intent3);
                break;
        }
    }
}
