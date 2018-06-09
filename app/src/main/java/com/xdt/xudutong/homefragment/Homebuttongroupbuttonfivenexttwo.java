package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.ProductRecyclerAdapterone;
import com.xdt.xudutong.bean.TrainTicketQuery;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.DataAnalysetwo;
import com.xdt.xudutong.utils.SpUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class Homebuttongroupbuttonfivenexttwo extends BaseActivity {

    private EditText buttongroup_button51_eduittext1;
    private ImageView mhome_seach51;
    private RecyclerView buttongroup_button51_recycleview1;
    private ProgressBar homebuttongroup_button51progressbar1;
    private List list1;
    private List list2;
    private Gson gson;
    private LinearLayout homebuttongroup_button52back1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button52);
    }

    @Override
    public void initView() {
        homebuttongroup_button52back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button52back);
        mhome_seach51 = (ImageView) findViewById(R.id.home_seach52);
        homebuttongroup_button51progressbar1 = (ProgressBar) findViewById(R.id.homebuttongroup_button52progressbar);
        buttongroup_button51_recycleview1 = (RecyclerView) findViewById(R.id.buttongroup_button52_rlistview);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        buttongroup_button51_recycleview1.setLayoutManager(llm);
        buttongroup_button51_eduittext1 = (EditText) findViewById(R.id.buttongroup_button52_eduittext);
        initData();
    }

    private void initData() {
        homebuttongroup_button52back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mhome_seach51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Homebuttongroupbuttonfivenexttwo.this, Homebuttongroupbuttonfive.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        buttongroup_button51_eduittext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String s1 = s.toString();
                list1 = new ArrayList();
                list2 = new ArrayList();
                if (!s1.isEmpty()) {
                    List trainlistdata = DataAnalysetwo.readFile(Homebuttongroupbuttonfivenexttwo.this, s1);
                    //这个里面取list中的值
                    for (int i = 0; i < trainlistdata.size(); i++) {
                        String s3 = trainlistdata.get(i).toString();
                        gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(s3));
                        reader.setLenient(true);
                        TrainTicketQuery demotwo = gson.fromJson(reader, TrainTicketQuery.class);
                        String label = demotwo.getLabel();
                        list1.add(label);
                    }
                    for (int j = 0; j < trainlistdata.size(); j++) {
                        String s2 = trainlistdata.get(j).toString();
                        gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(s2));
                        reader.setLenient(true);
                        TrainTicketQuery demotwo = gson.fromJson(reader, TrainTicketQuery.class);
                        String label = demotwo.getValue();
                        list2.add(label);
                    }
                }
                showData(list1, list2);
                buttongroup_button51_recycleview1.setVisibility(View.VISIBLE);
                homebuttongroup_button51progressbar1.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }

        });
    }

    private void showData(final List list1, final List list2) {
        ProductRecyclerAdapterone productRecyclerAdapter = new ProductRecyclerAdapterone(Homebuttongroupbuttonfivenexttwo.this, list1);
        buttongroup_button51_recycleview1.setAdapter(productRecyclerAdapter);
        productRecyclerAdapter.setOnItemClickListener(new ProductRecyclerAdapterone.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //获取点击条目的代号和内容
                String accuratetarinquerytwo1 = list1.get(position).toString();
                String accuratetarinqueryonetext = list2.get(position).toString();
                buttongroup_button51_recycleview1.setVisibility(View.GONE);
                homebuttongroup_button51progressbar1.setVisibility(View.GONE);
                //点击条目的内容进行文件精确查询，得到地址代号
                String accuratetarinqueryone = DataAnalysetwo.readFile2(Homebuttongroupbuttonfivenexttwo.this, accuratetarinquerytwo1);
                //保存到本地条目内容，和条目代号
                SpUtils.putParam(Homebuttongroupbuttonfivenexttwo.this, "accuratetarinquerytwotext", accuratetarinqueryonetext);
                SpUtils.putParam(Homebuttongroupbuttonfivenexttwo.this, "accuratetarinquerytwo", accuratetarinqueryone);
                Bundle bundle = new Bundle();
                bundle.putString("accuratetarinquerytwotext", accuratetarinqueryonetext);
                bundle.putString("accuratetarinquerytwo", accuratetarinqueryone);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                // 返回intent
                setResult(15, intent);
                finish();

            }


        });
    }
}
