package com.juntai.wisdom.inspection.home_page.firecheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.BaseRecyclerviewFragment;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseSearchActivity;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/29 16:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/29 16:22
 */
public class UnitsFragment extends BaseRecyclerviewFragment<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    private int currentPage = 1;
    private int pageSize = 10;
    private String unitCheckTag;


    @Override
    protected void lazyLoad() {
        unitCheckTag = getArguments().getString(BaseInspectContract.TAB_TITLES);
        ((BaseSearchActivity) getActivity()).setTypeTypeSelectedCallBack(new BaseSearchActivity.OnTypeSelectedCallBack() {
            @Override
            public void typeSelected() {
                currentPage = 1;
                getAdapterData();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, UnitInfoActivity.class));
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SearchedUnitsBean.DataBean.DatasBean datasBean = (SearchedUnitsBean.DataBean.DatasBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.item_navigation_tv:
                       String lat = datasBean.getLatitude();
                       String lng = datasBean.getLongitude();
                        if (TextUtils.isEmpty(lat)||TextUtils.isEmpty(lng)) {
                            ToastUtils.toast(mContext,"无法获取位置信息");
                            return;
                        }
                       getBaseAppActivity().navigationLogic(new LatLng(Double.parseDouble(lat),
                               Double.parseDouble(lng)),datasBean.getAddress());
                        break;
                    default:
                        break;
                }
            }
        });
        //获取相应的单位
        getAdapterData();
    }

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    public static UnitsFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BaseInspectContract.TAB_TITLES, type);
        UnitsFragment fragment = new UnitsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void freshlayoutOnLoadMore() {
        currentPage++;
        getAdapterData();
    }

    /**
     * 获取数据
     */
    private void getAdapterData() {
        String keyWord = ((BaseSearchActivity) getActivity()).getKeyword();
        mPresenter.searchUnitFromFireInspection(getBaseAppActivity().getBaseBuilder().add("keyword", keyWord)
                        .add("state", String.valueOf(getCheckStatus())).add("unitType",
                        String.valueOf(((BaseSearchActivity) getActivity()).currentTypeId)).add("pageSize",
                        String.valueOf(pageSize)).add("currentPage", String.valueOf(currentPage)).build(),
                AppHttpPath.SEARCH_UNIT_TO_CHECK);
    }

    @Override
    protected void freshlayoutOnRefresh() {
        currentPage = 1;
        getAdapterData();
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new UnitsAdapter(R.layout.check_item,true);
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
                if (arrays != null && arrays.size() < pageSize) {
                    mSmartrefreshlayout.finishLoadMoreWithNoMoreData();
                }
            }
        }
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
