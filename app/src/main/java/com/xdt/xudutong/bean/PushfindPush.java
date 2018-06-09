package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\9\27 0027.
 */

public class PushfindPush {
    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"id":62,"title":"咨询测试","appAlias":"all","extra":"https://www.baidu.com/","pushTime":"2017-09-21 14:54:13","remarks":"411002199501251010","pushStatus":1,"pushUser":"15538998832","pushObj":1}]}
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

    public static class ContentBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 62
             * title : 咨询测试
             * appAlias : all
             * extra : https://www.baidu.com/
             * pushTime : 2017-09-21 14:54:13
             * remarks : 411002199501251010
             * pushStatus : 1
             * pushUser : 15538998832
             * pushObj : 1
             */

            private int id;
            private String title;
            private String appAlias;
            private String extra;
            private String pushTime;
            private String remarks;
            private int pushStatus;
            private String pushUser;
            private int pushObj;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAppAlias() {
                return appAlias;
            }

            public void setAppAlias(String appAlias) {
                this.appAlias = appAlias;
            }

            public String getExtra() {
                return extra;
            }

            public void setExtra(String extra) {
                this.extra = extra;
            }

            public String getPushTime() {
                return pushTime;
            }

            public void setPushTime(String pushTime) {
                this.pushTime = pushTime;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public int getPushStatus() {
                return pushStatus;
            }

            public void setPushStatus(int pushStatus) {
                this.pushStatus = pushStatus;
            }

            public String getPushUser() {
                return pushUser;
            }

            public void setPushUser(String pushUser) {
                this.pushUser = pushUser;
            }

            public int getPushObj() {
                return pushObj;
            }

            public void setPushObj(int pushObj) {
                this.pushObj = pushObj;
            }
        }
    }
}
