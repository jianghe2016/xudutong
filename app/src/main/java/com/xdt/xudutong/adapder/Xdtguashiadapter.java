package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.CitygetUserCards;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class Xdtguashiadapter extends RecyclerView.Adapter<Xdtguashiadapter.MyHolder> implements View.OnClickListener {

    private List<CitygetUserCards.ResponseBean.BodyBean> citygetusercardsbody;
    private String name;
    private String idcardNo;
    private String phoneNo;
    private Context context;
    private LayoutInflater inflater;

    public Xdtguashiadapter(Context context, List<CitygetUserCards.ResponseBean.BodyBean> citygetusercardsbody, String name, String idcardNo, String phoneNo) {
        this.context = context;
        this.citygetusercardsbody = citygetusercardsbody;
        this.name = name;
        this.idcardNo = idcardNo;
        this.phoneNo = phoneNo;
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
        View view = inflater.inflate(R.layout.xdt_guashi_recycleitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String cityCardno = citygetusercardsbody.get(position).getCityCardno();
        //截取卡号
        String substring1 = cityCardno.substring(0, 1);
        String substring2 = "";
        //截取身份证号
        String substring3 = idcardNo.substring(0, 6);
        String substring4 = idcardNo.substring(idcardNo.length() - 3, idcardNo.length());
        //截取电话号码
        String substring5 = phoneNo.substring(0, 3);
        String substring6 = phoneNo.substring(phoneNo.length() - 4, phoneNo.length());
        holder.itemone.setText("姓         名 : " + name);
        holder.itemtwo.setText("身份证号 : " + substring3 + "*******" + substring4);
        holder.itemthree.setText("卡         号 : " + substring1 + "*******" + substring2);
        holder.itemfour.setText("手机号 : " + substring5 + "****" + substring6);
        // holder.itemone.setText(list.get(position).toString());
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
        return citygetusercardsbody.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;

        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.xdt_guashi_recycleviewitem1);
            itemtwo = (TextView) view.findViewById(R.id.xdt_guashi_recycleviewitem2);
            itemthree = (TextView) view.findViewById(R.id.xdt_guashi_recycleviewitem3);
            itemfour = (TextView) view.findViewById(R.id.xdt_guashi_recycleviewitem4);

        }
    }
}