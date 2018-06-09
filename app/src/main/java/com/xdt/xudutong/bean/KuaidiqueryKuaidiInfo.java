package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class KuaidiqueryKuaidiInfo {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":{"message":"ok","nu":"6000015438109","ischeck":"0","condition":"F10","com":"yunda","status":"200",
     * "data":[{"time":"2017-05-20 15:49:20","ftime":"2017-05-20 15:49:20","context":"[河南许昌公司电业局东巷分部]快件异常；
     * 备注：客户拒收","location":"河南许昌公司电业局东巷分部"},{"time":"2017-05-20 15:49:07","ftime":"2017-05-20 15:49:07",
     * "context":"[河南许昌公司电业局东巷分部]进行派件扫描；派送业务员：杨永锋；联系电话：18623800208","location":"河南许昌公司电业局东巷分部"},
     * {"time":"2017-05-20 13:27:56","ftime":"2017-05-20 13:27:56","context":"[河南许昌公司]进行快件扫描，将发往：河南许昌公司电业局东巷分部",
     * "location":"河南许昌公司"},{"time":"2017-05-20 05:03:47","ftime":"2017-05-20 05:03:47","context":"[河南郑州分拨中心]从站点发出，
     * 本次转运目的地：河南许昌公司","location":"河南郑州分拨中心"},{"time":"2017-05-20 04:37:49","ftime":"2017-05-20 04:37:49",
     * "context":"[河南郑州分拨中心]在分拨中心进行卸车扫描","location":"河南郑州分拨中心"},{"time":"2017-05-19 03:22:26",
     * "ftime":"2017-05-19 03:22:26","context":"[广东深圳公司]进行装车扫描，即将发往：河南郑州分拨中心","location":"广东深圳公司"},
     * {"time":"2017-05-18 21:26:08","ftime":"2017-05-18 21:26:08","context":"[广东深圳公司市场开发部分部]进行下级地点扫描，
     * 将发往：河南郑州分拨中心","location":"广东深圳公司市场开发部分部"},{"time":"2017-05-18 21:16:51","ftime":"2017-05-18 21:16:51","
     * context":"[广东深圳公司市场开发部分部]进行揽件扫描","location":"广东深圳公司市场开发部分部"}]}}
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
         * data : {"message":"ok","nu":"6000015438109","ischeck":"0","condition":"F10","com":"yunda","status":"200","data":[{"time":"2017-05-20 15:49:20","ftime":"2017-05-20 15:49:20","context":"[河南许昌公司电业局东巷分部]快件异常；备注：客户拒收","location":"河南许昌公司电业局东巷分部"},{"time":"2017-05-20 15:49:07","ftime":"2017-05-20 15:49:07","context":"[河南许昌公司电业局东巷分部]进行派件扫描；派送业务员：杨永锋；联系电话：18623800208","location":"河南许昌公司电业局东巷分部"},{"time":"2017-05-20 13:27:56","ftime":"2017-05-20 13:27:56","context":"[河南许昌公司]进行快件扫描，将发往：河南许昌公司电业局东巷分部","location":"河南许昌公司"},{"time":"2017-05-20 05:03:47","ftime":"2017-05-20 05:03:47","context":"[河南郑州分拨中心]从站点发出，本次转运目的地：河南许昌公司","location":"河南郑州分拨中心"},{"time":"2017-05-20 04:37:49","ftime":"2017-05-20 04:37:49","context":"[河南郑州分拨中心]在分拨中心进行卸车扫描","location":"河南郑州分拨中心"},{"time":"2017-05-19 03:22:26","ftime":"2017-05-19 03:22:26","context":"[广东深圳公司]进行装车扫描，即将发往：河南郑州分拨中心","location":"广东深圳公司"},{"time":"2017-05-18 21:26:08","ftime":"2017-05-18 21:26:08","context":"[广东深圳公司市场开发部分部]进行下级地点扫描，将发往：河南郑州分拨中心","location":"广东深圳公司市场开发部分部"},{"time":"2017-05-18 21:16:51","ftime":"2017-05-18 21:16:51","context":"[广东深圳公司市场开发部分部]进行揽件扫描","location":"广东深圳公司市场开发部分部"}]}
         */

        private DataBeanX data;

        public DataBeanX getData() {
            return data;
        }

        public void setData(DataBeanX data) {
            this.data = data;
        }

        public class DataBeanX {
            /**
             * message : ok
             * nu : 6000015438109
             * ischeck : 0
             * condition : F10
             * com : yunda
             * status : 200
             * data : [{"time":"2017-05-20 15:49:20","ftime":"2017-05-20 15:49:20","context":"[河南许昌公司电业局东巷分部]快件异常；备注：客户拒收","location":"河南许昌公司电业局东巷分部"},{"time":"2017-05-20 15:49:07","ftime":"2017-05-20 15:49:07","context":"[河南许昌公司电业局东巷分部]进行派件扫描；派送业务员：杨永锋；联系电话：18623800208","location":"河南许昌公司电业局东巷分部"},{"time":"2017-05-20 13:27:56","ftime":"2017-05-20 13:27:56","context":"[河南许昌公司]进行快件扫描，将发往：河南许昌公司电业局东巷分部","location":"河南许昌公司"},{"time":"2017-05-20 05:03:47","ftime":"2017-05-20 05:03:47","context":"[河南郑州分拨中心]从站点发出，本次转运目的地：河南许昌公司","location":"河南郑州分拨中心"},{"time":"2017-05-20 04:37:49","ftime":"2017-05-20 04:37:49","context":"[河南郑州分拨中心]在分拨中心进行卸车扫描","location":"河南郑州分拨中心"},{"time":"2017-05-19 03:22:26","ftime":"2017-05-19 03:22:26","context":"[广东深圳公司]进行装车扫描，即将发往：河南郑州分拨中心","location":"广东深圳公司"},{"time":"2017-05-18 21:26:08","ftime":"2017-05-18 21:26:08","context":"[广东深圳公司市场开发部分部]进行下级地点扫描，将发往：河南郑州分拨中心","location":"广东深圳公司市场开发部分部"},{"time":"2017-05-18 21:16:51","ftime":"2017-05-18 21:16:51","context":"[广东深圳公司市场开发部分部]进行揽件扫描","location":"广东深圳公司市场开发部分部"}]
             */

            private String message;
            private String nu;
            private String ischeck;
            private String condition;
            private String com;
            private String status;
            private List<DataBean> data;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getNu() {
                return nu;
            }

            public void setNu(String nu) {
                this.nu = nu;
            }

            public String getIscheck() {
                return ischeck;
            }

            public void setIscheck(String ischeck) {
                this.ischeck = ischeck;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public String getCom() {
                return com;
            }

            public void setCom(String com) {
                this.com = com;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public class DataBean {
                /**
                 * time : 2017-05-20 15:49:20
                 * ftime : 2017-05-20 15:49:20
                 * context : [河南许昌公司电业局东巷分部]快件异常；备注：客户拒收
                 * location : 河南许昌公司电业局东巷分部
                 */

                private String time;
                private String ftime;
                private String context;
                private String location;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getFtime() {
                    return ftime;
                }

                public void setFtime(String ftime) {
                    this.ftime = ftime;
                }

                public String getContext() {
                    return context;
                }

                public void setContext(String context) {
                    this.context = context;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }
            }
        }
    }
}
