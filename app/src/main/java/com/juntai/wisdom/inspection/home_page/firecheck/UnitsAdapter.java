package com.juntai.wisdom.inspection.home_page.firecheck;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

/**
 * @aouther tobato
 * @description 描述  公司适配器
 * @date 2021/4/29 16:24
 */

public class UnitsAdapter extends BaseQuickAdapter<SearchedUnitsBean.DataBean.DatasBean, BaseViewHolder> {

    private boolean hasNavigation = false;//导航

    public UnitsAdapter(int layoutResId,boolean hasNavigation) {
        super(layoutResId);
        this.hasNavigation = hasNavigation;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchedUnitsBean.DataBean.DatasBean item) {
        ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(item.getCoverPicture()),
                helper.getView(R.id.cover_pic_iv));
        helper.setText(R.id.item_title_tv, item.getName());
        helper.setText(R.id.item_des_tv, item.getAddress());
        helper.addOnClickListener(R.id.item_navigation_tv);
        if (hasNavigation) {
            helper.setText(R.id.item_navigation_tv,"导航");
            helper.setTextColor(R.id.item_navigation_tv, ContextCompat.getColor(mContext,R.color.white));
            helper.setBackgroundRes(R.id.item_navigation_tv,R.drawable.sp_blue_square_button);
        }else {
            if (0==item.getIsAdd()) {
                //未添加
                helper.setText(R.id.item_navigation_tv,"添加");
                helper.setTextColor(R.id.item_navigation_tv, ContextCompat.getColor(mContext,R.color.white));
                helper.setBackgroundRes(R.id.item_navigation_tv,R.drawable.sp_blue_square_button);
            }else {
                helper.setText(R.id.item_navigation_tv,"已添加");
                helper.setTextColor(R.id.item_navigation_tv, ContextCompat.getColor(mContext,R.color.black));
                helper.setBackgroundRes(R.id.item_navigation_tv,R.drawable.sp_filled_gray);
            }
        }


    }
}