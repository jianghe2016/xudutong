package com.xdt.xudutong.homefragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.PeccancygetXcWeizhang;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/24.
 */

public class Homecardgroupbuttonthree extends BaseActivity {

    private EditText cardgroupbt31;
    private RadioGroup cardgroupbt32;
    private RadioButton cardgroupbt33;
    private RadioButton cardgroupbt34;
    private RadioButton cardgroupbt35;
    private TextView cardgroupbt36;
    private ListView home_cardgroupbtlistview3;
    private List<PeccancygetXcWeizhang.ContentBean.DataBean> peccancygetxcweizhangdata;
    private LinearLayout mhome_cardgroup_button3;
    private TextView home_cardgroupbuttonlistview3framelayouttext1;
    private TextView home_cardgroupbuttonlistview3framelayouttext21;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_button3);
    }
    @Override
    public void initView() {
        mhome_cardgroup_button3 = (LinearLayout) findViewById(R.id.home_cardgroup_button3back);
        cardgroupbt31 = (EditText) findViewById(R.id.cardgroupbutton31);
        cardgroupbt32 = (RadioGroup) findViewById(R.id.cardgroupbutton32);
        cardgroupbt33 = (RadioButton) findViewById(R.id.cardgroupbutton33);
        cardgroupbt34 = (RadioButton) findViewById(R.id.cardgroupbutton34);
        cardgroupbt35 = (RadioButton) findViewById(R.id.cardgroupbutton35);
        cardgroupbt36 = (TextView) findViewById(R.id.cardgroupbutton36);
        home_cardgroupbuttonlistview3framelayouttext1 = (TextView) findViewById(R.id.home_cardgroupbuttonlistview3framelayouttext);
        home_cardgroupbuttonlistview3framelayouttext21 = (TextView) findViewById(R.id.home_cardgroupbuttonlistview3framelayouttext2);
        home_cardgroupbtlistview3 = (ListView) findViewById(R.id.home_cardgroupbuttonlistview3);
        LinearLayout weizhangclear1 = (LinearLayout) findViewById(R.id.weizhangclear);
        weizhangclear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        initData();
    }

    private void initData() {
        mhome_cardgroup_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    finish();
                }
            }
        });
        cardgroupbt36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    if (cardgroupbt31.hasFocus()) {
                        InputMethodManager imm = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    String s1 = cardgroupbt31.getText().toString();
                    String t1 = "2";
                    ShowVolleyReques(s1, t1);
                }
            }
        });

        cardgroupbt33.setChecked(true);
        cardgroupbt34.setChecked(false);
        cardgroupbt35.setChecked(false);
        cardgroupbt32.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cardgroupbutton33:
                        cardgroupbt33.setTextColor(getResources().getColor(R.color.whitecolortext));
                        cardgroupbt34.setTextColor(getResources().getColor(R.color.trainunselect));
                        cardgroupbt35.setTextColor(getResources().getColor(R.color.trainunselect));
                        cardgroupbt36.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //小车
                                String s1 = cardgroupbt31.getText().toString();
                                String t1 = "2";
                                ShowVolleyReques(s1, t1);
                            }
                        });
                        break;
                    case R.id.cardgroupbutton34:
                        cardgroupbt33.setTextColor(getResources().getColor(R.color.trainunselect));
                        cardgroupbt34.setTextColor(getResources().getColor(R.color.whitecolortext));
                        cardgroupbt35.setTextColor(getResources().getColor(R.color.trainunselect));
                        cardgroupbt36.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //大车
                                String s2 = cardgroupbt31.getText().toString();
                                String t2 = "1";
                                ShowVolleyReques(s2, t2);
                            }
                        });
                        break;
                    case R.id.cardgroupbutton35:
                        cardgroupbt33.setTextColor(getResources().getColor(R.color.trainunselect));
                        cardgroupbt34.setTextColor(getResources().getColor(R.color.trainunselect));
                        cardgroupbt35.setTextColor(getResources().getColor(R.color.whitecolortext));
                        //挂车
                        cardgroupbt36.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String s3 = cardgroupbt31.getText().toString();
                                String t3 = "15";
                                ShowVolleyReques(s3, t3);

                            }
                        });
                        break;
                }
            }


        });
    }

    private void ShowVolleyReques(String s3, String t3) {
        String url = ApiUrls.GETXCWEIZHANG;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("l", s3);
        params.put("t", t3);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    //ViewHolder静态类

                    class ViewHolder1 {
                        public TextView title31;
                        public TextView title32;
                        public TextView title33;
                        public TextView title34;
                    }

                    class ViewHolder2 {
                        TextView vh2_name;

                    }

                    class MyAdapter extends BaseAdapter {
                        private final int typeOne = 0;
                        private final int typeTwo = 1;
                        private LayoutInflater mInflater = null;
                        private List<PeccancygetXcWeizhang.ContentBean.DataBean> peccancygetxcweizhangdata;

                        private MyAdapter(Context context, List<PeccancygetXcWeizhang.ContentBean.DataBean> peccancygetxcweizhangdata) {
                            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
                            this.mInflater = LayoutInflater.from(context);
                            this.peccancygetxcweizhangdata = peccancygetxcweizhangdata;
                        }

                        @Override
                        public int getCount() {
                            //How many items are in the data set represented by this Adapter.
                            //在此适配器中所代表的数据集中的条目数
                            return peccancygetxcweizhangdata.size();
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
                        public int getViewTypeCount() {

                            return 2;
                        }

                        @Override
                        public int getItemViewType(int position) {
                            if (position == peccancygetxcweizhangdata.size() - 1) {
                                return typeTwo;
                            } else {
                                return typeOne;
                            }
                        }

                        //Get a View that displays the data at the specified position in the data set.
                        //获取一个在数据集中指定索引的视图来显示数据
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            ViewHolder1 vh1 = null;
                            ViewHolder2 vh2 = null;
                            int type = getItemViewType(position);
                            if (convertView == null) {
                                switch (type) {
                                    case typeOne:
                                        vh1 = new ViewHolder1();
                                        convertView = mInflater.inflate(R.layout.home_cardgroup_button3item, null);
                                        vh1.title31 = (TextView) convertView.findViewById(R.id.weizhangtext1);
                                        vh1.title32 = (TextView) convertView.findViewById(R.id.weizhangtext2);
                                        vh1.title33 = (TextView) convertView.findViewById(R.id.weizhangtext3);
                                        vh1.title34 = (TextView) convertView.findViewById(R.id.weizhangtext4);
                                        convertView.setTag(vh1);
                                        break;
                                    case typeTwo:
                                        vh2 = new ViewHolder2();
                                        convertView = mInflater.inflate(R.layout.home_cardgroup_button3item2, null);
                                        vh2.vh2_name = (TextView) convertView.findViewById(R.id.hoem_cardgropu_button3item2text1);
                                        convertView.setTag(vh2);
                                        break;
                                    default:
                                        break;
                                }
                            }


                            switch (type) {
                                case typeOne:
                                    vh1 = (ViewHolder1) convertView.getTag();
                                    home_cardgroupbuttonlistview3framelayouttext1.setVisibility(View.INVISIBLE);
                                    home_cardgroupbuttonlistview3framelayouttext21.setVisibility(View.INVISIBLE);
                                    String peccancyInfo = peccancygetxcweizhangdata.get(position).getPeccancyInfo();
                                    String PeccancyDate = peccancygetxcweizhangdata.get(position).getPeccancyDate();
                                    String PeccancyPlace = peccancygetxcweizhangdata.get(position).getPeccancyPlace();
                                    String FinePoints = peccancygetxcweizhangdata.get(position).getFinePoints();
                                    if (PeccancyDate != null && PeccancyPlace != null && FinePoints != null) {
                                        vh1.title31.setText(peccancyInfo);
                                        vh1.title32.setText(PeccancyDate);
                                        vh1.title33.setText(PeccancyPlace);
                                        vh1.title34.setText(FinePoints);
                                    }
                                    break;
                                case typeTwo:
                                    vh2 = (ViewHolder2) convertView.getTag();
                                    Object finePointsTotal = peccancygetxcweizhangdata.get(position).getFinePointsTotal();
                                    Object dataDeclaration = peccancygetxcweizhangdata.get(position).getDataDeclaration();
                                    Object dataUpdate = peccancygetxcweizhangdata.get(0).getDataUpdate();
                                    String peccancyInfo2 = peccancygetxcweizhangdata.get(0).getPeccancyInfo();
                                    if (finePointsTotal != null && dataDeclaration != null) {
                                        vh2.vh2_name.setText(finePointsTotal.toString());
                                    } else if (finePointsTotal == null && dataDeclaration == null && peccancyInfo2 != null) {
                                        home_cardgroupbuttonlistview3framelayouttext21.setVisibility(View.VISIBLE);
                                        home_cardgroupbuttonlistview3framelayouttext1.setVisibility(View.INVISIBLE);
                                        convertView.setVisibility(View.GONE);
                                        String peccancyInfo1 = peccancygetxcweizhangdata.get(0).getPeccancyInfo();
                                        home_cardgroupbuttonlistview3framelayouttext21.setText(peccancyInfo1.toString());
                                    } else if (finePointsTotal == null && dataDeclaration == null && dataUpdate != null && peccancyInfo2 != null) {
                                        home_cardgroupbuttonlistview3framelayouttext1.setVisibility(View.VISIBLE);
                                        home_cardgroupbuttonlistview3framelayouttext21.setVisibility(View.VISIBLE);
                                        String peccancyInfo1 = peccancygetxcweizhangdata.get(0).getPeccancyInfo();
                                        Object dataUpdate1 = peccancygetxcweizhangdata.get(0).getDataUpdate();
                                        home_cardgroupbuttonlistview3framelayouttext1.setText(peccancyInfo1.toString());
                                        home_cardgroupbuttonlistview3framelayouttext21.setText(dataUpdate1.toString());
                                    }
                                    break;
                                default:
                                    break;
                            }
                            return convertView;

                        }

                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        PeccancygetXcWeizhang peccancygetxcweizhang = gson.fromJson(response.toString(), PeccancygetXcWeizhang.class);
                        String desc = peccancygetxcweizhang.getDesc().toString();
                        int flag = peccancygetxcweizhang.getFlag();
                        if (flag == 1) {
                            peccancygetxcweizhangdata = peccancygetxcweizhang.getContent().getData();
                            showData(peccancygetxcweizhangdata);
                            LogUtil.d("登录成功====", desc);
                        } else {
                            ToastUtils.getInstance(Homecardgroupbuttonthree.this).showMessage("输入车牌号不正确");
                        }
                    }

                    private void showData(List<PeccancygetXcWeizhang.ContentBean.DataBean> peccancygetxcweizhangdata) {
                        MyAdapter adapter = new MyAdapter(Homecardgroupbuttonthree.this, peccancygetxcweizhangdata);
                        // adapter.Addall(sales);
                        adapter.notifyDataSetChanged();
                        home_cardgroupbtlistview3.setAdapter(adapter);
                        LogUtil.d("登录成功=", "即将展示数据。。。");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Homecardgroupbuttonthree.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
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
}

