package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\10 0010.
 */

public class DsepgetEnterprise {

    /**
     * flag : 1
     * code : R00001
     * desc : 获取企业基本信息数据成功
     * content : {"data":[{"uniscId":"91411000MA3XBRQE6J","regNo":"411093000022867","companyName":"许昌市速腾网吧","legalPerson":"徐云婷","regCap":"50","regCapName":"人民币元","regOrgName":"许昌市工商行政管理局东城分局","regStatus":"正常","opScope":"互联网上网服务；预包装食品销售"}]}
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
             * uniscId : 91411000MA3XBRQE6J
             * regNo : 411093000022867
             * companyName : 许昌市速腾网吧
             * legalPerson : 徐云婷
             * regCap : 50
             * regCapName : 人民币元
             * regOrgName : 许昌市工商行政管理局东城分局
             * regStatus : 正常
             * opScope : 互联网上网服务；预包装食品销售
             */

            private String uniscId;
            private String regNo;
            private String companyName;
            private String legalPerson;
            private String regCap;
            private String regCapName;
            private String regOrgName;
            private String regStatus;
            private String opScope;

            public String getUniscId() {
                return uniscId;
            }

            public void setUniscId(String uniscId) {
                this.uniscId = uniscId;
            }

            public String getRegNo() {
                return regNo;
            }

            public void setRegNo(String regNo) {
                this.regNo = regNo;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getLegalPerson() {
                return legalPerson;
            }

            public void setLegalPerson(String legalPerson) {
                this.legalPerson = legalPerson;
            }

            public String getRegCap() {
                return regCap;
            }

            public void setRegCap(String regCap) {
                this.regCap = regCap;
            }

            public String getRegCapName() {
                return regCapName;
            }

            public void setRegCapName(String regCapName) {
                this.regCapName = regCapName;
            }

            public String getRegOrgName() {
                return regOrgName;
            }

            public void setRegOrgName(String regOrgName) {
                this.regOrgName = regOrgName;
            }

            public String getRegStatus() {
                return regStatus;
            }

            public void setRegStatus(String regStatus) {
                this.regStatus = regStatus;
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
