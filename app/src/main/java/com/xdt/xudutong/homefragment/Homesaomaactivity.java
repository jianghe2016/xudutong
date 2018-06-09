package com.xdt.xudutong.homefragment;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;



public class Homesaomaactivity extends BaseActivity {
    private static final int PICK_CONTACT = 1;
    private LinearLayout home_erweimaback1;
    private ImageView mssssssss;
    private TextView maaaaaaaaaaa;
    private TextView mbbbbb;

    @Override
    public void initView() {

    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_saoma);
        /*mssssssss = (ImageView) findViewById(R.id.ssssssss);
        maaaaaaaaaaa = (TextView) findViewById(R.id.aaaaaaaaaaa);
        mbbbbb = (TextView) findViewById(R.id.bbbbb);
        mbbbbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts
                        .CONTENT_URI);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });
        maaaaaaaaaaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScannerActivity.gotoActivity(Homesaomaactivity.this,
                        false, ScannerActivity.EXTRA_LASER_LINE_MODE_0, ScannerActivity.EXTRA_SCAN_MODE_0, false, false, true);
            }
        });*/

    }

   /* @Override
    public void initView() {
        final Intent intent = getIntent();
        //收到天健js页面的扫码
        final int saomaxdt = intent.getIntExtra("saomaxdt", 0);
        LogUtil.d("saomaxdtsaomaxdt", saomaxdt+"");
        mScannerView = (ScannerView) findViewById(R.id.scanner_view);
        home_erweimaback1 = (LinearLayout) findViewById(R.id.home_erweimaback);
        home_erweimaback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mScannerView.setLaserFrameSize(256, 256)
                .setLaserFrameCornerLength(22)
                .setMediaResId(R.raw.baidu_beep)
                .setLaserFrameCornerWidth(2)
                .setLaserFrameBoundColor(0xff06c1ae)
                .setLaserColor(0xff06c1ae)
                .setLaserLineHeight(2)
                .toggleLight(true)
                .setDrawText("将二维码条形码/放入框内,即可自动扫描", 12, 0x000000, true, 19)
                .setLaserFrameTopMargin(132);
        mScannerView.setOnScannerCompletionListener(new OnScannerCompletionListener() {
            @Override
            public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
                ParsedResultType type = parsedResult.getType();
                switch (type) {
                    case ADDRESSBOOK:
                        AddressBookActivity.gotoActivity(Homesaomaactivity.this, bundle);
                        break;
                    case URI:
                        URIParsedResult uriParsedResult = (URIParsedResult) parsedResult;
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(uriParsedResult.toString()));//为Intent设置数据
                        startActivity(intent);
                        finish();
                        URIParsedResult uriParsedResult = (URIParsedResult) parsedResult;
                        //如果收到1，则代表天健的扫描并post给天健js
                        if (saomaxdt == 1) {
                            ZxingEvent event = new ZxingEvent();
                            event.result = uriParsedResult.toString();
                            EventBus.getDefault().post(event);
                            finish();
                        } else {
                            Intent intent = new Intent(Homesaomaactivity.this, BrowserActivity.class);
                            intent.putExtra("urll", uriParsedResult.toString());
                            LogUtil.d("uriParsedResult1111", uriParsedResult.toString());
                            startActivity(intent);
                            finish();
                        }

                        break;
                    case TEXT:
                        String text = rawResult.getText();
                        LogUtil.d("uriParsedResult222", text.toString());

                        //如果是天健的扫码
                        if (saomaxdt == 1) {
                            ZxingEvent event = new ZxingEvent();
                            event.result = text;
                            EventBus.getDefault().post(event);
                            finish();
                        } else {
                            Log.d("uriParsedResult212", text.toString());
                            //showlittlegreenbikeresult(text.toString());
                            ToastUtils.getInstance(Homesaomaactivity.this).showMessage("非法链接 : " + text);

                        }
                        break;
                }
            }
        });

       // OptionsScannerActivity.gotoActivity(Homesaomaactivity.this);

    }

    private void showlittlegreenbikeresult(String littleorderString) {
        //请求天气

        String url = ApiUrls.WSBIKEAPPSCANCODESCANCODE;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderno", littleorderString);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("uriParsedResult333", response.toString());
                        final Gson gson = new Gson();
                        WsbikeappScanCodescanCode wsbikemakecardrecord1 = gson.fromJson(response.toString(), WsbikeappScanCodescanCode.class);
                        String code = wsbikemakecardrecord1.getCode();
                        if (code.equals("R00001")) {
                            WsbikeappScanCodescanCode.ContentBean.DataBean data = wsbikemakecardrecord1.getContent().getData();
                            String shedid = data.getShedid();
                            String lockid = data.getLockid();
                            Log.d("uriParsedResult444", "uriParsedResult444.");
                            Intent intent = new Intent(Homesaomaactivity.this, Littlegreenbikesearchdetails.class);
                            if (!TextUtils.isEmpty(shedid)) {
                                intent.putExtra("shedid", shedid);
                            }
                            if (!TextUtils.isEmpty(shedid)) {
                                intent.putExtra("lockid", lockid);
                            }
                            startActivity(intent);
                        } else {
                            String desc = wsbikemakecardrecord1.getDesc();
                            ToastUtils.getInstance(Homesaomaactivity.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {
            if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
                if (data != null) {
                    String stringExtra = data.getStringExtra(Scanner.Scan.RESULT);
                    maaaaaaaaaaa.setText(stringExtra);
                }
            } else if (requestCode == PICK_CONTACT) {
                // Data field is content://contacts/people/984
                showContactAsBarcode(data.getData());
            }
        }
    }
    private void showContactAsBarcode(Uri contactUri) {
        //可以自己组装bundle;
//        ParserUriToVCard parserUriToVCard = new ParserUriToVCard();
//        Bundle bundle = parserUriToVCard.parserUri(this, contactUri);
//        if (bundle != null) {
//            Bitmap bitmap = QREncode.encodeQR(new QREncode.Builder(this)
//                    .setParsedResultType(ParsedResultType.ADDRESSBOOK)
//                    .setBundle(bundle).build());
//            imageView.setImageBitmap(bitmap);
//            tvResult.setText("单击二维码图片识别");
//        } else tvResult.setText("联系人Uri错误");

        //只传Uri
        Bitmap bitmap = new QREncode.Builder(this)
                .setParsedResultType(ParsedResultType.ADDRESSBOOK)
                .setAddressBookUri(contactUri).build().encodeAsBitmap();
        mssssssss.setImageBitmap(bitmap);
        maaaaaaaaaaa.setText("单击二维码图片识别");
    }
 @Override
    protected void onResume() {
        mScannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mScannerView.onPause();
        super.onPause();
    }
*/
}
