package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\6\26 0026.
 */

public class Vipregister {


    /**
     * flag : 1
     * code : R00001
     * desc : 注册成功
     * content : {"data":{"username":"18003821286","nickname":"wew","type":0,"gender":null,"age":null,"birthday":null,"mobile":null,"id":220,"headImg":null,"certId":null,"realName":null,"real":null}}
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
         * data : {"username":"18003821286","nickname":"wew","type":0,"gender":null,"age":null,"birthday":null,"mobile":null,"id":220,"headImg":null,"certId":null,"realName":null,"real":null}
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
             * username : 18003821286
             * nickname : wew
             * type : 0
             * gender : null
             * age : null
             * birthday : null
             * mobile : null
             * id : 220
             * headImg : null
             * certId : null
             * realName : null
             * real : null
             */

            private String username;
            private String nickname;
            private int type;
            private Object gender;
            private Object age;
            private Object birthday;
            private Object mobile;
            private int id;
            private Object headImg;
            private Object certId;
            private Object realName;
            private Object real;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public Object getGender() {
                return gender;
            }

            public void setGender(Object gender) {
                this.gender = gender;
            }

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getHeadImg() {
                return headImg;
            }

            public void setHeadImg(Object headImg) {
                this.headImg = headImg;
            }

            public Object getCertId() {
                return certId;
            }

            public void setCertId(Object certId) {
                this.certId = certId;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
            }

            public Object getReal() {
                return real;
            }

            public void setReal(Object real) {
                this.real = real;
            }
        }
    }
}
