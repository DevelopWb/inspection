package com.juntai.wisdom.inspection.home_page.firecheck;

import android.content.Intent;

import com.baidu.mapapi.model.LatLng;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.ActionBean;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述  治安巡检信息
 * @date 2021/4/20 16:12
 */
public class UnitInfoActivity extends BaseInspectionInfoActivity {


    private UnitDetailBean.DataBean dataBean;

    @Override
    protected Object getBaseBean() {
        return dataBean;
    }

    @Override
    protected void navigationLogic() {
        if (dataBean != null) {
            navigationLogic(new LatLng(Double.parseDouble(dataBean.getLatitude()),
                    Double.parseDouble(dataBean.getLongitude())), dataBean.getGpsAddress());
        }

    }

    @Override
    protected List<ActionBean> getActionAdapterData() {
        List<ActionBean> arrays = new ArrayList<>();
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.INSPECTION_CHECK_RECORD,
                R.mipmap.action_check_record));
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.INSPECTION_RESPONSIBILITY,
                R.mipmap.action_responsibility));
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.INSPECTION_RECTIFY_NOTICE,
                R.mipmap.action_rectifynotice));
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.INSPECTION_WORKER,
                R.mipmap.action_worker));
        return arrays;
    }

    @Override
    protected String getTitleName() {
        return "单位详情";
    }

    @Override
    protected String getStartWorkName() {
        return START_CHECK;
    }

    @Override
    protected void seeMoreInfo() {
        startActivity(new Intent(mContext, UnitInfoMoreActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY
                , dataBean));
    }

    @Override
    public void initData() {
        FormBody.Builder builder = getBaseBuilder().add("unitId", String.valueOf(baseId));
        if (contentId > 0) {
            builder.add("messageId", String.valueOf(contentId));
        }
        mPresenter.getUnitInfoDetail(builder.build(),
                AppHttpPath.GET_UNIT_INFO_DETAIL);

    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.GET_UNIT_INFO_DETAIL:
                UnitDetailBean unitDetailBean = (UnitDetailBean) o;
                if (unitDetailBean != null) {
                    dataBean = unitDetailBean.getData();
                    if (dataBean != null) {
                        baseInfoAdapter.setNewData(getData(dataBean));
                        ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(dataBean.getQrCode()),
                                mQrCodeIv);
                    }
                }

                break;
            default:
                break;
        }
    }

    private List<TextKeyValueBean> getData(UnitDetailBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("单位名称:", dataBean.getName()));
        arrays.add(new TextKeyValueBean("单位地址:", dataBean.getAddress()));
        arrays.add(new TextKeyValueBean("单位法人:", dataBean.getLegal()));
        return arrays;
    }
}
