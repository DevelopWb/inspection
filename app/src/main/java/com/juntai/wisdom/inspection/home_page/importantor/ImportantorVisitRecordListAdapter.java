package com.juntai.wisdom.inspection.home_page.importantor;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorVisitRecordListBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordListBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/15 15:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 15:51
 */
public class ImportantorVisitRecordListAdapter extends BaseQuickAdapter<ImportantorVisitRecordListBean.DataBean.DatasBean,
        BaseViewHolder> {
    public ImportantorVisitRecordListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImportantorVisitRecordListBean.DataBean.DatasBean item) {
        helper.setText(R.id.record_time_tv, item.getCheckTime());
        helper.setText(R.id.record_person_tv, item.getNickname());
        helper.setText(R.id.record_type_tv, "走访记录");

    }
}
