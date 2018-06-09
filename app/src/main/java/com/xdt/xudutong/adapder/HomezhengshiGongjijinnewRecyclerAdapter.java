package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.PubfundgetXcPubfund;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class HomezhengshiGongjijinnewRecyclerAdapter extends RecyclerView.Adapter<HomezhengshiGongjijinnewRecyclerAdapter.MyHolder> implements View.OnClickListener {

    private List<PubfundgetXcPubfund.ContentBean.DataBean> pubfundgetxcpubfunddata;
    private String[] gongjijintext2;
    private Context context;
    private LayoutInflater inflater;

    public HomezhengshiGongjijinnewRecyclerAdapter(Context context, List<PubfundgetXcPubfund.ContentBean.DataBean> pubfundgetxcpubfunddata, String[] gongjijintext2) {
        this.context = context;
        this.pubfundgetxcpubfunddata = pubfundgetxcpubfunddata;
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
        View view = inflater.inflate(R.layout.home_gongjijinnewrecycleview_item, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String unitName = pubfundgetxcpubfunddata.get(position).getUnitName();
        if (!TextUtils.isEmpty(unitName)) {
            holder.item2.setText(unitName);
        }


        String cardID = pubfundgetxcpubfunddata.get(position).getCardID();
        if (!TextUtils.isEmpty(cardID)) {
            holder.item3.setText(gongjijintext2[0]);
            holder.item4.setText(cardID);
        }

        String unitCode = pubfundgetxcpubfunddata.get(position).getUnitCode();
        if (!TextUtils.isEmpty(unitCode)) {
            holder.item5.setText(gongjijintext2[1]);
            holder.item6.setText(unitCode);
        }

        String userCode = pubfundgetxcpubfunddata.get(position).getUserCode();
        if (!TextUtils.isEmpty(userCode)) {
            holder.item7.setText(gongjijintext2[2]);
            holder.item8.setText(userCode);
        }

        String payMonth = pubfundgetxcpubfunddata.get(position).getPayMonth();
        if (!TextUtils.isEmpty(payMonth)) {
            holder.item9.setText(gongjijintext2[3]);
            holder.item10.setText(payMonth);
        }

        String balance = pubfundgetxcpubfunddata.get(position).getBalance();
        if (!TextUtils.isEmpty(balance)) {
            holder.item11.setText(gongjijintext2[4]);
            holder.item12.setText(balance);
        }

         String paydate = pubfundgetxcpubfunddata.get(position).getPaydate();
        if (!TextUtils.isEmpty(paydate)) {
            holder.item13.setText(gongjijintext2[5]);
            holder.item14.setText(paydate);
        }
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
        return pubfundgetxcpubfunddata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
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

        public MyHolder(View view) {
            super(view);
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
        }
    }
}