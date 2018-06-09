package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.DsepgetMaternityInsurance;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class HomeShengyubaoxiannewRecyclerAdapter extends RecyclerView.Adapter<HomeShengyubaoxiannewRecyclerAdapter.MyHolder> implements View.OnClickListener {

    private List<DsepgetMaternityInsurance.ContentBean.DataBean> dsepgetmaternityinsurancedata;
    private String[] gongjijintext2;
    private Context context;
    private LayoutInflater inflater;

    public HomeShengyubaoxiannewRecyclerAdapter(Context context, List<DsepgetMaternityInsurance.ContentBean.DataBean> dsepgetmaternityinsurancedata, String[] gongjijintext2) {
        this.context = context;
        this.dsepgetmaternityinsurancedata = dsepgetmaternityinsurancedata;
        this.gongjijintext2 = gongjijintext2;
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
        View view = inflater.inflate(R.layout.home_yanglaobaoxiannewrecycleview_item, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.item1.setText(gongjijintext2[0]);
        holder.item2.setText(dsepgetmaternityinsurancedata.get(position).getCardType());
        holder.item3.setText(gongjijintext2[1]);
        holder.item4.setText(dsepgetmaternityinsurancedata.get(position).getCardNum());
     /*   holder.item5.setText(gongjijintext2[2]);
        holder.item6.setText(dsepgetmaternityinsurancedata.get(position).getFertilityStaffType()); */
        holder.item5.setVisibility(View.GONE);
        holder.item6.setVisibility(View.GONE);
        holder.item7.setText(gongjijintext2[2]);
        holder.item8.setText(dsepgetmaternityinsurancedata.get(position).getHospitalName());
        holder.item9.setText(gongjijintext2[3]);
        holder.item10.setText(dsepgetmaternityinsurancedata.get(position).getPayDate());
        holder.item11.setVisibility(View.GONE);
        holder.item12.setVisibility(View.GONE);
        holder.item13.setVisibility(View.GONE);
        holder.item14.setVisibility(View.GONE);
        holder.item15.setVisibility(View.GONE);
        holder.item16.setVisibility(View.GONE);
        holder.item17.setVisibility(View.GONE);
        holder.item18.setVisibility(View.GONE);
        holder.item19.setVisibility(View.GONE);
        holder.item20.setVisibility(View.GONE);
        holder.item21.setVisibility(View.GONE);
        holder.item22.setVisibility(View.GONE);

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
        return dsepgetmaternityinsurancedata.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private TextView item1;
        private TextView item2;
        private TextView item3;
        private TextView item4;
        private TextView item5;
        private TextView item6;
        private TextView item7;
        private TextView item8;
        private TextView item9;
        private TextView item10;
        private TextView item11;
        private TextView item12;
        private TextView item13;
        private TextView item14;
        private TextView item15;
        private TextView item16;
        private TextView item17;
        private TextView item18;
        private TextView item19;
        private TextView item20;
        private TextView item21;
        private TextView item22;
        public MyHolder(View view) {
            super(view);
            item1 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext1);
            item2 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext2);
            item2 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext2);
            item3 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext3);
            item4 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext4);
            item5 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext5);
            item6 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext6);
            item7 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext7);
            item8 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext8);
            item9 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext9);
            item10 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext10);
            item11 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext11);
            item12 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext12);
            item13 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext13);
            item14 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext14);
            item15 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext15);
            item16 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext16);
            item17 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext17);
            item18 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext18);
            item19 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext19);
            item20 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext20);
            item21 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext21);
            item22 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext22);

        }
    }
}