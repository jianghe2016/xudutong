package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\10 0010.
 */

public class DsepgetMedicalInsurance {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取个人医疗保险信息数据成功
     * content : {"data":[{"cardType":"身份证","cardNum":"410482198910126710","fullName":"任旭萌","gender":"1","companyName":"河南升环劳务派遣有限公司","peopleType":"11","payDate":"2012-07-01 00:00:00.0","areaAddName":"411000","sino":"00279968","insureType":"31","insureStatus":"1","payAmt":"5130"}]}
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
             * cardType : 身份证
             * cardNum : 410482198910126710
             * fullName : 任旭萌
             * gender : 1
             * companyName : 河南升环劳务派遣有限公司
             * peopleType : 11
             * payDate : 2012-07-01 00:00:00.0
             * areaAddName : 411000
             * sino : 00279968
             * insureType : 31
             * insureStatus : 1
             * payAmt : 5130
             */

            private String cardType;
            private String cardNum;
            private String fullName;
            private String gender;
            private String companyName;
            private String peopleType;
            private String payDate;
            private String areaAddName;
            private String sino;
            private String insureType;
            private String insureStatus;
            private String payAmt;

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public String getCardNum() {
                return cardNum;
            }

            public void setCardNum(String cardNum) {
                this.cardNum = cardNum;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getPeopleType() {
                return peopleType;
            }

            public void setPeopleType(String peopleType) {
                this.peopleType = peopleType;
            }

            public String getPayDate() {
                return payDate;
            }

            public void setPayDate(String payDate) {
                this.payDate = payDate;
            }

            public String getAreaAddName() {
                return areaAddName;
            }

            public void setAreaAddName(String areaAddName) {
                this.areaAddName = areaAddName;
            }

            public String getSino() {
                return sino;
            }

            public void setSino(String sino) {
                this.sino = sino;
            }

            public String getInsureType() {
                return insureType;
            }

            public void setInsureType(String insureType) {
                this.insureType = insureType;
            }

            public String getInsureStatus() {
                return insureStatus;
            }

            public void setInsureStatus(String insureStatus) {
                this.insureStatus = insureStatus;
            }

            public String getPayAmt() {
                return payAmt;
            }

            public void setPayAmt(String payAmt) {
                this.payAmt = payAmt;
            }
        }
    }
}
