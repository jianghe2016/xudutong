package com.xdt.xudutong.frgment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.LocalLiifeFragmentlifeRecycleAdapter;
import com.xdt.xudutong.adapder.LocallifeBookrecycleadapter;
import com.xdt.xudutong.homefragment.Homecardgroupbuttonsixnext;
import com.xdt.xudutong.locallifefragment.Locallifelifeview;
import com.xdt.xudutong.locallifefragment.Localrecycledetails;
import com.xdt.xudutong.locallifefragment.Scenery;
import com.xdt.xudutong.locallifefragment.SceneryDetails;
import com.xdt.xudutong.utils.ToastUtils;

/**
 * Created by Administrator on 2017/5/8.
 */

public class LocalLifeFragment extends BaseFragment {
    private View view;
    private RecyclerView fragment_locallifebuttongrouprecycleview1;
    private int[] locallifeimgs;
    private String[] locallifeStrings;
    private String[] locallifeStrings2;
    private String[] numberstrings;
    private RecyclerView locallife_bookrecycle1;
    private ImageView loacllife_searchbook1;
    private EditText loacllife_searchbooktext1;
    private LinearLayout mlocal_life_jingdian1;
    private LinearLayout mlocal_life_jingdian2;
    private LinearLayout mlocal_life_jingdian3;
    private LinearLayout mlocal_life_jingdian4;
    private TextView locallife_scenerymore1;
    private LinearLayout mlocallife_lifeitem1;
    private LinearLayout mlocallife_lifeitem2;
    private LinearLayout mlocallife_lifeitem3;
    private LinearLayout mlocallife_lifeitem4;
    private LinearLayout mlocallife_lifeitem5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_locallife, null);
        Myinitview();
        MyinitData();
        //mGridView.setAdapter(new LocalAdapter());
        return view;
    }

    @Override
    public View initView() {
        return null;
    }


    private void Myinitview() {

        //自定义线性布局，不要滑动
        class CustomLinearLayoutManager extends LinearLayoutManager {
            private boolean isScrollEnabled = true;

            public CustomLinearLayoutManager(Context context) {
                super(context);
            }

            public void setScrollEnabled(boolean flag) {
                this.isScrollEnabled = flag;
            }

            @Override
            public boolean canScrollVertically() {
                //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
                return isScrollEnabled && super.canScrollVertically();
            }
        }
        //查找控件
        locallifeimgs = new int[]{R.drawable.life_banjia, R.drawable.life_canyin, R.drawable.life_hunjie,
                R.drawable.life_huishou, R.drawable.life_lvxing, R.drawable.life_weixiu};
        locallifeStrings = new String[]{"搬家/物流", "美容/美发",
                "婚介", "回收服务", "旅游", "维修",};
        locallifeStrings2 = new String[]{"快递/配送", "餐饮/娱乐",
                "婚庆/摄影", "家政/保洁",
                "休闲/健身", "疏通/装修"};
        numberstrings = new String[]{"1", "6", "9", "15", "19", "25"};

        loacllife_searchbook1 = (ImageView) view.findViewById(R.id.loacllife_searchbook);
        loacllife_searchbooktext1 = (EditText) view.findViewById(R.id.loacllife_searchbooktext);
        loacllife_searchbooktext1.clearFocus();
        //四个展示的景点
        mlocal_life_jingdian1 = (LinearLayout) view.findViewById(R.id.local_life_jingdian1);
        mlocal_life_jingdian2 = (LinearLayout) view.findViewById(R.id.local_life_jingdian2);
        mlocal_life_jingdian3 = (LinearLayout) view.findViewById(R.id.local_life_jingdian3);
        mlocal_life_jingdian4 = (LinearLayout) view.findViewById(R.id.local_life_jingdian4);
        //更多景点
        locallife_scenerymore1 = (TextView) view.findViewById(R.id.locallife_scenerymore);

        //地图五个
        mlocallife_lifeitem1 = (LinearLayout) view.findViewById(R.id.locallife_lifeitem1);
        mlocallife_lifeitem2 = (LinearLayout) view.findViewById(R.id.locallife_lifeitem2);
        mlocallife_lifeitem3 = (LinearLayout) view.findViewById(R.id.locallife_lifeitem3);
        mlocallife_lifeitem4 = (LinearLayout) view.findViewById(R.id.locallife_lifeitem4);
        mlocallife_lifeitem5 = (LinearLayout) view.findViewById(R.id.locallife_lifeitem5);

        fragment_locallifebuttongrouprecycleview1 = (RecyclerView) view.findViewById(R.id.fragment_locallifebuttongrouprecycleview);
        locallife_bookrecycle1 = (RecyclerView) view.findViewById(R.id.locallife_bookrecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        locallife_bookrecycle1.setLayoutManager(layoutManager);
        fragment_locallifebuttongrouprecycleview1.setLayoutManager(new GridLayoutManager(getContext(), 2));

    }

    private void MyinitData() {
        LocalLiifeFragmentlifeRecycleAdapter localLiifeFragmentlifeRecycleAdapter = new LocalLiifeFragmentlifeRecycleAdapter(getContext(), locallifeimgs, locallifeStrings, locallifeStrings2);
        localLiifeFragmentlifeRecycleAdapter.notifyDataSetChanged();
        fragment_locallifebuttongrouprecycleview1.setAdapter(localLiifeFragmentlifeRecycleAdapter);
        //中间6个按钮的点击事件
        localLiifeFragmentlifeRecycleAdapter.setOnItemClickListener(new LocalLiifeFragmentlifeRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), Localrecycledetails.class);
                intent.putExtra("locallifelocallifeidone", numberstrings[position]);
                intent.putExtra("locallifelocallifeidoneid", position);
                startActivity(intent);
            }
        });
        //图书查询的recycleview
        int[] books = new int[]{R.drawable.life_book1, R.drawable.life_book2, R.drawable.life_book3, R.drawable.life_book4,R.drawable.life_book5,R.drawable.life_book6};
        LocallifeBookrecycleadapter locallifeBookrecycle = new LocallifeBookrecycleadapter(getContext(), books);
        locallife_bookrecycle1.setAdapter(locallifeBookrecycle);
        locallifeBookrecycle.setOnItemClickListener(new LocallifeBookrecycleadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getContext(), Homecardgroupbuttonsixnext.class);
                        intent.putExtra("libraryname", "解忧杂货店");
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getContext(), Homecardgroupbuttonsixnext.class);
                        intent1.putExtra("libraryname", "平凡的世界");
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getContext(), Homecardgroupbuttonsixnext.class);
                        intent2.putExtra("libraryname", "活着");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getContext(), Homecardgroupbuttonsixnext.class);
                        intent3.putExtra("libraryname", "白夜行");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getContext(), Homecardgroupbuttonsixnext.class);
                        intent4.putExtra("libraryname", "围城");
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(getContext(), Homecardgroupbuttonsixnext.class);
                        intent5.putExtra("libraryname", "朝花夕拾");
                        startActivity(intent5);
                        break;
                }

            }
        });
        //地图五兄弟
        mlocallife_lifeitem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Locallifelifeview.class);
                intent.putExtra("locallife_locallifelife", "餐饮服务");
                intent.putExtra("locallife_locallifelife1", "餐饮");
                startActivity(intent);
            }
        });
        mlocallife_lifeitem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Locallifelifeview.class);
                intent.putExtra("locallife_locallifelife", "商务住宅");
                intent.putExtra("locallife_locallifelife1", "住宅");
                startActivity(intent);
            }
        });
        mlocallife_lifeitem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Locallifelifeview.class);
                intent.putExtra("locallife_locallifelife", "生活服务");
                intent.putExtra("locallife_locallifelife1", "生活服务");
                startActivity(intent);
            }
        });
        mlocallife_lifeitem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Locallifelifeview.class);
                intent.putExtra("locallife_locallifelife", "汽车服务");
                intent.putExtra("locallife_locallifelife1", "汽车服务");
                startActivity(intent);
            }
        });
        mlocallife_lifeitem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Locallifelifeview.class);
                intent.putExtra("locallife_locallifelife", "汽车销售");
                intent.putExtra("locallife_locallifelife1", "汽车销售");
                startActivity(intent);
            }
        });

        //景点查询
        mlocal_life_jingdian1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SceneryDetails.class);
                intent.putExtra("sceneryid", "161");
                intent.putExtra("sceneryview_name", "许昌曹丞相府");
                startActivity(intent);

            }
        });
        mlocal_life_jingdian2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SceneryDetails.class);
                intent.putExtra("sceneryid", "281");
                intent.putExtra("sceneryview_name", "灞陵桥");
                startActivity(intent);

            }
        });
        mlocal_life_jingdian3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SceneryDetails.class);
                intent.putExtra("sceneryid", "160");
                intent.putExtra("sceneryview_name", "春秋楼");
                startActivity(intent);

            }
        });
        mlocal_life_jingdian4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SceneryDetails.class);
                intent.putExtra("sceneryid", "168");
                intent.putExtra("sceneryview_name", "文峰塔");
                startActivity(intent);

            }
        });
        //更多景点查询
        locallife_scenerymore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Scenery.class);
                startActivity(intent);
            }
        });
        //点击搜索按钮开始图书查询
        loacllife_searchbook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookname = loacllife_searchbooktext1.getText().toString();
                if (!bookname.isEmpty()) {
                    Intent intent4 = new Intent(getContext(), Homecardgroupbuttonsixnext.class);
                    intent4.putExtra("libraryname", bookname);
                    startActivity(intent4);
                } else {
                    ToastUtils.getInstance(getContext()).showMessage("输入不能为空");
                }

            }
        });
        loacllife_searchbooktext1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEND ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER))
                {
                    String bookname = loacllife_searchbooktext1.getText().toString();
                    if (!bookname.isEmpty()) {
                        Intent intent5 = new Intent(getContext(), Homecardgroupbuttonsixnext.class);
                        intent5.putExtra("libraryname", bookname);
                        startActivity(intent5);
                    } else {
                        ToastUtils.getInstance(getContext()).showMessage("输入不能为空");
                    }
                }
                return false;

            }
        });
    }
}
