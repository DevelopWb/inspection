package com.juntai.wisdom.inspection.bean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/7/3 11:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/7/3 11:46
 */
public class BaseStringBean {

    private  int  grivityType;
    private  String  content;

    public BaseStringBean(int grivityType, String content) {
        this.grivityType = grivityType;
        this.content = content;
    }

    public int getGrivityType() {
        return grivityType;
    }

    public void setGrivityType(int grivityType) {
        this.grivityType = grivityType;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }
}
