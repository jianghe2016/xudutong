package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2018/5/14.
 */

public class HuiMinBaoYuEBean {


    /**
     * dataTotal : 0
     * flag : 0
     * code : E00020
     * content : {"data":{"frontAvaBalance":0,"backBalance":0,"todaySumPurAmount":0,"sumPurchaseAmount":0,"bonusModifyDate":"","latestBonusMod":0,"todaySumRedAmount":0,"msgId":null,"marketValue":0,"backAvaBalance":0,"sumRedControlDate":"","returnCode":4020,"returnMsg":"[4020] 通讯错误,主机和通用网关间没有活动的会话","fundId":"","bonusFlag":0,"success":false,"sumRedeemAmount":0,"frontBalance":0,"adjustCashqua":0,"sumBonusAmount":0,"sumPurControlDate":"","status":0}}
     * desc : 请求基金余额查询失败
     */
    private int dataTotal;
    private int flag;
    private String code;
    private Contents contents;
    private String desc;

    public void setDataTotal(int dataTotal) {
        this.dataTotal = dataTotal;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setContents(Contents content) {
        this.contents = content;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDataTotal() {
        return dataTotal;
    }

    public int getFlag() {
        return flag;
    }

    public String getCode() {
        return code;
    }

    public Contents getContents() {
        return contents;
    }

    public String getDesc() {
        return desc;
    }

    public class Contents {
        /**
         * data : {"frontAvaBalance":0,"backBalance":0,"todaySumPurAmount":0,"sumPurchaseAmount":0,"bonusModifyDate":"","latestBonusMod":0,"todaySumRedAmount":0,"msgId":null,"marketValue":0,"backAvaBalance":0,"sumRedControlDate":"","returnCode":4020,"returnMsg":"[4020] 通讯错误,主机和通用网关间没有活动的会话","fundId":"","bonusFlag":0,"success":false,"sumRedeemAmount":0,"frontBalance":0,"adjustCashqua":0,"sumBonusAmount":0,"sumPurControlDate":"","status":0}
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
             * frontAvaBalance : 0
             * backBalance : 0
             * todaySumPurAmount : 0
             * sumPurchaseAmount : 0
             * bonusModifyDate :
             * latestBonusMod : 0
             * todaySumRedAmount : 0
             * msgId : null
             * marketValue : 0
             * backAvaBalance : 0
             * sumRedControlDate :
             * returnCode : 4020
             * returnMsg : [4020] 通讯错误,主机和通用网关间没有活动的会话
             * fundId :
             * bonusFlag : 0
             * success : false
             * sumRedeemAmount : 0
             * frontBalance : 0
             * adjustCashqua : 0
             * sumBonusAmount : 0
             * sumPurControlDate :
             * status : 0
             */
            private int frontAvaBalance;
            private int backBalance;
            private int todaySumPurAmount;
            private int sumPurchaseAmount;
            private String bonusModifyDate;
            private int latestBonusMod;
            private int todaySumRedAmount;
            private String msgId;
            private int marketValue;
            private int backAvaBalance;
            private String sumRedControlDate;
            private int returnCode;
            private String returnMsg;
            private String fundId;
            private int bonusFlag;
            private boolean success;
            private int sumRedeemAmount;
            private int frontBalance;
            private int adjustCashqua;
            private int sumBonusAmount;
            private String sumPurControlDate;
            private int status;

            public void setFrontAvaBalance(int frontAvaBalance) {
                this.frontAvaBalance = frontAvaBalance;
            }

            public void setBackBalance(int backBalance) {
                this.backBalance = backBalance;
            }

            public void setTodaySumPurAmount(int todaySumPurAmount) {
                this.todaySumPurAmount = todaySumPurAmount;
            }

            public void setSumPurchaseAmount(int sumPurchaseAmount) {
                this.sumPurchaseAmount = sumPurchaseAmount;
            }

            public void setBonusModifyDate(String bonusModifyDate) {
                this.bonusModifyDate = bonusModifyDate;
            }

            public void setLatestBonusMod(int latestBonusMod) {
                this.latestBonusMod = latestBonusMod;
            }

            public void setTodaySumRedAmount(int todaySumRedAmount) {
                this.todaySumRedAmount = todaySumRedAmount;
            }

            public void setMsgId(String msgId) {
                this.msgId = msgId;
            }

            public void setMarketValue(int marketValue) {
                this.marketValue = marketValue;
            }

            public void setBackAvaBalance(int backAvaBalance) {
                this.backAvaBalance = backAvaBalance;
            }

            public void setSumRedControlDate(String sumRedControlDate) {
                this.sumRedControlDate = sumRedControlDate;
            }

            public void setReturnCode(int returnCode) {
                this.returnCode = returnCode;
            }

            public void setReturnMsg(String returnMsg) {
                this.returnMsg = returnMsg;
            }

            public void setFundId(String fundId) {
                this.fundId = fundId;
            }

            public void setBonusFlag(int bonusFlag) {
                this.bonusFlag = bonusFlag;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }

            public void setSumRedeemAmount(int sumRedeemAmount) {
                this.sumRedeemAmount = sumRedeemAmount;
            }

            public void setFrontBalance(int frontBalance) {
                this.frontBalance = frontBalance;
            }

            public void setAdjustCashqua(int adjustCashqua) {
                this.adjustCashqua = adjustCashqua;
            }

            public void setSumBonusAmount(int sumBonusAmount) {
                this.sumBonusAmount = sumBonusAmount;
            }

            public void setSumPurControlDate(String sumPurControlDate) {
                this.sumPurControlDate = sumPurControlDate;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getFrontAvaBalance() {
                return frontAvaBalance;
            }

            public int getBackBalance() {
                return backBalance;
            }

            public int getTodaySumPurAmount() {
                return todaySumPurAmount;
            }

            public int getSumPurchaseAmount() {
                return sumPurchaseAmount;
            }

            public String getBonusModifyDate() {
                return bonusModifyDate;
            }

            public int getLatestBonusMod() {
                return latestBonusMod;
            }

            public int getTodaySumRedAmount() {
                return todaySumRedAmount;
            }

            public String getMsgId() {
                return msgId;
            }

            public int getMarketValue() {
                return marketValue;
            }

            public int getBackAvaBalance() {
                return backAvaBalance;
            }

            public String getSumRedControlDate() {
                return sumRedControlDate;
            }

            public int getReturnCode() {
                return returnCode;
            }

            public String getReturnMsg() {
                return returnMsg;
            }

            public String getFundId() {
                return fundId;
            }

            public int getBonusFlag() {
                return bonusFlag;
            }

            public boolean isSuccess() {
                return success;
            }

            public int getSumRedeemAmount() {
                return sumRedeemAmount;
            }

            public int getFrontBalance() {
                return frontBalance;
            }

            public int getAdjustCashqua() {
                return adjustCashqua;
            }

            public int getSumBonusAmount() {
                return sumBonusAmount;
            }

            public String getSumPurControlDate() {
                return sumPurControlDate;
            }

            public int getStatus() {
                return status;
            }
        }
    }
}
