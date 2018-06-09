package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\7\8 0008.
 */

public class ZwjfeegetBuildings {


    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"building_id":"7B0E83C842134EBCABC0F466C3590505","building_name":"一号楼"},{"building_id":"437EDFD59DAF468A9B65891AD267C269","building_name":"二号楼"},{"building_id":"1AF43F56D8F34F1EA2C8BF96012709F3","building_name":"三号楼"},{"building_id":"42350C05DA8C47D0ACF7D0A952B0E6EC","building_name":"五号楼"},{"building_id":"C5057357A46B46EDA7A8F6A2F755FA29","building_name":"六号楼"},{"building_id":"0C3BB42A92D845C4A43B32CA9E22AE59","building_name":"七号楼"},{"building_id":"C6B06B75D6484708A0365C1F13698544","building_name":"八号楼"},{"building_id":"3AEFF47671F84EA5892BDB0E11C49F29","building_name":"九号楼"},{"building_id":"350C9C2834E04569BD918B57188BDCD5","building_name":"十号楼"},{"building_id":"B0D0F52D552D4AFFA7B31F74AE1EF70D","building_name":"十一号楼"},{"building_id":"A2280889479344D0A824A4B68A47CDEF","building_name":"十二号楼"},{"building_id":"131ECCC23C90430DBA6C0798CAB5B1B5","building_name":"十三号楼"},{"building_id":"D2A97596E2E147A898637460C3E9A1FE","building_name":"十五号楼"},{"building_id":"C8BF5C6EA32740F8BCBD5D9265860DA3","building_name":"二十一号楼"},{"building_id":"24A1793E7048452088FB34EA5E0B1CD6","building_name":"二十二号楼"},{"building_id":"0D1F3AD3BAF540F494387001EC0E8C30","building_name":"非业主"},{"building_id":"B19D9FE8CC2F48CFB1DE01959ED243BE","building_name":"商铺"}]}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * building_id : 7B0E83C842134EBCABC0F466C3590505
             * building_name : 一号楼
             */

            private String building_id;
            private String building_name;

            public String getBuilding_id() {
                return building_id;
            }

            public void setBuilding_id(String building_id) {
                this.building_id = building_id;
            }

            public String getBuilding_name() {
                return building_name;
            }

            public void setBuilding_name(String building_name) {
                this.building_name = building_name;
            }
        }
    }
}
