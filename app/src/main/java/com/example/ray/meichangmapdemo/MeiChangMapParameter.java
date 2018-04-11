package com.example.ray.meichangmapdemo;

public class MeiChangMapParameter {

    int bg_width, bg_height;
    //为使图片居中，导致的高度差。
    int distanceHeight;
    int cvsWidth;
    int cvsHeight;

    public int getBg_width() {
        return bg_width;
    }

    public void setBg_width(int bg_width) {
        this.bg_width = bg_width;
    }

    public int getBg_height() {
        return bg_height;
    }

    public void setBg_height(int bg_height) {
        this.bg_height = bg_height;
    }

    public int getDistanceHeight() {
        return distanceHeight;
    }

    public void setDistanceHeight(int distanceHeight) {
        this.distanceHeight = distanceHeight;
    }

    public int getCvsWidth() {
        return cvsWidth;
    }

    public void setCvsWidth(int cvsWidth) {
        this.cvsWidth = cvsWidth;
    }

    public int getCvsHeight() {
        return cvsHeight;
    }

    public void setCvsHeight(int cvsHeight) {
        this.cvsHeight = cvsHeight;
    }

    public MeiChangMapParameter(int bg_width, int bg_height, int distanceHeight, int cvsWidth, int cvsHeight) {
        this.bg_width = bg_width;
        this.bg_height = bg_height;
        this.distanceHeight = distanceHeight;
        this.cvsWidth = cvsWidth;
        this.cvsHeight = cvsHeight;
    }
}
