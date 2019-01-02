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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.Bean.SwichBean;
import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.utils.AutoUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by wangwenjie001 on 2018/10/5.
 */

public class CollRecordeAdapter extends RecyclerView.Adapter<CollRecordeAdapter.VhHolder> {
    private List<DealBean.DataBean> mList;
    private LayoutInflater inflater;
    private Context mContext;
    private BackCashAdapter.OnItemClickListener mListener;

    public CollRecordeAdapter(List<DealBean.DataBean> mComOrderData, Context context) {
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

    public void setOnItemClickListener(BackCashAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, DealBean.DataBean categorysBean);

    }
}
