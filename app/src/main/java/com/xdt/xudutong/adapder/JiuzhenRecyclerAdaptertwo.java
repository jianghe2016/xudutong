package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;

/**
 * Created by Administrator on 2017/6/17.
 */
public class JiuzhenRecyclerAdaptertwo extends RecyclerView.Adapter<JiuzhenRecyclerAdaptertwo.MyHolder> implements View.OnClickListener {

    private String[] date;
    private Context context;
    private LayoutInflater inflater;

    public JiuzhenRecyclerAdaptertwo(Context context, String[] date) {
        this.context = context;
        this.date = date;
        inflater = LayoutInflater.from(context);
    }


    // 点击事件的接口设置
    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_grid, parent, false);
        View view = inflater.inflate(R.layout.jiuzhentoprecycleview_itemitemtwo, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setText(date[position]);
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        if (date!=null){
            return date.length;
        }else{
            return 0;
        }

    }

     class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;




        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.jiuzhenrecycletoptwoitem1);

        }
    }
}