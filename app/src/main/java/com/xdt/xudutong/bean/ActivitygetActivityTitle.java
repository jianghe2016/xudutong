package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */

public class ActivitygetActivityTitle {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取公告标题成功
     * content : {"data":[{"id":22,"title":"省委书记、省人大常委会主任谢伏瞻莅临许都通体验中心","description":"","content":"","createTime":"2017-05-06","key":"card","status":1,"author":"","icon":""},{"id":23,"title":"许都通·芯心相通","description":"","content":"","createTime":"2017-05-06","key":"card","status":1,"author":"","icon":""},{"id":21,"title":"\u201c许都通\u201d点亮现代服务业新征途","description":"","content":"","createTime":"2017-05-05","key":"card","status":1,"author":"","icon":""}]}
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
             * id : 22
             * title : 省委书记、省人大常委会主任谢伏瞻莅临许都通体验中心
             * description :
             * content :
             * createTime : 2017-05-06
             * key : card
             * status : 1
             * author :
             * icon :
             */

            private int id;
            private String title;
            private String description;
            private String content;
            private String createTime;
            private String key;
            private int status;
            private String author;
            private String icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
