package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class SearchRecharge {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功！
     * content : {"recharge":[{"opFare":"10","opDt":"2016-11-09 18:07:14","opType":"第一银行圈款","netName":"测试1"},{"opFare":"10","opDt":"2016-11-09 18:06:46","opType":"第一银行圈款","netName":"测试1"},{"opFare":"50","opDt":"2016-11-09 11:21:53","opType":"第一银行圈款","netName":"测试1"},{"opFare":"1","opDt":"2016-11-09 08:43:49","opType":"第一银行圈款","netName":"测试1"}]}
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
        private List<RechargeBean> recharge;

        public List<RechargeBean> getRecharge() {
            return recharge;
        }

        public void setRecharge(List<RechargeBean> recharge) {
            this.recharge = recharge;
        }

        public class RechargeBean {
            /**
             * opFare : 10
             * opDt : 2016-11-09 18:07:14
             * opType : 第一银行圈款
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
