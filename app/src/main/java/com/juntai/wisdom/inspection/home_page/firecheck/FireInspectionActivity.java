package com.juntai.wisdom.inspection.home_page.firecheck;

import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;

import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionSearchActivity;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  搜寻所有的企业
 *
 * @date 2021/4/29 16:58
 */
public class FireInspectionActivity extends BaseInspectionSearchActivity implements BaseInspectContract.IInspectView {

    @Override
    protected String[] getTabTitles() {
        return new String[]{BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL,
                BaseInspectContract.IMPORTANTOR_TAB_TITLE_TO_CHECK,
                BaseInspectContract.IMPORTANTOR_TAB_TITLE_REPAIRING,BaseInspectContract.IMPORTANTOR_TAB_TITLE_IS_OK};
    }

    @Override
    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();
        arrays.append(0, UnitsFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL));
        arrays.append(1, UnitsFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_TO_CHECK));
        arrays.append(2, UnitsFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_REPAIRING));
        arrays.append(3, UnitsFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_IS_OK));
        return arrays;
    }

    @Override
    protected String getTitleName() {
        return "消防检查";
    }

    @Override
    public void initData() {
        mPresenter.getUnitType(getBaseBuilder().build(), AppHttpPath.UNIT_TYPE);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.UNIT_TYPE:
                IdNameBean idNameBean = (IdNameBean) o;
                if (idNameBean != null) {
                    List<IdNameBean.DataBean> arrays = idNameBean.getData();
                    setTypes(arrays);
                }
                break;
        }
    }

}
