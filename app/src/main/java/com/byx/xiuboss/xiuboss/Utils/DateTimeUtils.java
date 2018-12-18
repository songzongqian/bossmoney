package com.byx.xiuboss.xiuboss.Utils;

import android.content.Context;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ZhangYanyan on 2018/10/15/ 00:09
 *
 * @description
 */
public class DateTimeUtils {


    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater3 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater4 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            // dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        }
    };

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            if (TextUtils.isEmpty(sdate)) {
                return null;
            }
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }


    public static String getFriendTime(long time, Context context) {
        if (time < 9999999999L) {
            time = time * 1000;
        }
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return friendly_time(format.format(date), context);
    }

    /*设置结束时间*/
    public static String finishTime(long time, Context context) {
        if (time < 9999999999L) {
            time = time * 1000;
        }
        if (time - System.currentTimeMillis() > 0) {
            Date date = new Date(time);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            return friendly_time(format.format(date), context);
        }
        return "";
    }

    public static String friendly_time(String sdate, Context context) {
        if (TextUtils.isEmpty(sdate)) {
            return "";
        }
        Date time = toDate(sdate);
        if (time == null) {
            return "Unknown";
        }
        String ftime;
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0) {
                if (cal.getTimeInMillis() - time.getTime() < 60000) {
                    ftime = "刚刚";
                } else {
                    ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) +
                            " " + (context != null ? "分钟前" : "分钟前");
                }
            } else if (hour <= 5) {
                ftime = hour + " " + (context != null ? "小时前" : "小时前");
            } else {
                ftime = (context != null ? "今天" + " " : "今天") + sdate.substring(11, 16).trim();
            }
            return ftime;
        } else {
            long lt = time.getTime() / 86400000;
            long ct = cal.getTimeInMillis() / 86400000;
            int days = (int) (ct - lt);
            if (days == 1) {
                ftime = context != null ? "昨天" + " " : "昨天";
            } else if (days == 2 && context == null) {
                ftime = "前天";
            } else {
                return sdate.substring(5, 16);
            }
            return (ftime + sdate.substring(11, 16)).trim();
        }
    }

    /*与系统时间比对，返回昨天今天和明天*/
    public static String judteTime(String time, Context context) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        if (time == null) {
            return "Unknown";
        }
        Calendar cal = Calendar.getInstance();
        // 判断是否是同一天
        String curDate = dateFormater3.get().format(cal.getTime());

        if (curDate.equals(time)) {
            return "今天";
        } else {
            int days = (int) (Integer.parseInt(curDate) - Integer.parseInt(time));
            if (days == 1) {
                return "昨天";
            } else if (days == 2 && context == null) {
                return "前天";
            } else {
                return time.substring(0, 4) + "年" + time.substring(4, 6) + "月" + time.substring(6, time.length()) + "日";
            }
            //return (ftime + sdate.substring(11, 16)).trim();
        }
    }

    /*比较设置订单*/
    public static String orderConfim(String day, String time) {
        try {
            Date orderDate = dateFormater.get().parse(day);
            String orderStr = dateFormater2.get().format(orderDate);

            Date currDate = Calendar.getInstance().getTime();
            String curStr = dateFormater2.get().format(currDate);
            /*当前减去订单日期，大于0说明订单过时*/
            if ((Integer.parseInt(curStr) - Integer.parseInt(orderStr)) > 0) {

            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*获取半小时之后的事件*/
    public static String getSelfTime(long time) {
        if (time < 9999999999L) {
            time = time * 1000 + 30*60*1000;
        }
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String dateStr = sdf.format(date);
        //long time = System.currentTimeMillis();
       // System.out.println("获取当前系统时间为：" + new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒").format(time));
       // System.out.println("获取当前系统时间为：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
        //System.out.println("三十分钟后的时间为：" + dateStr);
        return dateStr;
    }


}
