package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.HomeShengyubaoxiannewRecyclerAdapter;
import com.xdt.xudutong.adapder.HomeShiyebaoxiannewRecyclerAdapter;
import com.xdt.xudutong.adapder.HomeYanglaoRecyclerAdapter;
import com.xdt.xudutong.adapder.HomeYanglaobaoxiannewRecyclerAdapter;
import com.xdt.xudutong.adapder.HomeYiliaobaoxiannewRecyclerAdapter;
import com.xdt.xudutong.adapder.HomecanbaobaoxiannewRecyclerAdapter;
import com.xdt.xudutong.adapder.HomegongshangbaoxiannewRecyclerAdapter;
import com.xdt.xudutong.adapder.HomezhengshiGongjijinnewRecyclerAdapter;
import com.xdt.xudutong.bean.DsepgetEmploymentInjuryInsurance;
import com.xdt.xudutong.bean.DsepgetEndowmentInsurance;
import com.xdt.xudutong.bean.DsepgetMaternityInsurance;
import com.xdt.xudutong.bean.DsepgetMedicalInsurance;
import com.xdt.xudutong.bean.DsepgetSocialInsurance;
import com.xdt.xudutong.bean.DsepgetUnemploymentInsurance;
import com.xdt.xudutong.bean.PubfundgetXcPubfund;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.Finaltext;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\10 0010.
 */

public class Home_yanglaodetails extends BaseActivity {

    private LinearLayout home_gongjijin_detailsback1;
    private TextView yanglaodetailsheadtext1;
    private RecyclerView home_yanglaorecycleview1;
    private TextView home_yanglaoname1;
    private ProgressDialog progressDialog;
    private String gongjijinidcardnumber;
    private String yanglaobaoxian;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_yanglao_details);
        progressDialog = ProgressDialog.showDialog(Home_yanglaodetails.this);
        progressDialog.show();
        Intent intent = getIntent();
        //获取养老保险和标题头
        gongjijinidcardnumber = intent.getStringExtra("yanglaoidcardnumber");
        yanglaobaoxian = intent.getStringExtra("yanglaobaoxian");
        switch (yanglaobaoxian) {
            case "公积金":
                ShowVolleyRequesforgongjijin(gongjijinidcardnumber);
                break;
            case "养老保险":
                ShowVolleyRequesforyanglao(gongjijinidcardnumber);
                break;
            case "医疗保险":
                ShowVolleyRequesforyiliao(gongjijinidcardnumber);
                break;
            case "工伤保险":
                ShowVolleyRequesforgongshang(gongjijinidcardnumber);
                break;
            case "失业保险":
                ShowVolleyRequesforshiye(gongjijinidcardnumber);
                break;
            case "生育保险":
                ShowVolleyRequesforshengyu(gongjijinidcardnumber);
                break;
            case "参保信息":
                ShowVolleyRequesforcanbao(gongjijinidcardnumber);
                break;
            default:
                System.out.println("default");
                break;
        }
        ;
    }

    @Override
    public void initView() {
        home_gongjijin_detailsback1 = (LinearLayout) findViewById(R.id.home_yanglao2_detailsback);
        yanglaodetailsheadtext1 = (TextView) findViewById(R.id.yanglaodetailsheadtext);
        home_yanglaoname1 = (TextView) findViewById(R.id.home_yanglaoname);
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        home_yanglaorecycleview1 = (RecyclerView) findViewById(R.id.home_yanglaorecycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        home_yanglaorecycleview1.setLayoutManager(layoutManager);
        List list = new ArrayList();
        List list1 = new ArrayList();
        HomeYanglaoRecyclerAdapter homeYanglaoRecyclerAdapter = new HomeYanglaoRecyclerAdapter(Home_yanglaodetails.this, list, list1);
        home_yanglaorecycleview1.setAdapter(homeYanglaoRecyclerAdapter);
        home_gongjijin_detailsback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    // 设置返回数据
                    Bundle bundle = new Bundle();
                    bundle.putString("yanglaobaoxian", yanglaobaoxian);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    // 返回intent
                    setResult(Finaltext.YILIAORESULTCODE, intent);
                    finish();
                }
            }
        });
        yanglaodetailsheadtext1.setText(yanglaobaoxian);
    }

    //查询养老保险请求
    private void ShowVolleyRequesforyanglao(String useridnumberstring) {
        String url = ApiUrls.GETENDOWMENTINSURANCE;
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNum", useridnumberstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetEndowmentInsurance dsepgetendowmentinsurance = gson.fromJson(response.toString(), DsepgetEndowmentInsurance.class);
                        String code = dsepgetendowmentinsurance.getCode();
                        if (code.equals("R00001")) {
                            successcontent();
                            //展示数据
                            List<DsepgetEndowmentInsurance.ContentBean.DataBean> dsepgetendowmentinsurancedata = dsepgetendowmentinsurance.getContent().getData();
                            showDataforyanglao(dsepgetendowmentinsurancedata);
                        } else {
                            failcontent();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.e("请求失败", error.getMessage()+ error);
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

    private void failcontent() {
        msearch_content.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        msearch_content.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }

    //查询公积金请求
    private void ShowVolleyRequesforgongjijin(String useridnumberstring) {
        String url = ApiUrls.GETXCPUBFUND;
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardID", useridnumberstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new Gson();
                        PubfundgetXcPubfund pubfundgetxcpubfund = gson.fromJson(response.toString(), PubfundgetXcPubfund.class);
                        String code = pubfundgetxcpubfund.getCode();
                        if (code.equals("R00001")) {
                            List<PubfundgetXcPubfund.ContentBean.DataBean> pubfundgetxcpubfunddata = pubfundgetxcpubfund.getContent().getData();
                            String unitCode = pubfundgetxcpubfunddata.get(0).getUserName();
                            if (TextUtils.isEmpty(unitCode)) {
                                failcontent();
                            } else {
                                successcontent();
                                //展示数据
                                showDataforgongjijin(pubfundgetxcpubfunddata);
                            }

                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.e("请求失败", error.getMessage()+error);
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

    //查询医疗保险请求
    private void ShowVolleyRequesforyiliao(String useridnumberstring) {
        String url = ApiUrls.GETMEDICALINSURANCE;
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNum", useridnumberstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetMedicalInsurance dsepgetmedicalinsurance = gson.fromJson(response.toString(), DsepgetMedicalInsurance.class);
                        String code = dsepgetmedicalinsurance.getCode();
                        if (code.equals("R00001")) {
                            successcontent();
                            //展示数据
                            List<DsepgetMedicalInsurance.ContentBean.DataBean> dsepgetmedicalinsurancedata = dsepgetmedicalinsurance.getContent().getData();
                            showDataforyiliao(dsepgetmedicalinsurancedata);
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.e("请求失败", error.getMessage()+ error);
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

    //查询工伤保险请求
    private void ShowVolleyRequesforgongshang(String useridnumberstring) {
        String url = ApiUrls.GETEMPLOYMENTINJURYINSURANCE;
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNum", useridnumberstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetEmploymentInjuryInsurance dsepgetemploymentinjuryinsurance = gson.fromJson(response.toString(), DsepgetEmploymentInjuryInsurance.class);
                        String code = dsepgetemploymentinjuryinsurance.getCode();
                        if (code.equals("R00001")) {
                            successcontent();
                            //展示数据
                            List<DsepgetEmploymentInjuryInsurance.ContentBean.DataBean> dsepgetemploymentinjuryinsurancedata = dsepgetemploymentinjuryinsurance.getContent().getData();
                            showDataforgongshang(dsepgetemploymentinjuryinsurancedata);
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.e("请求失败", error.getMessage()+error);
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

    //查询失业保险请求
    private void ShowVolleyRequesforshiye(String useridnumberstring) {
        String url = ApiUrls.GETUNEMPLOYMENTINSURANCE;
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNum", useridnumberstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetUnemploymentInsurance dsepgetunemploymentinsurance = gson.fromJson(response.toString(), DsepgetUnemploymentInsurance.class);
                        String code = dsepgetunemploymentinsurance.getCode();
                        if (code.equals("R00001")) {
                            successcontent();
                            //展示数据
                            List<DsepgetUnemploymentInsurance.ContentBean.DataBean> dsepgetunemploymentinsurancedata = dsepgetunemploymentinsurance.getContent().getData();
                            showDataforshiye(dsepgetunemploymentinsurancedata);
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.e("请求失败", error.getMessage()+error);
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

    //查询生育保险请求
    private void ShowVolleyRequesforshengyu(String useridnumberstring) {
        String url = ApiUrls.GETMATERNITYINSURANCE;
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNum", useridnumberstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetMaternityInsurance dsepgetmaternityinsurance = gson.fromJson(response.toString(), DsepgetMaternityInsurance.class);
                        String code = dsepgetmaternityinsurance.getCode();
                        if (code.equals("R00001")) {
                            successcontent();
                            //展示数据
                            List<DsepgetMaternityInsurance.ContentBean.DataBean> dsepgetmaternityinsurancedata = dsepgetmaternityinsurance.getContent().getData();
                            showDataforshengyu(dsepgetmaternityinsurancedata);
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.e("请求失败", error.getMessage()+error);
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

    //查询参保信息请求
    private void ShowVolleyRequesforcanbao(String useridnumberstring) {
        String url = ApiUrls.GETSOCIALINSURANCE;
        //在这里设置需要post的参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNum", useridnumberstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetSocialInsurance dsepgetsocialinsurance = gson.fromJson(response.toString(), DsepgetSocialInsurance.class);
                        String code = dsepgetsocialinsurance.getCode();
                        if (code.equals("R00001")) {
                            successcontent();
                            //展示数据
                            List<DsepgetSocialInsurance.ContentBean.DataBean> dsepgetsocialinsurancedata = dsepgetsocialinsurance.getContent().getData();
                            showDataforcanbao(dsepgetsocialinsurancedata);
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.e("请求失败", error.getMessage()+ error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    //展示数据公积金
    private void showDataforgongjijin(List<PubfundgetXcPubfund.ContentBean.DataBean> pubfundgetxcpubfunddata) {
        String userName = pubfundgetxcpubfunddata.get(0).getUserName();
        if (!TextUtils.isEmpty(userName)) {
            home_yanglaoname1.setText(userName);
        } else {
            home_yanglaoname1.setText("暂无数据");
        }
        //  String[] gongjijintext2 = new String[]{"单位账户", "身份证号", "个人账户", "个人账户状态", "起始汇缴年月"};
        String[] gongjijintext2 = new String[]{"身份证号", "单位代码", "个人代码", "月缴额(元)", "余额(元)", "缴费年限"};

        HomezhengshiGongjijinnewRecyclerAdapter homeYanglaoRecyclerAdapter = new HomezhengshiGongjijinnewRecyclerAdapter(Home_yanglaodetails.this, pubfundgetxcpubfunddata, gongjijintext2);
        home_yanglaorecycleview1.setAdapter(homeYanglaoRecyclerAdapter);
        homeYanglaoRecyclerAdapter.notifyDataSetChanged();
    }

    //展示数据养老
    private void showDataforyanglao(List<DsepgetEndowmentInsurance.ContentBean.DataBean> dsepgetendowmentinsurancedata) {
        DsepgetEndowmentInsurance.ContentBean.DataBean dsepgetendowmentinsurancedatadataBean = dsepgetendowmentinsurancedata.get(0);
        String fullName = dsepgetendowmentinsurancedatadataBean.getFullName();
        home_yanglaoname1.setText(fullName);

        String[] yanglaolisttext = new String[]{"证件号码 :", "险种类型 :", "首次参保日期 :", "待遇享受开始年月 :"};

        HomeYanglaobaoxiannewRecyclerAdapter homeYanglaoRecyclerAdapter = new HomeYanglaobaoxiannewRecyclerAdapter(Home_yanglaodetails.this, dsepgetendowmentinsurancedata, yanglaolisttext);
        home_yanglaorecycleview1.setAdapter(homeYanglaoRecyclerAdapter);
        homeYanglaoRecyclerAdapter.notifyDataSetChanged();
    }

    //展示数据医疗1
    private void showDataforyiliao(List<DsepgetMedicalInsurance.ContentBean.DataBean> dsepgetmedicalinsurancedata) {
        DsepgetMedicalInsurance.ContentBean.DataBean dsepgetendowmentinsurancedatadataBean1 = dsepgetmedicalinsurancedata.get(0);
        String fullName = dsepgetendowmentinsurancedatadataBean1.getFullName();
        home_yanglaoname1.setText(fullName);
       /* String[] yanglaolisttext = new String[]{"证件类型", "证件号码", "工作单位名称", "人员类别",
                "社会保障卡号", "社保险种", "参保状态", "参保日期", "缴费地区", "个人缴费基数金额"};*/
        String[] yanglaolisttext = new String[]{"性别 :","证件类型 :", "证件号码 :",
                "社会保障卡号 :", "参保日期 :", "缴费地区 :", "个人缴费基数金额 :"};

        HomeYiliaobaoxiannewRecyclerAdapter homeYanglaoRecyclerAdapter = new HomeYiliaobaoxiannewRecyclerAdapter(Home_yanglaodetails.this, dsepgetmedicalinsurancedata, yanglaolisttext);
        home_yanglaorecycleview1.setAdapter(homeYanglaoRecyclerAdapter);
        homeYanglaoRecyclerAdapter.notifyDataSetChanged();
    }

    //展示数据工伤1
    private void showDataforgongshang(List<DsepgetEmploymentInjuryInsurance.ContentBean.DataBean> dsepgetemploymentinjuryinsurancedata) {
        DsepgetEmploymentInjuryInsurance.ContentBean.DataBean dataBean = dsepgetemploymentinjuryinsurancedata.get(0);
        String fullName = dataBean.getFullName();
        home_yanglaoname1.setText(fullName);
        String[] yanglaolisttext = new String[]{"性别","证件类型", "证件号码", "工伤发生日期", "工伤认定日期", "工伤伤情及认定部位", "参保日期"};
        HomegongshangbaoxiannewRecyclerAdapter homeYanglaoRecyclerAdapter = new HomegongshangbaoxiannewRecyclerAdapter(Home_yanglaodetails.this, dsepgetemploymentinjuryinsurancedata, yanglaolisttext);
        home_yanglaorecycleview1.setAdapter(homeYanglaoRecyclerAdapter);
        homeYanglaoRecyclerAdapter.notifyDataSetChanged();
    }

    //展示数据失业1
    private void showDataforshiye(List<DsepgetUnemploymentInsurance.ContentBean.DataBean> dsepgetunemploymentinsurancedata) {
        DsepgetUnemploymentInsurance.ContentBean.DataBean dsepgetunemploymentinsurancedataBean = dsepgetunemploymentinsurancedata.get(0);
        String fullName = dsepgetunemploymentinsurancedataBean.getFullName();
        home_yanglaoname1.setText(fullName);
        String[] yanglaolisttext = new String[]{"性别 :","民族 :","证件类型 :", "证件号码 :",  "保险编号 :", "社保险种 :", "个人缴纳失业保险费 :",
                "待遇享受开始日期 :", "待遇享受结束日期 :", "参加工作时间 :", "参保日期 :"};
        HomeShiyebaoxiannewRecyclerAdapter homeYanglaoRecyclerAdapter = new HomeShiyebaoxiannewRecyclerAdapter(Home_yanglaodetails.this, dsepgetunemploymentinsurancedata, yanglaolisttext);
        home_yanglaorecycleview1.setAdapter(homeYanglaoRecyclerAdapter);
        homeYanglaoRecyclerAdapter.notifyDataSetChanged();
    }

    //展示数据生育
    private void showDataforshengyu(List<DsepgetMaternityInsurance.ContentBean.DataBean> dsepgetmaternityinsurancedata) {
        DsepgetMaternityInsurance.ContentBean.DataBean dataBean = dsepgetmaternityinsurancedata.get(0);
        String fullName = dataBean.getFullName();
        home_yanglaoname1.setText(fullName);

        //  String[] yanglaolisttext = new String[]{"证件类型", "证件号码", "生育人员类别", "选定住院生育医疗机构名称", "参保日期"};
        String[] yanglaolisttext = new String[]{"证件类型 :", "证件号码 :", "选定住院生育医疗机构名称 :", "参保日期 :"};


        HomeShengyubaoxiannewRecyclerAdapter homeYanglaoRecyclerAdapter = new HomeShengyubaoxiannewRecyclerAdapter(Home_yanglaodetails.this, dsepgetmaternityinsurancedata, yanglaolisttext);
        home_yanglaorecycleview1.setAdapter(homeYanglaoRecyclerAdapter);
        homeYanglaoRecyclerAdapter.notifyDataSetChanged();
    }

    //展示数据参保
    private void showDataforcanbao(List<DsepgetSocialInsurance.ContentBean.DataBean> dsepgetsocialinsurancedata) {
        DsepgetSocialInsurance.ContentBean.DataBean dataBean = dsepgetsocialinsurancedata.get(0);
        String fullName = dataBean.getFullName();
        home_yanglaoname1.setText(fullName);

        String[] yanglaolisttext = new String[]{"个人社保号", "证件号", "单位社保号", "人员参保状态", "个人缴费状态", "个人参保日期", "险种类型"};

        HomecanbaobaoxiannewRecyclerAdapter homeYanglaoRecyclerAdapter = new HomecanbaobaoxiannewRecyclerAdapter(Home_yanglaodetails.this, dsepgetsocialinsurancedata, yanglaolisttext);
        home_yanglaorecycleview1.setAdapter(homeYanglaoRecyclerAdapter);
        homeYanglaoRecyclerAdapter.notifyDataSetChanged();
    }
}
