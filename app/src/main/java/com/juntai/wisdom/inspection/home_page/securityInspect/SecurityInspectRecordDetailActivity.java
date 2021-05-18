package com.juntai.wisdom.inspection.home_page.securityInspect;

import android.os.Bundle;
import android.view.View;

import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  治安巡检记录详情
 * @date 2021/4/23 16:47
 */
public class SecurityInspectRecordDetailActivity extends BaseInspectionActivity {

    @Override
    public void initData() {
        if (getIntent() != null) {
            int id = getIntent().getIntExtra(BASEID, 0);
            mPresenter.getSecurityInspectRecordDetail(getBaseBuilder().add("recordId", String.valueOf(id)).build(), "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idDetail = true;
        super.onCreate(savedInstanceState);
    }


    @Override
    protected String getTitleName() {
        return "治安巡检详情";
    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        SecurityInspectRecordDetailBean securityInspectRecordDetailBean = (SecurityInspectRecordDetailBean) o;
        if (securityInspectRecordDetailBean != null) {
            SecurityInspectRecordDetailBean.DataBean dataBean = securityInspectRecordDetailBean.getData();
            adapter.setNewData(mPresenter.getSecurityInpsectData(dataBean, true));
        }
    }
}
