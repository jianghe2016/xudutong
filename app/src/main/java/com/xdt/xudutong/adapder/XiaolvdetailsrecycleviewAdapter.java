package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Wsbikemakecardrecord;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class XiaolvdetailsrecycleviewAdapter extends RecyclerView.Adapter<XiaolvdetailsrecycleviewAdapter.MyHolder> implements View.OnClickListener {


    private List<Wsbikemakecardrecord.ContentBean.DataBean> wsbikemakecardrecorddata;
    private Context context;
    private LayoutInflater inflater;

    public XiaolvdetailsrecycleviewAdapter(Context context, List<Wsbikemakecardrecord.ContentBean.DataBean> wsbikemakecardrecorddata) {
        this.context = context;
        this.wsbikemakecardrecorddata = wsbikemakecardrecorddata;
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
        View view = inflater.inflate(R.layout.xiaolvdetailsrecycleview_item, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemzero.setText("车号 : "+wsbikemakecardrecorddata.get(position).getBikeno());
        holder.itemzero.setText("车号 : "+wsbikemakecardrecorddata.get(position).getBikeno());
        String cost = wsbikemakecardrecorddata.get(position).getCost();
        if (!TextUtils.isEmpty(cost)){
            holder.itemzero2.setText("费用 : "+cost);
        }else{
            holder.itemzero2.setText("费用 : "+0+"元");
        }

        holder.itemone.setText(wsbikemakecardrecorddata.get(position).getBorrowtime());
        holder.itemtwo.setText(wsbikemakecardrecorddata.get(position).getBorrowstation());
        holder.itemthree.setText(wsbikemakecardrecorddata.get(position).getReturntime());
        holder.itemfour.setText(wsbikemakecardrecorddata.get(position).getReturnstation());
        holder.itemfive.setText(wsbikemakecardrecorddata.get(position).getManual());
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
        return wsbikemakecardrecorddata.size();
    }

     class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemzero;
        private TextView itemzero2;
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;



        public MyHolder(View view) {
            super(view);
            itemzero = (TextView) view.findViewById(R.id.xiaolvdetailsrecycleviewitem11);
            itemzero2 = (TextView) view.findViewById(R.id.xiaolvdetailsrecycleviewitem12);
            itemone = (TextView) view.findViewById(R.id.xiaolvdetailsrecycleviewitem1);
            itemtwo = (TextView) view.findViewById(R.id.xiaolvdetailsrecycleviewitem2);
            itemthree = (TextView) view.findViewById(R.id.xiaolvdetailsrecycleviewitem3);
            itemfour = (TextView) view.findViewById(R.id.xiaolvdetailsrecycleviewitem4);
            itemfive = (TextView) view.findViewById(R.id.xiaolvdetailsrecycleviewitem5);
        }
    }
}