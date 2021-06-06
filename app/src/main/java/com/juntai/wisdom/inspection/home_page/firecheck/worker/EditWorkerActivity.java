package com.juntai.wisdom.inspection.home_page.firecheck.worker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.firecheck.WorkerDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  申请修改从业人员
 * @date 2021/5/31 16:10
 */
public class EditWorkerActivity extends BaseCommitFootViewActivity {

    private WorkerDetailBean.DataBean dataBean;

    @Override
    protected String getCommitTextValue() {
        return "提交审核";
    }

    @Override
    protected void commitRequest(MultipartBody.Builder builder) {
        mPresenter.editWorker(builder
                        .addFormDataPart("id",String.valueOf(dataBean.getId()))
                        .addFormDataPart("unitId", String.valueOf(dataBean.getUnitId())).build(),
                AppHttpPath.EDIT_WORKER);

    }

    @Override
    protected void saveDraft() {
        if (getBaseAdapterData(true) != null) {
            Hawk.put(HawkProperty.EDIT_WORKER_KEY+dataBean.getUnitName()+dataBean.getId(), getBaseAdapterData(true).getWorkerBean());
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
    }

    @Override
    protected String getTitleName() {
        return "编辑从业人员";
    }

    @Override
    public void initData() {
        dataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
        if (dataBean != null) {
            adapter.setNewData(mPresenter.getWorkerData(dataBean,false));
        }
        WorkerDetailBean.DataBean  savedWorkerBean =
                Hawk.get(HawkProperty.EDIT_WORKER_KEY+ dataBean.getUnitName()+ dataBean.getId());
        if (savedWorkerBean != null) {
            new AlertDialog.Builder(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.setNewData(mPresenter.getWorkerData(savedWorkerBean,false));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startLocation();
                }
            }).show();
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag,o);
        if (AppHttpPath.EDIT_WORKER.equals(tag)) {
            ToastUtils.toast(mContext,"已成功提交审核");
            finish();
        }
    }
}
