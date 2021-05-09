package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.RuleTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.disabled.bdmap.utils.DateUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.inspection.bean.HeadPicBean;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.bean.ItemFragmentBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.utils.StringTools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.File;
import java.util.Date;
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

    protected abstract String getTitleName();

    protected abstract View getFootView();

    private TextKeyValueBean selectBean;
    private TextView mSelectTv;
    private int unitTypeId = 0;//单位类型id

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
     *
     * @return
     */
    protected MultipartBody.Builder getBuilderOfAdapterData() {
        MultipartBody.Builder builder = mPresenter.getPublishMultipartBody();
        List<MultipleItem> arrays = adapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {

                case MultipleItem.ITEM_HEAD_PIC:
                    HeadPicBean headPicBean = (HeadPicBean) array.getObject();
                    if (TextUtils.isEmpty(headPicBean.getPicPath())) {
                        ToastUtils.toast(mContext, "请选择申请人照片");
                        return null;
                    }
                    builder.addFormDataPart("photoFile", "photoFile", RequestBody.create(MediaType.parse("file"),
                            new File(headPicBean.getPicPath())));
                    break;
                case MultipleItem.ITEM_EDIT:
                    TextKeyValueBean textValueEditBean = (TextKeyValueBean) array
                            .getObject();
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
                    String formKey = null;
                    switch (textValueEditBean.getKey()) {
                        case BaseInspectContract.INSPECTION_TEL:
                            //联系电话
                            if (!RuleTools.isMobileNO(textValueEditBean.getValue())) {
                                ToastUtils.toast(mContext, "联系电话格式不正确");
                                return null;
                            }

                            formKey = "telephone";
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_NAME:
                            //单位名称
                            formKey = "name";
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_ADDR:
                            //单位地址
                            formKey = "address";
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_UCC:
                            //社会信用代码
                            formKey = "unifiedCreditCode";
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON:
                            //单位法人
                            formKey = "legal";
                            break;
                        case BaseInspectContract.INSPECTION_UNIT_LEGAL_PERSON_TEL:
                            //法人手机号
                            formKey = "legalPhone";
                            break;
                        case BaseInspectContract.INSPECTION_RESPONSIBLE:
                            //安全责任人
                            formKey = "personLiable";
                            break;
                        case BaseInspectContract.INSPECTION_RESPONSIBLE_TEL:
                            //安全责任人电话
                            formKey = "liablePhone";
                            break;
                        case BaseInspectContract.INSPECTION_SPARE_PERSON:
                            //备用联系人
                            formKey = "sparePerson";
                            break;
                        case BaseInspectContract.INSPECTION_SPARE_PERSON_TEL:
                            //备用联系人电话
                            formKey = "sparePhone";
                            break;
                        case BaseInspectContract.REMARK:
                            //备注
                            formKey = "remarks";
                            break;

                        default:
                            break;
                    }
                    if (StringTools.isStringValueOk(textValueEditBean.getValue()) && formKey != null) {
                        builder.addFormDataPart(formKey, textValueEditBean.getValue());
                    }

                    break;
                case MultipleItem.ITEM_SELECT:
                    TextKeyValueBean textValueSelectBean = (TextKeyValueBean) array.getObject();
                    if (textValueSelectBean.isImportant() && !StringTools.isStringValueOk(textValueSelectBean.getValue())) {
                        ToastUtils.toast(mContext, "请选择" + textValueSelectBean.getKey());
                        return null;
                    }
                    switch (textValueSelectBean.getKey()) {
                        case BaseInspectContract.INSPECTION_UNIT_TYPE:
                            if (StringTools.isStringValueOk(textValueSelectBean.getValue())) {
                                builder.addFormDataPart("type", String.valueOf(unitTypeId));
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
                    break;

                case MultipleItem.ITEM_FRAGMENT:

                    ItemFragmentBean fragmentBean = (ItemFragmentBean) array.getObject();
                    List<String> photos = fragmentBean.getFragmentPics();
                    if (photos.isEmpty()) {
                        ToastUtils.toast(mContext, "请选择图片");
                        return null;
                    }
                    if (photos.size()<fragmentBean.getMinCount()) {
                        ToastUtils.toast(mContext, "最少需要"+fragmentBean.getMinCount()+"张照片");
                        return null;
                    }
                    for (int i = 0; i < photos.size(); i++) {
                        String picPah = photos.get(i);
                        switch (i) {
                            case 0:
                                builder.addFormDataPart("cover", "cover",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                break;
                            case 1:
                                builder.addFormDataPart("pictureTwo", "pictureTwo",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                break;
                            case 2:
                                builder.addFormDataPart("pictureThree", "pictureThree",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                break;
                            case 3:
                                builder.addFormDataPart("pictureFour", "pictureFour",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                break;
                            case 4:
                                builder.addFormDataPart("pictureFive", "pictureFive",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
                                break;
                            case 5:
                                builder.addFormDataPart("pictureSix", "pictureSix",
                                        RequestBody.create(MediaType.parse("file"),
                                                new File(picPah)));
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
        return builder;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.UNIT_TYPE:
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
                                        unitTypeId = dataBean.getId();
                                    }
                                });
                    }
                }
                break;
        }
    }
}
