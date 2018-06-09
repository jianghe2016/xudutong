package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.DsepgetInstitutionBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class HomeshiyedanweiRecyclerAdapter extends RecyclerView.Adapter<HomeshiyedanweiRecyclerAdapter.MyHolder> implements View.OnClickListener {

    private List<DsepgetInstitutionBean.ContentBean.DataBean> dsepgetinstitutionbeandata;
    private Context context;
    private LayoutInflater inflater;

    public HomeshiyedanweiRecyclerAdapter(Context context, List<DsepgetInstitutionBean.ContentBean.DataBean> dsepgetinstitutionbeandata) {
        this.context = context;
        this.dsepgetinstitutionbeandata = dsepgetinstitutionbeandata;
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
        View view = inflater.inflate(R.layout.home_shiyedanweirecycleviewitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.item1.setText(dsepgetinstitutionbeandata.get(position).getOrgName());
        holder.item2.setText(dsepgetinstitutionbeandata.get(position).getLegalPerson());
        holder.item3.setText(dsepgetinstitutionbeandata.get(position).getUniscId());
        holder.item4.setText(dsepgetinstitutionbeandata.get(position).getContactName());
        holder.item5.setText(dsepgetinstitutionbeandata.get(position).getStaffNo());
        holder.item6.setText(dsepgetinstitutionbeandata.get(position).getOpScope());
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
        return dsepgetinstitutionbeandata.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private TextView item1;
        private TextView item2;
        private TextView item3;
        private TextView item4;
        private TextView item5;
        private TextView item6;

        public MyHolder(View view) {
            super(view);
            item1 = (TextView) view.findViewById(R.id.shiyedetailsitem1);
            item2 = (TextView) view.findViewById(R.id.shiyedetailsitem2);
            item3 = (TextView) view.findViewById(R.id.shiyedetailsitem3);
            item4 = (TextView) view.findViewById(R.id.shiyedetailsitem4);
            item5 = (TextView) view.findViewById(R.id.shiyedetailsitem5);
            item6 = (TextView) view.findViewById(R.id.shiyedetailsitem6);
        }
    }
}