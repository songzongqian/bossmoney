package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.TurnoverData;
import com.byx.xiuboss.xiuboss.MainActivity;
import com.byx.xiuboss.xiuboss.Mvp.activity.TodayMoneyActivity;
import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.utils.AutoUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by wwj on 2018/9/1.
 */

public class TurnoverAdapter extends RecyclerView.Adapter<TurnoverAdapter.ViewHolder>  {
    private TurnoverData data;
    private Context context;
    private SharedPreferences share;
    private String sid;
    private int pageIndex=0;
    private List<TurnoverData.DataBean> listData;
    private MainActivity activity=new MainActivity();
    private View VIEW_FOOTER;
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_FOOTER = 1002;

    public TurnoverAdapter(Context context, List<TurnoverData.DataBean> listData,TurnoverData data) {
        this.data=data;
        this.context = context;
        this.listData = listData;
    }

    public TurnoverAdapter(Context context, List<TurnoverData.DataBean> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void setData(List<TurnoverData.DataBean> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new ViewHolder(VIEW_FOOTER);
        } else {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.turn_over_adapter_item, null));
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_FOOTER)
            return;
        if (holder instanceof ViewHolder) {

            if (listData.get(position).getPrice().indexOf(".")!=-1){
                holder.text_money.setText("+" + listData.get(position).getPrice());
            }else{
                holder.text_money.setText("+" + listData.get(position).getPrice()+".00");
            }

            if(listData.get(position).getMoney_total().equals("0")){
                holder.text_money_item_wwj.setVisibility(View.GONE);
            }else{
                holder.text_money_item_wwj.setText("已返现"+listData.get(position).getMoney_total());
            }
            String addtime = listData.get(position).getAddtime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Long aLong = Long.valueOf(addtime);
            long lt = new Long(aLong*1000);
            Date date = new Date(lt);
            holder.text_time.setText(simpleDateFormat.format(date));
            holder.text_name.setText(listData.get(position).getNickname());

            if(listData.get(position).getNickname()!=null){

            if (listData.get(position).getNickname().equals("微信用户")){
                Glide.with(context).load(R.mipmap.common_wx_logo).into(holder.image_item_one);
            }else if (listData.get(position).getNickname().equals("支付宝用户")) {
                Glide.with(context).load(R.mipmap.common_ali_logo).into(holder.image_item_one);
            }else{
                Glide.with(context).load(listData.get(position).getAvatar()).into(holder.image_item_one);
            }
            }else{

            }

            if (position+1 ==5) {
                holder.text_jia.setVisibility(View.VISIBLE);
            }else{
                holder.text_jia.setVisibility(View.GONE);
            }
            if (data.getMessage().equals("没有营业额")){
                holder.relativi_one.setVisibility(View.GONE);
                holder.relativi_two.setVisibility(View.VISIBLE);
            }else{
                holder.relativi_one.setVisibility(View.VISIBLE);
                holder.relativi_two.setVisibility(View.GONE);
            }


            holder.text_jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, TodayMoneyActivity.class);
                    context.startActivity(intent);

                }
            });

        }
    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            //没有脚布局时，需要创建脚布局

           // RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
           // footerView.setLayoutParams(lp);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
                VIEW_FOOTER = footerView;
                notifyItemInserted(getItemCount());
        }
    }

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }



    public void addData(int position) {

    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    @Override
    public int getItemCount() {
        int count = (listData == null ? 0 : listData.size());

        if (count>5){
            count=5;
           return ++count;
        }
        if (VIEW_FOOTER != null) {
            count++;
        }
        return count;
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView image_item_one;
        private TextView text_name, text_time, text_money, text_jia,text_money_item_wwj;
        private RelativeLayout relativi_one,relativi_two;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            image_item_one = itemView.findViewById(R.id.image_head_item_one);
            text_name = itemView.findViewById(R.id.text_name_item_one);
            text_time = itemView.findViewById(R.id.text_time_item_one);
            text_money = itemView.findViewById(R.id.text_money_item_one);
            text_money_item_wwj = itemView.findViewById(R.id.text_money_item_wwj);
            text_jia = itemView.findViewById(R.id.text_jia_item_one);
            relativi_one = itemView.findViewById(R.id.relativi_one);
            relativi_two = itemView.findViewById(R.id.relativi_two);
        }
    }
}
