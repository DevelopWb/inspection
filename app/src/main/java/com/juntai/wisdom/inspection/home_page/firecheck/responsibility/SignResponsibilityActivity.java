package com.juntai.wisdom.inspection.home_page.firecheck.responsibility;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.firecheck.ResponsibilityBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  签署责任书
 * @date 2021/5/29 11:56
 */
public class SignResponsibilityActivity extends BaseResponsibilityActivity  {

    private int unitId;
    private String titleName;
    private String content;


    @Override
    boolean isDetail() {
        return false;
    }

    @Override
    public void initData() {
        super.initData();
        mCommitTv.setText("完成");
        titleName = dataBean.getName();
        setTitleName(titleName);
        content = dataBean.getContent();
        unitId = getIntent().getIntExtra(BASE_ID, 0);
        ResponsibilityBean.DataBean dataBean = new ResponsibilityBean.DataBean();
        if ("消防安全责任书".equals(titleName)) {
            dataBean.setName(getString(R.string.fire_safe_responsibility_title));
            dataBean.setContent(getString(R.string.fire_safe_responsibility_content));
        }else {
            dataBean.setName(getString(R.string.security_manager_responsibility_title));
            dataBean.setContent(getString(R.string.security_manager_responsibility_content));
        }
        adapter.setNewData(mPresenter.getResponsibilityData(dataBean, false));

    }

    @Override
    protected void downloadFile() {

    }

    @Override
    public void shareToWechat() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected String getTitleName() {
        return titleName;
    }



    @Override
    public void onSuccess(String tag, Object o) {
        ToastUtils.toast(mContext, "已提交");
        finish();
    }


    @Override
    protected void commitLogic(MultipartBody.Builder builder) {
        if (TextUtils.isEmpty(signPath)) {
            ToastUtils.toast(mContext,"请签字");
            return;
        }
        builder.addFormDataPart("pictureSign", "pictureSign",
                RequestBody.create(MediaType.parse(
                        "file"), new File(getSignPath(FileCacheUtils.SIGN_PIC_NAME))));
        mPresenter.signResponsibility(builder.addFormDataPart("unitId", String.valueOf(unitId))
                .addFormDataPart("typeId", isFireSafeResponsibility ? "1" : "2")
                .addFormDataPart("unitName", dataBean.getUnitName())
                .addFormDataPart("legalPhone", dataBean.getLegalPhone())
                .addFormDataPart("content", content).build(), "");
    }



}
