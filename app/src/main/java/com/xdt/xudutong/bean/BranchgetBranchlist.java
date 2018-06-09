package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\9\1 0001.
 */

public class BranchgetBranchlist {

    /**
     * flag : 1
     * code : R00001
     * desc : 获取附近网点成功
     * content : {"data":[{"branchName":"中国工商银行许昌八一路支行","branchAddress":"许昌市八一路与劳动路交叉口西南角","branchPhone":"0374-4364548","version":1,"status":1,"distance":4.9,"branchLng":"113.827319","branchLat":"34.044183"},{"branchName":"中国工商银行许昌东城支行","branchAddress":"许昌市八龙路府西雅园西门","branchPhone":"0374-3172854","version":1,"status":1,"distance":4.4,"branchLng":"113.851887","branchLat":"34.044018"},{"branchName":"中国工商银行许昌魏文路支行","branchAddress":"许昌市魏文路与八一路交叉口南侧","branchPhone":"0374-2939067","version":1,"status":1,"distance":4.5,"branchLng":"113.863143","branchLat":"34.044138"}]}
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

    public  class ContentBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public  class DataBean {
            /**
             * branchName : 中国工商银行许昌八一路支行
             * branchAddress : 许昌市八一路与劳动路交叉口西南角
             * branchPhone : 0374-4364548
             * version : 1
             * status : 1
             * distance : 4.9
             * branchLng : 113.827319
             * branchLat : 34.044183
             */

            private String branchName;
            private String branchAddress;
            private String branchPhone;
            private int version;
            private int status;
            private double distance;
            private String branchLng;
            private String branchLat;

            public String getBranchName() {
                return branchName;
            }

            public void setBranchName(String branchName) {
                this.branchName = branchName;
            }

            public String getBranchAddress() {
                return branchAddress;
            }

            public void setBranchAddress(String branchAddress) {
                this.branchAddress = branchAddress;
            }

            public String getBranchPhone() {
                return branchPhone;
            }

            public void setBranchPhone(String branchPhone) {
                this.branchPhone = branchPhone;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public String getBranchLng() {
                return branchLng;
            }

            public void setBranchLng(String branchLng) {
                this.branchLng = branchLng;
            }

            public String getBranchLat() {
                return branchLat;
            }

            public void setBranchLat(String branchLat) {
                this.branchLat = branchLat;
            }
        }
    }
}
