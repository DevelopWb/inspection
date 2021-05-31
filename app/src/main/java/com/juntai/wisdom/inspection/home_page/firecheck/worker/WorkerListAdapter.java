package com.juntai.wisdom.inspection.home_page.firecheck.worker;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckRecordListBean;
import com.juntai.wisdom.inspection.bean.firecheck.WorkerDetailBean;
import com.juntai.wisdom.inspection.bean.firecheck.WorkerListBean;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

/**
 * @Author: tobato
 * @Description: 作用描述  从业人员
 * @CreateDate: 2021/5/15 15:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 15:51
 */
public class WorkerListAdapter extends BaseQuickAdapter<WorkerListBean.DataBean.DatasBean,
        BaseViewHolder> {
    public WorkerListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkerListBean.DataBean.DatasBean item) {
        ImageLoadUtil.loadImageNoCache(mContext, UrlFormatUtil.getImageOriginalUrl(item.getPersonnelPhoto()),
                helper.getView(R.id.worker_icon_iv));
        helper.setText(R.id.worker_name_tv, item.getName());
        if (TextUtils.isEmpty(item.getPostName())) {
            helper.setGone(R.id.worker_tag_tv,false);
        }else {
            helper.setGone(R.id.worker_tag_tv,true);
        }
        helper.setText(R.id.worker_tag_tv, item.getPostName());
        helper.setText(R.id.worker_ids_tv, item.getIdNumber());


    }
}
