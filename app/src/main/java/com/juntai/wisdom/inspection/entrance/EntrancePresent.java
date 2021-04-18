package com.juntai.wisdom.inspection.entrance;


import android.annotation.SuppressLint;

import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.PubUtil;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.inspection.AppNetModule;
import com.juntai.wisdom.inspection.bean.UserBean;

import io.reactivex.functions.Consumer;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/3/5 15:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 15:55
 */
public class EntrancePresent extends BasePresenter<IModel, EntranceContract.IEntranceView> implements EntranceContract.IEntrancePresent {
    private IView iView;
    public void  setCallBack(IView iView) {
        this.iView = iView;
    }

    @Override
    protected IModel createModel() {
        return null;
    }


    @SuppressLint("CheckResult")
    @Override
    public void login(String account, String password, String weChatId, String qqId,String tag) {
        IView viewCallBack = null;
        if (getView()==null) {
            if (iView != null) {
                viewCallBack = iView;
                viewCallBack.showLoading();
            }
        }else{
            viewCallBack = getView();
            viewCallBack.showLoading();
        }
        IView finalViewCallBack = viewCallBack;
        AppNetModule
                .createrRetrofit()
                .login(account, password, weChatId, qqId)
                .compose(RxScheduler.ObsIoMain(viewCallBack))
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        if (finalViewCallBack != null) {
                            finalViewCallBack.hideLoading();
                            finalViewCallBack.onSuccess(tag, userBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (finalViewCallBack != null) {
                            finalViewCallBack.hideLoading();
                            finalViewCallBack.onError(tag, PubUtil.ERROR_NOTICE);
                        }
                    }
                });
    }





}
