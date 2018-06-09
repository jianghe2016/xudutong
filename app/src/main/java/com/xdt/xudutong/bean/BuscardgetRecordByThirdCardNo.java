package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class BuscardgetRecordByThirdCardNo {

    /**
     * flag : 1
     * code : R00001
     * desc : 获取电子现金记录成功
     * content : {"data":[{"name":"623059113300721842","viceonoddfare":null,"viceonopfare":null,"electronoddfare":100,"electronopfare":0.6,"dscrp":"电子现金消费支出","busnumber":"豫K96211","linename":"公交2路","opdt":"2016-11-08 07:12:57","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"623059113300721842","viceonoddfare":null,"viceonopfare":null,"electronoddfare":99.4,"electronopfare":0.6,"dscrp":"电子现金消费支出","busnumber":"豫K95699","linename":"公交11路","opdt":"2016-11-08 07:37:13","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"623059113300721842","viceonoddfare":null,"viceonopfare":null,"electronoddfare":95.8,"electronopfare":0.6,"dscrp":"电子现金消费支出","busnumber":"豫K96200","linename":"公交2路","opdt":"2016-11-10 17:37:38","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"623059113300721842","viceonoddfare":null,"viceonopfare":null,"electronoddfare":91.6,"electronopfare":0.6,"dscrp":"电子现金消费支出","busnumber":"豫K96212","linename":"公交2路","opdt":"2016-11-22 07:36:27","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"623059113300721842","viceonoddfare":null,"viceonopfare":null,"electronoddfare":91,"electronopfare":0.6,"dscrp":"电子现金消费支出","busnumber":"豫K67937","linename":"公交6路","opdt":"2016-11-24 08:13:46","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"623059113300721842","viceonoddfare":null,"viceonopfare":null,"electronoddfare":89.2,"electronopfare":0.6,"dscrp":"电子现金消费支出","busnumber":"豫K96271","linename":"公交2路","opdt":"2016-11-26 17:57:12","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"623059113300721842","viceonoddfare":null,"viceonopfare":null,"electronoddfare":88.6,"electronopfare":0.6,"dscrp":"电子现金消费支出","busnumber":"豫K96290","linename":"公交2路","opdt":"2016-11-29 11:44:30","time":null,"linedesc":null,"cardtypedetailid":null},{"name":"623059113300721842","viceonoddfare":null,"viceonopfare":null,"electronoddfare":87.4,"electronopfare":0.6,"dscrp":"电子现金消费支出","busnumber":"豫K86986","linename":"公交4路","opdt":"2016-11-29 15:00:14","time":null,"linedesc":null,"cardtypedetailid":null}]}
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
             * name : 623059113300721842
             * viceonoddfare : null
             * viceonopfare : null
             * electronoddfare : 100
             * electronopfare : 0.6
             * dscrp : 电子现金消费支出
             * busnumber : 豫K96211
             * linename : 公交2路
             * opdt : 2016-11-08 07:12:57
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
