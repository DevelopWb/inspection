package com.juntai.wisdom.inspection.base.bottomDialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.IdNameBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  底部弹窗  多选
 * @CreateDate: 2021/5/14 9:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/14 9:09
 */
public class MultiSelectBottomSheetDialog extends BottomSheetDialog implements View.OnClickListener {
    private Context mContext;
    private RecyclerView mRecycleView;
    private CheckBoxAdapter checkBoxAdapter;
    private OnConfirmCallBack onConfirmCallBack;

    public MultiSelectBottomSheetDialog(@NonNull Context context, OnConfirmCallBack onConfirmCallBack) {
        super(context);
        this.mContext = context;
        this.onConfirmCallBack = onConfirmCallBack;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.multi_select_sheetdialog, null);
        mRecycleView = view.findViewById(R.id.recyclerview);
        TextView mCancelTv = view.findViewById(R.id.bottom_dialog_cancel_tv);
        mCancelTv.setOnClickListener(this);
        TextView mConfirmTv = view.findViewById(R.id.bottom_dialog_confirm_tv);
        mConfirmTv.setOnClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(manager);
        checkBoxAdapter = new CheckBoxAdapter(R.layout.item_checkboxes, false);
        mRecycleView.setAdapter(checkBoxAdapter);
        setContentView(view);
    }


    /**
     * 设置适配器数据
     *
     * @param arrays
     * @return
     */
    public void setAdapterData(List<IdNameBean.DataBean> arrays) {
        if (checkBoxAdapter != null) {
            checkBoxAdapter.setNewData(arrays);
            show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_dialog_cancel_tv:
                dismiss();
                break;
            case R.id.bottom_dialog_confirm_tv:
                String names = getSelectedItemNames(checkBoxAdapter.getData());
                if (TextUtils.isEmpty(names)) {
                    ToastUtils.toast(mContext, "请选择最少一种");
                    return;
                }
                if (onConfirmCallBack != null) {
                    onConfirmCallBack.confirm(names,
                            getSelectedItemIds(checkBoxAdapter.getData()));
                }
                dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 获取选中的项目
     *
     * @param data
     * @return
     */
    protected String getSelectedItemNames(List<IdNameBean.DataBean> data) {
        StringBuilder sb = new StringBuilder();
        for (IdNameBean.DataBean datum : data) {
            if (datum.isSelecte()) {
                sb.append(datum.getName() + "\n");
            }
        }
        if (sb.toString().length()>0) {
            return sb.toString().substring(0,sb.toString().lastIndexOf("\n"));
        }
       return "";
    }

    /**
     * 获取选中的项目
     *
     * @param data
     * @return
     */
    protected String getSelectedItemIds(List<IdNameBean.DataBean> data) {
       StringBuilder sb = new StringBuilder();
        for (IdNameBean.DataBean datum : data) {
            if (datum.isSelecte()) {
               sb.append(datum.getId()+",");
            }
        }
        return (sb.toString()).substring(0,(sb.toString()).length()-1);
    }




    public interface OnConfirmCallBack {

        void confirm(String names, String ids);

    }
}
