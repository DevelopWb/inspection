package com.juntai.disabled.federation.home_page;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @aouther Ma
 * @date 2019/3/14
 */
public class HomePagePresent extends BasePresenter<IModel, HomePageContract.IHomePageView> implements HomePageContract.IHomePagePresent {
    @Override
    protected IModel createModel() {
        return null;
    }


}
