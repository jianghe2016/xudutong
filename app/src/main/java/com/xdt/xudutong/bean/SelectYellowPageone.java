package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */

public class SelectYellowPageone {

    /**
     * flag : 1
     * code : R00001
     * desc : 查询黄页成功
     * content : {"data":[{"VAL":"交通运输局","LEVELNUM":"1","VALNUM":"00707"},{"VAL":"人防办","LEVELNUM":"1","VALNUM":"00709"},{"VAL":"公安局","LEVELNUM":"1","VALNUM":"00713"},{"VAL":"体育局","LEVELNUM":"1","VALNUM":"00711"},{"VAL":"供水公司","LEVELNUM":"1","VALNUM":"00712"},{"VAL":"农业机械管理局","LEVELNUM":"1","VALNUM":"00715"},{"VAL":"农业局","LEVELNUM":"1","VALNUM":"00714"},{"VAL":"卫计委","LEVELNUM":"1","VALNUM":"00719"},{"VAL":"人力资源和社会保障局","LEVELNUM":"1","VALNUM":"00716"},{"VAL":"发改委","LEVELNUM":"1","VALNUM":"00720"},{"VAL":"司法局","LEVELNUM":"1","VALNUM":"00721"},{"VAL":"商务局","LEVELNUM":"1","VALNUM":"00722"},{"VAL":"地震局","LEVELNUM":"1","VALNUM":"00727"},{"VAL":"安监局","LEVELNUM":"1","VALNUM":"00730"},{"VAL":"国税局","LEVELNUM":"1","VALNUM":"00724"},{"VAL":"地税局","LEVELNUM":"1","VALNUM":"00726"},{"VAL":"城管局","LEVELNUM":"1","VALNUM":"00728"},{"VAL":"国土局","LEVELNUM":"1","VALNUM":"00725"},{"VAL":"工商局","LEVELNUM":"1","VALNUM":"00733"},{"VAL":"药监局","LEVELNUM":"1","VALNUM":"00734"},{"VAL":"住房和城乡建设局","LEVELNUM":"1","VALNUM":"00736"},{"VAL":"文广新局","LEVELNUM":"1","VALNUM":"00741"},{"VAL":"教育局","LEVELNUM":"1","VALNUM":"00740"},{"VAL":"林业局","LEVELNUM":"1","VALNUM":"00744"},{"VAL":"旅游局","LEVELNUM":"1","VALNUM":"00742"},{"VAL":"无管委","LEVELNUM":"1","VALNUM":"00743"},{"VAL":"民政局","LEVELNUM":"1","VALNUM":"00747"},{"VAL":"残联","LEVELNUM":"1","VALNUM":"00746"},{"VAL":"民族宗教局","LEVELNUM":"1","VALNUM":"00748"},{"VAL":"水利局","LEVELNUM":"1","VALNUM":"00750"},{"VAL":"气象局","LEVELNUM":"1","VALNUM":"00749"},{"VAL":"质监局","LEVELNUM":"1","VALNUM":"00754"},{"VAL":"环保局","LEVELNUM":"1","VALNUM":"00753"},{"VAL":"烟草局","LEVELNUM":"1","VALNUM":"00752"},{"VAL":"畜牧局","LEVELNUM":"1","VALNUM":"00755"},{"VAL":"盐业局","LEVELNUM":"1","VALNUM":"00756"},{"VAL":"规划局","LEVELNUM":"1","VALNUM":"00762"},{"VAL":"科技局","LEVELNUM":"1","VALNUM":"00757"},{"VAL":"财政局","LEVELNUM":"1","VALNUM":"00764"}]}
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
             * VAL : 交通运输局
             * LEVELNUM : 1
             * VALNUM : 00707
             */

            private String VAL;
            private String LEVELNUM;
            private String VALNUM;

            public String getVAL() {
                return VAL;
            }

            public void setVAL(String VAL) {
                this.VAL = VAL;
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
