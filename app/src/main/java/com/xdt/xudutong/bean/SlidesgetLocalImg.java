package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\11\9 0009.
 */

public class SlidesgetLocalImg {

    /**
     * flag : 1
     * code : R00001
     * desc : 获取幻灯片列表成功
     * content : {"data":[{"id":1,"url":"http://app.xudutong.com/xudutongapp/files/slides/slide_2.png","index":2,"title":"testtitle","platform":2}]}
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

    public static class ContentBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * url : http://app.xudutong.com/xudutongapp/files/slides/slide_2.png
             * index : 2
             * title : testtitle
             * platform : 2
             */

            private int id;
            private String url;
            private int index;
            private String title;
            private int platform;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }
        }
    }
}
