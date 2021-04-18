package com.juntai.wisdom.inspection.home_page;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.HomePageMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther Ma
 * @date 2019/3/14
 */
public class HomePagePresent extends BasePresenter<IModel, HomePageContract.IHomePageView> implements HomePageContract.IHomePagePresent {
    @Override
    protected IModel createModel() {
        return null;
    }



    protected List<HomePageMenuBean> getMenuList(){

        List<HomePageMenuBean> arrays = new ArrayList<>();

        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_FIRE_CHECK, R.drawable.sp_filled_gray_circle));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_IMPORTANTER, R.drawable.sp_filled_gray_circle));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_SECURITY_CHECK, R.drawable.sp_filled_gray_circle));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_FLOATING_POPULATION, R.drawable.sp_filled_gray_circle));

        return arrays;
    }
}
