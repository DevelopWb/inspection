package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.RuleTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.disabled.federation.R;
import com.juntai.disabled.video.img.ImageZoomActivity;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.base.bottomDialog.MultiSelectBottomSheetDialog;
import com.juntai.wisdom.inspection.base.customview.GestureSignatureView;
import com.juntai.wisdom.inspection.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.HeadPicBean;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.bean.ItemFragmentBean;
import com.juntai.wisdom.inspection.bean.ItemSignBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnQuailityFormBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnQualifiedBean;
import com.juntai.wisdom.inspection.bean.firecheck.WorkerDetailBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorVisitRecordDetailBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.SecurityInspectRecordDetailBean;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnitDetailBean;
import com.juntai.wisdom.inspection.utils.AppUtils;
import com.juntai.wisdom.inspection.utils.StringTools;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: ????????????  ???????????????
 * @CreateDate: 2021/4/22 11:08
 * @UpdateUser: ?????????
 * @UpdateDate: 2021/4/22 11:08
 */
public abstract class BaseInspectionActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView, View.OnClickListener, SelectPhotosFragment.OnPhotoItemClick {

    protected BaseInspectionAdapter adapter;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;
    public static String PARCELABLE_KEY = "parcelable";
    public static String BASE_ID = "baseid";
    public static String BASE_ID2 = "baseid2";
    public static String BASE_STRING = "basestring";
    public String SDCARD_TAG = "/storage/emulated";
    public static String BASE_STRING2 = "basestring2";
    public final static String ADD_UNIT = "????????????";
    public final static String ADD_INSPECTION_SITE = "?????????????????????";
    public final static String ADD_IMPORTANTOR = "??????????????????";
    private HeadPicBean headPicBean;
    private int currentPosition;
    private BottomSheetDialog bottomSheetDialog;
    private GestureSignatureView gsv_signature;
    private ImageView mSignIv;
    private ItemSignBean itemSignBean;
    public TextView mCommitTv;

    protected abstract String getTitleName();


    private TextKeyValueBean selectBean;
    private TextView mSelectTv;

    public boolean idDetail = false;


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
        adapter = new BaseInspectionAdapter(null, idDetail, getSupportFragmentManager());
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        if (getFootView() != null) {
            adapter.setFooterView(getFootView());
        }
        setAdapterClick();


    }

    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_commit, null);
        mCommitTv = view.findViewById(R.id.commit_form_tv);
        mCommitTv.setText("??????");
        mCommitTv.setOnClickListener(this);
        return view;
    }

    /**
     * ???????????????
     */
    protected void commitLogic(MultipartBody.Builder builder) {
    }

    private void setAdapterClick() {
        adapter.setCheckEdittextValueFormatCallBack(new BaseInspectionAdapter.OnCheckEdittextValueFormatCallBack() {
            @Override
            public void checkEdittextValueFormat(TextKeyValueBean keyValueBean) {
                String content = keyValueBean.getValue();
                switch (keyValueBean.getKey()) {
                    case BaseInspectContract.INSPECTION_UNIT_NAME:
                        if (TextUtils.isEmpty(content)) {
                            ToastUtils.toast(mContext, "?????????????????????");
                        } else {
                            //?????????????????????????????????
                            checkUnitUnique(keyValueBean);
                        }
                        break;
                    case BaseInspectContract.INSPECTION_SITE:
                        if (TextUtils.isEmpty(content)) {
                            ToastUtils.toast(mContext, "????????????????????????");
                        } else {
                            //????????????????????????????????????
                            checkUnitUnique(keyValueBean);
                        }
                        break;
                    case BaseInspectContract.INSPECTION_UNIT_UCC:
                        if (TextUtils.isEmpty(content)) {
                            ToastUtils.toast(mContext, "???????????????????????????");
                        } else {
                            //???????????????????????????????????????
                            checkUnitUnique(keyValueBean);

                        }
                        break;
                    case BaseInspectContract.INSPECTION_ID_CARD:
                        if (TextUtils.isEmpty(content)) {
                            ToastUtils.toast(mContext, "?????????????????????");
                        } else {
                            //????????????
                            checkUnitUnique(keyValueBean);

                        }
                        break;
                    default:
                        //                        if (keyValueBean.isImportant()) {
                        //                            if (TextUtils.isEmpty(content)) {
                        //                                ToastUtils.toast(mContext, "?????????" + keyValueBean.getKey());
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
                        HeadPicBean headPicBean = (HeadPicBean) multipleItem.getObject();
                        if (TextUtils.isEmpty(headPicBean.getPicPath())) {
                            choseImage(0, BaseInspectionActivity.this, 1);
                        }else {
                            if (headPicBean.getPicPath().contains("/key_personnel/keyPersonnel.png")) {
                                choseImage(0, BaseInspectionActivity.this, 1);
                            }else {
                                ArrayList<String> photos = new ArrayList<>();
                                photos.add(UrlFormatUtil.getImageOriginalUrl(headPicBean.getPicPath()));
                                //????????????
                                startActivity(new Intent(mContext, ImageZoomActivity.class)
                                        .putExtra("paths", photos)
                                        .putExtra("item", 0));
                            }

                        }

                        break;
                    case R.id.sign_ll:
                        itemSignBean = (ItemSignBean) multipleItem.getObject();
                        //??????
                        mSignIv = (ImageView) view.findViewById(R.id.sign_name_iv);
                        showSignatureView();
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
                            case BaseInspectContract.INSPECTION_CHECK_PROBLEMS:
                                mPresenter.getInspectQuestions(getBaseBuilder().build(),
                                        AppHttpPath.SECURITY_INSPECT_QUESTION);
                                break;
                            case BaseInspectContract.INSPECTION_VISIT_PROBLEMS:
                                mPresenter.getVisitQuestions(getBaseBuilder().build(), AppHttpPath.VISIT_QUESTIONS);
                                break;
                            case BaseInspectContract.INSPECTION_WORK_TYPE:
                                mPresenter.getWorkerType(getBaseBuilder().build(), AppHttpPath.GET_WORKER_TYPE);
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
                                                selectBean.setValue(String.valueOf(getVistTime(nums.get(options1),
                                                        timeUnits.get(options1).get(option2))));
                                                mSelectTv.setText(day);
                                            }
                                        });
                                break;

                            default:
                                break;
                        }
                        break;

                    case R.id.location_ll:
                        //????????????????????????
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
     * ??????????????????
     *
     * @return
     */
    private List<List<String>> getTimeUnits() {
        List<List<String>> arrays = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            List<String> arr = new ArrayList<>();
            arr.add("???");
            arr.add("???");
            arr.add("???");
            arrays.add(arr);
        }

        return arrays;
    }

    /**
     * ?????????????????????
     *
     * @return
     */
    private int getVistTime(String day, String dayUnit) {
        int result = 0;
        switch (dayUnit) {
            case "???":
                result = Integer.parseInt(day) * 1;
                break;
            case "???":
                result = Integer.parseInt(day) * 7;
                break;
            case "???":
                result = Integer.parseInt(day) * 30;
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * ????????????
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
     * ????????????
     *
     * @return
     */
    private List<String> getSexContent() {
        List<String> arrays = new ArrayList<>();
        arrays.add("???");
        arrays.add("???");
        return arrays;
    }

    @Override
    protected void selectedPicsAndEmpressed(List<String> icons) {
        if (icons.size() > 0) {
            String path = icons.get(0);
            headPicBean = (HeadPicBean) ((MultipleItem) adapter.getData().get(currentPosition)).getObject();

            if (BaseInspectContract.INSPECTION_IMPORTANTOR_PHOTO.equals(headPicBean.getPicName())) {
                //??????????????????????????????
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
        ArrayList<String> photos = new ArrayList<>();
        List<String> arrays = adapter.getData();
        for (String array : arrays) {
            if (array.contains(AppHttpPath.BASE_IMAGE_THUM)) {
                array = array.replace(AppHttpPath.BASE_IMAGE_THUM,AppHttpPath.BASE_IMAGE);
            }
            photos.add(array);
        }
        //????????????
        startActivity(new Intent(mContext, ImageZoomActivity.class)
                .putExtra("paths", photos)
                .putExtra("item", position));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signature_view_save:
                if (gsv_signature.getTouched()) {
                    try {
                        String signPath = FileCacheUtils.getAppImagePath() + FileCacheUtils.SIGN_PIC_NAME;
                        //???????????????
                        gsv_signature.save(signPath);
                        if (mSignIv != null) {
                            ImageLoadUtil.loadImageNoCache(mContext, signPath, mSignIv);
                        }
                        if (itemSignBean != null) {
                            itemSignBean.setSignPicPath(signPath);
                        }
                        //                        SINGE_STATE = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //                    mSignNameTagIv.setVisibility(View.GONE);
                    //                    mSignNameNoticeTv.setVisibility(View.GONE);
                    //                    mSignRedactImg.setVisibility(View.VISIBLE);
                    bottomSheetDialog.dismiss();
                    //                    mSignResign.getRightTextView().setVisibility(View.VISIBLE);
                } else {
                    ToastUtils.toast(mContext, "????????????");
                }

                break;

            case R.id.signature_view_rewrite:
                gsv_signature.clear();
                break;
            case R.id.signature_view_cancel:
                gsv_signature.clear();
                bottomSheetDialog.dismiss();
                break;
            case R.id.commit_form_tv:
                //??????
                BaseAdapterDataBean baseAdapterDataBean = getBaseAdapterData(false);
                if (baseAdapterDataBean == null) {
                    return;
                }
                commitLogic(baseAdapterDataBean.getBuilder());
                break;
            default:
                break;
        }
    }

    /**
     * ?????????????????????
     */
    protected void showSignatureView() {

        bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.signature_view_layout, null);
        view.findViewById(R.id.signature_view_save).setOnClickListener(this);
        view.findViewById(R.id.signature_view_rewrite).setOnClickListener(this);
        view.findViewById(R.id.signature_view_cancel).setOnClickListener(this);
        //????????????
        gsv_signature = view.findViewById(R.id.gsv_signature);

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.removeAllFooterView();
            adapter.removeAllHeaderView();
        }
        if (bottomSheetDialog != null) {
            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
            }
            bottomSheetDialog = null;
        }
        //??????????????????
        FileCacheUtils.clearImage(getSignPath(FileCacheUtils.SIGN_PIC_NAME));
    }

    /**
     * ??????adapter????????????
     * skipFilter  ????????????
     *
     * @return
     */
    protected BaseAdapterDataBean getBaseAdapterData(boolean skipFilter) {

        BaseAdapterDataBean bean = new BaseAdapterDataBean();
        UnitDetailBean.DataBean unitDataBean = new UnitDetailBean.DataBean();
        InspectionSiteBean.DataBean inspectionSiteBean = new InspectionSiteBean.DataBean();
        ImportantorBean.DataBean importantorBean = new ImportantorBean.DataBean();
        FireCheckBean.DataBean fireCheckBean = new FireCheckBean.DataBean();
        WorkerDetailBean.DataBean workerBean = new WorkerDetailBean.DataBean();
        SecurityInspectRecordDetailBean.DataBean recordDetailBean = new SecurityInspectRecordDetailBean.DataBean();
        ImportantorVisitRecordDetailBean.DataBean visitRecordDetailBean =
                new ImportantorVisitRecordDetailBean.DataBean();
        MultipartBody.Builder builder = mPresenter.getPublishMultipartBody();
        List<MultipleItem> arrays = adapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
                case MultipleItem.ITEM_SIGN:
                    //??????
                    ItemSignBean signBean = (ItemSignBean) array.getObject();
                    if (!StringTools.isStringValueOk(signBean.getSignPicPath())) {
                        ToastUtils.toast(mContext, "?????????");
                        return null;
                    }
                    builder.addFormDataPart("pictureSign", "pictureSign",
                            RequestBody.create(MediaType.parse(
                                    "file"), new File(getSignPath(FileCacheUtils.SIGN_PIC_NAME))));
                    break;
                case MultipleItem.ITEM_HEAD_PIC:
                    HeadPicBean headPicBean = (HeadPicBean) array.getObject();
                    if (!skipFilter) {
                        if (TextUtils.isEmpty(headPicBean.getPicPath())) {
                            ToastUtils.toast(mContext, "?????????????????????");
                            return null;
                        }
                    }
                    String headPicPath = headPicBean.getPicPath();
                    importantorBean.setPersonnelPhoto(headPicPath);
                    workerBean.setPersonnelPhoto(headPicPath);

                    if (headPicPath.contains(SDCARD_TAG)) {
                        builder.addFormDataPart("personnelPicture", "personnelPicture",
                                RequestBody.create(MediaType.parse("file"),
                                        new File(headPicPath)));
                    } else {
                        if (headPicPath.contains(AppHttpPath.BASE_IMAGE)) {
                            builder.addFormDataPart("personnelPhoto",
                                    headPicPath.substring(AppHttpPath.BASE_IMAGE.length(),
                                            headPicPath.length()));
                        }

                    }
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
                            //                            key = "?????????" + key.substring(1, key.length());
                            //                        } else if (key.contains(mPresenter.PERSIONAL_TAG)) {
                            //                            key = "?????????" + key.substring(1, key.length());
                            //                        }
                            ToastUtils.toast(mContext, "?????????" + key);
                            return null;
                        }
                    }

                    String formKey = null;
                    switch (textValueEditBean.getKey()) {
                        case BaseInspectContract.INSPECTION_TEL:
                            //????????????
                            if (!skipFilter) {
                                if (textValueEditBean.isImportant() && !RuleTools.isMobileNO(value)) {
                                    ToastUtils.toast(mContext, "???????????????????????????");
                                    return null;
                                }
                            }
                            formKey = "phone";
                            importantorBean.setPhone(value);
                            workerBean.setPhone(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_NAME:
                            //????????????
                            formKey = "name";
                            unitDataBean.setName(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_ADDR:
                            //????????????
                            formKey = "address";
                            unitDataBean.setAddress(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_UCC:
                            //??????????????????
                            formKey = "unifiedCreditCode";
                            unitDataBean.setUnifiedCreditCode(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON:
                            //????????????
                            formKey = "legal";
                            unitDataBean.setLegal(value);
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON_TEL:
                            //???????????????
                            if (!skipFilter) {
                                if (textValueEditBean.isImportant() && !RuleTools.isMobileNO(value)) {
                                    ToastUtils.toast(mContext, "??????????????????????????????");
                                    return null;
                                }
                            }
                            formKey = "legalPhone";
                            unitDataBean.setLegalPhone(value);
                            break;
                        case BaseInspectContract.INSPECTION_RESPONSIBLE:
                            //???????????????
                            formKey = "personLiable";
                            unitDataBean.setPersonLiable(value);
                            inspectionSiteBean.setPersonLiable(value);
                            break;
                        case BaseInspectContract.INSPECTION_RESPONSIBLE_TEL:
                            //?????????????????????
                            if (!skipFilter) {
                                if (textValueEditBean.isImportant() && !RuleTools.isMobileNO(value)) {
                                    ToastUtils.toast(mContext, "????????????????????????????????????");
                                    return null;
                                }
                            }
                            formKey = "liablePhone";
                            unitDataBean.setLiablePhone(value);
                            inspectionSiteBean.setLiablePhone(value);
                            break;
                        case BaseInspectContract.INSPECTION_SPARE_PERSON:
                            //???????????????
                            formKey = "sparePerson";
                            unitDataBean.setSparePerson(value);
                            inspectionSiteBean.setSparePerson(value);
                            importantorBean.setSparePerson(value);
                            break;
                        case BaseInspectContract.INSPECTION_SPARE_PERSON_TEL:
                            //?????????????????????
                            if (!skipFilter) {
                                if (textValueEditBean.isImportant() && !RuleTools.isMobileNO(value)) {
                                    ToastUtils.toast(mContext, "????????????????????????????????????");
                                    return null;
                                }
                            }
                            formKey = "sparePhone";
                            unitDataBean.setSparePhone(value);
                            inspectionSiteBean.setSparePhone(value);
                            importantorBean.setSparePhone(value);
                            break;
                        case BaseInspectContract.REMARK:
                            //??????
                            formKey = "remarks";
                            if ("????????????".equals(getTitleName())) {
                                formKey = "concreteProblems";
                            }
                            unitDataBean.setRemarks(value);
                            inspectionSiteBean.setRemarks(value);
                            fireCheckBean.setConcreteProblems(value);
                            importantorBean.setRemarks(value);
                            workerBean.setRemarks(value);
                            recordDetailBean.setRemarks(value);
                            visitRecordDetailBean.setRemarks(value);
                            break;
                        case BaseInspectContract.INSPECTION_SITE:
                            //?????????
                            formKey = "name";
                            inspectionSiteBean.setName(value);
                            break;
                        case BaseInspectContract.PUNISH_INFO:
                            //????????????
                            formKey = "content";
                            break;
                        case BaseInspectContract.INSPECTION_ADDR:
                            //????????????
                            formKey = "address";
                            inspectionSiteBean.setAddress(value);
                            break;
                        case BaseInspectContract.INSPECTION_NAME:
                            //??????
                            formKey = "name";
                            importantorBean.setName(value);
                            workerBean.setName(value);
                            break;
                        case BaseInspectContract.INSPECTION_NICK_NAME:
                            //?????????
                            formKey = "nickname";
                            importantorBean.setNickname(value);
                            break;
                        case BaseInspectContract.INSPECTION_POLICE_NAME:
                            //????????????
                            formKey = "policeName";
                            importantorBean.setPoliceName(value);
                            break;
                        case BaseInspectContract.INSPECTION_ID_CARD:
                            //????????????
                            if (!skipFilter) {
                                if (textValueEditBean.isImportant() && !RuleTools.isIdNO(mContext,
                                        textValueEditBean.getValue())) {
                                    ToastUtils.toast(mContext, "???????????????????????????");
                                    return null;
                                }
                            }
                            //????????????
                            formKey = "idNumber";
                            importantorBean.setIdNumber(value);
                            workerBean.setIdNumber(value);
                            break;
                        case BaseInspectContract.INSPECTION_ADDR_LATEST:
                            //????????????
                            formKey = "address";
                            importantorBean.setAddress(value);
                            workerBean.setAddress(value);
                            break;
                        case BaseInspectContract.INSPECTION_WORK_UNIT_LATEST:
                            //???????????????
                            formKey = "unitName";
                            importantorBean.setUnitName(value);
                            workerBean.setUnitName(value);
                            break;
                        case BaseInspectContract.INSPECTION_OTHER_CONNECT_TYPE:
                            //??????????????????(QQ?????????????????????)
                            formKey = "otherPhone";
                            importantorBean.setOtherPhone(value);
                            break;
                        case BaseInspectContract.INSPECTION_RESULT_DESCRIPTION:
                            //??????????????????
                            formKey = "treatment";
                            importantorBean.setTreatment(value);
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
                    String selectBeanValue = textValueSelectBean.getValue();
                    if (!skipFilter) {
                        if (textValueSelectBean.isImportant() && !StringTools.isStringValueOk(selectBeanValue)) {
                            ToastUtils.toast(mContext, "?????????" + textValueSelectBean.getKey());
                            return null;
                        }
                    }

                    switch (textValueSelectBean.getKey()) {
                        case BaseInspectContract.INSPECTION_UNIT_TYPE:
                            builder.addFormDataPart("type", textValueSelectBean.getIds());
                            unitDataBean.setTypeName(selectBeanValue);
                            if (!TextUtils.isEmpty(textValueSelectBean.getIds())) {
                                unitDataBean.setTypeId(Integer.parseInt(textValueSelectBean.getIds()));
                            }

                            break;
                        case BaseInspectContract.INSPECTION_SEX:

                            if (!TextUtils.isEmpty(selectBeanValue)) {
                                builder.addFormDataPart("gender", "???".equals(selectBeanValue) ? "1" : "2");
                                importantorBean.setGender("???".equals(selectBeanValue) ? 1 : 2);
                                workerBean.setGender("???".equals(selectBeanValue) ? 1 : 2);
                            }

                            break;
                        case BaseInspectContract.INSPECTION_PERSONAL_TYPE:
                            builder.addFormDataPart("typeId", textValueSelectBean.getIds());
                            importantorBean.setTypeName(selectBeanValue);
                            if (!TextUtils.isEmpty(textValueSelectBean.getIds())) {
                                importantorBean.setTypeId(textValueSelectBean.getIds());
                            }

                            break;
                        case BaseInspectContract.INSPECTION_PERSONAL_STATUS:
                            builder.addFormDataPart("keyStatus", textValueSelectBean.getIds());
                            importantorBean.setKeyStatusName(selectBeanValue);
                            if (!TextUtils.isEmpty(textValueSelectBean.getIds())) {
                                importantorBean.setKeyStatus(Integer.parseInt(textValueSelectBean.getIds()));
                            }

                            break;
                        case BaseInspectContract.INSPECTION_CHECK_PROBLEMS:
                            builder.addFormDataPart("typeId", textValueSelectBean.getIds());
                            if (!TextUtils.isEmpty(textValueSelectBean.getIds())) {
                                recordDetailBean.setTypeId(Integer.parseInt(textValueSelectBean.getIds()));
                            }
                            recordDetailBean.setTypeName(selectBeanValue);
                            break;
                        case BaseInspectContract.INSPECTION_VISIT_PROBLEMS:
                            builder.addFormDataPart("inspectionId", textValueSelectBean.getIds());
                            if (!TextUtils.isEmpty(textValueSelectBean.getIds())) {
                                visitRecordDetailBean.setInspectionId(Integer.parseInt(textValueSelectBean.getIds()));
                            }
                            visitRecordDetailBean.setInspectionName(selectBeanValue);
                            break;
                        case BaseInspectContract.INSPECTION_WORK_TYPE:
                            builder.addFormDataPart("postId", textValueSelectBean.getIds());
                            if (!TextUtils.isEmpty(textValueSelectBean.getIds())) {
                                workerBean.setPostId(Integer.parseInt(textValueSelectBean.getIds()));
                            }
                            workerBean.setPostName(selectBeanValue);
                            break;
                        case BaseInspectContract.INSPECTION_VISIT_TIMES:
                            //????????????
                            builder.addFormDataPart("checkTime", selectBeanValue);
                            if (!TextUtils.isEmpty(selectBeanValue)) {
                                importantorBean.setCheckTime(Integer.parseInt(selectBeanValue));
                            }
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
                    importantorBean.setGpsAddress(locationBean.getAddress());
                    importantorBean.setLatitude(locationBean.getLatitude());
                    importantorBean.setLongitude(locationBean.getLongitude());
                    break;
                case MultipleItem.ITEM_FIRE_CHECK_FORM:
                    //??????????????????
                    UnQuailityFormBean formBean = (UnQuailityFormBean) array.getObject();
                    String json = formBean.getProblems();
                    fireCheckBean.setItemsJson(json);
                    List<UnQualifiedBean> list = GsonTools.changeGsonToList(json, UnQualifiedBean.class);
                    for (UnQualifiedBean unQualifiedBean : list) {
                        if (11 == unQualifiedBean.getItemid()) {
                            if (1 == unQualifiedBean.getChild().get(0).getSelectStatus()) {
                                //?????????????????????11  ????????????????????????
                                if (TextUtils.isEmpty(formBean.getOtherProblem())) {
                                    if (!skipFilter) {
                                        ToastUtils.toast(mContext, "???????????????11?????????");
                                        return null;
                                    }
                                } else {
                                    fireCheckBean.setOtherProblem(formBean.getOtherProblem());
                                    builder.addFormDataPart("otherProblem", formBean.getOtherProblem());
                                }
                            }
                        }
                    }
                    builder.addFormDataPart("itemsJson", json);
                    if (TextUtils.isEmpty(formBean.getConcreteProblems())) {
                        if (!skipFilter) {
                            ToastUtils.toast(mContext, "?????????????????????");
                            return null;
                        }
                    } else {
                        builder.addFormDataPart("concreteProblems", formBean.getConcreteProblems());
                        fireCheckBean.setConcreteProblems(formBean.getConcreteProblems());
                    }
                    if (TextUtils.isEmpty(formBean.getItemOne()) && TextUtils.isEmpty(formBean.getItemTwo())) {

                        if (!skipFilter) {
                            ToastUtils.toast(mContext, "?????????????????????????????????");
                            return null;
                        }
                    } else {
                        if (!TextUtils.isEmpty(formBean.getItemOne())) {
                            fireCheckBean.setItemOne(formBean.getItemOne());
                            builder.addFormDataPart("itemOne", formBean.getItemOne());
                        }
                        if (!TextUtils.isEmpty(formBean.getItemTwo())) {
                            fireCheckBean.setItemTwo(formBean.getItemTwo());
                            builder.addFormDataPart("itemTwo", formBean.getItemTwo());
                        }
                    }
                    if (TextUtils.isEmpty(formBean.getItemOneTime()) && TextUtils.isEmpty(formBean.getItemTwoTime())) {
                        if (!skipFilter) {
                            ToastUtils.toast(mContext, "??????????????????????????????");
                            return null;
                        }
                    } else {
                        if (!TextUtils.isEmpty(formBean.getItemOneTime())) {
                            fireCheckBean.setItemOneTime(formBean.getItemOneTime());
                            builder.addFormDataPart("itemOneTime", formBean.getItemOneTime());
                        }
                        if (!TextUtils.isEmpty(formBean.getItemTwoTime())) {
                            fireCheckBean.setItemTwoTime(formBean.getItemTwoTime());
                            builder.addFormDataPart("itemTwoTime", formBean.getItemTwoTime());
                        }
                    }


                    break;
                case MultipleItem.ITEM_FRAGMENT:
                    ItemFragmentBean fragmentBean = (ItemFragmentBean) array.getObject();
                    List<String> photos = fragmentBean.getFragmentPics();
                    if (!skipFilter) {
                        if (photos.isEmpty()) {
                            ToastUtils.toast(mContext, "???????????????");
                            return null;
                        }
                        if (photos.size() < fragmentBean.getMinCount()) {
                            ToastUtils.toast(mContext, "????????????" + fragmentBean.getMinCount() + "?????????");
                            return null;
                        }
                    }

                    for (int i = 0; i < photos.size(); i++) {
                        String picPah = photos.get(i);
                        switch (i) {
                            case 0:
                                if ("??????????????????".equals(getTitleName()) || "????????????".equals(getTitleName())
                                        || "????????????".equals(getTitleName())
                                        || "?????????????????????".equals(getTitleName())
                                        || "?????????????????????".equals(getTitleName())
                                        || "????????????".equals(getTitleName())) {
                                    if (picPah.contains(SDCARD_TAG)) {
                                        builder.addFormDataPart("pictureOne", "pictureOne",
                                                RequestBody.create(MediaType.parse("file"),
                                                        new File(picPah)));
                                    }

                                } else {
                                    if (picPah.contains(SDCARD_TAG)) {
                                        builder.addFormDataPart("cover", "cover",
                                                RequestBody.create(MediaType.parse("file"),
                                                        new File(picPah)));
                                    } else {
                                        builder.addFormDataPart("coverPicture",
                                                picPah.substring(AppHttpPath.BASE_IMAGE_THUM.length(),
                                                        picPah.length()));
                                    }
                                }
                                recordDetailBean.setPhotoOne(picPah);
                                visitRecordDetailBean.setPhotoOne(picPah);
                                unitDataBean.setCoverPicture(picPah);
                                inspectionSiteBean.setCoverPicture(picPah);
                                fireCheckBean.setPhotoOne(picPah);
                                break;
                            case 1:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureTwo", "pictureTwo",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoTwo",
                                            picPah.substring(AppHttpPath.BASE_IMAGE_THUM.length(),
                                                    picPah.length()));
                                }
                                unitDataBean.setPhotoTwo(picPah);
                                inspectionSiteBean.setPhotoTwo(picPah);
                                recordDetailBean.setPhotoTwo(picPah);
                                visitRecordDetailBean.setPhotoTwo(picPah);
                                fireCheckBean.setPhotoTwo(picPah);
                                break;
                            case 2:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureThree", "pictureThree",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoThree",
                                            picPah.substring(AppHttpPath.BASE_IMAGE_THUM.length(),
                                                    picPah.length()));
                                }
                                unitDataBean.setPhotoThree(picPah);
                                inspectionSiteBean.setPhotoThree(picPah);
                                recordDetailBean.setPhotoThree(picPah);
                                visitRecordDetailBean.setPhotoThree(picPah);
                                fireCheckBean.setPhotoThree(picPah);
                                break;
                            case 3:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureFour", "pictureFour",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoFour",
                                            picPah.substring(AppHttpPath.BASE_IMAGE_THUM.length(),
                                                    picPah.length()));
                                }
                                unitDataBean.setPhotoFour(picPah);
                                inspectionSiteBean.setPhotoFour(picPah);
                                recordDetailBean.setPhotoFour(picPah);
                                visitRecordDetailBean.setPhotoFour(picPah);
                                fireCheckBean.setPhotoFour(picPah);
                                break;
                            case 4:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureFive", "pictureFive",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoFive",
                                            picPah.substring(AppHttpPath.BASE_IMAGE_THUM.length(),
                                                    picPah.length()));
                                }
                                unitDataBean.setPhotoFive(picPah);
                                inspectionSiteBean.setPhotoFive(picPah);
                                recordDetailBean.setPhotoFive(picPah);
                                visitRecordDetailBean.setPhotoFive(picPah);
                                fireCheckBean.setPhotoFive(picPah);
                                break;
                            case 5:
                                if (picPah.contains(SDCARD_TAG)) {
                                    builder.addFormDataPart("pictureSix", "pictureSix",
                                            RequestBody.create(MediaType.parse("file"),
                                                    new File(picPah)));
                                } else {
                                    builder.addFormDataPart("photoSix",
                                            picPah.substring(AppHttpPath.BASE_IMAGE_THUM.length(),
                                                    picPah.length()));
                                }
                                unitDataBean.setPhotoSix(picPah);
                                inspectionSiteBean.setPhotoSix(picPah);
                                recordDetailBean.setPhotoSix(picPah);
                                visitRecordDetailBean.setPhotoSix(picPah);
                                fireCheckBean.setPhotoSix(picPah);
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
        bean.setImportantorBean(importantorBean);
        bean.setRecordDetailBean(recordDetailBean);
        bean.setVisitRecordDetailBean(visitRecordDetailBean);
        bean.setFireCheckBean(fireCheckBean);
        bean.setWorkerBean(workerBean);
        return bean;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        if (AppHttpPath.UNIT_TYPE.equals(tag) || AppHttpPath.SECURITY_INSPECT_QUESTION.equals(tag)
                || AppHttpPath.IMPORTANTOR_STATUS.equals(tag)
                || AppHttpPath.GET_WORKER_TYPE.equals(tag) || AppHttpPath.VISIT_QUESTIONS.equals(tag)) {
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
                                    selectBean.setIds(String.valueOf(dataBean.getId()));
                                    mSelectTv.setText(dataBean.getName());
                                    if (AppHttpPath.IMPORTANTOR_STATUS.equals(tag)) {
                                       int defaultCheckTime = dataBean.getCheckTime();
                                        //?????????????????? ??????????????????
                                        List<MultipleItem> arrays = adapter.getData();
                                        for (MultipleItem array : arrays) {
                                            if (array.getItemType() == MultipleItem.ITEM_SELECT) {
                                                TextKeyValueBean textKeyValueBean = (TextKeyValueBean) array.getObject();
                                                if (BaseInspectContract.INSPECTION_VISIT_TIMES.equals(textKeyValueBean.getKey())) {
                                                    textKeyValueBean.setValue(String.valueOf(defaultCheckTime));
                                                    adapter.notifyDataSetChanged();
                                                    break;
                                                }
                                            }
                                        }

                                    }

                                }
                            });
                }
            }
        } else if (AppHttpPath.IMPORTANTOR_TYPES.equals(tag)) {
            IdNameBean idNameBean = (IdNameBean) o;
            if (idNameBean != null) {
                List<IdNameBean.DataBean> arrays = idNameBean.getData();
                MultiSelectBottomSheetDialog multiSelectBottomSheetDialog =
                        new MultiSelectBottomSheetDialog(mContext,
                                new MultiSelectBottomSheetDialog.OnConfirmCallBack() {
                                    @Override
                                    public void confirm(String names, String ids) {
                                        selectBean.setValue(names);
                                        selectBean.setIds(ids);
                                        mSelectTv.setText(names);
                                    }
                                });
                multiSelectBottomSheetDialog.setAdapterData(arrays);
            }
        } else {

        }
    }
}
