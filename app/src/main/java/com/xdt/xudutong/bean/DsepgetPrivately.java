package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\11 0011.
 */

public class DsepgetPrivately {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取个体工商户基本信息数据成功
     * content : {"data":[{"uniscId":null,"regNo":"411002604178347","traName":"许昌魏都宝华超市","industryPhy":"批发和零售业","operatorName":"侯俊锋","regCap":"10","officeAddress":"许昌市魏都区玉皇阁路东段","opScope":"预包装食品、散装食品、日用百货、瓜果蔬菜、生肉零售"}]}
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

        public  class DataBean {
            /**
             * uniscId : null
             * regNo : 411002604178347
             * traName : 许昌魏都宝华超市
             * industryPhy : 批发和零售业
             * operatorName : 侯俊锋
             * regCap : 10
             * officeAddress : 许昌市魏都区玉皇阁路东段
             * opScope : 预包装食品、散装食品、日用百货、瓜果蔬菜、生肉零售
             */

            private Object uniscId;
            private String regNo;
            private String traName;
            private String industryPhy;
            private String operatorName;
            private String regCap;
            private String officeAddress;
            private String opScope;

            public Object getUniscId() {
                return uniscId;
            }

            public void setUniscId(Object uniscId) {
                this.uniscId = uniscId;
            }

            public String getRegNo() {
                return regNo;
            }

            public void setRegNo(String regNo) {
                this.regNo = regNo;
            }

            public String getTraName() {
                return traName;
            }

            public void setTraName(String traName) {
                this.traName = traName;
            }

            public String getIndustryPhy() {
                return industryPhy;
            }

            public void setIndustryPhy(String industryPhy) {
                this.industryPhy = industryPhy;
            }

            public String getOperatorName() {
                return operatorName;
            }

            public void setOperatorName(String operatorName) {
                this.operatorName = operatorName;
            }

            public String getRegCap() {
                return regCap;
            }

            public void setRegCap(String regCap) {
                this.regCap = regCap;
            }

            public String getOfficeAddress() {
                return officeAddress;
            }

            public void setOfficeAddress(String officeAddress) {
                this.officeAddress = officeAddress;
            }

            public String getOpScope() {
                return opScope;
            }

            public void setOpScope(String opScope) {
                this.opScope = opScope;
            }
        }
    }
}
