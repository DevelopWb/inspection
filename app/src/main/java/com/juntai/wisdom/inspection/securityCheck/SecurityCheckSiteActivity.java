package com.juntai.wisdom.inspection.securityCheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;

/**
 * @aouther tobato
 * @description 描述  治安巡检
 * @date 2021/4/18 16:43
 */
public class SecurityCheckSiteActivity extends BaseAppActivity<SecurityPresent> implements SecurityContract.ISecurityView {

    @Override
    protected SecurityPresent createPresenter() {
        return null;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_security_check_site;
    }

    @Override
    public void initView() {
        setTitleName("治安巡检点");
    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {

    }
}
