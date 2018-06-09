package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\12\22 0022.
 */

public class DxbossgetTelbalance {


    /**
     * flag : 1
     * code : R00001
     * content : {"data":{"flag":1,"code":"1","sessionId":null,"content":{"nowCharge":"12.90","balance":"2.1708000000000003","feePayable":"0.00","tel":"17719931988","userName":"李**"},"desc":"查询成功"}}
     * desc : 查询成功
     */
    private int flag;
    private String code;
    private ContentEntity content;
    private String desc;

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setContent(ContentEntity content) {
        this.content = content;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFlag() {
        return flag;
    }

    public String getCode() {
        return code;
    }

    public ContentEntity getContent() {
        return content;
    }

    public String getDesc() {
        return desc;
    }

    public class ContentEntity {
        /**
         * data : {"flag":1,"code":"1","sessionId":null,"content":{"nowCharge":"12.90","balance":"2.1708000000000003","feePayable":"0.00","tel":"17719931988","userName":"李**"},"desc":"查询成功"}
         */
        private DataEntity data;

        public void setData(DataEntity data) {
            this.data = data;
        }

        public DataEntity getData() {
            return data;
        }

        public class DataEntity {
            /**
             * flag : 1
             * code : 1
             * sessionId : null
             * content : {"nowCharge":"12.90","balance":"2.1708000000000003","feePayable":"0.00","tel":"17719931988","userName":"李**"}
             * desc : 查询成功
             */
            private int flag;
            private String code;
            private String sessionId;
            private ContentEntitys content;
            private String desc;

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setSessionId(String sessionId) {
                this.sessionId = sessionId;
            }

            public void setContent(ContentEntitys content) {
                this.content = content;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getFlag() {
                return flag;
            }

            public String getCode() {
                return code;
            }

            public String getSessionId() {
                return sessionId;
            }

            public ContentEntitys getContent() {
                return content;
            }

            public String getDesc() {
                return desc;
            }

            public class ContentEntitys {
                /**
                 * nowCharge : 12.90
                 * balance : 2.1708000000000003
                 * feePayable : 0.00
                 * tel : 17719931988
                 * userName : 李**
                 */
                private String nowCharge;
                private String balance;
                private String feePayable;
                private String tel;
                private String userName;

                public void setNowCharge(String nowCharge) {
                    this.nowCharge = nowCharge;
                }

                public void setBalance(String balance) {
                    this.balance = balance;
                }

                public void setFeePayable(String feePayable) {
                    this.feePayable = feePayable;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getNowCharge() {
                    return nowCharge;
                }

                public String getBalance() {
                    return balance;
                }

                public String getFeePayable() {
                    return feePayable;
                }

                public String getTel() {
                    return tel;
                }

                public String getUserName() {
                    return userName;
                }
            }
        }
    }
}
