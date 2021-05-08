package com.juntai.wisdom.inspection.home_page.add;

import android.os.Bundle;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.home_page.firecheck.CompanysAdapter;

/**
 * @aouther tobato
 * @description 描述  添加巡检点
 * @date 2021/5/6 15:36
 */
public class SearchInspectSiteToAddActivity extends BaseSearchToAddActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new CompanysAdapter(R.layout.check_item);
    }

    @Override
    protected void startSearch(String s) {
        if (TextUtils.isEmpty(s)) {
            ToastUtils.toast(mContext,"请输入要搜索的内容");
            return;
        }
        adapter.setNewData(getTestData());
    }

    @Override
    protected String getTitleName() {
        return ADD_INSPECTION_SITE;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
    }
}
