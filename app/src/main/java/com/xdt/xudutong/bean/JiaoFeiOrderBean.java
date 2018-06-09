package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2018/6/4.
 */

public class JiaoFeiOrderBean {


    /**
     * flag : true
     * code : R00000
     * content : {"orderSubtype":1,"orderType":1,"creatTime":"20180604085905","orderId":"20180604085905905540","pendingId":"15d3616b50494605843ec1360867bbc7","orderMoneyTotal":"1","orderpPlatFormType":1,"version_us":1,"pendingCreateTime":"20180604085905","status":0,"orderPayChannel":1}
     * desc : 订单生成成功！
     */
    private boolean flag;
    private String code;
    private ContentEntity content;
    private String desc;

    public void setFlag(boolean flag) {
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

    public boolean isFlag() {
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

    public class ContentEntity {
        /**
         * orderSubtype : 1
         * orderType : 1
         * creatTime : 20180604085905
         * orderId : 20180604085905905540
         * pendingId : 15d3616b50494605843ec1360867bbc7
         * orderMoneyTotal : 1
         * orderpPlatFormType : 1
         * version_us : 1
         * pendingCreateTime : 20180604085905
         * status : 0
         * orderPayChannel : 1
         */
        private int orderSubtype;
        private int orderType;
        private String creatTime;
        private String orderId;
        private String pendingId;
        private String orderMoneyTotal;
        private int orderpPlatFormType;
        private int version_us;
        private String pendingCreateTime;
        private int status;
        private int orderPayChannel;

        public void setOrderSubtype(int orderSubtype) {
            this.orderSubtype = orderSubtype;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public void setCreatTime(String creatTime) {
            this.creatTime = creatTime;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public void setPendingId(String pendingId) {
            this.pendingId = pendingId;
        }

        public void setOrderMoneyTotal(String orderMoneyTotal) {
            this.orderMoneyTotal = orderMoneyTotal;
        }

        public void setOrderpPlatFormType(int orderpPlatFormType) {
            this.orderpPlatFormType = orderpPlatFormType;
        }

        public void setVersion_us(int version_us) {
            this.version_us = version_us;
        }

        public void setPendingCreateTime(String pendingCreateTime) {
            this.pendingCreateTime = pendingCreateTime;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setOrderPayChannel(int orderPayChannel) {
            this.orderPayChannel = orderPayChannel;
        }

        public int getOrderSubtype() {
            return orderSubtype;
        }

        public int getOrderType() {
            return orderType;
        }

        public String getCreatTime() {
            return creatTime;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getPendingId() {
            return pendingId;
        }

        public String getOrderMoneyTotal() {
            return orderMoneyTotal;
        }

        public int getOrderpPlatFormType() {
            return orderpPlatFormType;
        }

        public int getVersion_us() {
            return version_us;
        }

        public String getPendingCreateTime() {
            return pendingCreateTime;
        }

        public int getStatus() {
            return status;
        }

        public int getOrderPayChannel() {
            return orderPayChannel;
        }
    }
}
