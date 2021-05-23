package com.juntai.wisdom.inspection.home_page.firecheck.check;

import android.os.Bundle;
import android.view.View;

import com.juntai.wisdom.inspection.bean.firecheck.FireCheckBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  消防检查详情
 * @date 2021/5/21 14:46
 */
public class FireCheckRecordDetailActivity extends BaseInspectionActivity {

    @Override
    protected String getTitleName() {
        return "消防检查详情";
    }

    @Override
    protected View getFootView() {
        return null;
    }
    @Override
    public void initData() {
        if (getIntent() != null) {
            int id = getIntent().getIntExtra(BASEID, 0);
            mPresenter.getFireCheckRecordDetail(getBaseBuilder().add("recordId", String.valueOf(id)).build(), "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idDetail = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        FireCheckBean fireCheckBean = (FireCheckBean) o;
        if (fireCheckBean != null) {
            FireCheckBean.DataBean dataBean = fireCheckBean.getData();
            adapter.setNewData(mPresenter.getFireCheckDetailData(dataBean, 1==dataBean.getQualified()?true:false));

        }
    }
}
