package com.juntai.wisdom.inspection.home_page.importantor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.importantor.AllImportantorBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.home_page.add.importantor.ImportantorAdapter;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.base.BaseRecyclerviewFragment;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseSearchActivity;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/29 16:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/29 16:22
 */
public class ImportantorsFragment extends BaseRecyclerviewFragment<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    private int currentPage = 1;
    private int pageSize = 10;
    private String tag;

    @Override
    protected void lazyLoad() {
        tag = getArguments().getString(BaseInspectContract.TAB_TITLES);
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
                ImportantorBean.DataBean datasBean =
                        (ImportantorBean.DataBean) adapter.getData().get(position);
                startActivity(new Intent(mContext, ImportantorInfoActivity.class).putExtra(BaseInspectionInfoActivity.BASE_ID,datasBean.getId()));
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ImportantorBean.DataBean datasBean =
                        (ImportantorBean.DataBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.item_navigation_tv:
                        String lat = datasBean.getLatitude();
                        String lng = datasBean.getLongitude();
                        if (TextUtils.isEmpty(lat) || TextUtils.isEmpty(lng)) {
                            ToastUtils.toast(mContext, "无法获取位置信息");
                            return;
                        }
                        getBaseAppActivity().navigationLogic(new LatLng(Double.parseDouble(lat),
                                Double.parseDouble(lng)), datasBean.getGpsAddress());
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

    public static ImportantorsFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BaseInspectContract.TAB_TITLES, type);
        ImportantorsFragment fragment = new ImportantorsFragment();
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

    @Override
    protected void freshlayoutOnRefresh() {
        currentPage = 1;
        getAdapterData();
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new ImportantorAdapter(R.layout.check_item, true);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        if (AppHttpPath.SEARCH_ALL_ADDED_IMPORTANTOR.equals(tag)) {
            AllImportantorBean searchedBean = (AllImportantorBean) o;
            if (searchedBean != null) {
                AllImportantorBean.DataBean dataBean = searchedBean.getData();
                if (dataBean != null) {
                    List<ImportantorBean.DataBean> arrays = dataBean.getDatas();
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
    }

    /**
     * 获取数据
     */
    private void getAdapterData() {
        String keyWord = ((BaseSearchActivity) getActivity()).getKeyword();
        mPresenter.getAllAddedImportantor(getBaseAppActivity().getBaseBuilder().add("keyword", keyWord)
                        .add("state", String.valueOf(getVisitStatus())).add("typeId",
                        String.valueOf(((BaseSearchActivity) getActivity()).currentTypeId)).add("pageSize",
                        String.valueOf(pageSize)).add("currentPage", String.valueOf(currentPage)).build(),
                AppHttpPath.SEARCH_ALL_ADDED_IMPORTANTOR);
    }

    /**
     * 获取采访类型  单位检查状态（1待检查；2整改中；3合格）
     *
     * @return
     */
    private int getVisitStatus() {

        int status = 0;
        switch (tag) {
            case BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL:
                status = 0;
                break;
            case BaseInspectContract.IMPORTANTOR_TAB_TITLE_TO_VISITE:
                status = 1;
                break;
            case BaseInspectContract.IMPORTANTOR_TAB_TITLE_VISITED:
                status = 2;
                break;
            default:
                break;
        }
        return status;
    }
}
