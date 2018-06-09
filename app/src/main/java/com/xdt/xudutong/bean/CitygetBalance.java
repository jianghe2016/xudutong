package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\8\3 0003.
 */

public class CitygetBalance {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询余额成功
     * content : {"data":13.6}
     * sessionId : null
     */

    private int flag;
    private String code;
    private String desc;
    private ContentBean content;
    private Object sessionId;

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

    public Object getSessionId() {
        return sessionId;
    }

    public void setSessionId(Object sessionId) {
        this.sessionId = sessionId;
    }

    public static class ContentBean {
        /**
         * data : 13.6
         */

        private double data;

        public double getData() {
            return data;
        }

        public void setData(double data) {
            this.data = data;
        }
    }
}
