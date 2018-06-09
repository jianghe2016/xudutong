package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ViewselectView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class SceneryAdapter extends RecyclerView.Adapter<SceneryAdapter.MyHolder> implements View.OnClickListener {

    private final List<ViewselectView.ContentBean.DataBean> viewselectviewdata;
    private Context context;
    private LayoutInflater inflater;
    public SceneryAdapter(Context context, List<ViewselectView.ContentBean.DataBean> viewselectviewdata) {
        this.context = context;
        this.viewselectviewdata = viewselectviewdata;
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
        View view = inflater.inflate(R.layout.locallife_sceneryfirstrecycleitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setText(viewselectviewdata.get(position).getVIEW_NAME());
        holder.itemtwo.setText(viewselectviewdata.get(position).getVIEW_DESC());
        //加载图片
        String view_pic = viewselectviewdata.get(position).getVIEW_PIC();
        //以返回的分号进行切分，得到第一个元素，进行网络请求展示在主页面上
        String[] split = view_pic.split(";");
        String s = split[0];
      /*  for(String str : split){
            System.out.println(str);
        }*/
        Glide.with(context).load(s).into(holder.itemfour);
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
        return viewselectviewdata.size();
    }

     class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private ImageView itemfour;



        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.local_sceneryfirstrecycleitemtext1);
            itemtwo = (TextView) view.findViewById(R.id.local_sceneryfirstrecycleitemtext2);
            itemfour = (ImageView) view.findViewById(R.id.local_sceneryfirstrecycleitemimg1);


        }
    }
}