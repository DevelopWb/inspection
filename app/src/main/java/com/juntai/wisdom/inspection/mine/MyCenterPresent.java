package com.juntai.wisdom.inspection.mine;


import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppNetModule;
import com.juntai.wisdom.inspection.bean.MyMenuBean;
import com.juntai.wisdom.inspection.bean.UserBean;
import com.juntai.wisdom.inspection.mine.setting.MySettingActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

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



    public void getUserInfo(RequestBody requestBody,String tag){

        AppNetModule.createrRetrofit()
                .getUserInfo(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UserBean>(getView()) {
                    @Override
                    public void onSuccess(UserBean o) {
                        if (getView() != null){
                            getView().onSuccess(tag,o);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (getView() != null){
                            getView().onError(tag,msg);
                        }
                    }
                });

    }

    /**
     * 退出登录
     * @param requestBody
     * @param tag
     */
    public void loginOut(RequestBody requestBody,String tag){

        AppNetModule.createrRetrofit()
                .loginOut(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null){
                            getView().onSuccess(tag,o);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (getView() != null){
                            getView().onError(tag,msg);
                        }
                    }
                });

    }

}
