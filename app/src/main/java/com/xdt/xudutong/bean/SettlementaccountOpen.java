package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2018\3\9 0009.
 */

public class SettlementaccountOpen {

    /**
     * flag : 1
     * code : R00001
     * desc : 请求开户成功
     * content : {"data":{"returnCode":0,"returnMsg":"","msgId":"188fa745-7338-4560-9865-162965380f49","corpSerno":"20180309162343273","eventNo":"040400131273901162349000320","smsSendNo":"281697","success":true}}
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
         * data : {"returnCode":0,"returnMsg":"","msgId":"188fa745-7338-4560-9865-162965380f49","corpSerno":"20180309162343273","eventNo":"040400131273901162349000320","smsSendNo":"281697","success":true}
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
             * msgId : 188fa745-7338-4560-9865-162965380f49
             * corpSerno : 20180309162343273
             * eventNo : 040400131273901162349000320
             * smsSendNo : 281697
             * success : true
             */

            private int returnCode;
            private String returnMsg;
            private String msgId;
            private String corpSerno;
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

            public String getCorpSerno() {
                return corpSerno;
            }

            public void setCorpSerno(String corpSerno) {
                this.corpSerno = corpSerno;
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
