package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.DsepgetEnterprise;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class HomeqiyegerenRecyclerAdapter extends RecyclerView.Adapter<HomeqiyegerenRecyclerAdapter.MyHolder> implements View.OnClickListener {

    private List<DsepgetEnterprise.ContentBean.DataBean> dsepgetenterprisedata;
    private String[] yanglaolist;
    private Context context;
    private LayoutInflater inflater;

    public HomeqiyegerenRecyclerAdapter(Context context, List<DsepgetEnterprise.ContentBean.DataBean> dsepgetenterprisedata, String[] yanglaolist) {
        this.context = context;
        this.dsepgetenterprisedata = dsepgetenterprisedata;
        this.yanglaolist = yanglaolist;
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
        View view = inflater.inflate(R.layout.home_getirecycleview_item, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.item1.setText(yanglaolist[0]);
        holder.item2.setText(dsepgetenterprisedata.get(position).getUniscId());
        holder.item3.setText(yanglaolist[1]);
        holder.item4.setText(dsepgetenterprisedata.get(position).getRegNo());
        holder.item5.setText(yanglaolist[2]);
        holder.item6.setText(dsepgetenterprisedata.get(position).getRegOrgName());
        holder.item7.setText(yanglaolist[3]);
        holder.item8.setText(dsepgetenterprisedata.get(position).getRegStatus());
        holder.item9.setText(yanglaolist[4]);
        holder.item10.setText(dsepgetenterprisedata.get(position).getOpScope());

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
        return dsepgetenterprisedata.size();
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
        public MyHolder(View view) {
            super(view);
            item1 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext1);
            item2 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext2);
            item3 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext3);
            item4 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext4);
            item5 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext5);
            item6 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext6);
            item7 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext7);
            item8 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext8);
            item9 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext9);
            item10 = (TextView) view.findViewById(R.id.home_yanglaorecycleview_itemtext10);


        }
    }
}