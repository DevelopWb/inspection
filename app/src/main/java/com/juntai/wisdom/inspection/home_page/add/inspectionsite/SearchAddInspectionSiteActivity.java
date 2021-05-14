package com.juntai.wisdom.inspection.home_page.add.inspectionsite;

import android.os.Bundle;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.home_page.add.unit.BaseAddUnitActivity;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  搜索添加巡检点
 * @date 2021/5/9 11:09
 */
public class SearchAddInspectionSiteActivity extends BaseAddInspectionSiteActivity {


    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        if (bean != null) {
            builder.addFormDataPart("securityId",String.valueOf(bean.getId()));
        }
        mPresenter.searchAddInspectSite(builder.build(), AppHttpPath.SEARCH_ADD_INSP_SITE);
    }


    @Override
    protected String getTitleName() {
        return ADD_INSPECTION_SITE;
    }

}
