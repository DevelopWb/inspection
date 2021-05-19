package com.juntai.wisdom.inspection.home_page.firecheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.unit.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.add.unit.BaseAddUnitActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.AppUtils;
import com.juntai.wisdom.inspection.utils.HawkProperty;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  修改单位详情
 * @date 2021/5/18 15:31
 */

public class EditUnitInfoActivity extends BaseAddUnitActivity {

    @Override
    protected String getTitleName() {
        return "修改单位详情";
    }


    @Override
    protected String getCommitTextValue() {
        return "提交审核";
    }

    @Override
    protected String getHawkKey() {
        return HawkProperty.EDIT_UNIT_KEY + bean.getId();
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        mPresenter.applyEditUnitInfo( builder.addFormDataPart("unitId", String.valueOf(unitId)).addFormDataPart("version",
                String.valueOf(AppUtils.getVersionCode(mContext) + 1)).build(), AppHttpPath.APPLY_EDIT_UNIT_INFO);

    }
}
