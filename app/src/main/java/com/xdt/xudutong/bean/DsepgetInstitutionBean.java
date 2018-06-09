package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

public class DsepgetInstitutionBean {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取事业单位基本信息数据成功
     * content : {"data":[{"uniscId":"12411000063830044F","orgName":"许昌市郊菅庄小学","legalPerson":"魏建立","opScope":"实施小学义务教育，促进基础教育发展小学学历教育","staffNo":"0","contactName":"菅丽娜"}]}
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
             * uniscId : 12411000063830044F
             * orgName : 许昌市郊菅庄小学
             * legalPerson : 魏建立
             * opScope : 实施小学义务教育，促进基础教育发展小学学历教育
             * staffNo : 0
             * contactName : 菅丽娜
             */

            private String uniscId;
            private String orgName;
            private String legalPerson;
            private String opScope;
            private String staffNo;
            private String contactName;

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

            public String getLegalPerson() {
                return legalPerson;
            }

            public void setLegalPerson(String legalPerson) {
                this.legalPerson = legalPerson;
            }

            public String getOpScope() {
                return opScope;
            }

            public void setOpScope(String opScope) {
                this.opScope = opScope;
            }

            public String getStaffNo() {
                return staffNo;
            }

            public void setStaffNo(String staffNo) {
                this.staffNo = staffNo;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }
        }
    }
}
