package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.CityuserBindCard;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.xdt.xudutong.utils.EventMsg.ADDCARDSTATES;

/**
 * Created by Administrator on 2017\7\6 0006.
 */

public class PersoncenterAddCard extends BaseActivity {
    private EditText mperson_card_addcardeduittext3;
    private TextView mperson_card_addcardsubmit;
    private String token1;
    private String token2;
    private LinearLayout person_card_manageraddcardback1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_card_manager_addcard);
    }
    @Override
    public void initView() {
        person_card_manageraddcardback1 = (LinearLayout) findViewById(R.id.person_card_manageraddcardback);
        mperson_card_addcardeduittext3 = (EditText) findViewById(R.id.person_card_addcardeduittext3);
        mperson_card_addcardsubmit = (TextView) findViewById(R.id.person_card_addcardsubmit);
        initData();
    }
    private void initData() {
        person_card_manageraddcardback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        mperson_card_addcardsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String card = mperson_card_addcardeduittext3.getText().toString().toString();
                    if (!card.isEmpty()) {
                        if (card.length()==13){
                            ShowVolleyRequest(card);
                        }else{
                            ToastUtils.getInstance(PersoncenterAddCard.this).showMessage("请输入有效卡号");
                        }

                    } else {
                        ToastUtils.getInstance(PersoncenterAddCard.this).showMessage("请输入许都通卡号");
                    }
                }
            }
        });
    }

    //因许都通卡暂未确定，无法解析网络请求数据
    private void ShowVolleyRequest(String card) {
        String url = ApiUrls.USERBINDCARDS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("cityCardNo", card);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String code1string;

                    @Override
                    public void onResponse(JSONObject response) {

                        //CityuserBindCard
                        try {
                            Object code = response.get("code");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (code1string.equals("R00001")) {
                            EventBus.getDefault().post(new EventMsg(ADDCARDSTATES, true));
                            // 设置返回数据
                            Intent intent = new Intent();
                            // 返回intent
                            setResult(33, intent);
                            finish();
                        } else if (code1string.equals("E00003")) {
                            Gson gson = new Gson();
                            CityuserBindCard cityuserBindCard = gson.fromJson(response.toString(), CityuserBindCard.class);
                            String desc = cityuserBindCard.getDesc();
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PersoncenterAddCard.this, R.style.my_dialog);
                            final android.app.AlertDialog dialog = builder.create();
                            View view = LayoutInflater.from(PersoncenterAddCard.this).inflate(R.layout.person_adcardfail, null);  //通过LayoutInflater获取布局
                            LinearLayout person_fankui_dialog_cancel1 = (LinearLayout) view.findViewById(R.id.person_addcardfail_dialog_cancel);
                            TextView person_addcardfail_dialog_text11 = (TextView) view.findViewById(R.id.person_addcardfail_dialog_text1);
                            person_addcardfail_dialog_text11.setText(desc);
                            dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
                            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                            int widthPixels = displayMetrics.widthPixels;
                            int layout_width = (int) ((widthPixels) * 0.8);// 宽度
                            //  lp.height = (int) ((widthPixels) * 0.6);
                            int layout_height = WindowManager.LayoutParams.WRAP_CONTENT;// 高度
                            dialog.getWindow().setLayout(layout_width, layout_height); //对话框大小应根据屏幕大小调整

                            Window dialogWindow = dialog.getWindow();
                            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                            lp.x = 0; // 新位置X坐标
                            lp.y = -100; // 新位置Y坐标
                            dialogWindow.setAttributes(lp);
                            person_fankui_dialog_cancel1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        } else if (code1string.equals("E00002")) {
                            ToastUtils.getInstance(PersoncenterAddCard.this).showMessage("该卡已被绑定");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的数据为=", error.toString());
                ToastUtils.getInstance(PersoncenterAddCard.this).showMessage("系统繁忙，请稍后再试");
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
