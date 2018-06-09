package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2018/6/5.
 */

public class PayBean {


    /**
     * flag : true
     * code : R00000
     * content : {"orderId":"20180605085554597803","tn":"703930138645008748407","respCode":"00","txnAmt":"1"}
     * desc : 银联同步应答数据成功
     */
    private boolean flag;
    private String code;
    private ContentEntity content;
    private String desc;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getCode() {
        return code;
    }

    public ContentEntity getContent() {
        return content;
    }

    public String getDesc() {
        return desc;
    }

    public class ContentEntity {
        /**
         * orderId : 20180605085554597803
         * tn : 703930138645008748407
         * respCode : 00
         * txnAmt : 1
         */
        private String orderId;
        private String tn;
        private String respCode;
        private String txnAmt;

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public void setTn(String tn) {
            this.tn = tn;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public void setTxnAmt(String txnAmt) {
            this.txnAmt = txnAmt;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getTn() {
            return tn;
        }

        public String getRespCode() {
            return respCode;
        }

        public String getTxnAmt() {
            return txnAmt;
        }
    }
}
