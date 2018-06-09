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
public class LocalLiifeFragmentlifeRecycleAdapter extends RecyclerView.Adapter<LocalLiifeFragmentlifeRecycleAdapter.MyHolder> implements View.OnClickListener {

    private int[] locallifeimgs;
    private String[] locallifeStrings;
    private String[] locallifeStrings2;
    private Context context;
    private LayoutInflater inflater;

    public LocalLiifeFragmentlifeRecycleAdapter(Context context, int[] locallifeimgs, String[] locallifeStrings, String[] locallifeStrings2) {
        this.context = context;
        this.locallifeimgs = locallifeimgs;
        this.locallifeStrings = locallifeStrings;
        this.locallifeStrings2 = locallifeStrings2;
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
        View view = inflater.inflate(R.layout.locallife_localliferecycleview_item, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setImageResource(locallifeimgs[position]);
        holder.itemtwo.setText(locallifeStrings[position]);
        holder.itemthree.setText(locallifeStrings2[position]);
      // holder.itemone.setText(list.get(position).toString());
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
        return locallifeimgs.length;
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        public MyHolder(View view) {
            super(view);
            itemone = (ImageView) view.findViewById(R.id.locallife_localrecycleviewimg);
            itemtwo = (TextView) view.findViewById(R.id.locallife_localrecycleviewtext1);
            itemthree = (TextView) view.findViewById(R.id.locallife_localrecycleviewtext2);

        }
    }
}