package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.DsepgetGovernmentUnit;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class HomejiguandanweiRecyclerAdapter extends RecyclerView.Adapter<HomejiguandanweiRecyclerAdapter.MyHolder> implements View.OnClickListener {

    private List<DsepgetGovernmentUnit.ContentBean.DataBean> dsepgetgovernmentunitdata;
    private Context context;
    private LayoutInflater inflater;

    public HomejiguandanweiRecyclerAdapter(Context context, List<DsepgetGovernmentUnit.ContentBean.DataBean> dsepgetgovernmentunitdata) {
        this.context = context;
        this.dsepgetgovernmentunitdata = dsepgetgovernmentunitdata;
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
        View view = inflater.inflate(R.layout.home_jiguandanweirecycleviewitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.item1.setText(dsepgetgovernmentunitdata.get(position).getOrgName());
        holder.item2.setText(dsepgetgovernmentunitdata.get(position).getLegalPerson());
        holder.item3.setText(dsepgetgovernmentunitdata.get(position).getUniscId());
        holder.item4.setText(dsepgetgovernmentunitdata.get(position).getOrgAddress());
        holder.item5.setText(dsepgetgovernmentunitdata.get(position).getOrgCategory());
        holder.item6.setText(dsepgetgovernmentunitdata.get(position).getContactName());
        holder.item7.setText(dsepgetgovernmentunitdata.get(position).getContactTel());
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
        return dsepgetgovernmentunitdata.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private TextView item1;
        private TextView item2;
        private TextView item3;
        private TextView item4;
        private TextView item5;
        private TextView item6;
        private TextView item7;

        public MyHolder(View view) {
            super(view);
            item1 = (TextView) view.findViewById(R.id.jiguandetailsitem1);
            item2 = (TextView) view.findViewById(R.id.jiguandetailsitem2);
            item3 = (TextView) view.findViewById(R.id.jiguandetailsitem3);
            item4 = (TextView) view.findViewById(R.id.jiguandetailsitem4);
            item5 = (TextView) view.findViewById(R.id.jiguandetailsitem5);
            item6 = (TextView) view.findViewById(R.id.jiguandetailsitem6);
            item7 = (TextView) view.findViewById(R.id.jiguandetailsitem7);

        }
    }
}