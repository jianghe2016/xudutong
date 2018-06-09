package com.xdt.xudutong.adapder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.utils.BaseXRecyclerViewAdapterthree;
import com.xdt.xudutong.view.LogUtil;

/**
 * Created by Administrator on 2017/6/17.
 */
public class PersoneightlittlegreenpushAdapter extends BaseXRecyclerViewAdapterthree {
    private View view;
    private OnItemClickListener mOnItemClickListener = null;

    public PersoneightlittlegreenpushAdapter() {

    }


    public interface OnItemClickListener {
        void onItemClick(String id, int position);

        void onDelClick(String id, int position);

        void onFlagcircle(String id, int position);
    }

    //以下两个方法为：RecycleItemTouchHelper.ItemTouchHelperCallback
    public void setOnClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //  View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_grid, parent, false);
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pushlittlegreen_recycleview_item, parent, false);
        MyHolder holder = new MyHolder(view);
        //将创建的View注册点击事件
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyHolder holderOne1 = (MyHolder) holder;
        holderOne1.itemone.setText(datas.getJSONObject(position).get("title").toString());
        holderOne1.itemtwo.setText(datas.getJSONObject(position).get("remarks").toString());
        if (datas2.size() > 0) {
            LogUtil.e("xiaoxi个数============="+datas2.size());
            String readStatus = datas2.get(position).toString();
            LogUtil.d("readStatus", readStatus);
            if (readStatus.equals("1")) {
                holderOne1.itemoval.setVisibility(View.INVISIBLE);
                holderOne1.mbtnsetreaded.setVisibility(View.GONE);
            } else {
                holderOne1.itemoval.setVisibility(View.VISIBLE);
                holderOne1.mbtnsetreaded.setVisibility(View.VISIBLE);
            }
        }

        final String messageId = datas.getJSONObject(position).get("messageId").toString();

        String pushTimestring = datas.getJSONObject(position).get("pushTime").toString();
        if (!TextUtils.isEmpty(pushTimestring)) {
            String substring = pushTimestring.substring(0, 11);
            holderOne1.itemthree.setText(substring);
        } else {
            holderOne1.itemthree.setText("暂无数据");
        }
        //获取小绿订单号
        String extraorder = datas.getJSONObject(position).get("extra").toString();
        holderOne1.itemroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (listener != null) {
                    listener.onClick(1,1);
                }*/
                //注意这里使用getTag方法获取position
                int layoutPosition = holderOne1.getLayoutPosition();
                mOnItemClickListener.onItemClick(messageId, layoutPosition);
            }
        });
        holderOne1.mbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int layoutPosition = holderOne1.getLayoutPosition();
                    mOnItemClickListener.onDelClick(messageId, layoutPosition);
                }
            }
        });
        holderOne1.mbtnsetreaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int layoutPosition = holderOne1.getLayoutPosition();
                    mOnItemClickListener.onFlagcircle(messageId, layoutPosition);
                }
            }
        });
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public LinearLayout itemroot;
        public TextView itemone;
        public TextView itemtwo;
        public TextView itemthree;
        public Button mbtnsetreaded;
        public Button mbtnDelete;
        public TextView itemoval;


        public MyHolder(View view) {
            super(view);
            itemroot = (LinearLayout) view.findViewById(R.id.pushlittlegreen_recycleview_rootlayout);
            itemone = (TextView) view.findViewById(R.id.pushlittlegreenrecycleview_itemtext1);
            itemtwo = (TextView) view.findViewById(R.id.pushlittlegreenrecycleview_itemtext2);
            itemthree = (TextView) view.findViewById(R.id.pushlittlegreenrecycleview_itemtext3);
            mbtnsetreaded = (Button) view.findViewById(R.id.btnsetreaded);
            mbtnDelete = (Button) view.findViewById(R.id.btnDelete);
            itemoval = (TextView) view.findViewById(R.id.pushlittlegreenrecycleview_itemoval);

        }
    }

}