package com.juntai.disabled.federation.mine;


import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.federation.bean.MyMenuBean;

import java.io.File;
import java.util.List;

import retrofit2.http.Query;

/**
 * Describe: 个人信息接口类
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public interface MyCenterContract {
    String SET_UPDATE_TAG = "setUpdateTag";
    String SET_CLEAR_TAG ="setClearTag";
    //设置相关
    String SET_UPDATE_PSD_TAG = "setUpdatePsdTag";
    String SET_UPDATE_TEL_TAG = "setUpdateTelTag";
    String SET_WEIXIN_TAG ="setWeiXinTag";
    String SET_QQ_TAG = "setQQTag";
    String SET_ABOUT_TAG ="setAboutTag";
    String CENTER_SETTING_TAG ="centerSettingTag";

    interface ICenterView extends IView {
    }

    interface ICenterPresent {
        void initList();

        List<MyMenuBean> getMenuBeans();
    }
}
