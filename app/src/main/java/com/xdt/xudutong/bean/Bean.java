package com.xdt.xudutong.bean;

/**
 * Created by Administrator on 2018/5/11.
 */

public class Bean {

    public String dataz;
    public String descs;
    public int progress;
    public int colors;

    public Bean(String dataz, String descs, int progress, int colors) {
        this.dataz = dataz;
        this.descs = descs;
        this.progress = progress;
        this.colors = colors;
    }

    public String getDataz() {
        return dataz;
    }

    public void setDataz(String dataz) {
        this.dataz = dataz;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getColors() {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }
}
