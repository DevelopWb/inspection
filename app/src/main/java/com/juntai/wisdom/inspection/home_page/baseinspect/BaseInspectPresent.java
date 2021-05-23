package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.text.TextUtils;

import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.AppNetModule;
import com.juntai.wisdom.inspection.base.BaseAppPresent;
import com.juntai.wisdom.inspection.bean.HeadPicBean;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.bean.ImportantTagBean;
import com.juntai.wisdom.inspection.bean.ItemFragmentBean;
import com.juntai.wisdom.inspection.bean.ItemSignBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnQuailityFormBean;
import com.juntai.wisdom.inspection.bean.importantor.AllImportantorBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorVisitRecordDetailBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorVisitRecordListBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.AllInspectionSiteBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordDetailBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordListBean;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckBean;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckRecordListBean;
import com.juntai.wisdom.inspection.bean.firecheck.SearchedUnitsBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnitDetailBean;
import com.juntai.wisdom.inspection.utils.AppUtils;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

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
     * 消防检查记录详情  没问题
     *
     * @param dataBean
     * @return
     */
    public List<MultipleItem> getFireCheckedOkDetailData(FireCheckBean.DataBean dataBean) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW,
                getStartFireCheckData(dataBean)));
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, dataBean == null ? "" :
                dataBean.getConcreteProblems(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传检查图片"));
        List<String> fragmentPics = new ArrayList<>();
        if (dataBean != null) {
            addFragmentPics(dataBean.getPhotoOne(), fragmentPics);
            addFragmentPics(dataBean.getPhotoTwo(), fragmentPics);
            addFragmentPics(dataBean.getPhotoThree(), fragmentPics);
            addFragmentPics(dataBean.getPhotoFour(), fragmentPics);
            addFragmentPics(dataBean.getPhotoFive(), fragmentPics);
            addFragmentPics(dataBean.getPhotoSix(), fragmentPics);
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3,
                fragmentPics.size() ,
                3, false,
                fragmentPics)));
        return arrays;
    }
    /**
     * 消防检查记录详情  有问题
     *
     * @param dataBean
     * @return
     */
    public List<MultipleItem> getFireCheckedHasQuestionDetailData(FireCheckBean.DataBean dataBean) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW,
                getStartFireCheckData(dataBean)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_FIRE_CHECK_FORM,
                new UnQuailityFormBean(dataBean.getItemsJson(),dataBean.getOtherProblem(),
                        dataBean.getConcreteProblems(),dataBean.getItemOne(),dataBean.getItemOneTime(),dataBean.getItemTwo(),
                        dataBean.getItemTwoTime(),dataBean.getNoticeName(),dataBean.getNoticeContent(),dataBean.isHideSummarize())));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "检查图片"));
        List<String> fragmentPics = new ArrayList<>();
        if (dataBean != null) {
            addFragmentPics(dataBean.getPhotoOne(), fragmentPics);
            addFragmentPics(dataBean.getPhotoTwo(), fragmentPics);
            addFragmentPics(dataBean.getPhotoThree(), fragmentPics);
            addFragmentPics(dataBean.getPhotoFour(), fragmentPics);
            addFragmentPics(dataBean.getPhotoFive(), fragmentPics);
            addFragmentPics(dataBean.getPhotoSix(), fragmentPics);
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3,
                fragmentPics.size() ,
                3, false,
                fragmentPics)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean("责任人签字：", dataBean == null ? "" :
                dataBean.getSignPhoto(), 0,true)));
        return arrays;
    }

    /**
     * 开始消防检查
     *
     * @param dataBean
     * @param isCheckedOk 是否检查正常
     * @return
     */
    public List<MultipleItem> getFireCheckData(FireCheckBean.DataBean dataBean,
                                              boolean isCheckedOk) {
        List<MultipleItem> arrays = new ArrayList<>();
        if (isCheckedOk) {
            initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, dataBean == null ? "" :
                    dataBean.getConcreteProblems(), true, 1);
        } else {
            arrays.add(new MultipleItem(MultipleItem.ITEM_FIRE_CHECK_FORM,
                    new UnQuailityFormBean(dataBean.getItemsJson(),dataBean.getOtherProblem(),
                            dataBean.getConcreteProblems(),dataBean.getItemOne(),dataBean.getItemOneTime(),dataBean.getItemTwo(),
                            dataBean.getItemTwoTime(),dataBean.getNoticeName(),dataBean.getNoticeContent(),dataBean.isHideSummarize())));
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传检查图片"));
        List<String> fragmentPics = new ArrayList<>();
        if (dataBean != null) {
            addFragmentPics(dataBean.getPhotoOne(), fragmentPics);
            addFragmentPics(dataBean.getPhotoTwo(), fragmentPics);
            addFragmentPics(dataBean.getPhotoThree(), fragmentPics);
            addFragmentPics(dataBean.getPhotoFour(), fragmentPics);
            addFragmentPics(dataBean.getPhotoFive(), fragmentPics);
            addFragmentPics(dataBean.getPhotoSix(), fragmentPics);
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3, 6,
                3, false,
                fragmentPics)));
        return arrays;
    }
    /**
     * 整改通知书
     *
     * @param dataBean
     * @return
     */
    public List<MultipleItem> getFireCheckNoticeData(FireCheckBean.DataBean dataBean) {
        List<MultipleItem> arrays = new ArrayList<>();
            arrays.add(new MultipleItem(MultipleItem.ITEM_FIRE_CHECK_FORM,
                    new UnQuailityFormBean(dataBean.getItemsJson(),dataBean.getOtherProblem(),
                            dataBean.getConcreteProblems(),dataBean.getItemOne(),dataBean.getItemOneTime(),dataBean.getItemTwo(),
                            dataBean.getItemTwoTime(),dataBean.getNoticeName(),dataBean.getNoticeContent(),
                            dataBean.isHideSummarize())));

        arrays.add(new MultipleItem(MultipleItem.ITEM_SIGN, new ItemSignBean("责任人签字：", dataBean == null ? "" :
                "", 0,true)));

        return arrays;
    }

    /**
     * 开始治安巡检数据
     *
     * @return
     */
    public List<MultipleItem> getSecurityInpsectData(SecurityInspectRecordDetailBean.DataBean recordDetailBean,
                                                     boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW,
                getStartInspectBaseData(recordDetailBean)));
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_CHECK_PROBLEMS,
                recordDetailBean == null ? "" : recordDetailBean.getTypeName(), false, 0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, recordDetailBean == null ? "" :
                recordDetailBean.getRemarks(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传巡检图片"));
        List<String> fragmentPics = new ArrayList<>();
        if (recordDetailBean != null) {
            addFragmentPics(recordDetailBean.getPhotoOne(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoTwo(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoThree(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoFour(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoFive(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoSix(), fragmentPics);
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3, isDetail ?
                fragmentPics.size() : 6,
                3, false,
                fragmentPics)));
        return arrays;
    }

    /**
     * 开始走访
     *
     * @return
     */
    public List<MultipleItem> getVisitData(ImportantorVisitRecordDetailBean.DataBean recordDetailBean,
                                           boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW,
                getStartVisitBaseData(recordDetailBean)));
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_VISIT_PROBLEMS,
                recordDetailBean == null ? "" : recordDetailBean.getInspectionName(), false, 0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, recordDetailBean == null ? "" :
                recordDetailBean.getRemarks(), false, 1);
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传走访图片"));
        List<String> fragmentPics = new ArrayList<>();
        if (recordDetailBean != null) {
            addFragmentPics(recordDetailBean.getPhotoOne(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoTwo(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoThree(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoFour(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoFive(), fragmentPics);
            addFragmentPics(recordDetailBean.getPhotoSix(), fragmentPics);
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3, isDetail ?
                fragmentPics.size() : 6,
                3, false,
                fragmentPics)));
        return arrays;
    }

    /**
     * 添加 单位详情
     *
     * @return
     */
    public List<MultipleItem> getUnitInfoData(UnitDetailBean.DataBean bean, boolean isDetail) {
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
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(bean == null ? null :
                bean.getGpsAddress()
                , bean == null ? null : bean.getLatitude(), bean == null ? null : bean.getLongitude())));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传单位图片"));
        List<String> fragmentPics = new ArrayList<>();
        if (bean != null) {
            addFragmentPics(bean.getCoverPicture(), fragmentPics);
            addFragmentPics(bean.getPhotoTwo(), fragmentPics);
            addFragmentPics(bean.getPhotoThree(), fragmentPics);
            addFragmentPics(bean.getPhotoFour(), fragmentPics);
            addFragmentPics(bean.getPhotoFive(), fragmentPics);
            addFragmentPics(bean.getPhotoSix(), fragmentPics);
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3, isDetail ?
                fragmentPics.size() : 6,
                3, true,
                fragmentPics)));
        return arrays;
    }

    /**
     * 添加 巡检点详情
     *
     * @return
     */
    public List<MultipleItem> getInspectionSiteInfoData(InspectionSiteBean.DataBean bean, boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SITE, bean == null ? "" :
                        bean.getName(),
                true,
                0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_ADDR, bean == null ? "" :
                        bean.getAddress()
                , true, 0);
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
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(bean == null ? null :
                bean.getGpsAddress()
                , bean == null ? null : bean.getLatitude(), bean == null ? null : bean.getLongitude())));
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "上传现场图片"));
        List<String> fragmentPics = new ArrayList<>();
        if (bean != null) {
            addFragmentPics(bean.getCoverPicture(), fragmentPics);
            addFragmentPics(bean.getPhotoTwo(), fragmentPics);
            addFragmentPics(bean.getPhotoThree(), fragmentPics);
            addFragmentPics(bean.getPhotoFour(), fragmentPics);
            addFragmentPics(bean.getPhotoFive(), fragmentPics);
            addFragmentPics(bean.getPhotoSix(), fragmentPics);
        }
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(3, isDetail ?
                fragmentPics.size() : 6,
                3, false,
                fragmentPics)));
        return arrays;
    }

    /**
     * 添加 重点人员
     *
     * @return
     */
    public List<MultipleItem> getImportantorData(ImportantorBean.DataBean bean) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL,
                new ImportantTagBean(BaseInspectContract.INSPECTION_IMPORTANTOR_PHOTO, true)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_HEAD_PIC,
                new HeadPicBean(BaseInspectContract.INSPECTION_IMPORTANTOR_PHOTO, -1,
                        bean == null ? "" : bean.getPersonnelPhoto())));
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_NAME, bean == null ? "" :
                        bean.getName()
                , true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_NICK_NAME, bean == null ? "" :
                        bean.getNickname()
                , true, 0);
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_SEX, bean == null ? "" :
                1 == bean.getGender() ? "男" : "女", true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_ID_CARD, bean == null ? "" :
                        bean.getIdNumber()
                , true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_ADDR_LATEST, bean == null ? "" :
                        bean.getAddress()
                , true, 0);
        initTextSelectType(arrays, BaseInspectContract.INSPECTION_PERSONAL_TYPE, bean == null ? "" : bean.getTypeId()
                , bean == null ? "" :
                        bean.getTypeName(), true);
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_PERSONAL_STATUS, bean == null ?
                        "" :
                        bean.getKeyStatusName()
                , true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_WORK_UNIT_LATEST, bean == null ?
                        "" :
                        bean.getUnitName()
                , true, 0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_TEL, bean == null ? "" :
                bean.getPhone(), true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_OTHER_CONNECT_TYPE, bean == null
                ? "" :
                bean.getOtherPhone(), false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SPARE_PERSON, bean == null ? "" :
                bean.getSparePerson(), false, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_SPARE_PERSON_TEL, bean == null ?
                "" :
                bean.getSparePhone(), false, 0);

        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_RESULT_DESCRIPTION, bean == null
                        ? "" :
                        bean.getTreatment()
                , true, 1);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.REMARK, bean == null ? "" :
                bean.getRemarks(), false, 1);
        initTextType(arrays, MultipleItem.ITEM_SELECT, BaseInspectContract.INSPECTION_VISIT_TIMES, bean == null ? "" :
                        String.valueOf(bean.getCheckTime())
                , true, 0);
        initTextType(arrays, MultipleItem.ITEM_EDIT, BaseInspectContract.INSPECTION_POLICE_NAME, bean == null ? "" :
                        bean.getPoliceName()
                , true, 0);
        arrays.add(new MultipleItem(MultipleItem.ITEM_LOCATION, new LocationBean(bean == null ? null :
                bean.getGpsAddress()
                , bean == null ? null : bean.getLatitude(), bean == null ? null : bean.getLongitude())));

        return arrays;
    }

    private void addFragmentPics(String picPath, List<String> fragmentPics) {
        if (!TextUtils.isEmpty(picPath)) {
            if (picPath.contains(AppUtils.getAppName())) {
                fragmentPics.add(picPath);
            } else {
                if (picPath.contains(AppHttpPath.BASE_IMAGE_THUM)) {
                    fragmentPics.add(picPath);
                } else {
                    fragmentPics.add(UrlFormatUtil.getImageThumUrl(picPath));
                }

            }

        }
    }


    private List<TextKeyValueBean> getStartInspectBaseData(SecurityInspectRecordDetailBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("检查时间:", dataBean.getInspectTime()));
        arrays.add(new TextKeyValueBean("检查人员:", dataBean.getInspectName()));
        arrays.add(new TextKeyValueBean("责任人:", dataBean.getUnitLiable()));
        arrays.add(new TextKeyValueBean("电话号码:", dataBean.getLiablePhone()));
        return arrays;
    }

    /**
     * 消防检查
     *
     * @param dataBean
     * @return
     */

    public List<TextKeyValueBean> getStartFireCheckData(FireCheckBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("检查时间:", dataBean.getInspectTime()));
        arrays.add(new TextKeyValueBean("检查人员:", dataBean.getInspectName()));
        arrays.add(new TextKeyValueBean("责任人:", dataBean.getUnitLiable()));
        arrays.add(new TextKeyValueBean("电话号码:", dataBean.getLiablePhone()));
        return arrays;
    }

    private List<TextKeyValueBean> getStartVisitBaseData(ImportantorVisitRecordDetailBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("走访时间:", dataBean.getCheckTime()));
        arrays.add(new TextKeyValueBean("管控民警:", dataBean.getLiable()));
        arrays.add(new TextKeyValueBean("重点人员:", dataBean.getNickname()));
        arrays.add(new TextKeyValueBean("电话号码:", dataBean.getLiablePhone()));
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

    /**
     * initTextType
     *
     * @param arrays
     * @param typeName
     */
    private void initTextSelectType(List<MultipleItem> arrays, String typeName, String id, String value,
                                    boolean isImportant) {
        arrays.add(new MultipleItem(MultipleItem.ITEM_TITILE_SMALL, new ImportantTagBean
                (typeName, isImportant)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(typeName, value, id, String.format("%s%s", "请选择",
                        typeName), 0, isImportant)));
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
    public void searchInspectionSitesToAdd(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .searchInspectionSitesToAdd(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<AllInspectionSiteBean>(getView()) {
                    @Override
                    public void onSuccess(AllInspectionSiteBean o) {
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
    public void getInspectionSiteDetail(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getInspectionSiteDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<InspectionSiteBean>(getView()) {
                    @Override
                    public void onSuccess(InspectionSiteBean o) {
                        if (getView() != null) {
                            if (o != null) {
                                if (o.getData() != null) {
                                    getView().onSuccess(tag, o.getData());
                                }
                            }

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
    public void searchInspectionSitesAdded(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .searchInspectionSitesAdded(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<AllInspectionSiteBean>(getView()) {
                    @Override
                    public void onSuccess(AllInspectionSiteBean o) {
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
    public void searchImportantorToAdd(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .searchImportantorToAdd(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<AllImportantorBean>(getView()) {
                    @Override
                    public void onSuccess(AllImportantorBean o) {
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
    public void getImportantorDetail(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getImportantorDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ImportantorBean>(getView()) {
                    @Override
                    public void onSuccess(ImportantorBean o) {
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
    public void searchAddImportantor(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .searchAddImportantor(requestBody)
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
    public void manualAddImportantor(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .manualAddImportantor(requestBody)
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
    public void startVist(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .startVist(requestBody)
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
    public void getImportantorTypes(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getImportantorTypes(requestBody)
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

    @Override
    public void getImportantorStatus(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getImportantorStatus(requestBody)
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

    @Override
    public void getAllAddedImportantor(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getAllAddedImportantor(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<AllImportantorBean>(getView()) {
                    @Override
                    public void onSuccess(AllImportantorBean o) {
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
    public void getSecurityInspectRecords(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getSecurityInspectRecords(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SecurityInspectRecordListBean>(getView()) {
                    @Override
                    public void onSuccess(SecurityInspectRecordListBean o) {
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
    public void getSecurityInspectRecordDetail(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getSecurityInspectRecordDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<SecurityInspectRecordDetailBean>(getView()) {
                    @Override
                    public void onSuccess(SecurityInspectRecordDetailBean o) {
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
    public void getUnitInfoDetail(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getUnitInfoDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UnitDetailBean>(getView()) {
                    @Override
                    public void onSuccess(UnitDetailBean o) {
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
    public void applyEditUnitInfo(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .applyEditUnitInfo(requestBody)
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
    public void addFireCheckRecord(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .addFireCheck(requestBody)
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
    public void getFireCheckRecords(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getFireCheckRecords(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<FireCheckRecordListBean>(getView()) {
                    @Override
                    public void onSuccess(FireCheckRecordListBean o) {
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
    public void getFireCheckRecordDetail(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getFireCheckRecordDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<FireCheckBean>(getView()) {
                    @Override
                    public void onSuccess(FireCheckBean o) {
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
    public void applyEditInspectionSitInfo(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .applyEditInspectionSitInfo(requestBody)
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
    public void searchAddInspectSite(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .searchAddInspectSite(requestBody)
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
    public void manualAddInspectSite(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .manualAddInspectSite(requestBody)
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
    public void addInspectionRecord(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .addInspectionRecord(requestBody)
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
    public void checkInspectionSiteNameUnique(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .checkInspectionSiteNameUnique(requestBody)
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
    public void checkImportantorIDUnique(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .checkImportantorIDUnique(requestBody)
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

    @Override
    public void getInspectQuestions(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getInspectQuestions(requestBody)
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

    @Override
    public void getVisitQuestions(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getVisitQuestions(requestBody)
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

    @Override
    public void getVisitRecordList(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getVisitRecordList(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ImportantorVisitRecordListBean>(getView()) {
                    @Override
                    public void onSuccess(ImportantorVisitRecordListBean o) {
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
    public void getVisitRecordDetail(RequestBody requestBody, String tag) {
        AppNetModule.createrRetrofit()
                .getVisitRecordDetail(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<ImportantorVisitRecordDetailBean>(getView()) {
                    @Override
                    public void onSuccess(ImportantorVisitRecordDetailBean o) {
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
