package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class TrainTicketgetTrainTicket {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"train_no":null,"station_train_code":"G1318","start_station_telecode":null,"start_station_name":null,"end_station_telecode":null,
     * "end_station_name":null,"from_station_telecode":null,"from_station_name":"成都东","to_station_telecode":null,"to_station_name":"长沙南",
     * "start_time":"07:54","arrive_time":"19:01","day_difference":null,"train_class_name":null,"lishi":"11:07","canWebBuy":null,
     * "lishiValue":null,"yp_info":null,"control_train_day":null,"start_train_date":null,"seat_feature":null,"yp_ex":null,"train_seat_feature":null,
     * "train_type_code":null,"start_province_code":null,"start_city_code":"ICW","end_province_code":null,"end_city_code":"CWQ","seat_types":null,
     * "location_code":null,"from_station_no":null,"to_station_no":null,"control_day":null,"sale_time":null,"is_support_card":null,
     * "controlled_train_flag":null,"controlled_train_message":null,"gg_num":"","gr_num":null,"qt_num":null,"rw_num":"","rz_num":null,"tz_num":null,
     * "wz_num":"","yb_num":null,"yw_num":"","yz_num":"","ze_num":"有","zy_num":"无","swz_num":"4"},{"train_no":null,"station_train_code":"K578",
     * "start_station_telecode":null,"start_station_name":null,"end_station_telecode":null,"end_station_name":null,"from_station_telecode":null,
     * "from_station_name":"成都东","to_station_telecode":null,"to_station_name":"长沙","start_time":"08:50","arrive_time":"05:58","day_difference":null,
     * "train_class_name":null,"lishi":"21:08","canWebBuy":null,"lishiValue":null,"yp_info":null,"control_train_day":null,"start_train_date":null,
     * "seat_feature":null,"yp_ex":null,"train_seat_feature":null,"train_type_code":null,"start_province_code":null,"start_city_code":"ICW",
     * "end_province_code":null,"end_city_code":"CSQ","seat_types":null,"location_code":null,"from_station_no":null,"to_station_no":null,
     * "control_day":null,"sale_time":null,"is_support_card":null,"controlled_train_flag":null,"controlled_train_message":null,"gg_num":"",
     * "gr_num":null,"qt_num":null,"rw_num":"15","rz_num":null,"tz_num":null,"wz_num":"有","yb_num":null,"yw_num":"有","yz_num":"有","ze_num":"",
     * "zy_num":"","swz_num":""},{"train_no":null,"station_train_code":"G314","start_station_telecode":null,"start_station_name":null,"
     * end_station_telecode":null,"end_station_name":null,"from_station_telecode":null,"from_station_name":"成都东","to_station_telecode":null,
     * "to_station_name":"长沙南","start_time":"09:17","arrive_time":"20:38","day_difference":null,"train_class_name":null,"lishi":"11:21",
     * "canWebBuy":null,"lishiValue":null,"yp_info":null,"control_train_day":null,"start_train_date":null,"seat_feature":null,"yp_ex":null,
     * "train_seat_feature":null,"train_type_code":null,"start_province_code":null,"start_city_code":"ICW","end_province_code":null,
     * "end_city_code":"CWQ","seat_types":null,"location_code":null,"from_station_no":null,"to_station_no":null,"control_day":null,
     * "sale_time":null,"is_support_card":null,"controlled_train_flag":null,"controlled_train_message":null,"gg_num":"","gr_num":null,
     * "qt_num":null,"rw_num":"","rz_num":null,"tz_num":null,"wz_num":"","yb_num":null,"yw_num":"","yz_num":"","ze_num":"有","
     * zy_num":"无","swz_num":"2"},{"train_no":null,"station_train_code":"K504","start_station_telecode":null,"start_station_name":null,
     * "end_station_telecode":null,"end_station_name":null,"from_station_telecode":null,"from_station_name":"成都东","to_station_telecode":null,
     * "to_station_name":"长沙","start_time":"12:01","arrive_time":"06:22","day_difference":null,"train_class_name":null,"lishi":"18:21",
     * "canWebBuy":null,"lishiValue":null,"yp_info":null,"control_train_day":null,"start_train_date":null,"seat_feature":null,"yp_ex":null,
     * "train_seat_feature":null,"train_type_code":null,"start_province_code":null,"start_city_code":"ICW","end_province_code":null,
     * "end_city_code":"CSQ","seat_types":null,"location_code":null,"from_station_no":null,"to_station_no":null,"control_day":null,"sale_time":null,"is_support_card":null,"controlled_train_flag":null,"controlled_train_message":null,"gg_num":"","gr_num":null,"qt_num":null,"rw_num":"5","rz_num":null,"tz_num":null,"wz_num":"有","yb_num":null,"yw_num":"有","yz_num":"有","ze_num":"","zy_num":"","swz_num":""},{"train_no":null,"station_train_code":"K788","start_station_telecode":null,"start_station_name":null,"end_station_telecode":null,"end_station_name":null,"from_station_telecode":null,"from_station_name":"成都东","to_station_telecode":null,"to_station_name":"长沙","start_time":"15:23","arrive_time":"12:55","day_difference":null,"train_class_name":null,"lishi":"21:32","canWebBuy":null,"lishiValue":null,"yp_info":null,"control_train_day":null,"start_train_date":null,"seat_feature":null,"yp_ex":null,"train_seat_feature":null,"train_type_code":null,"start_province_code":null,"start_city_code":"ICW","end_province_code":null,"end_city_code":"CSQ","seat_types":null,"location_code":null,"from_station_no":null,"to_station_no":null,"control_day":null,"sale_time":null,"is_support_card":null,"controlled_train_flag":null,"controlled_train_message":null,"gg_num":"","gr_num":null,"qt_num":null,"rw_num":"无","rz_num":null,"tz_num":null,"wz_num":"有","yb_num":null,"yw_num":"有","yz_num":"有","ze_num":"","zy_num":"","swz_num":""},{"train_no":null,"station_train_code":"Z124","start_station_telecode":null,"start_station_name":null,"end_station_telecode":null,"end_station_name":null,"from_station_telecode":null,"from_station_name":"成都","to_station_telecode":null,"to_station_name":"长沙","start_time":"17:20","arrive_time":"10:55","day_difference":null,"train_class_name":null,"lishi":"17:35","canWebBuy":null,"lishiValue":null,"yp_info":null,"control_train_day":null,"start_train_date":null,"seat_feature":null,"yp_ex":null,"train_seat_feature":null,"train_type_code":null,"start_province_code":null,"start_city_code":"CDW","end_province_code":null,"end_city_code":"CSQ","seat_types":null,"location_code":null,"from_station_no":null,"to_station_no":null,"control_day":null,"sale_time":null,"is_support_card":null,"controlled_train_flag":null,"controlled_train_message":null,"gg_num":"","gr_num":null,"qt_num":null,"rw_num":"16","rz_num":null,"tz_num":null,"wz_num":"有","yb_num":null,"yw_num":"有","yz_num":"有","ze_num":"","zy_num":"","swz_num":""}]}
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
             * train_no : null
             * station_train_code : G1318
             * start_station_telecode : null
             * start_station_name : null
             * end_station_telecode : null
             * end_station_name : null
             * from_station_telecode : null
             * from_station_name : 成都东
             * to_station_telecode : null
             * to_station_name : 长沙南
             * start_time : 07:54
             * arrive_time : 19:01
             * day_difference : null
             * train_class_name : null
             * lishi : 11:07
             * canWebBuy : null
             * lishiValue : null
             * yp_info : null
             * control_train_day : null
             * start_train_date : null
             * seat_feature : null
             * yp_ex : null
             * train_seat_feature : null
             * train_type_code : null
             * start_province_code : null
             * start_city_code : ICW
             * end_province_code : null
             * end_city_code : CWQ
             * seat_types : null
             * location_code : null
             * from_station_no : null
             * to_station_no : null
             * control_day : null
             * sale_time : null
             * is_support_card : null
             * controlled_train_flag : null
             * controlled_train_message : null
             * gg_num :
             * gr_num : null
             * qt_num : null
             * rw_num :
             * rz_num : null
             * tz_num : null
             * wz_num :
             * yb_num : null
             * yw_num :
             * yz_num :
             * ze_num : 有
             * zy_num : 无
             * swz_num : 4
             */

            private Object train_no;
            private String station_train_code;
            private Object start_station_telecode;
            private Object start_station_name;
            private Object end_station_telecode;
            private Object end_station_name;
            private Object from_station_telecode;
            private String from_station_name;
            private Object to_station_telecode;
            private String to_station_name;
            private String start_time;
            private String arrive_time;
            private Object day_difference;
            private Object train_class_name;
            private String lishi;
            private Object canWebBuy;
            private Object lishiValue;
            private Object yp_info;
            private Object control_train_day;
            private Object start_train_date;
            private Object seat_feature;
            private Object yp_ex;
            private Object train_seat_feature;
            private Object train_type_code;
            private Object start_province_code;
            private String start_city_code;
            private Object end_province_code;
            private String end_city_code;
            private Object seat_types;
            private Object location_code;
            private Object from_station_no;
            private Object to_station_no;
            private Object control_day;
            private Object sale_time;
            private Object is_support_card;
            private Object controlled_train_flag;
            private Object controlled_train_message;
            private String gg_num;
            private Object gr_num;
            private Object qt_num;
            private String rw_num;
            private Object rz_num;
            private Object tz_num;
            private String wz_num;
            private Object yb_num;
            private String yw_num;
            private String yz_num;
            private String ze_num;
            private String zy_num;
            private String swz_num;

            public Object getTrain_no() {
                return train_no;
            }

            public void setTrain_no(Object train_no) {
                this.train_no = train_no;
            }

            public String getStation_train_code() {
                return station_train_code;
            }

            public void setStation_train_code(String station_train_code) {
                this.station_train_code = station_train_code;
            }

            public Object getStart_station_telecode() {
                return start_station_telecode;
            }

            public void setStart_station_telecode(Object start_station_telecode) {
                this.start_station_telecode = start_station_telecode;
            }

            public Object getStart_station_name() {
                return start_station_name;
            }

            public void setStart_station_name(Object start_station_name) {
                this.start_station_name = start_station_name;
            }

            public Object getEnd_station_telecode() {
                return end_station_telecode;
            }

            public void setEnd_station_telecode(Object end_station_telecode) {
                this.end_station_telecode = end_station_telecode;
            }

            public Object getEnd_station_name() {
                return end_station_name;
            }

            public void setEnd_station_name(Object end_station_name) {
                this.end_station_name = end_station_name;
            }

            public Object getFrom_station_telecode() {
                return from_station_telecode;
            }

            public void setFrom_station_telecode(Object from_station_telecode) {
                this.from_station_telecode = from_station_telecode;
            }

            public String getFrom_station_name() {
                return from_station_name;
            }

            public void setFrom_station_name(String from_station_name) {
                this.from_station_name = from_station_name;
            }

            public Object getTo_station_telecode() {
                return to_station_telecode;
            }

            public void setTo_station_telecode(Object to_station_telecode) {
                this.to_station_telecode = to_station_telecode;
            }

            public String getTo_station_name() {
                return to_station_name;
            }

            public void setTo_station_name(String to_station_name) {
                this.to_station_name = to_station_name;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getArrive_time() {
                return arrive_time;
            }

            public void setArrive_time(String arrive_time) {
                this.arrive_time = arrive_time;
            }

            public Object getDay_difference() {
                return day_difference;
            }

            public void setDay_difference(Object day_difference) {
                this.day_difference = day_difference;
            }

            public Object getTrain_class_name() {
                return train_class_name;
            }

            public void setTrain_class_name(Object train_class_name) {
                this.train_class_name = train_class_name;
            }

            public String getLishi() {
                return lishi;
            }

            public void setLishi(String lishi) {
                this.lishi = lishi;
            }

            public Object getCanWebBuy() {
                return canWebBuy;
            }

            public void setCanWebBuy(Object canWebBuy) {
                this.canWebBuy = canWebBuy;
            }

            public Object getLishiValue() {
                return lishiValue;
            }

            public void setLishiValue(Object lishiValue) {
                this.lishiValue = lishiValue;
            }

            public Object getYp_info() {
                return yp_info;
            }

            public void setYp_info(Object yp_info) {
                this.yp_info = yp_info;
            }

            public Object getControl_train_day() {
                return control_train_day;
            }

            public void setControl_train_day(Object control_train_day) {
                this.control_train_day = control_train_day;
            }

            public Object getStart_train_date() {
                return start_train_date;
            }

            public void setStart_train_date(Object start_train_date) {
                this.start_train_date = start_train_date;
            }

            public Object getSeat_feature() {
                return seat_feature;
            }

            public void setSeat_feature(Object seat_feature) {
                this.seat_feature = seat_feature;
            }

            public Object getYp_ex() {
                return yp_ex;
            }

            public void setYp_ex(Object yp_ex) {
                this.yp_ex = yp_ex;
            }

            public Object getTrain_seat_feature() {
                return train_seat_feature;
            }

            public void setTrain_seat_feature(Object train_seat_feature) {
                this.train_seat_feature = train_seat_feature;
            }

            public Object getTrain_type_code() {
                return train_type_code;
            }

            public void setTrain_type_code(Object train_type_code) {
                this.train_type_code = train_type_code;
            }

            public Object getStart_province_code() {
                return start_province_code;
            }

            public void setStart_province_code(Object start_province_code) {
                this.start_province_code = start_province_code;
            }

            public String getStart_city_code() {
                return start_city_code;
            }

            public void setStart_city_code(String start_city_code) {
                this.start_city_code = start_city_code;
            }

            public Object getEnd_province_code() {
                return end_province_code;
            }

            public void setEnd_province_code(Object end_province_code) {
                this.end_province_code = end_province_code;
            }

            public String getEnd_city_code() {
                return end_city_code;
            }

            public void setEnd_city_code(String end_city_code) {
                this.end_city_code = end_city_code;
            }

            public Object getSeat_types() {
                return seat_types;
            }

            public void setSeat_types(Object seat_types) {
                this.seat_types = seat_types;
            }

            public Object getLocation_code() {
                return location_code;
            }

            public void setLocation_code(Object location_code) {
                this.location_code = location_code;
            }

            public Object getFrom_station_no() {
                return from_station_no;
            }

            public void setFrom_station_no(Object from_station_no) {
                this.from_station_no = from_station_no;
            }

            public Object getTo_station_no() {
                return to_station_no;
            }

            public void setTo_station_no(Object to_station_no) {
                this.to_station_no = to_station_no;
            }

            public Object getControl_day() {
                return control_day;
            }

            public void setControl_day(Object control_day) {
                this.control_day = control_day;
            }

            public Object getSale_time() {
                return sale_time;
            }

            public void setSale_time(Object sale_time) {
                this.sale_time = sale_time;
            }

            public Object getIs_support_card() {
                return is_support_card;
            }

            public void setIs_support_card(Object is_support_card) {
                this.is_support_card = is_support_card;
            }

            public Object getControlled_train_flag() {
                return controlled_train_flag;
            }

            public void setControlled_train_flag(Object controlled_train_flag) {
                this.controlled_train_flag = controlled_train_flag;
            }

            public Object getControlled_train_message() {
                return controlled_train_message;
            }

            public void setControlled_train_message(Object controlled_train_message) {
                this.controlled_train_message = controlled_train_message;
            }

            public String getGg_num() {
                return gg_num;
            }

            public void setGg_num(String gg_num) {
                this.gg_num = gg_num;
            }

            public Object getGr_num() {
                return gr_num;
            }

            public void setGr_num(Object gr_num) {
                this.gr_num = gr_num;
            }

            public Object getQt_num() {
                return qt_num;
            }

            public void setQt_num(Object qt_num) {
                this.qt_num = qt_num;
            }

            public String getRw_num() {
                return rw_num;
            }

            public void setRw_num(String rw_num) {
                this.rw_num = rw_num;
            }

            public Object getRz_num() {
                return rz_num;
            }

            public void setRz_num(Object rz_num) {
                this.rz_num = rz_num;
            }

            public Object getTz_num() {
                return tz_num;
            }

            public void setTz_num(Object tz_num) {
                this.tz_num = tz_num;
            }

            public String getWz_num() {
                return wz_num;
            }

            public void setWz_num(String wz_num) {
                this.wz_num = wz_num;
            }

            public Object getYb_num() {
                return yb_num;
            }

            public void setYb_num(Object yb_num) {
                this.yb_num = yb_num;
            }

            public String getYw_num() {
                return yw_num;
            }

            public void setYw_num(String yw_num) {
                this.yw_num = yw_num;
            }

            public String getYz_num() {
                return yz_num;
            }

            public void setYz_num(String yz_num) {
                this.yz_num = yz_num;
            }

            public String getZe_num() {
                return ze_num;
            }

            public void setZe_num(String ze_num) {
                this.ze_num = ze_num;
            }

            public String getZy_num() {
                return zy_num;
            }

            public void setZy_num(String zy_num) {
                this.zy_num = zy_num;
            }

            public String getSwz_num() {
                return swz_num;
            }

            public void setSwz_num(String swz_num) {
                this.swz_num = swz_num;
            }
        }
    }
}
