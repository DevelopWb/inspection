package com.juntai.wisdom.inspection.home_page.add.inspectionsite;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.home_page.add.unit.BaseAddUnitActivity;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  手动添加单位  manual
 * @date 2021/5/9 11:44
 */

public class ManualAddInspectionSiteActivity extends BaseAddInspectionSiteActivity {

    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected String getHawkKey() {
        return HawkProperty.ADD_INSPECRTION_SITE_KEY;
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        mPresenter.manualAddInspectSite(builder.build(), AppHttpPath.MANUAL_ADD_INSP_SITE);
    }

    @Override
    protected String getTitleName() {
        return ADD_INSPECTION_SITE;
    }

}
