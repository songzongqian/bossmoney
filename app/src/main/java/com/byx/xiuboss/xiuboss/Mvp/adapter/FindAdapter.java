package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.byx.xiuboss.xiuboss.Bean.FindBean;
import com.byx.xiuboss.xiuboss.Mvp.activity.FindWebViewActivity;
import com.byx.xiuboss.xiuboss.R;

import java.util.List;

public class FindAdapter extends RecyclerView.Adapter {
    List<FindBean.DataBean> list;
    Context context;

    public FindAdapter(List<FindBean.DataBean> dataList, FragmentActivity activity) {
        this.list=dataList;
        this.context=activity;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_find,parent,false);
        MyViewHolder m  = new MyViewHolder(view);
        return m;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        FindBean.DataBean dataBean = list.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .error(R.mipmap.my_icon_portrait_s)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(dataBean.getIcon()).apply(requestOptions).into(myViewHolder.imageView);
        myViewHolder.lineView.setAlpha(0.1f);
        myViewHolder.textView.setText(dataBean.getTitle());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,FindWebViewActivity.class);
                intent.putExtra("title",dataBean.getTitle());
                intent.putExtra("findUrl",dataBean.getUrl());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView textView;
        private final RelativeLayout rlItem;
        private final View lineView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item_find);
            textView = itemView.findViewById(R.id.tv_item_find);
            rlItem = itemView.findViewById(R.id.rl_item);
            lineView = itemView.findViewById(R.id.line);
        }
    }
}
