package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.byx.xiuboss.xiuboss.Bean.ToDayBean;
import com.byx.xiuboss.xiuboss.Bean.TurnoverTwoData;
import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;



public class ToDayAdapter extends RecyclerView.Adapter<ToDayAdapter.ViewHoder> {
    private List<ToDayBean.DataBean> toDayList;
    private Context mContext;

    public ToDayAdapter(List<ToDayBean.DataBean> toDayList, Context mContext) {
        this.toDayList = toDayList;
        this.mContext = mContext;
    }
    public void setData(List<ToDayBean.DataBean> toDayList) {
        this.toDayList = toDayList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.today_item, null);

        return new ViewHoder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
        if(toDayList.get(position).getNickname()!=null){
            if (toDayList.get(position).getNickname().equals("微信用户")){
                Glide.with(mContext).load(R.mipmap.common_wx_logo).into(holder.image_item_one);
            }else if (toDayList.get(position).getNickname().equals("支付宝用户")){
                Glide.with(mContext).load(R.mipmap.common_ali_logo).into(holder.image_item_one);
            }else{
                RequestOptions requestOptions=new RequestOptions();
                requestOptions.placeholder(R.mipmap.icons).error(R.mipmap.icons);
                Glide.with(mContext).load(toDayList.get(position).getAvatar()).apply(requestOptions).into(holder.image_item_one);
            }
        }else{
            Glide.with(mContext).load(R.mipmap.icons).into(holder.image_item_one);
        }


        holder.text_name.setText(toDayList.get(position).getNickname());
        String addtime = toDayList.get(position).getAddtime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Long aLong = Long.valueOf(addtime);
        long lt = new Long(aLong*1000);
        Date date = new Date(lt);
        holder.text_time.setText(simpleDateFormat.format(date));
        holder.text_money.setText("+"+toDayList.get(position).getPrice());
        String money_total = toDayList.get(position).getMoney_total();
        holder.text_money_item_wwj.setText("已返现"+money_total);
    }

    @Override
    public int getItemCount() {
        return toDayList.isEmpty() ? 0 : toDayList.size();
    }



    public class ViewHoder extends RecyclerView.ViewHolder {

        private CircleImageView image_item_one;
        private TextView text_name, text_time, text_money, text_jia,text_money_item_wwj;
        public ViewHoder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            image_item_one = itemView.findViewById(R.id.image_head_item_one);
            text_name = itemView.findViewById(R.id.nickName);
            text_time = itemView.findViewById(R.id.payTime);
            text_money = itemView.findViewById(R.id.payMoney);
            text_money_item_wwj = itemView.findViewById(R.id.moneySource);
        }
    }
}
