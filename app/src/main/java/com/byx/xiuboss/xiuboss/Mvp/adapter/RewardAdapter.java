package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.RewardInfo;
import com.byx.xiuboss.xiuboss.R;

import java.util.List;

/**
 * Created by wangwenjie001 on 2018/10/5.
 */

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.VhHolder> {
    private List<RewardInfo.DataBean.ShareTasksBean> mList;
    private LayoutInflater inflater;
    private Context mContext;
    private RewardAdapter.OnItemClickListener mListener;

    public RewardAdapter(List<RewardInfo.DataBean.ShareTasksBean> mRewardList, Context context) {
        this.mList = mRewardList;
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
        RewardInfo.DataBean.ShareTasksBean stBean = mList.get(position);
        holder.mBackMoney.setText(stBean.getReturnCash());
        holder.mTitle.setText(stBean.getStoreName());
        holder.mAddress.setText(stBean.getStoreAddress());

        setOnClickListener(holder, mList.get(position), position);
    }

    private void setOnClickListener(VhHolder holder, final RewardInfo.DataBean.ShareTasksBean dataBean, final int position) {

        holder.itemView.setOnClickListener(view -> {
           // mListener.onItemClick(position, dataBean);
        });

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

    public void setOnItemClickListener(RewardAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, RewardInfo.DataBean.ShareTasksBean rdst);

    }
}
