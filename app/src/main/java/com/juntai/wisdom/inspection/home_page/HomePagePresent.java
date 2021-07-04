package com.juntai.wisdom.inspection.home_page;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppNetModule;
import com.juntai.wisdom.inspection.bean.HomePageMenuBean;
import com.juntai.wisdom.inspection.bean.search.SearchBean;
import com.juntai.wisdom.inspection.bean.search.SearchResultBean;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

import java.util.ArrayList;
import java.util.List;

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



    protected List<HomePageMenuBean> getMenuList(){

        List<HomePageMenuBean> arrays = new ArrayList<>();

        //2代表警务助理  警务助理不能消防检查 和 重点人员走访
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_FIRE_CHECK,"Fire inspection",
                R.mipmap.home_menu_fire));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_IMPORTANTER,"Key persionnel",
                R.mipmap.home_menu_importantor));

        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_SECURITY_CHECK,"The security check", R.mipmap.home_menu_check));
        arrays.add(new HomePageMenuBean(HomePageContract.HOMEPAGE_MENU_FLOATING_POPULATION,"Floating population",R.mipmap.home_menu_peoples));

        return arrays;
    }

    /**
     * @param requestBody
     * @param tag
     */
    public void search(RequestBody requestBody, String tag) {

        AppNetModule.createrRetrofit()
                .search(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SearchBean>(getView()) {
                    @Override
                    public void onSuccess(SearchBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });

    }
    /**
     * @param requestBody
     * @param tag
     */
    public void searchMore(RequestBody requestBody, String tag) {

        AppNetModule.createrRetrofit()
                .searchMore(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SearchResultBean>(getView()) {
                    @Override
                    public void onSuccess(SearchResultBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });

    }
}
