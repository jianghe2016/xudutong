package com.xdt.xudutong.adapder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.BankCardSelectBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */

public class BankCardSelectAdapter extends BaseAdapter {

    private Context mContext;
    private List<BankCardSelectBean> mList;

    public BankCardSelectAdapter(Context context, List<BankCardSelectBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.bank_card_select_item,null);
            vh.mIvIcon1 = (ImageView) convertView.findViewById(R.id.iv_icon1);
            vh.mTvSelectName = (TextView) convertView.findViewById(R.id.tv_select_name);
            vh.mLlItem1 = (LinearLayout) convertView.findViewById(R.id.ll_item1);
            vh.mIvIcon2 = (ImageView) convertView.findViewById(R.id.iv_icon2);
            vh.mTvBankName = (TextView) convertView.findViewById(R.id.tv_bank_name);
            vh.mTvDesc = (TextView) convertView.findViewById(R.id.tv_desc);
            vh.mLlItem2 = (LinearLayout) convertView.findViewById(R.id.ll_item2);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView mIvIcon1;
        TextView mTvSelectName;
        LinearLayout mLlItem1;
        ImageView mIvIcon2;
        TextView mTvBankName;
        TextView mTvDesc;
        LinearLayout mLlItem2;
    }
}
