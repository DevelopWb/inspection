package com.juntai.disabled.federation.home_page;


import android.view.View;

import com.juntai.disabled.basecomponent.base.BaseMvpFragment;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.disabled.federation.mine.MyCenterContract;

/**
 * @aouther tobato
 * @description 描述  homepage
 * @date 2021/4/17 14:59
 */
public class HomePageFragment extends BaseMvpFragment<HomePagePresent> implements MyCenterContract.ICenterView,
        View.OnClickListener {

    @Override
    protected int getLayoutRes() {
        return R.layout.homepage_fg;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void lazyLoad() {
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }


    @Override
    public void onClick(View v) {
    }

    @Override
    public void onSuccess(String tag, Object o) {
    }

    @Override
    public void onError(String tag, Object o) {
        ToastUtils.error(mContext, String.valueOf(o));
    }

}
