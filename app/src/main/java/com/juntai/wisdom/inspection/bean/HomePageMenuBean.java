package com.juntai.wisdom.inspection.bean;

import android.text.TextUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 15:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 15:33
 */
public class HomePageMenuBean {


    private  String menuName;

    private int menuBgId;

    public HomePageMenuBean(String menuName, int menuBgId) {
        this.menuName = menuName;
        this.menuBgId = menuBgId;
    }

    public String getMenuName() {
        return TextUtils.isEmpty(menuName) ? "暂无" : menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? "" : menuName;
    }

    public int getMenuBgId() {
        return menuBgId;
    }

    public void setMenuBgId(int menuBgId) {
        this.menuBgId = menuBgId;
    }
}
