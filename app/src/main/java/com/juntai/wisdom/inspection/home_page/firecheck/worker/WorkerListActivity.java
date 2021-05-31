package com.juntai.wisdom.inspection.home_page.firecheck.worker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.firecheck.WorkerListBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseRecordActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.check.FireCheckRecordListAdapter;

/**
 * @aouther tobato
 * @description 描述  从业人员
 * @date 2021/5/31 10:25
 */
public class WorkerListActivity extends BaseRecordActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "从业人员";
    }

    @Override
    public void initView() {
        super.initView();
        getTitleRightTv().setText("");
        initViewRightDrawable(getTitleRightTv(), R.mipmap.add_worker_icon, 20, 20);
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加从业人员
                startActivityForResult(new Intent(mContext, AddWorkerActivity.class).putExtra(ID, id),
                        BASE_REQUEST_RESULT);
            }
        });
    }

    @Override
    protected void requestHisData() {
        mPresenter.getWorkerList(getBaseBuilder().add("unitId", String.valueOf(id)).add("pageSize",
                String.valueOf(pagesize)).add("currentPage", String.valueOf(currentPage)).build(),
                AppHttpPath.GET_WORKER_LIST);
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new WorkerListAdapter(R.layout.item_workers);
    }

    @Override
    protected void onAdapterItemClick(BaseQuickAdapter adapter, int position) {
        WorkerListBean.DataBean.DatasBean datasBean =
                (WorkerListBean.DataBean.DatasBean) adapter.getData().get(position);

        startActivity(new Intent(mContext,WorkerDetailInfoActivity.class).putExtra(ID,datasBean.getId()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (BASE_REQUEST_RESULT == requestCode) {
            currentPage = 1;
            requestHisData();
        }
    }
}
