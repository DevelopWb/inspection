package com.juntai.wisdom.inspection.home_page.securityCheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  治安巡检记录详情
 * @date 2021/4/23 16:47
 */
public class SecurityInspectRecordDetailActivity extends BaseInspectionActivity {

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getSecurityInpsectRecordDetailData());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "治安巡查详情";
    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
