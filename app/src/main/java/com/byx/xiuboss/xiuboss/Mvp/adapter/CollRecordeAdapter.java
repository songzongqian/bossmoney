package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.ReceipeInfo;
import com.byx.xiuboss.xiuboss.R;

import java.util.List;

/**
 * Created by wangwenjie001 on 2018/10/5.
 */

public class CollRecordeAdapter extends RecyclerView.Adapter<CollRecordeAdapter.VhHolder> {
    private List<ReceipeInfo.DataBean.OrderListBean> mList;
    private LayoutInflater inflater;
    private Context mContext;
    private CollRecordeAdapter.OnItemClickListener mListener;

    public CollRecordeAdapter(List<ReceipeInfo.DataBean.OrderListBean> mComOrderData, Context context) {
        this.mList = mComOrderData;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public VhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.collrecorder_item, parent, false);
        VhHolder holder = new VhHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VhHolder holder, int position) {

        ReceipeInfo.DataBean.OrderListBean orderListBean = mList.get(position);
        holder.mTitle.setText(orderListBean.getInfo());
        holder.mTime.setText(orderListBean.getDatetime());
        holder.mCash.setText("￥ " + orderListBean.getTotal());
        holder.mBack.setText("返现￥" + orderListBean.getReturnCash());

        setOnClickListener(holder, orderListBean, position);
    }

    private void setOnClickListener(VhHolder holder, final ReceipeInfo.DataBean.OrderListBean dataBean, final int position) {

        holder.itemView.setOnClickListener(view -> {
            // mListener.onItemClick(position, dataBean);
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VhHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mTime;
        TextView mCash;
        TextView mBack;

        public VhHolder(View childView) {
            super(childView);
            mTitle = childView.findViewById(R.id.mTitle);
            mTime = childView.findViewById(R.id.mTime);
            mCash = childView.findViewById(R.id.mCash);
            mBack = childView.findViewById(R.id.mBack);
        }
    }

    public void setOnItemClickListener(CollRecordeAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, ReceipeInfo.DataBean.OrderListBean orderBean);

    }
}
