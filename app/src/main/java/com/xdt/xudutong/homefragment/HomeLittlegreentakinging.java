package com.xdt.xudutong.homefragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.xdt.xudutong.R;
import com.xdt.xudutong.crashexception.AppManager;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.xdt.xudutong.utils.ApplicationController.getContext;

/**
 * Created by Administrator on 2017\8\4 0004.
 */
//平移动画，-向上，+向下
public class HomeLittlegreentakinging extends AppCompatActivity implements LocationSource,
        AMapLocationListener, AMap.OnCameraChangeListener
        , AMap.OnMapClickListener, AMap.OnMapLoadedListener {
    private LinearLayout mhomebuttongroup_xiaolvback;
    private MapView gaodemapview1;
    private AMap aMap = null;
    /**
     * 用于显示当前的位置
     * <p>
     * 示例中是为了显示当前的位置，在实际使用中，单独的地理围栏可以不使用定位接口
     * </p>
     */
    private AMapLocationClient mlocationClient;
    private OnLocationChangedListener mListener;
    private AMapLocationClientOption mLocationOption;
    // 中心点坐标
    private LatLng centerLatLng = null;
    // 中心点marker

    //private List<Marker> mList = new ArrayList<Marker>();

    private Marker marker;

    private LinearLayout mxiaolvtop;
    private PopupWindow popupWindow2;
    private String address;
    private double locallatitude;
    private double locallongitude;
    private LinearLayout mcustom_gaodeinfo_windowlayout;
    private TextView mcustom_gaodeinfo_window1;
    private TextView mcustom_gaodeinfo_window2;
    private TextView mcustom_gaodeinfo_window3;
    private boolean mymarkflag;
    private ImageView centerImageView;
    private android.view.animation.Animation centeranimation;

    // 当前的坐标点集合，主要用于进行地图的可视区域的缩放
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_littlegreentakinginglayout);
        gaodemapview1 = (MapView) findViewById(R.id.gaodemapview);
        gaodemapview1.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            //取消状态栏透明
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //添加Flag把状态栏设为可绘制模式
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getResources().getColor(R.color.locallife_headviewtext));
            //设置系统状态栏处于可见状态
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            //让view不根据系统窗口来调整自己的布局
            ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
                ViewCompat.requestApplyInsets(mChildView);
            }
        }
        initView();
    }


    public void initView() {
        Intent intent = getIntent();
        String xdtcardNo = intent.getStringExtra("xdtcardNo");
        mhomebuttongroup_xiaolvback = (LinearLayout) findViewById(R.id.custom_gaodelittlegreentakingingback);
        centerImageView = (ImageView) findViewById(R.id.custom_gaodetaking_centermark);
        mxiaolvtop = (LinearLayout) findViewById(R.id.xiaolvtakingtop);
        mcustom_gaodeinfo_windowlayout = (LinearLayout) findViewById(R.id.custom_gaodelittlegreentakingtopview);
        mcustom_gaodeinfo_window1 = (TextView) findViewById(R.id.custom_gaodelittlegreentakinging_window_text1);
        mcustom_gaodeinfo_window2 = (TextView) findViewById(R.id.custom_gaodelittlegreentakinging_window_text2);
        mcustom_gaodeinfo_window3 = (TextView) findViewById(R.id.custom_gaodelittlegreentakinging_window_text3);
        centeranimation = AnimationUtils.loadAnimation(HomeLittlegreentakinging.this, R.anim.center_map_bounds);
        if (aMap == null) {
            aMap = gaodemapview1.getMap();
        }
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        //aMap.moveCamera(CameraUpdateFactory.zoomBy(16));
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locallatitude, locallongitude), 15));
        setUpMap();
        initData(xdtcardNo);
    }


    private void initData(String xdtcardNo) {
        mhomebuttongroup_xiaolvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //卡号，开锁识别号
        if (!TextUtils.isEmpty(xdtcardNo)) {
            ShowVolleyRequestforxiaolv(xdtcardNo);
        } else {
            ToastUtils.getInstance(HomeLittlegreentakinging.this).showMessage("系统繁忙");
        }

    }

    private void setUpMap() {
        //取消右下角的缩放
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.setOnMapClickListener(this);
        aMap.setOnCameraChangeListener(this);//位置发生变化监听器
        aMap.setOnMapLoadedListener(this);//位置发生变化监听器
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
   /*   myLocationStyle.myLocationIcon(
                BitmapDescriptorFactory.fromResource(R.drawable.gaodemap_center_marker));*/
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(0);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        // 定位、且将视角移动到地图中心点,定位点依照设备方向旋转,并且会跟随设备移动。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);

        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        gaodemapview1.onSaveInstanceState(outState);
    }


    //请求小绿正在骑行的数据
    private void ShowVolleyRequestforxiaolv(String cardfaceno) {
        String urltruename = ApiUrls.WSBIKEAPPSCANCODECHECKCREDITRECORD;
        Map<String, String> params = new HashMap<>();
        params.put("cardfaceno", cardfaceno);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = response.getString("code");
                            if (code.equals("R00001")) {
                                //无不良记录

                            } else if (code.equals("R00002")) {
                                //用户正在骑行
                                JSONObject content = response.getJSONObject("content");
                                JSONObject data = content.getJSONObject("data");
                                String bikesn = data.getString("bikesn");
                                String costTime = data.getString("costTime");
                                String cost = data.getString("cost");
                                if (!TextUtils.isEmpty(bikesn)) {
                                    mcustom_gaodeinfo_window1.setText(costTime);
                                }
                                if (!TextUtils.isEmpty(costTime)) {
                                    mcustom_gaodeinfo_window2.setText(bikesn);
                                }
                                if (!TextUtils.isEmpty(cost)) {
                                    mcustom_gaodeinfo_window3.setText(cost);
                                }
                                ObjectAnimator.ofFloat(mcustom_gaodeinfo_windowlayout, "translationY", 0.0F, 500.0F)
                                        // 设置执行时间(1000ms)
                                        .setDuration(500)
                                        // 开始动画
                                        .start();
                            } else if (code.equals("R00003")) {
                                //用户有未付款项

                            } else if (code.equals("E00000")) {
                                //参数不能为空

                            } else if (code.equals("E00001")) {
                                //请求失败

                            }
                        } catch (JSONException e) {
                            LogUtil.d("捕捉到了请求小绿正在骑行的异常=", e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的数据为=", "请求小绿失败");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(getContext()).getRequestQueue().add(jsonRequest);
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            // 设置定位监听
            mlocationClient.setLocationListener(this);
            // 设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
            mLocationOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
            mLocationOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
            mLocationOption.setInterval(5000);//可选，设置定位间隔。默认为2秒
            //mLocationOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
            mLocationOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
            mLocationOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
            //  AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
            //   mLocationOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
            // mLocationOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
            mLocationOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true*/
            // 设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        gaodemapview1.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        gaodemapview1.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mlocationClient.stopLocation();//停止定位
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gaodemapview1.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                locallatitude = amapLocation.getLatitude();
                locallongitude = amapLocation.getLongitude();
                address = amapLocation.getAddress();
                LogUtil.d("自己写的定位成功latitude", locallatitude + "");
                LogUtil.d("自己写的定位成功longitude", locallongitude + "");
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                LogUtil.e("AmapErr", errText);
            }
        }
    }

    //位置发生改变的时候的方法
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        LatLng target = cameraPosition.target;
        double latitude = target.latitude;
        double longitude = target.longitude;
        centerLatLng = new LatLng(latitude, longitude);
        System.out.println("结束后、、" + latitude + "jinjin------" + longitude);
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.draggable(false);//设置Marker可拖动
        markerOption.setFlat(true);//设置marker平贴地图效果
   /*     if (null == centerMarker) {


            centerMarker = aMap.addMarker(markerOption);
        }
        centerMarker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.drawable.gaodemap_center_marker)));
        centerMarker.setPosition(centerLatLng);
        centerMarker.setVisible(true);*/
    }

    //位置发生改变的时候后。。。。的方法
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        centerImageView.startAnimation(centeranimation);
        LatLng target = cameraPosition.target;
        double latitude = target.latitude;
        double longitude = target.longitude;

    }


    //点击高德地图地图页面上消失上下弹窗
    @Override
    public void onMapClick(LatLng latLng) {
        mymarkflag = false;
        ObjectAnimator.ofFloat(mcustom_gaodeinfo_windowlayout, "translationY", 0.0F, -500.0F)
                // 设置执行时间(1000ms)
                .setDuration(100)
                // 开始动画
                .start();
    }

    //地图加载完成后
    @Override
    public void onMapLoaded() {
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locallatitude, locallongitude), 15));
    }

}
