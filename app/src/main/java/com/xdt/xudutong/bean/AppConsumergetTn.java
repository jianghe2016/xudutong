package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\12\28 0028.
 */

public class AppConsumergetTn {
    /**
     * flag : true
     * code : R00000
     * desc : 银联同步应答数据成功
     * content : {"tn":"663688701236108971800","respCode":"00","orderId":"20171226112709021103","txnAmt":"100"}
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
         * tn : 663688701236108971800
         * respCode : 00
         * orderId : 20171226112709021103
         * txnAmt : 100
         */

        private String tn;
        private String respCode;
        private String orderId;
        private String txnAmt;

        public String getTn() {
            return tn;
        }

        public void setTn(String tn) {
            this.tn = tn;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getTxnAmt() {
            return txnAmt;
        }

        public void setTxnAmt(String txnAmt) {
            this.txnAmt = txnAmt;
        }
    }
}
