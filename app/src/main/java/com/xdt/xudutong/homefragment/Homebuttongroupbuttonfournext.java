package com.xdt.xudutong.homefragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.BuscardgetRecordByName;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/22.
 */

public class Homebuttongroupbuttonfournext extends BaseActivity {
    private int year, monthOfYear, dayOfMonth;
    private ListView xiaofeilistview;
    private Button buttonsubmit;
    private TextView edittextbutton11;
    private TextView edittextbutton22;
    private List<BuscardgetRecordByName.ContentBean.DataBean> data;
    private ProgressBar homebuttongroup_button41progressbar1;
    private String chengcheputongstartDate41;
    private String chengcheputongenddate41;
    private String zhanghao41;
    private String idnumber41;
    private LinearLayout mhome_takecardetailsempty;


    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroupbuttonfournext);
    }

    @Override
    public void initView() {
        final Intent intent = getIntent();
        //姓名和身份证号
        zhanghao41 = intent.getStringExtra("zhanghao41");
        idnumber41 = intent.getStringExtra("idnumber41");
        final boolean personssecretstates = intent.getBooleanExtra("personssecretstates", true);
        final int realrestates = intent.getIntExtra("realrestates", -1);
        chengcheputongstartDate41 = intent.getStringExtra("chengcheputongstartDate");
        chengcheputongenddate41 = intent.getStringExtra("chengcheputongenddate");
        LinearLayout homebuttongroup_button41nexback1 = (LinearLayout) findViewById(R.id.homebuttongroup_button41nexback);
        mhome_takecardetailsempty = (LinearLayout) findViewById(R.id.home_takecardetailsempty);
        TextView mhome_chengchejilu_mybalance = (TextView) findViewById(R.id.home_chengchejilu_mybalance);

        mhome_chengchejilu_mybalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent1 = new Intent(Homebuttongroupbuttonfournext.this, LoadBlance.class);
                    intent1.putExtra("queryloadblanceusername", zhanghao41);
                    intent1.putExtra("zhaqueryloadblancepassword", idnumber41);
                    startActivity(intent1);
                    //此段逻辑可增加，实名认证逻辑
                   /* if (realrestates == 1) {
                    } else {
                        Intent intent = new Intent(Homebuttongroupbuttonfournext.this, Homebuttongroupbuttonsixtwonexttwo.class);
                        intent.putExtra("gongjiaointentnext", "卡内余额普通公交卡查询");
                        startActivity(intent);
                    }*/

                    //此段逻辑可给公交卡余额增加免密逻辑

                /*    if (personssecretstates == true) {

                    } else {
                        Intent intent = new Intent(Homebuttongroupbuttonfournext.this, Homebuttongroupbuttonsixtwonexttwo.class);
                        intent.putExtra("gongjiaointentnext", "卡内余额普通公交卡查询");
                        startActivity(intent);
                    }*/
                }
            }
        });
        homebuttongroup_button41nexback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        xiaofeilistview = (ListView) findViewById(R.id.listview44);
        buttonsubmit = (Button) findViewById(R.id.home_buttom1_next_submit4);
        edittextbutton11 = (TextView) findViewById(R.id.edittextbutton41);
        edittextbutton22 = (TextView) findViewById(R.id.edittextbutton42);
        homebuttongroup_button41progressbar1 = (ProgressBar) findViewById(R.id.homebuttongroup_button41progressbar);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        //输入年月日的窗口
        initData();
    }


    private void initData() {
        edittextbutton11.setText(chengcheputongstartDate41);
        edittextbutton22.setText(chengcheputongenddate41);
        ShowVolleyReques(zhanghao41, idnumber41, chengcheputongstartDate41, chengcheputongenddate41);

        //起止时间按钮
        edittextbutton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    /**
                     * 实例化一个DatePickerDialog的对象
                     * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                     */
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Homebuttongroupbuttonfournext.this, new DatePickerDialog.OnDateSetListener() {
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
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Homebuttongroupbuttonfournext.this, new DatePickerDialog.OnDateSetListener() {
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
                    if (!s.isEmpty() && !s1.isEmpty()) {
                        if (null != data && data.size() > 0) {
                            data.clear();
                        }
                        ShowVolleyReques(zhanghao41, idnumber41, s, s1);
                    } else {
                        ToastUtils.getInstance(Homebuttongroupbuttonfournext.this).showMessage("输入日期不能为空");
                    }
                }
            }
        });
    }

    //请求网络

    private void ShowVolleyReques(String zhanghao41, String idnumber41, String chengcheputongstartDate41, String chengcheputongenddate41) {
        String url = ApiUrls.GETRECORDBYNAME;
        LogUtil.d("解析的数据为=", "进入了消费查询界面");
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", zhanghao41);
        params.put("certId", idnumber41);
        params.put("startDate", chengcheputongstartDate41);
        params.put("endDate", chengcheputongenddate41);
        params.put("pageNum", "1");
        params.put("pageSize", "150");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        BuscardgetRecordByName buscardgetrecordbyname = gson.fromJson(response.toString(), BuscardgetRecordByName.class);
                        int flag = buscardgetrecordbyname.getFlag();
                        String desc = buscardgetrecordbyname.getDesc();
                        if (flag == 1) {
                            //展示数据
                            data = buscardgetrecordbyname.getContent().getData();
                            xiaofeilistview.setVisibility(View.VISIBLE);
                            mhome_takecardetailsempty.setVisibility(View.INVISIBLE);
                            homebuttongroup_button41progressbar1.setVisibility(View.INVISIBLE);
                            showData(data);
                        } else {
                            xiaofeilistview.setVisibility(View.INVISIBLE);
                            mhome_takecardetailsempty.setVisibility(View.VISIBLE);
                            homebuttongroup_button41progressbar1.setVisibility(View.INVISIBLE);
                            ToastUtils.getInstance(Homebuttongroupbuttonfournext.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                homebuttongroup_button41progressbar1.setVisibility(View.VISIBLE);
                ToastUtils.getInstance(Homebuttongroupbuttonfournext.this).showMessage("系统繁忙");
                Log.e("请求失败", error.getMessage(), error);
                mhome_takecardetailsempty.setVisibility(View.VISIBLE);
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
    private void showData(List<BuscardgetRecordByName.ContentBean.DataBean> data) {
        //data=getData();
        MyAdapter adapter = new MyAdapter(Homebuttongroupbuttonfournext.this, data);
        // adapter.Addall(sales);
        xiaofeilistview.setAdapter(adapter);
    }

    //ViewHolder静态类
    class ViewHolder {
        public TextView title1;
        public TextView title2;
        public TextView title3;
        public TextView title4;
    }

    class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private List<BuscardgetRecordByName.ContentBean.DataBean> data;

        private MyAdapter(Context context, List<BuscardgetRecordByName.ContentBean.DataBean> data) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适配器中所代表的数据集中的条目数
            return data.size();
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

        //Get a View that displays the data at the specified position in the data set.
        //获取一个在数据集中指定索引的视图来显示数据
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.home_xiaofeichaxun_item1, null);
                holder.title1 = (TextView) convertView.findViewById(R.id.xiaofeiitem1);
                holder.title2 = (TextView) convertView.findViewById(R.id.xiaofeiitem2);
                holder.title3 = (TextView) convertView.findViewById(R.id.xiaofeiitem3);
                holder.title4 = (TextView) convertView.findViewById(R.id.xiaofeiitem4);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title1.setText(data.get(position).getName().toString());
            holder.title2.setText(data.get(position).getOpdt().toString());
            holder.title3.setText(data.get(position).getLinename().toString());
            holder.title4.setText(data.get(position).getBusnumber().toString());
            return convertView;
        }
    }
}
