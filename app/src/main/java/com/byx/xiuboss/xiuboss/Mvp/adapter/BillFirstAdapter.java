package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.byx.xiuboss.xiuboss.Bean.BillTestFirstBean;
import com.byx.xiuboss.xiuboss.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BillFirstAdapter extends RecyclerView.Adapter {
    List<BillTestFirstBean.DataBean> list;
    Context context;

    public BillFirstAdapter(FragmentActivity activity, List<BillTestFirstBean.DataBean> dataFirst) {
        this.list=dataFirst;
        this.context=activity;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.bill_first_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        BillTestFirstBean.DataBean dataBean = list.get(position);

        if(dataBean.getNickname()!=null){
            if(dataBean.getNickname().equals("微信用户")){
                Glide.with(context).load(R.mipmap.common_wx_logo).into(myViewHolder.headImage);
            }else if(dataBean.getNickname().equals("支付宝用户")){
                Glide.with(context).load(R.mipmap.common_ali_logo).into(myViewHolder.headImage);

            }else{
                RequestOptions requestOptions=new RequestOptions();
                requestOptions.placeholder(R.mipmap.icons).error(R.mipmap.icons);
                Glide.with(context).load(dataBean.getAvatar()).apply(requestOptions).into(myViewHolder.headImage);
            }
        }else{
            Glide.with(context).load(R.mipmap.icons).into(myViewHolder.headImage);

        }


        myViewHolder.payMoney.setText("+" + dataBean.getPrice());
        myViewHolder.nickName.setText(dataBean.getNickname());
        myViewHolder.payTime.setText(dataBean.getAddtime());
        myViewHolder.paySource.setText("已返现"+dataBean.getMoney_total());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private final CircleImageView headImage;
        private final TextView nickName;
        private final TextView payTime;
        private final TextView payMoney;
        private final TextView paySource;

        public MyViewHolder(View itemView) {
            super(itemView);
            headImage = itemView.findViewById(R.id.image_head_item_one);
            nickName = itemView.findViewById(R.id.nickName);
            payTime = itemView.findViewById(R.id.payTime);
            payMoney = itemView.findViewById(R.id.payMoney);
            paySource = itemView.findViewById(R.id.moneySource);
        }
    }
}
