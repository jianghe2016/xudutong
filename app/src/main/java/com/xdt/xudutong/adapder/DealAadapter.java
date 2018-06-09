package com.xdt.xudutong.adapder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xdt.xudutong.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class DealAadapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public DealAadapter(Context context, List<String> list) {
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
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.deal_item, null);
            vh.mTvType = (TextView) convertView.findViewById(R.id.tv_type);
            vh.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
            vh.mTvTime = (TextView) convertView.findViewById(R.id.tv_time);
            vh.mTvJine = (TextView) convertView.findViewById(R.id.tv_jine);
            vh.mTvDealType = (TextView) convertView.findViewById(R.id.tv_deal_type);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder {
        TextView mTvType;
        TextView mTvName;
        TextView mTvTime;
        TextView mTvJine;
        TextView mTvDealType;
    }
}
