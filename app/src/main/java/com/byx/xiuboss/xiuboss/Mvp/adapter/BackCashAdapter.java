package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.Bean.StoreInfo;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DateTimeUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by night_slight on 2018/11/9.
 */

public class BackCashAdapter extends RecyclerView.Adapter<BackCashAdapter.VhHolder> {

    private List<StoreInfo.DataBean.OrderListBean> mList;
    private LayoutInflater inflater;
    private Context mContext;
    private OnItemClickListener mListener;

    public BackCashAdapter(List<StoreInfo.DataBean.OrderListBean> mComOrderData, Context context) {
        this.mList = mComOrderData;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public VhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.back_cash_item, parent, false);
        VhHolder holder = new VhHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VhHolder holder, int position) {

        StoreInfo.DataBean.OrderListBean orderListBean = mList.get(position);
        Glide.with(mContext).load(orderListBean.getCustomerAvatar()).into(holder.mIcon);
        holder.mTitle.setText(orderListBean.getNickName());
        holder.mTime.setText(orderListBean.getDatetime());
        holder.mCash.setText(orderListBean.getTotal());
        holder.mBack.setText(orderListBean.getReturnCash());

        setOnClickListener(holder, mList.get(position), position);
    }

    private void setOnClickListener(VhHolder holder, final StoreInfo.DataBean.OrderListBean dataBean, final int position) {

        holder.itemView.setOnClickListener(view->{
            mListener.onItemClick(position, dataBean);
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VhHolder extends RecyclerView.ViewHolder {
        ImageView mIcon;
        TextView mTitle;
        TextView mTime;
        TextView mCash;
        TextView mBack;



        public VhHolder(View childView) {
            super(childView);
            mIcon = childView.findViewById(R.id.mIcon);
            mTitle = childView.findViewById(R.id.mTitle);
            mTime = childView.findViewById(R.id.mTime);
            mCash = childView.findViewById(R.id.mCash);
            mBack = childView.findViewById(R.id.mBack);

        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, StoreInfo.DataBean.OrderListBean sorb);

    }
}
