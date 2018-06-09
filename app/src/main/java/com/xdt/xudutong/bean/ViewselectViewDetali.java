package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\6\28 0028.
 */

public class ViewselectViewDetali {
    /**
     * flag : 1
     * code : R00001
     * desc : 查询景点成功
     * content : {"data":[{"VIEW_LOCATION":"113.096621,34.244017","CREATE_TIME":"2017-02-28 09:03:58","VIEW_DESC":"大鸿寨，大鸿寨旅游风景区集自然、山、水、洞、林为一体，其优美而丰富的自然生态旅游环境，符合人们回归自然的心理需求，是河南省少有的自然生态旅游区之一。大鸿寨位于禹州西北边陲，其主峰高1156米，为许昌第一高山，号称\u201c许昌屋脊\u201d。 驻足山巅，俯视四周，可见万壑纵横，群山围拱，峰峦叠翠，气象万千，主峰向东不远，还依次排列着尖峭峻拔的摘墨楼，开阔平坦的坷垃垛，在这三峰的怀抱里， 自西向东有水帘洞，金龟出海、雄鸡峰、观音石等30余处景观，这些景点或高悬于峭壁上，或掩映于森林之间，或盘踞于沟壑之中，或凌翅子高峰之巅．置身于其间，移步换景，变幻莫测，令人游兴盎然。 大鸿寨山位于禹州西北边陲，其主峰高1156米，为许昌第一高山，号称\u201c许昌屋脊\u201d。驻足山巅，俯视四周，可见万壑纵横，群山围拱，峰峦叠翠，气象万千，主峰向东不远，还依次排列着尖峭峻拔的摘墨楼，开阔平坦的坷垃垛，在这三峰的怀抱里， 自西向东有水帘洞，金龟出海、雄鸡峰、观音石等30余处景观，这些景点或高悬于峭壁上，或掩映于森林之间，或盘踞于沟壑之中，或凌翅子高峰之巅．置身于其间，移步换景，变幻莫测，令人游兴盎然。","VIEW_PIC":"http://m.tuniucdn.com/fb2/t1/G1/M00/7D/DE/Cii9EFZ7SP-IRnleAAGJc8qPF_sAABIpQI-HwsAAYmL630_w1536_h520_c1_t0.jpg;http://m.tuniucdn.com/fb2/t1/G1/M00/7D/DE/Cii9EFZ7SRiIBKt0AAEv2nmrHVkAABIpQNC1HcAAS_y227_w400_h300_c1_t0.jpg","ID":132,"OPEN_TIME":"7:00-18:00","STATUS":"1","VIEW_NAME":"大鸿寨","VIEW_ADDR":"河南省禹州市西北边陲鸠山乡境内。"}]}
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
             * VIEW_LOCATION : 113.096621,34.244017
             * CREATE_TIME : 2017-02-28 09:03:58
             * VIEW_DESC : 大鸿寨，大鸿寨旅游风景区集自然、山、水、洞、林为一体，其优美而丰富的自然生态旅游环境，符合人们回归自然的心理需求，是河南省少有的自然生态旅游区之一。大鸿寨位于禹州西北边陲，其主峰高1156米，为许昌第一高山，号称“许昌屋脊”。 驻足山巅，俯视四周，可见万壑纵横，群山围拱，峰峦叠翠，气象万千，主峰向东不远，还依次排列着尖峭峻拔的摘墨楼，开阔平坦的坷垃垛，在这三峰的怀抱里， 自西向东有水帘洞，金龟出海、雄鸡峰、观音石等30余处景观，这些景点或高悬于峭壁上，或掩映于森林之间，或盘踞于沟壑之中，或凌翅子高峰之巅．置身于其间，移步换景，变幻莫测，令人游兴盎然。 大鸿寨山位于禹州西北边陲，其主峰高1156米，为许昌第一高山，号称“许昌屋脊”。驻足山巅，俯视四周，可见万壑纵横，群山围拱，峰峦叠翠，气象万千，主峰向东不远，还依次排列着尖峭峻拔的摘墨楼，开阔平坦的坷垃垛，在这三峰的怀抱里， 自西向东有水帘洞，金龟出海、雄鸡峰、观音石等30余处景观，这些景点或高悬于峭壁上，或掩映于森林之间，或盘踞于沟壑之中，或凌翅子高峰之巅．置身于其间，移步换景，变幻莫测，令人游兴盎然。
             * VIEW_PIC : http://m.tuniucdn.com/fb2/t1/G1/M00/7D/DE/Cii9EFZ7SP-IRnleAAGJc8qPF_sAABIpQI-HwsAAYmL630_w1536_h520_c1_t0.jpg;http://m.tuniucdn.com/fb2/t1/G1/M00/7D/DE/Cii9EFZ7SRiIBKt0AAEv2nmrHVkAABIpQNC1HcAAS_y227_w400_h300_c1_t0.jpg
             * ID : 132
             * OPEN_TIME : 7:00-18:00
             * STATUS : 1
             * VIEW_NAME : 大鸿寨
             * VIEW_ADDR : 河南省禹州市西北边陲鸠山乡境内。
             */

            private String VIEW_LOCATION;
            private String CREATE_TIME;
            private String VIEW_DESC;
            private String VIEW_PIC;
            private int ID;
            private String OPEN_TIME;
            private String STATUS;
            private String VIEW_NAME;
            private String VIEW_ADDR;

            public String getVIEW_LOCATION() {
                return VIEW_LOCATION;
            }

            public void setVIEW_LOCATION(String VIEW_LOCATION) {
                this.VIEW_LOCATION = VIEW_LOCATION;
            }

            public String getCREATE_TIME() {
                return CREATE_TIME;
            }

            public void setCREATE_TIME(String CREATE_TIME) {
                this.CREATE_TIME = CREATE_TIME;
            }

            public String getVIEW_DESC() {
                return VIEW_DESC;
            }

            public void setVIEW_DESC(String VIEW_DESC) {
                this.VIEW_DESC = VIEW_DESC;
            }

            public String getVIEW_PIC() {
                return VIEW_PIC;
            }

            public void setVIEW_PIC(String VIEW_PIC) {
                this.VIEW_PIC = VIEW_PIC;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getOPEN_TIME() {
                return OPEN_TIME;
            }

            public void setOPEN_TIME(String OPEN_TIME) {
                this.OPEN_TIME = OPEN_TIME;
            }

            public String getSTATUS() {
                return STATUS;
            }

            public void setSTATUS(String STATUS) {
                this.STATUS = STATUS;
            }

            public String getVIEW_NAME() {
                return VIEW_NAME;
            }

            public void setVIEW_NAME(String VIEW_NAME) {
                this.VIEW_NAME = VIEW_NAME;
            }

            public String getVIEW_ADDR() {
                return VIEW_ADDR;
            }

            public void setVIEW_ADDR(String VIEW_ADDR) {
                this.VIEW_ADDR = VIEW_ADDR;
            }
        }
    }
}
