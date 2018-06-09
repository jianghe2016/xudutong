package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class SocialSecuritygetXcSocialSecurity {
    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"userName":"王哲","sex":"男","national":"汉族","birthdate":"1991-10-01","userCode":"41100010717367","cardID":"411023199110015039","worktime":"2016-01-01","userpaydate":"2016-01","createdate":"2015-12","status":"正常参保","accountmonths":"4","accounttotal":"702.12","unitowe":"433.39","userowe":"182.48","owemoths":"1","owetotal":"615.87"}]}
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
             * userName : 王哲
             * sex : 男
             * national : 汉族
             * birthdate : 1991-10-01
             * userCode : 41100010717367
             * cardID : 411023199110015039
             * worktime : 2016-01-01
             * userpaydate : 2016-01
             * createdate : 2015-12
             * status : 正常参保
             * accountmonths : 4
             * accounttotal : 702.12
             * unitowe : 433.39
             * userowe : 182.48
             * owemoths : 1
             * owetotal : 615.87
             */

            private String userName;
            private String sex;
            private String national;
            private String birthdate;
            private String userCode;
            private String cardID;
            private String worktime;
            private String userpaydate;
            private String createdate;
            private String status;
            private String accountmonths;
            private String accounttotal;
            private String unitowe;
            private String userowe;
            private String owemoths;
            private String owetotal;

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getNational() {
                return national;
            }

            public void setNational(String national) {
                this.national = national;
            }

            public String getBirthdate() {
                return birthdate;
            }

            public void setBirthdate(String birthdate) {
                this.birthdate = birthdate;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getCardID() {
                return cardID;
            }

            public void setCardID(String cardID) {
                this.cardID = cardID;
            }

            public String getWorktime() {
                return worktime;
            }

            public void setWorktime(String worktime) {
                this.worktime = worktime;
            }

            public String getUserpaydate() {
                return userpaydate;
            }

            public void setUserpaydate(String userpaydate) {
                this.userpaydate = userpaydate;
            }

            public String getCreatedate() {
                return createdate;
            }

            public void setCreatedate(String createdate) {
                this.createdate = createdate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAccountmonths() {
                return accountmonths;
            }

            public void setAccountmonths(String accountmonths) {
                this.accountmonths = accountmonths;
            }

            public String getAccounttotal() {
                return accounttotal;
            }

            public void setAccounttotal(String accounttotal) {
                this.accounttotal = accounttotal;
            }

            public String getUnitowe() {
                return unitowe;
            }

            public void setUnitowe(String unitowe) {
                this.unitowe = unitowe;
            }

            public String getUserowe() {
                return userowe;
            }

            public void setUserowe(String userowe) {
                this.userowe = userowe;
            }

            public String getOwemoths() {
                return owemoths;
            }

            public void setOwemoths(String owemoths) {
                this.owemoths = owemoths;
            }

            public String getOwetotal() {
                return owetotal;
            }

            public void setOwetotal(String owetotal) {
                this.owetotal = owetotal;
            }
        }
    }
}
