package com.byx.xiuboss.xiuboss.Mvp.fragmment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.ConfimOrderBean;
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.Jgim.utils.ToastUtil;
import com.byx.xiuboss.xiuboss.Mvp.activity.CancelOrderActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.OrderActivity;
import com.byx.xiuboss.xiuboss.Mvp.adapter.ComfimAdapter;
import com.byx.xiuboss.xiuboss.Mvp.adapter.OAdapter;
import com.byx.xiuboss.xiuboss.Mvp.net.OkHttpUtils;
import com.byx.xiuboss.xiuboss.NetUrl.AppUrl;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DateTimeUtils;
import com.byx.xiuboss.xiuboss.Utils.RewritePopwindow;
import com.byx.xiuboss.xiuboss.Utils.SPUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.autolayout.AutoLinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.iwgang.countdownview.CountdownView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * 待确认 重构
 */
@SuppressLint("ValidFragment")
public class WaitConfimFragment extends OrderBaseFragment {


    private int position;
    private String title;
    private View mView;
    private boolean isRefreshData = true;

    SmartRefreshLayout refreshLayout;
    RelativeLayout empty;

    private LayoutInflater inflater;
    private ArrayList<DealBean.DataBean> mList = new ArrayList<>();
    private int start = 0;

    private TextView childUniacid;
    private TextView child_diviceTime;
    private TextView child_status;
    private TextView child_PayTime;
    private CircleImageView child_UserIcon;
    private TextView child_UserName;
    private TextView child_userSex;
    private ImageView order_phone;
    private ImageView order_message;
    private TextView child_deliverPrice;
    private TextView child_finalNoce;
    //TextView child_sendUserName;
    private AutoLinearLayout orderLayout;
    private AutoLinearLayout confimOrder;
    //ImageView child_sendPhone;
    private TextView child_tipic;

    private CountdownView countDownView;
    private TextView cancelOrder;
    private AutoLinearLayout auto_layout;
    private AutoLinearLayout mConfimLayout;

    public static WaitConfimFragment getConfimFragment(int position, String title) {
        WaitConfimFragment fragment = new WaitConfimFragment();
        Bundle bundle = new Bundle(position);
        bundle.putInt("position", position);
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
            title = bundle.getString("title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = LayoutInflater.from(getActivity());
        mView = inflater.inflate(R.layout.wait_confim, container, false);
        initView();
        isPrepared = true;
        whetherLazyLoad();
        return mView;
    }

    private void initView() {
        empty = mView.findViewById(R.id.empty);
        auto_layout = mView.findViewById(R.id.auto_layout);
        childUniacid = mView.findViewById(R.id.child_uniacid);
        child_diviceTime = mView.findViewById(R.id.child_diviceTime);
        child_status = mView.findViewById(R.id.child_status);
        child_PayTime = mView.findViewById(R.id.child_PayTime);
        child_UserIcon = mView.findViewById(R.id.child_UserIcon);
        child_UserName = mView.findViewById(R.id.child_UserName);
        child_userSex = mView.findViewById(R.id.child_userSex);
        order_phone = mView.findViewById(R.id.order_phone);
        order_message = mView.findViewById(R.id.order_message);
        child_deliverPrice = mView.findViewById(R.id.child_deliverPrice);
        child_finalNoce = mView.findViewById(R.id.child_finalNoce);
        orderLayout = mView.findViewById(R.id.order_layout);
        confimOrder = mView.findViewById(R.id.confim_order);
        cancelOrder = mView.findViewById(R.id.cancel_order);
        countDownView = mView.findViewById(R.id.countDownView);
        child_tipic = mView.findViewById(R.id.child_tipic);
        mConfimLayout = mView.findViewById(R.id.bottomConfimLayout);
        refreshLayout = mView.findViewById(R.id.smartRefreshLayout);

        mConfimLayout.setVisibility(View.GONE);
        setEmptyIsHind(false);
        setOnClick();

    }


    private void setOnClick() {
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                start += 20;
                isRefreshData = false;
                initData(1, 0);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isRefreshData = true;
                start = 0;
                initData(1, 0);
                refreshLayout.finishRefresh();
            }
        });
    }

    /*弹出PopupWindow*/
    private void showPopup(int type, final DealBean.DataBean categorysBean) {
        View pView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_order_item, null);
        final RewritePopwindow rPop = new RewritePopwindow(getActivity(), pView);
        TextView cancelView = pView.findViewById(R.id.order_cancel);
        TextView confimView = pView.findViewById(R.id.order_confim);

        rPop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rPop.dismiss();
            }
        });
        confimView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rPop.dismiss();
                confimOrder(type, categorysBean);
            }
        });

    }

    /*请求确认订单*/
    public void confimOrder(int type, DealBean.DataBean categorysBean) {
        //Toast.makeText(getActivity(),"你点击了确认订单按钮",Toast.LENGTH_SHORT).show();

        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");

        Map<String, String> params = new HashMap<>();
        params.put("status", "1");
        params.put("delivery_status", "0");
        params.put("id", categorysBean.getId());
        OkHttpUtils.getInstance().postDataAsynToUi(AppUrl.ORDER_COMFIM, params, new OkHttpUtils.UserNetCall() {
            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                ConfimOrderBean orderBean = gson.fromJson(json, ConfimOrderBean.class);
                if (orderBean != null && orderBean.getStatus() == 1) {
                    isRefreshData = true;
                    initData(1, 0);
                    ToastUtil.shortToast(getActivity(), "订单已接收");
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                ToastUtil.shortToast(getActivity(), "接单接收失败");
            }
        });
    }

    /*请求网络数据*/
    private void initData(final int status, int delivever_staus) {

        SharedPreferences share = getContext().getSharedPreferences("login_sucess", getContext().MODE_PRIVATE);
        String sid = share.getString("sid", "");
        String path = AppUrl.ORDER_LIST + "?sid=" + sid + "&status=" + status + "&delivery_status=" + delivever_staus + "&start=" + start;
        OkHttpUtils.getInstance().getDataAsynFromUi(path, new OkHttpUtils.UserNetCall() {

            @Override
            public void success(Call call, String json) {
                Gson gson = new Gson();
                DealBean reCoinBean = gson.fromJson(json, DealBean.class);
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                if (reCoinBean != null && reCoinBean.getData() != null) {
                    if (isRefreshData) {
                        mList.clear();
                    }
                    mList.addAll(reCoinBean.getData());
                    SPUtils.getInstance(getActivity()).put("orderNum", mList.size());
                    ((Orderragment) getParentFragment()).setBadgeNum(mList.size());
                    if (mList.size() > 0) {
                        setEmptyIsHind(true);
                        mConfimLayout.setVisibility(View.VISIBLE);
                        setChildInitData(mList.get(0));
                    } else {
                        mConfimLayout.setVisibility(View.GONE);
                        setEmptyIsHind(false);
                    }

                } else {
                    mConfimLayout.setVisibility(View.GONE);
                    if (start == 0) {
                        setEmptyIsHind(false);
                    }
                }
            }

            @Override
            public void failed(Call call, IOException e) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                setEmptyIsHind(false);
                mConfimLayout.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setChildInitData(DealBean.DataBean dataBean) {
        if (dataBean == null) return;
        childUniacid.setText("# " + dataBean.getId());
        if (dataBean.getOrder_type() != null && dataBean.getOrder_type().equals("1")) {
            child_diviceTime.setText("立即送出");
        } else {
            child_diviceTime.setText("自提订单");
        }
        if (dataBean.getStatus() != null && dataBean.getDelivery_status() != null) {
            String orderStatus = OAdapter.getOrderStatus(dataBean.getStatus(), dataBean.getDelivery_status());
            child_status.setText(orderStatus);
        }
        if (dataBean.getDelivery_time() != null) {
            String friendTime = DateTimeUtils.getFriendTime(Long.parseLong(dataBean.getAddtime()), getActivity());
            child_PayTime.setText(friendTime + "下单");//下单时间
        }
        Glide.with(getActivity()).load(dataBean.getAvatar()).into(child_UserIcon);
        child_UserName.setText(dataBean.getUsername());
        child_userSex.setText(dataBean.getSex());

         /*顾客实际支付*/
        SpannableStringBuilder esitmate = OAdapter.getActual(dataBean.getTotal_fee());
        child_finalNoce.setText(esitmate);
        /*设置预计收入*/
        SpannableStringBuilder actual = OAdapter.getEsitmate(dataBean.getStore_final_fee());
        child_deliverPrice.setText(actual);

        if (dataBean.getNote() != null) {
            child_tipic.setVisibility(View.VISIBLE);
            child_tipic.setText(dataBean.getNote());
        } else {
            child_tipic.setVisibility(View.GONE);
        }

        //child_sendUserName.setText(dataBean.getOrdersn());
        if (dataBean.getCountdowntime() != null) {
            long clerkTime = Long.parseLong(dataBean.getCountdowntime()) * 1000 - System.currentTimeMillis();
            countDownView.start(clerkTime);
        }
        /*设置完成的订单*/
        if (dataBean.getCart() != null && dataBean.getCart().size() > 0) {
            orderLayout.removeAllViews();
            List<DealBean.DataBean.CartBean> cart = dataBean.getCart();
            for (int n = 0; n < cart.size(); n++) {
                if (cart.get(n) != null && cart.get(n).getOptions() != null && cart.get(n).getOptions().size() > 0) {
                    List<DealBean.DataBean.CartBean.OptionsBean> options = cart.get(n).getOptions();
                    for (int i = 0; i < options.size(); i++) {
                        View item = inflater.inflate(R.layout.layout_order, null);
                        TextView order_title = item.findViewById(R.id.order_title);
                        TextView order_num = item.findViewById(R.id.order_num);
                        TextView order_price = item.findViewById(R.id.order_price);

                        order_title.setText(options.get(i).getTitle());
//                        order_num.setText("x" + options.get(i).getNum());
                        if (Integer.parseInt(options.get(i).getNum()) > 1) {
                            order_num.setTextColor(Color.parseColor("#F24744"));
                            order_num.setText("x" + options.get(i).getNum());
                        } else {
                            order_num.setTextColor(Color.parseColor("#000000"));
                            order_num.setText("x" + options.get(i).getNum());
                        }
                        if (options.get(i).getTitle().equals("餐盒费")) {
                            if (dataBean.getBox_price() != null && Integer.parseInt(options.get(i).getNum()) > 0) {
                                order_price.setText("￥" + dataBean.getBox_price());
                            }
                        } else {
                            if (options.get(i).getPrice() != null) {
                                order_price.setText("￥" + options.get(i).getPrice());
                            }
                        }
                        orderLayout.addView(item);
                    }
                }
            }
        }
        setOnClickListener(0, dataBean);

    }

    private void setOnClickListener(final int position, final DealBean.DataBean dataBean) {
        order_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + dataBean.getMobile());
                intent.setData(data);
                getActivity().startActivity(intent);
            }
        });
        order_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                Uri data = Uri.parse("smsto:" + dataBean.getMobile());
                sendIntent.setData(data);
                getActivity().startActivity(sendIntent);
            }
        });

        auto_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(position, dataBean);
            }
        });
        confimOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemConfimClick(position, dataBean);
            }
        });
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCancelClick(position, dataBean);
            }
        });

    }

    public void onItemConfimClick(int position, DealBean.DataBean categorysBean) {
        if (categorysBean.getOrder_type() != null && categorysBean.getOrder_type().equals("1")) {
                    /*配送订单*/
            showPopup(1, categorysBean);
        } else {
                    /*自提订单*/
            showPopup(0, categorysBean);
        }

    }

    public void onItemCancelClick(int position, DealBean.DataBean categorysBean) {
        Intent intent = new Intent(getActivity(), CancelOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", categorysBean.getId());
        bundle.putString("uid", categorysBean.getUid());
        intent.putExtras(bundle);
        startActivityForResult(intent, 0x111);
        //cancelOrder(categorysBean);
    }

    public void onItemClick(int position, DealBean.DataBean categorysBean) {
        Intent intent = new Intent(getActivity(), OrderActivity.class);
        intent.putExtra("id", categorysBean.getId());
        startActivity(intent);
    }


    public void setEmptyIsHind(boolean isHind) {
        if (isHind) {
            empty.setVisibility(View.GONE);
            auto_layout.setVisibility(View.VISIBLE);

        } else {
            empty.setVisibility(View.VISIBLE);
            auto_layout.setVisibility(View.GONE);
        }

    }

    @Override
    public void lazyLoad() {
        isRefreshData = true;
        if (title.equals("待确认")) {
            initData(1, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x111) {
            if (resultCode == 0x111) {
                isRefreshData = true;
                initData(1, 0);
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
