package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2018\3\14 0014.
 */

public class SettlementaccountBinding {

    /**
     * flag : 1
     * code : R00001
     * desc : 请求绑卡成功
     * content : {"data":{"returnCode":0,"returnMsg":"","msgId":"7adaac58-ec4e-40af-b7a4-dc2d612321bd","icbcWorkDate":"1635523200000","eventNo":"040400131303901150311033101","smsSendNo":"382547","success":true}}
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
        /**
         * data : {"returnCode":0,"returnMsg":"","msgId":"7adaac58-ec4e-40af-b7a4-dc2d612321bd","icbcWorkDate":"1635523200000","eventNo":"040400131303901150311033101","smsSendNo":"382547","success":true}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * returnCode : 0
             * returnMsg :
             * msgId : 7adaac58-ec4e-40af-b7a4-dc2d612321bd
             * icbcWorkDate : 1635523200000
             * eventNo : 040400131303901150311033101
             * smsSendNo : 382547
             * success : true
             */

            private int returnCode;
            private String returnMsg;
            private String msgId;
            private String icbcWorkDate;
            private String eventNo;
            private String smsSendNo;
            private boolean success;

            public int getReturnCode() {
                return returnCode;
            }

            public void setReturnCode(int returnCode) {
                this.returnCode = returnCode;
            }

            public String getReturnMsg() {
                return returnMsg;
            }

            public void setReturnMsg(String returnMsg) {
                this.returnMsg = returnMsg;
            }

            public String getMsgId() {
                return msgId;
            }

            public void setMsgId(String msgId) {
                this.msgId = msgId;
            }

            public String getIcbcWorkDate() {
                return icbcWorkDate;
            }

            public void setIcbcWorkDate(String icbcWorkDate) {
                this.icbcWorkDate = icbcWorkDate;
            }

            public String getEventNo() {
                return eventNo;
            }

            public void setEventNo(String eventNo) {
                this.eventNo = eventNo;
            }

            public String getSmsSendNo() {
                return smsSendNo;
            }

            public void setSmsSendNo(String smsSendNo) {
                this.smsSendNo = smsSendNo;
            }

            public boolean isSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }
        }
    }
}
