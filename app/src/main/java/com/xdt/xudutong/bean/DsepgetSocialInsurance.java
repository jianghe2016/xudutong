package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\11 0011.
 */

public class DsepgetSocialInsurance {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取个人参保信息数据成功
     * content : {"data":[{"sino":"411022196210093031","fullName":"田慧鹏","cardNum":"411022196210093031","unitSINo":"411082000304","state":"正常","paymentStatus":"正常","insureDate":"2012-01-01 00:00:00.0","insureType":"基本医疗保险"},{"sino":"411022196210093031","fullName":"田慧鹏","cardNum":"411022196210093031","unitSINo":"411082000304","state":"正常","paymentStatus":"正常","insureDate":"2016-01-01 00:00:00.0","insureType":"工伤保险"},{"sino":"411022196210093031","fullName":"田慧鹏","cardNum":"411022196210093031","unitSINo":"411082000304","state":"正常","paymentStatus":"正常","insureDate":"2012-01-01 00:00:00.0","insureType":"失业保险"},{"sino":"411022196210093031","fullName":"田慧鹏","cardNum":"411022196210093031","unitSINo":"411082000304","state":"正常","paymentStatus":"正常","insureDate":"2012-01-01 00:00:00.0","insureType":"大病救助"},{"sino":"411022196210093031","fullName":"田慧鹏","cardNum":"411022196210093031","unitSINo":"411082000304","state":"正常","paymentStatus":"正常","insureDate":"2012-05-01 00:00:00.0","insureType":"生育保险"}]}
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
             * sino : 411022196210093031
             * fullName : 田慧鹏
             * cardNum : 411022196210093031
             * unitSINo : 411082000304
             * state : 正常
             * paymentStatus : 正常
             * insureDate : 2012-01-01 00:00:00.0
             * insureType : 基本医疗保险
             */

            private String sino;
            private String fullName;
            private String cardNum;
            private String unitSINo;
            private String state;
            private String paymentStatus;
            private String insureDate;
            private String insureType;

            public String getSino() {
                return sino;
            }

            public void setSino(String sino) {
                this.sino = sino;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getCardNum() {
                return cardNum;
            }

            public void setCardNum(String cardNum) {
                this.cardNum = cardNum;
            }

            public String getUnitSINo() {
                return unitSINo;
            }

            public void setUnitSINo(String unitSINo) {
                this.unitSINo = unitSINo;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPaymentStatus() {
                return paymentStatus;
            }

            public void setPaymentStatus(String paymentStatus) {
                this.paymentStatus = paymentStatus;
            }

            public String getInsureDate() {
                return insureDate;
            }

            public void setInsureDate(String insureDate) {
                this.insureDate = insureDate;
            }

            public String getInsureType() {
                return insureType;
            }

            public void setInsureType(String insureType) {
                this.insureType = insureType;
            }
        }
    }
}
