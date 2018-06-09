package com.xdt.xudutong.adapder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.orhanobut.logger.Logger;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Bean;
import com.xdt.xudutong.widget.ProfitProgerssBar;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class EarningsAdapter extends BaseAdapter {

    private Context mContext;
    private List<Bean> mList;

    public EarningsAdapter(Context context, List<Bean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        Logger.e(mList.size()+"");
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.earnings_item, null);
            vh.mProfitProgerssBar = (ProfitProgerssBar) convertView.findViewById(R.id.profitProgerssBar);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Bean mBean = mList.get(position);
        vh.mProfitProgerssBar.init(mBean.getDataz(),mBean.getDescs(),mBean.getProgress(),mBean.getColors());
        return convertView;
    }

    static class ViewHolder {
        ProfitProgerssBar mProfitProgerssBar;
    }
}
