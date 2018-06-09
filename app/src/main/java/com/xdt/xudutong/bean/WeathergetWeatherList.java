package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\7\12 0012.
 */

public class WeathergetWeatherList {
    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":{"wendu":"35","ganmao":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","cityQuality":"北京空气质量指数","qualityNumber":"91 良","qualityDate":"2017年07月12日 16:00发布","forecast":[{"fengxiang":"南风","fengli":"微风级","high":"高温 36℃","type":"多云","low":"低温 27℃","date":"12日星期三"},{"fengxiang":"南风","fengli":"微风级","high":"高温 37℃","type":"多云","low":"低温 26℃","date":"13日星期四"},{"fengxiang":"北风","fengli":"微风级","high":"高温 35℃","type":"雷阵雨","low":"低温 25℃","date":"14日星期五"},{"fengxiang":"南风","fengli":"微风级","high":"高温 32℃","type":"阴","low":"低温 25℃","date":"15日星期六"},{"fengxiang":"南风","fengli":"微风级","high":"高温 33℃","type":"多云","low":"低温 26℃","date":"16日星期天"}],"yesterday":{"fl":"微风","fx":"南风","high":"高温 37℃","type":"多云","low":"低温 26℃","date":"11日星期二"},"city":"朝阳"}}
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
         * data : {"wendu":"35","ganmao":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。","cityQuality":"北京空气质量指数","qualityNumber":"91 良","qualityDate":"2017年07月12日 16:00发布","forecast":[{"fengxiang":"南风","fengli":"微风级","high":"高温 36℃","type":"多云","low":"低温 27℃","date":"12日星期三"},{"fengxiang":"南风","fengli":"微风级","high":"高温 37℃","type":"多云","low":"低温 26℃","date":"13日星期四"},{"fengxiang":"北风","fengli":"微风级","high":"高温 35℃","type":"雷阵雨","low":"低温 25℃","date":"14日星期五"},{"fengxiang":"南风","fengli":"微风级","high":"高温 32℃","type":"阴","low":"低温 25℃","date":"15日星期六"},{"fengxiang":"南风","fengli":"微风级","high":"高温 33℃","type":"多云","low":"低温 26℃","date":"16日星期天"}],"yesterday":{"fl":"微风","fx":"南风","high":"高温 37℃","type":"多云","low":"低温 26℃","date":"11日星期二"},"city":"朝阳"}
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
             * wendu : 35
             * ganmao : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
             * cityQuality : 北京空气质量指数
             * qualityNumber : 91 良
             * qualityDate : 2017年07月12日 16:00发布
             * forecast : [{"fengxiang":"南风","fengli":"微风级","high":"高温 36℃","type":"多云","low":"低温 27℃","date":"12日星期三"},{"fengxiang":"南风","fengli":"微风级","high":"高温 37℃","type":"多云","low":"低温 26℃","date":"13日星期四"},{"fengxiang":"北风","fengli":"微风级","high":"高温 35℃","type":"雷阵雨","low":"低温 25℃","date":"14日星期五"},{"fengxiang":"南风","fengli":"微风级","high":"高温 32℃","type":"阴","low":"低温 25℃","date":"15日星期六"},{"fengxiang":"南风","fengli":"微风级","high":"高温 33℃","type":"多云","low":"低温 26℃","date":"16日星期天"}]
             * yesterday : {"fl":"微风","fx":"南风","high":"高温 37℃","type":"多云","low":"低温 26℃","date":"11日星期二"}
             * city : 朝阳
             */

            private String wendu;
            private String ganmao;
            private String cityQuality;
            private String qualityNumber;
            private String qualityDate;
            private YesterdayBean yesterday;
            private String city;
            private List<ForecastBean> forecast;

            public String getWendu() {
                return wendu;
            }

            public void setWendu(String wendu) {
                this.wendu = wendu;
            }

            public String getGanmao() {
                return ganmao;
            }

            public void setGanmao(String ganmao) {
                this.ganmao = ganmao;
            }

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

            public YesterdayBean getYesterday() {
                return yesterday;
            }

            public void setYesterday(YesterdayBean yesterday) {
                this.yesterday = yesterday;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public List<ForecastBean> getForecast() {
                return forecast;
            }

            public void setForecast(List<ForecastBean> forecast) {
                this.forecast = forecast;
            }

            public  class YesterdayBean {
                /**
                 * fl : 微风
                 * fx : 南风
                 * high : 高温 37℃
                 * type : 多云
                 * low : 低温 26℃
                 * date : 11日星期二
                 */

                private String fl;
                private String fx;
                private String high;
                private String type;
                private String low;
                private String date;

                public String getFl() {
                    return fl;
                }

                public void setFl(String fl) {
                    this.fl = fl;
                }

                public String getFx() {
                    return fx;
                }

                public void setFx(String fx) {
                    this.fx = fx;
                }

                public String getHigh() {
                    return high;
                }

                public void setHigh(String high) {
                    this.high = high;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getLow() {
                    return low;
                }

                public void setLow(String low) {
                    this.low = low;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }
            }

            public class ForecastBean {
                /**
                 * fengxiang : 南风
                 * fengli : 微风级
                 * high : 高温 36℃
                 * type : 多云
                 * low : 低温 27℃
                 * date : 12日星期三
                 */

                private String fengxiang;
                private String fengli;
                private String high;
                private String type;
                private String low;
                private String date;

                public String getFengxiang() {
                    return fengxiang;
                }

                public void setFengxiang(String fengxiang) {
                    this.fengxiang = fengxiang;
                }

                public String getFengli() {
                    return fengli;
                }

                public void setFengli(String fengli) {
                    this.fengli = fengli;
                }

                public String getHigh() {
                    return high;
                }

                public void setHigh(String high) {
                    this.high = high;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getLow() {
                    return low;
                }

                public void setLow(String low) {
                    this.low = low;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }
            }
        }
    }
}
