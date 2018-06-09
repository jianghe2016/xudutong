package com.xdt.xudutong.adapder;

import android.support.v7.widget.RecyclerView;
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
public class HomeMerchantrecordrecycleveiwAdapter1 extends BaseXRecyclerViewAdapter implements View.OnClickListener {
    private View view;
    private String getmerchantslecturl;

    public HomeMerchantrecordrecycleveiwAdapter1(String getmerchantslecturl) {
        this.getmerchantslecturl = getmerchantslecturl;
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
        if (getmerchantslecturl.equals("merchant/transactionDetails"))//充值类交易明细
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_merchantrecordseearchrecycleview1item, parent, false);
            holder = new ViewHolderOne(view);
        } else if (getmerchantslecturl.equals("merchant/consumeRecord")) //消费类交易明细接口
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_merchantrecordseearchrecycleview4item, parent, false);
            holder = new ViewHolderFour(view);
        } else if (getmerchantslecturl.equals("merchant/consumeSummary") || getmerchantslecturl.equals("merchant/rechargeSummary")) {
            //消费类交易汇总，充值类交易汇总
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_merchantrecordseearchrecycleview2item, parent, false);
            holder = new ViewHolderTwo(view);
        } else if (getmerchantslecturl.equals("merchant/consumerSuspiciousDetails"))//消费可疑明细报表接口
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_merchantrecordseearchrecycleview5item, parent, false);
            holder = new ViewHolderFive(view);
        } else if (getmerchantslecturl.equals("merchant/consumerSuspiciousSummary") //消费消费可疑交易汇总接口
                || getmerchantslecturl.equals("merchant/returnedPurchase")//退货类交易明细接口
                || getmerchantslecturl.equals("merchant/consumeErrorCorrection")//消费纠错明细
                || getmerchantslecturl.equals("merchant/consumeErrorCorrectionSummary")//消费纠错交易汇总接口
                || getmerchantslecturl.equals("merchant/rechargeRepeal")//充值类撤销交易明细接口
                || getmerchantslecturl.equals("merchant/rechargeRepealSummary")) //充值类撤销交易明细接口
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_merchantrecordseearchrecycleview3item, parent, false);
            holder = new ViewHolderThree(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_merchantrecordseearchrecycleview4item, parent, false);
            holder = new ViewHolderFour(view);
        }
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {
            if (getmerchantslecturl.equals("merchant/transactionDetails")) {//充值类交易明细
                ViewHolderOne holderOne = (ViewHolderOne) holder;
                holderOne.itemone.setText("¥  " + datas.getJSONObject(position).get("opfare").toString()+"元");
                holderOne.itemtwo.setText(datas.getJSONObject(position).get("opDT").toString());
                holderOne.itemthree.setText(datas.getJSONObject(position).get("uploadTime").toString());
                holderOne.itemfour.setText("许都通卡号：" + datas.getJSONObject(position).get("xdtNo").toString());
                holderOne.itemfive.setText("姓 名：" + datas.getJSONObject(position).get("username").toString());
                holderOne.itemsix.setText("交易类型：" + datas.getJSONObject(position).get("dscrp").toString());
                holderOne.itemseven.setText("商户名称：" + datas.getJSONObject(position).get("netname").toString());
                holderOne.itemeight.setText("操 作 员：" + datas.getJSONObject(position).get("empName").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/consumeRecord"))//消费类交易明细接口
            {
                ViewHolderFour holderfour = (ViewHolderFour) holder;
                holderfour.itemone.setText("¥  " + datas.getJSONObject(position).get("opfare").toString());
                holderfour.itemtwo.setText(datas.getJSONObject(position).get("opDT").toString());
                holderfour.itemthree.setText(datas.getJSONObject(position).get("uploadTime").toString());
                holderfour.itemfour.setText("卡账号：" + datas.getJSONObject(position).get("customerId").toString());
                holderfour.itemfive.setText("交易类型：" + datas.getJSONObject(position).get("accdscrp").toString());
                holderfour.itemsix.setText("消费终端：" + datas.getJSONObject(position).get("termname").toString());
                holderfour.itemseven.setText("商户名称：" + datas.getJSONObject(position).get("netname").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/consumeSummary")) {
                //消费类交易汇总
                ViewHolderTwo holderTwo = (ViewHolderTwo) holder;
                holderTwo.itemone.setText("¥  " + datas.getJSONObject(position).get("countMoney").toString());
                holderTwo.itemtwo.setText("商户编号：" + datas.getJSONObject(position).get("netid").toString());
                holderTwo.itemthree.setText("商户名称：" + datas.getJSONObject(position).get("netname").toString());
                holderTwo.itemfour.setText("交易次数：" + datas.getJSONObject(position).get("count").toString());
                holderTwo.itemfive.setText("交易代码：" + datas.getJSONObject(position).get("acccode").toString());
                holderTwo.itemsix.setText("交易描述：" + datas.getJSONObject(position).get("accdscrp").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/rechargeSummary")) {
                //充值类交易汇总
                ViewHolderTwo holderTwo = (ViewHolderTwo) holder;
                holderTwo.itemone.setText("¥  " + datas.getJSONObject(position).get("countMoney").toString());
                holderTwo.itemtwo.setText("网点编号：" + datas.getJSONObject(position).get("netid").toString());
                holderTwo.itemthree.setText("网点名称：" + datas.getJSONObject(position).get("netname").toString());
                holderTwo.itemfour.setText("交易科目：" + datas.getJSONObject(position).get("acccode").toString());
                holderTwo.itemfive.setText("交易描述：" + datas.getJSONObject(position).get("dscrp").toString());
                holderTwo.itemsix.setText("交易次数：" + datas.getJSONObject(position).get("count").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/consumerSuspiciousDetails")) //消费可疑明细报表接口
            {
                ViewHolderFive holderfive = (ViewHolderFive) holder;
                holderfive.itemone.setText("¥  " + datas.getJSONObject(position).get("opfare").toString());
                holderfive.itemtwo.setText(datas.getJSONObject(position).get("opDT").toString());
                holderfive.itemthree.setText("商户名称：" + datas.getJSONObject(position).get("netname").toString());
                holderfive.itemfour.setText("交易流水号：" + datas.getJSONObject(position).get("cstaccfc").toString());
                holderfive.itemfive.setText("卡账号：" + datas.getJSONObject(position).get("customerId").toString());
                holderfive.itemsix.setText("交易终端：" + datas.getJSONObject(position).get("termname").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/consumerSuspiciousSummary"))
            //消费消费可疑交易汇总接口
            {
                ViewHolderThree holderthree = (ViewHolderThree) holder;
                holderthree.itemone.setText("¥  " + datas.getJSONObject(position).get("countMoney").toString());
                holderthree.itemtwo.setText("商户编号：" + datas.getJSONObject(position).get("netid").toString());
                holderthree.itemthree.setText("可疑次数：" + datas.getJSONObject(position).get("count").toString());
                holderthree.itemfour.setText("商户名称：" + datas.getJSONObject(position).get("netname").toString());
                holderthree.itemfive.setText("可疑类型名称：" + datas.getJSONObject(position).get("accdscrp").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/returnedPurchase"))
            //退货类交易明细接口
            {
                ViewHolderThree holderthree = (ViewHolderThree) holder;
                holderthree.itemone.setText("¥  " + datas.getJSONObject(position).get("opfare").toString());
                holderthree.itemtwo.setText("交易时间：" + datas.getJSONObject(position).get("opDT").toString());
                holderthree.itemthree.setText("卡账号：" + datas.getJSONObject(position).get("customerId").toString());
                holderthree.itemfour.setText("交易终端：" + datas.getJSONObject(position).get("termname").toString());
                holderthree.itemfive.setText("商户名称：" + datas.getJSONObject(position).get("netname").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/consumeErrorCorrection")) {//消费纠错明细
                ViewHolderThree holderthree = (ViewHolderThree) holder;
                holderthree.itemone.setText("¥  " + datas.getJSONObject(position).get("opfare").toString());
                holderthree.itemtwo.setText("交易日期：" + datas.getJSONObject(position).get("opDT").toString());
                holderthree.itemthree.setText("交易描述：" + datas.getJSONObject(position).get("accdscrp").toString());
                holderthree.itemfour.setText("卡账号：" + datas.getJSONObject(position).get("customerId").toString());
                holderthree.itemfive.setText("网点：" + datas.getJSONObject(position).get("netname").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/consumeErrorCorrectionSummary")) {//消费纠错交易汇总
                ViewHolderThree holderthree = (ViewHolderThree) holder;
                holderthree.itemone.setText("¥  " + datas.getJSONObject(position).get("countMoney").toString());
                holderthree.itemtwo.setText("网点名称：" + datas.getJSONObject(position).get("netname").toString());
                holderthree.itemthree.setText("卡账号：" + datas.getJSONObject(position).get("customerId").toString());
                holderthree.itemfour.setText("网点编号：" + datas.getJSONObject(position).get("netid").toString());
                holderthree.itemfive.setText("纠错笔数：" + datas.getJSONObject(position).get("count").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/rechargeRepealSummary")) {//充值类撤销交易汇总
                ViewHolderThree holderthree = (ViewHolderThree) holder;
                holderthree.itemone.setText("¥  " + datas.getJSONObject(position).get("countMoney").toString());
                holderthree.itemtwo.setText("网点编号：" + datas.getJSONObject(position).get("netid").toString());
                holderthree.itemthree.setText("客户账号：" + datas.getJSONObject(position).get("customerid").toString());
                holderthree.itemfour.setText("网店名称：" + datas.getJSONObject(position).get("netname").toString());
                holderthree.itemfive.setText("撤销笔数：" + datas.getJSONObject(position).get("count").toString());
                holder.itemView.setTag(position);
            } else if (getmerchantslecturl.equals("merchant/rechargeRepeal")) {//充值类撤销交易明细接口
                ViewHolderThree holderthree = (ViewHolderThree) holder;
                holderthree.itemone.setText("¥  " + datas.getJSONObject(position).get("opfare").toString());
                holderthree.itemtwo.setText("交易日期：" + datas.getJSONObject(position).get("opdt").toString());
                holderthree.itemthree.setText("操作员：" + datas.getJSONObject(position).get("editperson").toString());
                holderthree.itemfour.setText("客服账号：" + datas.getJSONObject(position).get("customerid").toString());
                holderthree.itemfive.setText("商户名称：" + datas.getJSONObject(position).get("netname").toString());
                holder.itemView.setTag(position);
            } else {
                ViewHolderOne holderOne1 = (ViewHolderOne) holder;
                holderOne1.itemone.setText("¥  " + datas.getJSONObject(position).get("opfare").toString());
                holderOne1.itemtwo.setText(datas.getJSONObject(position).get("opDT").toString());
                holderOne1.itemthree.setText(datas.getJSONObject(position).get("uploadTime").toString());
                holderOne1.itemfive.setText("卡账号：" + datas.getJSONObject(position).get("customerId").toString());
                holderOne1.itemsix.setText("交易类型：" + datas.getJSONObject(position).get("accdscrp").toString());
                holderOne1.itemseven.setText("消费终端：" + datas.getJSONObject(position).get("termname").toString());
                holderOne1.itemeight.setText("商户名称：" + datas.getJSONObject(position).get("netname").toString());
                holder.itemView.setTag(position);
            }

        } catch (Exception e) {
            LogUtil.d("商家详情页面被捕捉了222", e.toString());

        }


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

    class ViewHolderOne extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;
        private TextView itemsix;
        private TextView itemseven;
        private TextView itemeight;

        public ViewHolderOne(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.home1_merchantrecordseearchleft1);
            itemtwo = (TextView) view.findViewById(R.id.home1_merchantrecordseearchleft2);
            itemthree = (TextView) view.findViewById(R.id.home1_merchantrecordseearchleft3);
            itemfour = (TextView) view.findViewById(R.id.home1_merchantrecordseearchright1);
            itemfive = (TextView) view.findViewById(R.id.home1_merchantrecordseearchright2);
            itemsix = (TextView) view.findViewById(R.id.home1_merchantrecordseearchright3);
            itemseven = (TextView) view.findViewById(R.id.home1_merchantrecordseearchright4);
            itemeight = (TextView) view.findViewById(R.id.home1_merchantrecordseearchright5);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;
        private TextView itemsix;

        public ViewHolderTwo(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.home2_merchantrecordseearchleft1);
            itemtwo = (TextView) view.findViewById(R.id.home2_merchantrecordseearchright1);
            itemthree = (TextView) view.findViewById(R.id.home2_merchantrecordseearchright2);
            itemfour = (TextView) view.findViewById(R.id.home2_merchantrecordseearchright3);
            itemfive = (TextView) view.findViewById(R.id.home2_merchantrecordseearchright4);
            itemsix = (TextView) view.findViewById(R.id.home2_merchantrecordseearchright5);
        }
    }

    class ViewHolderThree extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;

        public ViewHolderThree(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.home3_merchantrecordseearchleft1);
            itemtwo = (TextView) view.findViewById(R.id.home3_merchantrecordseearchleft2);
            itemthree = (TextView) view.findViewById(R.id.home3_merchantrecordseearchleft3);
            itemfour = (TextView) view.findViewById(R.id.home3_merchantrecordseearchright1);
            itemfive = (TextView) view.findViewById(R.id.home3_merchantrecordseearchright2);
        }
    }

    class ViewHolderFour extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;
        private TextView itemsix;
        private TextView itemseven;

        public ViewHolderFour(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.home4_merchantrecordseearchleft1);
            itemtwo = (TextView) view.findViewById(R.id.home4_merchantrecordseearchleft2);
            itemthree = (TextView) view.findViewById(R.id.home4_merchantrecordseearchleft3);
            itemfour = (TextView) view.findViewById(R.id.home4_merchantrecordseearchright2);
            itemfive = (TextView) view.findViewById(R.id.home4_merchantrecordseearchright3);
            itemsix = (TextView) view.findViewById(R.id.home4_merchantrecordseearchright4);
            itemseven = (TextView) view.findViewById(R.id.home4_merchantrecordseearchright5);
        }
    }

    class ViewHolderFive extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;
        private TextView itemsix;

        public ViewHolderFive(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.home5_merchantrecordseearchleft1);
            itemtwo = (TextView) view.findViewById(R.id.home5_merchantrecordseearchleft3);
            itemthree = (TextView) view.findViewById(R.id.home5_merchantrecordseearchright2);
            itemfour = (TextView) view.findViewById(R.id.home5_merchantrecordseearchright3);
            itemfive = (TextView) view.findViewById(R.id.home5_merchantrecordseearchright4);
            itemsix = (TextView) view.findViewById(R.id.home5_merchantrecordseearchright5);
        }
    }
}