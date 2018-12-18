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
import com.byx.xiuboss.xiuboss.Bean.ReCoinBean;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DateTimeUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by night_slight on 2018/11/9.
 */

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.VhHolder> {

    private List<DealBean.DataBean> mList;
    private LayoutInflater inflater;
    private Context mContext;
    private OnItemClickListener mListener;

    public DealAdapter(List<DealBean.DataBean> mComOrderData, Context context) {
        this.mList = mComOrderData;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public VhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.com_orderl_item, parent, false);
        VhHolder holder = new VhHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VhHolder holder, int position) {

        setChildInitData(holder, mList.get(position), position);
        setOnClickListener(holder, mList.get(position), position);
    }

    private void setOnClickListener(VhHolder holder, final DealBean.DataBean dataBean, final int position) {

        holder.order_phone.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + dataBean.getMobile());
            intent.setData(data);
            mContext.startActivity(intent);
        });
        holder.order_message.setOnClickListener(view->{
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
            Uri data = Uri.parse("smsto:" + dataBean.getMobile());
            sendIntent.setData(data);
            mContext.startActivity(sendIntent);
        });
        holder.child_sendPhone.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + dataBean.getDeliveryerMobile());
            intent.setData(data);
            mContext.startActivity(intent);
        });
        holder.itemView.setOnClickListener(view->{
            mListener.onItemClick(position, dataBean);
        });
        holder.order_bossPhone.setOnClickListener(view->{
            mListener.onBossPhoneClick(dataBean.getStationPhone()!=null?dataBean.getStationPhone():"");
        });
        holder.order_mineSend.setOnClickListener(view->{
            mListener.onMeSendClick(1,position,dataBean);
        });
        holder.child_sendComplate.setOnClickListener(view->{
            mListener.onMeSendClick(0,position,dataBean);
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
        if (dataBean.getNote() != null) {
            cVh.child_tipic.setVisibility(View.VISIBLE);
            cVh.child_tipic.setText(dataBean.getNote());
        } else {
            cVh.child_tipic.setVisibility(View.GONE);
        }

       /*顾客实际支付*/
        SpannableStringBuilder esitmate = OAdapter.getActual(dataBean.getTotal_fee());
        cVh.child_finalNoce.setText(esitmate);
        /*设置预计收入*/
        SpannableStringBuilder actual = OAdapter.getEsitmate(dataBean.getStore_final_fee());
        cVh.child_deliverPrice.setText(actual);


        if (dataBean.getTitle() != null) {
            cVh.child_sendPhone.setVisibility(View.VISIBLE);
            cVh.child_sendComplate.setVisibility(View.GONE);
            cVh.order_bosslayout.setVisibility(View.GONE);
            if (dataBean.getStatus().equals("4") && dataBean.getDelivery_status().equals("7")){
                cVh.child_sendUserName.setText("待骑手接单(" + dataBean.getTitle() + ")");
            }else{
                cVh.child_sendUserName.setText("骑手已接单(" + dataBean.getTitle() + ")");
            }

        } else {
            cVh.child_sendPhone.setVisibility(View.GONE);
            if (dataBean.getStatus()!=null && dataBean.getDelivery_status()!=null && dataBean.getStatus().equals("4") && dataBean.getDelivery_status().equals("7")){
                cVh.child_sendComplate.setVisibility(View.GONE);
                //cVh.child_sendUserName.setText("待骑手接单");
                cVh.child_sendUserName.setText("无骑手接单");
                cVh.order_bosslayout.setVisibility(View.VISIBLE);
            }else{
                cVh.order_bosslayout.setVisibility(View.GONE);
                cVh.child_sendComplate.setVisibility(View.VISIBLE);
                cVh.child_sendUserName.setText("自行配送中...");
            }

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
        ImageView order_phone;    //商家电话
        ImageView order_message;
        TextView child_deliverPrice;
        TextView child_finalNoce;
        TextView child_tipic;
        TextView child_sendUserName;
        ImageView child_sendPhone;   //配送员电话
        AutoLinearLayout orderLayout;
        AutoLinearLayout chile_senlayout;

        TextView order_bossPhone; //站长电话
        TextView order_mineSend; //自行配送
        AutoLinearLayout order_bosslayout; //站长电话与自行配送的父布局
        TextView child_sendComplate; //老板配送完成


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
            child_tipic = childView.findViewById(R.id.child_tipic);
            order_message = childView.findViewById(R.id.order_message);
            child_deliverPrice = childView.findViewById(R.id.child_deliverPrice);
            child_finalNoce = childView.findViewById(R.id.child_finalNoce);
            child_sendUserName = childView.findViewById(R.id.child_sendUserName);
            child_sendPhone = childView.findViewById(R.id.child_sendPhone);
            orderLayout = childView.findViewById(R.id.order_layout);
            chile_senlayout = childView.findViewById(R.id.chile_senlayout);
            order_bossPhone = childView.findViewById(R.id.order_bossPhone);
            order_mineSend = childView.findViewById(R.id.order_mineSend);
            order_bosslayout = childView.findViewById(R.id.order_bosslayout);
            child_sendComplate = childView.findViewById(R.id.child_sendComplate);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, DealBean.DataBean categorysBean);
        void onBossPhoneClick(String phone);
        /*type  0 :完成  1自己配送*/
        void onMeSendClick(int type,int postion,DealBean.DataBean categorysBean);
    }
}
