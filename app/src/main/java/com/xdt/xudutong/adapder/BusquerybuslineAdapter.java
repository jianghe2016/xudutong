package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.MobilebusqueryBusLineInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class BusquerybuslineAdapter extends RecyclerView.Adapter<BusquerybuslineAdapter.MyHolder> implements View.OnClickListener {

    private final List<MobilebusqueryBusLineInfo.ContentBean.DataBean.LinedataBean> mobilebusquerybuslineinfolinedata;
    private Context context;
    private LayoutInflater inflater;

    public BusquerybuslineAdapter(Context context, List<MobilebusqueryBusLineInfo.ContentBean.DataBean.LinedataBean> mobilebusquerybuslineinfolinedata) {
        this.context = context;
        this.mobilebusquerybuslineinfolinedata = mobilebusquerybuslineinfolinedata;
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
        View view = inflater.inflate(R.layout.home_buttongroup_button6querybuslineitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        int[] img = new int[]{R.drawable.home_busqi, R.drawable.home_bus_no_station, R.drawable.home_bus_station, R.drawable.home_buszhongdian};
        int[] img2 = new int[]{R.drawable.home_bus_green, R.drawable.home_bus_green, R.drawable.home_busin};
        if (position == 0) {
            holder.itemone.setImageResource(img[0]);
            holder.itemthree.setText("");
        } else if (position == mobilebusquerybuslineinfolinedata.size() + 1) {
            holder.itemone.setImageResource(img[3]);
            holder.itemthree.setText("");
        } else {
            String busImg = mobilebusquerybuslineinfolinedata.get(position - 1).getBusImg();
            String busStation = mobilebusquerybuslineinfolinedata.get(position - 1).getBusStation();
            //加序列号1.2.3.4.5
            int i = position + 2;
            int i1 = (int) i / 2;
            String s = String.valueOf(i1);
            if (!busStation.trim().isEmpty()) {
                holder.itemthree.setText(s + " " + busStation);
            }
            //如果文字长度大于1，车则显示在路上
            if (busStation.length() > 1) {
                holder.itemone.setImageResource(img[2]);
            } else {
                holder.itemone.setImageResource(img[1]);
            }
            //如果。。。。显示小绿车
            if (busImg.trim().equals("1") && busStation.equals("")) {
                holder.itemtwo.setImageResource(img2[1]);
            } else if (busImg.trim().equals("1") && busStation.length() > 1) {
                holder.itemone.setImageResource(img2[2]);
                holder.itemtwo.setImageResource(img2[0]);
            }
            //显示起点
            // if (s.equals("1") && busStation.length() > 1) {
            //     holder.itemone.setImageResource(img[0]);
            //}
            int i2 = mobilebusquerybuslineinfolinedata.size() / 2 + 1;
            String s1 = String.valueOf(i2);

        }
        holder.itemView.setTag(position);
       /* holder.itemtwo.setTag(img2[1]);*/
        holder.setIsRecyclable(false);
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
        return mobilebusquerybuslineinfolinedata.size() + 2;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        //起点
        private ImageView itemone;
        //站
        private ImageView itemtwo;
        //内容
        private TextView itemthree;

        public MyHolder(View view) {
            super(view);
            itemone = (ImageView) view.findViewById(R.id.home_buttongroupbutton6querybuslineimg1);
            itemtwo = (ImageView) view.findViewById(R.id.home_buttongroupbutton6querybuslineimg2);
            itemthree = (TextView) view.findViewById(R.id.home_buttongroupbutton6querybuslinetext1);
        }
    }
}