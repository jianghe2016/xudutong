package com.xdt.xudutong.waituse;

/**
 * Created by Administrator on 2018\3\9 0009.
 */

public class Littlegreenexchange {
    private String littlestartdate;
    private String littleenddate;
    @Override
    public String toString() {

        return "Littlegreenexchange{" +
                "littlestartdate='" + littlestartdate + '\'' +
                ", littleenddate='" + littleenddate + '\'' +
                '}';
    }

    public String getLittlestartdate() {
        return littlestartdate;
    }

    public void setLittlestartdate(String littlestartdate) {
        this.littlestartdate = littlestartdate;
    }

    public String getLittleenddate() {
        return littleenddate;
    }

    public void setLittleenddate(String littleenddate) {
        this.littleenddate = littleenddate;
    }


}
