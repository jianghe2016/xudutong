package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2018\1\5 0005.
 */
//小绿扫码结果
public class WsbikeappScanCodescanCode {


    /**
     * code : R00001
     * flag : 1
     * content : {"data":{"lockid":"27","ret":"0","bikecardsn":"3100","freetime":"30","peakvalue":"20","price":"0.5","deposit":"100","shedid":"30","bikestatetime":"20180517100520"}}
     * desc : 获取车桩信息成功,车辆可以使用
     */
    private String code;
    private int flag;
    private ContentEntity content;
    private String desc;

    public void setCode(String code) {
        this.code = code;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public int getFlag() {
        return flag;
    }

    public ContentEntity getContent() {
        return content;
    }

    public String getDesc() {
        return desc;
    }

    public class ContentEntity {
        /**
         * data : {"lockid":"27","ret":"0","bikecardsn":"3100","freetime":"30","peakvalue":"20","price":"0.5","deposit":"100","shedid":"30","bikestatetime":"20180517100520"}
         */
        private DataEntity data;

        public void setData(DataEntity data) {
            this.data = data;
        }

        public DataEntity getData() {
            return data;
        }

        public class DataEntity {
            /**
             * lockid : 27
             * ret : 0
             * bikecardsn : 3100
             * freetime : 30
             * peakvalue : 20
             * price : 0.5
             * deposit : 100
             * shedid : 30
             * bikestatetime : 20180517100520
             */
            private String lockid;
            private String ret;
            private String bikecardsn;
            private String freetime;
            private String peakvalue;
            private String price;
            private String deposit;
            private String shedid;
            private String bikestatetime;

            public void setLockid(String lockid) {
                this.lockid = lockid;
            }

            public void setRet(String ret) {
                this.ret = ret;
            }

            public void setBikecardsn(String bikecardsn) {
                this.bikecardsn = bikecardsn;
            }

            public void setFreetime(String freetime) {
                this.freetime = freetime;
            }

            public void setPeakvalue(String peakvalue) {
                this.peakvalue = peakvalue;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
            }

            public void setShedid(String shedid) {
                this.shedid = shedid;
            }

            public void setBikestatetime(String bikestatetime) {
                this.bikestatetime = bikestatetime;
            }

            public String getLockid() {
                return lockid;
            }

            public String getRet() {
                return ret;
            }

            public String getBikecardsn() {
                return bikecardsn;
            }

            public String getFreetime() {
                return freetime;
            }

            public String getPeakvalue() {
                return peakvalue;
            }

            public String getPrice() {
                return price;
            }

            public String getDeposit() {
                return deposit;
            }

            public String getShedid() {
                return shedid;
            }

            public String getBikestatetime() {
                return bikestatetime;
            }
        }
    }
}
