package com.juntai.wisdom.inspection.home_page.firecheck.check;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckRecordListBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/15 15:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 15:51
 */
public class FireCheckRecordListAdapter extends BaseQuickAdapter<FireCheckRecordListBean.DataBean.DatasBean,
        BaseViewHolder> {
    public FireCheckRecordListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FireCheckRecordListBean.DataBean.DatasBean item) {
        helper.setText(R.id.record_time_tv, item.getInspectTime());
        helper.setText(R.id.record_person_tv, item.getInspectName());
        if (1==item.getPunish()) {
            //添加处罚了
            helper.setVisible(R.id.record_tag_tv,true);
        }else {
            helper.setVisible(R.id.record_tag_tv,false);
        }
        helper.setText(R.id.record_type_tv, "检查记录");
    }
}
