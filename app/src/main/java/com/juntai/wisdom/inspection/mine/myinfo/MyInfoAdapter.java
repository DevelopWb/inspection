package com.juntai.wisdom.inspection.mine.myinfo;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.TextKeyValueAdapter;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  我的信息
 * @date 2021/6/1 16:48
 */
public class MyInfoAdapter extends BaseQuickAdapter<TextKeyValueBean, BaseViewHolder> {

    public MyInfoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TextKeyValueBean item) {
        helper.setText(R.id.item_myinfo_name, item.getKey());
        helper.setText(R.id.item_myinfo_value, item.getValue());

    }
}