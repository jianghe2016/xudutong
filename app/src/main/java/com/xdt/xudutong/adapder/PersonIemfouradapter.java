package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.CitygetUserCards;
import com.xdt.xudutong.view.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class PersonIemfouradapter extends RecyclerView.Adapter<PersonIemfouradapter.MyHolder> implements View.OnClickListener {

    private List<CitygetUserCards.ResponseBean.BodyBean> citygetusercardsbody;
    private Context context;
    private LayoutInflater inflater;
    private int guashiflag;
    private MyHolder holder;

    public PersonIemfouradapter(Context context, List<CitygetUserCards.ResponseBean.BodyBean> citygetusercardsbody, int guashiflag) {
        this.context = context;
        this.citygetusercardsbody = citygetusercardsbody;
        this.guashiflag = guashiflag;
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
        if (guashiflag == 1) {
            //1.卡挂失
            View view = inflater.inflate(R.layout.personitenfourguashi_recycleitem, parent, false);
            holder = new MyHolder(view);
            view.setOnClickListener(this);
        } else {
            //2.卡管理
            View view = inflater.inflate(R.layout.personitenfourrecycleitem, parent, false);
            holder = new MyHolder(view);
            //将创建的View注册点击事件
            view.setOnClickListener(this);
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String cityCardno = citygetusercardsbody.get(position).getCityCardno();
        LogUtil.i("获得的卡号==", cityCardno);
        String substring2 = cityCardno.substring(cityCardno.length() - 4, cityCardno.length());
        holder.itemone.setText("***  ***  *** " + substring2);
        int status = citygetusercardsbody.get(position).getStatus();
        if (guashiflag == 1) {
            LogUtil.d("卡状态为======"+position, status +"");
            //标识显示的挂失页面，2选中标识,3挂失成功的标识,
            holder.itemthree.setVisibility(View.VISIBLE);
            if (status==3){
                holder.itemfour.setVisibility(View.VISIBLE);
            }else{
                holder.itemfour.setVisibility(View.GONE);
            }

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
        return citygetusercardsbody == null ? 0 : citygetusercardsbody.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private ImageView itemthree;
        private ImageView itemfour;

        public MyHolder(View view) {
            super(view);
            if (guashiflag == 1) {
                //卡挂失
                itemone = (TextView) view.findViewById(R.id.personitenfourguashirecycleitem1);
                itemthree = (ImageView) view.findViewById(R.id.person_cardmanager_selectcancleguashiimg2);
                itemfour = (ImageView) view.findViewById(R.id.person_cardmanager_selectcancleguashiimg2);

            } else {
                //卡管理，卡解绑
                itemone = (TextView) view.findViewById(R.id.personitenfourrecycleitem1);
            }


        }
    }
}