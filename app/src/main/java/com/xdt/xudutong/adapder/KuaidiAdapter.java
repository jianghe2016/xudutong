package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.KuaidiqueryKuaidiInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class KuaidiAdapter extends RecyclerView.Adapter<KuaidiAdapter.MyHolder> implements View.OnClickListener {

    private List<KuaidiqueryKuaidiInfo.ContentBean.DataBeanX.DataBean> kuaidiquerykuaidiinfodata;
    private Context context;
    private LayoutInflater inflater;
    public KuaidiAdapter(Context context, List<KuaidiqueryKuaidiInfo.ContentBean.DataBeanX.DataBean> kuaidiquerykuaidiinfodata) {
        this.context = context;
        this.kuaidiquerykuaidiinfodata = kuaidiquerykuaidiinfodata;
        inflater = LayoutInflater.from(context);
    }


    // 点击事件的接口设置
    private OnItemClickListener mOnItemClickListener = null;



    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_kuaidiitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setText(kuaidiquerykuaidiinfodata.get(position).getContext());
        holder.itemtwo.setText(kuaidiquerykuaidiinfodata.get(position).getTime());
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
        return kuaidiquerykuaidiinfodata.size();
    }

     class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.kuaidiitem1);
            itemtwo = (TextView) view.findViewById(R.id.kuaidiitem2);


        }
    }
}