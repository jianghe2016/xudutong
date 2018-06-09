package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\1 0001.
 */

public class HujidifindHujidis {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"id":"b50f382a5eb84d51ae4d814f697a9ad9","huji_addr":"北大街道办事处","huji_code":"411002005"},{"id":"3390f378625941e8a8ef13fc00fde5eb","huji_addr":"魏都区北大街道办事处八一社区居委会","huji_code":"411002005001"},{"id":"7d1536c5fe6e49009b6d6168c054861f","huji_addr":"魏都区北大街道办事处西湖社区居委会","huji_code":"411002005002"},{"id":"2de2cdf894bd4f75bf400af3459d64f2","huji_addr":"魏都区北大街道办事处新村社区居委会","huji_code":"411002005005"},{"id":"c0563eef0da44e4fa919cfc875d09c2c","huji_addr":"魏都区北大街道办事处玉皇阁社区居委会","huji_code":"411002005007"},{"id":"652f79a817fd4f5e94ae5d8df1906bdf","huji_addr":"魏都区北大街道办事处学院社区居委会","huji_code":"411002005006"}]}
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
             * id : b50f382a5eb84d51ae4d814f697a9ad9
             * huji_addr : 北大街道办事处
             * huji_code : 411002005
             */

            private String id;
            private String huji_addr;
            private String huji_code;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getHuji_addr() {
                return huji_addr;
            }

            public void setHuji_addr(String huji_addr) {
                this.huji_addr = huji_addr;
            }

            public String getHuji_code() {
                return huji_code;
            }

            public void setHuji_code(String huji_code) {
                this.huji_code = huji_code;
            }
        }
    }
}
