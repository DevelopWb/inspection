package com.juntai.wisdom.inspection.home_page.securityInspect;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.CalendarUtil;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.juntai.wisdom.inspection.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  开始治安巡检
 * @date 2021/4/23 15:18
 */
public class StartSecurityInspectActivity extends BaseInspectionActivity {

    private InspectionSiteBean.DataBean dataBean;
    private SecurityInspectRecordDetailBean.DataBean recordDetailBean;

    @Override
    public void initData() {
        recordDetailBean = new SecurityInspectRecordDetailBean.DataBean();
        SecurityInspectRecordDetailBean.DataBean savedRecordBean = Hawk.get(HawkProperty.ADD_INSPECRTION_RECORD_KEY);
        if (savedRecordBean != null) {
            unSavedLogic();
            new AlertDialog.Builder(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!TextUtils.isEmpty(savedRecordBean.getTypeName())) {
                                questionName = savedRecordBean.getTypeName();
                                questionId = savedRecordBean.getTypeId();
                            }
                            recordDetailBean.setInspectTime(savedRecordBean.getInspectTime());
                            recordDetailBean.setInspectName(savedRecordBean.getInspectName());
                            recordDetailBean.setUnitLiable(savedRecordBean.getUnitLiable());
                            recordDetailBean.setLiablePhone(savedRecordBean.getLiablePhone());
                            adapter.setNewData(mPresenter.getSecurityInpsectData(savedRecordBean,false));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    unSavedLogic();
                }
            }).show();

        } else {
            unSavedLogic();
        }

    }

    private void unSavedLogic() {
        if (getIntent() != null) {
            dataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
        }

        recordDetailBean.setInspectTime(CalendarUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
        recordDetailBean.setInspectName(UserInfoManager.getUserNickName());
        recordDetailBean.setUnitLiable(dataBean.getPersonLiable());
        recordDetailBean.setLiablePhone(dataBean.getLiablePhone());
        adapter.setNewData(mPresenter.getSecurityInpsectData(recordDetailBean,false));
    }

    @Override
    protected String getTitleName() {
        return "开始巡检";
    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_save_commit, null);
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
            case AppHttpPath.ADD_INSPECTION_RECORD:
                ToastUtils.toast(mContext, "提交成功");
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
                    SecurityInspectRecordDetailBean.DataBean dataBean =
                            getBaseAdapterData(true).getRecordDetailBean();
                    dataBean.setInspectTime(recordDetailBean.getInspectTime());
                    dataBean.setInspectName(recordDetailBean.getInspectName());
                    dataBean.setUnitLiable(recordDetailBean.getUnitLiable());
                    dataBean.setLiablePhone(recordDetailBean.getLiablePhone());
                    Hawk.put(HawkProperty.ADD_INSPECRTION_RECORD_KEY, dataBean);
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
                builder.addFormDataPart("securityId", String.valueOf(dataBean.getId()))
                        .addFormDataPart("inspectTime", recordDetailBean.getInspectTime())
                        .addFormDataPart("inspectName", recordDetailBean.getInspectName())
                        .addFormDataPart("unitLiable", recordDetailBean.getUnitLiable())
                        .addFormDataPart("liablePhone", recordDetailBean.getLiablePhone());

                mPresenter.addInspectionRecord(builder.build(), AppHttpPath.ADD_INSPECTION_RECORD);

                break;
            default:
                break;
        }
    }
}
