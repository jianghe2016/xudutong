package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\6\26 0026.
 */

public class MobilebusqueryBusLineInfo {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":{"lineinfo":"11路   运营时间:06:40-19:30 胖东来大众服饰→高铁东站","linedata":[{"busImg":"","busStation":"胖东来大众服饰"},{"busImg":"","busStation":""},{"busImg":"","busStation":"火车站汽车站"},{"busImg":"","busStation":""},{"busImg":"","busStation":"七一路电子数码城"},{"busImg":"","busStation":""},{"busImg":"","busStation":"胖东来时代广场"},{"busImg":"","busStation":""},{"busImg":"","busStation":"七一路电子数码特色街东口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"文峰路文会街东口"},{"busImg":"1","busStation":""},{"busImg":"","busStation":"森林公安分局东关路口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"文峰路建安大道口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"鼎鑫花园"},{"busImg":"","busStation":""},{"busImg":"","busStation":"许昌市慈善总会"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市人大政协行政大厅"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市委市政府"},{"busImg":"","busStation":""},{"busImg":"","busStation":"建业小区"},{"busImg":"1","busStation":""},{"busImg":"","busStation":"九洲溪雅苑"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市财政局许昌银行"},{"busImg":"","busStation":""},{"busImg":"","busStation":"许昌学院"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市疾控中心"},{"busImg":"1","busStation":""},{"busImg":"","busStation":"市广电大厦"},{"busImg":"","busStation":""},{"busImg":"","busStation":"空港新城"},{"busImg":"","busStation":""},{"busImg":"","busStation":"学府街许州路口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"高铁东站"}]}}
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
         * data : {"lineinfo":"11路   运营时间:06:40-19:30 胖东来大众服饰→高铁东站","linedata":[{"busImg":"","busStation":"胖东来大众服饰"},{"busImg":"","busStation":""},{"busImg":"","busStation":"火车站汽车站"},{"busImg":"","busStation":""},{"busImg":"","busStation":"七一路电子数码城"},{"busImg":"","busStation":""},{"busImg":"","busStation":"胖东来时代广场"},{"busImg":"","busStation":""},{"busImg":"","busStation":"七一路电子数码特色街东口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"文峰路文会街东口"},{"busImg":"1","busStation":""},{"busImg":"","busStation":"森林公安分局东关路口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"文峰路建安大道口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"鼎鑫花园"},{"busImg":"","busStation":""},{"busImg":"","busStation":"许昌市慈善总会"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市人大政协行政大厅"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市委市政府"},{"busImg":"","busStation":""},{"busImg":"","busStation":"建业小区"},{"busImg":"1","busStation":""},{"busImg":"","busStation":"九洲溪雅苑"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市财政局许昌银行"},{"busImg":"","busStation":""},{"busImg":"","busStation":"许昌学院"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市疾控中心"},{"busImg":"1","busStation":""},{"busImg":"","busStation":"市广电大厦"},{"busImg":"","busStation":""},{"busImg":"","busStation":"空港新城"},{"busImg":"","busStation":""},{"busImg":"","busStation":"学府街许州路口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"高铁东站"}]}
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
             * lineinfo : 11路   运营时间:06:40-19:30 胖东来大众服饰→高铁东站
             * linedata : [{"busImg":"","busStation":"胖东来大众服饰"},{"busImg":"","busStation":""},{"busImg":"","busStation":"火车站汽车站"},{"busImg":"","busStation":""},{"busImg":"","busStation":"七一路电子数码城"},{"busImg":"","busStation":""},{"busImg":"","busStation":"胖东来时代广场"},{"busImg":"","busStation":""},{"busImg":"","busStation":"七一路电子数码特色街东口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"文峰路文会街东口"},{"busImg":"1","busStation":""},{"busImg":"","busStation":"森林公安分局东关路口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"文峰路建安大道口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"鼎鑫花园"},{"busImg":"","busStation":""},{"busImg":"","busStation":"许昌市慈善总会"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市人大政协行政大厅"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市委市政府"},{"busImg":"","busStation":""},{"busImg":"","busStation":"建业小区"},{"busImg":"1","busStation":""},{"busImg":"","busStation":"九洲溪雅苑"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市财政局许昌银行"},{"busImg":"","busStation":""},{"busImg":"","busStation":"许昌学院"},{"busImg":"","busStation":""},{"busImg":"","busStation":"市疾控中心"},{"busImg":"1","busStation":""},{"busImg":"","busStation":"市广电大厦"},{"busImg":"","busStation":""},{"busImg":"","busStation":"空港新城"},{"busImg":"","busStation":""},{"busImg":"","busStation":"学府街许州路口"},{"busImg":"","busStation":""},{"busImg":"","busStation":"高铁东站"}]
             */

            private String lineinfo;
            private List<LinedataBean> linedata;

            public String getLineinfo() {
                return lineinfo;
            }

            public void setLineinfo(String lineinfo) {
                this.lineinfo = lineinfo;
            }

            public List<LinedataBean> getLinedata() {
                return linedata;
            }

            public void setLinedata(List<LinedataBean> linedata) {
                this.linedata = linedata;
            }

            public class LinedataBean {
                /**
                 * busImg :
                 * busStation : 胖东来大众服饰
                 */

                private String busImg;
                private String busStation;

                public String getBusImg() {
                    return busImg;
                }

                public void setBusImg(String busImg) {
                    this.busImg = busImg;
                }

                public String getBusStation() {
                    return busStation;
                }

                public void setBusStation(String busStation) {
                    this.busStation = busStation;
                }
            }
        }
    }
}
