package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017/5/26.
 */

public class Librarygetbookinfo {

    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":{"coverPath":"","coverUrl":"http://img3x9.ddimg.cn/70/16/1016536129-1_w_1.jpg","mediumCoverPath":"","smallCoverPath":"","catalog":"目录<br/>聊斋志异第一册<br/>高序<br/>唐序<br/>聊斋自志<br/>卷一<br/>考城隍<br/>耳中人<br/>尸变<br/>喷水<br/>瞳人语<br/>画壁<br/>山魈<br/>咬鬼<br/>捉狐<br/>莜中怪  ","editorRecommend":"","summary":"《聊斋志异》，清代短篇小说集，是蒲松龄的代表作，在他40岁左右时基本完成，此后不断有所增补和修改。\u201c聊斋\u201d是他的书屋名称，\u201c志\u201d是记述的意思，\u201c异\u201d指奇异的故事。全书有短篇小说491篇。题材非常广泛，内容极其丰富。多数作品通过谈狐说鬼的手法，对当时社会的腐败、黑暗进行了有力批判，在一定程度上揭露了社会矛盾，表达了人民的愿望。但其中也夹杂着一些封建伦理观念和因果报应的宿命论思想。《聊斋志异》的艺术成就很高。它成功的塑造了众多的艺术典型，人物形象鲜明生动，故事情节曲折离奇，结构布局严谨巧妙，文笔简练，描写细腻，堪称中国古典短篇小说之**。","authorIntroduction":"","previewUrl":"","previewContent":""}}
     * sessionId : null
     */

    private int flag;
    private String code;
    private String desc;
    private ContentBean content;
    private Object sessionId;

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

    public Object getSessionId() {
        return sessionId;
    }

    public void setSessionId(Object sessionId) {
        this.sessionId = sessionId;
    }

    public class ContentBean {
        /**
         * data : {"coverPath":"","coverUrl":"http://img3x9.ddimg.cn/70/16/1016536129-1_w_1.jpg","mediumCoverPath":"","smallCoverPath":"","catalog":"目录<br/>聊斋志异第一册<br/>高序<br/>唐序<br/>聊斋自志<br/>卷一<br/>考城隍<br/>耳中人<br/>尸变<br/>喷水<br/>瞳人语<br/>画壁<br/>山魈<br/>咬鬼<br/>捉狐<br/>莜中怪  ","editorRecommend":"","summary":"《聊斋志异》，清代短篇小说集，是蒲松龄的代表作，在他40岁左右时基本完成，此后不断有所增补和修改。\u201c聊斋\u201d是他的书屋名称，\u201c志\u201d是记述的意思，\u201c异\u201d指奇异的故事。全书有短篇小说491篇。题材非常广泛，内容极其丰富。多数作品通过谈狐说鬼的手法，对当时社会的腐败、黑暗进行了有力批判，在一定程度上揭露了社会矛盾，表达了人民的愿望。但其中也夹杂着一些封建伦理观念和因果报应的宿命论思想。《聊斋志异》的艺术成就很高。它成功的塑造了众多的艺术典型，人物形象鲜明生动，故事情节曲折离奇，结构布局严谨巧妙，文笔简练，描写细腻，堪称中国古典短篇小说之**。","authorIntroduction":"","previewUrl":"","previewContent":""}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * coverPath :
             * coverUrl : http://img3x9.ddimg.cn/70/16/1016536129-1_w_1.jpg
             * mediumCoverPath :
             * smallCoverPath :
             * catalog : 目录<br/>聊斋志异第一册<br/>高序<br/>唐序<br/>聊斋自志<br/>卷一<br/>考城隍<br/>耳中人<br/>尸变<br/>喷水<br/>瞳人语<br/>画壁<br/>山魈<br/>咬鬼<br/>捉狐<br/>莜中怪  
             * editorRecommend :
             * summary : 《聊斋志异》，清代短篇小说集，是蒲松龄的代表作，在他40岁左右时基本完成，此后不断有所增补和修改。“聊斋”是他的书屋名称，“志”是记述的意思，“异”指奇异的故事。全书有短篇小说491篇。题材非常广泛，内容极其丰富。多数作品通过谈狐说鬼的手法，对当时社会的腐败、黑暗进行了有力批判，在一定程度上揭露了社会矛盾，表达了人民的愿望。但其中也夹杂着一些封建伦理观念和因果报应的宿命论思想。《聊斋志异》的艺术成就很高。它成功的塑造了众多的艺术典型，人物形象鲜明生动，故事情节曲折离奇，结构布局严谨巧妙，文笔简练，描写细腻，堪称中国古典短篇小说之**。
             * authorIntroduction :
             * previewUrl :
             * previewContent :
             */

            private String coverPath;
            private String coverUrl;
            private String mediumCoverPath;
            private String smallCoverPath;
            private String catalog;
            private String editorRecommend;
            private String summary;
            private String authorIntroduction;
            private String previewUrl;
            private String previewContent;

            public String getCoverPath() {
                return coverPath;
            }

            public void setCoverPath(String coverPath) {
                this.coverPath = coverPath;
            }

            public String getCoverUrl() {
                return coverUrl;
            }

            public void setCoverUrl(String coverUrl) {
                this.coverUrl = coverUrl;
            }

            public String getMediumCoverPath() {
                return mediumCoverPath;
            }

            public void setMediumCoverPath(String mediumCoverPath) {
                this.mediumCoverPath = mediumCoverPath;
            }

            public String getSmallCoverPath() {
                return smallCoverPath;
            }

            public void setSmallCoverPath(String smallCoverPath) {
                this.smallCoverPath = smallCoverPath;
            }

            public String getCatalog() {
                return catalog;
            }

            public void setCatalog(String catalog) {
                this.catalog = catalog;
            }

            public String getEditorRecommend() {
                return editorRecommend;
            }

            public void setEditorRecommend(String editorRecommend) {
                this.editorRecommend = editorRecommend;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getAuthorIntroduction() {
                return authorIntroduction;
            }

            public void setAuthorIntroduction(String authorIntroduction) {
                this.authorIntroduction = authorIntroduction;
            }

            public String getPreviewUrl() {
                return previewUrl;
            }

            public void setPreviewUrl(String previewUrl) {
                this.previewUrl = previewUrl;
            }

            public String getPreviewContent() {
                return previewContent;
            }

            public void setPreviewContent(String previewContent) {
                this.previewContent = previewContent;
            }
        }
    }
}
