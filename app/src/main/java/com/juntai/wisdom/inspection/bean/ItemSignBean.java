package com.juntai.wisdom.inspection.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  签名
 * @CreateDate: 2021/1/28 16:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/28 16:39
 */
public class ItemSignBean {

    private String  signName;
    private String  signPicPath;
    private int  layoutGravity;//0点居左  1代表居右
    private boolean canSign;//是否可以签名

   public ItemSignBean(String signName, String signPicPath, int layoutGravity, boolean canSign) {
        this.signName = signName;
        this.signPicPath = signPicPath;
        this.layoutGravity = layoutGravity;
        this.canSign = canSign;
    }

    public boolean isCanSign() {
        return canSign;
    }

    public void setCanSign(boolean canSign) {
        this.canSign = canSign;
    }

    public String getSignName() {
        return signName == null ? "" : signName;
    }

    public void setSignName(String signName) {
        this.signName = signName == null ? "" : signName;
    }

    public String getSignPicPath() {
        return signPicPath == null ? "" : signPicPath;
    }

    public void setSignPicPath(String signPicPath) {
        this.signPicPath = signPicPath == null ? "" : signPicPath;
    }

    public int getLayoutGravity() {
        return layoutGravity;
    }

    public void setLayoutGravity(int layoutGravity) {
        this.layoutGravity = layoutGravity;
    }
}
