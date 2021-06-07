package com.juntai.wisdom.inspection.home_page.add.unit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.firecheck.SearchedUnitsBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.add.BaseAddActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitsAdapter;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  添加单位
 * @date 2021/5/6 15:36
 */
public class AddUnitActivity extends BaseAddActivity {

    @Override
    public void initData() {
        startSearch("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {
        UnitDetailBean.DataBean bean =
                (UnitDetailBean.DataBean) adapter.getData().get(position);
        if (0==bean.getIsAdd()) {
            //未添加  搜索添加 需要将系统封面清空
            bean.setCoverPicture(null);
            startActivityForResult(new Intent(mContext, SearchAddUnitActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY,bean),BASE_REQUEST_RESULT);
        }else {
            ToastUtils.toast(mContext,"该单位已添加");
        
        }
        

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new UnitsAdapter(R.layout.check_item,false);
    }

    @Override
    protected void startSearch(String s) {
        mPresenter.searchCompanys(getBaseBuilder()
                        .add("keyword", s).add("pageSize", String.valueOf(pagesize)).add("currentPage",
                        String.valueOf(currentPage)).build(),
                AppHttpPath.SEARCH_COMPANYS);
    }

    @Override
    protected String getTitleName() {
        return BaseInspectionActivity.ADD_UNIT;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        SearchedUnitsBean searchedUnitsBean = (SearchedUnitsBean) o;
        if (searchedUnitsBean != null) {
            SearchedUnitsBean.DataBean dataBean = searchedUnitsBean.getData();
            if (dataBean != null) {
                List<UnitDetailBean.DataBean> arrays = dataBean.getDatas();
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
