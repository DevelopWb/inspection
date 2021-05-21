package com.juntai.wisdom.inspection.home_page.securityInspect;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordListBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/15 15:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 15:51
 */
public class SecurityInspectRecordListAdapter extends BaseQuickAdapter<SecurityInspectRecordListBean.DataBean.DatasBean,
        BaseViewHolder> {
    public SecurityInspectRecordListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecurityInspectRecordListBean.DataBean.DatasBean item) {
        helper.setText(R.id.record_time_tv, item.getInspectTime());
        helper.setText(R.id.record_person_tv, item.getInspectName());
        helper.setText(R.id.record_type_tv, "巡检记录");
    }
}
