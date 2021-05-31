package com.juntai.wisdom.inspection.home_page.firecheck.worker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.wisdom.inspection.bean.firecheck.WorkerDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseRecordActivity;

/**
 * @aouther tobato
 * @description 描述  从业人员详情
 * @date 2021/5/31 14:55
 */
public class WorkerDetailInfoActivity extends BaseInspectionActivity {

    @Override
    protected String getTitleName() {
        return "从业人员详情";
    }

    @Override
    public void initData() {
        adapter.setDetail(true);
        int id = getIntent().getIntExtra(BaseRecordActivity.ID,0);
        mPresenter.getWorkerDetail(getBaseBuilder().add("staffId",String.valueOf(id)).build(),"");

    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        WorkerDetailBean  workerDetailBean = (WorkerDetailBean) o;
        if (workerDetailBean != null) {
            WorkerDetailBean.DataBean dataBean =  workerDetailBean.getData();
            if (dataBean != null) {
                adapter.setNewData(mPresenter.getWorkerData(dataBean,true));
            }
        }
    }
}
