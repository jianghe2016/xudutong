package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\9\1 0001.
 */

public class Wsbikemakecardrecord {

    /**
     * flag : 1
     * code : R00001
     * desc : 获取数据成功
     * content : {"data":[{"sn":"1","bikeno":"XCB005630","borrowtime":"20171019132816","borrowstation":"金融大厦","returntime":"20171019133303",
     * "returnstation":"电子商务产业园","manual":"否","cost":"","tradedate":""},{"sn":"2","bikeno":"XCB006370","borrowtime":"20171019133822",
     * "borrowstation":"电子商务产业园","returntime":"20171019134538","returnstation":"金融大厦","manual":"否","cost":"","tradedate":""},
     * {"sn":"3","bikeno":"XCB000806","borrowtime":"20171019140458","borrowstation":"金融大厦","returntime":"20171019140858",
     * "returnstation":"电子商务产业园","manual":"否","cost":"","tradedate":""},{"sn":"4","bikeno":"XCB005927","borrowtime":"20171019140947",
     * "borrowstation":"电子商务产业园","returntime":"20171019141512","returnstation":"金融大厦","manual":"否","cost":"","tradedate":""}]}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * sn : 1
             * bikeno : XCB005630
             * borrowtime : 20171019132816
             * borrowstation : 金融大厦
             * returntime : 20171019133303
             * returnstation : 电子商务产业园
             * manual : 否
             * cost :
             * tradedate :
             */

            private String sn;
            private String bikeno;
            private String borrowtime;
            private String borrowstation;
            private String returntime;
            private String returnstation;
            private String manual;
            private String cost;
            private String tradedate;

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getBikeno() {
                return bikeno;
            }

            public void setBikeno(String bikeno) {
                this.bikeno = bikeno;
            }

            public String getBorrowtime() {
                return borrowtime;
            }

            public void setBorrowtime(String borrowtime) {
                this.borrowtime = borrowtime;
            }

            public String getBorrowstation() {
                return borrowstation;
            }

            public void setBorrowstation(String borrowstation) {
                this.borrowstation = borrowstation;
            }

            public String getReturntime() {
                return returntime;
            }

            public void setReturntime(String returntime) {
                this.returntime = returntime;
            }

            public String getReturnstation() {
                return returnstation;
            }

            public void setReturnstation(String returnstation) {
                this.returnstation = returnstation;
            }

            public String getManual() {
                return manual;
            }

            public void setManual(String manual) {
                this.manual = manual;
            }

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }

            public String getTradedate() {
                return tradedate;
            }

            public void setTradedate(String tradedate) {
                this.tradedate = tradedate;
            }
        }
    }
}
