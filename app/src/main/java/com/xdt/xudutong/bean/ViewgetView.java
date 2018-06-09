package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\10\31 0031.
 */

public class ViewgetView {

    /**
     * flag : 1
     * code : R00001
     * desc : 查询景点成功
     * content : {"data":[{"id":"281","view_name":"灞陵桥","view_pic":"http://m.tuniucdn.com/filebroker/cdn/snc/f9/e5/f9e5784be0ae03c308569ee3bd54f87d_w1536_h520_c1_t0.jpg","sort":"1","overview":"三国经典典故，关公挑袍发生地"},{"id":"168","view_name":"文峰塔","view_pic":"http://m.tuniucdn.com/fb2/t1/G2/M00/13/FA/Cii-T1eV0wyIAWZTAA9fv1eq8tsAAAc4QKPO94AD1_X123_w1536_h520_c1_t0.PNG","sort":"2","overview":"许昌著名古迹"},{"id":"161","view_name":"许昌曹丞相府","view_pic":"http://m.tuniucdn.com/filebroker/cdn/olb/53/a3/53a3916088f259b0acafe658aa1df3d4_w1536_h520_c1_t0.png","sort":"3","overview":"挟天子以令诸侯"},{"id":"160","view_name":"春秋楼","view_pic":"http://m.tuniucdn.com/filebroker/cdn/online/d5/fe/d5fef3b9_w1536_h520_c1_t0.jpg;http://m.tuniucdn.com/filebroker/cdn/olb/4b/48/4b487eb30f752776e848341af167939a_w400_h300_c1_t0.jpg","sort":"4","overview":"三国时期遗址"}]}
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
             * id : 281
             * view_name : 灞陵桥
             * view_pic : http://m.tuniucdn.com/filebroker/cdn/snc/f9/e5/f9e5784be0ae03c308569ee3bd54f87d_w1536_h520_c1_t0.jpg
             * sort : 1
             * overview : 三国经典典故，关公挑袍发生地
             */

            private String id;
            private String view_name;
            private String view_pic;
            private String sort;
            private String overview;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getView_name() {
                return view_name;
            }

            public void setView_name(String view_name) {
                this.view_name = view_name;
            }

            public String getView_pic() {
                return view_pic;
            }

            public void setView_pic(String view_pic) {
                this.view_pic = view_pic;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getOverview() {
                return overview;
            }

            public void setOverview(String overview) {
                this.overview = overview;
            }
        }
    }
}
