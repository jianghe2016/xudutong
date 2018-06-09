package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class PubfundgetXcPubfund {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"unitCode":"0000050126","unitName":"河南升环劳务派遣有限公司","userCode":"3686","userName":"任旭萌","cardID":"410482198910126710","payMonth":"718.2","balance":"16592.39","paydate":"201703","status":"正常"}]}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * unitCode : 0000050126
             * unitName : 河南升环劳务派遣有限公司
             * userCode : 3686
             * userName : 任旭萌
             * cardID : 410482198910126710
             * payMonth : 718.2
             * balance : 16592.39
             * paydate : 201703
             * status : 正常
             */

            private String unitCode;
            private String unitName;
            private String userCode;
            private String userName;
            private String cardID;
            private String payMonth;
            private String balance;
            private String paydate;
            private String status;

            public String getUnitCode() {
                return unitCode;
            }

            public void setUnitCode(String unitCode) {
                this.unitCode = unitCode;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getCardID() {
                return cardID;
            }

            public void setCardID(String cardID) {
                this.cardID = cardID;
            }

            public String getPayMonth() {
                return payMonth;
            }

            public void setPayMonth(String payMonth) {
                this.payMonth = payMonth;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getPaydate() {
                return paydate;
            }

            public void setPaydate(String paydate) {
                this.paydate = paydate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
