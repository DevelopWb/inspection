package com.juntai.wisdom.inspection.home_page.securityInspect;

import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordListBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseRecordActivity;

/**
 * @aouther tobato
 * @description 描述  治安巡检记录
 * @date 2021/4/23 16:14
 */
public class SecurityInspectRecordListActivity extends BaseRecordActivity {

    @Override
    protected String getTitleName() {
        return "治安巡检记录";
    }

    @Override
    protected void requestHisData() {
        mPresenter.getSecurityInspectRecords(getBaseBuilder().add("securityId", String.valueOf(id)).add("pageSize",
                String.valueOf(pagesize)).add("currentPage", String.valueOf(currentPage)).build(),
                AppHttpPath.SECURITY_INSPECT_RECORDS);
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new SecurityInspectRecordListAdapter(R.layout.item_record);
    }

    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {
        SecurityInspectRecordListBean.DataBean.DatasBean datasBean =
                (SecurityInspectRecordListBean.DataBean.DatasBean) adapter.getData().get(position);
        startActivity(new Intent(mContext, SecurityInspectRecordDetailActivity.class).putExtra(BaseInspectionActivity.BASE_ID, datasBean.getId()));
    }
}
