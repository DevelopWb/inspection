package com.juntai.wisdom.inspection.home_page.firecheck.rectifynotice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.firecheck.RectifyNoticeListBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseRecordActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.check.FireCheckRecordListAdapter;

/**
 * @aouther tobato
 * @description 描述  整改通知书列表
 * @date 2021/5/28 15:50
 */
public class RectifyNoticeListActivity extends BaseRecordActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "整改通知书列表";
    }

    @Override
    protected void requestHisData() {
        mPresenter.getRectifyNoticeList(getBaseBuilder().add("unitId", String.valueOf(id)).add("pageSize",
                String.valueOf(pagesize)).add("currentPage", String.valueOf(currentPage)).build(),
                AppHttpPath.GET_RECTIFY_NOTICE_LIST);
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new RectifyNoticeListAdapter(R.layout.item_record);
    }

    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {
        RectifyNoticeListBean.DataBean.DatasBean datasBean = (RectifyNoticeListBean.DataBean.DatasBean) adapter.getData().get(position);
        startActivity(new Intent(mContext,RectifyNoticeDetailActivity.class)
                .putExtra(BaseInspectionActivity.BASE_STRING,"1")
                .putExtra(BaseInspectionActivity.BASE_ID,
                datasBean.getNoticeId()));

    }
}
