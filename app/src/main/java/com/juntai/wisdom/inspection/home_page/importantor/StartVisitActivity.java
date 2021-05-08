package com.juntai.wisdom.inspection.home_page.importantor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  开始访问
 * @date 2021/5/5 17:17
 */
public class StartVisitActivity extends BaseInspectionActivity {

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getVisitData());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "重点人员走访";
    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
