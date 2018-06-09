package com.xdt.xudutong.homefragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.gjiazhe.wavesidebar.WaveSideBar;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ZwjfeegetCommunitys;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\6\24 0024.
 */

public class Homecardgroupbuttonfour extends BaseActivity {

    private ListView country_lvcountrylistview1;
    private ProgressDialog progressDialog;
    private List<ZwjfeegetCommunitys.ContentBean.DataBean> zwjfeegetcommunitysdata;
    private WaveSideBar side_bar1;
    private ArrayList contacts = new ArrayList();
    private LinearLayout memptystates_layout;



    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_button4);
        progressDialog = ProgressDialog.showDialog(Homecardgroupbuttonfour.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        String countryside = "2";
        ShowVolleyRequest(countryside);
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);

        LinearLayout homebuttongroup_button4back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button4back);
        country_lvcountrylistview1 = (ListView) findViewById(R.id.country_lvcountrylistview);
        side_bar1 = (WaveSideBar) findViewById(R.id.side_bar);
        side_bar1.setLazyRespond(true);
        side_bar1.setTextAlign(WaveSideBar.TEXT_ALIGN_CENTER);
        side_bar1.setMaxOffset(100);
        side_bar1.setTextColor(Color.BLACK);
        homebuttongroup_button4back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }

    private void ShowVolleyRequest(String countryside) {
        String url = ApiUrls.GETCOMMUNITYS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("area_code", countryside);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private Cardbuttonlistviewoneadapter cardbuttonlistviewoneadapter1;

                    //ViewHolder静态类
                    class ViewHolder {
                        public TextView title1;
                        public ListView title2;

                    }

                    class Cardbuttonlistviewoneadapter extends BaseAdapter implements SectionIndexer {
                        private List<ZwjfeegetCommunitys.ContentBean.DataBean> zwjfeegetcommunitysdata;
                        private LayoutInflater mInflater = null;

                        public Cardbuttonlistviewoneadapter(Context applicationContext, List<ZwjfeegetCommunitys.ContentBean.DataBean> zwjfeegetcommunitysdata) {
                            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
                            this.mInflater = LayoutInflater.from(applicationContext);
                            this.zwjfeegetcommunitysdata = zwjfeegetcommunitysdata;
                            notifyDataSetChanged();
                        }

                        @Override
                        public int getCount() {
                            return zwjfeegetcommunitysdata.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return position;
                        }

                        @Override
                        public long getItemId(int position) {
                            return position;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            ViewHolder holder = null;
                            //如果缓存convertView为空，则需要创建View
                            if (convertView == null) {
                                holder = new ViewHolder();
                                //根据自定义的Item布局加载布局
                                convertView = mInflater.inflate(R.layout.home_cardgroup_button41item, null);
                                holder.title1 = (TextView) convertView.findViewById(R.id.home_cardgroup_button41itemtext1);
                                holder.title2 = (ListView) convertView.findViewById(R.id.home_cardgroup_button41itemlistview);
                                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                                convertView.setTag(holder);
                            } else {
                                holder = (ViewHolder) convertView.getTag();
                            }
                            //获取字母和子listview集合的数据
                            String charNamesend = zwjfeegetcommunitysdata.get(position).getCharName();
                            contacts.add(charNamesend);
                            final List<ZwjfeegetCommunitys.ContentBean.DataBean.CommunitysBean> communitys = zwjfeegetcommunitysdata.get(position).getCommunitys();

                            //子listview的适配器
                            Inneradapter inneradapter = new Inneradapter(Homecardgroupbuttonfour.this, charNamesend, communitys);
                            holder.title2.setAdapter(inneradapter);
                            //点击子Listview的条目，进入下页面
                            holder.title2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String community_id = communitys.get(position).getCommunity_id();
                                    String community_name = communitys.get(position).getCommunity_name();
                                    Intent intent = new Intent(Homecardgroupbuttonfour.this, Homecardgroupbuttonfournext.class);
                                    intent.putExtra("community_id", community_id);
                                    intent.putExtra("community_name", community_name);
                                    startActivity(intent);
                                }
                            });
                            /**
                             *重新设置Listview的高度，就可以展示子Listview的全部数据
                             */
                            setListViewHeight(holder.title2);

                            //根据position获取分类的首字母的char ascii值
                            int section = getSectionForPosition(position);

                            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
                            if (position == getPositionForSection(section)) {
                                holder.title1.setVisibility(View.VISIBLE);
                                holder.title1.setText(charNamesend);
                            } else {
                                holder.title1.setVisibility(View.GONE);
                            }

                            return convertView;
                        }


                        @Override
                        public Object[] getSections() {
                            return null;
                        }

                        @Override
                        public int getPositionForSection(int sectionIndex) {
                            for (int i = 0; i < getCount(); i++) {
                                String PositionForSectioncharname = zwjfeegetcommunitysdata.get(i).getCharName();
                                char firstChar = PositionForSectioncharname.toUpperCase().charAt(0);
                                if (firstChar == sectionIndex) {
                                    return i;
                                }
                            }
                            return -1;
                        }

                        @Override
                        public int getSectionForPosition(int position) {
                            return zwjfeegetcommunitysdata.get(position).getCharName().charAt(0);
                        }
                    }

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ZwjfeegetCommunitys zwjfeegetcommunitys = gson.fromJson(response.toString(), ZwjfeegetCommunitys.class);
                        zwjfeegetcommunitysdata = zwjfeegetcommunitys.getContent().getData();
                        int flag = zwjfeegetcommunitys.getFlag();
                        if (flag == 1) {
                           successcontent();
                            cardbuttonlistviewoneadapter1 = new Cardbuttonlistviewoneadapter(Homecardgroupbuttonfour.this, zwjfeegetcommunitysdata);
                            country_lvcountrylistview1.setAdapter(cardbuttonlistviewoneadapter1);

                            side_bar1.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
                                @Override
                                public void onSelectIndexItem(String index) {
                                    LogUtil.d("indexindex",index+"");
                                    int position = cardbuttonlistviewoneadapter1.getPositionForSection(index.charAt(0));
                                    if (position != -1) {
                                        country_lvcountrylistview1.setSelection(position);
                                    }
                                }
                            });
                        } else {
                         failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
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

    //ViewHolder静态类
    class ViewHolder {
        public TextView title1;
    }

    public class Inneradapter extends BaseAdapter {
        private List<ZwjfeegetCommunitys.ContentBean.DataBean.CommunitysBean> communitys;
        private LayoutInflater mInflater = null;
        private String charNamesend;

        public Inneradapter(Context applicationContext, String charNamesend, List<ZwjfeegetCommunitys.ContentBean.DataBean.CommunitysBean> communitys) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(applicationContext);
            this.communitys = communitys;
            this.charNamesend = charNamesend;

        }

        @Override
        public int getCount() {
            return communitys.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.home_cardgroup_button41inneritem, null);
                holder.title1 = (TextView) convertView.findViewById(R.id.home_cardgroup_button41inneritemtext1);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String charName = zwjfeegetcommunitysdata.get(position).getCharName();
            String community_name = communitys.get(position).getCommunity_name();

            holder.title1.setText(community_name);
            return convertView;
        }
    }

    //给子listvew重新测量宽高，不然只显示一个条目
    public void setListViewHeight(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0);

            totalHeight += listItem.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);

    }
    private void failcontent() {
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }

}
