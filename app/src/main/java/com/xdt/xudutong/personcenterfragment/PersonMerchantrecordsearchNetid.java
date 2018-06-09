package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.PersonmerchansearchNetIdone1Adapter;
import com.xdt.xudutong.adapder.PersonmerchansearchNetIdone1InnerAdapter;
import com.xdt.xudutong.adapder.PersonmerchansearchNetIdone2Adapter;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017\11\23 0023.
 */

public class PersonMerchantrecordsearchNetid extends BaseActivity {

    private RecyclerView mhome_merchantrecordseearchnextrecycleviewone;
    private RecyclerView mhome_merchantrecordseearchnextrecycleviewonedetailsrecycleview;
    private String mnowdate;
    private String mcompletemerchantslecturl;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.personmerchantrecordsearchnetid);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        mnowdate = intent.getStringExtra("nowdate");
        mcompletemerchantslecturl = intent.getStringExtra("completemerchantslecturl");
        String loadinnetid = intent.getStringExtra("loadinnetid");
        LinearLayout mhome_merchantrecordseearchnextlinearlayoutone = (LinearLayout) findViewById(R.id.home_merchantrecordseearchnextlinearlayoutone);
        LinearLayout home_merchantrecordseearchnextback = (LinearLayout) findViewById(R.id.home_merchantrecordseearchnextback);
        mhome_merchantrecordseearchnextrecycleviewone = (RecyclerView) findViewById(R.id.home_merchantrecordseearchnextrecycleviewone1);
        mhome_merchantrecordseearchnextrecycleviewonedetailsrecycleview = (RecyclerView) findViewById(R.id.home_merchantrecordseearchnextrecycleviewone2);
        final ImageView home_merchantrecordseearchnextimgtop = (ImageView) findViewById(R.id.home_merchantrecordseearchnextimgtop);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonMerchantrecordsearchNetid.this);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(PersonMerchantrecordsearchNetid.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        mhome_merchantrecordseearchnextrecycleviewone.setLayoutManager(linearLayoutManager);
        mhome_merchantrecordseearchnextrecycleviewonedetailsrecycleview.setLayoutManager(linearLayoutManager2);
        Requestexpendtransactiondetails(loadinnetid);
        home_merchantrecordseearchnextback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mhome_merchantrecordseearchnextlinearlayoutone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mhome_merchantrecordseearchnextrecycleviewone.getVisibility() == v.GONE) {
                    home_merchantrecordseearchnextimgtop.setImageDrawable(getResources().getDrawable(R.drawable.personmerchantsearchnetiddetailsnext2));
                    mhome_merchantrecordseearchnextrecycleviewone.setVisibility(View.VISIBLE);
                    mhome_merchantrecordseearchnextrecycleviewonedetailsrecycleview.setVisibility(View.VISIBLE);
                } else {
                    home_merchantrecordseearchnextimgtop.setImageDrawable(getResources().getDrawable(R.drawable.personmerchantsearchnetiddetailsnext1));
                    mhome_merchantrecordseearchnextrecycleviewone.setVisibility(View.GONE);
                    mhome_merchantrecordseearchnextrecycleviewonedetailsrecycleview.setVisibility(View.GONE);
                }
            }
        });
    }


    private void Requestexpendtransactiondetails(String loadinnetid) {
        //加载更多传参
        String url = ApiUrls.GETBASEEMPLOYEE;
        Map<String, String> params = new HashMap<String, String>();
        params.put("netid", loadinnetid);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String code = response.getString("code");
                    if (code.equals("R00001")) {
                        JSONObject content = response.getJSONObject("content");
                        JSONObject netSite1 = content.getJSONObject("netSite");
                        JSONArray netSites1 = netSite1.getJSONArray("netSites");
                        JSONArray listUsers1 = netSite1.getJSONArray("listUsers");
                        LogUtil.d("netSite1netSite1", netSites1 + "");
                        LogUtil.d("listUsers1listUsers1", listUsers1 + "");
                        ShowDataListonerecycleviewone1(netSites1);
                        ShowDataListonerecycleviewone2(listUsers1);
                    } else {
                        String  desc= response.getString("desc");
                        ToastUtils.getInstance(PersonMerchantrecordsearchNetid.this).showMessage(desc);
                    }
                } catch (JSONException e) {
                    LogUtil.d("捕捉到了选择商户网点的异常", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(PersonMerchantrecordsearchNetid.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
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

    private void ShowDataListonerecycleviewone1(final JSONArray onenetsize) {
        PersonmerchansearchNetIdone1Adapter personmerchansearchNetIdone1Adapter = new PersonmerchansearchNetIdone1Adapter(PersonMerchantrecordsearchNetid.this, onenetsize);
        mhome_merchantrecordseearchnextrecycleviewone.setAdapter(personmerchansearchNetIdone1Adapter);
        personmerchansearchNetIdone1Adapter.setOnItemClickListener(new PersonmerchansearchNetIdone1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position1) {
                final RecyclerView inneradapterrecycleview = (RecyclerView) view.findViewById(R.id.person_merchant_searchnetid_recycleviewone1_innerrecycleview);
                final ImageView person_merchant_searchnetid_recycleviewone1_item1img11 = (ImageView) view.findViewById(R.id.person_merchant_searchnetid_recycleviewone1_item1img1);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonMerchantrecordsearchNetid.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                inneradapterrecycleview.setLayoutManager(linearLayoutManager);

                try {
                    final JSONArray netname = onenetsize.getJSONObject(position1).getJSONArray("listUsers");
                    if (netname.length()>0){
                        if (inneradapterrecycleview.getVisibility() == view.GONE) {
                            person_merchant_searchnetid_recycleviewone1_item1img11.setImageDrawable(getResources().getDrawable(R.drawable.personmerchantsearchnetiddetailsnext2));
                            inneradapterrecycleview.setVisibility(View.VISIBLE);
                        } else {
                            person_merchant_searchnetid_recycleviewone1_item1img11.setImageDrawable(getResources().getDrawable(R.drawable.personmerchantsearchnetiddetailsnext1));
                            inneradapterrecycleview.setVisibility(View.GONE);
                        }
                    }else{
                        ToastUtils.getInstance(PersonMerchantrecordsearchNetid.this).showMessage("暂无下级数据");
                    }
                    PersonmerchansearchNetIdone1InnerAdapter personmerchansearchNetIdone2InnerAdapter = new PersonmerchansearchNetIdone1InnerAdapter(PersonMerchantrecordsearchNetid.this, netname);
                    inneradapterrecycleview.setAdapter(personmerchansearchNetIdone2InnerAdapter);
                    personmerchansearchNetIdone2InnerAdapter.setOnItemClickListener(new PersonmerchansearchNetIdone1InnerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            try {

                                String empid = netname.getJSONObject(position).getString("empid");
                                String listUsersname = netname.getJSONObject(position).getString("name");
                                Bundle bundle = new Bundle();
                                bundle.putString("listusersnetid", empid);
                                bundle.putString("listUsersname", listUsersname);
                                Intent intent = new Intent();
                                intent.putExtras(bundle);
                                // 返回intent
                                setResult(32, intent);
                                finish();
                            } catch (JSONException e) {
                                LogUtil.d("商家详情页面内部的innerad被捕捉了3", e + "");
                            }
                        }
                    });
                } catch (JSONException e) {
                    LogUtil.d("商家详情页面内部的innerad被捕捉了0", e + "");
                }

            }
        });
    }

    private void ShowDataListonerecycleviewone2(final JSONArray listUsers) {
        PersonmerchansearchNetIdone2Adapter personmerchansearchNetIdone2Adapter = new PersonmerchansearchNetIdone2Adapter(PersonMerchantrecordsearchNetid.this, listUsers);
        mhome_merchantrecordseearchnextrecycleviewonedetailsrecycleview.setAdapter(personmerchansearchNetIdone2Adapter);
        personmerchansearchNetIdone2Adapter.setOnItemClickListener(new PersonmerchansearchNetIdone2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                try {
                    String empid = listUsers.getJSONObject(position).getString("empid");
                    String listUsersname = listUsers.getJSONObject(position).getString("name");
                    Bundle bundle = new Bundle();
                    bundle.putString("listusersnetid", empid);
                    bundle.putString("listUsersname", listUsersname);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    // 返回intent
                    setResult(31, intent);
                    finish();
                } catch (JSONException e) {
                    LogUtil.d("商家详情页面内部的innerad被捕捉了2", e + "");
                }
            }
        });
    }
}
