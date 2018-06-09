package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\9\29 0029.
 */

public class ApplloadAppimg {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"appName":"养老保险","appImg":"iVBORw0KGgoAAAANSUhEUgAAAFMAAABTCAYAAADjsjsAAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ\r\nbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp\r\nbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6\r\neD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1\r\nNTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo\r\ndHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw\r\ndGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAv\r\nIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RS\r\nZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpD\r\ncmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiIHhtcE1NOklu\r\nc3RhbmNlSUQ9InhtcC5paWQ6OTNBMzE2M0M3NUQyMTFFN0FFMkI5QzgxREVCMTY5MzUiIHhtcE1N\r\nOkRvY3VtZW50SUQ9InhtcC5kaWQ6OTNBMzE2M0Q3NUQyMTFFN0FFMkI5QzgxREVCMTY5MzUiPiA8\r\neG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo5M0EzMTYzQTc1RDIx\r\nMUU3QUUyQjlDODFERUIxNjkzNSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo5M0EzMTYzQjc1\r\nRDIxMUU3QUUyQjlDODFERUIxNjkzNSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8\r\nL3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Pk0ZYiQAABGTSURBVHja5F1pkxPXFX3d6tFs\r\n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"}]}
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
             * appName : 养老保险
             * appImg : iVBORw0KGgoAAAANSUhEUgAAAFMAAABTCAYAAADjsjsAAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ
             bWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp
             bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6
             eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1
             NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo
             dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw
             dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAv
             IiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RS
             ZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpD
             cmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiIHhtcE1NOklu
             c3RhbmNlSUQ9InhtcC5paWQ6OTNBMzE2M0M3NUQyMTFFN0FFMkI5QzgxREVCMTY5MzUiIHhtcE1N
             OkRvY3VtZW50SUQ9InhtcC5kaWQ6OTNBMzE2M0Q3NUQyMTFFN0FFMkI5QzgxREVCMTY5MzUiPiA8
             eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo5M0EzMTYzQTc1RDIx
             MUU3QUUyQjlDODFERUIxNjkzNSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo5M0EzMTYzQjc1
             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
             */

            private String appName;
            private String appImg;

            public String getAppName() {
                return appName;
            }

            public void setAppName(String appName) {
                this.appName = appName;
            }

            public String getAppImg() {
                return appImg;
            }

            public void setAppImg(String appImg) {
                this.appImg = appImg;
            }
        }
    }
}
