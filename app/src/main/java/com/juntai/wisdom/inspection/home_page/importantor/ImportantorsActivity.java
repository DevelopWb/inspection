package com.juntai.wisdom.inspection.home_page.importantor;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionSearchActivity;

/**
 * @aouther tobato
 * @description 描述  重点人员
 * @date 2021/4/27 15:06
 */
public class ImportantorsActivity extends BaseInspectionSearchActivity implements BaseInspectContract.IInspectView {

    @Override
    protected String[] getTabTitles() {
        return new String[]{BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL,
                BaseInspectContract.IMPORTANTOR_TAB_TITLE_TO_VISITE, BaseInspectContract.IMPORTANTOR_TAB_TITLE_VISITED};
    }

    @Override
    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();
        arrays.append(0,ImportantorsFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL));
        arrays.append(1,ImportantorsFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_TO_VISITE));
        arrays.append(2,ImportantorsFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_VISITED));
        return arrays;
    }

    @Override
    protected String getTitleName() {
        return "重点人员";
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }

}
