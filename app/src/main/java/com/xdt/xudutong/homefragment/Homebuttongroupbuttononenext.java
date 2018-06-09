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
import com.xdt.xudutong.bean.CitygetBalance;
import com.xdt.xudutong.bean.SearchSales;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.xudutong.Xdtyue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xdt.xudutong.utils.ApplicationController.getContext;

/**
 * Created by Administrator on 2017/5/17.
 */
//消费记录查询页面
public class Homebuttongroupbuttononenext extends BaseActivity {
    private ListView xiaofeilistview;
    private Button buttonsubmit;
    private String zhanghao;
    private String startdate11;
    private String endedata11;
    private String acccode = "";
    private TextView edittextbutton11;
    private TextView edittextbutton22;
    private TextView mhome_buttongroupbuttononenextheadview;
    private int year, monthOfYear, dayOfMonth;
    private LinearLayout homebuttongroup_button1nextback1;
    private LinearLayout mhome_xiaofeidetailsempty;
    private TextView mhome_xdt_chengchejilu_mybalance;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroupbuttononenext);
    }


    @Override
    public void initView() {
        homebuttongroup_button1nextback1 = (LinearLayout) findViewById(R.id.homebuttongroup_button1nextback);
        mhome_xiaofeidetailsempty = (LinearLayout) findViewById(R.id.home_xiaofeidetailsempty);
        xiaofeilistview = (ListView) findViewById(R.id.listview1);
        buttonsubmit = (Button) findViewById(R.id.home_buttom1_next_submit);
        edittextbutton11 = (TextView) findViewById(R.id.edittextbutton1);
        edittextbutton22 = (TextView) findViewById(R.id.edittextbutton2);
        mhome_buttongroupbuttononenextheadview = (TextView) findViewById(R.id.home_buttongroupbuttononenextheadview);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mhome_xdt_chengchejilu_mybalance = (TextView) findViewById(R.id.home_xdt_chengchejilu_mybalance);
        //输入年月日的窗口
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        zhanghao = intent.getStringExtra("zhanghao");
        startdate11 = intent.getStringExtra("startdate11");
        endedata11 = intent.getStringExtra("endedata11");
        final String xdtcardsaledetailstopviewxdtcardsaledetailstopview = intent.getStringExtra("xdtcardsaledetailstopview");
        final String realIdnumber = intent.getStringExtra("realIdnumber");
        final boolean xdtcardstates = intent.getBooleanExtra("xdtcardstates", false);
        final boolean personssecretstates = intent.getBooleanExtra("personssecretstates", false);

        if (!TextUtils.isEmpty(startdate11) && !TextUtils.isEmpty(endedata11)) {
            edittextbutton11.setText(startdate11);
            edittextbutton22.setText(endedata11);
        }
        if (!TextUtils.isEmpty(xdtcardsaledetailstopviewxdtcardsaledetailstopview)) {
            mhome_buttongroupbuttononenextheadview.setText(xdtcardsaledetailstopviewxdtcardsaledetailstopview);
            acccode = "2011";
            mhome_xdt_chengchejilu_mybalance.setVisibility(View.VISIBLE);
            final String substring2 = realIdnumber.substring(realIdnumber.length() - 6, realIdnumber.length());
            mhome_xdt_chengchejilu_mybalance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fastClick()) {
                        //许都通卡乘车记录
                        ShowVolleyrequestforgetBalance(zhanghao, substring2);
                        //此段逻辑可增加余额页面的额免密逻辑
                      /*  if (personssecretstates == true && xdtcardstates == true) {

                        } else {
                            Intent intent2 = new Intent(Homebuttongroupbuttononenext.this, Xdtyuequery.class);
                            startActivity(intent2);
                        }*/
                    }
                }
            });
        }
        ShowVolleyReques(zhanghao, startdate11, endedata11);
        LogUtil.d("传递过来的数据为1", zhanghao);
        LogUtil.d("传递过来的数据为11", startdate11);
        LogUtil.d("传递过来的数据为111", endedata11);
        homebuttongroup_button1nextback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Homebuttongroupbuttononenext.this, new DatePickerDialog.OnDateSetListener() {
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
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Homebuttongroupbuttononenext.this, new DatePickerDialog.OnDateSetListener() {
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
        String url = ApiUrls.CONSUMPTION;
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNo", zhanghao);
        params.put("startDate", s);
        params.put("endDate", s1);
        params.put("pageIndex", "1");
        params.put("pageSize", "150");
        params.put("acccode", acccode);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        SearchSales searchSales = gson.fromJson(response.toString(), SearchSales.class);
                        int flag = searchSales.getFlag();
                        String desc = searchSales.getDesc();
                        if (flag == 1) {
                            //展示数据
                            try {
                                Object content1 = response.get("content");
                                if (content1 != null && !content1.equals("")) {
                                    SearchSales.ContentBean content = searchSales.getContent();
                                    List<SearchSales.ContentBean.SalesBean> sales = content.getSales();
                                    if (sales != null && !sales.isEmpty()) {
                                        mhome_xiaofeidetailsempty.setVisibility(View.INVISIBLE);
                                        xiaofeilistview.setVisibility(View.VISIBLE);
                                        showData(sales);
                                    } else {
                                        mhome_xiaofeidetailsempty.setVisibility(View.VISIBLE);
                                        xiaofeilistview.setVisibility(View.INVISIBLE);
                                    }
                                } else {
                                    mhome_xiaofeidetailsempty.setVisibility(View.VISIBLE);
                                    xiaofeilistview.setVisibility(View.INVISIBLE);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else {
                            ToastUtils.getInstance(Homebuttongroupbuttononenext.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mhome_xiaofeidetailsempty.setVisibility(View.VISIBLE);
                ToastUtils.getInstance(Homebuttongroupbuttononenext.this).showMessage("系统繁忙");
                Log.e("请求失败", error.getMessage(), error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(getContext()).getRequestQueue().add(jsonRequest);


    }

    //展示数据
    private void showData(List<SearchSales.ContentBean.SalesBean> sales) {
        //data=getData();
        MyAdapter adapter = new MyAdapter(Homebuttongroupbuttononenext.this, sales);
        // adapter.Addall(sales);
        xiaofeilistview.setAdapter(adapter);
    }

    //ViewHolder静态类
    class ViewHolder0 {
        public TextView title1;
        public TextView title2;
        public TextView title3;
        public TextView title4;
        public TextView title42;
    }

    class ViewHolder1 {
        public TextView title1;
        public TextView title2;
        public TextView title3;
        public TextView title4;
        public TextView title42;
    }

    class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private List<SearchSales.ContentBean.SalesBean> sales;

        private MyAdapter(Context context, List<SearchSales.ContentBean.SalesBean> sales) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
            this.sales = sales;
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适配器中所代表的数据集中的条目数
            return sales.size();
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            //获取数据集中与指定索引对应的数据项
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

        @Override
        public long getItemId(int position) {
            //Get the row id associated with the specified position in the list.
            //获取在列表中与指定索引对应的行id
            return position;
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
                    holder.title1 = (TextView) convertView.findViewById(R.id.xiaofeiitem1);
                    holder.title2 = (TextView) convertView.findViewById(R.id.xiaofeiitem2);
                    holder.title3 = (TextView) convertView.findViewById(R.id.xiaofeiitem3);
                    holder.title4 = (TextView) convertView.findViewById(R.id.xiaofeiitem4);
                    holder.title42 = (TextView) convertView.findViewById(R.id.xiaofeiitem42);
                    //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder0) convertView.getTag();
                }
                holder.title1.setText(sales.get(position).getNetName().toString());
                holder.title2.setText(sales.get(position).getOpType().toString());
                holder.title3.setText("- " + sales.get(position).getOpFare().toString() + "元");
                String s = sales.get(position).getOpDt().toString();
                if (!TextUtils.isEmpty(s)) {
                    if(s.indexOf(" ")!=-1){
                        String[] tt=s.split("\\s+");
                        holder.title4.setText(tt[0]);
                        holder.title42.setText(tt[1]);
                    }
                }

            } else {
                ViewHolder1 holder = null;
                //如果缓存convertView为空，则需要创建View
                if (convertView == null) {
                    holder = new ViewHolder1();
                    //根据自定义的Item布局加载布局
                    convertView = mInflater.inflate(R.layout.home_xiaofeichaxun_item1, null);
                    holder.title1 = (TextView) convertView.findViewById(R.id.xiaofeiitem1);
                    holder.title2 = (TextView) convertView.findViewById(R.id.xiaofeiitem2);
                    holder.title3 = (TextView) convertView.findViewById(R.id.xiaofeiitem3);
                    holder.title4 = (TextView) convertView.findViewById(R.id.xiaofeiitem4);
                    holder.title42 = (TextView) convertView.findViewById(R.id.xiaofeiitem42);
                    //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder1) convertView.getTag();
                }
                holder.title1.setText(sales.get(position).getNetName().toString());
                holder.title2.setText(sales.get(position).getOpType().toString());
                holder.title3.setText("- " + sales.get(position).getOpFare().toString() + "元");
                String s = sales.get(position).getOpDt().toString();
                if (!TextUtils.isEmpty(s)) {
                    if(s.indexOf(" ")!=-1){
                        String[] tt=s.split("\\s+");
                        holder.title4.setText(tt[0]);
                        holder.title42.setText(tt[1]);
                    }
                }
            }
            return convertView;
        }
    }

    //请求当前账户余额
    private void ShowVolleyrequestforgetBalance(String cityCardno, String substring2) {
        String url = ApiUrls.GETBALANCE;
        //Volley请求网络进行判断is);
        Map<String, String> params = new HashMap<String, String>();
        params.put("citycardno", cityCardno);
        params.put("searchPwd", substring2);
        LogUtil.d("citycardno=", cityCardno + "");
        LogUtil.d("searchPwd=", substring2 + "");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        CitygetBalance citygetbalance = gson.fromJson(response.toString(), CitygetBalance.class);
                        int flag = citygetbalance.getFlag();
                        if (flag == 1) {
                            double yue1 = citygetbalance.getContent().getData();
                            Intent intent = new Intent(Homebuttongroupbuttononenext.this, Xdtyue.class);
                            intent.putExtra("yuequeryresult", yue1 + "");
                            startActivity(intent);
                        } else {
                            String desc = citygetbalance.getDesc();
                            ToastUtils.getInstance(Homebuttongroupbuttononenext.this).showMessage(desc);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Homebuttongroupbuttononenext.this).showMessage("系统繁忙");
                LogUtil.d("请求的当前账户余额失败数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(getContext()).getRequestQueue().add(jsonRequest);

    }
}
