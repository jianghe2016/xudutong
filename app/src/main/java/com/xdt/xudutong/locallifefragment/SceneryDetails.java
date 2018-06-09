package com.xdt.xudutong.locallifefragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ViewselectViewDetali;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.GlideImageLoader;
import com.xdt.xudutong.view.MapContainer;
import com.xdt.xudutong.view.ProgressDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\6\28 0028.
 */

public class SceneryDetails extends AppCompatActivity {

    private TextView mlocallife_scenerydetialtext1;
    private TextView mlocallife_scenerydetialtext2;
    private TextView mlocallife_scenerydetialtext3;
    private TextView mlocallife_scenerydetialtext4;
    private Banner locallife_scenerydetialviewpager1;
    private AMap aMap = null;
    private ImageView locallife_scenerydetialimgview1;
    private ProgressDialog progressDialog;
    private MapView mSceneryDetailsmapview;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;
    // private LinearLayout locallife_scenerydetails1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locallife_scenerydetails);
        progressDialog = ProgressDialog.showDialog(SceneryDetails.this);
        progressDialog.show();
        mSceneryDetailsmapview = (MapView) findViewById(R.id.SceneryDetailsmapview);
        mSceneryDetailsmapview.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String sceneryid = intent.getStringExtra("sceneryid");
        ShowVolleyRequest(sceneryid, savedInstanceState);
        initView();
    }

    public void initView() {
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        LinearLayout locallife_scenerydetailsback1 = (LinearLayout) findViewById(R.id.locallife_scenerydetailsback);
        locallife_scenerydetialviewpager1 = (Banner) findViewById(R.id.locallife_scenerydetialviewpager);
        //名称
        mlocallife_scenerydetialtext1 = (TextView) findViewById(R.id.locallife_scenerydetialtext1);
        //开放时间
        mlocallife_scenerydetialtext2 = (TextView) findViewById(R.id.locallife_scenerydetialtext2);
        //景点地址
        mlocallife_scenerydetialtext3 = (TextView) findViewById(R.id.locallife_scenerydetialtext3);
        //内容详情介绍
        mlocallife_scenerydetialtext4 = (TextView) findViewById(R.id.locallife_scenerydetialtext4);
        locallife_scenerydetialimgview1 = (ImageView) findViewById(R.id.locallife_scenerydetialimgview);

        ScrollView locallife_scenerydetialscrollview1 = (ScrollView) findViewById(R.id.locallife_scenerydetialscrollview);
        MapContainer locallife_scenerydetialMapContainer1 = (MapContainer) findViewById(R.id.locallife_scenerydetialMapContainer);
        locallife_scenerydetialMapContainer1.setScrollView(locallife_scenerydetialscrollview1);
        if (aMap == null) {
            aMap = mSceneryDetailsmapview.getMap();
        }
        locallife_scenerydetailsback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mSceneryDetailsmapview.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mSceneryDetailsmapview != null) {
            mSceneryDetailsmapview.onResume();
        }

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSceneryDetailsmapview != null) {
            mSceneryDetailsmapview.onDestroy();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (mSceneryDetailsmapview != null) {
            mSceneryDetailsmapview.onPause();
        }

    }


    private void ShowVolleyRequest(String sceneryid, final Bundle savedInstanceState) {
        String url = ApiUrls.SELECTVIEW;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", sceneryid);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ViewselectViewDetali viewselectviewdetali = gson.fromJson(response.toString(), ViewselectViewDetali.class);
                        int flag = viewselectviewdetali.getFlag();
                        if (flag == 1) {
                            successcontent();
                            List<ViewselectViewDetali.ContentBean.DataBean> viewselectviewdetalidata = viewselectviewdetali.getContent().getData();
                            ShowData(viewselectviewdetalidata);
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
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

    private void ShowData(List<ViewselectViewDetali.ContentBean.DataBean> viewselectviewdetalidata) {
        String view_pic = viewselectviewdetalidata.get(0).getVIEW_PIC();
        List list = new ArrayList<>();
        //判断是单张图片还是多张，如果是多张，则按；切割成数组，如果是单张，则直接请求网络展示
        if (view_pic.indexOf(";") != -1) {
            String[] split = view_pic.split(";");
            if (split != null && split.length >= 1) {
                for (int i = 0; i < split.length; i++) {
                    list.add(split[i]);
                }
                locallife_scenerydetialviewpager1.setImageLoader(new GlideImageLoader());
                locallife_scenerydetialviewpager1.setImages(list);
                locallife_scenerydetialviewpager1.isAutoPlay(true);
                //设置轮播时间
                locallife_scenerydetialviewpager1.setDelayTime(1500);
                locallife_scenerydetialviewpager1.setIndicatorGravity(BannerConfig.CENTER);
                locallife_scenerydetialviewpager1.start();
            }
        } else {
            Glide.with(getApplicationContext()).load(view_pic).into(locallife_scenerydetialimgview1);
        }
        String view_name = viewselectviewdetalidata.get(0).getVIEW_NAME();
        mlocallife_scenerydetialtext1.setText(view_name + "介绍");
        mlocallife_scenerydetialtext2.setText("开放时间:  " + viewselectviewdetalidata.get(0).getOPEN_TIME());
        mlocallife_scenerydetialtext3.setText("景点地址:  " + viewselectviewdetalidata.get(0).getVIEW_ADDR());
        mlocallife_scenerydetialtext4.setText("        " + viewselectviewdetalidata.get(0).getVIEW_DESC());

        //创建InfoWindow展示的view
        /*LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.locallife_baidumap,
                (ViewGroup) findViewById(R.id.locallife_scenerydetails));*/
        Button btn = new Button(SceneryDetails.this);
        btn.setBackgroundResource(R.drawable.popup);
        btn.setTextColor(0xAA000000);
        //定义用于显示该InfoWindow的坐标点
    /*    TextView locallife_scenerydetailsimg1 = (TextView) layout.findViewById(R.id.locallife_scenerydetailsimg);
        TextView locallife_scenerydetailstext1 = (TextView) layout.findViewById(R.id.locallife_scenerydetailstext);*/
        btn.setText(viewselectviewdetalidata.get(0).getVIEW_NAME());
        String view_location = viewselectviewdetalidata.get(0).getVIEW_LOCATION();
        String[] view_locationsplit = view_location.split(",");
        //获取经纬度
        String s = view_locationsplit[0];
        String s1 = view_locationsplit[1];
        Double aDouble = Double.valueOf(s);
        Double aDouble2 = Double.valueOf(s1);
        //显示marker
        //    LatLng pt = new LatLng(aDouble2, aDouble);
        double[] doubles = bdToGaoDe(aDouble, aDouble2);
        double locallatitude = doubles[0];
        double locallongitude = doubles[1];
        MarkerOptions markerOption = new MarkerOptions();
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locallatitude, locallongitude), 15));
        LatLng latLng = new LatLng(locallatitude, locallongitude);
        markerOption.title(view_name);
        markerOption.position(latLng);
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.drawable.shape_gaodeyuandian)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        Marker marker = aMap.addMarker(markerOption);
        marker.setInfoWindowEnable(true);
        marker.showInfoWindow();

    }

    private double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
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
}
