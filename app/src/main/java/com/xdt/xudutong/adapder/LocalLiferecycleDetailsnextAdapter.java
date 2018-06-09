package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Localliferecycledetailsnext;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class LocalLiferecycleDetailsnextAdapter extends RecyclerView.Adapter<LocalLiferecycleDetailsnextAdapter.MyHolder> implements View.OnClickListener {

    private List<Localliferecycledetailsnext.ContentBean.DataBean> localliferecycledetailsnextdata;
    private Context context;
    private LayoutInflater inflater;

    public LocalLiferecycleDetailsnextAdapter(Context context,  List<Localliferecycledetailsnext.ContentBean.DataBean> localliferecycledetailsnextdata) {
        this.context = context;
        this.localliferecycledetailsnextdata = localliferecycledetailsnextdata;
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
        View view = inflater.inflate(R.layout.locallife_localrecycleviewdetailsnextitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String addr = localliferecycledetailsnextdata.get(position).getVAL().getAddr();
        if (addr.contains(",")){
            String[] splitString = addr.split(",");
            holder.itemthree.setText(splitString[0]);
            holder.itemfive.setText(splitString[1]);
        }else{
            holder.itemthree.setText(addr);
            holder.itemfive.setText("");
        }
        holder.itemone.setText(localliferecycledetailsnextdata.get(position).getVAL().getCompanyName());
        holder.itemtwo.setText("联系人:"+localliferecycledetailsnextdata.get(position).getVAL().getUsername());
        holder.itemfour.setText("电话:"+localliferecycledetailsnextdata.get(position).getVAL().getUserphone());
        holder.itemsix.setText(localliferecycledetailsnextdata.get(position).getVAL().getDesc());
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
        return localliferecycledetailsnextdata.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;
        private TextView itemsix;
        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.localliferecycledetailsnexttext1);
            itemtwo = (TextView) view.findViewById(R.id.localliferecycledetailsnexttext2);
            itemthree = (TextView) view.findViewById(R.id.localliferecycledetailsnexttext3);
            itemfour = (TextView) view.findViewById(R.id.localliferecycledetailsnexttext4);
            itemfive = (TextView) view.findViewById(R.id.localliferecycledetailsnexttext5);
            itemsix = (TextView) view.findViewById(R.id.localliferecycledetailsnexttext6);

        }
    }
}