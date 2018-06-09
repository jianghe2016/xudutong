package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\10\27 0027.
 */

public class CityunbindCard {
    /**
     * code : R00001
     * flag : true
     * desc : 操作成功！
     * response : {}
     */

    private String code;
    private boolean flag;
    private String desc;
    private ResponseBean response;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
    }
}
