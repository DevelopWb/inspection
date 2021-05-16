package com.juntai.wisdom.inspection.home_page;

import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.disabled.basecomponent.mvp.IView;

/**
 * @aouther tobato
 * @description 描述
 * @date 2020/3/12 16:00
 */
public interface HomePageContract {


    public  static String HOMEPAGE_MENU_FIRE_CHECK = "消防检查";
    public  static String HOMEPAGE_MENU_IMPORTANTER = "重点人员";
    public  static String HOMEPAGE_MENU_SECURITY_CHECK = "治安巡检";
    public  static String HOMEPAGE_MENU_FLOATING_POPULATION = "流动人口";


    interface IHomePageView extends IView {

    }

    interface IHomePagePresent extends IPresenter<IHomePageView> {
    }

}
