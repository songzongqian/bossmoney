package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.CancelMode;
import com.byx.xiuboss.xiuboss.R;

import java.util.ArrayList;

/**
 * Created by night_slight on 2018/11/19.
 */

public class CancelAdapter extends RecyclerView.Adapter<CancelAdapter.CancelHolder> {

    private ArrayList<CancelMode>mList;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickListener mListener;

    public CancelAdapter(ArrayList<CancelMode> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    @Override
    public CancelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cancelresume_item,parent,false);
        CancelHolder holder = new CancelHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CancelHolder holder, final int position) {
        final CancelMode cancelMode = mList.get(position);
        holder.selectIcon.setSelected(cancelMode.isSelect());
        holder.tipView.setText(cancelMode.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.itemClick(position,cancelMode);
                }
            }
        });
    }



    class CancelHolder extends RecyclerView.ViewHolder {
        ImageView selectIcon;
        TextView tipView;
        public CancelHolder(View itemView) {
            super(itemView);
            selectIcon = itemView.findViewById(R.id.select_icon);
            tipView = itemView.findViewById(R.id.select_result);
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public interface OnItemClickListener{
        void itemClick(int position,CancelMode mode);
    }
}
