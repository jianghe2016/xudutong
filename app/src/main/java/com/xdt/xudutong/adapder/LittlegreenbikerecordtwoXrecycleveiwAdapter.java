package com.xdt.xudutong.adapder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.utils.BaseXRecyclerViewAdapter;
import com.xdt.xudutong.view.LogUtil;

/**
 * Created by Administrator on 2017/6/17.
 */
public class LittlegreenbikerecordtwoXrecycleveiwAdapter extends BaseXRecyclerViewAdapter implements View.OnClickListener {
    private View view;

    public LittlegreenbikerecordtwoXrecycleveiwAdapter() {
    }

    // 点击事件的接口设置
    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.littlegreenbikerecordtwo, parent, false);
        holder = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holderOne1 = (ViewHolder) holder;
        String s = datas.getJSONObject(position).get("leasetime").toString().trim();
        if (!TextUtils.isEmpty(s)){
            String substringtimemonth = s.substring(5, 10);
            String substringyears = s.substring(0, 4);
            //填写月日，和年份
            holderOne1.itemone.setText(substringtimemonth);
            holderOne1.itemfour.setText(substringyears);
        }
        //车卡号
        String s3 = datas.getJSONObject(position).get("bikesn").toString();
        if (!TextUtils.isEmpty(s3)){
            holderOne1.itemtwo.setText(s3);
        }else{
            holderOne1.itemtwo.setText("暂无数据");
        }

        //费用
        String s1 = datas.getJSONObject(position).get("cost").toString();
        if (!TextUtils.isEmpty(s1)){
            holderOne1.itemthree.setText(s1+" 元");
        }else{
            holderOne1.itemthree.setText("0 元");
        }

        // 起始地点
        final String s4 = datas.getJSONObject(position).get("lease_shed").toString();
        if (!TextUtils.isEmpty(s4)) {
            holderOne1.itemfive.setText(s4);
        }else{
            holderOne1.itemfive.setText("暂无数据");
        }

        //结束地点
        String s5 = datas.getJSONObject(position).get("return_shed").toString();
        if (!TextUtils.isEmpty(s5)){
            holderOne1.itemsix.setText(s5);
        }else{
            holderOne1.itemsix.setText("暂无数据");
        }

        //开始时间
        if (!TextUtils.isEmpty(s)){
            String substring = s.substring(11, s.length());
            holderOne1.itemseven.setText(substring);
        }
        //经过时间
        String cost_time1 = datas.getJSONObject(position).get("cost_time").toString();
        LogUtil.e("cost_time1========",cost_time1);
        holderOne1.itemeight.setText(cost_time1+"");
//        if (!TextUtils.isEmpty(cost_time1+"")){
//            int i = Integer.parseInt(cost_time1);
//            holderOne1.itemeight.setText(i/60+" 分");
//        }else{
//            holderOne1.itemeight.setText("0 分");
//        }

        //结束时间
        String s2 = datas.getJSONObject(position).get("returntime").toString();
        if (!TextUtils.isEmpty(s2)){
            String substring = s2.substring(11, s2.length());
            holderOne1.itemnine.setText(substring);
        }else{
            holderOne1.itemnine.setText("暂无数据");
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
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;
        private TextView itemsix;
        private TextView itemseven;
        private TextView itemeight;
        private TextView itemnine;

        public ViewHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.littlegreenbikerecordtextline11);
            itemtwo = (TextView) view.findViewById(R.id.littlegreenbikerecordtextline12);
            itemthree = (TextView) view.findViewById(R.id.littlegreenbikerecordtextline13);
            itemfour = (TextView) view.findViewById(R.id.littlegreenbikerecordtextline21);
            itemfive = (TextView) view.findViewById(R.id.littlegreenbikerecordtextline31);
            itemsix = (TextView) view.findViewById(R.id.littlegreenbikerecordtextline32);
            itemseven = (TextView) view.findViewById(R.id.littlegreenbikerecordtextline41);
            itemeight = (TextView) view.findViewById(R.id.littlegreenbikerecordtextline42);
            itemnine = (TextView) view.findViewById(R.id.littlegreenbikerecordtextline43);
        }
    }
}