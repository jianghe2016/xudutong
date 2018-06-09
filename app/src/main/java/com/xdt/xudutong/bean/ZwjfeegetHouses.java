package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\7\10 0010.
 */

public class ZwjfeegetHouses {
    /**
     * flag : 1
     * code : R00001
     * desc : 查询成功
     * content : {"data":[{"houseId":"56BB83E29786405FA833694733413DBF","houseNumber":"1-1-301"},{"houseId":"1C0C0BE6DDF84F4AA810957248F4B494","houseNumber":"1-1-302"},{"houseId":"F2BBDC16EE154488A8071B7FE683CDBB","houseNumber":"1-1-401"},{"houseId":"CA880D46E1E34DFEB970D73A8114D50D","houseNumber":"1-1-402"},{"houseId":"5B2C6E53A4CC4524BFEE62D28B911BAD","houseNumber":"1-1-501"},{"houseId":"7349217C50B04119BA0ED79C3D2C40C4","houseNumber":"1-1-502"},{"houseId":"0106BAB039DB47C69E84C0C1EB22CCB5","houseNumber":"1-1-601"},{"houseId":"B412574F5C9A4A4491EAB5A80357AB44","houseNumber":"1-1-602"},{"houseId":"C7AAF5D6CE81419D8B0343C1734DD2B8","houseNumber":"1-1-701"},{"houseId":"B7192CC473034B528F2507CEB85B1F28","houseNumber":"1-1-702"},{"houseId":"9DD03956F19E45D3ABDEA841FDCC86AC","houseNumber":"1-1-801"},{"houseId":"10734EB75DE447F4BF4960614DCC9A66","houseNumber":"1-1-802"},{"houseId":"1722CCA001214BF78AA0D40F02F4CE54","houseNumber":"1-1-901"},{"houseId":"4424EB373887471E8651572B1AD59281","houseNumber":"1-1-902"},{"houseId":"4C286523CA6547E9844C07F320920A4C","houseNumber":"1-1-1001"},{"houseId":"DCF4CE476D18456D9388857F4AFEE398","houseNumber":"1-1-1002"},{"houseId":"964BC9C5AE8442F1BEECF8C4FB5EE902","houseNumber":"1-1-1101"},{"houseId":"7636B2C925824952B01A4D45B79BB09D","houseNumber":"1-1-1102"},{"houseId":"627FF123441743D7AE0501D9B25F0F59","houseNumber":"1-1-1201"},{"houseId":"E1E3E429AEE1469DA94629691EF5E38E","houseNumber":"1-1-1202"},{"houseId":"1119FFCCCC704FFC8DEA761C2703E518","houseNumber":"1-1-1301"},{"houseId":"373B80807CC04B67BD5051295125865A","houseNumber":"1-1-1302"},{"houseId":"A6213EA8B6B241089B4E762736157E9A","houseNumber":"1-1-1401"},{"houseId":"41609CBBC9C84359BC38563F93BB8AEA","houseNumber":"1-1-1402"},{"houseId":"8D7B748619884E31840FFE63175329C3","houseNumber":"1-1-1501"},{"houseId":"62192B32B7604EC892697FFACE673E1E","houseNumber":"1-1-1502"},{"houseId":"68E150887E14452BA51E399F61648B57","houseNumber":"1-1-1601"},{"houseId":"93120384C3774F1BAF62499087BF26AD","houseNumber":"1-1-1602"},{"houseId":"44F24C33CE5D43B7BD54C26248942896","houseNumber":"1-1-1701"},{"houseId":"F49936BD96DE46AE9E50BB851A7C862F","houseNumber":"1-1-1702"},{"houseId":"69F1F80734DA4B14A38A1F8534A58F71","houseNumber":"1-1-1801"},{"houseId":"6B63DFF0F7B44FA1B8C797788FF9B36B","houseNumber":"1-1-1802"}]}
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
             * houseId : 56BB83E29786405FA833694733413DBF
             * houseNumber : 1-1-301
             */

            private String houseId;
            private String houseNumber;

            public String getHouseId() {
                return houseId;
            }

            public void setHouseId(String houseId) {
                this.houseId = houseId;
            }

            public String getHouseNumber() {
                return houseNumber;
            }

            public void setHouseNumber(String houseNumber) {
                this.houseNumber = houseNumber;
            }
        }
    }
}
