package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\12\22 0022.
 */

public class DxbosscheckArea {

    /**
     * flag : 1
     * code : R00001
     * desc : 电信
     * content : {"data":{"province":"河南","carrier":"河南电信"}}
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
        /**
         * data : {"province":"河南","carrier":"河南电信"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * province : 河南
             * carrier : 河南电信
             */

            private String province;
            private String carrier;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCarrier() {
                return carrier;
            }

            public void setCarrier(String carrier) {
                this.carrier = carrier;
            }
        }
    }
}
