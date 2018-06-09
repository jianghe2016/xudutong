package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Libraryquerybooks;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/24.
 */

public class Homecardgroupbuttonsix extends BaseActivity {
    private EditText cardbt61;
    private RelativeLayout cardbt62;
    private TextView cardbt63;
    private TextView cardbt64;
    private TextView cardbt65;
    private TextView cardbt66;
    private TextView cardbt67;
    private TextView cardbt68;
    private TextView cardbt69;
    private TextView cardbt70;
    private ProgressBar cardbutton61progressbar1;
    private LinearLayout mhome_cardgroup_button6;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_button6);
    }

    @Override
    public void initView() {
        mhome_cardgroup_button6 = (LinearLayout) findViewById(R.id.home_cardgroup_button6);
        cardbt61 = (EditText) findViewById(R.id.cardbutton61);
        cardbt62 = (RelativeLayout) findViewById(R.id.cardbutton62);
        cardbt63 = (TextView) findViewById(R.id.cardbutton63);
        cardbt64 = (TextView) findViewById(R.id.cardbutton64);
        cardbt65 = (TextView) findViewById(R.id.cardbutton65);
        cardbt66 = (TextView) findViewById(R.id.cardbutton66);
        cardbt67 = (TextView) findViewById(R.id.cardbutton67);
        cardbt68 = (TextView) findViewById(R.id.cardbutton68);
        cardbt69 = (TextView) findViewById(R.id.cardbutton69);
        cardbt70 = (TextView) findViewById(R.id.cardbutton70);
        cardbutton61progressbar1 = (ProgressBar) findViewById(R.id.cardbutton61progressbar);
        initData();
    }

    private void initData() {
        mhome_cardgroup_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        cardbt62.setOnClickListener(new View.OnClickListener() {

            private String carduserlibrarytext;

            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    cardbutton61progressbar1.setVisibility(View.VISIBLE);
                    LogUtil.d("解析的数据为=", "进入了图书查询界面");
                    carduserlibrarytext = cardbt61.getText().toString().trim();
                    LogUtil.d("输入的图书查询名字为=", carduserlibrarytext.toString());
                    String bookname = carduserlibrarytext;
                    ShowVolleyRequest(bookname.trim());
                }
            }
        });
        cardbt63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    cardbutton61progressbar1.setVisibility(View.VISIBLE);
                    String bookname = "水浒传";
                    ShowVolleyRequest(bookname.trim());
                }
            }
        });
        cardbt64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    cardbutton61progressbar1.setVisibility(View.VISIBLE);
                    String bookname = "西游记";
                    ShowVolleyRequest(bookname.trim());
                }
            }
        });
        cardbt65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    cardbutton61progressbar1.setVisibility(View.VISIBLE);
                    String bookname = "红楼梦";
                    ShowVolleyRequest(bookname.trim());
                }
            }
        });
        cardbt66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    cardbutton61progressbar1.setVisibility(View.VISIBLE);
                    String bookname = "三国演义";
                    ShowVolleyRequest(bookname.trim());
                }
            }
        });
        cardbt67.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    cardbutton61progressbar1.setVisibility(View.VISIBLE);
                    String bookname = "镜花缘";
                    ShowVolleyRequest(bookname.trim());
                }
            }
        });
        cardbt68.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    cardbutton61progressbar1.setVisibility(View.VISIBLE);
                    String bookname = "儒林外史";
                    ShowVolleyRequest(bookname.trim());
                }
            }
        });
        cardbt69.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    cardbutton61progressbar1.setVisibility(View.VISIBLE);
                    String bookname = "封神演义";
                    ShowVolleyRequest(bookname.trim());
                }
            }
        });
        cardbt70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    cardbutton61progressbar1.setVisibility(View.VISIBLE);
                    String bookname = "聊斋志异";
                    ShowVolleyRequest(bookname.trim());
                }
            }
        });
    }

    private void ShowVolleyRequest(final String bookname) {
        String url = ApiUrls.QUERYBOOKS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("bookmetaTitle", bookname);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Libraryquerybooks libraryquerybooks = gson.fromJson(response.toString(), Libraryquerybooks.class);
                        String desc = libraryquerybooks.getDesc().toString();
                        int flag = libraryquerybooks.getFlag();
                        if (flag == 1) {
                            Intent intent = new Intent(Homecardgroupbuttonsix.this, Homecardgroupbuttonsixnext.class);
                            intent.putExtra("libraryname", bookname);
                            startActivity(intent);
                            cardbutton61progressbar1.setVisibility(View.INVISIBLE);
                            finish();
                            LogUtil.d("登录图书查新成功====", desc);
                        } else {
                            ToastUtils.getInstance(Homecardgroupbuttonsix.this).showMessage("系统繁忙");
                            cardbutton61progressbar1.setVisibility(View.INVISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的数据为=", error.toString());
                cardbutton61progressbar1.setVisibility(View.INVISIBLE);
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
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }

}

