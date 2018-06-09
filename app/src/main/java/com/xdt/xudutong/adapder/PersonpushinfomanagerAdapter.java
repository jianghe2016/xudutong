package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.PushfindPush;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class PersonpushinfomanagerAdapter extends RecyclerView.Adapter<PersonpushinfomanagerAdapter.MyHolder> implements View.OnClickListener {

    private List<PushfindPush.ContentBean.DataBean> pushfindPushdata;
    private Context context;
    private LayoutInflater inflater;

    public PersonpushinfomanagerAdapter(Context context, List<PushfindPush.ContentBean.DataBean> pushfindPushdata) {
        this.context = context;
        this.pushfindPushdata = pushfindPushdata;

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
        //  View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_grid, parent, false);
        View view = inflater.inflate(R.layout.pushinfomanagerrecycleview_item, parent, false);
        MyHolder holder = new MyHolder(view);

        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setText(pushfindPushdata.get(position).getTitle());
        String pushTime = pushfindPushdata.get(position).getPushTime();
        if (!TextUtils.isEmpty(pushTime)) {
            String[] split = pushTime.split(" ");
            String s = split[1];
            String[] split1 = s.split(":");
            String s1 = split1[0];
            String s2 = split1[1];
            holder.itemtwo.setText(s1 + ":" + s2);
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
        return pushfindPushdata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;

        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.pushinfomanagerrecycleview_itemtext1);
            itemtwo = (TextView) view.findViewById(R.id.pushinfomanagerrecycleview_itemtext2);

        }
    }
}