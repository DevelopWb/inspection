package com.juntai.wisdom.inspection.home_page.add.importantor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {

    }

    @Override
    protected String getTitleName() {
        return ADD_IMPORTANTOR;
    }
}
