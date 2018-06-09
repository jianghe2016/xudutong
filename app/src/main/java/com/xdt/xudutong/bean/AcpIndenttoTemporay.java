package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2017\12\28 0028.
 */

public class AcpIndenttoTemporay {

    /**
     * flag : true
     * code : R00000
     * desc : 订单生成成功！
     * content : {"orderpPlatFormType":1,"orderSubtype":1,"orderType":1,"status":0,"orderPayChannel":1,"version_us":1,"creatTime":1,"pendingCreateTime":{"centuryOfEra":20,"yearOfEra":2017,"yearOfCentury":17,"weekyear":2017,"monthOfYear":12,"weekOfWeekyear":52,"hourOfDay":9,"minuteOfHour":24,"secondOfMinute":42,"millisOfSecond":227,"millisOfDay":33882227,"secondOfDay":33882,"minuteOfDay":564,"year":2017,"dayOfMonth":28,"dayOfWeek":4,"era":1,"dayOfYear":362,"chronology":{"zone":{"fixed":false,"uncachedZone":{"fixed":false,"cachable":true,"id":"Asia/Shanghai"},"id":"Asia/Shanghai"}},"zone":{"fixed":false,"uncachedZone":{"fixed":false,"cachable":true,"id":"Asia/Shanghai"},"id":"Asia/Shanghai"},"millis":1514424282227,"afterNow":false,"beforeNow":true,"equalNow":false},"orderId":"20171228092442226617","pendingId":"20171228092442226617","orderMoneyTotal":"1000"}
     */

    private boolean flag;
    private String code;
    private String desc;
    private ContentBean content;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
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
        /**
         * orderpPlatFormType : 1
         * orderSubtype : 1
         * orderType : 1
         * status : 0
         * orderPayChannel : 1
         * version_us : 1
         * creatTime : 1
         * pendingCreateTime : {"centuryOfEra":20,"yearOfEra":2017,"yearOfCentury":17,"weekyear":2017,"monthOfYear":12,"weekOfWeekyear":52,"hourOfDay":9,"minuteOfHour":24,"secondOfMinute":42,"millisOfSecond":227,"millisOfDay":33882227,"secondOfDay":33882,"minuteOfDay":564,"year":2017,"dayOfMonth":28,"dayOfWeek":4,"era":1,"dayOfYear":362,"chronology":{"zone":{"fixed":false,"uncachedZone":{"fixed":false,"cachable":true,"id":"Asia/Shanghai"},"id":"Asia/Shanghai"}},"zone":{"fixed":false,"uncachedZone":{"fixed":false,"cachable":true,"id":"Asia/Shanghai"},"id":"Asia/Shanghai"},"millis":1514424282227,"afterNow":false,"beforeNow":true,"equalNow":false}
         * orderId : 20171228092442226617
         * pendingId : 20171228092442226617
         * orderMoneyTotal : 1000
         */

        private int orderpPlatFormType;
        private int orderSubtype;
        private int orderType;
        private int status;
        private int orderPayChannel;
        private int version_us;
        private int creatTime;
        private PendingCreateTimeBean pendingCreateTime;
        private String orderId;
        private String pendingId;
        private String orderMoneyTotal;

        public int getOrderpPlatFormType() {
            return orderpPlatFormType;
        }

        public void setOrderpPlatFormType(int orderpPlatFormType) {
            this.orderpPlatFormType = orderpPlatFormType;
        }

        public int getOrderSubtype() {
            return orderSubtype;
        }

        public void setOrderSubtype(int orderSubtype) {
            this.orderSubtype = orderSubtype;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOrderPayChannel() {
            return orderPayChannel;
        }

        public void setOrderPayChannel(int orderPayChannel) {
            this.orderPayChannel = orderPayChannel;
        }

        public int getVersion_us() {
            return version_us;
        }

        public void setVersion_us(int version_us) {
            this.version_us = version_us;
        }

        public int getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(int creatTime) {
            this.creatTime = creatTime;
        }

        public PendingCreateTimeBean getPendingCreateTime() {
            return pendingCreateTime;
        }

        public void setPendingCreateTime(PendingCreateTimeBean pendingCreateTime) {
            this.pendingCreateTime = pendingCreateTime;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPendingId() {
            return pendingId;
        }

        public void setPendingId(String pendingId) {
            this.pendingId = pendingId;
        }

        public String getOrderMoneyTotal() {
            return orderMoneyTotal;
        }

        public void setOrderMoneyTotal(String orderMoneyTotal) {
            this.orderMoneyTotal = orderMoneyTotal;
        }

        public static class PendingCreateTimeBean {
            /**
             * centuryOfEra : 20
             * yearOfEra : 2017
             * yearOfCentury : 17
             * weekyear : 2017
             * monthOfYear : 12
             * weekOfWeekyear : 52
             * hourOfDay : 9
             * minuteOfHour : 24
             * secondOfMinute : 42
             * millisOfSecond : 227
             * millisOfDay : 33882227
             * secondOfDay : 33882
             * minuteOfDay : 564
             * year : 2017
             * dayOfMonth : 28
             * dayOfWeek : 4
             * era : 1
             * dayOfYear : 362
             * chronology : {"zone":{"fixed":false,"uncachedZone":{"fixed":false,"cachable":true,"id":"Asia/Shanghai"},"id":"Asia/Shanghai"}}
             * zone : {"fixed":false,"uncachedZone":{"fixed":false,"cachable":true,"id":"Asia/Shanghai"},"id":"Asia/Shanghai"}
             * millis : 1514424282227
             * afterNow : false
             * beforeNow : true
             * equalNow : false
             */

            private int centuryOfEra;
            private int yearOfEra;
            private int yearOfCentury;
            private int weekyear;
            private int monthOfYear;
            private int weekOfWeekyear;
            private int hourOfDay;
            private int minuteOfHour;
            private int secondOfMinute;
            private int millisOfSecond;
            private int millisOfDay;
            private int secondOfDay;
            private int minuteOfDay;
            private int year;
            private int dayOfMonth;
            private int dayOfWeek;
            private int era;
            private int dayOfYear;
            private ChronologyBean chronology;
            private ZoneBeanX zone;
            private long millis;
            private boolean afterNow;
            private boolean beforeNow;
            private boolean equalNow;

            public int getCenturyOfEra() {
                return centuryOfEra;
            }

            public void setCenturyOfEra(int centuryOfEra) {
                this.centuryOfEra = centuryOfEra;
            }

            public int getYearOfEra() {
                return yearOfEra;
            }

            public void setYearOfEra(int yearOfEra) {
                this.yearOfEra = yearOfEra;
            }

            public int getYearOfCentury() {
                return yearOfCentury;
            }

            public void setYearOfCentury(int yearOfCentury) {
                this.yearOfCentury = yearOfCentury;
            }

            public int getWeekyear() {
                return weekyear;
            }

            public void setWeekyear(int weekyear) {
                this.weekyear = weekyear;
            }

            public int getMonthOfYear() {
                return monthOfYear;
            }

            public void setMonthOfYear(int monthOfYear) {
                this.monthOfYear = monthOfYear;
            }

            public int getWeekOfWeekyear() {
                return weekOfWeekyear;
            }

            public void setWeekOfWeekyear(int weekOfWeekyear) {
                this.weekOfWeekyear = weekOfWeekyear;
            }

            public int getHourOfDay() {
                return hourOfDay;
            }

            public void setHourOfDay(int hourOfDay) {
                this.hourOfDay = hourOfDay;
            }

            public int getMinuteOfHour() {
                return minuteOfHour;
            }

            public void setMinuteOfHour(int minuteOfHour) {
                this.minuteOfHour = minuteOfHour;
            }

            public int getSecondOfMinute() {
                return secondOfMinute;
            }

            public void setSecondOfMinute(int secondOfMinute) {
                this.secondOfMinute = secondOfMinute;
            }

            public int getMillisOfSecond() {
                return millisOfSecond;
            }

            public void setMillisOfSecond(int millisOfSecond) {
                this.millisOfSecond = millisOfSecond;
            }

            public int getMillisOfDay() {
                return millisOfDay;
            }

            public void setMillisOfDay(int millisOfDay) {
                this.millisOfDay = millisOfDay;
            }

            public int getSecondOfDay() {
                return secondOfDay;
            }

            public void setSecondOfDay(int secondOfDay) {
                this.secondOfDay = secondOfDay;
            }

            public int getMinuteOfDay() {
                return minuteOfDay;
            }

            public void setMinuteOfDay(int minuteOfDay) {
                this.minuteOfDay = minuteOfDay;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getDayOfMonth() {
                return dayOfMonth;
            }

            public void setDayOfMonth(int dayOfMonth) {
                this.dayOfMonth = dayOfMonth;
            }

            public int getDayOfWeek() {
                return dayOfWeek;
            }

            public void setDayOfWeek(int dayOfWeek) {
                this.dayOfWeek = dayOfWeek;
            }

            public int getEra() {
                return era;
            }

            public void setEra(int era) {
                this.era = era;
            }

            public int getDayOfYear() {
                return dayOfYear;
            }

            public void setDayOfYear(int dayOfYear) {
                this.dayOfYear = dayOfYear;
            }

            public ChronologyBean getChronology() {
                return chronology;
            }

            public void setChronology(ChronologyBean chronology) {
                this.chronology = chronology;
            }

            public ZoneBeanX getZone() {
                return zone;
            }

            public void setZone(ZoneBeanX zone) {
                this.zone = zone;
            }

            public long getMillis() {
                return millis;
            }

            public void setMillis(long millis) {
                this.millis = millis;
            }

            public boolean isAfterNow() {
                return afterNow;
            }

            public void setAfterNow(boolean afterNow) {
                this.afterNow = afterNow;
            }

            public boolean isBeforeNow() {
                return beforeNow;
            }

            public void setBeforeNow(boolean beforeNow) {
                this.beforeNow = beforeNow;
            }

            public boolean isEqualNow() {
                return equalNow;
            }

            public void setEqualNow(boolean equalNow) {
                this.equalNow = equalNow;
            }

            public static class ChronologyBean {
                /**
                 * zone : {"fixed":false,"uncachedZone":{"fixed":false,"cachable":true,"id":"Asia/Shanghai"},"id":"Asia/Shanghai"}
                 */

                private ZoneBean zone;

                public ZoneBean getZone() {
                    return zone;
                }

                public void setZone(ZoneBean zone) {
                    this.zone = zone;
                }

                public static class ZoneBean {
                    /**
                     * fixed : false
                     * uncachedZone : {"fixed":false,"cachable":true,"id":"Asia/Shanghai"}
                     * id : Asia/Shanghai
                     */

                    private boolean fixed;
                    private UncachedZoneBean uncachedZone;
                    private String id;

                    public boolean isFixed() {
                        return fixed;
                    }

                    public void setFixed(boolean fixed) {
                        this.fixed = fixed;
                    }

                    public UncachedZoneBean getUncachedZone() {
                        return uncachedZone;
                    }

                    public void setUncachedZone(UncachedZoneBean uncachedZone) {
                        this.uncachedZone = uncachedZone;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public static class UncachedZoneBean {
                        /**
                         * fixed : false
                         * cachable : true
                         * id : Asia/Shanghai
                         */

                        private boolean fixed;
                        private boolean cachable;
                        private String id;

                        public boolean isFixed() {
                            return fixed;
                        }

                        public void setFixed(boolean fixed) {
                            this.fixed = fixed;
                        }

                        public boolean isCachable() {
                            return cachable;
                        }

                        public void setCachable(boolean cachable) {
                            this.cachable = cachable;
                        }

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }
                    }
                }
            }

            public static class ZoneBeanX {
                /**
                 * fixed : false
                 * uncachedZone : {"fixed":false,"cachable":true,"id":"Asia/Shanghai"}
                 * id : Asia/Shanghai
                 */

                private boolean fixed;
                private UncachedZoneBeanX uncachedZone;
                private String id;

                public boolean isFixed() {
                    return fixed;
                }

                public void setFixed(boolean fixed) {
                    this.fixed = fixed;
                }

                public UncachedZoneBeanX getUncachedZone() {
                    return uncachedZone;
                }

                public void setUncachedZone(UncachedZoneBeanX uncachedZone) {
                    this.uncachedZone = uncachedZone;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public static class UncachedZoneBeanX {
                    /**
                     * fixed : false
                     * cachable : true
                     * id : Asia/Shanghai
                     */

                    private boolean fixed;
                    private boolean cachable;
                    private String id;

                    public boolean isFixed() {
                        return fixed;
                    }

                    public void setFixed(boolean fixed) {
                        this.fixed = fixed;
                    }

                    public boolean isCachable() {
                        return cachable;
                    }

                    public void setCachable(boolean cachable) {
                        this.cachable = cachable;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }
                }
            }
        }
    }
}
