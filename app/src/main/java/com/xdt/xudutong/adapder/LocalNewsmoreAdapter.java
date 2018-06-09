package com.xdt.xudutong.adapder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.PressselectPress;
import com.xdt.xudutong.utils.BaseXRecyclerViewAdaptertwo;

/**
 * Created by Administrator on 2017/6/17.
 */
public class LocalNewsmoreAdapter extends BaseXRecyclerViewAdaptertwo {

    //define interface设置点击事件的借口的两个不同的方法
    public interface OnItemClickListener {
        void onItemClick(TextView textView, int position);

    }

    public interface OnitemthreeListener {
        void mitemthreeClick(ImageView itemthree, String isCollect, int position);
    }

    // 点击事件的接口设置
    private OnItemClickListener mOnItemClickListener;
    private OnitemthreeListener mitemthreeClick;

    //文字点击
    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    //收藏图片点击
    public void setthreeClicklistener(OnitemthreeListener mitemthreeClick) {
        this.mitemthreeClick = mitemthreeClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locallifenewsmoreitem, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewholder, int position) {
        final MyHolder holder =(MyHolder)viewholder;
        PressselectPress.ContentBean.DataBean pressselectpressdata = (PressselectPress.ContentBean.DataBean) datas.get(position);

      //  final String isCollect = pressselectpressdata.get(position).getIsCollect();
        final String isCollect = pressselectpressdata.getIsCollect();
        holder.itemone.setText(pressselectpressdata.getTITLE());
        String pubtime = pressselectpressdata.getPUBTIME();
        String[] split = pubtime.split(" ");
        String s = split[0];
        holder.itemtwo.setText(s);
        if (isCollect.equals("1")) {
            int img = R.drawable.locallifenewsmorecollect2;
            holder.itemthree.setImageResource(img);
        } else {
            int img = R.drawable.locallifenewsmorecollect;
            holder.itemthree.setImageResource(img);
        }
        holder.itemone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* int pos = holder.getLayoutPosition();
                onItemClickLitener.onitemclick(holder.iv, pos);*/
                int layoutPosition = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemone, layoutPosition);
            }
        });
        holder.itemfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int layoutPosition = holder.getLayoutPosition();
                mitemthreeClick.mitemthreeClick(holder.itemthree, isCollect, layoutPosition);
            }
        });
        holder.itemView.setTag(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private ImageView itemthree;
        private LinearLayout itemfour;
        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.locallifenewsmoretext1);
            itemtwo = (TextView) view.findViewById(R.id.locallifenewsmoretext2);
            itemthree = (ImageView) view.findViewById(R.id.locallifenewsmoreimgcollect);
            itemfour = (LinearLayout) view.findViewById(R.id.locallifenewsmoreimgcollectlinearlayout);
        }
    }
}