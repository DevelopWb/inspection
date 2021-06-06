package com.juntai.wisdom.inspection.home_page.importantor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.home_page.add.importantor.BaseAddImportantorActivity;
import com.juntai.wisdom.inspection.utils.AppUtils;
import com.juntai.wisdom.inspection.utils.HawkProperty;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  编辑重点人员
 * @date 2021/6/6 9:28
 */
public class EditImportantorActivity extends BaseAddImportantorActivity {

    @Override
    protected String getHawkKey() {
        return HawkProperty.EDIT_IMPORTANTOR_KEY+importantorId;
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        mPresenter.editImportantor( builder.addFormDataPart("keyId", String.valueOf(importantorId)).addFormDataPart("version",
                String.valueOf(AppUtils.getVersionCode(mContext) + 1)).build(), AppHttpPath.EDIT_IMPORTANTOR);
    }

    @Override
    protected String getCommitTextValue() {
        return "提交审核";
    }

    @Override
    protected String getTitleName() {
        return "编辑重点人员";
    }
}
