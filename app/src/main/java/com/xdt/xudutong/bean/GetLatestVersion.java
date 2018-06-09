package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\7\15 0015.
 */

public class GetLatestVersion {


    /**
     * flag : 1
     * code : R00001
     * desc : 请求成功
     * content : {"data":{"filename":"v2.wgt","url":"http://192.168.2.65:8081/xudutongapp/version/download/42","version":"2.0"}}
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
        /**
         * data : {"filename":"v2.wgt","url":"http://192.168.2.65:8081/xudutongapp/version/download/42","version":"2.0"}
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
             * filename : v2.wgt
             * url : http://192.168.2.65:8081/xudutongapp/version/download/42
             * version : 2.0
             */

            private String filename;
            private String url;
            private String version;

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }
    }
}
