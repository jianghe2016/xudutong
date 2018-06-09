package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.BusquerybuslineAdapter;
import com.xdt.xudutong.bean.MobilebusqueryBusLineInfo;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\6\26 0026.
 */

public class Homebuttongroupbuttonsixquerybusline extends BaseActivity {

    private RecyclerView home_button6_querybuslineinforecycle1;
    private TextView home_button6_querybuslineinfoshoufatime1;
    private TextView home_button6_querybuslineinfomofatime1;
    private TextView home_button6_querybuslineinfostarplace1;
    private TextView home_button6_querybuslineinfoendplace1;
    private ImageView home_button6_querybuslineinfobusjiaohuan1;
    private String busStation2;
    private String busStation;
    private String buslinenumber2;
    private String substring;
    private TextView home_button6_querybuslineinfobusnukmber1;
    private ProgressDialog progressDialog;
    private String cityname;
    private String cityid;
    private String buslinenumber1;
    private String s2 = "0";
    private String s3 = "1";
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;
    private LinearLayout mhome_buttongroupbutton6querybuslinecontent;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_button6_querybuslineinfo);
        progressDialog = ProgressDialog.showDialog(Homebuttongroupbuttonsixquerybusline.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        //第一个页面传过来的公交路线
        Intent intent = getIntent();
        buslinenumber1 = intent.getStringExtra("buslinenumber");
        int containlu = buslinenumber1.indexOf("路");
        if (containlu == -1) {
            //不含路字
            buslinenumber2 = buslinenumber1 + "路";
            substring = buslinenumber1;
        } else {
            //含路字
            buslinenumber2 = buslinenumber1;
            String[] containlusplit = buslinenumber1.split("路");
            //截取路之前的数字
            String s = containlusplit[0];
            substring = s;
        }
        //选择城市页面传过来的城市名字和id
        cityname = SpUtils.getParam(getApplicationContext(), "cityname", "许昌");
        cityid = SpUtils.getParam(getApplicationContext(), "cityid", "0374");
        String linnuber = "0";
        RequestVolley(buslinenumber2, substring, linnuber, cityname, cityid);
        LinearLayout home_buttongroupbutton6querybuslineback11 = (LinearLayout) findViewById(R.id.home_buttongroupbutton6querybuslineback);
        mhome_buttongroupbutton6querybuslinecontent = (LinearLayout) findViewById(R.id.home_buttongroupbutton6querybuslinecontent);
        home_buttongroupbutton6querybuslineback11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        home_button6_querybuslineinforecycle1 = (RecyclerView) findViewById(R.id.home_button6_querybuslineinforecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//LinearLayoutManager.HORIZONTAL
        home_button6_querybuslineinforecycle1.setLayoutManager(layoutManager);
        home_button6_querybuslineinfobusnukmber1 = (TextView) findViewById(R.id.home_button6_querybuslineinfobusnukmber);
        //首发末发时间
        home_button6_querybuslineinfoshoufatime1 = (TextView) findViewById(R.id.home_button6_querybuslineinfoshoufatime);
        home_button6_querybuslineinfomofatime1 = (TextView) findViewById(R.id.home_button6_querybuslineinfomofatime);
        //首发末发地点
        home_button6_querybuslineinfostarplace1 = (TextView) findViewById(R.id.home_button6_querybuslineinfostarplace);
        home_button6_querybuslineinfoendplace1 = (TextView) findViewById(R.id.home_button6_querybuslineinfoendplace);

        //点击交换
        home_button6_querybuslineinfobusjiaohuan1 = (ImageView) findViewById(R.id.home_button6_querybuslineinfobusjiaohuan);
        home_button6_querybuslineinfobusjiaohuan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // empet2=空值  s2=0  s3=1
                String empet2 = s2;
                s2 = s3;
                s3 = empet2;
                RequestVolley(buslinenumber2, substring, s2, cityname, cityid);
            }
        });
        home_button6_querybuslineinfobusnukmber1.setText(buslinenumber2);
    }

    private void RequestVolley(String buslinenumber2, String substring, String linnuber, String cityname, String cityid) {
        String url = ApiUrls.QUERYBUSLINEINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("line", buslinenumber2);
        params.put("linename", substring);
        params.put("lineud", linnuber);
        params.put("cityid", cityid);
        params.put("cityname", cityname);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        MobilebusqueryBusLineInfo mobilebusquerybuslineinfodata = gson.fromJson(response.toString(), MobilebusqueryBusLineInfo.class);
                        String cosde = mobilebusquerybuslineinfodata.getCode();
                        if (cosde.equals("R00001")) {
                            successcontent();
                            List<MobilebusqueryBusLineInfo.ContentBean.DataBean.LinedataBean> mobilebusquerybuslineinfolinedata = mobilebusquerybuslineinfodata.getContent().getData().getLinedata();
                            String lineinfo = mobilebusquerybuslineinfodata.getContent().getData().getLineinfo();
                            String[] split = lineinfo.split("运营时间:");
                            String s = split[1];
                            String[] split1 = s.split(" ");
                            String s1 = split1[0];
                            String[] split2 = s1.split("-");
                            String s2 = split2[0];
                            String s3 = split2[1];
                            home_button6_querybuslineinfoshoufatime1.setText("  " + s2);
                            home_button6_querybuslineinfomofatime1.setText("  " + s3);
                            showData(mobilebusquerybuslineinfolinedata);
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
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

    private void showData(List<MobilebusqueryBusLineInfo.ContentBean.DataBean.LinedataBean> mobilebusquerybuslineinfolinedata) {
        BusquerybuslineAdapter productRecyclerAdapter = new BusquerybuslineAdapter(Homebuttongroupbuttonsixquerybusline.this, mobilebusquerybuslineinfolinedata);
        home_button6_querybuslineinforecycle1.setAdapter(productRecyclerAdapter);
        //获取首末班车地点，并赋值
        busStation = mobilebusquerybuslineinfolinedata.get(0).getBusStation();
        busStation2 = mobilebusquerybuslineinfolinedata.get(mobilebusquerybuslineinfolinedata.size() - 1).getBusStation();
        home_button6_querybuslineinfostarplace1.setText(busStation);
        home_button6_querybuslineinfoendplace1.setText(busStation2);
    }

    private void failcontent() {
        mhome_buttongroupbutton6querybuslinecontent.setVisibility(View.GONE);
        msearch_content.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        msearch_content.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
        mhome_buttongroupbutton6querybuslinecontent.setVisibility(View.VISIBLE);
    }
}



