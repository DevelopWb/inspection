package com.juntai.wisdom.inspection.home_page.baseinspect;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.inspection.AppNetModule;
import com.juntai.wisdom.inspection.base.BaseAppPresent;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.bean.ImportantTagBean;
import com.juntai.wisdom.inspection.bean.ItemFragmentBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.utils.CalendarUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 16:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 16:44
 */
public class BaseInspectPresent extends BaseAppPresent<IModel, BaseInspectContract.IInspectView> implements BaseInspectContract.IInspectPresent {
    @Override
    protected IModel createModel() {
        return null;
    }

    /**
     * 治安巡检点更多信息
     *
     * @return
     */
    public List<MultipleItem> getMoreInfoDetail() {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW,
                getData()));
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, "", false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, "地址"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "现场图片"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, ""));
        return arrays;
    }

    /**
     * 开始治安巡检数据
     *
     * @return
     */
    public List<MultipleItem> getSecurityInpsectData() {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW,
                getStartInspectBaseData()));
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_CHECK_TYPE, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_CHECK_PROBLEMS, "", false, 0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, "", false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传巡查图片"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, ""));
        return arrays;
    }

    /**
     * 开始走访
     *
     * @return
     */
    public List<MultipleItem> getVisitData() {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW,
                getStartInspectBaseData()));
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_CHECK_TYPE, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_CHECK_PROBLEMS, "", false, 0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, "", false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传走访图片"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, ""));
        return arrays;
    }

    /**
     * 添加 单位详情
     *
     * @return
     */
    public List<MultipleItem> getUnitInfoData(SearchedUnitsBean.DataBean.DatasBean bean) {
        List<MultipleItem> arrays = new ArrayList<>();
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_NAME, bean == null ? "" :
                        bean.getName(),
                true,
                0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_ADDR, bean == null ? "" :
                        bean.getAddress()
                , true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_UCC, bean == null ? "" :
                        bean.getUnifiedCreditCode(), true
                , 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON, bean == null ?
                "" :
                bean.getLegal(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON_TEL, bean == null
                ? "" : bean.getLegalPhone(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_UNIT_TYPE, bean == null ? "" :
                bean.getTypeName(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_RESPONSIBLE, bean == null ? "" :
                bean.getPersonLiable(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_RESPONSIBLE_TEL, bean == null ? "" :
                bean.getLiablePhone(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SPARE_PERSON, bean == null ? "" :
                bean.getSparePerson(), false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SPARE_PERSON_TEL, bean == null ?
                "" :
                bean.getSparePhone(), false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, bean == null ? "" :
                bean.getRemarks(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(null,null,null)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传单位图片"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3,6,3,true,true,
            new ArrayList<>())));
        return arrays;
    }

    /**
     * 治安巡检记录详情
     *
     * @return
     */
    public List<MultipleItem> getSecurityInpsectRecordDetailData() {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW,
                getStartInspectBaseData()));
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, "", false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传巡查图片"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, ""));
        return arrays;
    }

    /**
     * 编辑治安巡检点信息
     *
     * @return
     */
    public List<MultipleItem> getEditSecurityInspectSiteInfo() {
        List<MultipleItem> arrays = new ArrayList<>();
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SITE, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_ADDR, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_RESPONSIBLE, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_TEL, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SPARE_PERSON, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SPARE_PERSON_TEL, "", false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, "", false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, "地址"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "现场图片"));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, ""));
        return arrays;
    }

    public List<TextKeyValueBean> getData() {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("巡检点:", "暂无"));
        arrays.add(new TextKeyValueBean("巡检地址:", "暂无"));
        arrays.add(new TextKeyValueBean("安全责任人:", "暂无"));
        arrays.add(new TextKeyValueBean("联系电话:", "暂无"));
        arrays.add(new TextKeyValueBean("备用联系人:", "暂无"));
        arrays.add(new TextKeyValueBean("联系电话:", "暂无"));
        return arrays;
    }

    private List<TextKeyValueBean> getStartInspectBaseData() {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("检查时间:", CalendarUtil.getCurrentTime()));
        arrays.add(new TextKeyValueBean("检查人员:", "暂无"));
        arrays.add(new TextKeyValueBean("责任人:", "暂无"));
        arrays.add(new TextKeyValueBean("电话号码:", "暂无"));
        return arrays;
    }


    /**
     * initTextType
     *
     * @param arrays
     * @param typeName
     * @param editHeightType 0代表高度固定 1代表不固定
     */
    private void initTextType(List<MultipleItem> arrays, int layoutType, String typeName, String value,
                              boolean isImportant, int editHeightType) {
        switch (layoutType) {
            case MultipleItem.ITEM_SELECT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                        (typeName, isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                        new TextKeyValueBean(typeName, value, String.format("%s%s", "请选择",
                                typeName), 0, isImportant)));
                break;
            case MultipleItem.ITEM_EDIT:
                arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean(typeName,
                        isImportant)));
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant)));

                break;
            case MultipleItem.ITEM_EDIT2:
                arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT2,
                        new TextKeyValueBean(typeName, value,
                                String.format("%s%s", "请输入", typeName), editHeightType, isImportant)));
                break;
            default:
                break;
        }

    }

    @Override
    public void searchCompanys(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .searchCompanys(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SearchedUnitsBean>(getView()) {
                    @Override
                    public void onSuccess(SearchedUnitsBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    @Override
    public void searchUnitFromFireInspection(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .searchUnitFromFireInspection(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SearchedUnitsBean>(getView()) {
                    @Override
                    public void onSuccess(SearchedUnitsBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    @Override
    public void searchAddUnit(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .searchAddUnit(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
    @Override
    public void manualAddUnit(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .manualAddUnit(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    @Override
    public void checkUnitUnique(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .checkUnitUnique(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }

    @Override
    public void getUnitType(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getUnitType(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<IdNameBean>(getView()) {
                    @Override
                    public void onSuccess(IdNameBean o) {
                        if (getView() != null) {
                            getView().onSuccess(tag, o);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (getView() != null) {
                            getView().onError(tag, msg);
                        }
                    }
                });
    }
}
