package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ViewgetView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class LocallifejingdianimgsRecycleAdapter extends RecyclerView.Adapter<LocallifejingdianimgsRecycleAdapter.MyHolder> implements View.OnClickListener {

    private List<ViewgetView.ContentBean.DataBean> viewgetviewdata;
    private Context context;
    private LayoutInflater inflater;

    public LocallifejingdianimgsRecycleAdapter(Context context, List<ViewgetView.ContentBean.DataBean> viewgetviewdata) {
        this.context = context;
        this.viewgetviewdata = viewgetviewdata;
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
        View view = inflater.inflate(R.layout.locallife_jingdian_recycleview_item, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        ViewgetView.ContentBean.DataBean dataBean = viewgetviewdata.get(position);
        String view_pic = dataBean.getView_pic();
        Glide.with(context).load(view_pic).into(holder.itemone);
        holder.itemtwo.setText(dataBean.getView_name());
        holder.itemthree.setText(dataBean.getOverview());
        if (position %2==1) {
            holder.itemfour.setVisibility(View.GONE);
        }
        if (position>1){
            holder.itemfive.setVisibility(View.GONE);
        }
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
        return viewgetviewdata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;

        public MyHolder(View view) {
            super(view);
            itemone = (ImageView) view.findViewById(R.id.locallife_jingdianrecycleviewitemimg1);
            itemtwo = (TextView) view.findViewById(R.id.locallife_jingdianrecycleviewitemtext1);
            itemthree = (TextView) view.findViewById(R.id.locallife_jingdianrecycleviewitemtext2);
            itemfour = (TextView) view.findViewById(R.id.locallife_jingdian_line);
            itemfive = (TextView) view.findViewById(R.id.locallife_jingdian_line2);
        }
    }
}