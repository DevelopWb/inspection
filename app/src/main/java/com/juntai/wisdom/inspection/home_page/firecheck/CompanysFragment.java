package com.juntai.wisdom.inspection.home_page.firecheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseRecyclerviewFragment;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.home_page.importantor.ImportantorAdapter;
import com.juntai.wisdom.inspection.home_page.importantor.ImportantorInfoActivity;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/29 16:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/29 16:22
 */
public class CompanysFragment extends BaseRecyclerviewFragment<BaseInspectPresent> implements BaseInspectContract.IInspectView {
    @Override
    protected void lazyLoad() {

    }

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    public static CompanysFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BaseInspectContract.IMPORTANTOR_TAB_TITLES, type);
        CompanysFragment fragment = new CompanysFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        adapter.setNewData(getBaseActivity().getTestData());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, CompanyInfoActivity.class));
            }
        });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new CompanysAdapter(R.layout.check_item);
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
