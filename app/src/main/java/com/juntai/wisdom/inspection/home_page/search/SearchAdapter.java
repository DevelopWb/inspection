package com.juntai.wisdom.inspection.home_page.search;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.search.SearchBean;
import com.juntai.wisdom.inspection.bean.search.SearchMoreBean;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/6/4 15:17
 */
public class SearchAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SearchAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_TITLE, R.layout.item_layout_type_title_big);
        addItemType(MultipleItem.ITEM_CONTENT, R.layout.check_item);
        addItemType(MultipleItem.ITEM_LOAD_MORE, R.layout.item_content_load_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.ITEM_TITLE:
                SearchBean.DataBean dataBean = (SearchBean.DataBean) item.getObject();
                helper.setText(R.id.item_big_title_tv, dataBean.getName());

                break;
            case MultipleItem.ITEM_CONTENT:
                SearchBean.DataBean.ListBean listBean = (SearchBean.DataBean.ListBean) item.getObject();
                helper.setText(R.id.item_title_tv, listBean.getName());
                helper.setText(R.id.item_des_tv, listBean.getContent());
                ImageLoadUtil.loadImageNoCache(mContext,UrlFormatUtil.getImageThumUrl(listBean.getCoverPicture()),
                        helper.getView(R.id.cover_pic_iv));
                helper.addOnClickListener(R.id.item_navigation_tv);
                break;
            case MultipleItem.ITEM_LOAD_MORE:
                SearchMoreBean searchMoreBean = (SearchMoreBean) item.getObject();
                String msg = searchMoreBean.getMsg();
                if (!mContext.getString(R.string.load_more_no_data).equals(msg)) {
                    helper.setGone(R.id.item_load_more_tv, true);
                } else {
                    helper.setGone(R.id.item_load_more_tv, false);
                }
                helper.setText(R.id.item_load_more_tv, searchMoreBean.getMsg());
                break;
            default:
                break;
        }
    }
}
