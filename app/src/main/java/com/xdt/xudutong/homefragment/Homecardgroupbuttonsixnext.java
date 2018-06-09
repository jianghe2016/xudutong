package com.xdt.xudutong.homefragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Libraryquerybooks;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/25.
 */

public class Homecardgroupbuttonsixnext extends BaseActivity {
    private ListView listview61;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;


    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_button61);
        progressDialog = ProgressDialog.showDialog(Homecardgroupbuttonsixnext.this);
        progressDialog.show();
    }
    @Override
    public void initView() {
        Intent intent = getIntent();
        String bookmetaTitle = intent.getStringExtra("libraryname");
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        LogUtil.d("上个页面传递过来的数据============", bookmetaTitle);
        ShowVolleyReques(bookmetaTitle);
        LinearLayout home_cardgroup_button61back1 = (LinearLayout) findViewById(R.id.home_cardgroup_button61back);
        listview61 = (ListView) findViewById(R.id.cardbuttonlistview61);
        home_cardgroup_button61back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }


    //请求网络
    private void ShowVolleyReques(String bookmetaTitle) {
        String url = ApiUrls.QUERYBOOKS;
        LogUtil.d("解析的数据为=", "进入了图书详情查询界面");

        //在这里设置需要post的参数
        final Map<String, String> params = new HashMap<String, String>();
        params.put("bookmetaTitle", bookmetaTitle.trim());
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Libraryquerybooks libraryquerybooks2 = gson.fromJson(response.toString(), Libraryquerybooks.class);
                        int flag = libraryquerybooks2.getFlag();
                        if (flag == 1) {
                         successcontent();
                            //展示数据
                             List<Libraryquerybooks.ContentBean.DataBean.BooksBean>  sixnextbooks1 = libraryquerybooks2.getContent().getData().getBooks();
                            if (null!=sixnextbooks1 && !sixnextbooks1.isEmpty()) {
                                showData(sixnextbooks1);
                            } else {
                                ToastUtils.getInstance(Homecardgroupbuttonsixnext.this).showMessage("暂无此书");
                            }
                        }else{
                         failcontent();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               failcontent();
                Log.e("图书详情请求失败", error.getMessage(), error);

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }

    //展示数据
    private void showData(final List<Libraryquerybooks.ContentBean.DataBean.BooksBean> sixnextbooks1) {

        //data=getData();
        MyAdapter adapter = new MyAdapter(Homecardgroupbuttonsixnext.this, sixnextbooks1);
        // adapter.Addall(sales);
        listview61.setAdapter(adapter);
        listview61.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String isbn1 = sixnextbooks1.get(position).getIsbn();
                String bookmetaTitle1 = sixnextbooks1.get(position).getBookmetaTitle();
                String author1 = sixnextbooks1.get(position).getAuthor();
                List<Libraryquerybooks.ContentBean.DataBean.BooksBean.BookLibsBean> bookLibs = sixnextbooks1.get(position).getBookLibs();
                if (bookLibs!=null&&!bookLibs.isEmpty()){
                    //索书号
                    String callno = bookLibs.get(0).getCallno();
                    //全部数量
                    String copycount = bookLibs.get(0).getCopycount();
                    //可供借阅
                    String loanableCount = bookLibs.get(0).getLoanableCount();
                    //所在馆
                    String curlibname = bookLibs.get(0).getCurlibName();
                    //所在藏书地点
                    String curlocalname = bookLibs.get(0).getCurlocalName();
                    Intent intent = new Intent(Homecardgroupbuttonsixnext.this, Homecardgroupbuttonsixnextnext.class);
                    intent.putExtra("isbnnumber", isbn1);
                    intent.putExtra("bookmetaTitle1", bookmetaTitle1);
                    intent.putExtra("author1", author1);
                    //索书号等
                    intent.putExtra("callno", callno);
                    intent.putExtra("copycount", copycount);
                    intent.putExtra("loanableCount", loanableCount);
                    intent.putExtra("curlibname", curlibname);
                    intent.putExtra("curlocalname", curlocalname);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Homecardgroupbuttonsixnext.this, Homecardgroupbuttonsixnextnext.class);
                    intent.putExtra("isbnnumber", isbn1);
                    intent.putExtra("bookmetaTitle1", bookmetaTitle1);
                    intent.putExtra("author1", author1);
                    startActivity(intent);
                }



            }
        });
    }

    //ViewHolder静态类
    class ViewHolder {
        public ImageView title1;
        public TextView title2;
        public TextView title3;
        public TextView title4;
        public TextView title5;
        public TextView title6;
        public TextView title7;
    }

    private class MyAdapter extends BaseAdapter {
        private List<Libraryquerybooks.ContentBean.DataBean.BooksBean> sixnextbooks1;
        private LayoutInflater mInflater = null;
        private Context context;

        private MyAdapter(Context context, List<Libraryquerybooks.ContentBean.DataBean.BooksBean> sixnextbooks1) {
            //根据context上下文加载布局，这里的是Demo17Activity本身，即this
            this.mInflater = LayoutInflater.from(context);
            this.sixnextbooks1 = sixnextbooks1;
            this.context = context;
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适器中所代表的数据集中的条目数
            return sixnextbooks1.size();
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            //获取数据集中与指定索引对应的数据项
            return position;
        }

        @Override
        public long getItemId(int position) {
            //Get the row id associated with the specified position in the list.
            //获取在列表中与指定索引对应的行id
            return position;
        }

        //Get a View that displays the data at the specified position in the data set.
        //获取一个在数据集中指定索引的视图来显示数据
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            //如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.home_cardgroup_button61item, null);

                holder.title1 = (ImageView) convertView.findViewById(R.id.cardgroupbutton61item1);
                holder.title2 = (TextView) convertView.findViewById(R.id.cardgroupbutton61item2);
                holder.title3 = (TextView) convertView.findViewById(R.id.cardgroupbutton61item3);
                holder.title4 = (TextView) convertView.findViewById(R.id.cardgroupbutton61item4);
                holder.title5 = (TextView) convertView.findViewById(R.id.cardgroupbutton61item5);
                holder.title6 = (TextView) convertView.findViewById(R.id.cardgroupbutton61item6);
                holder.title7 = (TextView) convertView.findViewById(R.id.cardgroupbutton61item7);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Glide.with(context).load(sixnextbooks1.get(position).getImgsrc()).into(holder.title1);
            holder.title2.setText(sixnextbooks1.get(position).getBookmetaTitle().toString());
            holder.title3.setText(sixnextbooks1.get(position).getAuthor().toString());
            holder.title4.setText(sixnextbooks1.get(position).getPubCompany().toString());
            holder.title5.setText(sixnextbooks1.get(position).getPubDate().toString());
            List<Libraryquerybooks.ContentBean.DataBean.BooksBean.BookLibsBean> bookLibs = sixnextbooks1.get(position).getBookLibs();
            if (bookLibs!=null){
                holder.title6.setText(bookLibs.get(0).getCopycount());
                holder.title7.setText(bookLibs.get(0).getLoanableCount());
            }
            else{
                holder.title6.setText("暂无数据");
                holder.title7.setText("暂无数据");
            }
            return convertView;
        }
    }
    private void failcontent() {
        listview61.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        listview61.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }
}