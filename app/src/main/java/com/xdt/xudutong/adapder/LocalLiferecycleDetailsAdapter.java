package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Localliferecycledetails;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class LocalLiferecycleDetailsAdapter extends RecyclerView.Adapter<LocalLiferecycleDetailsAdapter.MyHolder> implements View.OnClickListener {

    private List<Localliferecycledetails.ContentBean.DataBean> localliferecycledetailsdta;
    private int[] localliferecycleoneimgs;
    private Context context;
    private LayoutInflater inflater;

    public LocalLiferecycleDetailsAdapter(Context context, int[] localliferecycleoneimgs, List<Localliferecycledetails.ContentBean.DataBean> localliferecycledetailsdta) {
        this.context = context;
        this.localliferecycleoneimgs = localliferecycleoneimgs;
        this.localliferecycledetailsdta = localliferecycledetailsdta;
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
        View view = inflater.inflate(R.layout.locallife_localrecycleviewdetailsitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setImageResource(localliferecycleoneimgs[position]);
        holder.itemtwo.setText(localliferecycledetailsdta.get(position).getVAL());
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
        return localliferecycledetailsdta.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView itemone;
        private TextView itemtwo;
        private ImageView itemthree;
        public MyHolder(View view) {
            super(view);
            itemone = (ImageView) view.findViewById(R.id.locallife_localrecycledetailsrecycleviewitemimg1);
            itemtwo = (TextView) view.findViewById(R.id.locallife_localrecycledetailsrecycleviewitemtext);
            itemthree = (ImageView) view.findViewById(R.id.locallife_localrecycledetailsrecycleviewitemimg2);

        }
    }
}