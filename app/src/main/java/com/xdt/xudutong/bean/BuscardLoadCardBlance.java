package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class BuscardLoadCardBlance {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取电子现金余额成功
     * content : {"data":[{"name":null,"viceonoddfare":null,"viceonopfare":null,"electronoddfare":75.4,"electronopfare":null,"dscrp":null,"busnumber":null,"linename":null,"opdt":"2017-01-15 15:12:49","time":null,"linedesc":null,"cardtypedetailid":null}]}
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

    public  class ContentBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public  class DataBean {
            /**
             * name : null
             * viceonoddfare : null
             * viceonopfare : null
             * electronoddfare : 75.4
             * electronopfare : null
             * dscrp : null
             * busnumber : null
             * linename : null
             * opdt : 2017-01-15 15:12:49
             * time : null
             * linedesc : null
             * cardtypedetailid : null
             */

            private Object name;
            private Object viceonoddfare;
            private Object viceonopfare;
            private double electronoddfare;
            private Object electronopfare;
            private Object dscrp;
            private Object busnumber;
            private Object linename;
            private String opdt;
            private Object time;
            private Object linedesc;
            private Object cardtypedetailid;

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getViceonoddfare() {
                return viceonoddfare;
            }

            public void setViceonoddfare(Object viceonoddfare) {
                this.viceonoddfare = viceonoddfare;
            }

            public Object getViceonopfare() {
                return viceonopfare;
            }

            public void setViceonopfare(Object viceonopfare) {
                this.viceonopfare = viceonopfare;
            }

            public double getElectronoddfare() {
                return electronoddfare;
            }

            public void setElectronoddfare(double electronoddfare) {
                this.electronoddfare = electronoddfare;
            }

            public Object getElectronopfare() {
                return electronopfare;
            }

            public void setElectronopfare(Object electronopfare) {
                this.electronopfare = electronopfare;
            }

            public Object getDscrp() {
                return dscrp;
            }

            public void setDscrp(Object dscrp) {
                this.dscrp = dscrp;
            }

            public Object getBusnumber() {
                return busnumber;
            }

            public void setBusnumber(Object busnumber) {
                this.busnumber = busnumber;
            }

            public Object getLinename() {
                return linename;
            }

            public void setLinename(Object linename) {
                this.linename = linename;
            }

            public String getOpdt() {
                return opdt;
            }

            public void setOpdt(String opdt) {
                this.opdt = opdt;
            }

            public Object getTime() {
                return time;
            }

            public void setTime(Object time) {
                this.time = time;
            }

            public Object getLinedesc() {
                return linedesc;
            }

            public void setLinedesc(Object linedesc) {
                this.linedesc = linedesc;
            }

            public Object getCardtypedetailid() {
                return cardtypedetailid;
            }

            public void setCardtypedetailid(Object cardtypedetailid) {
                this.cardtypedetailid = cardtypedetailid;
            }
        }
    }
}
