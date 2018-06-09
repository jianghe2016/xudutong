package com.xdt.xudutong.benefitthepeople;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.RSAutilsmy;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018\2\2 0002.
 */
//充值页面
public class Benefitthepeoplererechargeforcard extends BaseActivity {
    private String rsaopenkey = RSAutilsmy.RSAOPENKEY;

    @Override
    public void initView() {
        final PublicKey publicKey = RSAutilsmy.keyStrToPublicKey(rsaopenkey);
        LinearLayout mbenefitthepeoplererechargeforcardback = (LinearLayout) findViewById(R.id.benefitthepeoplererechargeforcardback);
        LinearLayout mbenefitthepeoplererechargeforcardselectcard = (LinearLayout) findViewById(R.id.benefitthepeoplererechargeforcardselectcard);
        Button mbenefitthepeoplererechargeforcardsubmit = (Button) findViewById(R.id.benefitthepeoplererechargeforcardsubmit);
        mbenefitthepeoplererechargeforcardback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeoplererechargeforcardselectcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeoplererechargeforcard.this, Benefitthepeopleselectbankcard.class);
                startActivity(intent);
            }
        });
        mbenefitthepeoplererechargeforcardsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = "100";
                String bindMedium = "6222080200001116600";
                String mediumId = "6214761708600604241";
                String a = RSAutilsmy.encryptDataByPublicKey(money.getBytes(), publicKey).replaceAll("\r|\n", "");
                String b = RSAutilsmy.encryptDataByPublicKey(bindMedium.getBytes(), publicKey).replaceAll("\r|\n", "");
                String c = RSAutilsmy.encryptDataByPublicKey(mediumId.getBytes(), publicKey).replaceAll("\r|\n", "");

                LogUtil.d("money==", a + "");
                LogUtil.d("bindMedium==", b + "");
                LogUtil.d("mediumId==", c + "");

                ShowVolleyRequestforaddIDBCrecharge(a, b, c);

            }


        });
    }

    //工商银行二类户结算账户充值接口
    private void ShowVolleyRequestforaddIDBCrecharge(String money, String bindMedium, String mediumId) {
        String url = ApiUrls.ACCOUNTRECHARGE;
        Map<String, String> params = new HashMap<String, String>();
        params.put("amount", money);//交易额（后两位为小数位，100表示1元）（RSA加密）
        params.put("bindMedium",bindMedium);//绑定的I类卡号（RSA加密）
        params.put("mediumId", mediumId);//工行联名卡号（RSA加密）
        params.put("summary", "充值");
        params.put("remarks", "充值");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = response.getString("code");
                            if (code.equals("R00001")) {
                                Intent intent = new Intent(Benefitthepeoplererechargeforcard.this, Benefitthepeoplerechargesuccessful.class);
                                startActivity(intent);
                                finish();
                            } else {
                                String desc = response.getString("desc");
                                ToastUtils.getInstance(Benefitthepeoplererechargeforcard.this).showMessage(desc);
                            }
                        } catch (JSONException e) {
                            ToastUtils.getInstance(Benefitthepeoplererechargeforcard.this).showMessage("系统繁忙");

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("VolleyError", error + "");
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

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeoplererechargeforcardlayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
