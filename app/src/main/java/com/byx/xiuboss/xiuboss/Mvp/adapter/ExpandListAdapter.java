package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.DealBean;
import com.byx.xiuboss.xiuboss.Bean.ExtractBean;
import com.byx.xiuboss.xiuboss.Bean.OrderBean;
import com.byx.xiuboss.xiuboss.Mvp.adapter.SwichAdapter;
import com.byx.xiuboss.xiuboss.R;
import com.byx.xiuboss.xiuboss.Utils.DateTimeUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by night_slight on 2018/11/13.
 */

public class ExpandListAdapter extends BaseExpandableListAdapter {
    private List<OrderBean.DataBeanX> mList;
    private LayoutInflater inflater;
    private Context mContext;

    public ExpandListAdapter(Context mContext, List<OrderBean.DataBeanX> mList) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.mList = mList;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mList.size() > 0 && mList.get(groupPosition).getData() != null) {
            return mList.get(groupPosition).getData().size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getData().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder gVh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group_order_item, parent, false);
            gVh = new GroupHolder(convertView);
            convertView.setTag(gVh);
        } else {
            gVh = (GroupHolder) convertView.getTag();
        }
        setGroupInitData(gVh, mList.get(groupPosition), groupPosition);
        gVh.groupSelect.setSelected(isExpanded);
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildHolder cVh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_oreder_item, parent, false);
            cVh = new ChildHolder(convertView);
            convertView.setTag(cVh);
        } else {
            cVh = (ChildHolder) convertView.getTag();
        }
        setChildInitData(cVh, mList.get(groupPosition).getData().get(childPosition), groupPosition, childPosition);
        setOnClickListener(cVh, mList.get(groupPosition).getData().get(childPosition), groupPosition, childPosition);
        return convertView;
    }


    private void setOnClickListener(ChildHolder cVh, final OrderBean.DataBeanX.DataBean dataBean, int groupPosition, int childPosition) {

        cVh.order_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + dataBean.getMobile());
                intent.setData(data);
                mContext.startActivity(intent);
            }
        });
        cVh.order_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                Uri data = Uri.parse("smsto:" + dataBean.getMobile());
                sendIntent.setData(data);
                mContext.startActivity(sendIntent);
            }
        });
        cVh.child_sendPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + dataBean.getMobile());
                intent.setData(data);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        AutoLinearLayout groupOrderMore;
        TextView groupPrice;
        TextView cancelNum;
        TextView groupDate;
        ImageView groupSelect;

        public GroupHolder(View groupView) {
            groupOrderMore = groupView.findViewById(R.id.group_orderMore);
            groupPrice = groupView.findViewById(R.id.group_orderPrice);
            cancelNum = groupView.findViewById(R.id.group_orderCancelNum);
            groupDate = groupView.findViewById(R.id.group_date);
            groupSelect = groupView.findViewById(R.id.group_select);
        }
    }

    class ChildHolder {
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
        AutoLinearLayout child_orderlayout;

        public ChildHolder(View childView) {
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
            child_orderlayout = childView.findViewById(R.id.child_orderlayout);

        }
    }

    private void setChildInitData(ChildHolder cVh, OrderBean.DataBeanX.DataBean dataBean, int groupPosition, int childPosition) {
        if (dataBean == null) return;
        cVh.childUniacid.setText("# " + dataBean.getId());
        //cVh.child_diviceTime.setText(dataBean.getDelivery_time());
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
//
       /*顾客实际支付*/
        SpannableStringBuilder esitmate = OAdapter.getActual(dataBean.getTotal_fee());
        cVh.child_finalNoce.setText(esitmate);
        /*设置预计收入*/
        SpannableStringBuilder actual = OAdapter.getEsitmate(dataBean.getStore_final_fee());
        cVh.child_deliverPrice.setText(actual);


        if (dataBean.getOrder_type().equals("2")){
            cVh.child_sendPhone.setVisibility(View.GONE);
            cVh.child_sendUserName.setText("客户已自提");
        }else{
            if (dataBean.getTitle() != null) {
                //cVh.child_orderlayout.setVisibility(View.VISIBLE);
                cVh.child_sendPhone.setVisibility(View.VISIBLE);
                cVh.child_sendUserName.setText("骑手配送完成("+dataBean.getTitle()+")");
            } else {
                if (dataBean.getStatus() != null && dataBean.getDelivery_status() != null && dataBean.getStatus().equals("6")&& dataBean.getDelivery_status().equals("6")) {
                    cVh.child_sendPhone.setVisibility(View.GONE);
                    cVh.child_sendUserName.setText("无配送");
                }else{
                    cVh.child_sendPhone.setVisibility(View.VISIBLE);
                    cVh.child_sendUserName.setText("老板自配完成");

                }
                //cVh.child_orderlayout.setVisibility(View.GONE);
               // cVh.child_sendPhone.setVisibility(View.GONE);
            }
        }

         /*设置完成的订单*/
        if (dataBean.getCart() != null && dataBean.getCart().size() > 0) {
            cVh.orderLayout.removeAllViews();
            List<OrderBean.DataBeanX.DataBean.CartBean> cart = dataBean.getCart();
            for (int n = 0; n < cart.size(); n++) {
                if (cart.get(n) != null && cart.get(n).getOptions() != null && cart.get(n).getOptions().size() > 0) {
                    List<OrderBean.DataBeanX.DataBean.CartBean.OptionsBean> options = cart.get(n).getOptions();
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

    private void setGroupInitData(GroupHolder gVh, OrderBean.DataBeanX dataBeanX, int groupPosition) {
        if (dataBeanX == null) return;
        gVh.groupPrice.setText(String.valueOf(dataBeanX.getPrice()));
        String num = String.valueOf(Integer.parseInt(dataBeanX.getComplete()) + Integer.parseInt(dataBeanX.getCancel()));
        gVh.cancelNum.setText("共计" + num + "单，取消" + dataBeanX.getCancel() + "单");
        gVh.groupDate.setText(dataBeanX.getStat_day());
        gVh.groupDate.setText(DateTimeUtils.judteTime(dataBeanX.getStat_day(), mContext));
    }
}
