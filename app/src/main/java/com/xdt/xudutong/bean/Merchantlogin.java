package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\11\20 0020.
 */

public class Merchantlogin {


    /**
     * flag : true
     * code : R00001
     * desc : 该用户认证通过
     * content : {"netname":"测试1","netid":"09000001"}
     */

    private boolean flag;
    private String code;
    private String desc;
    private ContentBean content;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
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
        /**
         * netname : 测试1
         * netid : 09000001
         */

        private String netname;
        private String netid;

        public String getNetname() {
            return netname;
        }

        public void setNetname(String netname) {
            this.netname = netname;
        }

        public String getNetid() {
            return netid;
        }

        public void setNetid(String netid) {
            this.netid = netid;
        }
    }
}
