package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\11 0011.
 */

public class DsepgetMaternityInsurance {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取个人生育保险信息数据成功
     * content : {"data":[{"cardType":"身份证","cardNum":"411002196108251061","fullName":"杨孟秋","fertilityStaffType":"1","hospitalName":"外地医院(支付段自负增加10%)一级","payDate":"2000-06-01 00:00:00.0"}]}
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
             * cardNum : 411002196108251061
             * fullName : 杨孟秋
             * fertilityStaffType : 1
             * hospitalName : 外地医院(支付段自负增加10%)一级
             * payDate : 2000-06-01 00:00:00.0
             */

            private String cardType;
            private String cardNum;
            private String fullName;
            private String fertilityStaffType;
            private String hospitalName;
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

            public String getFertilityStaffType() {
                return fertilityStaffType;
            }

            public void setFertilityStaffType(String fertilityStaffType) {
                this.fertilityStaffType = fertilityStaffType;
            }

            public String getHospitalName() {
                return hospitalName;
            }

            public void setHospitalName(String hospitalName) {
                this.hospitalName = hospitalName;
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
