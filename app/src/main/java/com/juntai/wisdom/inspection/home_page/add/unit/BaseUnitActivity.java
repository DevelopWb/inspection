package com.juntai.wisdom.inspection.home_page.add.unit;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  单位
 * @date 2021/5/7 11:30
 */
public abstract class BaseUnitActivity extends BaseInspectionActivity {

    private boolean isUnitNameUnque = false;//单位名称是否唯一
    private boolean isUnitUCCUnique = false;//社会信用代码是否唯一
    public SearchedUnitsBean.DataBean.DatasBean bean;

    @Override
    public void initData() {
        if (getIntent() != null) {
            bean = getIntent().getParcelableExtra(PARCELABLE_KEY);
            adapter.setNewData(mPresenter.getUnitInfoData(bean));
            if (bean != null) {
                isUnitNameUnque = true;
                if (!TextUtils.isEmpty(bean.getUnifiedCreditCode())) {
                    isUnitUCCUnique = true;
                }

            }
        }

    }

    @Override
    public boolean requestLocation() {
        return true;
    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {
        super.onLocationReceived(bdLocation);
        if (bdLocation != null) {
            lat = bdLocation.getLatitude();
            lng = bdLocation.getLongitude();
            address = bdLocation.getAddrStr();
            notifyLocationItem();
        }

    }

    /**
     * 更新定位item
     */
    private void notifyLocationItem() {
        List<MultipleItem> arrays = adapter.getData();
        for (int i = 0; i < arrays.size(); i++) {
            MultipleItem array = arrays.get(i);
            if (MultipleItem.ITEM_LOCATION== array.getItemType()) {
                //定位
                LocationBean locationBean = (LocationBean) array.getObject();
                locationBean.setAddress(address);
                locationBean.setLatitude(String.valueOf(lat));
                locationBean.setLongitude(String.valueOf(lng));
                adapter.notifyItemChanged(i);
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LocateSelectionActivity.SELECT_ADDR && resultCode == RESULT_OK) {
            //地址选择
            lat = data.getDoubleExtra("lat", 0.0);
            lng = data.getDoubleExtra("lng", 0.0);
            address = data.getStringExtra("address");
            notifyLocationItem();
        }

    }
    /**
     * 检测单位名称或者社会信用代码是否唯一
     * @param keyValueBean
     */
    @Override
    public void checkUnitUnique(TextKeyValueBean keyValueBean) {
        super.checkUnitUnique(keyValueBean);
        String content = keyValueBean.getValue();
        switch (keyValueBean.getKey()) {
            case BaseInspectContract.INSPECTION_UNIT_NAME:
                //更改后的名称如果和原名称一致 不需要检测是否唯一
                if (bean != null) {
                    if (!content.equals(bean.getName())) {
                        mPresenter.checkUnitUnique(getBaseBuilder().add("keyword", content).build(),
                                BaseInspectContract.INSPECTION_UNIT_NAME);
                    }else {
                        isUnitNameUnque = true;
                    }
                }else {
                    //检查单位名称是否是唯一
                    mPresenter.checkUnitUnique(getBaseBuilder().add("keyword", content).build(),
                            BaseInspectContract.INSPECTION_UNIT_NAME);
                }


                break;
            case BaseInspectContract.INSPECTION_UNIT_UCC:
                if (bean != null) {
                    if (!content.equals(bean.getUnifiedCreditCode())) {
                        //检查社会信用代码是否是唯一
                        mPresenter.checkUnitUnique(getBaseBuilder().add("unifiedCreditCode", content).build(),
                                BaseInspectContract.INSPECTION_UNIT_UCC);
                    }else {
                        isUnitUCCUnique = true;
                    }
                }else {
                    //检查社会信用代码是否是唯一
                    mPresenter.checkUnitUnique(getBaseBuilder().add("unifiedCreditCode", content).build(),
                            BaseInspectContract.INSPECTION_UNIT_UCC);
                }

            default:

                break;
        }
    }



    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_save_commit, null);
        TextView mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        mCommitBusinessTv.setText(getCommitTextValue());
        mCommitBusinessTv.setOnClickListener(this);
        return view;
    }

    protected abstract String getCommitTextValue();

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.save_draft_tv:
                //保存草稿
                break;
            case R.id.commit_form_tv:
                if (!isUnitNameUnque) {
                    ToastUtils.toast(mContext,"单位名称已存在,不可重复提交");
                    return;
                }
                if (!isUnitUCCUnique) {
                    ToastUtils.toast(mContext,"社会信用代码已存在,不可重复提交");
                    return;
                }
                MultipartBody.Builder builder = getBuilderOfAdapterData();
                if (builder == null) {
                    return;
                }

                commit(builder);

                break;
            default:
                break;
        }
    }

    protected abstract void commit(MultipartBody.Builder builder);


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag,o);
        switch (tag) {
            case BaseInspectContract.INSPECTION_UNIT_NAME:
                BaseResult result = (BaseResult) o;
                if ("成功".equals(result.message)) {
                    isUnitNameUnque = true;
                } else {
                    ToastUtils.toast(mContext,"单位名称已存在");
                    isUnitNameUnque = false;
                }
                break;
            case BaseInspectContract.INSPECTION_UNIT_UCC:
                BaseResult baseResult = (BaseResult) o;
                if ("成功".equals(baseResult.message)) {
                    isUnitUCCUnique = true;
                } else {
                    ToastUtils.toast(mContext,"社会信用代码已存在");
                    isUnitUCCUnique = false;
                }
                break;
            default:
                break;
        }
    }

}
