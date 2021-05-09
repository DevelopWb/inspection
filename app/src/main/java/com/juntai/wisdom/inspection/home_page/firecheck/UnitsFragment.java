package com.juntai.wisdom.inspection.home_page.firecheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseRecyclerviewFragment;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionSearchActivity;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/29 16:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/29 16:22
 */
public class UnitsFragment extends BaseRecyclerviewFragment<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    private String unitCheckTag;//检查的状态
    private int currentPage = 1;
    private int pageSize = 10;
    private String keyWord;


    @Override
    protected void lazyLoad() {
        keyWord = ((BaseInspectionSearchActivity) getActivity()).getKeyword();
        if (TextUtils.isEmpty(keyWord)) {
            ToastUtils.toast(mContext,"请输入搜索的内容");
            return;
        }
        unitCheckTag = getArguments().getString(BaseInspectContract.IMPORTANTOR_TAB_TITLES);
        //获取相应的单位

    }

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    public static UnitsFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BaseInspectContract.IMPORTANTOR_TAB_TITLES, type);
        UnitsFragment fragment = new UnitsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, UnitInfoActivity.class));
            }
        });
    }

    @Override
    protected void freshlayoutOnLoadMore() {
        currentPage++;
    }

    @Override
    protected void freshlayoutOnRefresh() {
        currentPage = 1;
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new UnitsAdapter(R.layout.check_item);
    }

    @Override
    public void onSuccess(String tag, Object o) {
//        if (currentPage == 1) {
//            adapter.setNewData(arrays);
//        } else {
//            adapter.addData(arrays);
//        }
//        if (arrays != null && arrays.size() < pageSize) {
//            mSmartrefreshlayout.finishLoadMoreWithNoMoreData();
//        }
    }

    /**
     * 获取检查的状态  单位检查状态（1待检查；2整改中；3合格）
     *
     * @return
     */
    private int getCheckStatus() {
        int status = 0;
        switch (unitCheckTag) {
            case BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL:
                status = 0;
                break;
            case BaseInspectContract.IMPORTANTOR_TAB_TITLE_TO_CHECK:
                status = 1;
                break;
            case BaseInspectContract.IMPORTANTOR_TAB_TITLE_REPAIRING:
                status = 2;
                break;
            case BaseInspectContract.IMPORTANTOR_TAB_TITLE_IS_OK:
                status = 3;
                break;
            default:
                break;
        }
        return status;
    }
}
