package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\11\15 0015.
 */

public class MerchantconsumeRecord {

    /**
     * flag : true
     * code : R00000
     * desc : 查询成功！
     * content : {"total":7,"countMoney":"10.40","pages":2,"list":[{"customerId":"80","opfare":"1.0","accdscrp":"医院","termname":"市立终端","opDT":"2017-11-03 20:48:28.0","uploadTime":"2017-11-03 20:48:11.0","netname":"许昌市立医院","count":"","countMoney":""},{"customerId":"80","opfare":"1.0","accdscrp":"医院","termname":"市立终端","opDT":"2017-11-03 20:36:47.0","uploadTime":"2017-11-03 20:36:28.0","netname":"许昌市立医院","count":"","countMoney":""},{"customerId":"80","opfare":"1.0","accdscrp":"医院","termname":"市立终端","opDT":"2017-11-03 20:23:59.0","uploadTime":"2017-11-03 20:23:42.0","netname":"许昌市立医院","count":"","countMoney":""},{"customerId":"80","opfare":"1.0","accdscrp":"医院","termname":"市立终端","opDT":"2017-11-03 19:40:19.0","uploadTime":"2017-11-03 19:40:01.0","netname":"许昌市立医院","count":"","countMoney":""},{"customerId":"80","opfare":"0.5","accdscrp":"医院","termname":"市立终端","opDT":"2017-11-03 19:32:41.0","uploadTime":"2017-11-03 19:32:24.0","netname":"许昌市立医院","count":"","countMoney":""}],"pageNum":1}
     */

    private boolean flag;
    private String code;
    private String desc;
    private ContentBean content;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
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

    public static class ContentBean {
        /**
         * total : 7
         * countMoney : 10.40
         * pages : 2
         * list : [{"customerId":"80","opfare":"1.0","accdscrp":"医院","termname":"市立终端","opDT":"2017-11-03 20:48:28.0","
         * uploadTime":"2017-11-03 20:48:11.0","netname":"许昌市立医院","count":"","countMoney":""},{"customerId":"80","opfare":"1.0","accdscrp":
         * "医院","termname":"市立终端","opDT":"2017-11-03 20:36:47.0","uploadTime":"2017-11-03 20:36:28.0","netname":"许昌市立医院","count":"",
         * "countMoney":""},{"customerId":"80","opfare":"1.0","accdscrp":"医院","termname":"市立终端","opDT":"2017-11-03 20:23:59.0","uploadTime":
         * "2017-11-03 20:23:42.0","netname":"许昌市立医院","count":"","countMoney":""},{"customerId":"80","opfare":"1.0","accdscrp":"医院","termname":
         * "市立终端","opDT":"2017-11-03 19:40:19.0","uploadTime":"2017-11-03 19:40:01.0","netname":"许昌市立医院","count":"","countMoney":""},
         * {"customerId":"80","opfare":"0.5","accdscrp":"医院","termname":"市立终端","opDT":"2017-11-03 19:32:41.0","uploadTime":"
         * 2017-11-03 19:32:24.0","netname":"许昌市立医院","count":"","countMoney":""}]
         * pageNum : 1
         */

        private int total;
        private String countMoney;
        private int pages;
        private int pageNum;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getCountMoney() {
            return countMoney;
        }

        public void setCountMoney(String countMoney) {
            this.countMoney = countMoney;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * customerId : 80
             * opfare : 1.0
             * accdscrp : 医院
             * termname : 市立终端
             * opDT : 2017-11-03 20:48:28.0
             * uploadTime : 2017-11-03 20:48:11.0
             * netname : 许昌市立医院
             * count :
             * countMoney :
             */

            private String customerId;
            private String opfare;
            private String accdscrp;
            private String termname;
            private String opDT;
            private String uploadTime;
            private String netname;
            private String count;
            private String countMoney;

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getOpfare() {
                return opfare;
            }

            public void setOpfare(String opfare) {
                this.opfare = opfare;
            }

            public String getAccdscrp() {
                return accdscrp;
            }

            public void setAccdscrp(String accdscrp) {
                this.accdscrp = accdscrp;
            }

            public String getTermname() {
                return termname;
            }

            public void setTermname(String termname) {
                this.termname = termname;
            }

            public String getOpDT() {
                return opDT;
            }

            public void setOpDT(String opDT) {
                this.opDT = opDT;
            }

            public String getUploadTime() {
                return uploadTime;
            }

            public void setUploadTime(String uploadTime) {
                this.uploadTime = uploadTime;
            }

            public String getNetname() {
                return netname;
            }

            public void setNetname(String netname) {
                this.netname = netname;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getCountMoney() {
                return countMoney;
            }

            public void setCountMoney(String countMoney) {
                this.countMoney = countMoney;
            }
        }
    }
}
