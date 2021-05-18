package com.juntai.wisdom.inspection.home_page.firecheck;

import android.content.Intent;

import com.baidu.mapapi.model.LatLng;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.ActionBean;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.unit.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.home_page.importantor.ImportantorInfoMoreActivity;

import java.util.ArrayList;
import java.util.List;

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
                            Double.parseDouble(dataBean.getLongitude())),dataBean.getGpsAddress());
                }

    }

    @Override
    protected List<ActionBean> getActionAdapterData() {
        List<ActionBean> arrays = new ArrayList<>();
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter, BaseInspectContract.INSPECTION_CHECK_RECORD,
                R.mipmap.app_icon));
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter, BaseInspectContract.INSPECTION_RESPONSIBILITY,
                R.mipmap.app_icon));
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter, BaseInspectContract.INSPECTION_RECTIFY_NOTICE,
                R.mipmap.app_icon));
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter, BaseInspectContract.INSPECTION_WORKER,
                R.mipmap.app_icon));
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
                ,dataBean));
    }

    @Override
    public void initData() {
        mPresenter.getUnitInfoDetail(getBaseBuilder().add("unitId", String.valueOf(baseId)).build(),
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
                    }
                }

                break;
            default:
                break;
        }
    }

    private List<TextKeyValueBean> getData(UnitDetailBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("单位名称:",dataBean.getName()));
        arrays.add(new TextKeyValueBean("单位地址:", dataBean.getAddress()));
        arrays.add(new TextKeyValueBean("单位法人:", dataBean.getLegal()));
        return arrays;
    }
}
