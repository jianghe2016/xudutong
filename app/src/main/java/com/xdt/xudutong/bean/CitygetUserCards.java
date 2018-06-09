package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\7\5 0005.
 */

public class CitygetUserCards {

    /**
     * code : R00001
     * flag : true
     * desc : 获取成功！
     * response : {"body":[{"userid":162,"cityCardno":"4110810869731","cardType":21,"status":1}]}
     */

    private String code;
    private boolean flag;
    private String desc;
    private ResponseBean response;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public class ResponseBean {
        private List<BodyBean> body;

        public List<BodyBean> getBody() {
            return body;
        }

        public void setBody(List<BodyBean> body) {
            this.body = body;
        }

        public class BodyBean {
            /**
             * userid : 162
             * cityCardno : 4110810869731
             * cardType : 21
             * status : 1
             */

            private int userid;
            private String cityCardno;
            private int cardType;
            private int status;

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getCityCardno() {
                return cityCardno;
            }

            public void setCityCardno(String cityCardno) {
                this.cityCardno = cityCardno;
            }

            public int getCardType() {
                return cardType;
            }

            public void setCardType(int cardType) {
                this.cardType = cardType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
