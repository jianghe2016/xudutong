package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\4 0004.
 */

public class LittlegreengetLittleGreen {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取小绿站点成功
     * content : {"data":[{"id":30,"name":"金融大厦","lat":34.083968,"lng":113.85202,"capacity":30,"availBike":21,"address":"芙蓉大道金融大厦门口","lasttime":"2017/8/29 9:21:55"},{"id":239,"name":"电气谷管委会","lat":34.094261,"lng":113.856931,"capacity":22,"availBike":13,"address":"管委会大门西侧","lasttime":"2017/8/29 9:22:05"},{"id":247,"name":"电子商务产业园","lat":34.088823,"lng":113.850743,"capacity":24,"availBike":13,"address":"许昌县尚集镇恒成金融大厦北250米","lasttime":"2017/8/29 9:22:17"},{"id":259,"name":"永兴路许继集团","lat":34.088672,"lng":113.866058,"capacity":40,"availBike":15,"address":"魏武大道与永兴东路交叉口向东200米路北","lasttime":"2017/8/29 9:21:49"},{"id":268,"name":"永兴路饮马桥","lat":34.085959,"lng":113.858467,"capacity":34,"availBike":16,"address":"魏文路与永兴东路交叉口向东100米路南","lasttime":"2017/8/29 9:22:10"}]}
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
             * id : 30
             * name : 金融大厦
             * lat : 34.083968
             * lng : 113.85202
             * capacity : 30
             * availBike : 21
             * address : 芙蓉大道金融大厦门口
             * lasttime : 2017/8/29 9:21:55
             */

            private int id;
            private String name;
            private double lat;
            private double lng;
            private int capacity;
            private int availBike;
            private String address;
            private String lasttime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public int getCapacity() {
                return capacity;
            }

            public void setCapacity(int capacity) {
                this.capacity = capacity;
            }

            public int getAvailBike() {
                return availBike;
            }

            public void setAvailBike(int availBike) {
                this.availBike = availBike;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLasttime() {
                return lasttime;
            }

            public void setLasttime(String lasttime) {
                this.lasttime = lasttime;
            }
        }
    }
}
