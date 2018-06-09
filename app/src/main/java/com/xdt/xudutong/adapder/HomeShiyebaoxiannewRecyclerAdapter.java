package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.DsepgetUnemploymentInsurance;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class HomeShiyebaoxiannewRecyclerAdapter extends RecyclerView.Adapter<HomeShiyebaoxiannewRecyclerAdapter.MyHolder> implements View.OnClickListener {

    private List<DsepgetUnemploymentInsurance.ContentBean.DataBean> dsepgetunemploymentinsurancedata;
    private String[] gongjijintext2;
    private Context context;
    private LayoutInflater inflater;

    public HomeShiyebaoxiannewRecyclerAdapter(Context context, List<DsepgetUnemploymentInsurance.ContentBean.DataBean> dsepgetunemploymentinsurancedata, String[] gongjijintext2) {
        this.context = context;
        this.dsepgetunemploymentinsurancedata = dsepgetunemploymentinsurancedata;
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
        String gender = dsepgetunemploymentinsurancedata.get(position).getGender();
        if (!TextUtils.isEmpty(gender)) {
            if (gender.equals("1")) {
                holder.item2.setText("男");
            } else if (gender.equals("2")) {
                holder.item2.setText("女");
            } else {
                holder.item2.setText("空");
            }
        } else {
            holder.item2.setText("空");
        }
        holder.item3.setText(gongjijintext2[1]);
        holder.item4.setText(dsepgetunemploymentinsurancedata.get(position).getNation());
        holder.item5.setText(gongjijintext2[2]);
        holder.item6.setText(dsepgetunemploymentinsurancedata.get(position).getCardType());
        holder.item7.setText(gongjijintext2[3]);
        holder.item8.setText(dsepgetunemploymentinsurancedata.get(position).getCardNum());
        holder.item9.setText(gongjijintext2[4]);
        holder.item10.setText(dsepgetunemploymentinsurancedata.get(position).getSino());
        holder.item11.setText(gongjijintext2[5]);
        holder.item12.setText(dsepgetunemploymentinsurancedata.get(position).getInsureType());
        holder.item13.setText(gongjijintext2[6]);
        holder.item14.setText(dsepgetunemploymentinsurancedata.get(position).getInsureFeeAmt());
        holder.item15.setText(gongjijintext2[7]);
        holder.item16.setText(dsepgetunemploymentinsurancedata.get(position).getBdate());
        holder.item17.setText(gongjijintext2[8]);
        holder.item18.setText(dsepgetunemploymentinsurancedata.get(position).getEdate());
        holder.item19.setText(gongjijintext2[9]);
        holder.item20.setText(dsepgetunemploymentinsurancedata.get(position).getWorkDate());
        holder.item21.setText(gongjijintext2[10]);
        holder.item22.setText(dsepgetunemploymentinsurancedata.get(position).getPayDate());

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
        return dsepgetunemploymentinsurancedata.size();
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
            item11= (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext11);
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