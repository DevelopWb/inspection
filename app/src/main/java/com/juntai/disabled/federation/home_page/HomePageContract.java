package com.juntai.disabled.federation.home_page;

import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.disabled.basecomponent.mvp.IView;

import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述
 * @date 2020/3/12 16:00
 */
public interface HomePageContract {


    interface IHomePageView extends IView {

    }

    interface IHomePagePresent extends IPresenter<IHomePageView> {
    }

}
