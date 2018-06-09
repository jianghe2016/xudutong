package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */

public class ExceptionOrderBean {


    /**
     * code : R00001
     * flag : 1
     * content : {"data":[{"exceptionType":"费用超额且未还车","leasetime":"2018-05-17 10:37:43","orderno":"A35E9EDEB8B6A7EF18","bikesn":"2805"}]}
     * desc : 获取异常订单成功
     */
    private String code;
    private int flag;
    private ContentEntity content;
    private String desc;

    public void setCode(String code) {
        this.code = code;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public int getFlag() {
        return flag;
    }

    public ContentEntity getContent() {
        return content;
    }

    public String getDesc() {
        return desc;
    }

    public class ContentEntity {
        /**
         * data : [{"exceptionType":"费用超额且未还车","leasetime":"2018-05-17 10:37:43","orderno":"A35E9EDEB8B6A7EF18","bikesn":"2805"}]
         */
        private List<DataEntity> data;

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public class DataEntity {
            /**
             * exceptionType : 费用超额且未还车
             * leasetime : 2018-05-17 10:37:43
             * orderno : A35E9EDEB8B6A7EF18
             * bikesn : 2805
             */
            private String exceptionType;
            private String leasetime;
            private String orderno;
            private String bikesn;

            public void setExceptionType(String exceptionType) {
                this.exceptionType = exceptionType;
            }

            public void setLeasetime(String leasetime) {
                this.leasetime = leasetime;
            }

            public void setOrderno(String orderno) {
                this.orderno = orderno;
            }

            public void setBikesn(String bikesn) {
                this.bikesn = bikesn;
            }

            public String getExceptionType() {
                return exceptionType;
            }

            public String getLeasetime() {
                return leasetime;
            }

            public String getOrderno() {
                return orderno;
            }

            public String getBikesn() {
                return bikesn;
            }
        }
    }
}
