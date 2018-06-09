package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\7\4 0004.
 */

public class Localliferecycledetails {

    /**
     * flag : 1
     * code : R00001
     * desc : 查询生活服务成功
     * content : {"data":[{"VAL":"搬家","PARENTNUM":"1","LEVELNUM":"2","VALNUM":"2"},{"VAL":"货运物流","PARENTNUM":"1","LEVELNUM":"2","VALNUM":"3"},{"VAL":"快递","PARENTNUM":"1","LEVELNUM":"2","VALNUM":"4"},{"VAL":"生活配送","PARENTNUM":"1","LEVELNUM":"2","VALNUM":"5"}]}
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
             * VAL : 搬家
             * PARENTNUM : 1
             * LEVELNUM : 2
             * VALNUM : 2
             */

            private String VAL;
            private String PARENTNUM;
            private String LEVELNUM;
            private String VALNUM;

            public String getVAL() {
                return VAL;
            }

            public void setVAL(String VAL) {
                this.VAL = VAL;
            }

            public String getPARENTNUM() {
                return PARENTNUM;
            }

            public void setPARENTNUM(String PARENTNUM) {
                this.PARENTNUM = PARENTNUM;
            }

            public String getLEVELNUM() {
                return LEVELNUM;
            }

            public void setLEVELNUM(String LEVELNUM) {
                this.LEVELNUM = LEVELNUM;
            }

            public String getVALNUM() {
                return VALNUM;
            }

            public void setVALNUM(String VALNUM) {
                this.VALNUM = VALNUM;
            }
        }
    }
}
