package com.juntai.wisdom.inspection.home_page.firecheck;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;

import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionSearchActivity;

/**
 * @aouther tobato
 * @description 描述  搜寻所有的企业
 *
 * @date 2021/4/29 16:58
 */
public class CompanysActivity extends BaseInspectionSearchActivity implements BaseInspectContract.IInspectView {

    @Override
    protected String[] getTabTitles() {
        return new String[]{BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL,
                BaseInspectContract.IMPORTANTOR_TAB_TITLE_TO_CHECK,
                BaseInspectContract.IMPORTANTOR_TAB_TITLE_REPAIRING,BaseInspectContract.IMPORTANTOR_TAB_TITLE_IS_OK};
    }

    @Override
    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();
        arrays.append(0, CompanysFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL));
        arrays.append(1,CompanysFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_TO_CHECK));
        arrays.append(2,CompanysFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_REPAIRING));
        arrays.append(3,CompanysFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_IS_OK));
        return arrays;
    }

    @Override
    protected String getTitleName() {
        return "消防检查";
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }

}
