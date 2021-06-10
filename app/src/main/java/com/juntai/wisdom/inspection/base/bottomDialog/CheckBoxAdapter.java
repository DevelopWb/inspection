package com.juntai.wisdom.inspection.base.bottomDialog;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.IdNameBean;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/1/28 11:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/1/28 11:14
 */
public class CheckBoxAdapter extends BaseQuickAdapter<IdNameBean.DataBean, BaseViewHolder> {

    private boolean isSingleSelect = false;// 默认不是单选
    private boolean isDetail = false;//是否是详情模式

    public CheckBoxAdapter(int layoutResId, @Nullable List<IdNameBean.DataBean> data, boolean isSingleSelect,
                           boolean isDetail) {
        super(layoutResId, data);
        this.isSingleSelect = isSingleSelect;
        this.isDetail = isDetail;
    }

    public CheckBoxAdapter(int layoutResId, boolean isSingleSelect
    ) {
        super(layoutResId);
        this.isSingleSelect = isSingleSelect;
    }


    @Override
    protected void convert(BaseViewHolder helper, IdNameBean.DataBean item) {


        CheckBox checkBox = helper.getView(R.id.business_checkboxes_cb);
        //        if (isDetail) {
        //            checkBox.setEnabled(false);
        //        }else {
        //            checkBox.setEnabled(true);
        //        }
        checkBox.setText(item.getName());
        if (item.isSelecte()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSingleSelect) {
                    List<IdNameBean.DataBean> datas = getData();
                    for (IdNameBean.DataBean data : datas) {
                        if (data.isSelecte()) {
                            data.setSelecte(false);
                        }
                    }
                }
                if (item.isSelecte()) {
                    item.setSelecte(false);
                } else {
                    item.setSelecte(true);
                }
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        // 刷新操作
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
