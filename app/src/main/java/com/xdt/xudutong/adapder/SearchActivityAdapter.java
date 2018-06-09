package com.xdt.xudutong.adapder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ApplloadAppimg;
import com.xdt.xudutong.utils.DataAnalysetwo;
import com.xdt.xudutong.view.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class SearchActivityAdapter extends RecyclerView.Adapter<SearchActivityAdapter.MyHolder> implements View.OnClickListener {

    private List<ApplloadAppimg.ContentBean.DataBean> weathergetweatherdata;
    private String activityname;
    private Context context;
    private LayoutInflater inflater;
        //传进来的搜索到的文字进行变色
    public SearchActivityAdapter(Context context, List<ApplloadAppimg.ContentBean.DataBean> weathergetweatherdata, String activityname) {
        this.context = context;
        this.weathergetweatherdata = weathergetweatherdata;
        this.activityname = activityname;
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
        View view = inflater.inflate(R.layout.home_searchactivity_recycleitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //加载图片
        String itemone = weathergetweatherdata.get(position).getAppImg();
        Bitmap bitmap = DataAnalysetwo.base64ToBitmap(itemone);
        holder.itemone.setImageBitmap(bitmap);
        //获取请求到的数据
        String appName = weathergetweatherdata.get(position).getAppName();
        System.out.println("appNameappName"+appName);
        //输入框
        if(appName.indexOf(activityname) != -1)
        {
            String textStr = "<font color=\"#2575d4\">" + activityname + "</font>";
            String s = appName.replaceAll(activityname, textStr);
            LogUtil.i("替换后",s);
            holder.itemtwo.setText(Html.fromHtml(s));
            System.out.println("包含该字符串");
        }else{
            holder.itemtwo.setText(appName);
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
        return weathergetweatherdata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView itemone;
        private TextView itemtwo;


        public MyHolder(View view) {
            super(view);
            itemone = (ImageView) view.findViewById(R.id.home_search_activityimg1);
            itemtwo = (TextView) view.findViewById(R.id.home_search_activitytext1);


        }
    }
}