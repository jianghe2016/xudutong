package com.xdt.xudutong.adapder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.TrainTicketgetTrainTicket;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
public class ProductRecyclerAdaptertrainresult extends RecyclerView.Adapter<ProductRecyclerAdaptertrainresult.MyHolder> implements View.OnClickListener {

    private List<TrainTicketgetTrainTicket.ContentBean.DataBean> trainticketgettrainticketdata;
    private Context context;
    private LayoutInflater inflater;

    public ProductRecyclerAdaptertrainresult(Context context, List<TrainTicketgetTrainTicket.ContentBean.DataBean> trainticketgettrainticketdata) {
        this.context = context;
        this.trainticketgettrainticketdata = trainticketgettrainticketdata;
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
        View view = inflater.inflate(R.layout.homebuttongroupbuttonfivequeryresultrecycleitem, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.itemone.setText(trainticketgettrainticketdata.get(position).getStation_train_code());
        holder.itemtwo.setText(trainticketgettrainticketdata.get(position).getFrom_station_name());
        holder.itemthree.setText(trainticketgettrainticketdata.get(position).getTo_station_name());
        holder.itemfour.setText(trainticketgettrainticketdata.get(position).getStart_time());
        holder.itemfive.setText("历时 : "+trainticketgettrainticketdata.get(position).getLishi());
        holder.itemsix.setText(trainticketgettrainticketdata.get(position).getArrive_time());
        //截取火车首字母，进行判断
        String station_train_code = trainticketgettrainticketdata.get(position).getStation_train_code();
        int g = station_train_code.indexOf("G");
        int d = station_train_code.indexOf("D");
        int c = station_train_code.indexOf("C");
        if (g != -1 || d != -1||c!=-1) {
            //查找到了，商务，一等。二等
            String swz_num = trainticketgettrainticketdata.get(position).getSwz_num();
            String zy_num = trainticketgettrainticketdata.get(position).getZy_num();
            String ze_num = trainticketgettrainticketdata.get(position).getZe_num();
            if (swz_num.isEmpty()||swz_num.equals("无")) {
                holder.itemten1.setText("商务 : ");
                int color1=context.getResources().getColor(R.color.trainunselect);
                holder.itemten1.setTextColor(color1);
                holder.itemseven.setText("无");

                holder.itemseven.setTextColor(color1);
                holder.mtrainresultitemshapelinear1.setBackgroundResource(R.drawable.shape_trainresult2);
            } else {
                holder.itemten1.setText("商务 : ");
                int color1=context.getResources().getColor(R.color.trainselect);
                holder.itemten1.setTextColor(color1);

                holder.itemseven.setTextColor(color1);
                holder.mtrainresultitemshapelinear1.setBackgroundResource(R.drawable.shape_trainresult);
                holder.itemseven.setText(swz_num);
            }
            if (zy_num.isEmpty()||zy_num.equals("无")) {
                holder.itemten2.setText("一等 : ");
                int color1=context.getResources().getColor(R.color.trainunselect);
                holder.itemten2.setTextColor(color1);

                holder.itemeight.setTextColor(color1);
                holder.itemeight.setText("无");
                holder.mtrainresultitemshapelinear2.setBackgroundResource(R.drawable.shape_trainresult2);
            } else {
                holder.itemten2.setText("一等 : ");
                int color1=context.getResources().getColor(R.color.trainselect);
                holder.itemten2.setTextColor(color1);

                holder.itemeight.setTextColor(color1);
                holder.mtrainresultitemshapelinear2.setBackgroundResource(R.drawable.shape_trainresult);
                holder.itemeight.setText(zy_num);
            }
            if (ze_num.isEmpty()||ze_num.equals("无")) {
                holder.itemten3.setText("二等 : ");
                int color1=context.getResources().getColor(R.color.trainunselect);
                holder.itemten3.setTextColor(color1);

                holder.itemnine.setTextColor(color1);
                holder.itemnine.setText("无");
                holder.mtrainresultitemshapelinear3.setBackgroundResource(R.drawable.shape_trainresult2);
            } else {
                holder.itemten3.setText("二等 : ");
                int color1=context.getResources().getColor(R.color.trainselect);
                holder.itemten3.setTextColor(color1);

                holder.itemnine.setTextColor(color1);
                holder.mtrainresultitemshapelinear3.setBackgroundResource(R.drawable.shape_trainresult);
                holder.itemnine.setText(ze_num);
            }

        } else {
            String rw_num = trainticketgettrainticketdata.get(position).getRw_num();
            String yw_num = trainticketgettrainticketdata.get(position).getYw_num();
            String yz_num = trainticketgettrainticketdata.get(position).getYz_num();
            String wz_num = trainticketgettrainticketdata.get(position).getWz_num();

            if (rw_num.isEmpty()||rw_num.equals("无")) {
                holder.itemten1.setText("软卧 : ");
                int color1=context.getResources().getColor(R.color.trainunselect);
                holder.itemten1.setTextColor(color1);

                holder.itemseven.setTextColor(color1);
                holder.itemseven.setText("无");
                holder.mtrainresultitemshapelinear1.setBackgroundResource(R.drawable.shape_trainresult2);
            } else {
                holder.itemten1.setText("软卧 : ");
                int color1=context.getResources().getColor(R.color.trainselect);
                holder.itemten1.setTextColor(color1);

                holder.itemseven.setTextColor(color1);
                holder.mtrainresultitemshapelinear1.setBackgroundResource(R.drawable.shape_trainresult);
                holder.itemseven.setText(rw_num);
            }
            if (yw_num.isEmpty()||yw_num.equals("无")) {
                holder.itemten2.setText("硬卧 : ");
                int color1=context.getResources().getColor(R.color.trainunselect);
                holder.itemten2.setTextColor(color1);

                holder.itemeight.setTextColor(color1);
                holder.mtrainresultitemshapelinear2.setBackgroundResource(R.drawable.shape_trainresult2);
                holder.itemeight.setText("无");
            } else {
                holder.itemten2.setText("硬卧 : ");
                int color1=context.getResources().getColor(R.color.trainselect);
                holder.itemten2.setTextColor(color1);

                holder.itemeight.setTextColor(color1);
                holder.mtrainresultitemshapelinear2.setBackgroundResource(R.drawable.shape_trainresult);
                holder.itemeight.setText(yw_num);
            }
            if (yz_num.isEmpty()||yz_num.equals("无")) {
                holder.itemten3.setText("硬座 : ");
                int color1=context.getResources().getColor(R.color.trainunselect);
                holder.itemten3.setTextColor(color1);

                holder.itemnine.setTextColor(color1);
                holder.mtrainresultitemshapelinear3.setBackgroundResource(R.drawable.shape_trainresult2);
                holder.itemnine.setText("无");
            } else {
                holder.itemten3.setText("硬座 : ");
                int color1=context.getResources().getColor(R.color.trainselect);
                holder.itemten3.setTextColor(color1);

                holder.itemnine.setTextColor(color1);
                holder.mtrainresultitemshapelinear3.setBackgroundResource(R.drawable.shape_trainresult);
                holder.itemnine.setText(yz_num);
            }
            if (wz_num.isEmpty()||wz_num.equals("无")) {
                holder.itemten4.setText("站票 : ");
                int color1=context.getResources().getColor(R.color.trainunselect);
                holder.itemten4.setTextColor(color1);

                holder.itemten.setTextColor(color1);
                holder.itemten.setText("无");
                holder.mtrainresultitemshapelinear4.setBackgroundResource(R.drawable.shape_trainresult2);
            } else {
                holder.itemten4.setText("站票 : ");
                int color1=context.getResources().getColor(R.color.trainselect);
                holder.itemten4.setTextColor(color1);

                holder.itemten.setTextColor(color1);
                holder.mtrainresultitemshapelinear4.setBackgroundResource(R.drawable.shape_trainresult);
                holder.itemten.setText(wz_num);
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
        return trainticketgettrainticketdata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView itemone;
        private TextView itemtwo;
        private TextView itemthree;
        private TextView itemfour;
        private TextView itemfive;
        private TextView itemsix;
        private TextView itemseven;
        private TextView itemeight;
        private TextView itemnine;
        private TextView itemten;
        private TextView itemten1;
        private TextView itemten2;
        private TextView itemten3;
        private TextView itemten4;
        private final LinearLayout mtrainresultitemshapelinear1;
        private final LinearLayout mtrainresultitemshapelinear2;
        private final LinearLayout mtrainresultitemshapelinear3;
        private final LinearLayout mtrainresultitemshapelinear4;

        public MyHolder(View view) {
            super(view);
            itemone = (TextView) view.findViewById(R.id.trainresultitem1);
            itemtwo = (TextView) view.findViewById(R.id.trainresultitem2);
            itemthree = (TextView) view.findViewById(R.id.trainresultitem3);
            itemfour = (TextView) view.findViewById(R.id.trainresultitem4);
            itemfive = (TextView) view.findViewById(R.id.trainresultitem5);
            itemsix = (TextView) view.findViewById(R.id.trainresultitem6);
            //票数量的填写
            itemseven = (TextView) view.findViewById(R.id.trainresultitem7);
            itemeight = (TextView) view.findViewById(R.id.trainresultitem8);
            itemnine = (TextView) view.findViewById(R.id.trainresultitem9);
            itemten = (TextView) view.findViewById(R.id.trainresultitem10);
            //商务名称的填写
            itemten1 = (TextView) view.findViewById(R.id.trainresultitemnumber1);
            itemten2 = (TextView) view.findViewById(R.id.trainresultitemnumber2);
            itemten3 = (TextView) view.findViewById(R.id.trainresultitemnumber3);
            itemten4 = (TextView) view.findViewById(R.id.trainresultitemnumber4);
            //背景框的修改
            mtrainresultitemshapelinear1 = (LinearLayout) view.findViewById(R.id.trainresultitemshapelinear1);
            mtrainresultitemshapelinear2 = (LinearLayout) view.findViewById(R.id.trainresultitemshapelinear2);
            mtrainresultitemshapelinear3 = (LinearLayout) view.findViewById(R.id.trainresultitemshapelinear3);
            mtrainresultitemshapelinear4 = (LinearLayout) view.findViewById(R.id.trainresultitemshapelinear4);
        }
    }
}