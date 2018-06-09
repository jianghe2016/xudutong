package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\10\17 0017.
 */

public class TianjianRequest {
    /**
     * data : [{"roleAddress":"http://app.medvision.com.cn:18081/SignDocWeb/resident/login.html?name=测试&idNo=500106198402262045&mobileTel=15310673622&homeId=411023&deviceType=android","roleType":"1"},{"roleAddress":"http://app.medvision.com.cn:18081/SignDocWeb/doctor/index.html?name=测试&idNo=500106198402262045&mobileTel=15310673622&deviceType=android","roleType":"2"},{"roleAddress":"http://app.medvision.com.cn:18081/SignDocWeb/manager/login.html?name=测试&idNo=500106198402262045&mobileTel=15310673622&deviceType=android","roleType":"3"}]
     * flag : 0
     */

    private String flag;
    private List<DataBean> data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * roleAddress : http://app.medvision.com.cn:18081/SignDocWeb/resident/login.html?name=测试&idNo=500106198402262045&mobileTel=15310673622&homeId=411023&deviceType=android
         * roleType : 1
         */

        private String roleAddress;
        private String roleType;

        public String getRoleAddress() {
            return roleAddress;
        }

        public void setRoleAddress(String roleAddress) {
            this.roleAddress = roleAddress;
        }

        public String getRoleType() {
            return roleType;
        }

        public void setRoleType(String roleType) {
            this.roleType = roleType;
        }
    }
}
