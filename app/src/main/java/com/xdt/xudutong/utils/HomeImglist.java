package com.xdt.xudutong.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017\11\13 0013.
 */

public class HomeImglist implements Parcelable {

    private String urlString;
    private List lits;

    public HomeImglist() {

    }

    public HomeImglist(Parcel in){
        //如果元素数据是list类型的时候需要： lits = new ArrayList<?> in.readList(list);
        // 否则会出现空指针异常.并且读出和写入的数据类型必须相同.如果不想对部分关键字进行序列化,
        // 可以使用transient关键字来修饰以及static修饰.
        urlString = in.readString();

    }
    public void setHomeImglist(String urlString) {
        this.urlString = urlString;
    }

    public String getHomeImglist() {
        return urlString;
    }

    public static final Parcelable.Creator<HomeImglist> CREATOR = new Creator<HomeImglist>() {
        @Override
        public HomeImglist createFromParcel(Parcel in) {
            return new HomeImglist(in);
        }

        @Override
        public HomeImglist[] newArray(int size) {
            return new HomeImglist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(urlString);
    }
}
