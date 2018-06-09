package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.HomemerchantselectrecycleAdapter;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\11\15 0015.
 */

public class PersonMerchantSelect extends BaseActivity {

    private RecyclerView mhome_merchant_selectrecycleview;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_merchant_select);
    }

    @Override
    public void initView() {
        LinearLayout mperson_merchant_selectback = (LinearLayout) findViewById(R.id.person_merchant_selectback);
        mhome_merchant_selectrecycleview = (RecyclerView) findViewById(R.id.home_merchant_selectrecycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonMerchantSelect.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mhome_merchant_selectrecycleview.setLayoutManager(linearLayoutManager);
        mperson_merchant_selectback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        final Intent intent = getIntent();
        final String loadinnetid = intent.getStringExtra("loadinnetid");
        final String loadinnetname = intent.getStringExtra("loadinnetname");
        int[] person_merchant_selectimglist = {R.drawable.person_merchant_selectimg1, R.drawable.person_merchant_selectimg2, R.drawable.person_merchant_selectimg3,
                R.drawable.person_merchant_selectimg4, R.drawable.person_merchant_selectimg5, R.drawable.person_merchant_selectimg6,
                R.drawable.person_merchant_selectimg7, R.drawable.person_merchant_selectimg8, R.drawable.person_merchant_selectimg9,
                R.drawable.person_merchant_selectimg10, R.drawable.person_merchant_selectimg11};
        String[] person_merchant_selecttextlist = {"充值类交易明细", "充值类交易汇总", "充值类撤销交易明细", "充值类撤销交易汇总", "消费类交易明细", "消费类交易汇总",
                "消费可疑明细", "消费可疑交易汇总", "退货类交易明细", "消费纠错明细", "消费纠错交易汇总"};
        HomemerchantselectrecycleAdapter homemerchantselectrecycleAdapter = new HomemerchantselectrecycleAdapter(PersonMerchantSelect.this, person_merchant_selectimglist, person_merchant_selecttextlist);
        mhome_merchant_selectrecycleview.setAdapter(homemerchantselectrecycleAdapter);
        homemerchantselectrecycleAdapter.setOnItemClickListener(new HomemerchantselectrecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //进入不同的页面
                switch (position) {
                    case 0:
                        Intent intent1 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent1.putExtra("merchantslecttoptext","充值类交易明细");
                        intent1.putExtra("loadinnetid",loadinnetid);
                        intent1.putExtra("loadinnetname",loadinnetname);
                        intent1.putExtra("merchantslecturl","merchant/transactionDetails");
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent2.putExtra("merchantslecttoptext","充值类交易汇总");
                        intent2.putExtra("loadinnetid",loadinnetid);
                        intent2.putExtra("loadinnetname",loadinnetname);
                        intent2.putExtra("merchantslecturl","merchant/rechargeSummary");
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent3.putExtra("merchantslecttoptext","充值类撤销交易明细");
                        intent3.putExtra("loadinnetid",loadinnetid);
                        intent3.putExtra("loadinnetname",loadinnetname);
                        intent3.putExtra("merchantslecturl","merchant/rechargeRepeal");
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent4.putExtra("merchantslecttoptext","充值类撤销交易汇总");
                        intent4.putExtra("loadinnetid",loadinnetid);
                        intent4.putExtra("loadinnetname",loadinnetname);
                        intent4.putExtra("merchantslecturl","merchant/rechargeRepealSummary");
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent5.putExtra("merchantslecttoptext","消费类交易明细");
                        intent5.putExtra("loadinnetid",loadinnetid);
                        intent5.putExtra("loadinnetname",loadinnetname);
                        intent5.putExtra("merchantslecturl","merchant/consumeRecord");
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent6.putExtra("merchantslecttoptext","消费类交易汇总");
                        intent6.putExtra("loadinnetid",loadinnetid);
                        intent6.putExtra("loadinnetname",loadinnetname);
                        intent6.putExtra("merchantslecturl","merchant/consumeSummary");
                        startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent7.putExtra("merchantslecttoptext","消费可疑明细");
                        intent7.putExtra("loadinnetid",loadinnetid);
                        intent7.putExtra("loadinnetname",loadinnetname);
                        intent7.putExtra("merchantslecturl","merchant/consumerSuspiciousDetails");
                        startActivity(intent7);
                        break;
                    case 7:
                        Intent intent8 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent8.putExtra("merchantslecttoptext","消费可疑交易汇总");
                        intent8.putExtra("loadinnetid",loadinnetid);
                        intent8.putExtra("loadinnetname",loadinnetname);
                        intent8.putExtra("merchantslecturl","merchant/consumerSuspiciousSummary");
                        startActivity(intent8);
                        break;
                    case 8:
                        Intent intent9 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent9.putExtra("merchantslecttoptext","退货类交易明细");
                        intent9.putExtra("loadinnetid",loadinnetid);
                        intent9.putExtra("loadinnetname",loadinnetname);
                        intent9.putExtra("merchantslecturl","merchant/returnedPurchase");
                        startActivity(intent9);
                        break;
                    case 9:
                        Intent intent10 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent10.putExtra("merchantslecttoptext","消费纠错明细");
                        intent10.putExtra("loadinnetid",loadinnetid);
                        intent10.putExtra("loadinnetname",loadinnetname);
                        intent10.putExtra("merchantslecturl","merchant/consumeErrorCorrection");
                        startActivity(intent10);
                        break;
                    case 10:
                        Intent intent11 = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intent11.putExtra("merchantslecttoptext","消费纠错交易汇总");
                        intent11.putExtra("loadinnetid",loadinnetid);
                        intent11.putExtra("loadinnetname",loadinnetname);
                        intent11.putExtra("merchantslecturl","merchant/consumeErrorCorrectionSummary");
                        startActivity(intent11);
                        break;
                    default:
                        Intent intentdefult = new Intent(PersonMerchantSelect.this, PersonMerchantrecordsearch.class);
                        intentdefult.putExtra("loadinnetid",loadinnetid);
                        intentdefult.putExtra("merchantslecttoptext","消费类交易明细");
                        intentdefult.putExtra("loadinnetname",loadinnetname);
                        startActivity(intentdefult);
                        break;

                }

            }
        });
    }
}
