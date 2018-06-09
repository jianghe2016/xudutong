package com.xdt.xudutong.adapder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Weatherlikecity;

import java.util.List;

/**
 * Created by Administrator on 2017\6\21 0021.
 */

public class Weatheramohuchaxuncityrecycleadapter extends BaseAdapter {
    //ViewHolder静态类
    class ViewHolder {
        public TextView title41;
    }

    private LayoutInflater mInflater = null;
    private List<Weatherlikecity.ContentBean.DataBean> weatherlikecitydata;

    public Weatheramohuchaxuncityrecycleadapter(Context context, List<Weatherlikecity.ContentBean.DataBean> weatherlikecitydata) {
        //根据context上下文加载布局，这里的是Demo17Activity本身，即this
        this.mInflater = LayoutInflater.from(context);
        this.weatherlikecitydata = weatherlikecitydata;
    }

    @Override
    public int getCount() {
        return weatherlikecitydata.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.weathermohuchaxuncityrecycleitem, null);
            holder.title41 = (TextView) convertView.findViewById(R.id.weatheraddmohuchaxunrecycleitem1);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title41.setText(weatherlikecitydata.get(position).getCity());
        return convertView;

    }
}
