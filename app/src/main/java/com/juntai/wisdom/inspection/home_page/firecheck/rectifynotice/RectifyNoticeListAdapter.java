package com.juntai.wisdom.inspection.home_page.firecheck.rectifynotice;

import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckRecordListBean;
import com.juntai.wisdom.inspection.bean.firecheck.RectifyNoticeListBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/15 15:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 15:51
 */
public class RectifyNoticeListAdapter extends BaseQuickAdapter<RectifyNoticeListBean.DataBean.DatasBean,
        BaseViewHolder> {
    public RectifyNoticeListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RectifyNoticeListBean.DataBean.DatasBean item) {
        ImageView iconIv = helper.getView(R.id.unread_tag_iv);
        iconIv.setBackgroundResource(0);
        ImageLoadUtil.loadImage(mContext, R.mipmap.location_icon, iconIv);
        ConstraintLayout.LayoutParams linearParams = (ConstraintLayout.LayoutParams) iconIv.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        linearParams.width = DisplayUtil.dp2px(mContext, 25);// 控件的宽强制设成30
        linearParams.height = DisplayUtil.dp2px(mContext, 25);// 控件的高强制设成30
        iconIv.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        helper.setText(R.id.record_time_tv, item.getGmtCreate());
        helper.setGone(R.id.record_person_tv, false);
        helper.setText(R.id.record_type_tv, "整改通知书");
    }
}
