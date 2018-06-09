package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\7\13 0013.
 */

public class Weatherlikecity {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"areaid":"101180401","nameen":"xuchang","namecn":"许昌","city":"许昌市","districten":"xuchang","districtcn":"许昌","proven":"henan","provcn":"河南","nationen":"china","nationcn":"中国","showflag":"0"},{"areaid":"101180401","nameen":"xuchang","namecn":"许昌","city":"许昌县","districten":"xuchang","districtcn":"许昌","proven":"henan","provcn":"河南","nationen":"china","nationcn":"中国","showflag":"0"},{"areaid":"101180804","nameen":"tongxu","namecn":"通许","city":"通许县","districten":"kaifeng","districtcn":"开封","proven":"henan","provcn":"河南","nationen":"china","nationcn":"中国","showflag":"0"}]}
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
             * areaid : 101180401
             * nameen : xuchang
             * namecn : 许昌
             * city : 许昌市
             * districten : xuchang
             * districtcn : 许昌
             * proven : henan
             * provcn : 河南
             * nationen : china
             * nationcn : 中国
             * showflag : 0
             */

            private String areaid;
            private String nameen;
            private String namecn;
            private String city;
            private String districten;
            private String districtcn;
            private String proven;
            private String provcn;
            private String nationen;
            private String nationcn;
            private String showflag;

            public String getAreaid() {
                return areaid;
            }

            public void setAreaid(String areaid) {
                this.areaid = areaid;
            }

            public String getNameen() {
                return nameen;
            }

            public void setNameen(String nameen) {
                this.nameen = nameen;
            }

            public String getNamecn() {
                return namecn;
            }

            public void setNamecn(String namecn) {
                this.namecn = namecn;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistricten() {
                return districten;
            }

            public void setDistricten(String districten) {
                this.districten = districten;
            }

            public String getDistrictcn() {
                return districtcn;
            }

            public void setDistrictcn(String districtcn) {
                this.districtcn = districtcn;
            }

            public String getProven() {
                return proven;
            }

            public void setProven(String proven) {
                this.proven = proven;
            }

            public String getProvcn() {
                return provcn;
            }

            public void setProvcn(String provcn) {
                this.provcn = provcn;
            }

            public String getNationen() {
                return nationen;
            }

            public void setNationen(String nationen) {
                this.nationen = nationen;
            }

            public String getNationcn() {
                return nationcn;
            }

            public void setNationcn(String nationcn) {
                this.nationcn = nationcn;
            }

            public String getShowflag() {
                return showflag;
            }

            public void setShowflag(String showflag) {
                this.showflag = showflag;
            }
        }
    }
}
