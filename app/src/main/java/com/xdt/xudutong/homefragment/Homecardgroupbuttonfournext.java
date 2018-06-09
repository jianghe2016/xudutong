package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.bruce.pickerview.LoopView;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ZwjfeegetBuildings;
import com.xdt.xudutong.bean.ZwjfeegetHouses;
import com.xdt.xudutong.bean.ZwjfeegetUnits;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\7\8 0008.
 */

public class Homecardgroupbuttonfournext extends BaseActivity {
    private TextView mhome_cardgroup_button4nexttext1;
    private TextView mhome_cardgroup_button4nextselect1;
    private TextView mhome_cardgroup_button4nextselect2;
    private TextView mhome_cardgroup_button4nextselect3;
    private TextView mhome_cardgroup_button4nextsubmit;

    private LinearLayout homebuttongroup_button4nextback1;
    private ProgressBar home_cardgroup_button4nextprogressbar1;
    private LoopView mwheel_view_wv1;
    private TextView woyeselectcancle1;
    private TextView woyeselectyes1;

    private List<ZwjfeegetBuildings.ContentBean.DataBean> zwjfeegetbuildingsdata;
    private List<ZwjfeegetUnits.ContentBean.DataBean> zwjfeegetunitsdata;
    private List<ZwjfeegetHouses.ContentBean.DataBean> zwjfeegethousesdata;
    private String houseId;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_button4next);
    }

    @Override
    public void initView() {
        homebuttongroup_button4nextback1 = (LinearLayout) findViewById(R.id.homebuttongroup_button4nextback);
        home_cardgroup_button4nextprogressbar1 = (ProgressBar) findViewById(R.id.home_cardgroup_button4nextprogressbar);
        mhome_cardgroup_button4nexttext1 = (TextView) findViewById(R.id.home_cardgroup_button4nexttext1);
        mhome_cardgroup_button4nextselect1 = (TextView) findViewById(R.id.home_cardgroup_button4nextselect1);
        mhome_cardgroup_button4nextselect2 = (TextView) findViewById(R.id.home_cardgroup_button4nextselect2);
        mhome_cardgroup_button4nextselect3 = (TextView) findViewById(R.id.home_cardgroup_button4nextselect3);
        mhome_cardgroup_button4nextsubmit = (TextView) findViewById(R.id.home_cardgroup_button4nextsubmit);
        mwheel_view_wv1 = (LoopView) findViewById(R.id.wheel_view_wv1);
        woyeselectcancle1 = (TextView) findViewById(R.id.woyeselectcancle);
        woyeselectyes1 = (TextView) findViewById(R.id.woyeselectyes);
        initData();
    }


    private void initData() {
        homebuttongroup_button4nextback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        final Intent intent = getIntent();
        String community_id = intent.getStringExtra("community_id");
        String community_name = intent.getStringExtra("community_name");
        mhome_cardgroup_button4nexttext1.setText(community_name);
        String community_idtext = "community_id";
        ShowVolleyRequest(community_idtext, community_id);
        mhome_cardgroup_button4nextsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String s1 = mhome_cardgroup_button4nextselect1.getText().toString();
                    String s2 = mhome_cardgroup_button4nextselect2.getText().toString();
                    String s3 = mhome_cardgroup_button4nextselect3.getText().toString();
                    if (!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty()) {
                        Intent intent1 = new Intent(Homecardgroupbuttonfournext.this, Homecardgroupbuttonfournextdetails.class);
                        if (null!=houseId  && !houseId.isEmpty()) {
                            intent1.putExtra("houseId", houseId);
                        }
                        startActivity(intent1);
                    } else {
                        ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage("请选择楼宇");
                    }
                }
            }
        });


        mhome_cardgroup_button4nextselect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    if (!mhome_cardgroup_button4nextselect1.getText().toString().isEmpty()) {
                        if (null != zwjfeegetunitsdata && !zwjfeegetunitsdata.isEmpty()) {
                            final List list = new ArrayList();
                            for (int i = 0; i < zwjfeegetunitsdata.size(); i++) {
                                String building_name = zwjfeegetunitsdata.get(i).getUnit_name();
                                list.add(building_name);
                            }
                            //更新数据
                            Updatenumber(mhome_cardgroup_button4nextselect2, list);
                        } else {
                            ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage("楼宇数据为空，请重新选择");
                        }
                    } else {
                        ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage("请选择楼宇");
                    }
                }
            }
        });
        mhome_cardgroup_button4nextselect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    if (!mhome_cardgroup_button4nextselect1.getText().toString().isEmpty() && !mhome_cardgroup_button4nextselect2.getText().toString().isEmpty()) {
                        if (null != zwjfeegethousesdata && !zwjfeegethousesdata.isEmpty()) {
                            final List list = new ArrayList();
                            for (int i = 0; i < zwjfeegethousesdata.size(); i++) {
                                String building_name = zwjfeegethousesdata.get(i).getHouseNumber();
                                list.add(building_name);
                            }
                            //更新数据
                            Updatenumber(mhome_cardgroup_button4nextselect3, list);
                        } else {
                            ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage("单元数据为空，请重新选择");
                        }

                    } else {
                        ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage("请选择楼宇或单元");
                    }
                }
            }
        });
    }

    private void ShowVolleyRequest(String community_idtext, String community_id) {
        String url = ApiUrls.GETBUILDINGS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put(community_idtext, community_id);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ZwjfeegetBuildings zwjfeegetbuildings = gson.fromJson(response.toString(), ZwjfeegetBuildings.class);
                        int flag = zwjfeegetbuildings.getFlag();
                        if (flag == 1) {
                            zwjfeegetbuildingsdata = zwjfeegetbuildings.getContent().getData();
                            home_cardgroup_button4nextprogressbar1.setVisibility(View.GONE);
                            mhome_cardgroup_button4nextselect1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    final List list = new ArrayList();
                                    for (int i = 0; i < zwjfeegetbuildingsdata.size(); i++) {
                                        String building_name = zwjfeegetbuildingsdata.get(i).getBuilding_name();
                                        list.add(building_name);
                                    }
                                    //更新数据
                                    Updatenumber(mhome_cardgroup_button4nextselect1, list);
                                }
                            });
                            LogUtil.d("请求成功楼宇=====", "请求成功");
                        } else {
                            home_cardgroup_button4nextprogressbar1.setVisibility(View.GONE);
                            String desc = zwjfeegetbuildings.getDesc();
                            ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                home_cardgroup_button4nextprogressbar1.setVisibility(View.GONE);
                ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage("系统繁忙");
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


    private void ShowVolleyRequest2(String building_id) {
        String url = ApiUrls.GETUNITS;
        String buildindidtext = "building_id";
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put(buildindidtext, building_id);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ZwjfeegetUnits zwjfeegetunits = gson.fromJson(response.toString(), ZwjfeegetUnits.class);
                        int flag = zwjfeegetunits.getFlag();
                        if (flag == 1) {
                            LogUtil.d("请求成功单元=====", "请求成功");
                            zwjfeegetunitsdata = zwjfeegetunits.getContent().getData();
                        } else {
                            String desc = zwjfeegetunits.getDesc();
                            ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage("系统繁忙");
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

    private void ShowVolleyRequest3(String building_unit_id) {
        String url = ApiUrls.GETHOUSES;
        String building_unit_idtext = "unit_id";
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put(building_unit_idtext, building_unit_id);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ZwjfeegetHouses zwjfeegethouses = gson.fromJson(response.toString(), ZwjfeegetHouses.class);
                        int flag = zwjfeegethouses.getFlag();
                        if (flag == 1) {
                            zwjfeegethousesdata = zwjfeegethouses.getContent().getData();

                        } else {
                            String desc = zwjfeegethouses.getDesc();
                            ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Homecardgroupbuttonfournext.this).showMessage("系统繁忙");
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

    private void Updatenumber(final TextView a, final List list) {
        woyeselectcancle1.setVisibility(View.VISIBLE);
        woyeselectyes1.setVisibility(View.VISIBLE);
        mwheel_view_wv1.setVisibility(View.VISIBLE);
        mwheel_view_wv1.setInitPosition(0);
        mwheel_view_wv1.setCanLoop(false);

        mwheel_view_wv1.setTextSize(22);
        mwheel_view_wv1.setDataList(list);

        woyeselectcancle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    mwheel_view_wv1.setVisibility(View.GONE);
                    woyeselectcancle1.setVisibility(View.GONE);
                    woyeselectyes1.setVisibility(View.GONE);
                }
            }
        });

        woyeselectyes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    mwheel_view_wv1.setVisibility(View.GONE);
                    woyeselectcancle1.setVisibility(View.GONE);
                    woyeselectyes1.setVisibility(View.GONE);
                    int selectedItem = mwheel_view_wv1.getSelectedItem();
                    a.setText(list.get(selectedItem).toString());
                    if (a == mhome_cardgroup_button4nextselect1) {
                        ZwjfeegetBuildings.ContentBean.DataBean dataBean = zwjfeegetbuildingsdata.get(selectedItem);
                        String building_id = dataBean.getBuilding_id();
                        ShowVolleyRequest2(building_id);
                    } else if (a == mhome_cardgroup_button4nextselect2) {
                        ZwjfeegetUnits.ContentBean.DataBean dataBean = zwjfeegetunitsdata.get(selectedItem);
                        String building_id = dataBean.getBuilding_unit_id();
                        ShowVolleyRequest3(building_id);
                    } else if (a == mhome_cardgroup_button4nextselect3) {
                        ZwjfeegetHouses.ContentBean.DataBean dataBean = zwjfeegethousesdata.get(selectedItem);
                        houseId = dataBean.getHouseId();
                    }
                }
            }
        });
    }

}
