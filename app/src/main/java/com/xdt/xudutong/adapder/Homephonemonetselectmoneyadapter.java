package com.xdt.xudutong.adapder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.view.LogUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017\12\14 0014.
 */

public class Homephonemonetselectmoneyadapter extends RecyclerView.Adapter<Homephonemonetselectmoneyadapter.MyViewHolder> {
    private Context context;
    private boolean eduittextphonenumber;
    private String[] selectmoneylist;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener = null;
    private List<Boolean> isClicks;//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色
    private View view;

    public Homephonemonetselectmoneyadapter(Context context, String[] selectmoneylist, boolean eduittextphonenumber) {
        this.context = context;
        this.eduittextphonenumber = eduittextphonenumber;
        this.selectmoneylist = selectmoneylist;
        mInflater = LayoutInflater.from(context);
        isClicks = new ArrayList<>();
        for(int i = 0;i<selectmoneylist.length;i++){
            isClicks.add(false);
        }
    }



    public interface OnItemClickListener {
        void onItemClick(View view,String selectmoney,int position);
    }

    @Override
    public int getItemCount() {
        return selectmoneylist.length;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (eduittextphonenumber==true){
            LogUtil.d("eduittextphonenumber", eduittextphonenumber+"");
            view = mInflater.inflate(R.layout.home_phonemoneyselectmomeyitem, parent, false);
            view.setBackgroundColor(Color.parseColor("#2673d7"));
        }else{
            LogUtil.d("eduittextphonenumber", eduittextphonenumber+"");
            view = mInflater.inflate(R.layout.home_phonemoneyselectmomeyitem, parent, false);
            view.setBackgroundColor(Color.parseColor("#c9caca"));
        }
        MyViewHolder viewHolder = new MyViewHolder(view);
        //将创建的View注册点击事件
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String selecti = selectmoneylist[position];
        holder.item2.setText(selecti);
        holder.itemView.setTag(position);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        if (eduittextphonenumber==true){
            if(isClicks.get(position)){
                holder.item1.setBackground(context.getResources().getDrawable(R.drawable.shape_home_selectmoneybackaround));
                holder.item2.setTextColor(Color.parseColor("#ffffff"));
            }else{
                holder.item1.setBackground(context.getResources().getDrawable(R.drawable.shape_home_unselectmoneybackaround));
                holder.item2.setTextColor(Color.parseColor("#2673d7"));
            }
        }else{
            holder.item1.setBackground(context.getResources().getDrawable(R.drawable.shape_home_unselectmoneybackaround2));
            holder.item2.setTextColor(Color.parseColor("#c9caca"));
        }

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i = 0; i <isClicks.size();i++){
                        isClicks.set(i,false);
                    }
                    isClicks.set(position,true);
                    notifyDataSetChanged();
                    mOnItemClickListener.onItemClick(holder.itemView,selecti, position);
                }
            });
        }
    }

    public void setOnItemClickLitener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        private  LinearLayout item1;
        private  TextView item2;

        public MyViewHolder(View itemView) {
            super(itemView);
            item1 = (LinearLayout) itemView.findViewById(R.id.home_phonemoneyselectmomeyitembackaround);
            item2 = (TextView) itemView.findViewById(R.id.home_phonemoneyselectmomeyitemtext1);
        }
    }
}

