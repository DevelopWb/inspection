package com.juntai.wisdom.inspection.bean;

import java.io.Serializable;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/6/1 9:45
 */
public class MyMenuBean  {
    private String name;
    private int unreadNum;
    private int imageId;

    public MyMenuBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public int getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(int unreadNum) {
        this.unreadNum = unreadNum;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
