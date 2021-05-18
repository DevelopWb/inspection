package com.juntai.wisdom.inspection.home_page.add.importantor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.utils.HawkProperty;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  手动添加重点人员
 *
 * @date 2021/5/13 10:18
 */
public class ManualAddImportantorActivity extends BaseAddImportantorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected String getHawkKey() {
        return HawkProperty.ADD_IMPORTANTOR_KEY;
    }

    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        mPresenter.manualAddImportantor(builder.build(), AppHttpPath.SEARCH_IMPORTANTOR_TO_ADD);
    }

    @Override
    protected String getTitleName() {
        return ADD_IMPORTANTOR;
    }
}
