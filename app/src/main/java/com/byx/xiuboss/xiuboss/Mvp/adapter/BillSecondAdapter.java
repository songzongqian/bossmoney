package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byx.xiuboss.xiuboss.Bean.BillTestSecondBean;
import com.byx.xiuboss.xiuboss.Bean.TurnoverTwoData;
import com.byx.xiuboss.xiuboss.Mvp.activity.CollectDetailActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.SettlementActivity;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DateTimeUtils;
import com.byx.xiuboss.xiuboss.Utils.DatetoLong;

import java.util.List;



public class BillSecondAdapter extends RecyclerView.Adapter {
    List<BillTestSecondBean.DataBean> secondList;
    Context context;
    private String statDay;
    String mySid;




    public BillSecondAdapter(FragmentActivity activity, List<BillTestSecondBean.DataBean> dataSecond, String sid) {
        this.context=activity;
        this.secondList=dataSecond;
        this.mySid=sid;
    }



    public void setData(List<BillTestSecondBean.DataBean>  data) {
        this.secondList = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bill_second_item, null);
        return new SecondViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SecondViewHolder secondViewHolder= (SecondViewHolder) holder;
        BillTestSecondBean.DataBean dataBean = secondList.get(position);

        statDay = dataBean.getStat_day();
        if(!TextUtils.isEmpty(statDay) ){
            //String aLong = DatetoLong.getLong(statDay);
            String finalTime = DateTimeUtils.judteTime(statDay, context);
            secondViewHolder.tvDate.setText(finalTime);
            String month = statDay.substring(4, 6);
            String day = statDay.substring(6, 8);
            Log.e("month",month);
            Log.e("day",day);
            secondViewHolder.textDescribe.setText("收款"+dataBean.getSum()+"笔");
            secondViewHolder.moneyTotal.setText("￥"+dataBean.getPrice());
        }else{

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(context, CollectDetailActivity.class);
                Intent intent = new Intent(context, SettlementActivity.class);
                Bundle bundle= new Bundle();
                bundle.putString("sid",mySid);
                bundle.putString("stat_day",secondList.get(position).getStat_day());
                System.out.println("adapter中的日期"+statDay);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return secondList.size();
    }

    class  SecondViewHolder extends RecyclerView.ViewHolder{

        private final TextView textDescribe;
        private final TextView moneyTotal;
        private final RelativeLayout relativeLayout;
        private final TextView tvDate;

        public SecondViewHolder(View itemView) {
            super(itemView);
            textDescribe = itemView.findViewById(R.id.text_describe);
            moneyTotal = itemView.findViewById(R.id.moneyCount);
            relativeLayout = itemView.findViewById(R.id.recycler_wwj);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }



}
