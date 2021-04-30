package com.juntai.wisdom.inspection.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  巡检详情里面按钮
 * @CreateDate: 2021/4/30 11:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/30 11:01
 */
public class ActionBean {

    private int bgRes;//背景图
    private String actionName;//
    private int actionPic;//

    public ActionBean(int bgRes, String actionName, int actionPic) {
        this.bgRes = bgRes;
        this.actionName = actionName;
        this.actionPic = actionPic;
    }

    public int getBgRes() {
        return bgRes;
    }

    public void setBgRes(int bgRes) {
        this.bgRes = bgRes;
    }

    public String getActionName() {
        return actionName == null ? "" : actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName == null ? "" : actionName;
    }

    public int getActionPic() {
        return actionPic;
    }

    public void setActionPic(int actionPic) {
        this.actionPic = actionPic;
    }
}
