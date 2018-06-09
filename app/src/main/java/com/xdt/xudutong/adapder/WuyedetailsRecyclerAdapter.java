package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ZwjfeegetBillInfos;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class WuyedetailsRecyclerAdapter extends RecyclerView.Adapter<WuyedetailsRecyclerAdapter.MyHolder> implements View.OnClickListener {
    private List<ZwjfeegetBillInfos.ContentBean.DataBean.FeeInfosBean> zwjfeegetbillinfosfee_infos;
    private Context context;
    private LayoutInflater inflater;

    public WuyedetailsRecyclerAdapter(Context context, List<ZwjfeegetBillInfos.ContentBean.DataBean.FeeInfosBean> zwjfeegetbillinfosfee_infos) {
        this.context = context;
        this.zwjfeegetbillinfosfee_infos = zwjfeegetbillinfosfee_infos;
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
        View view = inflater.inflate(R.layout.home_cardgroup_button4nextdetailsrecycleviewitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setText(zwjfeegetbillinfosfee_infos.get(position).getFee_item_name());
        String fee_begin_date = zwjfeegetbillinfosfee_infos.get(position).getFee_begin_date();
        String fee_end_date = zwjfeegetbillinfosfee_infos.get(position).getFee_end_date();
        if (fee_begin_date!=null&&fee_end_date!=null){
            holder.itemtwo.setText("计费区间:  "+fee_begin_date+"~"+fee_end_date);
        }else{
            holder.itemtwo.setText("计费区间:  "+"暂无");
        }
        holder.itemthree.setText("应付:  "+zwjfeegetbillinfosfee_infos.get(position).getReceivable());
        holder.itemfour.setText("优惠金额:  "+zwjfeegetbillinfosfee_infos.get(position).getPrivilege());
        holder.itemfive.setText("实收:  "+zwjfeegetbillinfosfee_infos.get(position).getProceeds());
        holder.itemView.setTag(position);
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
        return zwjfeegetbillinfosfee_infos.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;

        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.home_cardgroup_button4nextdetailsrecycleviewitem1);
            itemtwo = (TextView) view.findViewById(R.id.home_cardgroup_button4nextdetailsrecycleviewitem2);
            itemthree = (TextView) view.findViewById(R.id.home_cardgroup_button4nextdetailsrecycleviewitem3);
            itemfour = (TextView) view.findViewById(R.id.home_cardgroup_button4nextdetailsrecycleviewitem4);
            itemfive = (TextView) view.findViewById(R.id.home_cardgroup_button4nextdetailsrecycleviewitem5);
        }
    }
}