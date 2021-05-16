package com.juntai.wisdom.inspection.home_page.securityInspect;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordListBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/15 15:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 15:51
 */
public class SecurityInspectRecordAdapter extends BaseQuickAdapter<SecurityInspectRecordListBean.DataBean.DatasBean, BaseViewHolder> {
    public SecurityInspectRecordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecurityInspectRecordListBean.DataBean.DatasBean item) {

    }
}
