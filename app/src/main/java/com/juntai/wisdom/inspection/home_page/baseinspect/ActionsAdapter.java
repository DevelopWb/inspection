package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.support.constraint.ConstraintLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.ActionBean;

/**
 * @aouther tobato
 * @description 描述  重点人员适配器
 * @date 2021/4/29 16:24
 */

public class ActionsAdapter extends BaseQuickAdapter<ActionBean, BaseViewHolder> {


    public ActionsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ActionBean item) {
        ConstraintLayout  bgCl = helper.getView(R.id.actions_bg_cl);
        bgCl.setBackgroundResource(item.getBgRes());
        ImageLoadUtil.loadImage(mContext,item.getActionPic(),helper.getView(R.id.item_pic_iv));
        helper.setText(R.id.item_title_tv,item.getActionName());
    }
}