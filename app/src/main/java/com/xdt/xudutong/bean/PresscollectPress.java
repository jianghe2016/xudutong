package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\7\3 0003.
 */


//收藏新闻
public class PresscollectPress {

    /**
     *
     *
     * flag : 1
     * code : R00001
     * desc : 收藏新闻成功
     * content : {}
     */

    private int flag;
    private String code;
    private String desc;
    private ContentBean content;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public class ContentBean {
    }
}
