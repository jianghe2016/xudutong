package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\11\22 0022.
 */

public class MerchantconsumeSummary {

    /**
     * flag : true
     * code : R00001
     * desc : 查询成功！
     * content : {"count":72,"countMoney":"91.27","pages":1,"list":[{"netid":"09000001","netname":"测试1","count":"3","countMoney":"0.03","acccode":"2009","accdscrp":"保留使用","countAll":"","countMoneyAll":""},{"netid":"09000001","netname":"测试1","count":"69","countMoney":"91.24","acccode":"2052","accdscrp":"联机消费","countAll":"","countMoneyAll":""}],"pageNum":1}
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
         * count : 72
         * countMoney : 91.27
         * pages : 1
         * list : [{"netid":"09000001","netname":"测试1","count":"3","countMoney":"0.03","acccode":"2009","accdscrp":"保留使用","countAll":"","countMoneyAll":""},{"netid":"09000001","netname":"测试1","count":"69","countMoney":"91.24","acccode":"2052","accdscrp":"联机消费","countAll":"","countMoneyAll":""}]
         * pageNum : 1
         */

        private int count;
        private String countMoney;
        private int pages;
        private int pageNum;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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
             * netid : 09000001
             * netname : 测试1
             * count : 3
             * countMoney : 0.03
             * acccode : 2009
             * accdscrp : 保留使用
             * countAll :
             * countMoneyAll :
             */

            private String netid;
            private String netname;
            private String count;
            private String countMoney;
            private String acccode;
            private String accdscrp;
            private String countAll;
            private String countMoneyAll;

            public String getNetid() {
                return netid;
            }

            public void setNetid(String netid) {
                this.netid = netid;
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

            public String getAcccode() {
                return acccode;
            }

            public void setAcccode(String acccode) {
                this.acccode = acccode;
            }

            public String getAccdscrp() {
                return accdscrp;
            }

            public void setAccdscrp(String accdscrp) {
                this.accdscrp = accdscrp;
            }

            public String getCountAll() {
                return countAll;
            }

            public void setCountAll(String countAll) {
                this.countAll = countAll;
            }

            public String getCountMoneyAll() {
                return countMoneyAll;
            }

            public void setCountMoneyAll(String countMoneyAll) {
                this.countMoneyAll = countMoneyAll;
            }
        }
    }
}
