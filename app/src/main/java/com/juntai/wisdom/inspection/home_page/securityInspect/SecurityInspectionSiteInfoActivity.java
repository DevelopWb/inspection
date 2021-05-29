package com.juntai.wisdom.inspection.home_page.securityInspect;

import android.content.Intent;

import com.baidu.mapapi.model.LatLng;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.ActionBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  治安巡检信息
 * @date 2021/4/20 16:12
 */
public class SecurityInspectionSiteInfoActivity extends BaseInspectionInfoActivity {

    private InspectionSiteBean.DataBean dataBean;


    @Override
    protected Object getBaseBean() {
        return dataBean;
    }

    @Override
    protected List<ActionBean> getActionAdapterData() {
        List<ActionBean> arrays = new ArrayList<>();
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.INSPECTION_SECURITY_RECORD,
                R.mipmap.action_check_record));
        return arrays;
    }

    @Override
    protected String getTitleName() {
        return "治安巡检点详情";
    }

    @Override
    protected String getStartWorkName() {
        return START_INSPECT;
    }

    @Override
    protected void navigationLogic() {
        if (dataBean != null) {
            navigationLogic(new LatLng(Double.parseDouble(dataBean.getLatitude()),
                    Double.parseDouble(dataBean.getLongitude())),dataBean.getAddress());
        }

    }

    @Override
    protected void seeMoreInfo() {
        startActivity(new Intent(mContext,SecurityInspectionSiteMoreInfoActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY
                ,dataBean));
    }

    @Override
    public void initData() {
        mPresenter.getInspectionSiteDetail(getBaseBuilder().add("securityId", String.valueOf(baseId)).build(),
                AppHttpPath.INSPECTION_SITE_DETAIL);

    }

    private List<TextKeyValueBean> getData(InspectionSiteBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("巡检点:",dataBean.getName()));
        arrays.add(new TextKeyValueBean("巡检地址:", dataBean.getAddress()));
        arrays.add(new TextKeyValueBean("安全责任人:", dataBean.getPersonLiable()));
        return arrays;
    }
    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.INSPECTION_SITE_DETAIL:
                dataBean = (InspectionSiteBean.DataBean) o;
                if (dataBean != null) {
                    baseInfoAdapter.setNewData(getData(dataBean));
                    ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(dataBean.getQrCode()),mQrCodeIv);
                }
                break;
            default:
                break;
        }
    }
}
