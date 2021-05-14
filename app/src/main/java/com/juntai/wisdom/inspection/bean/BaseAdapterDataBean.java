package com.juntai.wisdom.inspection.bean;

import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;

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
    private SearchedUnitsBean.DataBean.DatasBean  unitDataBean;
    private InspectionSiteBean.DataBean  inspectionSiteBean;
    private ImportantorBean.DataBean  importantorBean;

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

    public SearchedUnitsBean.DataBean.DatasBean getUnitDataBean() {
        return unitDataBean;
    }

    public void setUnitDataBean(SearchedUnitsBean.DataBean.DatasBean unitDataBean) {
        this.unitDataBean = unitDataBean;
    }
}
