package com.juntai.wisdom.inspection.home_page.importantor;

import android.content.Intent;

import com.baidu.mapapi.model.LatLng;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.ActionBean;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.home_page.securityInspect.SecurityInspectionSiteMoreInfoActivity;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

/**
 * @aouther tobato
 * @description 描述  重点人员详情
 * @date 2021/4/20 16:12
 */
public class ImportantorInfoActivity extends BaseInspectionInfoActivity {


    private ImportantorBean.DataBean dataBean;

    @Override
    protected Object getBaseBean() {
        return dataBean;
    }

    @Override
    protected List<ActionBean> getActionAdapterData() {
        List<ActionBean> arrays = new ArrayList<>();
        arrays.add(new ActionBean(R.mipmap.action_bg, BaseInspectContract.INSPECTION_VISIT_RECORD,
                R.mipmap.action_check_record));
        return arrays;
    }

    @Override
    protected void navigationLogic() {
        if (dataBean != null) {
            navigationLogic(new LatLng(Double.parseDouble(dataBean.getLatitude()),
                    Double.parseDouble(dataBean.getLongitude())),dataBean.getGpsAddress());
        }

    }
    @Override
    protected String getTitleName() {
        return "重点人员详情";
    }

    @Override
    protected String getStartWorkName() {
        return START_VISIT;
    }

    @Override
    protected String getQrCodePath() {
        return null;
    }


    @Override
    protected void seeMoreInfo() {
        startActivity(new Intent(mContext, ImportantorInfoMoreActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY
                ,dataBean));
    }

    @Override
    public void initData() {
        FormBody.Builder builder = getBaseBuilder().add("id", String.valueOf(baseId));
        if (contentId > 0) {
            builder.add("messageId", String.valueOf(contentId));
        }
        mPresenter.getImportantorDetail(builder.build(),
                AppHttpPath.IMPORTANTOR_DETAIL);

    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.IMPORTANTOR_DETAIL:
                ImportantorBean importantorBean = (ImportantorBean) o;
                if (importantorBean != null) {
                    dataBean = importantorBean.getData();
                    if (dataBean != null) {
                        baseInfoAdapter.setNewData(getData(dataBean));
                    }
                }

                break;
            default:
                break;
        }
    }

    private List<TextKeyValueBean> getData(ImportantorBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("姓名:",dataBean.getName()));
        arrays.add(new TextKeyValueBean("身份证号:", dataBean.getIdNumber()));
        arrays.add(new TextKeyValueBean("管控民警:", dataBean.getPoliceName()));
        return arrays;
    }
}
