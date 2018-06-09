package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

public class DsepgetGovernmentUnit {

    /**
     * flag : 1
     * code : R00001
     * desc : 获取机关单位基本信息数据成功
     * content : {"data":[{"uniscId":"11411025MB0T726174","orgName":"襄城县水务局","orgAddress":"襄城县紫云大道北段","legalPerson":"魏战标","orgCategory":"组成部门","contactName":"崔亚磊","contactTel":"15937474775"}]}
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
             * uniscId : 11411025MB0T726174
             * orgName : 襄城县水务局
             * orgAddress : 襄城县紫云大道北段
             * legalPerson : 魏战标
             * orgCategory : 组成部门
             * contactName : 崔亚磊
             * contactTel : 15937474775
             */

            private String uniscId;
            private String orgName;
            private String orgAddress;
            private String legalPerson;
            private String orgCategory;
            private String contactName;
            private String contactTel;

            public String getUniscId() {
                return uniscId;
            }

            public void setUniscId(String uniscId) {
                this.uniscId = uniscId;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public String getOrgAddress() {
                return orgAddress;
            }

            public void setOrgAddress(String orgAddress) {
                this.orgAddress = orgAddress;
            }

            public String getLegalPerson() {
                return legalPerson;
            }

            public void setLegalPerson(String legalPerson) {
                this.legalPerson = legalPerson;
            }

            public String getOrgCategory() {
                return orgCategory;
            }

            public void setOrgCategory(String orgCategory) {
                this.orgCategory = orgCategory;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public String getContactTel() {
                return contactTel;
            }

            public void setContactTel(String contactTel) {
                this.contactTel = contactTel;
            }
        }
    }
}

