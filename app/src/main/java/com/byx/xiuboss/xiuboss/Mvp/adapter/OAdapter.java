package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * Created by night_slight on 2018/11/20.
 */

public class OAdapter {

    public static String getOrderStatus(String status, String delivery_status) {
        if (status.equals("6") && delivery_status.equals("6")) {
            return "已取消";
        } else if (status.equals("1") && delivery_status.equals("0")) {
            return "待确定";
        } else if (status.equals("4") && delivery_status.equals("7")) {
            return "待配送";
        } else if (status.equals("4") && delivery_status.equals("4")) {
            return "配送中";
        } else if (status.equals("5") && delivery_status.equals("5")) {
            return "已完成";
        } else if (status.equals("7") && delivery_status.equals("7")) {
            return "退款中";
        }
        return null;

    }

    public static String getNote(String payNum, String packagingNum, String distribution, String discount) {
        return "(顾客实际支付" + payNum + "元,含包装费" + packagingNum + "元,配送费" + distribution + "元,返现" + discount + "元)";
    }
    /*预计收入收入设置*/
    public static SpannableStringBuilder getEsitmate(String total_fee){
        SpannableStringBuilder ssb = new SpannableStringBuilder("预计收入:￥"+total_fee);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")),0,5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(13)),0,5,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#C39A4B")),5,ssb.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(20)),5,ssb.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return ssb;
    }
    /*预计实际支付设置*/
    public static SpannableStringBuilder getActual(String store_final_fee){
        SpannableStringBuilder ssb = new SpannableStringBuilder("顾客支付:￥"+store_final_fee );
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")),0,5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(13)),0,5,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")),5,ssb.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(13)),5,ssb.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return ssb;
    }
}
