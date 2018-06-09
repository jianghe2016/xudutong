package com.xdt.xudutong.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2017/5/11.
 */

public class SpUtils {

    public static SharedPreferences sp = null;

    public static void putParam(Context context, String paramName, String value) {

        // 1.得到sp对象
        if(sp == null){
            sp = context.getSharedPreferences(
                    SpConstant.SP_CONFIG, Context.MODE_PRIVATE);
        }

        // 2.得到编辑器
        SharedPreferences.Editor editor = sp.edit();
        // 3.编辑数据
        editor.putString(paramName, value);
        // 3.提交 保存
        editor.commit();
    }

    public static void putParam(Context context, String paramName, int value) {

        // 1.得到sp对象
        if(sp == null){
            sp = context.getSharedPreferences(
                    SpConstant.SP_CONFIG, Context.MODE_PRIVATE);
        }
        // 2.得到编辑器
        SharedPreferences.Editor editor = sp.edit();
        // 3.编辑数据
        editor.putInt(paramName, value);
        // 3.提交 保存
        editor.commit();
    }

    public static void putParam(Context context, String paramName, boolean value) {

        // 1.得到sp对象
        if(sp == null){
            sp = context.getSharedPreferences(
                    SpConstant.SP_CONFIG, Context.MODE_PRIVATE);
        }
        // 2.得到编辑器
        SharedPreferences.Editor editor = sp.edit();
        // 3.编辑数据
        editor.putBoolean(paramName, value);
        // 3.提交 保存
        editor.commit();
    }

    public static String getParam(Context context, String paramName,
                                  String defValue) {
        // 得到sp对象
        if(sp == null){
            sp = context.getSharedPreferences(
                    SpConstant.SP_CONFIG, Context.MODE_PRIVATE);
        }

        return sp.getString(paramName, defValue);

    }

    public static int getParam(Context context, String paramName,
                               int defValue) {
        // 得到sp对象
        if(sp == null){
            sp = context.getSharedPreferences(
                    SpConstant.SP_CONFIG, Context.MODE_PRIVATE);
        }

        return sp.getInt(paramName, defValue);

    }

    public static boolean getParam(Context context, String paramName,
                                   boolean defValue) {
        // 得到sp对象
        if(sp == null){
            sp = context.getSharedPreferences(
                    SpConstant.SP_CONFIG, Context.MODE_PRIVATE);
        }

        return sp.getBoolean(paramName, defValue);

    }

    /**
     * 保存对象
     *
     * @param context 上下文
     * @param key     键
     * @param obj     要保存的对象（Serializable的子类）
     * @param <T>     泛型定义
     */
    public static <T extends Serializable> void putObject(Context context, String key, T obj) {
        try {
            put(context, key, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取对象
     *
     * @param context 上下文
     * @param key     键
     * @param <T>     指定泛型
     * @return 泛型对象
     */
    public static <T extends Serializable> T getObject(Context context, String key) {
        try {
            return (T) get(context, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 存储List集合
     * @param context 上下文
     * @param key 存储的键
     * @param list 存储的集合
     */
    public static void putList(Context context, String key, List<? extends Parcelable> list) {
        try {
            put(context, key, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 存储Map集合
     * @param context 上下文
     * @param key 键
     * @param map 存储的集合
     * @param <K> 指定Map的键
     * @param <V> 指定Map的值
     */
    public static <K extends Serializable, V extends Serializable> void putMap(Context context,
                                                                               String key, Map<K, V> map)
    {
        try {
            put(context, key, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <K extends Serializable, V extends Serializable> Map<K, V> getMap(Context context,
                                                                                    String key)
    {
        try {
            return (Map<K, V>) get(context, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取List集合
     * @param context 上下文
     * @param key 键
     * @param <E> 指定泛型
     * @return List集合
     */
    public static <E extends Serializable> List<E> getList(Context context, String key) {
        try {
            return (List<E>) get(context, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void cleanParam(Context context) {

        // 1.得到sp对象
        if(sp == null){
            sp = context.getSharedPreferences(
                    SpConstant.SP_CONFIG, Context.MODE_PRIVATE);
        }
        // 2.得到编辑器
        SharedPreferences.Editor editor = sp.edit();
        // 3.编辑数据
        editor.clear();
        // 3.提交 保存
        editor.commit();
    }

    public static void removeParam(Context context,String access_token) {

        // 1.得到sp对象
        if(sp == null){
            sp = context.getSharedPreferences(
                    SpConstant.SP_CONFIG, Context.MODE_PRIVATE);
        }
        // 2.得到编辑器
        SharedPreferences.Editor editor = sp.edit();
        // 3.编辑数据
        editor.remove(access_token);
        // 3.提交 保存
        editor.commit();
    }
    /**存储对象*/
    private static void put(Context context, String key, Object obj)
            throws IOException
    {
        if (obj == null) {//判断对象是否为空
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream    oos  = null;
        oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        // 将对象放到OutputStream中
        // 将对象转换成byte数组，并将其进行base64编码
        String objectStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        baos.close();
        oos.close();

        putParam(context, key, objectStr);
    }

    /**获取对象*/
    private static Object get(Context context, String key)
            throws IOException, ClassNotFoundException
    {
        String wordBase64 = getParam(context, key,"");
        // 将base64格式字符串还原成byte数组
        if (TextUtils.isEmpty(wordBase64)) { //不可少，否则在下面会报java.io.StreamCorruptedException
            return null;
        }
        byte[]               objBytes = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais     = new ByteArrayInputStream(objBytes);
        ObjectInputStream ois      = new ObjectInputStream(bais);
        // 将byte数组转换成product对象
        Object obj = ois.readObject();
        bais.close();
        ois.close();
        return obj;
    }
}
