package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\6\24 0024.
 */

public class UpdatePsw {


    /**
     * code : R00001
     * flag : true
     * desc : 更新成功
     * response : {"body":true}
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
        /**
         * body : true
         */

        private boolean body;

        public boolean isBody() {
            return body;
        }

        public void setBody(boolean body) {
            this.body = body;
        }
    }
}
