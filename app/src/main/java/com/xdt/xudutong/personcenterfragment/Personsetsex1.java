package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.xdt.xudutong.bean.VipupdateUserInfo;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/13.
 */
public class Personsetsex1 extends BaseActivity {

    private final String[] strs = new String[]{
            "男", "女"
    };
    private TextView save;
    private List sexlist;
    private String token1;
    private String token2;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_setsex1);
    }

    @Override
    public void initView() {
        LinearLayout person_setsexnextback1 = (LinearLayout) findViewById(R.id.person_setsexnextback);
        ListView setsexlistview = (ListView) findViewById(R.id.person_setsex_listview);
        save = (TextView) findViewById(R.id.person_baocun);
        person_setsexnextback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        setsexlistview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, strs));
        setsexlistview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        sexlist = new ArrayList();
        sexlist.add("男");
        sexlist.add("女");
        setsexlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击后在标题上显示点击了第几行
                final String selcetsex = sexlist.get(position).toString();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (fastClick()) {
                            token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
                            token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
                            if (token1 != null && !token1.isEmpty()) {
                                ShowVolleyRequest(selcetsex);
                            } else {
                                ToastUtils.getInstance(Personsetsex1.this).showMessage("系统繁忙");
                            }
                        }
                    }
                });
            }
        });
    }

    private void ShowVolleyRequest(final String selcetsex) {
        String url = ApiUrls.UPDATEUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("gender", selcetsex);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (codestring.equals("R00001")) {
                            VipupdateUserInfo VipupdateUserInfo = gson.fromJson(response.toString(), VipupdateUserInfo.class);
                            int flag = VipupdateUserInfo.getFlag();
                            Bundle bundle = new Bundle();
                            bundle.putString("sexupdate", selcetsex);
                            Intent intent = new Intent();
                            intent.putExtras(bundle);
                            // 返回intent
                            setResult(15, intent);
                            finish();
                        } else {
                            ToastUtils.getInstance(Personsetsex1.this).showMessage("会话已过期，请重新登录");
                            Intent intent = new Intent(Personsetsex1.this, Personuser_comein.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personsetsex1.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }
}
