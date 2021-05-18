package com.juntai.wisdom.inspection.home_page.firecheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.wisdom.inspection.bean.unit.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  修改单位详情
 * @date 2021/5/18 15:31
 */

public class EditUnitInfoActivity extends BaseCommitFootViewActivity {
    private UnitDetailBean.DataBean dataBean;
    @Override
    public void initData() {
        if (getIntent() != null) {
            dataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
        }
        adapter.setNewData(mPresenter.getUnitInfoData(dataBean,false));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "修改单位详情";
    }


    @Override
    protected String getCommitTextValue() {
        return "提交审核";
    }

    @Override
    protected void commitRequest(MultipartBody.Builder builder) {

    }

    @Override
    protected void saveDraft() {

    }
}
