package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.MyBalanceBean;
import com.byx.xiuboss.xiuboss.Mvp.activity.AccountActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.CompletedActivity;
import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwenjie001 on 2018/10/4.
 */

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.ViewHolder> {
        private List<MyBalanceBean.DataBeanX.DataBean> lance=new ArrayList<>();
        private Context context;
    private onListener listener;
    public BalanceAdapter(List<MyBalanceBean.DataBeanX.DataBean> lance, Context context) {
        this.lance = lance;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.balance_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int i = Integer.parseInt(lance.get(position).getStatus());
        String channel = lance.get(position).getChannel();
        MyBalanceBean.DataBeanX.DataBean dataBean = lance.get(position);



        if (channel.equals("支付宝")){
            holder.putforward.setText("提现 - 支付宝");
            holder.money.setText("-"+lance.get(position).getGet_fee());
            holder.money.setTextColor(0xFF666666);
        }else if (channel.equals("微信")){
            holder.putforward.setText("提现 - 微信");
            holder.money.setText("-"+lance.get(position).getGet_fee());
            holder.money.setTextColor(0xFF666666);
        }else if (channel.equals("银行卡")){
            holder.putforward.setText("提现 - 银行卡");
            holder.money.setText("-"+lance.get(position).getGet_fee());
            holder.money.setTextColor(0xFF666666);
        }else{
            holder.putforward.setText("收入 - "+lance.get(position).getChannel());
            holder.money.setText("+"+lance.get(position).getGet_fee());
            holder.money.setTextColor(0xFFC6A04D);
        }

        holder.time.setText(lance.get(position).getRegisterTime());

       /*0 签到   1  审核通过 2 审核中 3 提现撤销 */
        if (i==0){
            holder.status.setText("已到账");
            holder.status.setTextColor(0xFF999999);
        }else if (i==1){
            //已到账跳转页面
            holder.status.setText("已到账");
            holder.status.setTextColor(0xFF999999);
            holder.balance_relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, CompletedActivity.class);
                    intent.putExtra("completed",lance.get(position));
                    context.startActivity(intent);
                }
            });
        }else if (i==2){
            holder.status.setText("处理中");
            holder.status.setTextColor(0xFF999999);
            //未到账跳转页面
            holder.balance_relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, AccountActivity.class);
                    intent.putExtra("account",lance.get(position));
                    context.startActivity(intent);
                }
            });
        }else if (i==3){
            holder.status.setText("提现被撤销");
            holder.status.setTextColor(0xFFFE2741);
        }


    }

    @Override
    public int getItemCount() {
        return lance.isEmpty()?0:lance.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView putforward,time,money,status;
        private final RelativeLayout balance_relative;

        public ViewHolder(View itemView) {
            super(itemView);
             AutoUtils.autoSize(itemView);
             putforward = itemView.findViewById(R.id.Putforward);
             time = itemView.findViewById(R.id.time);
             money = itemView.findViewById(R.id.money);
             status = itemView.findViewById(R.id.status);
             balance_relative = itemView.findViewById(R.id.balance_relative);
        }
    }
    public interface  onListener{
        void OnListener(int i);
    }
    public void setListener( onListener listener) {
        this.listener = listener;
    }




}
