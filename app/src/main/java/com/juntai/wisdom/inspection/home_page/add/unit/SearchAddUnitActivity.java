package com.juntai.wisdom.inspection.home_page.add.unit;

import android.os.Bundle;

import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.utils.HawkProperty;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  搜索添加单位
 * @date 2021/5/9 11:09
 */
public class SearchAddUnitActivity extends BaseAddUnitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected String getHawkKey() {
        return HawkProperty.ADD_UNIT_KEY+bean.getId();
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        if (bean != null) {
            builder.addFormDataPart("unitId",String.valueOf(bean.getId()));
        }
        mPresenter.searchAddUnit(builder.build(), AppHttpPath.SEARCH_ADD_UNIT);
    }


    @Override
    protected String getTitleName() {
        return ADD_UNIT;
    }
}
