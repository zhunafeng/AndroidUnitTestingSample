package com.sample.ut.zhu.bean;

/**
 * @Description:
 * @Author: Nafeng Zhu
 * @Time: 2017/11/18 11:54.
 */

public class Banana extends Fruit{

    private static String COLOR = "yellow";

    public Banana() {}

    public static String getColor() {
        return COLOR;
    }

    public String getBananaInfo() {
        return flavor() + getColor();
    }

    private String flavor() {
        return "sweet";
    }

    public final boolean isLike() {
        return true;
    }
}
