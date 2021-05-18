package com.juntai.wisdom.inspection.home_page.add.importantor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  搜索添加重点人员
 * @date 2021/5/13 10:16
 */
public class SearchAddImportantorActivity extends BaseAddImportantorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected String getHawkKey() {
        return HawkProperty.ADD_IMPORTANTOR_KEY+bean.getId();
    }
    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        if (bean != null) {
            builder.addFormDataPart("keyId",String.valueOf(bean.getId()));
        }
        mPresenter.searchAddImportantor(builder.build(), AppHttpPath.SEARCH_IMPORTANTOR_TO_ADD);
    }

    @Override
    protected String getTitleName() {
        return ADD_IMPORTANTOR;
    }

}
