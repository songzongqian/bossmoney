package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.byx.xiuboss.xiuboss.Bean.Level;
import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by wangwenjie001 on 2018/10/12.
 */

public class LevelAdapter extends BaseAdapter {
    List<Level.DataBean> mList;
    private Context mContext;

    public LevelAdapter(List<Level.DataBean> mLevelList, Context mContext) {
        this.mList = mLevelList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tips_item, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Level.DataBean level = mList.get(position);
        vh.tipsGrade.setText(level.getLevel());
        vh.tipsMoney.setText(level.getValue());
        vh.tipsReward.setText(level.getReturnCash());
        return convertView;
    }

    class ViewHolder {
        TextView tipsGrade;
        TextView tipsMoney;
        TextView tipsReward;

        public ViewHolder(View convertView) {
            tipsGrade = convertView.findViewById(R.id.tips_grade);
            tipsMoney = convertView.findViewById(R.id.tips_money);
            tipsReward = convertView.findViewById(R.id.tips_reward);
        }
    }
}
