package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorVisitRecordListBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordListBean;
import com.juntai.wisdom.inspection.bean.unit.FireCheckRecordListBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  历史记录
 * @CreateDate: 2021/5/15 15:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 15:48
 */
public abstract class BaseRecordActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    public BaseQuickAdapter adapter;
    public int currentPage = 1, pagesize = 10;

    public static String ID = "id";
    public int id;

    @Override
    public int getLayoutView() {
        return R.layout.recycleview_layout;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        if (getIntent() != null) {
            id = getIntent().getIntExtra(ID, 0);
        }
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        adapter = getAdapter();
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        mSmartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                currentPage = 1;
                requestHisData();
            }
        });
        mSmartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                currentPage++;
                requestHisData();
            }
        });
    }

    protected abstract String getTitleName();

    /**
     * 请求历史记录
     */
    protected abstract void requestHisData();

    protected abstract BaseQuickAdapter getAdapter();

    @Override
    public void initData() {
        requestHisData();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onAdapterItemClick(adapter, position);
            }
        });

    }

    protected abstract void onAdapterItemClick(BaseQuickAdapter adapter, int position);

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        mSmartrefreshlayout.finishRefresh();
        mSmartrefreshlayout.finishLoadMore();
        switch (tag) {
            case AppHttpPath.SECURITY_INSPECT_RECORDS:
                SecurityInspectRecordListBean securityInspectRecordListBean = (SecurityInspectRecordListBean) o;
                if (securityInspectRecordListBean != null) {
                    List<SecurityInspectRecordListBean.DataBean.DatasBean> arrays =
                            securityInspectRecordListBean.getData().getDatas();
                    if (currentPage == 1) {
                        adapter.setNewData(arrays);
                    } else {
                        adapter.addData(arrays);
                    }
                    if (arrays != null && arrays.size() < pagesize) {
                        mSmartrefreshlayout.finishLoadMoreWithNoMoreData();
                    }
                }

                break;

            case AppHttpPath.VISIT_RECORD_LIST:
                ImportantorVisitRecordListBean importantorVisitRecordListBean = (ImportantorVisitRecordListBean) o;
                if (importantorVisitRecordListBean != null) {
                    List<ImportantorVisitRecordListBean.DataBean.DatasBean> arrays =
                            importantorVisitRecordListBean.getData().getDatas();
                    if (currentPage == 1) {
                        adapter.setNewData(arrays);
                    } else {
                        adapter.addData(arrays);
                    }
                    if (arrays != null && arrays.size() < pagesize) {
                        mSmartrefreshlayout.finishLoadMoreWithNoMoreData();
                    }
                }
                break;
            case AppHttpPath.GET_FIRE_INSPECTION_RECORDS:
                FireCheckRecordListBean fireCheckRecordListBean = (FireCheckRecordListBean) o;
                if (fireCheckRecordListBean != null) {
                    List<FireCheckRecordListBean.DataBean.DatasBean> arrays =
                            fireCheckRecordListBean.getData().getDatas();
                    if (currentPage == 1) {
                        adapter.setNewData(arrays);
                    } else {
                        adapter.addData(arrays);
                    }
                    if (arrays != null && arrays.size() < pagesize) {
                        mSmartrefreshlayout.finishLoadMoreWithNoMoreData();
                    }
                }
                break;
            default:
                break;
        }
    }

}
