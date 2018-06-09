package com.xdt.xudutong.huiminbao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.orhanobut.logger.Logger;
import com.xdt.xudutong.R;
import com.xdt.xudutong.rsa.RSAEncrypt;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/5/11.
 */

public class WeekDealFragment extends Fragment {
    @BindView(R.id.list_view)
    ListView mListView;
    @BindView(R.id.swipere_fresh_layout)
    SwipeRefreshLayout mSwipereFreshLayout;
    Unbinder unbinder;
    private String mCardNo;
    private int queryFlag = 1;
    private String mBeginDate;
    private String mEndDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week_deal, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //当前时间
        mBeginDate = format.format(new Date());
        //一周前时间
        c.setTime(new Date());
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        mEndDate = format.format(d);
        LogUtil.e("现在时间***:"+mBeginDate);
        LogUtil.e("一周前时间=====："+mEndDate);

//        cardNo	Y	客户卡号（联名卡）（RSA加密）
//        fundId		Y	基金代码（默认添益快线：000848）
//        beginDate	Y	开始日期
//        endDate	Y	截止日期下一页
//        queryRecordNum	Y	每次查询记录数，不能大于5
//        queryFlag	Y	查询标志1-首次查询 2-上一页 3-下一页
//        queryRecordNum	Y	每次查询记录数，不能大于5
        try {
            mCardNo = RSAEncrypt.encrypt(ICBC.TEST_NUM,RSAEncrypt.getPublicKey(ICBC.ICBC_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = ApiUrls.HUI_MIN_BAO_JI_JIN_MINGXI;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("fundId","000848");
        params.put("cardNo", mCardNo);
        params.put("beginDate",mBeginDate);
        params.put("endDate",mEndDate);
        params.put("queryFlag",queryFlag+"");
        params.put("queryRecordNum","5");

        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(response+"");
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            if (code1string.equals("R00001")) {
                            }else {
//                                ToastUtils.getInstance(FundsBalance.this).showMessage(response.get("desc").toString());
                            }
                        } catch (Exception e) {
                            LogUtil.e(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN-ERROR", error.getMessage(), error);
            }
        }) {
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
