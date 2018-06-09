package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Administrator on 2017/6/17.
 */
public class PersonmerchansearchNetIdone1Adapter extends RecyclerView.Adapter<PersonmerchansearchNetIdone1Adapter.MyHolder> implements View.OnClickListener {

    private JSONArray onenetsize;
    private Context context;
    private LayoutInflater inflater;


    // 点击事件的接口设置
    private OnItemClickListener mOnItemClickListener = null;

    public PersonmerchansearchNetIdone1Adapter(Context context, JSONArray onenetsize) {
        this.context = context;
        this.onenetsize = onenetsize;
        inflater = LayoutInflater.from(context);
    }

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.person_merchant_searchnetid_recycleviewone1_item, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        try {
            String netname = onenetsize.getJSONObject(position).getString("netname");
            holder.itemone.setText(netname);
        } catch (JSONException e) {
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
        return onenetsize.length();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;

        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.person_merchant_searchnetid_recycleviewone1_item1text);
        }
    }
}