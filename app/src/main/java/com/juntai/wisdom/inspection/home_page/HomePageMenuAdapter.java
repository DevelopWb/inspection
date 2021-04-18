package com.juntai.wisdom.inspection.home_page;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.HomePageMenuBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 15:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 15:39
 */
public class HomePageMenuAdapter extends BaseQuickAdapter<HomePageMenuBean, BaseViewHolder> {
    public HomePageMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomePageMenuBean item) {

        helper.setText(R.id.homepage_menu_title_tv,item.getMenuName());
        helper.setBackgroundRes(R.id.homepage_menu_ll,item.getMenuBgId());
    }
}
