package com.juntai.wisdom.inspection.home_page.securityInspect;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.home_page.add.inspectionsite.BaseAddInspectionSiteActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.AppUtils;
import com.juntai.wisdom.inspection.utils.HawkProperty;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  编辑治安巡检点
 * @date 2021/4/22 16:58
 */
public class EditSecurityInspectSiteActivity extends BaseAddInspectionSiteActivity {


    @Override
    protected String getHawkKey() {
        return HawkProperty.EDIT_INSPECRTION_SITE_KEY + inspectionSiteId;
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        mPresenter.applyEditInspectionSitInfo( builder.addFormDataPart("securityId", String.valueOf(inspectionSiteId)).addFormDataPart("version",
                String.valueOf(AppUtils.getVersionCode(mContext) + 1)).build(), AppHttpPath.APPLY_EDIT_INSPECTION_SITE_INFO);

    }


    @Override
    protected String getTitleName() {
        return "修改治安巡检点";
    }


    @Override
    protected String getCommitTextValue() {
        return "提交审核";
    }
}
