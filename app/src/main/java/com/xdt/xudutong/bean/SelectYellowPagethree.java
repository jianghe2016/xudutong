package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */

public class SelectYellowPagethree {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询黄页成功
     * content : {"data":[{"VAL":"许可依据-《中华人民共和国公路法》第四十九条","PARENTNUM":"00993","LEVELNUM":"3"},{"VAL":"申请材料-<p>1、承运单位申请<br>2、承运合同<br>3、车辆行驶证<\/p>","PARENTNUM":"00993","LEVELNUM":"3"},{"VAL":"联系电话-2968072","PARENTNUM":"00993","LEVELNUM":"3"},{"VAL":"许可期限-2个工作日","PARENTNUM":"00993","LEVELNUM":"3"},{"VAL":"收费标准-省内总运价5%","PARENTNUM":"00993","LEVELNUM":"3"},{"VAL":"许可程序-1、提出申请 2、现场勘察 3、签发通行证","PARENTNUM":"00993","LEVELNUM":"3"}]}
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
             * VAL : 许可依据-《中华人民共和国公路法》第四十九条
             * PARENTNUM : 00993
             * LEVELNUM : 3
             */

            private String VAL;
            private String PARENTNUM;
            private String LEVELNUM;

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
        }
    }
}
