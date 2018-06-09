package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class PeccancygetXcWeizhang {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"plateType":null,"plateState":null,"yearDate":null,"dataUpdate":null,
     * "peccancyInfo":"1. 逆向行驶 (违法代码: 1301)","peccancyDate":"2017.2.15 星期三 10:7","peccancyPlace":"紫云路与前进路交叉口",
     * "finePoints":"扣  3  分  罚款  200  元","finePointsTotal":null,"dataDeclaration":null},
     * {"plateType":null,"plateState":null,"yearDate":null,"dataUpdate":null,"peccancyInfo":"2.
     *  不按导向车道行驶 (违法代码: 1208)","peccancyDate":"2016.11.15 星期二 7:49",
     * "peccancyPlace":"徐西311国道351公里","finePoints":"扣  2  分  罚款  50  元","finePointsTotal":null,
     * "dataDeclaration":null},{"plateType":null,"plateState":null,"yearDate":null,"dataUpdate":null,"peccancyInfo":null,
     * "peccancyDate":null,"peccancyPlace":null,"finePoints":null,"finePointsTotal":"共扣:  5  分    罚款:  250  元",
     * "dataDeclaration":"提示：由于数据来源于互联网公开平台不能保证100%准确，扣分和罚款，请以官方数据为准。"}]}
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
             * plateType : null
             * plateState : null
             * yearDate : null
             * dataUpdate : null
             * peccancyInfo : 1. 逆向行驶 (违法代码: 1301)
             * peccancyDate : 2017.2.15 星期三 10:7
             * peccancyPlace : 紫云路与前进路交叉口
             * finePoints : 扣  3  分  罚款  200  元
             * finePointsTotal : null
             * dataDeclaration : null
             */

            private Object plateType;
            private Object plateState;
            private Object yearDate;
            private Object dataUpdate;
            private String peccancyInfo;
            private String peccancyDate;
            private String peccancyPlace;
            private String finePoints;
            private Object finePointsTotal;
            private Object dataDeclaration;

            public Object getPlateType() {
                return plateType;
            }

            public void setPlateType(Object plateType) {
                this.plateType = plateType;
            }

            public Object getPlateState() {
                return plateState;
            }

            public void setPlateState(Object plateState) {
                this.plateState = plateState;
            }

            public Object getYearDate() {
                return yearDate;
            }

            public void setYearDate(Object yearDate) {
                this.yearDate = yearDate;
            }

            public Object getDataUpdate() {
                return dataUpdate;
            }

            public void setDataUpdate(Object dataUpdate) {
                this.dataUpdate = dataUpdate;
            }

            public String getPeccancyInfo() {
                return peccancyInfo;
            }

            public void setPeccancyInfo(String peccancyInfo) {
                this.peccancyInfo = peccancyInfo;
            }

            public String getPeccancyDate() {
                return peccancyDate;
            }

            public void setPeccancyDate(String peccancyDate) {
                this.peccancyDate = peccancyDate;
            }

            public String getPeccancyPlace() {
                return peccancyPlace;
            }

            public void setPeccancyPlace(String peccancyPlace) {
                this.peccancyPlace = peccancyPlace;
            }

            public String getFinePoints() {
                return finePoints;
            }

            public void setFinePoints(String finePoints) {
                this.finePoints = finePoints;
            }

            public Object getFinePointsTotal() {
                return finePointsTotal;
            }

            public void setFinePointsTotal(Object finePointsTotal) {
                this.finePointsTotal = finePointsTotal;
            }

            public Object getDataDeclaration() {
                return dataDeclaration;
            }

            public void setDataDeclaration(Object dataDeclaration) {
                this.dataDeclaration = dataDeclaration;
            }
        }
    }
}
