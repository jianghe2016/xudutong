package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017/5/18.
 */

public class CheckSearchPwd {


    /**
     * flag : 1
     * code : R00001
     * desc : 卡号与查询密码匹配成功！
     * content : {}
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

    public class ContentBean {
    }
}
