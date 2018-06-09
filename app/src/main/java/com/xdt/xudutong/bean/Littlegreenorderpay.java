package com.xdt.xudutong.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018\1\26 0026.
 */

public class Littlegreenorderpay implements Serializable{


    /**
     * flag : 1
     * code : R00001
     * content : {"returnShed":"金融大厦","bikesn":"662","deposit":"97","consume":"1分","leaseShed":"金融大厦","rentTime":"2018-05-28 09:45:08","expense":"0","returnTime":"2018-05-28 09:45:39"}
     * desc : 骑行时间不足一小时，不产生费用
     */
    private int flag;
    private String code;
    private ContentEntity content;
    private String desc;

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFlag() {
        return flag;
    }

    public String getCode() {
        return code;
    }

    public ContentEntity getContent() {
        return content;
    }

    public String getDesc() {
        return desc;
    }

    public class ContentEntity implements Serializable{
        /**
         * returnShed : 金融大厦
         * bikesn : 662
         * deposit : 97
         * consume : 1分
         * leaseShed : 金融大厦
         * rentTime : 2018-05-28 09:45:08
         * expense : 0
         * returnTime : 2018-05-28 09:45:39
         */
        private String returnShed;
        private String bikesn;
        private String deposit;
        private String consume;
        private String leaseShed;
        private String rentTime;
        private String expense;
        private String returnTime;

        public void setReturnShed(String returnShed) {
            this.returnShed = returnShed;
        }

        public void setBikesn(String bikesn) {
            this.bikesn = bikesn;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public void setConsume(String consume) {
            this.consume = consume;
        }

        public void setLeaseShed(String leaseShed) {
            this.leaseShed = leaseShed;
        }

        public void setRentTime(String rentTime) {
            this.rentTime = rentTime;
        }

        public void setExpense(String expense) {
            this.expense = expense;
        }

        public void setReturnTime(String returnTime) {
            this.returnTime = returnTime;
        }

        public String getReturnShed() {
            return returnShed;
        }

        public String getBikesn() {
            return bikesn;
        }

        public String getDeposit() {
            return deposit;
        }

        public String getConsume() {
            return consume;
        }

        public String getLeaseShed() {
            return leaseShed;
        }

        public String getRentTime() {
            return rentTime;
        }

        public String getExpense() {
            return expense;
        }

        public String getReturnTime() {
            return returnTime;
        }
    }
}
