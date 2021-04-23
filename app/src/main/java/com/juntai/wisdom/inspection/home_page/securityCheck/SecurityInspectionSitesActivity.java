package com.juntai.wisdom.inspection.home_page.securityCheck;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.utils.StringTools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @aouther tobato
 * @description 描述  搜索所有的治安巡检点
 * @date 2021/4/18 16:43
 */
public class SecurityInspectionSitesActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView {

    private SearchView mSearchContentSv;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    private SecurityInspectionSiteAdapter adapter;

    @Override
    protected BaseInspectPresent createPresenter() {
        return null;
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
        adapter = new SecurityInspectionSiteAdapter(R.layout.check_item);
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        adapter.setNewData(getTestData());
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        mSearchContentSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!StringTools.isStringValueOk(s)) {
                    ToastUtils.warning(mContext, "请输入要搜索的内容");
                    return false;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void initData() {

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, SecurityInspectionSiteInfoActivity.class));
            }
        });

    }


    @Override
    public void onSuccess(String tag, Object o) {

    }

}
