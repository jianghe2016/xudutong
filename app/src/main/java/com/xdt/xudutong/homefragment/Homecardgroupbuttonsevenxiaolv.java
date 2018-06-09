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
import com.xdt.xudutong.bean.LittlegreengetLittleGreen;
import com.xdt.xudutong.crashexception.AppManager;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.WalkRouteCalculateActivity;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.waituse.LittlegreenbikeRecord;
import com.xdt.xudutong.waituse.Littlegreenrecordcomein;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xdt.xudutong.utils.ApplicationController.getContext;
import static com.xdt.xudutong.utils.Finaltext.Personssecretstates;

/**
 * Created by Administrator on 2017\8\4 0004.
 */
//平移动画，-向上，+向下
public class Homecardgroupbuttonsevenxiaolv extends AppCompatActivity implements LocationSource,
        AMapLocationListener, AMap.OnCameraChangeListener
        , AMap.OnMarkerClickListener, AMap.OnMapClickListener, AMap.OnMapLoadedListener, Animation.AnimationListener {
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
    private LinearLayout mcustom_gaodeinfo_windowlayout2;
    private TextView mcustom_gaodeinfo_window1;
    private TextView mcustom_gaodeinfo_window2;
    private TextView mcustom_gaodeinfo_window3;
    private boolean mymarkflag;
    private boolean isFirst = true;
    private ImageView centerImageView;
    private android.view.animation.Animation centeranimation;
    private LinearLayout mcustom_gaodeinfo_windowlayout1;
    private TextView mhome_xiaolvbikejiluquerynext;

    // 当前的坐标点集合，主要用于进行地图的可视区域的缩放
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_cardgroup_button7);
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
        mhomebuttongroup_xiaolvback = (LinearLayout) findViewById(R.id.homebuttongroup_xiaolvback);
        centerImageView = (ImageView) findViewById(R.id.custom_gaodeinfo_centermark);
        mxiaolvtop = (LinearLayout) findViewById(R.id.xiaolvtop);
        mcustom_gaodeinfo_windowlayout = (LinearLayout) findViewById(R.id.custom_gaodeinfo_windowlayout);
        mcustom_gaodeinfo_windowlayout2 = (LinearLayout) findViewById(R.id.custom_gaodeinfo_windowlayout2);
        mcustom_gaodeinfo_window1 = (TextView) findViewById(R.id.custom_gaodeinfo_window1);
        mcustom_gaodeinfo_window2 = (TextView) findViewById(R.id.custom_gaodeinfo_window2);
        mcustom_gaodeinfo_window3 = (TextView) findViewById(R.id.custom_gaodeinfo_window3);
        mhome_xiaolvbikejiluquerynext = (TextView) findViewById(R.id.home_xiaolvbikejiluquerynext);
        mhome_xiaolvbikejiluquerynext.setText("骑行记录");
        mhome_xiaolvbikejiluquerynext.setVisibility(View.VISIBLE);
        mcustom_gaodeinfo_windowlayout1 = (LinearLayout) findViewById(R.id.custom_gaodeinfo_windowlayout1);
        centeranimation = AnimationUtils.loadAnimation(Homecardgroupbuttonsevenxiaolv.this, R.anim.center_map_bounds);

        if (aMap == null) {
            aMap = gaodemapview1.getMap();
        }
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        //aMap.moveCamera(CameraUpdateFactory.zoomBy(16));
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locallatitude, locallongitude), 15));
        setUpMap();
        initData();
    }


    private void initData() {
        Intent intent = getIntent();
        final int muserid = intent.getIntExtra("userid", 0);
        final boolean mpersonssecretstates = SpUtils.getParam(getApplicationContext(), Personssecretstates, false);
        final String xdtcardNo = intent.getStringExtra("xdtcardNo");
        // final String volleygetidcardNo = intent.getStringExtra("volleygetidcardNo");
        mhome_xiaolvbikejiluquerynext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     Intent intent = new Intent(Homecardgroupbuttonsevenxiaolv.this, Homexiaolvbikejilu.class);
                intent.putExtra("real", real);
                startActivity(intent);*/
                //如果开启免密状态下
                if (mpersonssecretstates == true) {
                    Intent intent = new Intent(Homecardgroupbuttonsevenxiaolv.this, LittlegreenbikeRecord.class);
                    intent.putExtra("xdtcardnumber", xdtcardNo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Homecardgroupbuttonsevenxiaolv.this, Littlegreenrecordcomein.class);
                    intent.putExtra("userid", muserid);
                    startActivity(intent);
                }

            }
        });
        mhomebuttongroup_xiaolvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

        final String latitude = String.valueOf(s);
        final String longitude = String.valueOf(s1);
        String urltruename = ApiUrls.GETGREENLIST;
        Map<String, String> params = new HashMap<>();
        params.put("lat", latitude);
        params.put("lng", longitude);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        LittlegreengetLittleGreen littlegreengetlittlegreen = gson.fromJson(response.toString(), LittlegreengetLittleGreen.class);
                        String code = littlegreengetlittlegreen.getCode();
                        if (code.equals("R00001")) {
                            List<LittlegreengetLittleGreen.ContentBean.DataBean> littlegreengetlittlegreendata = littlegreengetlittlegreen.getContent().getData();
                            MarkerOptions markerOption = new MarkerOptions();
                            markerOption.draggable(false);//设置Marker可拖动
                            markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(getResources(), R.drawable.icon_marka)));
                            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                            markerOption.setFlat(true);//设置marker平贴地图效果
                            ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
                            removeMarkers(littlegreengetlittlegreendata);
                            for (int i = 0; i < littlegreengetlittlegreendata.size(); i++) {
                                //获取经纬度
                                double lat = littlegreengetlittlegreendata.get(i).getLat();
                                double lng = littlegreengetlittlegreendata.get(i).getLng();
                                LatLng latLng = new LatLng(lat, lng);
                                marker = aMap.addMarker(markerOption);
                                marker.setPosition(latLng);
                                marker.setTitle(littlegreengetlittlegreendata.get(i).getAddress());
                                marker.setSnippet(littlegreengetlittlegreendata.get(i).getAvailBike() + "");
                                // marker.setObject(littlegreengetlittlegreendata.get(i).getAddress());//这里可以存储用户数据
                                markerOptionlst.add(markerOption);
                                growInto(marker);
                            }
                            // mList = aMap.addMarkers(markerOptionlst, true);

                        } else {
                            String desc = littlegreengetlittlegreen.getDesc();
                            LogUtil.d("请求的数据为=", desc);
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
        ShowVolleyRequestforxiaolv(latitude, longitude);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        marker.setInfoWindowEnable(false);
        LogUtil.d("点击了mark", "点击了mark");
        //popwindow点击逻辑
        if (address != null && !address.isEmpty()) {
            mcustom_gaodeinfo_window1.setText(address);
        }
        if (mymarkflag == true) {
            mcustom_gaodeinfo_window2.setText(marker.getTitle());
            mcustom_gaodeinfo_window3.setText("剩余" + marker.getSnippet() + "辆");
        } else {
            mcustom_gaodeinfo_window2.setText(marker.getTitle());
            mcustom_gaodeinfo_window3.setText("剩余" + marker.getSnippet() + "辆");
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

        mcustom_gaodeinfo_windowlayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng position = marker.getPosition();
                double endlocallatitude = position.latitude;
                double endlocallongitude = position.longitude;
                Intent intent = new Intent(Homecardgroupbuttonsevenxiaolv.this, WalkRouteCalculateActivity.class);
                intent.putExtra("starlocallatitude", locallatitude);
                intent.putExtra("starlocallongitude", locallongitude);
                intent.putExtra("endlocallatitude", endlocallatitude);
                intent.putExtra("endlocallongitude", endlocallongitude);
                intent.putExtra("walkguideview1", "小绿导航");
                startActivity(intent);
            }
        });
        //如果标签等于ture,说明点击了mark,弹出框显示状态
        mymarkflag = true;
        return false;

    }


    //底部
    private void Showtopdatatop(final Marker marker) {
        // 一个自定义的布局，作为显示的内容
        int screenWidth = (int) (getWindowManager().getDefaultDisplay().getWidth() * 0.9); // 屏幕宽（像素，如：480px）
        int screenHeight = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.09); // 屏幕高（像素，如：800p）
        View contentView1 = LayoutInflater.from(Homecardgroupbuttonsevenxiaolv.this).inflate(
                R.layout.custom_gaodeinfo_window2, null);
        PopupWindow popupWindow1 = new PopupWindow(contentView1,
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
                Intent intent = new Intent(Homecardgroupbuttonsevenxiaolv.this, WalkRouteCalculateActivity.class);
                intent.putExtra("starlocallatitude", locallatitude);
                intent.putExtra("starlocallongitude", locallongitude);
                intent.putExtra("endlocallatitude", endlocallatitude);
                intent.putExtra("endlocallongitude", endlocallongitude);
                intent.putExtra("walkguideview1", "小绿导航");
                startActivity(intent);
            }
        });
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                LogUtil.d("mengdddddddddddddd", "onTouch : ");
                popupWindow2.dismiss();
            }
        });
        // ImageView custom_gaodeinfo_window1 = (ImageView) contentView1.findViewById(R.id.custom_gaodeinfo_window1);
        TextView mcustom_gaodeinfo_window2 = (TextView) contentView1.findViewById(R.id.custom_gaodeinfo_window2);
        TextView mcustom_gaodeinfo_window3 = (TextView) contentView1.findViewById(R.id.custom_gaodeinfo_window3);
        mcustom_gaodeinfo_window2.setText(marker.getTitle());
        mcustom_gaodeinfo_window3.setText("剩余" + marker.getSnippet() + "辆");
    }

    //头部
    private void Showtopdatabotton(Marker marker) {
        int screenWidth = (int) (getWindowManager().getDefaultDisplay().getWidth() * 0.9); // 屏幕宽（像素，如：480px）
        int screenHeight = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.08); // 屏幕高（像素，如：800p）
        // 一个自定义的布局，作为显示的内容
        View contentView2 = LayoutInflater.from(Homecardgroupbuttonsevenxiaolv.this).inflate(
                R.layout.custom_gaodeinfo_window, null);
        popupWindow2 = new PopupWindow(contentView2,
                screenWidth, screenHeight, true);
        TextView mcustom_gaodeinfo_window2 = (TextView) contentView2.findViewById(R.id.custom_gaodeinfo_window2);
        if (address != null && !address.isEmpty()) {
            mcustom_gaodeinfo_window2.setText(address);
        }
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow2.setAnimationStyle(R.style.AnimationFade);
        popupWindow2.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow2.setTouchable(true);
        // 设置好参数之后再show
        popupWindow2.showAsDropDown(mxiaolvtop, 30, 15, Gravity.CENTER_HORIZONTAL);
    }

    private void removeMarkers(List<LittlegreengetLittleGreen.ContentBean.DataBean> littlegreengetlittlegreendata) {//mList
        aMap.clear();
        gaodemapview1.invalidate();//刷新地图
    }

    //点击高德地图地图页面上消失上下弹窗
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

    //地图加载完成后
    @Override
    public void onMapLoaded() {

        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locallatitude, locallongitude), 15));
    }

    //高德地图中间小东西的动画
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
