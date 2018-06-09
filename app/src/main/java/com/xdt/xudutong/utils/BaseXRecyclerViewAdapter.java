package com.xdt.xudutong.utils;

import com.alibaba.fastjson.JSONArray;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2017\12\5 0005.
 */

public abstract class BaseXRecyclerViewAdapter<T,VH extends XRecyclerView.ViewHolder> extends XRecyclerView.Adapter<VH>{
    protected JSONArray datas = new JSONArray();

    public void addDatas(JSONArray list) {
        if (datas!=null){
            datas.addAll(list);
        }

    }
    public void setDatas(JSONArray list) {
        if (datas!=null) {
            this.datas = list;
        }
    }

    public void cleanList() {
        if (datas != null) {
            datas.clear();
            notifyDataSetChanged();
        }
    }
}

