package com.juntai.wisdom.inspection.home_page.importantor;

import android.os.Bundle;
import android.view.View;

import com.juntai.wisdom.inspection.bean.importantor.ImportantorVisitRecordDetailBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  走访记录详情
 * @date 2021/4/23 16:47
 */
public class ImportantorVistRecordDetailActivity extends BaseInspectionActivity {

    @Override
    public void initData() {
        if (getIntent() != null) {
            int id = getIntent().getIntExtra(BASEID, 0);
            mPresenter.getVisitRecordDetail(getBaseBuilder().add("recordId", String.valueOf(id)).build(), "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idDetail = true;
        super.onCreate(savedInstanceState);
    }


    @Override
    protected String getTitleName() {
        return "走访记录详情";
    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        ImportantorVisitRecordDetailBean recordDetailBean = (ImportantorVisitRecordDetailBean) o;
        if (recordDetailBean != null) {
            ImportantorVisitRecordDetailBean.DataBean dataBean = recordDetailBean.getData();
            adapter.setNewData(mPresenter.getVisitData(dataBean, true));
        }
    }
}
