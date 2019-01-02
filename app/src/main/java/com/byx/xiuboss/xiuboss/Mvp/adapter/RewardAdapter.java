package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.R;

import java.util.List;

/**
 * Created by wangwenjie001 on 2018/10/5.
 */

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.VhHolder> {
    private List<DealBean.DataBean> mList;
    private LayoutInflater inflater;
    private Context mContext;
    private BackCashAdapter.OnItemClickListener mListener;

    public RewardAdapter(List<DealBean.DataBean> mComOrderData, Context context) {
        this.mList = mComOrderData;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public VhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.reward_item, parent, false);
        VhHolder holder = new VhHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VhHolder holder, int position) {

        setChildInitData(holder, mList.get(position), position);
        setOnClickListener(holder, mList.get(position), position);
    }

    private void setOnClickListener(VhHolder holder, final DealBean.DataBean dataBean, final int position) {

        holder.itemView.setOnClickListener(view -> {
           // mListener.onItemClick(position, dataBean);
        });

    }


    private void setChildInitData(VhHolder cVh, DealBean.DataBean dataBean, int position) {
        if (dataBean == null) return;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VhHolder extends RecyclerView.ViewHolder {
        TextView mBackMoney;
        TextView mTitle;
        TextView mAddress;
        TextView mInvite;

        public VhHolder(View childView) {
            super(childView);
            mBackMoney = childView.findViewById(R.id.mBackMoney);
            mTitle = childView.findViewById(R.id.mTitle);
            mAddress = childView.findViewById(R.id.mAddress);
            mInvite = childView.findViewById(R.id.mInvite);
        }
    }

    public void setOnItemClickListener(BackCashAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, DealBean.DataBean categorysBean);

    }
}
