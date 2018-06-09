package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */

public class SelectYellowPagetwo {

    /**
     * flag : 1
     * code : R00001
     * desc : 查询黄页成功
     * content : {"data":[{"VAL":"超限运输车辆行驶公路审批（市内或65吨以下）","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"00993"},{"VAL":"道路运输证发放","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01012"},{"VAL":"公路养路费","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01031"},{"VAL":"公路两侧建筑物控制区内埋设管线、电缆等设施或修建临时设施审批","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01030"},{"VAL":"老年免费乘车IC卡核准","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"00997"},{"VAL":"道路运输经营许可（货运站）","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01010"},{"VAL":"道路运输经营许可（货物）","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01009"},{"VAL":"穿越、跨越国、省、重要县公路修建桥梁、埋设管线等设施审批","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01005"},{"VAL":"道路运输经营许可（机动车维修经营）","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01011"},{"VAL":"船舶与水上设施检验证书核发","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01382"},{"VAL":"船员服务薄签发","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01385"},{"VAL":"船舶所有权登记","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01381"},{"VAL":"船舶国籍证书签发","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01378"},{"VAL":"在国、省、重要县公路增设平交道口审批","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01204"},{"VAL":"穿越、跨越国、省、重要县公路设立非公路标牌审批","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01203"},{"VAL":"因修建铁路、电站、通信设施、水利工程或其他建设工程需要挖掘、占用国、省、重要县公路或使公路改线审批","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01191"},{"VAL":"汽车技术等级评定","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01114"},{"VAL":"道路客运班线经营许可（市际、省际）","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01083"},{"VAL":"普通机动车驾驶员培训许可","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01405"},{"VAL":"公路运输管理费","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01032"},{"VAL":"客（货）运附加费","PARENTNUM":"00707","LEVELNUM":"2","VALNUM":"01082"}]}
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
             * VAL : 超限运输车辆行驶公路审批（市内或65吨以下）
             * PARENTNUM : 00707
             * LEVELNUM : 2
             * VALNUM : 00993
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
