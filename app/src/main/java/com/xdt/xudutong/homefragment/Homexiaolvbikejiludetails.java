package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.XiaolvdetailsrecycleviewAdapter;
import com.xdt.xudutong.bean.Wsbikemakecardrecord;
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
 * Created by Administrator on 2017\8\29 0029.
 */

public class Homexiaolvbikejiludetails extends BaseActivity {


    private RecyclerView home_xiaolvbikejiluquerydetailsrecycleview1;
    private ProgressBar home_xiaolvbikejiluquerydetailsprogressbar1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.homebikebikejiludetails);
    }
    @Override
    public void initView() {
        LinearLayout home_xiaolvbikejiluquerydetailsback1 = (LinearLayout) findViewById(R.id.home_xiaolvbikejiluquerydetailsback);
        home_xiaolvbikejiluquerydetailsrecycleview1 = (RecyclerView) findViewById(R.id.home_xiaolvbikejiluquerydetailsrecycleview);
        home_xiaolvbikejiluquerydetailsprogressbar1 = (ProgressBar) findViewById(R.id.home_xiaolvbikejiluquerydetailsprogressbar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        home_xiaolvbikejiluquerydetailsrecycleview1.setLayoutManager(layoutManager);
        home_xiaolvbikejiluquerydetailsback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        initData();
    }

    private void initData() {
       /* String a = "4110237777";
        String b = "20170801";
        String c = "20170830";*/
        Intent intent = getIntent();
        String xiaolvjiluxdtnumber = intent.getStringExtra("xiaolvjiluxdtnumber");
        String xiaolvjilustarday = intent.getStringExtra("xiaolvjilustarday");
        String xiaolvjiluendday = intent.getStringExtra("xiaolvjiluendday");
        ShowVolleyRequestfornews(xiaolvjiluxdtnumber, xiaolvjilustarday, xiaolvjiluendday);

    }

    //请求小绿详情
    public void ShowVolleyRequestfornews(String a, String b, String c) {
        String url = ApiUrls.MAKECARDRECORD;
     /*   String s = url + "?carfaceno=" +
                a + "&startdate="
                + b + "&enddate=" + c;*/
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("carfaceno", a);
        params.put("startdate", b);
        params.put("enddate", c);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Wsbikemakecardrecord wsbikemakecardrecord = gson.fromJson(response.toString(), Wsbikemakecardrecord.class);
                        List<Wsbikemakecardrecord.ContentBean.DataBean> wsbikemakecardrecorddata = wsbikemakecardrecord.getContent().getData();
                        String desc = wsbikemakecardrecord.getDesc().toString();
                        int flag = wsbikemakecardrecord.getFlag();
                        if (flag == 1) {
                            Showdata(wsbikemakecardrecorddata);
                        } else {
                            LogUtil.d("请求小绿失败1的数据为=", "请求小绿失败1的数据");
                            ToastUtils.getInstance(Homexiaolvbikejiludetails.this).showMessage(desc);
                            home_xiaolvbikejiluquerydetailsprogressbar1.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Homexiaolvbikejiludetails.this).showMessage("系统繁忙");
                LogUtil.d("请求小绿失败2的数据为=", error.toString());
                home_xiaolvbikejiluquerydetailsprogressbar1.setVisibility(View.GONE);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(Homexiaolvbikejiludetails.this).getRequestQueue().add(jsonRequest);
    }

    private void Showdata(List<Wsbikemakecardrecord.ContentBean.DataBean> wsbikemakecardrecorddata) {
        XiaolvdetailsrecycleviewAdapter xiaolvdetailsrecycleviewAdapter = new XiaolvdetailsrecycleviewAdapter(Homexiaolvbikejiludetails.this, wsbikemakecardrecorddata);
        home_xiaolvbikejiluquerydetailsrecycleview1.setAdapter(xiaolvdetailsrecycleviewAdapter);
        home_xiaolvbikejiluquerydetailsprogressbar1.setVisibility(View.GONE);
    }
}
