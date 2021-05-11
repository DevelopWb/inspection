package com.juntai.wisdom.inspection.bean;

import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.bean.unit.UnitInfoBean;

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
