package com.juntai.wisdom.inspection.mine;

import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.MyMenuBean;

import java.util.List;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyMenuAdapter extends BaseQuickAdapter<MyMenuBean, BaseViewHolder> {

    public MyMenuAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMenuBean item) {
        helper.setText(R.id.item_name, item.getName());
        helper.setImageResource(R.id.item_iv, item.getImageId());
        if (item.getUnreadNum() > 0) {
            helper.setVisible(R.id.item_number, true);
            helper.setText(R.id.item_number, item.getUnreadNum() > 99 ? "99+" : String.valueOf(item.getUnreadNum()));
        } else {
            helper.setVisible(R.id.item_number, false);
        }


    }

}
