package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.byx.xiuboss.xiuboss.Bean.XiangQingBean;
import com.byx.xiuboss.xiuboss.Mvp.activity.CollectDetailActivity;
import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:created by ${szq}
 * Time: 2018/11/23 11:05
 */
public class CollectDetailAdapter extends RecyclerView.Adapter {
     Context context;
    List<XiangQingBean.DataBean> beanList;


    public CollectDetailAdapter(CollectDetailActivity collectDetailActivity, List<XiangQingBean.DataBean> dataList) {
        this.context=collectDetailActivity;
        this.beanList=dataList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView= LayoutInflater.from(context).inflate(R.layout.bill_everyday_item, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        XiangQingBean.DataBean dataBean = beanList.get(position);
        String nickname = dataBean.getNickname();
        if(nickname!=null){
            if(dataBean.getNickname().equals("微信用户")){
                Glide.with(context).load(R.mipmap.common_wx_logo).into(myViewHolder.headView);
            }else if(dataBean.getNickname().equals("支付宝用户")){
                Glide.with(context).load(R.mipmap.common_ali_logo).into(myViewHolder.headView);

            }else{
                RequestOptions requestOptions=new RequestOptions();
                requestOptions.placeholder(R.mipmap.icons).error(R.mipmap.icons);
                Glide.with(context).load(dataBean.getAvatar()).apply(requestOptions).into(myViewHolder.headView);
            }
        }else{
            Glide.with(context).load(R.mipmap.icons).into(myViewHolder.headView);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long( Long.valueOf(dataBean.getAddtime())*1000);
        Date date = new Date(lt);
        String format = simpleDateFormat.format(date);
        myViewHolder.nickName.setText(dataBean.getNickname());
        myViewHolder.time.setText(format);

        myViewHolder.tvSource.setText("已返现"+dataBean.getMoney_total());

        if (dataBean.getPrice().indexOf(".")!=-1){
            myViewHolder.money.setText("+"+dataBean.getPrice());
        }else{
            myViewHolder.money.setText("+"+dataBean.getPrice()+".00");
        }
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final CircleImageView headView;
        private final TextView nickName;
        private final TextView time;
        private final TextView money;
        private final TextView tvSource;

        public MyViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            headView = itemView.findViewById(R.id.image_head_item_one);
            nickName = itemView.findViewById(R.id.nickName);
            time = itemView.findViewById(R.id.payTime);
            money = itemView.findViewById(R.id.payMoney);
            tvSource = itemView.findViewById(R.id.moneySource);
        }
    }
}
