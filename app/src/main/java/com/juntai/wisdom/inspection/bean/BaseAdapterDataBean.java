package com.juntai.wisdom.inspection.bean;

import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorVisitRecordDetailBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordDetailBean;
import com.juntai.wisdom.inspection.bean.unit.FireCheckBean;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.bean.unit.UnitDetailBean;

import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述  适配器中的数据
 * @CreateDate: 2021/5/11 11:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/11 11:12
 */
public class BaseAdapterDataBean {

    private  MultipartBody.Builder  builder;
    private UnitDetailBean.DataBean  unitDataBean;
    private InspectionSiteBean.DataBean  inspectionSiteBean;
    private ImportantorBean.DataBean  importantorBean;
    private SecurityInspectRecordDetailBean.DataBean recordDetailBean;
    private ImportantorVisitRecordDetailBean.DataBean visitRecordDetailBean;
    private FireCheckBean.DataBean fireCheckBean;

    public FireCheckBean.DataBean getFireCheckBean() {
        return fireCheckBean;
    }

    public void setFireCheckBean(FireCheckBean.DataBean fireCheckBean) {
        this.fireCheckBean = fireCheckBean;
    }

    public ImportantorVisitRecordDetailBean.DataBean getVisitRecordDetailBean() {
        return visitRecordDetailBean;
    }

    public void setVisitRecordDetailBean(ImportantorVisitRecordDetailBean.DataBean visitRecordDetailBean) {
        this.visitRecordDetailBean = visitRecordDetailBean;
    }

    public SecurityInspectRecordDetailBean.DataBean getRecordDetailBean() {
        return recordDetailBean;
    }

    public void setRecordDetailBean(SecurityInspectRecordDetailBean.DataBean recordDetailBean) {
        this.recordDetailBean = recordDetailBean;
    }

    public InspectionSiteBean.DataBean getInspectionSiteBean() {
        return inspectionSiteBean;
    }

    public void setInspectionSiteBean(InspectionSiteBean.DataBean inspectionSiteBean) {
        this.inspectionSiteBean = inspectionSiteBean;
    }

    public ImportantorBean.DataBean getImportantorBean() {
        return importantorBean;
    }

    public void setImportantorBean(ImportantorBean.DataBean importantorBean) {
        this.importantorBean = importantorBean;
    }

    public MultipartBody.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(MultipartBody.Builder builder) {
        this.builder = builder;
    }

    public UnitDetailBean.DataBean getUnitDataBean() {
        return unitDataBean;
    }

    public void setUnitDataBean(UnitDetailBean.DataBean unitDataBean) {
        this.unitDataBean = unitDataBean;
    }
}
