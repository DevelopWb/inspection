package com.juntai.wisdom.inspection.home_page.firecheck.responsibility;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juntai.wisdom.inspection.base.ResponseListBean;
import com.juntai.wisdom.inspection.bean.firecheck.ResponsibilityBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  责任书详情
 * @date 2021/5/29 16:31
 */
public class ResponsibilityDetailActivity extends BaseResponsibilityActivity {

    private int letterId;
    private String titleName;

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
    public void onSuccess(String tag, Object o) {
        ResponsibilityBean responsibilityBean = (ResponsibilityBean) o;
        ResponsibilityBean.DataBean dataBean = responsibilityBean.getData();
        if (dataBean != null) {
            titleName = dataBean.getName();
            setTitleName(titleName);
            adapter.setDetail(true);
            adapter.setNewData(mPresenter.getResponsibilityData(dataBean,true));
            ResponseListBean.DataBean itemBean = new ResponseListBean.DataBean();
            itemBean.setName(dataBean.getName());
            itemBean.setContent(dataBean.getContent());
            itemBean.setLegal(dataBean.getLegal());
            itemBean.setLegalPhone(dataBean.getLegalPhone());
            itemBean.setSeal(dataBean.getSeal());
            itemBean.setNameSeal(dataBean.getNameSeal());
            itemBean.setUnitName(dataBean.getUnitName());
            itemBean.setSignPic(dataBean.getSignPhoto());
            itemBean.setGmtCreate(dataBean.getGmtCreate());
            initfootlayoutviews(itemBean);
        }
    }
}
