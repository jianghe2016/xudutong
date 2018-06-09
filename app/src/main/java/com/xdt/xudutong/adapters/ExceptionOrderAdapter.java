package com.xdt.xudutong.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ExceptionOrderBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */

public class ExceptionOrderAdapter extends BaseAdapter {

    private List<ExceptionOrderBean.ContentEntity.DataEntity> mList;
    private Context mContext;

    public ExceptionOrderAdapter(List<ExceptionOrderBean.ContentEntity.DataEntity> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 :mList.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exception_order_item,null);
            vh = new ViewHolder();
            vh.mTvBikeNum = (TextView) convertView.findViewById(R.id.tv_bike_num);
            vh.mTvTime = (TextView) convertView.findViewById(R.id.tv_time);
            vh.mTvYuanyin = (TextView) convertView.findViewById(R.id.tv_yuanyin);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        ExceptionOrderBean.ContentEntity.DataEntity mDataEntity = mList.get(position);
        vh.mTvBikeNum.setText(mDataEntity.getBikesn());
        vh.mTvTime.setText(mDataEntity.getLeasetime());
        vh.mTvYuanyin.setText(mDataEntity.getExceptionType());
        return convertView;
    }

    static class ViewHolder {
        TextView mTvBikeNum;
        TextView mTvTime;
        TextView mTvYuanyin;
    }
}
