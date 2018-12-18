package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.Bean.OrderBean;
import com.byx.xiuboss.xiuboss.Bean.ReCoinBean;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DateTimeUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by night_slight on 2018/11/9.
 */

public class ReCoinAdapter extends RecyclerView.Adapter<ReCoinAdapter.VhHolder> {

    private List<ReCoinBean.DataBean> mList;
    private LayoutInflater inflater;
    private Context mContext;
    private OnItemClickListener mListener;

    public ReCoinAdapter(List<ReCoinBean.DataBean> mComOrderData, Context context) {
        this.mList = mComOrderData;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public VhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recoin_item, parent, false);
        VhHolder holder = new VhHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VhHolder holder, int position) {

        setChildInitData(holder, mList.get(position), position);
        setOnClickListener(holder, mList.get(position), position);
    }

    private void setOnClickListener(VhHolder holder, final ReCoinBean.DataBean dataBean, final int position) {

        holder.order_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + dataBean.getMobile());
                intent.setData(data);
                mContext.startActivity(intent);
            }
        });
        holder.order_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                Uri data = Uri.parse("smsto:" + dataBean.getMobile());
                sendIntent.setData(data);
                mContext.startActivity(sendIntent);
            }
        });
        holder.child_sendPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + dataBean.getDeliveryerMobile());
                intent.setData(data);
                mContext.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position, dataBean);
            }
        });

        holder.recoin_agreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemConfim(position,dataBean);
            }
        });
        holder.recoin_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemResume(position,dataBean);
            }
        });

    }

    private void setChildInitData(VhHolder cVh, ReCoinBean.DataBean dataBean, int position) {
        if (dataBean == null) return;
        cVh.childUniacid.setText("# " + dataBean.getId());
        if (dataBean.getOrder_type() != null && dataBean.getOrder_type().equals("1")) {
            cVh.child_diviceTime.setText("立即送出");
        } else {
            cVh.child_diviceTime.setText("自提订单");
        }
        if (dataBean.getStatus() != null && dataBean.getDelivery_status() != null) {
            String orderStatus = OAdapter.getOrderStatus(dataBean.getStatus(), dataBean.getDelivery_status());
            cVh.child_status.setText(orderStatus);
        }
        if (dataBean.getDelivery_time() != null) {
            String friendTime = DateTimeUtils.getFriendTime(Long.parseLong(dataBean.getAddtime()), mContext);
            cVh.child_PayTime.setText(friendTime + "下单");//下单时间
        }
        Glide.with(mContext).load(dataBean.getAvatar()).into(cVh.child_UserIcon);
        cVh.child_UserName.setText(dataBean.getUsername());
        cVh.child_userSex.setText(dataBean.getSex());
        if (dataBean.getNote() != null) {
            cVh.child_tipic.setVisibility(View.VISIBLE);
            cVh.child_tipic.setText(dataBean.getNote());
        } else {
            cVh.child_tipic.setVisibility(View.GONE);
        }
        cVh.child_tipic.setText(dataBean.getNote());
//        cVh.child_deliverPrice.setText("￥" + dataBean.getTotal_fee());
        cVh.child_deliverPrice.setText("￥" + dataBean.getFinal_fee());

       /*顾客实际支付*/
        SpannableStringBuilder esitmate = OAdapter.getActual(dataBean.getTotal_fee());
        cVh.child_finalNoce.setText(esitmate);
        /*设置预计收入*/
        SpannableStringBuilder actual = OAdapter.getEsitmate(dataBean.getStore_final_fee());
        cVh.child_deliverPrice.setText(actual);

        if (dataBean.getTitle() != null) {
            cVh.recoin_sendLayout.setVisibility(View.VISIBLE);
            cVh.child_sendUserName.setText(dataBean.getTitle());
        } else {
            cVh.recoin_sendLayout.setVisibility(View.GONE);
        }
        if (dataBean.getCountdowntime()!=null){
            String countdowntime = dataBean.getCountdowntime();
            long clerkTime = Long.parseLong(countdowntime) * 1000 - System.currentTimeMillis();
            if (clerkTime>0){
                cVh.recoin_lastTime.setVisibility(View.VISIBLE);
                cVh.recoin_lastTime.start(clerkTime);
                cVh.recoin_content.setText("后系统将自动完成退款");
            }else{
                cVh.recoin_lastTime.setVisibility(View.GONE);
                cVh.recoin_content.setText("系统已自动完成退款");
            }
        }else{
            cVh.recoin_lastTime.setVisibility(View.GONE);
            cVh.recoin_content.setText("系统已自动完成退款");
        }
        if (dataBean.getSefund_content()!=null){
            cVh.recoin_applyReson.setText("理由："+dataBean.getSefund_content());
        }else{
            cVh.recoin_applyReson.setText("理由：无");
        }
        if (dataBean.getSefund_time()!=null){
            cVh.recoin_applyTime.setText(dataBean.getSefund_time());
        }

        /*设置完成的订单*/
        if (dataBean.getCart() != null && dataBean.getCart().size() > 0) {
            cVh.orderLayout.removeAllViews();
            List<ReCoinBean.DataBean.CartBean> cart = dataBean.getCart();
            for (int n = 0; n < cart.size(); n++) {
                if (cart.get(n) != null && cart.get(n).getOptions() != null && cart.get(n).getOptions().size() > 0) {
                    List<ReCoinBean.DataBean.CartBean.OptionsBean> options = cart.get(n).getOptions();
                    for (int i = 0; i < options.size(); i++) {
                        View item = inflater.inflate(R.layout.layout_order, null);
                        TextView order_title = item.findViewById(R.id.order_title);
                        TextView order_num = item.findViewById(R.id.order_num);
                        TextView order_price = item.findViewById(R.id.order_price);
                        order_title.setText(options.get(i).getTitle());
                        if (Integer.parseInt(options.get(i).getNum()) > 1) {
                            order_num.setTextColor(Color.parseColor("#F24744"));
                            order_num.setText("x" + options.get(i).getNum());
                        } else {
                            order_num.setTextColor(Color.parseColor("#000000"));
                            order_num.setText("x" + options.get(i).getNum());
                        }

                        if (options.get(i).getTitle().equals("餐盒费")){
                            if (dataBean.getBox_price() != null && Integer.parseInt(options.get(i).getNum())>0) {
                                order_price.setText("￥" + dataBean.getBox_price() );
                            }
                        }else{
                            if (options.get(i).getPrice() != null) {
                                order_price.setText("￥" + options.get(i).getPrice());
                            }
                        }
                        cVh.orderLayout.addView(item);
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VhHolder extends RecyclerView.ViewHolder {
        TextView childUniacid;
        TextView child_diviceTime;
        TextView child_status;
        TextView child_PayTime;
        CircleImageView child_UserIcon;
        TextView child_UserName;
        TextView child_userSex;
        ImageView order_phone;
        ImageView order_message;
        TextView child_deliverPrice;
        TextView child_finalNoce;
        TextView child_sendUserName;
        TextView child_tipic;
        ImageView child_sendPhone;
        AutoLinearLayout orderLayout;
        AutoLinearLayout recoin_sendLayout;

        /*添加后期*/
        CountdownView recoin_lastTime;
        TextView recoin_agreen;
        TextView recoin_resume;
        TextView recoin_resone;
        TextView recoin_applyTime;
        TextView recoin_applyReson;
        TextView recoin_content;

        public VhHolder(View childView) {
            super(childView);
            childUniacid = childView.findViewById(R.id.child_uniacid);
            child_diviceTime = childView.findViewById(R.id.child_diviceTime);
            child_status = childView.findViewById(R.id.child_status);
            child_PayTime = childView.findViewById(R.id.child_PayTime);
            child_UserIcon = childView.findViewById(R.id.child_UserIcon);
            child_UserName = childView.findViewById(R.id.child_UserName);
            child_userSex = childView.findViewById(R.id.child_userSex);
            order_phone = childView.findViewById(R.id.order_phone);
            order_message = childView.findViewById(R.id.order_message);
            child_deliverPrice = childView.findViewById(R.id.child_deliverPrice);
            child_finalNoce = childView.findViewById(R.id.child_finalNoce);
            child_sendUserName = childView.findViewById(R.id.child_sendUserName);
            child_sendPhone = childView.findViewById(R.id.child_sendPhone);
            orderLayout = childView.findViewById(R.id.order_layout);
            child_tipic = childView.findViewById(R.id.child_tipic);
            recoin_sendLayout = childView.findViewById(R.id.recoin_sendLayout);
            recoin_lastTime = childView.findViewById(R.id.recoin_lastTime);
            recoin_agreen = childView.findViewById(R.id.recoin_agreen);
            recoin_resume = childView.findViewById(R.id.recoin_resume);
            recoin_resone = childView.findViewById(R.id.recoin_resone);
            recoin_applyTime = childView.findViewById(R.id.recoin_applyTime);
            recoin_applyReson = childView.findViewById(R.id.recoin_applyReson);
            recoin_content = childView.findViewById(R.id.recoin_content);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, ReCoinBean.DataBean categorysBean);
        void onItemConfim(int position,ReCoinBean.DataBean categorysBean);
        void onItemResume(int position,ReCoinBean.DataBean categorysBean);
    }
}
