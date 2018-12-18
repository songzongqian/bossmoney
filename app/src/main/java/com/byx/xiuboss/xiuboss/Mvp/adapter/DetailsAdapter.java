package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.XiangQingBean;
import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwenjie001 on 2018/8/31.
 */

public class DetailsAdapter extends BaseAdapter {
    private List<XiangQingBean.DataBean> data;
    private Context context;

    public DetailsAdapter(List<XiangQingBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyDetails myDetails = null;

        if (convertView == null) {
            myDetails = new MyDetails();
            convertView = LayoutInflater.from(context).inflate(R.layout.turn_over_adapter_item, parent, false);
            AutoUtils.autoSize(convertView);
            myDetails.touxiang = convertView.findViewById(R.id.image_head_item_one);
            myDetails.nicheng = convertView.findViewById(R.id.text_name_item_one);
            myDetails.time = convertView.findViewById(R.id.text_time_item_one);
            myDetails.jine = convertView.findViewById(R.id.text_money_item_one);
            myDetails.xinxi = convertView.findViewById(R.id.text_money_item_wwj);
            convertView.setTag(myDetails);
        } else {
            myDetails = (MyDetails) convertView.getTag();
        }
        Glide.with(context).load(data.get(position).getAvatar()).into(myDetails.touxiang);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long( Long.valueOf(data.get(position).getAddtime())*1000);
        Date date = new Date(lt);
        String format = simpleDateFormat.format(date);
        myDetails.nicheng.setText(data.get(position).getNickname());
        myDetails.time.setText(format);
        if (data.get(position).getPrice().indexOf(".")!=-1){
            myDetails.jine.setText("+"+data.get(position).getPrice());
        }else{
            myDetails.jine.setText("+"+data.get(position).getPrice()+".00");
        }
        myDetails.xinxi.setText("已返现"+data.get(position).getMoney_total());


        return convertView;
    }

    public class MyDetails {

        ImageView touxiang;
        TextView nicheng;
        TextView time;
        TextView jine;
        TextView xinxi;
    }
}

