package com.juntai.wisdom.inspection.mine.myWorkerRecords;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.MyWorkRecordsBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnitDetailBean;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

/**
 * @aouther tobato
 * @description 描述  工作记录
 * @date 2021/6/1 16:16
 */

public class WorkRecordAdapter extends BaseQuickAdapter<MyWorkRecordsBean.DataBean.DatasBean, BaseViewHolder> {


    public WorkRecordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyWorkRecordsBean.DataBean.DatasBean item) {
        ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(item.getCoverPicture()),
                helper.getView(R.id.cover_pic_iv));
        helper.setText(R.id.item_title_tv, item.getName());
        helper.setText(R.id.item_des_tv, item.getAddress());
        helper.addOnClickListener(R.id.item_navigation_tv);
        helper.setText(R.id.item_navigation_tv,"导航");
        helper.setTextColor(R.id.item_navigation_tv, ContextCompat.getColor(mContext,R.color.white));
        helper.setBackgroundRes(R.id.item_navigation_tv,R.drawable.sp_blue_square_button);


    }
}