package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class BuscardgetRecordByName {

    /**
     * flag : 1
     * code : R00001
     * desc : 获取记录成功
     * content : {"data":[{"name":"安晓燕","viceonoddfare":null,"viceonopfare":null,"electronoddfare":25.9,"electronopfare":0.5,"dscrp":"电子钱包消费支出","busnumber":"豫K73611","linename":"公交66路","opdt":"2016-11-28 10:48:41","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"安晓燕","viceonoddfare":null,"viceonopfare":null,"electronoddfare":31.9,"electronopfare":0.5,"dscrp":"电子钱包消费支出","busnumber":"豫K73611","linename":"公交66路","opdt":"2016-11-18 09:38:25","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"安晓燕","viceonoddfare":null,"viceonopfare":null,"electronoddfare":32.4,"electronopfare":0.5,"dscrp":"电子钱包消费支出","busnumber":"豫K73611","linename":"公交66路","opdt":"2016-11-17 15:55:45","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"安晓燕","viceonoddfare":null,"viceonopfare":null,"electronoddfare":34.9,"electronopfare":0.5,"dscrp":"电子钱包消费支出","busnumber":"豫K73611","linename":"公交66路","opdt":"2016-11-15 10:06:41","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"安晓燕","viceonoddfare":null,"viceonopfare":null,"electronoddfare":39.7,"electronopfare":0.5,"dscrp":"电子钱包消费支出","busnumber":"豫K82967","linename":"公交1路","opdt":"2016-11-10 14:19:43","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"安晓燕","viceonoddfare":null,"viceonopfare":null,"electronoddfare":43.8,"electronopfare":0.5,"dscrp":"电子钱包消费支出","busnumber":"豫K82967","linename":"公交1路","opdt":"2016-11-08 11:09:17","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"安晓燕","viceonoddfare":null,"viceonopfare":null,"electronoddfare":49.8,"electronopfare":0.5,"dscrp":"电子钱包消费支出","busnumber":"豫K73611","linename":"公交66路","opdt":"2016-11-03 13:44:54","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"安晓燕","viceonoddfare":null,"viceonopfare":null,"electronoddfare":51.3,"electronopfare":0.5,"dscrp":"电子钱包消费支出","busnumber":"豫K73611","linename":"公交66路","opdt":"2016-11-02 14:15:54","time":null,"linedesc":null,"cardtypedetailid":null}]}
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

        public  class DataBean {
            /**
             * name : 安晓燕
             * viceonoddfare : null
             * viceonopfare : null
             * electronoddfare : 25.9
             * electronopfare : 0.5
             * dscrp : 电子钱包消费支出
             * busnumber : 豫K73611
             * linename : 公交66路
             * opdt : 2016-11-28 10:48:41
             * time : null
             * linedesc : null
             * cardtypedetailid : null
             */

            private String name;
            private Object viceonoddfare;
            private Object viceonopfare;
            private double electronoddfare;
            private double electronopfare;
            private String dscrp;
            private String busnumber;
            private String linename;
            private String opdt;
            private Object time;
            private Object linedesc;
            private Object cardtypedetailid;

            public String getName() {
                return name;
            }

            public void setName(String name) {
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

            public double getElectronopfare() {
                return electronopfare;
            }

            public void setElectronopfare(double electronopfare) {
                this.electronopfare = electronopfare;
            }

            public String getDscrp() {
                return dscrp;
            }

            public void setDscrp(String dscrp) {
                this.dscrp = dscrp;
            }

            public String getBusnumber() {
                return busnumber;
            }

            public void setBusnumber(String busnumber) {
                this.busnumber = busnumber;
            }

            public String getLinename() {
                return linename;
            }

            public void setLinename(String linename) {
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
