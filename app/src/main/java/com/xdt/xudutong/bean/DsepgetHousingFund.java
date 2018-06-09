package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\10 0010.
 */

public class DsepgetHousingFund {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取公积金数据成功
     * content : {"data":[{"companyId":"0000050126","companyName":"河南升环劳务派遣有限公司","fundId":"3686","fullName":"任旭萌","cardNum":"410482198910126710","idType":"正常","startMonth":"201207"},{"companyId":"0000050089","companyName":"许昌市人力源劳务派遣合作有限公司","fundId":"2002","fullName":"任旭萌","cardNum":"410482198910126710","idType":"转出","startMonth":"201207"}]}
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
             * companyId : 0000050126
             * companyName : 河南升环劳务派遣有限公司
             * fundId : 3686
             * fullName : 任旭萌
             * cardNum : 410482198910126710
             * idType : 正常
             * startMonth : 201207
             */

            private String companyId;
            private String companyName;
            private String fundId;
            private String fullName;
            private String cardNum;
            private String idType;
            private String startMonth;

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getFundId() {
                return fundId;
            }

            public void setFundId(String fundId) {
                this.fundId = fundId;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getCardNum() {
                return cardNum;
            }

            public void setCardNum(String cardNum) {
                this.cardNum = cardNum;
            }

            public String getIdType() {
                return idType;
            }

            public void setIdType(String idType) {
                this.idType = idType;
            }

            public String getStartMonth() {
                return startMonth;
            }

            public void setStartMonth(String startMonth) {
                this.startMonth = startMonth;
            }
        }
    }
}
