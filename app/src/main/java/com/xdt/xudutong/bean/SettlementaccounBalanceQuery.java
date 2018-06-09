package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2018\3\28 0028.
 */

public class SettlementaccounBalanceQuery {
    /**
     * flag : 1
     * code : R00001
     * desc : 请求余额查询成功
     * content : {"data":{"returnCode":0,"returnMsg":"","msgId":"89b7228a-11b5-4357-ba2b-ad238847f5d9","accountBalance":"300","holdBalance":"0","success":true}}
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
         * data : {"returnCode":0,"returnMsg":"","msgId":"89b7228a-11b5-4357-ba2b-ad238847f5d9","accountBalance":"300","holdBalance":"0","success":true}
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
             * msgId : 89b7228a-11b5-4357-ba2b-ad238847f5d9
             * accountBalance : 300
             * holdBalance : 0
             * success : true
             */

            private int returnCode;
            private String returnMsg;
            private String msgId;
            private String accountBalance;
            private String holdBalance;
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

            public String getAccountBalance() {
                return accountBalance;
            }

            public void setAccountBalance(String accountBalance) {
                this.accountBalance = accountBalance;
            }

            public String getHoldBalance() {
                return holdBalance;
            }

            public void setHoldBalance(String holdBalance) {
                this.holdBalance = holdBalance;
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
