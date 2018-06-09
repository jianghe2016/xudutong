package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\10\13 0013.
 */

public class SlidesgetSlidesList {

    /**
     * flag : 1
     * code : R00001
     * desc : 获取幻灯片列表成功
     * content : {"data":[{"id":29,"url":"http://172.18.33.238:8080/files/slides/首页广告.png","index":1,"title":"测试幻灯片"},{"id":15,"url":"http://app.xudutong.com/xudutongapp/files/slides/slide_1.png","index":1,"title":"testtitle"},{"id":2,"url":"http://app.xudutong.com/xudutongapp/files/slides/slide_3.png","index":3,"title":"testtitle"}]}
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
             * id : 29
             * url : http://172.18.33.238:8080/files/slides/首页广告.png
             * index : 1
             * title : 测试幻灯片
             */

            private int id;
            private String url;
            private int index;
            private String title;

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
        }
    }
}
