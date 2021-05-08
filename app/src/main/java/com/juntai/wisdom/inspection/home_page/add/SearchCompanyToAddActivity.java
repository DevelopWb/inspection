package com.juntai.wisdom.inspection.home_page.add;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.CompanysAdapter;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  添加单位
 * @date 2021/5/6 15:36
 */
public class SearchCompanyToAddActivity extends BaseSearchToAddActivity {

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        SearchedUnitsBean.DataBean.DatasBean bean =
                (SearchedUnitsBean.DataBean.DatasBean) adapter.getData().get(position);
        if (0==bean.getIsAdd()) {
            //未添加
            startActivity(new Intent(mContext, AddUnitActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY,bean));  
        }else {
            // TODO: 2021/5/8 已添加 需要跳转到详情列表 
        
        }
        

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new CompanysAdapter(R.layout.check_item);
    }

    @Override
    protected void startSearch(String s) {
        if (TextUtils.isEmpty(s)) {
            ToastUtils.toast(mContext, "请输入要搜索的内容");
            finishLoading();
            return;
        }
        mPresenter.searchCompanys(getBaseBuilder().add("departmentId",
                String.valueOf(UserInfoManager.getDepartmentId()))
                        .add("keyword", s).add("pageSize", String.valueOf(pagesize)).add("currentPage",
                        String.valueOf(currentPage)).build(),
                AppHttpPath.SEARCH_COMPANYS);
    }

    @Override
    protected String getTitleName() {
        return ADD_COMPANY;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        SearchedUnitsBean searchedUnitsBean = (SearchedUnitsBean) o;
        if (searchedUnitsBean != null) {
            SearchedUnitsBean.DataBean dataBean = searchedUnitsBean.getData();
            if (dataBean != null) {
                List<SearchedUnitsBean.DataBean.DatasBean> arrays = dataBean.getDatas();
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
