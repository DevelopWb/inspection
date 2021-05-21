package com.juntai.wisdom.inspection.home_page.firecheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseRecordActivity;
import com.juntai.wisdom.inspection.home_page.securityInspect.SecurityInspectRecordListAdapter;

/**
 * @aouther tobato
 * @description 描述  消防检查列表
 * @date 2021/5/21 11:06
 */
public class FireCheckRecordListActivity extends BaseRecordActivity {

    @Override
    protected String getTitleName() {
        return "消防检查记录";
    }

    @Override
    protected void requestHisData() {
        mPresenter.getFireCheckRecords(getBaseBuilder().add("unitId", String.valueOf(id)).add("pageSize",
                String.valueOf(pagesize)).add("currentPage", String.valueOf(currentPage)).build(),
                AppHttpPath.GET_FIRE_INSPECTION_RECORDS);
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new FireCheckRecordListAdapter(R.layout.item_record);
    }


    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {

    }
}
