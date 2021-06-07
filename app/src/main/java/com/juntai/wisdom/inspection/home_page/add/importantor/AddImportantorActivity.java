package com.juntai.wisdom.inspection.home_page.add.importantor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.importantor.AllImportantorBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.AllInspectionSiteBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.home_page.add.BaseAddActivity;
import com.juntai.wisdom.inspection.home_page.add.inspectionsite.SearchAddInspectionSiteActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitsAdapter;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  添加重点人员
 * @date 2021/5/6 15:36
 */
public class AddImportantorActivity extends BaseAddActivity {

    @Override
    public void initData() {
        startSearch("");
    }


    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {
        ImportantorBean.DataBean bean =
                (ImportantorBean.DataBean) adapter.getData().get(position);
        if (0 == bean.getIsAdd()) {
            //未添加
            startActivityForResult(new Intent(mContext, SearchAddImportantorActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY, bean), BASE_REQUEST_RESULT);
        } else {
            ToastUtils.toast(mContext, "此人已添加");

        }

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new ImportantorAdapter(R.layout.check_item, false);
    }

    @Override
    protected void startSearch(String s) {
        mPresenter.searchImportantorToAdd(getBaseBuilder()
                        .add("keyword", s).add("pageSize", String.valueOf(pagesize)).add("currentPage",
                        String.valueOf(currentPage)).build(),
                AppHttpPath.SEARCH_INSPECTION_SITES_TO_ADD);
    }

    @Override
    protected String getTitleName() {
        return BaseInspectionActivity.ADD_IMPORTANTOR;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        AllImportantorBean importantorBean = (AllImportantorBean) o;
        if (importantorBean != null) {
            AllImportantorBean.DataBean dataBean = importantorBean.getData();
            if (dataBean != null) {
                List<ImportantorBean.DataBean> arrays = dataBean.getDatas();
                if (currentPage == 1) {
                    adapter.setNewData(arrays);
                } else {
                    adapter.addData(arrays);
                }
                if (arrays != null && arrays.size() < pagesize) {
                    mSmartrefreshlayout.finishLoadMoreWithNoMoreData();
                }
            }
        }
    }
}
