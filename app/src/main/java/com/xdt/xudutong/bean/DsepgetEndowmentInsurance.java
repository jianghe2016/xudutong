package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\10 0010.
 */

public class DsepgetEndowmentInsurance {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取个人养老保险信息数据成功
     * content : {"data":[{"cardNum":"411002194606292019","fullName":"杨泽峰","insureType":"企业养老保险","state":"1","firstPayDate":"1992-08-01 00:00:00.0","giveBDate":"200607","giveStatus":"1"}]}
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
             * cardNum : 411002194606292019
             * fullName : 杨泽峰
             * insureType : 企业养老保险
             * state : 1
             * firstPayDate : 1992-08-01 00:00:00.0
             * giveBDate : 200607
             * giveStatus : 1
             */

            private String cardNum;
            private String fullName;
            private String insureType;
            private String state;
            private String firstPayDate;
            private String giveBDate;
            private String giveStatus;

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

            public String getInsureType() {
                return insureType;
            }

            public void setInsureType(String insureType) {
                this.insureType = insureType;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getFirstPayDate() {
                return firstPayDate;
            }

            public void setFirstPayDate(String firstPayDate) {
                this.firstPayDate = firstPayDate;
            }

            public String getGiveBDate() {
                return giveBDate;
            }

            public void setGiveBDate(String giveBDate) {
                this.giveBDate = giveBDate;
            }

            public String getGiveStatus() {
                return giveStatus;
            }

            public void setGiveStatus(String giveStatus) {
                this.giveStatus = giveStatus;
            }
        }
    }
}
