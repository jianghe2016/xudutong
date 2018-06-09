package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdt.xudutong.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class Homezhihuiyiliaorecycleadapter extends RecyclerView.Adapter<Homezhihuiyiliaorecycleadapter.MyHolder> implements View.OnClickListener {

    private List<Integer> list1;
    private List<String> list2;
    private Context context;
    private LayoutInflater inflater;

    public Homezhihuiyiliaorecycleadapter(Context context, List list1, List list2) {
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
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
        View view = inflater.inflate(R.layout.home_zhihuiyiliaoitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setImageResource(list1.get(position));
        holder.itemtwo.setText(list2.get(position));
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
        return list2.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView itemone;
        private TextView itemtwo;
        public MyHolder(View view) {
            super(view);
            itemone = (ImageView) view.findViewById(R.id.home_zhihuiyiliao_itemimg);
            itemtwo = (TextView) view.findViewById(R.id.home_zhihuiyiliao_itemtext);

        }
    }
}