package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by wangwenjie001 on 2018/10/12.
 */

public class TipsAdapter extends BaseAdapter {
    private String[] grddeList;
    private String[] moneyList;
    private String[] rewardList;
    private Context mContext;

    public TipsAdapter(String[] grddeList, String[] moneyList, String[] rewardList, Context mContext) {
        this.grddeList = grddeList;
        this.moneyList = moneyList;
        this.rewardList = rewardList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return grddeList.length;
    }

    @Override
    public Object getItem(int position) {
        return grddeList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(mContext).inflate(R.layout.tips_item, parent, false);
        AutoUtils.autoSize(convertView);
        TextView tipsGrade = convertView.findViewById(R.id.tips_grade);
        TextView tipsMoney = convertView.findViewById(R.id.tips_money);
        TextView tipsReward = convertView.findViewById(R.id.tips_reward);
        tipsGrade.setText(grddeList[position]);
        tipsMoney.setText(moneyList[position]);
        tipsReward.setText(rewardList[position]);
        return convertView;
    }
}
