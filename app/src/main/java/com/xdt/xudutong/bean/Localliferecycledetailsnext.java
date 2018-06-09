package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\7\4 0004.
 */

public class Localliferecycledetailsnext {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询生活服务成功
     * content : {"data":[{"VAL":{"userphone":"15537467993","username":"15537467993","desc":"专业人员搬运，拉坏包赔，价格全市最低。快速上门服务。","addr":"区域： 长葛市 ,地址： 长葛长社路东段","companyName":"长葛专业搬家，搬公司，空调拆移"},"PARENTNUM":"2","LEVELNUM":"3"},{"VAL":{"userphone":"13949802131","username":"xcdhwl","desc":"许昌帝豪物流是一家专业从事国内物流、货运、仓储、运输、配送等服务为一体的现代化物流公司，分公司货运站遍布全国各地一百多个大中小各城市，可承担许昌到全国公路、铁路、水路的整车、零单运输业务.公司备有4.2米至17.5米等各种平板车，高栏车，半封闭，厢式车百余部，并有回程车优势供您选择。安全，快捷，准时，每天发车，包您满意，现在您只需要一个电话13949802131，一切由我们来做！","addr":"区域： 许昌县 ,地址： 许昌","companyName":"许昌到全国搬家公司 许昌到全国配货站"},"PARENTNUM":"2","LEVELNUM":"3"},{"VAL":{"userphone":"15517380048","username":"345145016wjq","desc":"搬家拉货，拆装家具，空调，热水器，油烟机，电视等，维修各种家居，水管，另结杂活。","addr":"区域： 鄢陵县 ,地址： 东街桥头","companyName":"鄢陵搬家公司"},"PARENTNUM":"2","LEVELNUM":"3"},{"VAL":{"userphone":"15136812986","username":"许昌兄弟搬家","desc":"许昌喜盈盈搬家公司，是许昌搬家公司的引领者。经过几年的不懈努力，开拓发展，现已成为许昌规模最大的专业搬家货运。现拥有各类运输车辆、机械设备30多台套。许昌喜盈盈搬家货运以居民、企事业搬场为基础，建立起市内物流、工业搬场、小件快运、市内配送为主的运输体系，培育出一批经验丰富的专业队伍，对所有高档家具，钢琴、空调、大型机器等具有技术精通专业人员拆装及搬动，一条龙服务。","addr":"区域： 魏都区 ,地址： 毓秀路南段","companyName":"喜盈盈搬家公司"},"PARENTNUM":"2","LEVELNUM":"3"},{"VAL":{"userphone":"15517380048","username":"万军强","desc":"鄢陵大众搬家公司成立于2013年5月，是本县成立较早的搬家公司，公司可承接企事业单位及个人搬运、大批量、高难度搬家；租车拉货；拆装维修空调；拆装油烟机；拆装电视；拆装家具等业务。公司从业人员均是从事家政服务行业多年的专业服务人员，所有员工均搬运、拆装技术熟练，责任心强，人人素质优，专业性强，都能做到搬迁、拆装中不损坏、不遗失客户物品，一切达到客户满意。","addr":"区域： 鄢陵县 ,地址： 东街","companyName":"鄢陵大众搬家公司"},"PARENTNUM":"2","LEVELNUM":"3"},{"VAL":{"userphone":"13782235992","username":"许昌兄弟搬家","desc":"专业家庭搬家，工厂，单位，公司，宾馆，学校，钢琴，\u200b\u200c\u200c拆装，家具，空调，保洁，回收旧家电，旧家具！！！","addr":"区域： 许昌县 ,地址： 兴昌路","companyName":"许昌兄弟搬家"},"PARENTNUM":"2","LEVELNUM":"3"},{"VAL":{"userphone":"15136812986","username":"喜盈盈搬家公司","desc":"喜盈盈搬家公司，是许昌搬家公司的引领者。经过几年的不懈努力，开拓发展，现已成为许昌规模最大的专业搬家货运。现拥有各类运输车辆、机械设备30多台套。许昌喜盈盈搬家货运以居民、企事业搬场为基础，建立起市内物流、工业搬场、小件快运、市内配送为主的运输体系，培育出一批经验丰富的专业队伍，对所有高档家具，钢琴、空调、大型机器等具有技术精通专业人员拆装及搬动，一条龙服务。","addr":"区域： 魏都区 ,地址： 毓秀路南段","companyName":"喜盈盈搬家公司"},"PARENTNUM":"2","LEVELNUM":"3"},{"VAL":{"userphone":"13782371508","username":"adu6634380","desc":"从事人工起重、吊装、搬运、安装的专业队伍。对各种大型机器在室内外的大、高、难、险、重的起重、吊装工程的操作有丰富的经验。主要承接单位、工厂搬迁；机器、机床、等大小型设备物资拆装、就位、吊装、运输等","addr":"区域： 长葛市 ,地址： 河南省长葛市","companyName":"机床搬运 设备搬运"},"PARENTNUM":"2","LEVELNUM":"3"}]}
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
             * VAL : {"userphone":"15537467993","username":"15537467993","desc":"专业人员搬运，拉坏包赔，价格全市最低。快速上门服务。","addr":"区域： 长葛市 ,地址： 长葛长社路东段","companyName":"长葛专业搬家，搬公司，空调拆移"}
             * PARENTNUM : 2
             * LEVELNUM : 3
             */

            private VALBean VAL;
            private String PARENTNUM;
            private String LEVELNUM;

            public VALBean getVAL() {
                return VAL;
            }

            public void setVAL(VALBean VAL) {
                this.VAL = VAL;
            }

            public String getPARENTNUM() {
                return PARENTNUM;
            }

            public void setPARENTNUM(String PARENTNUM) {
                this.PARENTNUM = PARENTNUM;
            }

            public String getLEVELNUM() {
                return LEVELNUM;
            }

            public void setLEVELNUM(String LEVELNUM) {
                this.LEVELNUM = LEVELNUM;
            }

            public class VALBean {
                /**
                 * userphone : 15537467993
                 * username : 15537467993
                 * desc : 专业人员搬运，拉坏包赔，价格全市最低。快速上门服务。
                 * addr : 区域： 长葛市 ,地址： 长葛长社路东段
                 * companyName : 长葛专业搬家，搬公司，空调拆移
                 */

                private String userphone;
                private String username;
                private String desc;
                private String addr;
                private String companyName;

                public String getUserphone() {
                    return userphone;
                }

                public void setUserphone(String userphone) {
                    this.userphone = userphone;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getAddr() {
                    return addr;
                }

                public void setAddr(String addr) {
                    this.addr = addr;
                }

                public String getCompanyName() {
                    return companyName;
                }

                public void setCompanyName(String companyName) {
                    this.companyName = companyName;
                }
            }
        }
    }
}
