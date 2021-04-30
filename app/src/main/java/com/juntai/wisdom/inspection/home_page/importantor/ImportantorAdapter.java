package com.juntai.wisdom.inspection.home_page.importantor;

import android.view.Gravity;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
/**
 * @aouther tobato
 * @description 描述  重点人员适配器
 * @date 2021/4/29 16:24
 */

public class ImportantorAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public ImportantorAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
    }
}