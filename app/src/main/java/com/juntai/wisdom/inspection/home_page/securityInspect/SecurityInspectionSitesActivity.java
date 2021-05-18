package com.juntai.wisdom.inspection.home_page.securityInspect;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.bean.inspectionsite.AllInspectionSiteBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.home_page.add.inspectionsite.InspectionSiteAdapter;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.utils.StringTools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  搜索所有的治安巡检点
 * @date 2021/4/18 16:43
 */
public class SecurityInspectionSitesActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    private SearchView mSearchContentSv;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    private InspectionSiteAdapter adapter;
    public int currentPage = 1, pagesize = 10;
    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_security_check_site;
    }

    @Override
    public void initView() {
        setTitleName("治安巡检点");
        mSearchContentSv = (SearchView) findViewById(R.id.search_content_sv);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new InspectionSiteAdapter(R.layout.check_item, true);
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!StringTools.isStringValueOk(s)) {
                    ToastUtils.warning(mContext, "请输入要搜索的内容");
                    return false;
                }
                startSearch(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        mSmartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                currentPage = 1;
                startSearch(mSearchContentSv.getQuery().toString().trim());
            }
        });
        mSmartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                currentPage++;
                startSearch(mSearchContentSv.getQuery().toString().trim());
            }
        });
    }

    private void startSearch(String s) {
        mPresenter.searchInspectionSitesAdded(getBaseBuilder()
                        .add("keyword", s).add("pageSize", String.valueOf(pagesize)).add("currentPage",
                        String.valueOf(currentPage)).build(),
                AppHttpPath.SEARCH_ADDED_INSPECTION_SITES);
    }

    @Override
    public void initData() {

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InspectionSiteBean.DataBean dataBean = (InspectionSiteBean.DataBean) adapter.getData().get(position);
                startActivity(new Intent(mContext, SecurityInspectionSiteInfoActivity.class).putExtra(BaseInspectionInfoActivity.BASE_ID,
                        dataBean.getId()));
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                InspectionSiteBean.DataBean dataBean = (InspectionSiteBean.DataBean) adapter.getData().get(position);

                switch (view.getId()) {
                    case R.id.item_navigation_tv:
                        navigationLogic(new LatLng(Double.parseDouble(dataBean.getLatitude()),
                                Double.parseDouble(dataBean.getLongitude())),dataBean.getGpsAddress());
                        break;
                    default:
                        break;
                }
            }
        });

    }


    @Override
    public void onSuccess(String tag, Object o) {
        mSmartrefreshlayout.finishRefresh();
        mSmartrefreshlayout.finishLoadMore();
        getViewFocus(mRecyclerview);
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
