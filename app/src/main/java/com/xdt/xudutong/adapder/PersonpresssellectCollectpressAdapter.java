package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.PressselectCollectPress;
import com.xdt.xudutong.utils.BaseXRecyclerViewAdaptertwo;

/**
 * Created by Administrator on 2017/6/17.
 */
public class PersonpresssellectCollectpressAdapter extends BaseXRecyclerViewAdaptertwo  {
    private Context context;
    private LayoutInflater inflater;


    public PersonpresssellectCollectpressAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    // 点击事件的接口设置
    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onDelClick(int collectid,int position);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_grid, parent, false);
        View view = inflater.inflate(R.layout.person_collectnewsqueryitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final MyHolder holder1 = (MyHolder) holder;
        PressselectCollectPress.ContentBean.DataBean pressselectpressdata = (PressselectCollectPress.ContentBean.DataBean) datas.get(position);
        holder1.itemone.setText(pressselectpressdata.getTITLE());
        String pubtime = pressselectpressdata.getPUBTIME();
        final int collecttblid = pressselectpressdata.getID();
        String[] split = pubtime.split(" ");
        holder1.itemtwo.setText(split[0]);

        holder1.mbtnonclicknext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取position
                    int layoutPosition = holder1.getLayoutPosition();
                    mOnItemClickListener.onItemClick(v, layoutPosition);
                }
            }
        });
        holder1.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    int layoutPosition = holder1.getLayoutPosition();
                    mOnItemClickListener.onDelClick(collecttblid,layoutPosition);
                }
            }
        });
        holder.itemView.setTag(position);
    }


   /* @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }*/

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private LinearLayout mbtnonclicknext;
        private TextView itemone;
        private TextView itemtwo;
        private Button btnDelete;
        public MyHolder(View view) {
            super(view);
            mbtnonclicknext = (LinearLayout) view.findViewById(R.id.btnonclicknext);
            itemone = (TextView) view.findViewById(R.id.person_collectnewsqueryitem1);
            itemtwo = (TextView) view.findViewById(R.id.person_collectnewsqueryitem2);
            btnDelete = (Button) view.findViewById(R.id.btnDelete);

        }
    }
    public void removeData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
        if(position != datas.size()){ // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, datas.size() - position);
        }
    }
}