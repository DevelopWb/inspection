package com.juntai.wisdom.inspection.home_page.importantor;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorVisitRecordDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.CalendarUtil;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  开始访问
 * @date 2021/5/5 17:17
 */
public class StartVisitActivity extends BaseInspectionActivity {

    private ImportantorBean.DataBean importantorDataBean;
    private ImportantorVisitRecordDetailBean.DataBean recordDetailBean;

    @Override
    public void initData() {
        unSavedLogic();
        ImportantorVisitRecordDetailBean.DataBean savedRecordBean =
                Hawk.get(HawkProperty.ADD_IMPORTANTOR_VISIT_RECORD_KEY + importantorDataBean.getId());
        if (savedRecordBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            recordDetailBean = savedRecordBean;
                            adapter.setNewData(mPresenter.getVisitData(savedRecordBean, false));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show(), -1, 0);
        }
    }

    private void unSavedLogic() {
        if (getIntent() != null) {
            importantorDataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
        }
        recordDetailBean = new ImportantorVisitRecordDetailBean.DataBean();
        //走访时间
        recordDetailBean.setCheckTime(CalendarUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        //重点人员
        recordDetailBean.setLiable(importantorDataBean.getName());
        //管控民警
        recordDetailBean.setNickname(importantorDataBean.getPoliceName());
        //重点人员手机号
        recordDetailBean.setLiablePhone(importantorDataBean.getPhone());
        adapter.setNewData(mPresenter.getVisitData(recordDetailBean, false));
    }

    @Override
    protected String getTitleName() {
        return "开始走访";
    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_save_commit, null);
        TextView mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        TextView mSaveDraftTv = view.findViewById(R.id.save_draft_tv);
        mCommitBusinessTv.setText("提交");
        mCommitBusinessTv.setOnClickListener(this);
        mSaveDraftTv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPath.START_VISIT:
                ToastUtils.toast(mContext, "提交成功");
                Hawk.delete(HawkProperty.ADD_IMPORTANTOR_VISIT_RECORD_KEY + importantorDataBean.getId());
                finish();
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.save_draft_tv:
                //保存草稿
                if (getBaseAdapterData(true) != null) {
                    ImportantorVisitRecordDetailBean.DataBean dataBean =
                            getBaseAdapterData(true).getVisitRecordDetailBean();
                    dataBean.setCheckTime(recordDetailBean.getCheckTime());
                    dataBean.setLiable(recordDetailBean.getLiable());
                    dataBean.setNickname(recordDetailBean.getNickname());
                    dataBean.setLiablePhone(recordDetailBean.getLiablePhone());
                    Hawk.put(HawkProperty.ADD_IMPORTANTOR_VISIT_RECORD_KEY + importantorDataBean.getId(), dataBean);
                    ToastUtils.toast(mContext, "草稿保存成功");
                    finish();
                }
                break;
            case R.id.commit_form_tv:
                BaseAdapterDataBean baseAdapterDataBean = getBaseAdapterData(false);
                if (baseAdapterDataBean == null) {
                    return;
                }
                MultipartBody.Builder builder = getBaseAdapterData(false).getBuilder();
                builder.addFormDataPart("keyId", String.valueOf(importantorDataBean.getId()))
                        .addFormDataPart("checkTime", recordDetailBean.getCheckTime())
                        .addFormDataPart("liable", recordDetailBean.getLiable())
//                        .addFormDataPart("inspectionId", String.valueOf(questionId))
                        .addFormDataPart("liablePhone", recordDetailBean.getLiablePhone());

                mPresenter.startVist(builder.build(), AppHttpPath.START_VISIT);

                break;
            default:
                break;
        }
    }
}
