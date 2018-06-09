package com.xdt.xudutong.benefitthepeople;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gjiazhe.wavesidebar.WaveSideBar;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.Homebankselectrecycleviewadapter;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018\2\2 0002.
 */

public class Benefitthepeopleaddbankcardselectbank extends BaseActivity {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private WaveSideBar mbank_select_side_bar;
    private Homebankselectrecycleviewadapter homebankselectrecycleviewadapter;
    private RecyclerView mbenefitthepeopleaddbankcardselectbanklayout_recycleview;

    @Override
    public void initView() {
        String[] textlist = {"中国工商银行", "招商银行", "中国光大银行", "中信银行", "浦发银行", "广发银行", "华夏银行",
                "中国建设银行", "中国交通银行", "中国银行", "中国民生银行", "浦发银行", "广发银行", "华夏银行",
                "中国工商银行", "招商银行", "中国光大银行", "中信银行", "浦发银行", "广发银行", "华夏银行",
                "中国建设银行", "中国交通银行", "中国银行", "中国民生银行", "浦发银行", "广发银行", "华夏银行"};
        int[] imgtlist = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};
        mbenefitthepeopleaddbankcardselectbanklayout_recycleview = (RecyclerView) findViewById(R.id.benefitthepeopleaddbankcardselectbanklayout_recycleview);
        mbank_select_side_bar = (WaveSideBar) findViewById(R.id.bank_select_side_bar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Benefitthepeopleaddbankcardselectbank.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mbenefitthepeopleaddbankcardselectbanklayout_recycleview.setLayoutManager(linearLayoutManager);
        homebankselectrecycleviewadapter = new Homebankselectrecycleviewadapter(Benefitthepeopleaddbankcardselectbank.this, imgtlist, textlist);
        mbenefitthepeopleaddbankcardselectbanklayout_recycleview.setAdapter(homebankselectrecycleviewadapter);
        mbank_select_side_bar.setLazyRespond(true);
        mbank_select_side_bar.setTextAlign(WaveSideBar.TEXT_ALIGN_CENTER);
        mbank_select_side_bar.setMaxOffset(100);
        mbank_select_side_bar.setTextColor(Color.BLACK);
        initData();
    }

    private void initData() {
        homebankselectrecycleviewadapter.setOnItemClickListener(new Homebankselectrecycleviewadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                finish();
            }
        });
        mbank_select_side_bar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                //打印选择项，此功能未做完
                LogUtil.d("indexindex", index);
                ToastUtils.getInstance(Benefitthepeopleaddbankcardselectbank.this).showMessage(index);
                for (int i = 0; i < contacts.size(); i++) {
                    if (contacts.get(i).getIndex().equals(index)) {
                        ((LinearLayoutManager) mbenefitthepeopleaddbankcardselectbanklayout_recycleview.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeopleaddbankcardselectbanklayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }

    public class Contact {
        private String index;
        private String name;

        public Contact(String index, String name) {
            this.index = index;
            this.name = name;
        }

        public String getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }
    }
}
