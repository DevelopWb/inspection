package com.juntai.wisdom.inspection.home_page.add;

import android.os.Bundle;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitsAdapter;

/**
 * @aouther tobato
 * @description 描述  添加重点人员
 * @date 2021/5/6 15:36
 */
public class AddImportantorActivity extends BaseAddActivity {

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
        return new UnitsAdapter(R.layout.check_item,false);
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
        return BaseInspectionActivity.ADD_IMPORTANTOR;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
    }
}
