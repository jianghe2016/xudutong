package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class SearchSales {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功！
     * content : {"sales":[{"opFare":"20000","opDt":"2016-11-11 16:57:01","opType":"城市通中心消费","netName":"测试1"},{"opFare":"5400","opDt":"2016-11-11 15:57:50","opType":"城市通中心消费","netName":"测试1"},{"opFare":"5000","opDt":"2016-11-11 15:52:45","opType":"城市通中心消费","netName":"测试1"},{"opFare":"40000","opDt":"2016-11-11 15:29:44","opType":"城市通中心消费","netName":"测试1"}]}
     * sessionId : null
     */

    private int flag;
    private String code;
    private String desc;
    private ContentBean content;
    private Object sessionId;

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

    public Object getSessionId() {
        return sessionId;
    }

    public void setSessionId(Object sessionId) {
        this.sessionId = sessionId;
    }

    public class ContentBean {
        private List<SalesBean> sales;

        public List<SalesBean> getSales() {
            return sales;
        }

        public void setSales(List<SalesBean> sales) {
            this.sales = sales;
        }

        public class SalesBean {
            /**
             * opFare : 20000
             * opDt : 2016-11-11 16:57:01
             * opType : 城市通中心消费
             * netName : 测试1
             */

            private String opFare;
            private String opDt;
            private String opType;
            private String netName;

            public String getOpFare() {
                return opFare;
            }

            public void setOpFare(String opFare) {
                this.opFare = opFare;
            }

            public String getOpDt() {
                return opDt;
            }

            public void setOpDt(String opDt) {
                this.opDt = opDt;
            }

            public String getOpType() {
                return opType;
            }

            public void setOpType(String opType) {
                this.opType = opType;
            }

            public String getNetName() {
                return netName;
            }

            public void setNetName(String netName) {
                this.netName = netName;
            }
        }
    }
}
