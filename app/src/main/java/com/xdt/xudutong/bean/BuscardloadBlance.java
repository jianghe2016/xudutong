package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class BuscardloadBlance {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取余额成功
     * content : {"data":[{"name":"安晓燕","viceonoddfare":null,"viceonopfare":null,"electronoddfare":53.5,"electronopfare":0.5,"dscrp":"电子钱包消费支出","
     * busnumber":"豫K96287","linename":"K2","opdt":"2017-05-19 14:01:44","time":null,"linedesc":null,"cardtypedetailid":"0"}]}
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
             * name : 安晓燕
             * viceonoddfare : null
             * viceonopfare : null
             * electronoddfare : 53.5
             * electronopfare : 0.5
             * dscrp : 电子钱包消费支出
             * busnumber : 豫K96287
             * linename : K2
             * opdt : 2017-05-19 14:01:44
             * time : null
             * linedesc : null
             * cardtypedetailid : 0
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
            private String cardtypedetailid;

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

            public String getCardtypedetailid() {
                return cardtypedetailid;
            }

            public void setCardtypedetailid(String cardtypedetailid) {
                this.cardtypedetailid = cardtypedetailid;
            }
        }
    }
}
