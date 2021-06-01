package com.juntai.wisdom.inspection.mine;


import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.wisdom.inspection.bean.MyMenuBean;

import java.util.List;

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
    String MY_WORK_RECORD ="我的工作记录";
    String MY_MSG ="我的消息";
    String MY_MODIFY_PWD ="修改密码";
    String MY_CLEAR_CACHE ="清理缓存";
    String MY_UPDATE ="检查更新";
    String MY_ABOUT_US ="关于我们";

    interface ICenterView extends IView {
    }

    interface ICenterPresent {
        void initList();

        List<MyMenuBean> getMenuBeans();
    }
}
