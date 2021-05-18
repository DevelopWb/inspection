package com.juntai.wisdom.inspection.home_page.importantor;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.bottomDialog.MultiSelectBottomSheetDialog;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseSearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  重点人员
 * @date 2021/4/27 15:06
 */
public class ImportantorsActivity extends BaseSearchActivity implements BaseInspectContract.IInspectView {

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
        mPresenter.getImportantorTypes(getBaseBuilder().build(), AppHttpPath.IMPORTANTOR_TYPES);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        IdNameBean idNameBean = (IdNameBean) o;
        if (idNameBean != null) {
            List<IdNameBean.DataBean> arrays = new ArrayList<>();
            arrays.add(new IdNameBean.DataBean(0,"全部"));
            if (idNameBean != null) {
                arrays.addAll(idNameBean.getData());
                setTypes(arrays);
            }
        }
    }

}
