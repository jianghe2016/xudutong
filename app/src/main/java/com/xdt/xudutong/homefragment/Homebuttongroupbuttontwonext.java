package com.xdt.xudutong.homefragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.SearchRecharge;
import com.xdt.xudutong.bean.SearchSales;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/22.
 */
//充值记录查询页面
public class Homebuttongroupbuttontwonext extends BaseActivity {

    // private List<PullBean> data = new ArrayList<PullBean>();

    private ListView xiaofeilistview;
    private Button buttonsubmit;
    // private List<Map<String, Object>> data;
    private List<SearchSales.ContentBean.SalesBean> sales;
    private List list1;
    private String zhanghao;
    private String startdate21;
    private String endedata21;
    private TextView edittextbutton11;
    private TextView edittextbutton22;
    private int year, monthOfYear, dayOfMonth;
    private LinearLayout mhomebuttongroup_button1nextback;
    private TextView home_buttongroupbuttononenextheadview1;
    private LinearLayout xiaofeijilu_middleview22;
    private LinearLayout mhome_xiaofeidetailsempty;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroupbuttononenext);
    }

    @Override
    public void initView() {
        home_buttongroupbuttononenextheadview1 = (TextView) findViewById(R.id.home_buttongroupbuttononenextheadview);
        xiaofeijilu_middleview22 = (LinearLayout) findViewById(R.id.xiaofeijilu_middleview2);
        mhomebuttongroup_button1nextback = (LinearLayout) findViewById(R.id.homebuttongroup_button1nextback);
        mhome_xiaofeidetailsempty = (LinearLayout) findViewById(R.id.home_xiaofeidetailsempty);
        xiaofeilistview = (ListView) findViewById(R.id.listview1);
        buttonsubmit = (Button) findViewById(R.id.home_buttom1_next_submit);
        edittextbutton11 = (TextView) findViewById(R.id.edittextbutton1);
        edittextbutton22 = (TextView) findViewById(R.id.edittextbutton2);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        //输入年月日的窗口
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        zhanghao = intent.getStringExtra("zhanghao");
        startdate21 = intent.getStringExtra("startdate21");
        endedata21 = intent.getStringExtra("endedata21");
        String home_buttongroupbuttononenextheadview11 = intent.getStringExtra("home_buttongroupbuttononenextheadview1");
        home_buttongroupbuttononenextheadview1.setText(home_buttongroupbuttononenextheadview11);
        LogUtil.d("传递过来的数据为", zhanghao);
        edittextbutton11.setText(startdate21);
        edittextbutton22.setText(endedata21);
        ShowVolleyReques(zhanghao, startdate21, endedata21);
        mhomebuttongroup_button1nextback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        //起止时间按钮
        edittextbutton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                /**
                 * 实例化一个DatePickerDialog的对象
                 * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                 */
                DatePickerDialog datePickerDialog = new DatePickerDialog(Homebuttongroupbuttontwonext.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int mYear = year;
                        int mMonth = monthOfYear;
                        int mDay = dayOfMonth;
                        edittextbutton11.setText(new StringBuilder().append(mYear).append("-")
                                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                                .append((mDay < 10) ? "0" + mDay : mDay));

                    }
                }, year, monthOfYear, dayOfMonth);

                datePickerDialog.show();
            }

            }
        });
        //结束时间按钮
        edittextbutton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                /**
                 * 实例化一个DatePickerDialog的对象
                 * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                 */
                DatePickerDialog datePickerDialog = new DatePickerDialog(Homebuttongroupbuttontwonext.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        int mYear = year;
                        int mMonth = monthOfYear;
                        int mDay = dayOfMonth;
                        edittextbutton22.setText(new StringBuilder().append(mYear).append("-")
                                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                                .append((mDay < 10) ? "0" + mDay : mDay));
                    }
                }, year, monthOfYear, dayOfMonth);

                datePickerDialog.show();

            }
            }
        });

        //提交按钮的逻辑
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            //点击提交按钮的逻辑
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String s = edittextbutton11.getText().toString();
                    String s1 = edittextbutton22.getText().toString();
                    ShowVolleyReques(zhanghao, s, s1);
                }
            }
        });


    }

    //请求网络
    private void ShowVolleyReques(String zhanghao, String s, String s1) {
        String url = ApiUrls.RECHARGE;
        LogUtil.d("解析的数据为=", "进入了充值查询界面");
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNo", zhanghao);
        params.put("startDate", s);
        params.put("endDate", s1);
        params.put("pageIndex", "1");
        params.put("pageSize", "50");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        SearchRecharge searchrecharge = gson.fromJson(response.toString(), SearchRecharge.class);
                        int flag = searchrecharge.getFlag();
                        String desc = searchrecharge.getDesc();
                        if (flag == 1) {
                            //展示数据
                            try {
                                Object content1 = response.get("content");

                                String content2 = content1 + "";
                                if (content2 + "" != null && !content2.equals("")) {
                                    SearchRecharge.ContentBean content = searchrecharge.getContent();
                                    List<SearchRecharge.ContentBean.RechargeBean> recharge = content.getRecharge();
                                    if (recharge != null && !recharge.isEmpty()) {
                                        mhome_xiaofeidetailsempty.setVisibility(View.INVISIBLE);
                                        xiaofeijilu_middleview22.setVisibility(View.VISIBLE);
                                        xiaofeilistview.setVisibility(View.VISIBLE);
                                        showData(recharge);
                                    } else {
                                        mhome_xiaofeidetailsempty.setVisibility(View.VISIBLE);
                                        xiaofeijilu_middleview22.setVisibility(View.VISIBLE);
                                        xiaofeilistview.setVisibility(View.INVISIBLE);
                                    }
                                } else {
                                    xiaofeijilu_middleview22.setVisibility(View.VISIBLE);
                                    xiaofeilistview.setVisibility(View.INVISIBLE);
                                    mhome_xiaofeidetailsempty.setVisibility(View.VISIBLE);
                                }
                            } catch (JSONException e) {
                                Log.e("捕获了充值记录异常", "捕获了充值记录异常");
                            }
                        } else {
                            ToastUtils.getInstance(Homebuttongroupbuttontwonext.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("请求失败", error.getMessage(), error);
                ToastUtils.getInstance(Homebuttongroupbuttontwonext.this).showMessage("系统繁忙");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");

                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);


    }

    //展示数据
    private void showData(List<SearchRecharge.ContentBean.RechargeBean> recharge) {
        //data=getData();
        MyAdapter adapter = new MyAdapter(Homebuttongroupbuttontwonext.this, recharge);
        // adapter.Addall(sales);
        adapter.notifyDataSetChanged();
        xiaofeilistview.setAdapter(adapter);
    }

    //ViewHolder静态类
    class ViewHolder0 {
        public TextView title21;
        public TextView title22;
        public TextView title23;
        public TextView title24;
        public TextView title242;
    }

    class ViewHolder1 {
        public TextView title21;
        public TextView title22;
        public TextView title23;
        public TextView title24;
        public TextView title242;
    }

    class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private List<SearchRecharge.ContentBean.RechargeBean> recharge;

        private MyAdapter(Context context, List<SearchRecharge.ContentBean.RechargeBean> recharge) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
            this.recharge = recharge;
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适配器中所代表的数据集中的条目数
            return recharge.size();
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            //获取数据集中与指定索引对应的数据项
            return position;
        }

        @Override
        public long getItemId(int position) {
            //Get the row id associated with the specified position in the list.
            //获取在列表中与指定索引对应的行id
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        //Get a View that displays the data at the specified position in the data set.
        //获取一个在数据集中指定索引的视图来显示数据
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int itemViewType = getItemViewType(position);
            if (itemViewType == 0) {
                ViewHolder0 holder = null;
                //如果缓存convertView为空，则需要创建View
                if (convertView == null) {
                    holder = new ViewHolder0();
                    //根据自定义的Item布局加载布局
                    convertView = mInflater.inflate(R.layout.home_xiaofeichaxun_item0, null);
                    holder.title21 = (TextView) convertView.findViewById(R.id.xiaofeiitem1);
                    holder.title22 = (TextView) convertView.findViewById(R.id.xiaofeiitem2);
                    holder.title23 = (TextView) convertView.findViewById(R.id.xiaofeiitem3);
                    holder.title24 = (TextView) convertView.findViewById(R.id.xiaofeiitem4);
                    holder.title242 = (TextView) convertView.findViewById(R.id.xiaofeiitem42);
                    //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder0) convertView.getTag();
                }
                holder.title21.setText(recharge.get(position).getNetName().toString());
                holder.title22.setText(recharge.get(position).getOpType().toString());
                holder.title23.setText("+ " + recharge.get(position).getOpFare().toString() + "元");
                String s = recharge.get(position).getOpDt().toString();
                if (!TextUtils.isEmpty(s)) {
                    if(s.indexOf(" ")!=-1){
                        String[] tt=s.split("\\s+");
                        holder.title24.setText(tt[0]);
                        holder.title242.setText(tt[1]);
                    }
                }
            } else {
                ViewHolder1 holder = null;
                //如果缓存convertView为空，则需要创建View
                if (convertView == null) {
                    holder = new ViewHolder1();
                    //根据自定义的Item布局加载布局
                    convertView = mInflater.inflate(R.layout.home_xiaofeichaxun_item1, null);
                    holder.title21 = (TextView) convertView.findViewById(R.id.xiaofeiitem1);
                    holder.title22 = (TextView) convertView.findViewById(R.id.xiaofeiitem2);
                    holder.title23 = (TextView) convertView.findViewById(R.id.xiaofeiitem3);
                    holder.title24 = (TextView) convertView.findViewById(R.id.xiaofeiitem4);
                    holder.title242 = (TextView) convertView.findViewById(R.id.xiaofeiitem42);
                    //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder1) convertView.getTag();
                }
                holder.title21.setText(recharge.get(position).getNetName().toString());
                holder.title22.setText(recharge.get(position).getOpType().toString());
                holder.title23.setText("+ " + recharge.get(position).getOpFare().toString() + "元");
                String s = recharge.get(position).getOpDt().toString();
                if (!TextUtils.isEmpty(s)){
                    if(s.indexOf(" ")!=-1){
                        String[] tt=s.split("\\s+");
                        holder.title24.setText(tt[0]);
                        holder.title242.setText(tt[1]);
                    }
                }
            }

            return convertView;
        }
    }
}
