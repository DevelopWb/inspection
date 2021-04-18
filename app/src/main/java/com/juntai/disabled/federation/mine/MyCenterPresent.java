package com.juntai.disabled.federation.mine;


import android.annotation.SuppressLint;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.LogUtil;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.disabled.federation.AppNetModule;
import com.juntai.disabled.federation.MyApp;
import com.juntai.disabled.federation.R;
import com.juntai.disabled.federation.bean.MyMenuBean;
import com.juntai.disabled.federation.bean.UserBean;
import com.juntai.disabled.federation.mine.setting.MySettingActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyCenterPresent extends BasePresenter<IModel, MyCenterContract.ICenterView> implements MyCenterContract.ICenterPresent {
    List<MyMenuBean> menuBeans = new ArrayList<>();
    private IView iView;

    public void setCallBack(IView iView) {
        this.iView = iView;
    }
    @Override
    protected IModel createModel() {
        return null;
    }




    @Override
    public void initList() {
        menuBeans.clear();
        menuBeans.add(new MyMenuBean("我的消息", 0, R.mipmap.my_message, MyCenterContract.CENTER_SETTING_TAG, MySettingActivity.class));
        menuBeans.add(new MyMenuBean("个人设置", -1, R.mipmap.my_set_list, MyCenterContract.CENTER_SETTING_TAG, MySettingActivity.class));
    }

    @Override
    public List<MyMenuBean> getMenuBeans(){
        return menuBeans;
    }

}
