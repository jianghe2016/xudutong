package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\6\26 0026.
 */

public class MobilebusselectCity {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"cityid":"0372","cityname":"安阳"},{"cityid":"0373","cityname":"新乡"},{"cityid":"0374","cityname":"许昌"},{"cityid":"0377","cityname":"南阳"},{"cityid":"0378","cityname":"开封"},{"cityid":"0391","cityname":"焦作"},{"cityid":"0396","cityname":"驻马"},{"cityid":"0370","cityname":"商丘"},{"cityid":"0393","cityname":"濮阳"},{"cityid":"0392","cityname":"鹤壁"},{"cityid":"0376","cityname":"信阳"},{"cityid":"0395","cityname":"漯河"}]}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * cityid : 0372
             * cityname : 安阳
             */

            private String cityid;
            private String cityname;

            public String getCityid() {
                return cityid;
            }

            public void setCityid(String cityid) {
                this.cityid = cityid;
            }

            public String getCityname() {
                return cityname;
            }

            public void setCityname(String cityname) {
                this.cityname = cityname;
            }
        }
    }
}
