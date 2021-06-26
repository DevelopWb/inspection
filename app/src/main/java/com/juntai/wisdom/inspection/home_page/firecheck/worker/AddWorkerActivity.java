package com.juntai.wisdom.inspection.home_page.firecheck.worker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.firecheck.WorkerDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseRecordActivity;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  添加从业人员
 * @date 2021/5/31 11:26
 */
public class AddWorkerActivity extends BaseCommitFootViewActivity {

    private int unitId;

    @Override
    public void initData() {
        unitId = getIntent().getIntExtra(BaseRecordActivity.ID, 0);
        adapter.setNewData(mPresenter.getWorkerData(null,false));
        WorkerDetailBean.DataBean  savedWorkerBean = Hawk.get(HawkProperty.ADD_WORKER_KEY+unitId);
        if (savedWorkerBean != null) {
            setAlertDialogHeightWidth( DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
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
                    }).show(),-1,0);
        }

    }

    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected void commitRequest(MultipartBody.Builder builder) {
        mPresenter.addWorker(builder.addFormDataPart("unitId", String.valueOf(unitId)).build(), AppHttpPath.ADD_WORKER);
    }

    @Override
    protected void saveDraft() {
        if (getBaseAdapterData(true) != null) {
            Hawk.put(HawkProperty.ADD_WORKER_KEY+unitId, getBaseAdapterData(true).getWorkerBean());
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
    }

    @Override
    protected String getTitleName() {
        return "添加从业人员";
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        if (AppHttpPath.ADD_WORKER.equals(tag)) {
            ToastUtils.toast(mContext,"添加成功");
            finish();
        }
    }
}
