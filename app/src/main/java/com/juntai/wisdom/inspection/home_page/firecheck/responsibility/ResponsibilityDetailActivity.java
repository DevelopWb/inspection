package com.juntai.wisdom.inspection.home_page.firecheck.responsibility;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.ResponseListBean;
import com.juntai.wisdom.inspection.bean.firecheck.ResponsibilityBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.ToolShare;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

import java.util.Arrays;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * @aouther tobato
 * @description 描述  责任书详情
 * @date 2021/5/29 16:31
 */
public class ResponsibilityDetailActivity extends BaseResponsibilityActivity {

    private int letterId;
    private String titleName;
    private ResponsibilityBean.DataBean detailBean;

    @Override
    protected String getTitleName() {
        return titleName;
    }



    @Override
    boolean isDetail() {
        return true;
    }

    @Override
    public void initData() {
        letterId = getIntent().getIntExtra(BASE_ID, 0);
        mPresenter.getResponsibilityDetail(mPresenter.getPublishMultipartBody().addFormDataPart("letterId",
                String.valueOf(letterId)).build(), "");
    }

    @Override
    protected void downloadFile() {
        if (detailBean != null) {
            if (!TextUtils.isEmpty(detailBean.getWordFile())) {
                initBottomDialog(Arrays.asList("保存文档"),UrlFormatUtil.getImageOriginalUrl(detailBean.getWordFile()));
            }else {
                ToastUtils.toast(mContext,"无法获取文档信息");
            }
        }

    }

    @Override
    public void shareToWechat() {
        ToolShare.shareForMob(mContext, titleName, detailBean.getShareUrl(), detailBean.getUnitName(),
                UrlFormatUtil.getImageOriginalUrl(detailBean.getSeal()),
                new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
    }

    @Override
    public void onSuccess(String tag, Object o) {
        ResponsibilityBean responsibilityBean = (ResponsibilityBean) o;
        detailBean = responsibilityBean.getData();
        if (detailBean != null) {
            titleName = detailBean.getName();
            setTitleName(titleName);
            adapter.setDetail(true);
            if ("消防安全责任书".equals(titleName)) {
                detailBean.setName(getString(R.string.fire_safe_responsibility_title));
                detailBean.setContent(getString(R.string.fire_safe_responsibility_content));
            }else {
                detailBean.setName(getString(R.string.security_manager_responsibility_title));
                detailBean.setContent(getString(R.string.security_manager_responsibility_content));
            }
            adapter.setNewData(mPresenter.getResponsibilityData(detailBean,true));
            ResponseListBean.DataBean itemBean = new ResponseListBean.DataBean();
            itemBean.setName(detailBean.getName());
            itemBean.setContent(detailBean.getContent());
            itemBean.setLegal(detailBean.getLegal());
            itemBean.setLegalPhone(detailBean.getLegalPhone());
            itemBean.setSeal(detailBean.getSeal());
            itemBean.setNameSeal(detailBean.getNameSeal());
            itemBean.setUnitName(detailBean.getUnitName());
            itemBean.setSignPic(detailBean.getSignPhoto());
            itemBean.setGmtCreate(detailBean.getGmtCreate());
            initfootlayoutviews(itemBean);
        }
    }


}
