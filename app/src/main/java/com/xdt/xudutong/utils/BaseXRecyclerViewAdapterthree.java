package com.xdt.xudutong.utils;

import com.alibaba.fastjson.JSONArray;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\12\6 0006.
 */
//满足标记已读需求
public abstract class BaseXRecyclerViewAdapterthree<T,VH extends XRecyclerView.ViewHolder> extends XRecyclerView.Adapter<VH>{
    protected JSONArray datas = new JSONArray();
    protected List<T> datas2 = new ArrayList<T>();

    public void addDatas(JSONArray list,List<T> list2) {
        if (datas!=null){
            datas.addAll(list);
            datas2.addAll(list2);
        }

    }
    public void setDatas(JSONArray list,List<T> list2) {
        if (datas!=null) {
            this.datas = list;
            this.datas2 = list2;
        }
    }

    public void cleanList() {
        if (datas != null&&datas2 != null) {
            datas.clear();
            datas2.clear();
            notifyDataSetChanged();
        }
    }
}
