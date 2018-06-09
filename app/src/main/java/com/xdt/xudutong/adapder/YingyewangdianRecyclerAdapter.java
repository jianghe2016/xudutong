package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.BranchgetDistanceListBypage;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class YingyewangdianRecyclerAdapter extends RecyclerView.Adapter<YingyewangdianRecyclerAdapter.MyHolder> implements View.OnClickListener {

    private List<BranchgetDistanceListBypage.ContentBean.DataBean> branchgetdistancelistbypagedata;

    private Context context;
    private LayoutInflater inflater;

    public YingyewangdianRecyclerAdapter(Context context, List<BranchgetDistanceListBypage.ContentBean.DataBean> branchgetdistancelistbypagedata) {
        this.context = context;
        this.branchgetdistancelistbypagedata = branchgetdistancelistbypagedata;
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
        View view = inflater.inflate(R.layout.yingyewangdianrecycleview_item3, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setText(branchgetdistancelistbypagedata.get(position).getBranchName());
        holder.itemtwo.setText("地址 ："+branchgetdistancelistbypagedata.get(position).getBranchAddress());
        holder.itemthree.setText("电话 :"+branchgetdistancelistbypagedata.get(position).getBranchPhone());
        holder.itemfour.setText("距离 ："+branchgetdistancelistbypagedata.get(position).getDistance()+"km");
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
        return branchgetdistancelistbypagedata.size();
    }

     class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;


        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.yingyewangdianrecycleitem31);
            itemtwo = (TextView) view.findViewById(R.id.yingyewangdianrecycleitem32);
            itemthree = (TextView) view.findViewById(R.id.yingyewangdianrecycleitem33);
            itemfour = (TextView) view.findViewById(R.id.yingyewangdianrecycleitem34);
        }
    }
}