package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\11 0011.
 */

public class DsepgetUnemploymentInsurance {

    /**
     * flag : 1
     * code : R00001
     * desc : 获取个人失业保险信息数据成功
     * content : {"data":[{"cardType":"身份证","cardNum":"410426197202173027","fullName":"郭素娜","gender":"2","nation":"汉族","sino":"410426197202173027","insureType":"失业","insureFeeAmt":"3619.88","bdate":"2015-04-01 00:00:00.0","edate":"2017-03-31 00:00:00.0","workDate":"1994-10-01 00:00:00.0","payDate":"1997-05-01 00:00:00.0"}]}
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
             * cardNum : 410426197202173027
             * fullName : 郭素娜
             * gender : 2
             * nation : 汉族
             * sino : 410426197202173027
             * insureType : 失业
             * insureFeeAmt : 3619.88
             * bdate : 2015-04-01 00:00:00.0
             * edate : 2017-03-31 00:00:00.0
             * workDate : 1994-10-01 00:00:00.0
             * payDate : 1997-05-01 00:00:00.0
             */

            private String cardType;
            private String cardNum;
            private String fullName;
            private String gender;
            private String nation;
            private String sino;
            private String insureType;
            private String insureFeeAmt;
            private String bdate;
            private String edate;
            private String workDate;
            private String payDate;

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

            public String getNation() {
                return nation;
            }

            public void setNation(String nation) {
                this.nation = nation;
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

            public String getInsureFeeAmt() {
                return insureFeeAmt;
            }

            public void setInsureFeeAmt(String insureFeeAmt) {
                this.insureFeeAmt = insureFeeAmt;
            }

            public String getBdate() {
                return bdate;
            }

            public void setBdate(String bdate) {
                this.bdate = bdate;
            }

            public String getEdate() {
                return edate;
            }

            public void setEdate(String edate) {
                this.edate = edate;
            }

            public String getWorkDate() {
                return workDate;
            }

            public void setWorkDate(String workDate) {
                this.workDate = workDate;
            }

            public String getPayDate() {
                return payDate;
            }

            public void setPayDate(String payDate) {
                this.payDate = payDate;
            }
        }
    }
}
