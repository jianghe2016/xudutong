package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdt.xudutong.R;

/**
 * Created by Administrator on 2017/6/17.
 */
public class Homebankselectrecycleviewadapter extends RecyclerView.Adapter<Homebankselectrecycleviewadapter.MyHolder> implements View.OnClickListener{

    private int[] person_merchant_selectimglist;
    private String[] person_merchant_selecttextlist;
    private Context context;
    private LayoutInflater inflater;

    public Homebankselectrecycleviewadapter(Context context, int[] person_merchant_selectimglist, String[] person_merchant_selecttextlist) {
        this.context = context;
        this.person_merchant_selectimglist = person_merchant_selectimglist;
        this.person_merchant_selecttextlist = person_merchant_selecttextlist;
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
        View view = inflater.inflate(R.layout.home_bankselect_recycleview_item, parent, false);
        MyHolder holder = new MyHolder(view);

        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
       holder.itemone.setImageResource(person_merchant_selectimglist[position]);
       holder.itemtwo.setText(person_merchant_selecttextlist[position]);
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
        return person_merchant_selectimglist.length;
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView itemone;
        private TextView itemtwo;
        public MyHolder(View view) {
            super(view);
            itemone = (ImageView) view.findViewById(R.id.home_selectbankimgs);
            itemtwo = (TextView) view.findViewById(R.id.home_selectbanktexts);

        }
    }
}