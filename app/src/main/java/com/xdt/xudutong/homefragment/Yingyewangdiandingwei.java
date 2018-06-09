package com.xdt.xudutong.homefragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
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
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.animation.Animation;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.BranchgetBranchlist;
import com.xdt.xudutong.crashexception.AppManager;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.WalkRouteCalculateActivity;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.xudutong.Xdtyingyewangdian;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\9\4 0004.
 */

public class Yingyewangdiandingwei extends AppCompatActivity implements AMap.OnMapClickListener, LocationSource,
        AMapLocationListener, AMap.OnCameraChangeListener
        , AMap.OnMarkerClickListener, AMap.OnMapLoadedListener, Animation.AnimationListener {

    private AMapLocationClient mlocationClient;
    // 中心点坐标
    private LatLng centerLatLng = null;
    private Marker marker;
    private LinearLayout mxiaolvtop;
    private OnLocationChangedListener mListener;
    private AMapLocationClientOption mLocationOption;
    //定位的经纬度
    private String address;
    private double locallatitude;
    private double locallongitude;
    private MapView gaodemapview1;
    private AMap aMap = null;
    // 中心点marker
    private Marker centerMarker;
    private boolean mymarkflag;

    private PopupWindow popupWindow1;
    private PopupWindow popupWindow2;
    private String receiveyingyewangdainallbranchLat1;
    private String receiveyingyewangdainallbranchLng1;
    private ImageView centerImageView;
    private TextView mcustom_gaodeinfo_window1;
    private TextView mcustom_gaodeinfo_window2;
    private TextView mcustom_gaodeinfo_window3;
    private android.view.animation.Animation centeranimation;
    private LinearLayout mcustom_gaodeinfo_windowlayout;
    private LinearLayout mcustom_gaodeinfo_windowlayout2;
    private LinearLayout mcustom_gaodeinfo_windowlayout1;
    private TextView mhome_xiaolvbikejiluquerynext1;
    private TextView mhome_xiaolvbikejilutoptext1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_cardgroup_button7);
        AppManager.getInstance().addActivity(this);
        gaodemapview1 = (MapView) findViewById(R.id.gaodemapview);
        gaodemapview1.onCreate(savedInstanceState);
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
        centerImageView = (ImageView) findViewById(R.id.custom_gaodeinfo_centermark);
        LinearLayout mhomebuttongroup_xiaolvback = (LinearLayout) findViewById(R.id.homebuttongroup_xiaolvback);
        mcustom_gaodeinfo_window1 = (TextView) findViewById(R.id.custom_gaodeinfo_window1);
        mcustom_gaodeinfo_window2 = (TextView) findViewById(R.id.custom_gaodeinfo_window2);
        mcustom_gaodeinfo_window3 = (TextView) findViewById(R.id.custom_gaodeinfo_window3);
        mcustom_gaodeinfo_windowlayout = (LinearLayout) findViewById(R.id.custom_gaodeinfo_windowlayout);
        mcustom_gaodeinfo_windowlayout2 = (LinearLayout) findViewById(R.id.custom_gaodeinfo_windowlayout2);
        mcustom_gaodeinfo_windowlayout1 = (LinearLayout) findViewById(R.id.custom_gaodeinfo_windowlayout1);
        mhome_xiaolvbikejilutoptext1 = (TextView) findViewById(R.id.home_xiaolvbikejilutoptext);
        mhome_xiaolvbikejiluquerynext1 = (TextView) findViewById(R.id.home_xiaolvbikejiluquerynext);
        mxiaolvtop = (LinearLayout) findViewById(R.id.xiaolvtop);
        mhome_xiaolvbikejiluquerynext1.setVisibility(View.VISIBLE);
        mhome_xiaolvbikejiluquerynext1.setText("全部网点");
        mhome_xiaolvbikejilutoptext1.setText("业务网点");
        centeranimation = AnimationUtils.loadAnimation(Yingyewangdiandingwei.this, R.anim.center_map_bounds);

        mhomebuttongroup_xiaolvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        if (aMap == null) {
            aMap = gaodemapview1.getMap();
        }
        mhome_xiaolvbikejiluquerynext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Yingyewangdiandingwei.this, Xdtyingyewangdian.class);
                intent.putExtra("yingyewangdianlatitudenow", locallatitude);
                intent.putExtra("yingyewangdianlongitudenow", locallongitude);
                LogUtil.d("locallatitudelocallatit", locallatitude + "");
                LogUtil.d("locallongitudelocallon", locallongitude + "");
                intent.putExtra("yingyewangdianlocalsplace", address);
                startActivityForResult(intent, 13);
            }
        });

        aMap.getUiSettings().setRotateGesturesEnabled(false);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locallatitude, locallongitude), 15));
        setUpMap();
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
    protected void onDestroy() {
        super.onDestroy();
        gaodemapview1.onDestroy();
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

    private void setUpMap() {
        //取消右下角的缩放
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
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


    //请求网络传入经纬度
    private void ShowVolleyRequestforxiaolv(final Double s, final Double s1) {
        String latitude = String.valueOf(s);
        String longitude = String.valueOf(s1);
        String urltruename = ApiUrls.GETBRANCHLIST;
        Map<String, String> params = new HashMap<>();
        params.put("branchLng", longitude);
        params.put("branchLat", latitude);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        BranchgetBranchlist branchgetbranchlist = gson.fromJson(response.toString(), BranchgetBranchlist.class);
                        String code = branchgetbranchlist.getCode();
                        if (code.equals("R00001")) {
                            List<BranchgetBranchlist.ContentBean.DataBean> branchgetbranchlistdata = branchgetbranchlist.getContent().getData();
                            MarkerOptions markerOption = new MarkerOptions();
                            markerOption.draggable(false);//设置Marker可拖动
                            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(), R.drawable.icon_wangdian)));
                            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                            markerOption.setFlat(true);//设置marker平贴地图效果
                            ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
                            removeMarkers(branchgetbranchlistdata);
                            //获取标记遍历在地图上
                            for (int i = 0; i < branchgetbranchlistdata.size(); i++) {
                                //获取经纬度
                                String branchLat = branchgetbranchlistdata.get(i).getBranchLat();
                                String branchLng = branchgetbranchlistdata.get(i).getBranchLng();
                                String branchAddress = branchgetbranchlistdata.get(i).getBranchAddress();
                                Double lat = Double.valueOf(branchLat);
                                Double lng = Double.valueOf(branchLng);
                                double[] doubles = bdToGaoDe(lat, lng);
                                double latturn = doubles[0];
                                double lngturn = doubles[1];
                                LatLng latLng = new LatLng(lngturn, latturn);
                                marker = aMap.addMarker(markerOption);
                                marker.setTitle(branchgetbranchlistdata.get(i).getBranchAddress());
                                marker.setSnippet(branchgetbranchlistdata.get(i).getBranchName() + "");
                                marker.setPosition(latLng);
                                markerOptionlst.add(markerOption);
                                growInto(marker);
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    private void removeMarkers(List<BranchgetBranchlist.ContentBean.DataBean> branchgetbranchlistdata) {
        aMap.clear();
        gaodemapview1.invalidate();//刷新地图
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
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        centerImageView.startAnimation(centeranimation);

        LatLng target = cameraPosition.target;
        double latitude = target.latitude;
        double longitude = target.longitude;
        ShowVolleyRequestforxiaolv(latitude, longitude);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mymarkflag = false;
        ObjectAnimator.ofFloat(mcustom_gaodeinfo_windowlayout, "translationY", 0.0F, -200.0F)
                // 设置执行时间(1000ms)
                .setDuration(100)
                // 开始动画
                .start();
        ObjectAnimator.ofFloat(mcustom_gaodeinfo_windowlayout2, "translationY", 0.0F, 200.0F)
                // 设置执行时间(1000ms)
                .setDuration(100)
                // 开始动画
                .start();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        marker.setInfoWindowEnable(false);


        mcustom_gaodeinfo_windowlayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("111111111111", "11111111111111");
                LatLng position = marker.getPosition();
                double endlocallatitude = position.latitude;
                double endlocallongitude = position.longitude;
                Intent intent = new Intent(Yingyewangdiandingwei.this, WalkRouteCalculateActivity.class);
                intent.putExtra("starlocallatitude", locallatitude);
                intent.putExtra("starlocallongitude", locallongitude);
                intent.putExtra("endlocallatitude", endlocallatitude);
                intent.putExtra("endlocallongitude", endlocallongitude);
                intent.putExtra("walkguideview1", "激活网点导航");
                startActivity(intent);
            }
        });


        //popwindow点击逻辑
        if (address != null && !address.isEmpty()) {
            mcustom_gaodeinfo_window1.setText(address);
        }
        if (mymarkflag == true) {
            mcustom_gaodeinfo_window2.setText(marker.getTitle());
            mcustom_gaodeinfo_window3.setText(marker.getSnippet());
        } else {
            mcustom_gaodeinfo_window2.setText(marker.getTitle());
            mcustom_gaodeinfo_window3.setText(marker.getSnippet());
            ObjectAnimator.ofFloat(mcustom_gaodeinfo_windowlayout, "translationY", 0.0F, 200.0F)
                    // 设置执行时间(1000ms)
                    .setDuration(500)
                    // 开始动画
                    .start();
            ObjectAnimator.ofFloat(mcustom_gaodeinfo_windowlayout2, "translationY", 0.0F, -200.0F)
                    // 设置执行时间(1000ms)
                    .setDuration(500)
                    // 开始动画
                    .start();
        }
        //如果标签等于ture,说明点击了mark,弹出框显示状态
        mymarkflag = true;
        return false;
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

    @Override
    public void deactivate() {
        super.onDestroy();
        gaodemapview1.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }

    }


    //底部
    private void Showtopdatatop(final Marker marker) {

        Log.i("locallatitudelocallat44", locallatitude + "");
        Log.i("locallongitude44====", locallongitude + "");
        // 一个自定义的布局，作为显示的内容
        int screenWidth = (int) (getWindowManager().getDefaultDisplay().getWidth() * 0.9); // 屏幕宽（像素，如：480px）
        int screenHeight = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.09); // 屏幕高（像素，如：800p）
        final View contentView1 = LayoutInflater.from(Yingyewangdiandingwei.this).inflate(
                R.layout.custom_gaodeinfo_window2, null);
        popupWindow1 = new PopupWindow(contentView1,
                screenWidth, screenHeight, true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow1.setAnimationStyle(R.style.AnimationFade2);
        popupWindow1.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow1.setTouchable(true);
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.showAtLocation(mxiaolvtop, Gravity.BOTTOM, 10, 15);
        contentView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng position = marker.getPosition();
                double endlocallatitude = position.latitude;
                double endlocallongitude = position.longitude;
                Intent intent = new Intent(Yingyewangdiandingwei.this, WalkRouteCalculateActivity.class);
                String dingweilocallatitude = intent.getStringExtra("dingweilocallatitude");
                String dingweilocallongitude = intent.getStringExtra("dingweilocallongitude");
                if (!TextUtils.isEmpty(receiveyingyewangdainallbranchLat1) && !TextUtils.isEmpty(receiveyingyewangdainallbranchLng1)) {
                    //传过来的有值，不进行定位
                    intent.putExtra("starlocallatitude", dingweilocallatitude);
                    intent.putExtra("starlocallongitude", dingweilocallongitude);
                    Log.i("locallatitudelocallat22", dingweilocallatitude + "");
                    Log.i("locallongitude22====", dingweilocallongitude + "");
                }
                if (locallatitude != 0.0 && locallongitude != 0.0) {
                    intent.putExtra("starlocallatitude", locallatitude);
                    intent.putExtra("starlocallongitude", locallongitude);
                    Log.i("locallatitudelocallat33", locallatitude + "");
                    Log.i("locallongitude33====", locallongitude + "");
                }
                intent.putExtra("endlocallatitude", endlocallatitude);
                intent.putExtra("endlocallongitude", endlocallongitude);
                intent.putExtra("walkguideview1", "激活网点导航");
                startActivity(intent);
            }
        });
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.i("mengdddddddddddddd", "onTouch : ");
                popupWindow2.dismiss();
            }
        });
        TextView mcustom_gaodeinfo_window2 = (TextView) contentView1.findViewById(R.id.custom_gaodeinfo_window2);
        TextView mcustom_gaodeinfo_window3 = (TextView) contentView1.findViewById(R.id.custom_gaodeinfo_window3);
        mcustom_gaodeinfo_window2.setText(marker.getTitle());
        mcustom_gaodeinfo_window3.setText(marker.getSnippet());
    }

    //头部
    private void Showtopdatabotton(Marker marker) {
        int screenWidth = (int) (getWindowManager().getDefaultDisplay().getWidth() * 0.9); // 屏幕宽（像素，如：480px）
        int screenHeight = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.08); // 屏幕高（像素，如：800p）
        // 一个自定义的布局，作为显示的内容
        View contentView2 = LayoutInflater.from(Yingyewangdiandingwei.this).inflate(
                R.layout.custom_gaodeinfo_window, null);
        popupWindow2 = new PopupWindow(contentView2,
                screenWidth, screenHeight, true);
        TextView mcustom_gaodeinfo_window2 = (TextView) contentView2.findViewById(R.id.custom_gaodeinfo_window2);
        Intent intent = getIntent();
        String yingyewangdianlocalsplace = intent.getStringExtra("yingyewangdianlocalsplace");

        if (address != null && !address.isEmpty()) {
            mcustom_gaodeinfo_window2.setText(address);
        }
        if (!TextUtils.isEmpty(yingyewangdianlocalsplace)) {
            mcustom_gaodeinfo_window2.setText(yingyewangdianlocalsplace);
        }
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow2.setAnimationStyle(R.style.AnimationFade);
        popupWindow2.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow2.setTouchable(true);
        // 设置好参数之后再show
        popupWindow2.showAsDropDown(mxiaolvtop, 30, 15, Gravity.CENTER_HORIZONTAL);
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.i("mengdddddddddddddd", "onTouch : ");
                popupWindow1.dismiss();
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("requestCoderequestCode", requestCode + "");
        Log.i("resultCoderesultCode", resultCode + "");
        if (requestCode == 13) {
            if (resultCode == 15) {
                //全部网点按钮消失
                mhome_xiaolvbikejiluquerynext1.setVisibility(View.GONE);
                LogUtil.d("收到参数===", "收到参数" + "");
                //收到之后
                //接受的营业网点的单个地址
                receiveyingyewangdainallbranchLat1 = data.getStringExtra("yingyewangdainallbranchLat1");
                receiveyingyewangdainallbranchLng1 = data.getStringExtra("yingyewangdainallbranchLng1");
                //接收到的定位
               /* String receivedingweilocallatitude = data.getStringExtra("dingweilocallatitude");
                String receivedingweilocallongitude = data.getStringExtra("dingweilocallongitude");*/
                //收到的传过来的不为空，不要定位，把当前点放到地图中间
                Double aDoublelat = Double.valueOf(receiveyingyewangdainallbranchLat1);
                Double aDoublelng = Double.valueOf(receiveyingyewangdainallbranchLng1);
                LogUtil.d("接收到的网点信息11=", receiveyingyewangdainallbranchLat1 + "");
                LogUtil.d("接收到的网点信息22=", receiveyingyewangdainallbranchLng1 + "");
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aDoublelng, aDoublelat), 15));
            }
        }
    }

    @Override
    public void onMapLoaded() {
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locallatitude, locallongitude), 15));
    }

    @Override
    public void onAnimationStart() {

    }

    @Override
    public void onAnimationEnd() {

    }

    /**
     * 从地上生长效果，实现思路
     * 在较短的时间内，修改marker的图标大小，从而实现动画
     * 1.保存原始的图片；
     * 2.在原始图片上缩放得到新的图片，并设置给marker；
     * 3.回收上一张缩放后的图片资源；
     * 4.重复2，3步骤到时间结束；
     * 5.回收上一张缩放后的图片资源，设置marker的图标为最原始的图片；
     * <p>
     * 其中时间变化由AccelerateInterpolator控制
     *
     * @param marker
     */
    private void growInto(final Marker marker) {
        marker.setVisible(false);
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final long duration = 500;// 动画总时长
        final Bitmap bitMap = marker.getIcons().get(0).getBitmap();// BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        final int width = bitMap.getWidth();
        final int height = bitMap.getHeight();

        final Interpolator interpolator = new AccelerateInterpolator();
        handler.post(new Runnable() {

            private Bitmap lastMarkerBitMap;

            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);

                if (t > 1) {
                    t = 1;
                }

                // 计算缩放比例
                int scaleWidth = (int) (t * width);
                int scaleHeight = (int) (t * height);
                if (scaleWidth > 0 && scaleHeight > 0) {

                    // 使用最原始的图片进行大小计算
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(Bitmap
                            .createScaledBitmap(bitMap, scaleWidth,
                                    scaleHeight, true)));
                    marker.setVisible(true);

                    // 因为替换了新的图片，所以把旧的图片销毁掉，注意在设置新的图片之后再销毁
                    //这句不能用，用了容易滑动多次，不是因为oom莫名报错
                 /*   if (lastMarkerBitMap != null && !lastMarkerBitMap.isRecycled()) {
                        lastMarkerBitMap.recycle();
                    }*/

                    //第一次得到的缩放图片，在第二次回收，最后一次的缩放图片，在动画结束时回收
                    ArrayList<BitmapDescriptor> list = marker.getIcons();
                    if (list != null && list.size() > 0) {
                        // 保存旧的图片
                        lastMarkerBitMap = marker.getIcons().get(0).getBitmap();
                    }

                }


                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                } else {
                    // 动画结束回收缩放图片，并还原最原始的图片
                    if (lastMarkerBitMap != null && !lastMarkerBitMap.isRecycled()) {
                        lastMarkerBitMap.recycle();
                    }
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitMap));
                    marker.setVisible(true);
                }
            }
        });
    }
}

