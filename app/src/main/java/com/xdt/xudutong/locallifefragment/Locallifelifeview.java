package com.xdt.xudutong.locallifefragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.xdt.xudutong.R;
import com.xdt.xudutong.crashexception.AppManager;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.utils.WalkRouteCalculateActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\8\21 0021.
 */

public class Locallifelifeview extends AppCompatActivity implements
        AMap.OnMapClickListener, AMap.OnMarkerClickListener, AMap.OnMapLoadedListener,
        PoiSearch.OnPoiSearchListener, LocationSource,
        AMapLocationListener, AMap.OnCameraChangeListener {
    private AMapLocationClient mlocationClient;
    private OnLocationChangedListener mListener;
    private LinearLayout mlocallife_locallifelifeback;
    private MapView locallife_lifeviewmarview;
    private AMap aMap = null;
    private LatLng centerLatLng = null;
    private double locallatitude;
    private double locallongitude;
    private AMapLocationClientOption mLocationOption;
    private PoiSearch.Query query;// Poi查询条件类
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果
    private List<PoiItem> poiItems;// poi数据
    // 中心点marker
    private LatLonPoint latLng;
    private String mlocallife_locallifelife;
    private String mlocallife_locallifelife1;
    private PopupWindow popupWindow1;
    private PopupWindow popupWindow2;
    private LinearLayout lifearoundquerytop1;
    //定位地点的位置
    private String address;
    private LinearLayout mcustom_gaodeinfo_windowlayout;
    private LinearLayout mcustom_gaodeinfo_windowlayout2;
    private boolean mymarkflag;
    private TextView mcustom_gaodeinfo_window1;
    private TextView mcustom_gaodeinfo_window2;
    private TextView mcustom_gaodeinfo_window3;
    private android.view.animation.Animation centeranimation;
    private ImageView centerImageView;
    private LinearLayout mcustom_gaodeinfo_windowlayout1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locallife_locallifelife);
        locallife_lifeviewmarview = (MapView) findViewById(R.id.locallife_lifeview);
        locallife_lifeviewmarview.onCreate(savedInstanceState);
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
        mlocallife_locallifelife = intent.getStringExtra("locallife_locallifelife");
        mlocallife_locallifelife1 = intent.getStringExtra("locallife_locallifelife1");
        mlocallife_locallifelifeback = (LinearLayout) findViewById(R.id.locallife_locallifelifeback);
        centerImageView = (ImageView) findViewById(R.id.custom_gaodeinfo_centermark);
        mcustom_gaodeinfo_windowlayout = (LinearLayout) findViewById(R.id.custom_gaodeinfo_windowlayout);
        mcustom_gaodeinfo_windowlayout2 = (LinearLayout) findViewById(R.id.custom_gaodeinfo_windowlayout2);
        lifearoundquerytop1 = (LinearLayout) findViewById(R.id.lifearoundquerytop);
        TextView local_locallifelifeview1 = (TextView) findViewById(R.id.local_locallifelifeview);
        mcustom_gaodeinfo_window1 = (TextView) findViewById(R.id.custom_gaodeinfo_window1);
        mcustom_gaodeinfo_window2 = (TextView) findViewById(R.id.custom_gaodeinfo_window2);
        mcustom_gaodeinfo_window3 = (TextView) findViewById(R.id.custom_gaodeinfo_window3);
        mcustom_gaodeinfo_windowlayout1 = (LinearLayout) findViewById(R.id.custom_gaodeinfo_windowlayout1);
        centeranimation = AnimationUtils.loadAnimation(Locallifelifeview.this, R.anim.center_map_bounds);
        local_locallifelifeview1.setText(mlocallife_locallifelife);

        if (aMap == null) {
            aMap = locallife_lifeviewmarview.getMap();
        }
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locallatitude, locallongitude), 15));
        setUpMap();
        initData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        locallife_lifeviewmarview.onSaveInstanceState(outState);
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

    private void initData() {
        mlocallife_locallifelifeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置定位条件
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
            mLocationOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
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
/*        Showtopdatabotton(marker);
        Showtopdatatop(marker);*/
        //popwindow点击逻辑
        marker.setInfoWindowEnable(false);
        if (address != null && !address.isEmpty()) {
            mcustom_gaodeinfo_window1.setText(address);
        }
        if (mymarkflag == true) {
            if (marker.getObject() != null) {
                PoiItem PoiItemresult = (PoiItem) marker.getObject();
                mcustom_gaodeinfo_window2.setText(PoiItemresult.getTitle());
                mcustom_gaodeinfo_window3.setText(PoiItemresult.getSnippet() + PoiItemresult.getDistance());
            }
        } else {
            if (marker.getObject() != null) {
                PoiItem PoiItemresult = (PoiItem) marker.getObject();
                mcustom_gaodeinfo_window2.setText(PoiItemresult.getTitle());
                mcustom_gaodeinfo_window3.setText(PoiItemresult.getSnippet() + PoiItemresult.getDistance());
            }
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
                Intent intent = new Intent(Locallifelifeview.this, WalkRouteCalculateActivity.class);
                intent.putExtra("starlocallatitude", locallatitude);
                intent.putExtra("starlocallongitude", locallongitude);
                intent.putExtra("endlocallatitude", endlocallatitude);
                intent.putExtra("endlocallongitude", endlocallongitude);
                intent.putExtra("walkguideview1",mlocallife_locallifelife);
                startActivity(intent);
            }
        });
        mymarkflag = true;
        return false;
    }
    @Override
    public void onMapLoaded() {
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locallatitude, locallongitude), 15));
    }
    @Override
    public void onPoiSearched(PoiResult result, int rcode) {
        if (rcode == AMapException.CODE_AMAP_SUCCESS) {
            removeMarkers();
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    if (poiItems != null && poiItems.size() > 0) {
                        ArrayList<Marker> mPoiMarks = new ArrayList<Marker>();
                        for (int i = 0; i < poiItems.size(); i++) {
                            Marker marker = aMap.addMarker(getMarkerOptions(i));
                            PoiItem item = poiItems.get(i);
                            LatLonPoint latLonPoint = item.getLatLonPoint();
                            LatLng latLng = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
                            marker.setObject(item);
                            marker.setPosition(latLng);
                            mPoiMarks.add(marker);
                            growInto(marker);
                        }

                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                        showSuggestCity(suggestionCities);
                    } else {
                        //  Toast.makeText(Locallifelifeview.this, "对不起，没有搜索到相关数据！",Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                ToastUtils.getInstance(Locallifelifeview.this).showMessage("对不起，没有搜索到相关数据！");
            }
        } else {
            ToastUtils.getInstance(Locallifelifeview.this).showMessage(rcode);
        }
    }

    private MarkerOptions getMarkerOptions(int index) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.icon_wangdian));
        return new MarkerOptions().position(new LatLng(poiItems.get(index).getLatLonPoint()
                .getLatitude(), poiItems.get(index)
                .getLatLonPoint().getLongitude())).icon(icon);
    }


    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        ToastUtils.getInstance(Locallifelifeview.this).showMessage(infomation);
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }



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
        locallife_lifeviewmarview.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        locallife_lifeviewmarview.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
        AppManager.getInstance().removeActivity(this);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        locallife_lifeviewmarview.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mlocationClient.stopLocation();//停止定位
    }
    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                Log.i("自己写的定位成功", "自己写的定位成功");
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                locallatitude = amapLocation.getLatitude();
                locallongitude = amapLocation.getLongitude();
                address = amapLocation.getAddress();
                Log.i("自己写的定位成功latitude", locallatitude + "");
                Log.i("自己写的定位成功longitude", locallongitude + "");
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
                Log.i("自己写的定位失败", "自己写的定位失败");
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
        System.out.println("结束后、、" + latitude + "jinjin------" + longitude);
        currentPage = 0;
        query = new PoiSearch.Query(mlocallife_locallifelife, null, "许昌");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        LatLonPoint latLng = new LatLonPoint(latitude, longitude);
        if (latLng != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(latLng, 5000, true));//
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }



   /* //底部
    private void Showtopdatatop(final Marker marker) {

        Log.i("locallatitudelocallat44", locallatitude + "");
        Log.i("locallongitude44====", locallongitude + "");
        // 一个自定义的布局，作为显示的内容
        int screenWidth = (int) (getWindowManager().getDefaultDisplay().getWidth() * 0.9); // 屏幕宽（像素，如：480px）
        int screenHeight = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.09); // 屏幕高（像素，如：800p）
        final View contentView1 = LayoutInflater.from(Locallifelifeview.this).inflate(
                R.layout.custom_gaodeinfo_window2, null);
        popupWindow1 = new PopupWindow(contentView1,
                screenWidth, screenHeight, true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow1.setAnimationStyle(R.style.AnimationFade2);
        popupWindow1.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow1.setTouchable(true);
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.showAtLocation(lifearoundquerytop1, Gravity.BOTTOM, 10, 15);
        contentView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng position = marker.getPosition();
                double endlocallatitude = position.latitude;
                double endlocallongitude = position.longitude;
                Intent intent = new Intent(Locallifelifeview.this, WalkRouteCalculateActivity.class);
                intent.putExtra("starlocallatitude", locallatitude);
                intent.putExtra("starlocallongitude", locallongitude);
                intent.putExtra("endlocallatitude", endlocallatitude);
                intent.putExtra("endlocallongitude", endlocallongitude);
                intent.putExtra("walkguideview1",mlocallife_locallifelife);
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
        TextView custom_gaodeinfo_window1 = (TextView) contentView1.findViewById(R.id.custom_gaodeinfo_window1);
        TextView mcustom_gaodeinfo_window2 = (TextView) contentView1.findViewById(R.id.custom_gaodeinfo_window2);
        TextView mcustom_gaodeinfo_window3 = (TextView) contentView1.findViewById(R.id.custom_gaodeinfo_window3);

        if (marker.getObject() != null) {
            PoiItem PoiItemresult = (PoiItem) marker.getObject();
            mcustom_gaodeinfo_window2.setText(PoiItemresult.getTitle());
            mcustom_gaodeinfo_window3.setText(PoiItemresult.getSnippet() + PoiItemresult.getDistance());
        }

    }

    //头部
    private void Showtopdatabotton(Marker marker) {
        int screenWidth = (int) (getWindowManager().getDefaultDisplay().getWidth() * 0.9); // 屏幕宽（像素，如：480px）
        int screenHeight = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.08); // 屏幕高（像素，如：800p）
        // 一个自定义的布局，作为显示的内容
        View contentView2 = LayoutInflater.from(Locallifelifeview.this).inflate(
                R.layout.custom_gaodeinfo_window, null);
        popupWindow2 = new PopupWindow(contentView2,
                screenWidth, screenHeight, true);
        TextView mcustom_gaodeinfo_window2 = (TextView) contentView2.findViewById(R.id.custom_gaodeinfo_window2);
        if (!TextUtils.isEmpty(address)) {
            mcustom_gaodeinfo_window2.setText(address);
        }
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow2.setAnimationStyle(R.style.AnimationFade);
        popupWindow2.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow2.setTouchable(true);
        // 设置好参数之后再show
        popupWindow2.showAsDropDown(lifearoundquerytop1, 30, 15, Gravity.CENTER_HORIZONTAL);
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.i("mengdddddddddddddd", "onTouch : ");
                popupWindow1.dismiss();
            }
        });
    }*/
    /**
     * 从地上生长效果，实现思路
     * 在较短的时间内，修改marker的图标大小，从而实现动画
     * 1.保存原始的图片；
     * 2.在原始图片上缩放得到新的图片，并设置给marker；
     * 3.回收上一张缩放后的图片资源；
     * 4.重复2，3步骤到时间结束；
     * 5.回收上一张缩放后的图片资源，设置marker的图标为最原始的图片；
     *
     * 其中时间变化由AccelerateInterpolator控制
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


                if (t < 1.0 ) {
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
    private void removeMarkers() {//mList
        aMap.clear();
        locallife_lifeviewmarview.invalidate();//刷新地图
    }
}
