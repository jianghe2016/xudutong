package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\7\13 0013.
 */

public class WeathergetAirQuality {
    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":{"cityQuality":"北京空气质量指数","qualityNumber":"96 良","qualityDate":"2017年07月13日 17:00发布"}}
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
        /**
         * data : {"cityQuality":"北京空气质量指数","qualityNumber":"96 良","qualityDate":"2017年07月13日 17:00发布"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * cityQuality : 北京空气质量指数
             * qualityNumber : 96 良
             * qualityDate : 2017年07月13日 17:00发布
             */

            private String cityQuality;
            private String qualityNumber;
            private String qualityDate;

            public String getCityQuality() {
                return cityQuality;
            }

            public void setCityQuality(String cityQuality) {
                this.cityQuality = cityQuality;
            }

            public String getQualityNumber() {
                return qualityNumber;
            }

            public void setQualityNumber(String qualityNumber) {
                this.qualityNumber = qualityNumber;
            }

            public String getQualityDate() {
                return qualityDate;
            }

            public void setQualityDate(String qualityDate) {
                this.qualityDate = qualityDate;
            }
        }
    }
}
