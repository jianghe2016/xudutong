package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\7\24 0024.
 */

public class CityuserBindCard {


    /**
     * code : R00001
     * flag : true
     * desc : 绑定成功！
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

    public class ResponseBean {
    }
}
