package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DateTimeUtils;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by night_slight on 2018/11/9.
 */

public class ComfimAdapter extends RecyclerView.Adapter<ComfimAdapter.VhHolder> {

    private List<DealBean.DataBean> mList;
    private LayoutInflater inflater;
    private Context mContext;
    private OnItemClickListener mListener;

    public ComfimAdapter(List<DealBean.DataBean> mComOrderData, Context context) {
        this.mList = mComOrderData;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public VhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.confim_order_item, parent, false);
        VhHolder holder = new VhHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VhHolder holder, int position) {
        setChildInitData(holder, mList.get(position), position);
        setOnClickListener(holder, position, mList.get(position));
    }

    private void setOnClickListener(VhHolder holder, final int position, final DealBean.DataBean dataBean) {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position, dataBean);
            }
        });
        holder.confimOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemConfimClick(position, dataBean);
            }
        });
        holder.cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemCancelClick(position, dataBean);
            }
        });

    }

    private void setChildInitData(VhHolder cVh, DealBean.DataBean dataBean, int position) {
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

        /*顾客实际支付*/
        SpannableStringBuilder esitmate = OAdapter.getActual(dataBean.getTotal_fee());
        cVh.child_finalNoce.setText(esitmate);
        /*设置预计收入*/
        SpannableStringBuilder actual = OAdapter.getEsitmate(dataBean.getStore_final_fee());
        cVh.child_deliverPrice.setText(actual);

        if (dataBean.getNote() != null) {
            cVh.child_tipic.setVisibility(View.VISIBLE);
            cVh.child_tipic.setText(dataBean.getNote());
        } else {
            cVh.child_tipic.setVisibility(View.GONE);
        }

        //cVh.child_sendUserName.setText(dataBean.getOrdersn());
        if (dataBean.getCountdowntime()!=null){
            long clerkTime = Long.parseLong(dataBean.getCountdowntime()) * 1000 - System.currentTimeMillis();
            cVh.countDownView.start(clerkTime);
        }
        /*设置完成的订单*/
        if (dataBean.getCart() != null && dataBean.getCart().size() > 0) {
            cVh.orderLayout.removeAllViews();
            List<DealBean.DataBean.CartBean> cart = dataBean.getCart();
            for (int n = 0; n < cart.size(); n++) {
                if (cart.get(n) != null && cart.get(n).getOptions() != null && cart.get(n).getOptions().size() > 0) {
                    List<DealBean.DataBean.CartBean.OptionsBean> options = cart.get(n).getOptions();
                    for (int i = 0; i < options.size(); i++) {
                        View item = inflater.inflate(R.layout.layout_order, null);
                        TextView order_title = item.findViewById(R.id.order_title);
                        TextView order_num = item.findViewById(R.id.order_num);
                        TextView order_price = item.findViewById(R.id.order_price);

                        order_title.setText(options.get(i).getTitle());
//                        order_num.setText("x" + options.get(i).getNum());
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
        if (mList.size() > 0) {
            return 1;
        }
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
        //TextView child_sendUserName;
        AutoLinearLayout orderLayout;
        AutoLinearLayout confimOrder;
        //ImageView child_sendPhone;
        TextView child_tipic;

        CountdownView countDownView;
        TextView cancelOrder;

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
            //child_sendUserName = childView.findViewById(R.id.child_sendUserName);
            //child_sendPhone = childView.findViewById(R.id.child_sendPhone);
            orderLayout = childView.findViewById(R.id.order_layout);
            confimOrder = childView.findViewById(R.id.confim_order);
            cancelOrder = childView.findViewById(R.id.cancel_order);
            countDownView = childView.findViewById(R.id.countDownView);
            child_tipic = childView.findViewById(R.id.child_tipic);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemConfimClick(int position, DealBean.DataBean categorysBean);

        void onItemCancelClick(int position, DealBean.DataBean categorysBean);

        void onItemClick(int position, DealBean.DataBean categorysBean);
    }
}
