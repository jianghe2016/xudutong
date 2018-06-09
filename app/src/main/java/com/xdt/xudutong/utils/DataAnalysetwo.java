package com.xdt.xudutong.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.alibaba.fastjson.JSONArray;
import com.xdt.xudutong.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 2017/5/30.
 */

public class DataAnalysetwo {
    /**
     * @param searchKey 要搜索的关键列
     * @return
     */
    //模糊查询键
    public static List<JSONObject> readFile(Context context, String searchKey) {
        List<JSONObject> list = new ArrayList<>();
        JSONObject result = new JSONObject();
        String flag;
        try {
     /*       FileInputStream fis = new FileInputStream(path);
            //UTF-8编码的指定是很重要的
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");*/
            Resources resources = context.getResources();
            InputStream inputStream = resources.openRawResource(R.raw.traintext);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
            BufferedReader bfr = new BufferedReader(inputStreamReader);
            String str = "";
            while ((flag = bfr.readLine()) != null) {
                str = str.concat(flag);
            }
            JSONArray array = JSONArray.parseArray(str);
            System.out.print(array.size());
            for (Object o : array) {
                com.alibaba.fastjson.JSONObject object = (com.alibaba.fastjson.JSONObject) o;
                String label = (String) object.get("label");
                if (label.contains(searchKey)) {
                    list.add(new JSONObject(object.toJSONString()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //不输入信息进行查询全部内容
    public static List<JSONObject> readFileEmpty(Context context) {
        List<JSONObject> list = new ArrayList<>();
        JSONObject result = new JSONObject();
        String flag;
        try {
     /*       FileInputStream fis = new FileInputStream(path);
            //UTF-8编码的指定是很重要的
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");*/
            Resources resources = context.getResources();
            InputStream inputStream = resources.openRawResource(R.raw.traintext);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
            BufferedReader bfr = new BufferedReader(inputStreamReader);
            String str = "";
            while ((flag = bfr.readLine()) != null) {
                str = str.concat(flag);
            }
            JSONArray array = JSONArray.parseArray(str);
            System.out.print(array.size());
            for (Object o : array) {
                com.alibaba.fastjson.JSONObject object = (com.alibaba.fastjson.JSONObject) o;
                list.add(new JSONObject(object.toJSONString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //精确查询火车地址代号
    public static String readFile2(Context context, String searchKey) {
        String result = null;
        String flag;
        try {
          /*  FileInputStream fis = new FileInputStream(path);
            //UTF-8编码的指定是很重要的
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");*/
            Resources resources = context.getResources();
            InputStream inputStream = resources.openRawResource(R.raw.traintextall);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
            BufferedReader bfr = new BufferedReader(inputStreamReader);
            String str = "";
            while ((flag = bfr.readLine()) != null) {
                str = str.concat(flag);
            }
            com.alibaba.fastjson.JSONObject object = com.alibaba.fastjson.JSONObject.parseObject(str);
            result = (String) object.get(searchKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //精确查询值
    public static String readFile3(Context context, String searchKey) {
        String result = null;
        String flag;
        try {
     /*       FileInputStream fis = new FileInputStream(path);
            //UTF-8编码的指定是很重要的
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");*/
            Resources resources = context.getResources();
            InputStream inputStream = resources.openRawResource(R.raw.querykuaidi);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
            BufferedReader bfr = new BufferedReader(inputStreamReader);
            String str = "";
            while ((flag = bfr.readLine()) != null) {
                str = str.concat(flag);
            }
            com.alibaba.fastjson.JSONObject object = com.alibaba.fastjson.JSONObject.parseObject(str);
            JSONArray company = (JSONArray) object.get("company");
            for (Object o : company) {
                com.alibaba.fastjson.JSONObject object2 = (com.alibaba.fastjson.JSONObject) o;
                String code = (String) object2.get("code");
                if (code.equals(searchKey)) {
                    result = (String) object2.get("companyname");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 从Json对象中获取数据
     *
     * @return
     */
    public JSONObject getDataToJson(String data) {
        JSONObject result = new JSONObject();
        if (!"".equals(data)) {
            try {
                result = new JSONObject(data);
            } catch (Exception e) {
                return result;
            }

        }
        return result;
    }


    public static Bitmap base64ToBitmap(String base64Data) {
      /*  byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);*/


        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(base64Data, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;

    }

    public static String bitmap64toString(Bitmap bitmap) {
        // 将Bitmap转换成字符串
        String string = null;

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);

        byte[] bytes = bStream.toByteArray();

        string = Base64.encodeToString(bytes, Base64.DEFAULT);

        return string;


    }

    public static String bitmap64toString2(Bitmap bitmap) {
        //将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

}
