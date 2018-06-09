package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2018\1\24 0024.
 */

public class PushmessageManagement {

    /**
     * orderUnread : 0
     * yonganUnread : 0
     * certcheckUnread : 0
     * medicalUnread : 0
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"id":"1655","title":"还车成功","appAlias":"yonganPush","extra":"DF276A53E179BD84EC","pushTime":"2018-01-23 00:00:00","remarks":"还车时间:2018-01-18T09:35:55.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1672","title":"还车成功","appAlias":"yonganPush","extra":"353C072F0788544FA0","pushTime":"2018-01-23 00:00:00","remarks":"还车时间:2018-01-23T14:54:20.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1670","title":"还车成功","appAlias":"yonganPush","extra":"353C072F0788544FA0","pushTime":"2018-01-23 00:00:00","remarks":"还车时间:2018-01-23T14:54:20.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1668","title":"借车成功","appAlias":"yonganPush","extra":"353C072F0788544FA0","pushTime":"2018-01-23 00:00:00","remarks":"租车时间:2018-01-23T14:49:52.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1662","title":"借车成功","appAlias":"yonganPush","extra":"353C072F0788544FA0","pushTime":"2018-01-23 00:00:00","remarks":"租车时间:2018-01-23T14:49:52.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1656","title":"还车成功","appAlias":"yonganPush","extra":"2F801951C839DDF15E","pushTime":"2018-01-23 00:00:00","remarks":"还车时间:2018-01-18T09:34:27.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1654","title":"还车成功","appAlias":"yonganPush","extra":"2F801951C839DDF15E","pushTime":"2018-01-23 00:00:00","remarks":"还车时间:2018-01-18T09:34:27.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1657","title":"还车成功","appAlias":"yonganPush","extra":"DF276A53E179BD84EC","pushTime":"2018-01-23 00:00:00","remarks":"还车时间:2018-01-18T09:35:55.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1667","title":"还车成功","appAlias":"yonganPush","extra":"353C072F0788544FA0","pushTime":"2018-01-23 00:00:00","remarks":"还车时间:2018-01-23T14:54:20.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1648","title":"还车成功","appAlias":"yonganPush","extra":"2F801951C839DDF15E","pushTime":"2018-01-22 00:00:00","remarks":"还车时间:2018-01-18T09:34:27.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""},{"id":"1649","title":"还车成功","appAlias":"yonganPush","extra":"DF276A53E179BD84EC","pushTime":"2018-01-22 00:00:00","remarks":"还车时间:2018-01-18T09:35:55.000+08:00","pushStatus":"1","pushUser":"374","pushObj":"0","readStatus":""}]}
     */

    private String orderUnread;
    private String yonganUnread;
    private String certcheckUnread;
    private String medicalUnread;
    private int flag;
    private String code;
    private String desc;
    private ContentBean content;

    public String getOrderUnread() {
        return orderUnread;
    }

    public void setOrderUnread(String orderUnread) {
        this.orderUnread = orderUnread;
    }

    public String getYonganUnread() {
        return yonganUnread;
    }

    public void setYonganUnread(String yonganUnread) {
        this.yonganUnread = yonganUnread;
    }

    public String getCertcheckUnread() {
        return certcheckUnread;
    }

    public void setCertcheckUnread(String certcheckUnread) {
        this.certcheckUnread = certcheckUnread;
    }

    public String getMedicalUnread() {
        return medicalUnread;
    }

    public void setMedicalUnread(String medicalUnread) {
        this.medicalUnread = medicalUnread;
    }

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
             * id : 1655
             * title : 还车成功
             * appAlias : yonganPush
             * extra : DF276A53E179BD84EC
             * pushTime : 2018-01-23 00:00:00
             * remarks : 还车时间:2018-01-18T09:35:55.000+08:00
             * pushStatus : 1
             * pushUser : 374
             * pushObj : 0
             * readStatus :
             */

            private String id;
            private String title;
            private String appAlias;
            private String extra;
            private String pushTime;
            private String remarks;
            private String pushStatus;
            private String pushUser;
            private String pushObj;
            private String readStatus;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getPushStatus() {
                return pushStatus;
            }

            public void setPushStatus(String pushStatus) {
                this.pushStatus = pushStatus;
            }

            public String getPushUser() {
                return pushUser;
            }

            public void setPushUser(String pushUser) {
                this.pushUser = pushUser;
            }

            public String getPushObj() {
                return pushObj;
            }

            public void setPushObj(String pushObj) {
                this.pushObj = pushObj;
            }

            public String getReadStatus() {
                return readStatus;
            }

            public void setReadStatus(String readStatus) {
                this.readStatus = readStatus;
            }
        }
    }
}
