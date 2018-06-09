package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\9\21 0021.
 */

public class Feedbackaddfeedback {

    /**
     * flag : 1
     * code : R00001
     * desc : 反馈信息提交成功
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

    public static class ContentBean {
    }
}
