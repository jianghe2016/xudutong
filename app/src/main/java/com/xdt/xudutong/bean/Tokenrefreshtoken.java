package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\9\29 0029.
 */

public class Tokenrefreshtoken {

    /**
     * code : R00001
     * flag : true
     * desc : access_token刷新成功
     * response : {"body":{"access_token":"2164a2b1-c1d0-40a4-add8-a87b50c94008","x_auth_token":"9f4246cc-99f8-421d-8290-55fb5bc6a95b","refresh_token":"2f68047e-0677-498b-a6a0-2df89f9d4d2b","expires_in":60}}
     */

    private String code;
    private boolean flag;
    private String desc;
    private ResponseBean response;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * body : {"access_token":"2164a2b1-c1d0-40a4-add8-a87b50c94008","x_auth_token":"9f4246cc-99f8-421d-8290-55fb5bc6a95b","refresh_token":"2f68047e-0677-498b-a6a0-2df89f9d4d2b","expires_in":60}
         */

        private BodyBean body;

        public BodyBean getBody() {
            return body;
        }

        public void setBody(BodyBean body) {
            this.body = body;
        }

        public static class BodyBean {
            /**
             * access_token : 2164a2b1-c1d0-40a4-add8-a87b50c94008
             * x_auth_token : 9f4246cc-99f8-421d-8290-55fb5bc6a95b
             * refresh_token : 2f68047e-0677-498b-a6a0-2df89f9d4d2b
             * expires_in : 60
             */

            private String access_token;
            private String x_auth_token;
            private String refresh_token;
            private int expires_in;

            public String getAccess_token() {
                return access_token;
            }

            public void setAccess_token(String access_token) {
                this.access_token = access_token;
            }

            public String getX_auth_token() {
                return x_auth_token;
            }

            public void setX_auth_token(String x_auth_token) {
                this.x_auth_token = x_auth_token;
            }

            public String getRefresh_token() {
                return refresh_token;
            }

            public void setRefresh_token(String refresh_token) {
                this.refresh_token = refresh_token;
            }

            public int getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(int expires_in) {
                this.expires_in = expires_in;
            }
        }
    }
}
