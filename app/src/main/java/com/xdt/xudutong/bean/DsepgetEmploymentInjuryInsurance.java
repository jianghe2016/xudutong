package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\11 0011.
 */

public class DsepgetEmploymentInjuryInsurance {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取个人工伤保险信息数据成功
     * content : {"data":[{"cardType":"身份证","cardNum":"411002196604212037","fullName":"王水中","gender":"1","occDate":"2014-02-24 00:00:00.0","recDate":"2014-04-11 00:00:00.0","injuryPart":"09","payDate":"2011-11-01 00:00:00.0"}]}
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
             * cardNum : 411002196604212037
             * fullName : 王水中
             * gender : 1
             * occDate : 2014-02-24 00:00:00.0
             * recDate : 2014-04-11 00:00:00.0
             * injuryPart : 09
             * payDate : 2011-11-01 00:00:00.0
             */

            private String cardType;
            private String cardNum;
            private String fullName;
            private String gender;
            private String occDate;
            private String recDate;
            private String injuryPart;
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

            public String getOccDate() {
                return occDate;
            }

            public void setOccDate(String occDate) {
                this.occDate = occDate;
            }

            public String getRecDate() {
                return recDate;
            }

            public void setRecDate(String recDate) {
                this.recDate = recDate;
            }

            public String getInjuryPart() {
                return injuryPart;
            }

            public void setInjuryPart(String injuryPart) {
                this.injuryPart = injuryPart;
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
