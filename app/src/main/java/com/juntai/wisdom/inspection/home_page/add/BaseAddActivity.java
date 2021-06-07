package com.juntai.wisdom.inspection.home_page.add;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.home_page.add.importantor.ManualAddImportantorActivity;
import com.juntai.wisdom.inspection.home_page.add.inspectionsite.ManualAddInspectionSiteActivity;
import com.juntai.wisdom.inspection.home_page.add.unit.ManualAddUnitActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/6 15:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/6 15:07
 */
public abstract class BaseAddActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView,
        View.OnClickListener {
    private SearchView mSearchSv;
    private ImageView mAddIv;
    private RecyclerView mRecyclerview;
    protected SmartRefreshLayout mSmartrefreshlayout;
    protected BaseQuickAdapter adapter;


    public int currentPage = 1, pagesize = 10;

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_add;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        mSearchSv = (SearchView) findViewById(R.id.search_sv);
        mSearchSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                startSearch(s);
                getViewFocus(mRecyclerview);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        mAddIv = (ImageView) findViewById(R.id.add_iv);
        mAddIv.setOnClickListener(this);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        adapter = getAdapter();
        adapter.setEmptyView(getAdapterEmptyView("一条数据也没有，快去添加吧",-1));
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onAdapterItemClick(adapter, position);
            }
        });
        mSmartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                currentPage = 1;
                startSearch(mSearchSv.getQuery().toString().trim());
            }
        });
        mSmartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                currentPage++;
                startSearch(mSearchSv.getQuery().toString().trim());
            }
        });
    }

    protected abstract void onAdapterItemClick(BaseQuickAdapter adapter, int position);

    protected abstract BaseQuickAdapter getAdapter();

    protected abstract void startSearch(String s);

    protected abstract String getTitleName();

    @Override
    public void onSuccess(String tag, Object o) {
        finishLoading();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_iv:
                switch (getTitleName()) {
                    case BaseInspectionActivity.ADD_UNIT:
                        //手动添加单位
                        startActivity(new Intent(mContext, ManualAddUnitActivity.class));
                        break;
                    case BaseInspectionActivity.ADD_INSPECTION_SITE:
                        //手动巡检地址
                        startActivity(new Intent(mContext, ManualAddInspectionSiteActivity.class));
                        break;
                    case BaseInspectionActivity.ADD_IMPORTANTOR:
                        //手动重点人员
                        startActivity(new Intent(mContext, ManualAddImportantorActivity.class));
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    /**
     * 停止加载
     */
    protected  void  finishLoading(){
        mSmartrefreshlayout.finishRefresh();
        mSmartrefreshlayout.finishLoadMore();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (BASE_REQUEST_RESULT==requestCode) {
            startSearch(mSearchSv.getQuery().toString());
        }
    }

}
