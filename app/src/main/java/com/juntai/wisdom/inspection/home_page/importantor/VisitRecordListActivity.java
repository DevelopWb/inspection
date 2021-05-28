package com.juntai.wisdom.inspection.home_page.importantor;

import android.content.Intent;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorVisitRecordListBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseRecordActivity;

/**
 * @aouther tobato
 * @description 描述  走访记录列表
 * @date 2021/5/18 11:06
 */
public class VisitRecordListActivity extends BaseRecordActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "人员走访记录";
    }

    @Override
    protected void requestHisData() {
        mPresenter.getVisitRecordList(getBaseBuilder().add("keyId", String.valueOf(id)).add("pageSize",
                String.valueOf(pagesize)).add("currentPage", String.valueOf(currentPage)).build(),
                AppHttpPath.VISIT_RECORD_LIST);

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new ImportantorVisitRecordListAdapter(R.layout.item_record);
    }

    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {
        ImportantorVisitRecordListBean.DataBean.DatasBean
                datasBean = (ImportantorVisitRecordListBean.DataBean.DatasBean) adapter.getData().get(position);
        startActivity(new Intent(mContext, ImportantorVistRecordDetailActivity.class).putExtra(BaseInspectionActivity.BASE_ID, datasBean.getId()));
    }
}
