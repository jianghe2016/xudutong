package com.xdt.xudutong.utils;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\12\6 0006.
 */

public abstract class BaseXRecyclerViewAdaptertwo<T,VH extends XRecyclerView.ViewHolder> extends XRecyclerView.Adapter<VH>{
    protected List<T> datas = new ArrayList<T>();

    public void addDatas(List<T> list) {
        if (datas!=null){
            datas.addAll(list);
            notifyDataSetChanged();
        }

    }
    public void setDatas(List<T> list) {
        if (datas!=null) {
            this.datas = list;
            notifyDataSetChanged();
        }
    }

    public void cleanList() {
        if (datas != null) {
            datas.clear();
            notifyDataSetChanged();
        }
    }
}
