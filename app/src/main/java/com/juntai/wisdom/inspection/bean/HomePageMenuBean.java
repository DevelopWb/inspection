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
    private  String menuEnName;
    private int menuPicId;

    public HomePageMenuBean(String menuName, String menuEnName, int menuPicId) {
        this.menuName = menuName;
        this.menuEnName = menuEnName;
        this.menuPicId = menuPicId;
    }

    public String getMenuEnName() {
        return menuEnName == null ? "" : menuEnName;
    }

    public void setMenuEnName(String menuEnName) {
        this.menuEnName = menuEnName == null ? "" : menuEnName;
    }

    public String getMenuName() {
        return TextUtils.isEmpty(menuName) ? "暂无" : menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? "" : menuName;
    }

    public int getMenuPicId() {
        return menuPicId;
    }

    public void setMenuPicId(int menuPicId) {
        this.menuPicId = menuPicId;
    }
}
