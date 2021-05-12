package com.juntai.wisdom.inspection.home_page.add.inspectionsite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.inspectionsite.AllInspectionSiteBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.home_page.add.BaseAddActivity;
import com.juntai.wisdom.inspection.home_page.add.unit.SearchAddUnitActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitsAdapter;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  添加巡检点
 * @date 2021/5/6 15:36
 */
public class AddInspectSiteActivity extends BaseAddActivity {

    @Override
    public void initData() {

    }

    @Override
    protected void onLoadMoreLogic(String keywork) {
        startSearch(keywork);
    }

    @Override
    protected void onRefreshLogic(String keywork) {
        startSearch(keywork);
    }

    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {
        InspectionSiteBean.DataBean bean =
                (InspectionSiteBean.DataBean) adapter.getData().get(position);
        if (0==bean.getIsAdd()) {
            //未添加
            startActivityForResult(new Intent(mContext, SearchAddInspectionSiteActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY,bean),BASE_REQUEST_RESULT);
        }else {
            ToastUtils.toast(mContext,"该巡检点已添加");

        }

    }



    @Override
    protected BaseQuickAdapter getAdapter() {
        return new InspectionSiteAdapter(R.layout.check_item,false);
    }

    @Override
    protected void startSearch(String s) {
        if (TextUtils.isEmpty(s)) {
            finishLoading();
            ToastUtils.toast(mContext,"请输入要搜索的内容");
            return;
        }
        mPresenter.searchInspectionSitesToAdd(getBaseBuilder()
                        .add("keyword", s).add("pageSize", String.valueOf(pagesize)).add("currentPage",
                        String.valueOf(currentPage)).build(),
                AppHttpPath.SEARCH_INSPECTION_SITES_TO_ADD);
    }

    @Override
    protected String getTitleName() {
        return BaseInspectionActivity.ADD_INSPECTION_SITE;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        AllInspectionSiteBean siteBean = (AllInspectionSiteBean) o;
        if (siteBean != null) {
            AllInspectionSiteBean.DataBean dataBean = siteBean.getData();
            if (dataBean != null) {
                List<InspectionSiteBean.DataBean> arrays = dataBean.getDatas();
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
