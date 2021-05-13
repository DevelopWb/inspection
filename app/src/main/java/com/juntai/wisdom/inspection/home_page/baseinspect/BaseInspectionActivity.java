package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.RuleTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.HeadPicBean;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.bean.ItemFragmentBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.utils.StringTools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述  巡检的基类
 * @CreateDate: 2021/4/22 11:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 11:08
 */
public abstract class BaseInspectionActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView, View.OnClickListener, SelectPhotosFragment.OnPhotoItemClick {

    protected BaseInspectionAdapter adapter;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    public static String PARCELABLE_KEY = "parcelable";
    public final static String ADD_UNIT = "添加单位";
    public final static String ADD_INSPECTION_SITE = "添加治安巡检点";
    public final static String ADD_IMPORTANTOR = "添加重点人员";
    private HeadPicBean headPicBean;
    private int currentPosition;


    protected abstract String getTitleName();

    protected abstract View getFootView();

    private TextKeyValueBean selectBean;
    private TextView mSelectTv;
    public int importantorStatusId = 0;//人员状态
    public int unitTypeId = 0;//单位类型id
    public String unitTypeName;//单位类型id

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.recycleview_layout;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        adapter = new BaseInspectionAdapter(null, false, getSupportFragmentManager());
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        if (getFootView() != null) {
            adapter.setFooterView(getFootView());
        }
        setAdapterClick();


    }

    private void setAdapterClick() {
        adapter.setCheckEdittextValueFormatCallBack(new BaseInspectionAdapter.OnCheckEdittextValueFormatCallBack() {
            @Override
            public void checkEdittextValueFormat(TextKeyValueBean keyValueBean) {
                String content = keyValueBean.getValue();
                switch (keyValueBean.getKey()) {
                    case BaseInspectContract.INSPECTION_UNIT_NAME:
                        if (TextUtils.isEmpty(content)) {
                            ToastUtils.toast(mContext, "请输入单位名称");
                        } else {
                            //检查单位名称是否是唯一
                            checkUnitUnique(keyValueBean);
                        }
                        break;
                    case BaseInspectContract.INSPECTION_SITE:
                        if (TextUtils.isEmpty(content)) {
                            ToastUtils.toast(mContext, "请输入巡检点名称");
                        } else {
                            //检查巡检点名称是否是唯一
                            checkUnitUnique(keyValueBean);
                        }
                        break;
                    case BaseInspectContract.INSPECTION_UNIT_UCC:
                        if (TextUtils.isEmpty(content)) {
                            ToastUtils.toast(mContext, "请输入社会信用代码");
                        } else {
                            //检查社会信用代码是否是唯一
                            checkUnitUnique(keyValueBean);

                        }
                        break;
                    default:
                        //                        if (keyValueBean.isImportant()) {
                        //                            if (TextUtils.isEmpty(content)) {
                        //                                ToastUtils.toast(mContext, "请输入" + keyValueBean.getKey());
                        //                            }
                        //                        }

                        break;
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {


            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                currentPosition = position;
                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.form_head_pic_iv:
                        choseImage(0, BaseInspectionActivity.this, 1);
                        break;


                    case R.id.select_value_tv:
                        mSelectTv = (TextView) adapter.getViewByPosition(mRecyclerview, position,
                                R.id.select_value_tv);
                        selectBean = (TextKeyValueBean) multipleItem.getObject();
                        switch (selectBean.getKey()) {
                            case BaseInspectContract.INSPECTION_UNIT_TYPE:
                                mPresenter.getUnitType(getBaseBuilder().build(), AppHttpPath.UNIT_TYPE);
                                break;
                            case BaseInspectContract.INSPECTION_SEX:
                                List<String> sexs = getSexContent();
                                PickerManager.getInstance().showOptionPicker(mContext, sexs,
                                        new PickerManager.OnOptionPickerSelectedListener() {
                                            @Override
                                            public void onOptionsSelect(int options1, int option2, int options3,
                                                                        View v) {
                                                String str = sexs.get(options1);
                                                selectBean.setValue(str);
                                                mSelectTv.setText(str);
                                            }
                                        });
                                break;
                            case BaseInspectContract.INSPECTION_PERSONAL_TYPE:
                                mPresenter.getImportantorTypes(getBaseBuilder().build(), AppHttpPath.IMPORTANTOR_TYPES);
                                break;
                            case BaseInspectContract.INSPECTION_PERSONAL_STATUS:
                                mPresenter.getImportantorStatus(getBaseBuilder().build(),
                                        AppHttpPath.IMPORTANTOR_STATUS);
                                break;
                            case BaseInspectContract.INSPECTION_VISIT_TIMES:
                                List<String> nums = getNums();
                                List<List<String>> timeUnits = getTimeUnits();
                                PickerManager.getInstance().showOptionPicker(mContext, nums, timeUnits,
                                        new PickerManager.OnOptionPickerSelectedListener() {
                                            @Override
                                            public void onOptionsSelect(int options1, int option2, int options3,
                                                                        View v) {
                                                String day = String.format("%s%s", nums.get(options1),
                                                        timeUnits.get(options1).get(option2));
                                                selectBean.setValue(String.valueOf(getVistTime(nums.get(options1),timeUnits.get(options1).get(option2))));
                                                mSelectTv.setText(day);
                                            }
                                        });
                                break;

                            default:
                                break;
                        }
                        break;

                    case R.id.location_ll:
                        //跳转到选择位置类
                        startActivityForResult(new Intent(mContext, LocateSelectionActivity.class),
                                LocateSelectionActivity.SELECT_ADDR);
                        break;
                    default:
                        break;
                }

            }
        });


    }

    /**
     * 获取时间单位
     *
     * @return
     */
    private List<List<String>> getTimeUnits() {
        List<List<String>> arrays = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            List<String> arr = new ArrayList<>();
            arr.add("日");
            arr.add("周");
            arr.add("月");
            arrays.add(arr);
        }

        return arrays;
    }

    /**
     * 获取走访的频率
     *
     * @return
     */
    private int getVistTime(String day,String dayUnit) {
        int result =0;
        switch (dayUnit) {
            case "日":
                result = Integer.parseInt(day)*1;
                break;
            case "周":
                result = Integer.parseInt(day)*7;
                break;
            case "月":
                result = Integer.parseInt(day)*30;
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 获取天数
     *
     * @return
     */
    private List<String> getNums() {
        List<String> arrays = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            arrays.add(String.valueOf(i));
        }
        return arrays;
    }

    /**
     * 获取性别
     *
     * @return
     */
    private List<String> getSexContent() {
        List<String> arrays = new ArrayList<>();
        arrays.add("男");
        arrays.add("女");
        return arrays;
    }

    @Override
    protected void selectedPicsAndEmpressed(List<String> icons) {
        if (icons.size() > 0) {
            String path = icons.get(0);
            headPicBean = (HeadPicBean) ((MultipleItem) adapter.getData().get(currentPosition)).getObject();

            if (BaseInspectContract.INSPECTION_IMPORTANTOR_PHOTO.equals(headPicBean.getPicName())) {
                //跳转到裁剪头像的界面
                startActivityForResult(new Intent(this, HeadCropActivity.class).putExtra(HeadCropActivity.HEAD_PIC,
                        path), BASE_REQUEST_RESULT);
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == BASE_REQUEST_RESULT) {
            if (data != null) {
                String path = data.getStringExtra(HeadCropActivity.CROPED_HEAD_PIC);
                if (headPicBean != null && adapter != null) {
                    headPicBean.setPicPath(path);
                    adapter.notifyItemChanged(currentPosition);
                }

            }

        }

    }

    public void checkUnitUnique(TextKeyValueBean keyValueBean) {
    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 获取adapter中的数据
     * skipFilter  跳过过滤
     *
     * @return
     */
    protected BaseAdapterDataBean getBaseAdapterData(boolean skipFilter) {

        BaseAdapterDataBean bean = new BaseAdapterDataBean();
        SearchedUnitsBean.DataBean.DatasBean unitDataBean = new SearchedUnitsBean.DataBean.DatasBean();
        InspectionSiteBean.DataBean inspectionSiteBean = new InspectionSiteBean.DataBean();
        MultipartBody.Builder builder = mPresenter.getPublishMultipartBody();
        List<MultipleItem> arrays = adapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {

                case MultipleItem.ITEM_HEAD_PIC:
                    HeadPicBean headPicBean = (HeadPicBean) array.getObject();
                    if (!skipFilter) {
                        if (TextUtils.isEmpty(headPicBean.getPicPath())) {
                            ToastUtils.toast(mContext, "请选择头像照片");
                            return null;
                        }
                    }
                    builder.addFormDataPart("personnelPicture", "personnelPicture",
                            RequestBody.create(MediaType.parse("file"),
                                    new File(headPicBean.getPicPath())));
                    break;
                case MultipleItem.ITEM_EDIT:
                    TextKeyValueBean textValueEditBean = (TextKeyValueBean) array
                            .getObject();
                    String value = textValueEditBean.getValue();
                    if (!skipFilter) {
                        if (textValueEditBean.isImportant() && TextUtils.isEmpty(textValueEditBean
                                .getValue())) {
                            String key = textValueEditBean.getKey();
                            //                        if (key.contains(mPresenter.FAMILY_TAG)) {
                            //                            key = "监护人" + key.substring(1, key.length());
                            //                        } else if (key.contains(mPresenter.PERSIONAL_TAG)) {
                            //                            key = "残疾人" + key.substring(1, key.length());
                            //                        }
                            ToastUtils.toast(mContext, "请输入" + key);
                            return null;
                        }
                    }

                    String formKey = null;
                    switch (textValueEditBean.getKey()) {
                        case BaseInspectContract.INSPECTION_TEL:
                            //联系电话
                            if (!RuleTools.isMobileNO(value)) {
                                ToastUtils.toast(mContext, "联系电话格式不正确");
                                return null;
                            }

                            formKey = "telephone";
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_NAME:
                            //单位名称
                            formKey = "name";
                            unitDataBean.setName(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_ADDR:
                            //单位地址
                            formKey = "address";
                            unitDataBean.setAddress(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_UCC:
                            //社会信用代码
                            formKey = "unifiedCreditCode";
                            unitDataBean.setUnifiedCreditCode(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON:
                            //单位法人
                            formKey = "legal";
                            unitDataBean.setLegal(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON_TEL:
                            //法人手机号
                            formKey = "legalPhone";
                            unitDataBean.setLegalPhone(value);
                            break;
                        case BaseInspectContract.INSPECTION_RESPONSIBLE:
                            //安全责任人
                            formKey = "personLiable";
                            unitDataBean.setPersonLiable(value);
                            inspectionSiteBean.setPersonLiable(value);
                            break;
                        case BaseInspectContract.INSPECTION_RESPONSIBLE_TEL:
                            //安全责任人电话
                            formKey = "liablePhone";
                            unitDataBean.setLiablePhone(value);
                            inspectionSiteBean.setLiablePhone(value);
                            break;
                        case BaseInspectContract.INSPECTION_SPARE_PERSON:
                            //备用联系人
                            formKey = "sparePerson";
                            unitDataBean.setSparePerson(value);
                            inspectionSiteBean.setSparePerson(value);
                            break;
                        case BaseInspectContract.INSPECTION_SPARE_PERSON_TEL:
                            //备用联系人电话
                            formKey = "sparePhone";
                            unitDataBean.setSparePhone(value);
                            inspectionSiteBean.setSparePhone(value);
                            break;
                        case BaseInspectContract.REMARK:
                            //备注
                            formKey = "remarks";
                            unitDataBean.setRemarks(value);
                            inspectionSiteBean.setRemarks(value);
                            break;
                        case BaseInspectContract.INSPECTION_SITE:
                            //巡检点
                            formKey = "name";
                            inspectionSiteBean.setName(value);
                            break;
                        case BaseInspectContract.INSPECTION_ADDR:
                            //巡检地址
                            formKey = "address";
                            inspectionSiteBean.setAddress(value);
                            break;
                        default:
                            break;
                    }
                    if (StringTools.isStringValueOk(value) && formKey != null) {
                        builder.addFormDataPart(formKey, value);
                    }

                    break;
                case MultipleItem.ITEM_SELECT:
                    TextKeyValueBean textValueSelectBean = (TextKeyValueBean) array.getObject();
                    if (!skipFilter) {
                        if (textValueSelectBean.isImportant() && !StringTools.isStringValueOk(textValueSelectBean.getValue())) {
                            ToastUtils.toast(mContext, "请选择" + textValueSelectBean.getKey());
                            return null;
                        }
                    }

                    switch (textValueSelectBean.getKey()) {
                        case BaseInspectContract.INSPECTION_UNIT_TYPE:
                            builder.addFormDataPart("type", String.valueOf(unitTypeId));
                            unitDataBean.setTypeName(unitTypeName);
                            unitDataBean.setTypeId(unitTypeId);
                            break;
                        case BaseInspectContract.INSPECTION_SEX:
                            builder.addFormDataPart("gender", "男".equals(textValueSelectBean.getValue()) ? "1" : "2");
                            break;
                        case BaseInspectContract.INSPECTION_PERSONAL_STATUS:
                            builder.addFormDataPart("keyStatus", String.valueOf(importantorStatusId));
                            break;
                        default:
                            break;
                    }
                    break;
                case MultipleItem.ITEM_LOCATION:
                    LocationBean locationBean = (LocationBean) array.getObject();
                    builder.addFormDataPart("gpsAddress", locationBean.getAddress());
                    builder.addFormDataPart("longitude", locationBean.getLongitude());
                    builder.addFormDataPart("latitude", locationBean.getLatitude());
                    unitDataBean.setGpsAddress(locationBean.getAddress());
                    unitDataBean.setLatitude(locationBean.getLatitude());
                    unitDataBean.setLongitude(locationBean.getLongitude());
                    inspectionSiteBean.setGpsAddress(locationBean.getAddress());
                    inspectionSiteBean.setLatitude(locationBean.getLatitude());
                    inspectionSiteBean.setLongitude(locationBean.getLongitude());
                    break;

                case MultipleItem.ITEM_FRAGMENT:
                    ItemFragmentBean fragmentBean = (ItemFragmentBean) array.getObject();
                    List<String> photos = fragmentBean.getFragmentPics();
                    if (!skipFilter) {
                        if (photos.isEmpty()) {
                            ToastUtils.toast(mContext, "请选择图片");
                            return null;
                        }
                        if (photos.size() < fragmentBean.getMinCount()) {
                            ToastUtils.toast(mContext, "最少需要" + fragmentBean.getMinCount() + "张照片");
                            return null;
                        }
                    }

                    for (int i = 0; i < photos.size(); i++) {
                        String picPah = photos.get(i);
                        switch (i) {
                            case 0:
                                builder.addFormDataPart("cover", "cover",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                unitDataBean.setCoverPicture(picPah);
                                inspectionSiteBean.setCoverPicture(picPah);
                                break;
                            case 1:
                                builder.addFormDataPart("pictureTwo", "pictureTwo",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                unitDataBean.setPhotoTwo(picPah);
                                inspectionSiteBean.setPhotoTwo(picPah);
                                break;
                            case 2:
                                builder.addFormDataPart("pictureThree", "pictureThree",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                unitDataBean.setPhotoThree(picPah);
                                inspectionSiteBean.setPhotoThree(picPah);
                                break;
                            case 3:
                                builder.addFormDataPart("pictureFour", "pictureFour",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                unitDataBean.setPhotoFour(picPah);
                                inspectionSiteBean.setPhotoFour(picPah);
                                break;
                            case 4:
                                builder.addFormDataPart("pictureFive", "pictureFive",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                unitDataBean.setPhotoFive(picPah);
                                inspectionSiteBean.setPhotoFive(picPah);
                                break;
                            case 5:
                                builder.addFormDataPart("pictureSix", "pictureSix",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                unitDataBean.setPhotoSix(picPah);
                                inspectionSiteBean.setPhotoSix(picPah);
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        bean.setBuilder(builder);
        bean.setUnitDataBean(unitDataBean);
        bean.setInspectionSiteBean(inspectionSiteBean);
        return bean;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        if (AppHttpPath.UNIT_TYPE.equals(tag) || AppHttpPath.IMPORTANTOR_STATUS.equals(tag)) {
            IdNameBean idNameBean = (IdNameBean) o;
            if (idNameBean != null) {
                List<IdNameBean.DataBean> arrays = idNameBean.getData();
                if (arrays != null && arrays.size() > 0) {
                    PickerManager.getInstance().showOptionPicker(mContext, arrays,
                            new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    IdNameBean.DataBean dataBean = arrays.get(options1);
                                    selectBean.setValue(dataBean.getName());
                                    mSelectTv.setText(dataBean.getName());
                                    switch (tag) {
                                        case AppHttpPath.UNIT_TYPE:
                                            unitTypeId = dataBean.getId();
                                            unitTypeName = dataBean.getName();
                                            break;
                                        case AppHttpPath.IMPORTANTOR_TYPES:
                                            break;
                                        case AppHttpPath.IMPORTANTOR_STATUS:
                                            importantorStatusId = dataBean.getId();
                                            break;
                                        default:
                                            break;
                                    }

                                }
                            });
                }
            }
        } else if (AppHttpPath.IMPORTANTOR_TYPES.equals(tag)) {
            IdNameBean idNameBean = (IdNameBean) o;
            if (idNameBean != null) {
                List<IdNameBean.DataBean> arrays = idNameBean.getData();
            }
        } else {

        }
    }
}
