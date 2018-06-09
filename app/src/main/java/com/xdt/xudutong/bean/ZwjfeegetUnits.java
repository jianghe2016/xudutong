package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\7\8 0008.
 */

public class ZwjfeegetUnits {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"building_unit_id":"E3FC791C63404B87999B415E04B46EF7","unit_name":"1单元"},{"building_unit_id":"1B84C383CF3344AAB7D086CCEEAE5FFD","unit_name":"2单元"}]}
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
             * building_unit_id : E3FC791C63404B87999B415E04B46EF7
             * unit_name : 1单元
             */

            private String building_unit_id;
            private String unit_name;

            public String getBuilding_unit_id() {
                return building_unit_id;
            }

            public void setBuilding_unit_id(String building_unit_id) {
                this.building_unit_id = building_unit_id;
            }

            public String getUnit_name() {
                return unit_name;
            }

            public void setUnit_name(String unit_name) {
                this.unit_name = unit_name;
            }
        }
    }
}
