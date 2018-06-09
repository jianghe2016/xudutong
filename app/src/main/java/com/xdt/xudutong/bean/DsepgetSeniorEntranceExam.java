package com.xdt.xudutong.bean;

import java.util.List;

/**
 * Created by Administrator on 2017\8\10 0010.
 */

public class DsepgetSeniorEntranceExam {


    /**
     * flag : 1
     * code : R00001
     * desc : 获取中考成绩数据成功
     * content : {"data":[{"finishSch":"襄城县一高教育集团第一初级中学","examNum":"161025101936","fullName":"崔依杭","cardNum":"410426200101056045","allresult":"627","languages":"98","math":"111","foreignLanguage":"113","physical":"64","chemistry":"43","moral":"51","history":"41","sports":"66","experiment":"30","carePoints":"10","regCare":"农村独生子女家庭和计划生育双女家庭的子女","exOfYear":"2016"}]}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * finishSch : 襄城县一高教育集团第一初级中学
             * examNum : 161025101936
             * fullName : 崔依杭
             * cardNum : 410426200101056045
             * allresult : 627
             * languages : 98
             * math : 111
             * foreignLanguage : 113
             * physical : 64
             * chemistry : 43
             * moral : 51
             * history : 41
             * sports : 66
             * experiment : 30
             * carePoints : 10
             * regCare : 农村独生子女家庭和计划生育双女家庭的子女
             * exOfYear : 2016
             */

            private String finishSch;
            private String examNum;
            private String fullName;
            private String cardNum;
            private String allresult;
            private String languages;
            private String math;
            private String foreignLanguage;
            private String physical;
            private String chemistry;
            private String moral;
            private String history;
            private String sports;
            private String experiment;
            private String carePoints;
            private String regCare;
            private String exOfYear;

            public String getFinishSch() {
                return finishSch;
            }

            public void setFinishSch(String finishSch) {
                this.finishSch = finishSch;
            }

            public String getExamNum() {
                return examNum;
            }

            public void setExamNum(String examNum) {
                this.examNum = examNum;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getCardNum() {
                return cardNum;
            }

            public void setCardNum(String cardNum) {
                this.cardNum = cardNum;
            }

            public String getAllresult() {
                return allresult;
            }

            public void setAllresult(String allresult) {
                this.allresult = allresult;
            }

            public String getLanguages() {
                return languages;
            }

            public void setLanguages(String languages) {
                this.languages = languages;
            }

            public String getMath() {
                return math;
            }

            public void setMath(String math) {
                this.math = math;
            }

            public String getForeignLanguage() {
                return foreignLanguage;
            }

            public void setForeignLanguage(String foreignLanguage) {
                this.foreignLanguage = foreignLanguage;
            }

            public String getPhysical() {
                return physical;
            }

            public void setPhysical(String physical) {
                this.physical = physical;
            }

            public String getChemistry() {
                return chemistry;
            }

            public void setChemistry(String chemistry) {
                this.chemistry = chemistry;
            }

            public String getMoral() {
                return moral;
            }

            public void setMoral(String moral) {
                this.moral = moral;
            }

            public String getHistory() {
                return history;
            }

            public void setHistory(String history) {
                this.history = history;
            }

            public String getSports() {
                return sports;
            }

            public void setSports(String sports) {
                this.sports = sports;
            }

            public String getExperiment() {
                return experiment;
            }

            public void setExperiment(String experiment) {
                this.experiment = experiment;
            }

            public String getCarePoints() {
                return carePoints;
            }

            public void setCarePoints(String carePoints) {
                this.carePoints = carePoints;
            }

            public String getRegCare() {
                return regCare;
            }

            public void setRegCare(String regCare) {
                this.regCare = regCare;
            }

            public String getExOfYear() {
                return exOfYear;
            }

            public void setExOfYear(String exOfYear) {
                this.exOfYear = exOfYear;
            }
        }
    }
}
