package com.byx.xiuboss.xiuboss.Mvp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.byx.xiuboss.xiuboss.Bean.SwichBean;
import com.byx.xiuboss.xiuboss.R;
import com.zhy.autolayout.utils.AutoUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by wangwenjie001 on 2018/10/5.
 */

public class SwichAdapter extends RecyclerView.Adapter<SwichAdapter.ViewHolder> {
    private List<SwichBean.DataBean> data;
    private Context context;
    private String swichUrl="https://www.ourdaidai.com/CI/index.php/StoreMy/switchStore";
    private Map<String,String> map=new HashMap<>();
    private SparseBooleanArray mCheckStates=new SparseBooleanArray();
    private onListener listener;
    private String id;
    private Activity activity;

    public SwichAdapter(String id,List<SwichBean.DataBean> data, Context context,Activity activity) {
        this.activity = activity;
        this.id = id;
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.swich_item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //在最开始适配的时候，将每一个CheckBox设置一个当前的Tag值，这样每个CheckBox都有了一个固定的标识

        String cardId = data.get(position).getId();//获取店铺管理的唯一标识
        if(cardId!=null){
            if (data.get(position).getId().equals(this.id)){
                // Toast.makeText(context, "有", Toast.LENGTH_SHORT).show();
                if (holder.swich_check_box.isChecked()){

                }else {
                    holder.swich_check_box.setChecked(true);
                }
            }

            Glide.with(context).load(data.get(position).getLogo()).into(holder.swich_icon);
            holder.swich_name.setText(data.get(position).getTitle());


            holder.swich_check_box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.swich_check_box.isChecked()){
                        holder.swich_check_box.setChecked(false);
                    }else {
//                    holder.swich_check_box.setChecked(true);
                    }
                    mCheckStates.put(position,true);
                    SharedPreferences share = context.getSharedPreferences("login_sucess", MODE_PRIVATE);
                    SharedPreferences.Editor edit = share.edit();
                    edit.putString("sid",data.get(position).getId());
                    /**
                     * 添加修改店铺手机号码字段
                     */
                    String telephone = data.get(position).getTelephone();
                    edit.putString("payMobile", telephone);
                    edit.putString("homeTitle",data.get(position).getTitle());
                    System.out.println("切换后存储手机号"+telephone);
                    edit.commit();
                    EventBus.getDefault().post(data.get(position).getId());
                    activity.finish();
                }
            });

        }else{

        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CircleImageView swich_icon;
        private final TextView swich_name;
        private final CheckBox swich_check_box;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            swich_icon = itemView.findViewById(R.id.swich_icon);
            swich_name = itemView.findViewById(R.id.swich_name);
            swich_check_box = itemView.findViewById(R.id.swich_check_box);

        }
    }
    public interface  onListener{
        void OnListener(int i);
    }
    public void setListener( onListener listener) {
        this.listener = listener;
    }
}
