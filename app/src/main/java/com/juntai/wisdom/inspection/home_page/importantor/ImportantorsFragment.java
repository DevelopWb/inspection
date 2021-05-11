package com.juntai.wisdom.inspection.home_page.importantor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.base.BaseRecyclerviewFragment;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/29 16:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/29 16:22
 */
public class ImportantorsFragment extends BaseRecyclerviewFragment<BaseInspectPresent> implements BaseInspectContract.IInspectView {
    @Override
    protected void lazyLoad() {

    }

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    public static ImportantorsFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BaseInspectContract.IMPORTANTOR_TAB_TITLES, type);
        ImportantorsFragment fragment = new ImportantorsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        adapter.setNewData(getBaseActivity().getTestData());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext,ImportantorInfoActivity.class));
            }
        });
    }

    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new ImportantorAdapter(R.layout.check_item);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
    }
}
