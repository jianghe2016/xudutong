package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\6\24 0024.
 */

public class Tokenauth {


    /**
     * code : R00001
     * flag : true
     * desc : 认证成功
     * response : {"body":{"access_token":"f35ceb8e-5ef4-4f94-9176-b7631176e979","x_auth_token":"ab0da2a0-8894-4abc-9d52-c0d190be9751","refresh_token":"f0e1beff-18df-4c64-8d33-d65a165d88b9","expires_in":3600}}
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

    public class ResponseBean {
        /**
         * body : {"access_token":"f35ceb8e-5ef4-4f94-9176-b7631176e979","x_auth_token":"ab0da2a0-8894-4abc-9d52-c0d190be9751","refresh_token":"f0e1beff-18df-4c64-8d33-d65a165d88b9","expires_in":3600}
         */

        private BodyBean body;

        public BodyBean getBody() {
            return body;
        }

        public void setBody(BodyBean body) {
            this.body = body;
        }

        public class BodyBean {
            /**
             * access_token : f35ceb8e-5ef4-4f94-9176-b7631176e979
             * x_auth_token : ab0da2a0-8894-4abc-9d52-c0d190be9751
             * refresh_token : f0e1beff-18df-4c64-8d33-d65a165d88b9
             * expires_in : 3600
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
