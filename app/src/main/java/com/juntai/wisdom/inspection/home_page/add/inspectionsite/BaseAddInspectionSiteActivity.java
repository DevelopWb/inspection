package com.juntai.wisdom.inspection.home_page.add.inspectionsite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  巡检点
 * @date 2021/5/7 11:30
 */
public abstract class BaseAddInspectionSiteActivity extends BaseCommitFootViewActivity {

    private boolean isSiteNameUnque = false;//单位名称是否唯一
    public InspectionSiteBean.DataBean bean;
    private InspectionSiteBean.DataBean savedSiteBean;
    public int inspectionSiteId;

    @Override
    public void initData() {
        unSavedLogic();
        savedSiteBean = Hawk.get(getHawkKey());
        if (savedSiteBean != null) {
            setAlertDialogHeightWidth(DialogUtil.getDialog(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            initStatus(savedSiteBean);

                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startLocation();
                        }
                    }).show(), -1, 0);
        }

    }

    private void initStatus(InspectionSiteBean.DataBean bean) {
        if (bean != null && !TextUtils.isEmpty(bean.getName())) {
            isSiteNameUnque = true;
        }
        adapter.setNewData(mPresenter.getInspectionSiteInfoData(bean, false));
    }

    /**
     * 获取key
     *
     * @return
     */
    protected abstract String getHawkKey();

    /**
     * 未保存草稿的逻辑
     */
    private void unSavedLogic() {
        if (getIntent() != null) {
            bean = getIntent().getParcelableExtra(PARCELABLE_KEY);
            if (bean != null) {
                inspectionSiteId = bean.getId();
            }
            initStatus(bean);
        }
    }

    @Override
    public boolean requestLocation() {
        if (savedSiteBean != null && !TextUtils.isEmpty(savedSiteBean.getGpsAddress()) || bean != null && !TextUtils.isEmpty(bean.getGpsAddress())) {
            return false;
        }
        return true;
    }


    /**
     * 检测单位名称或者社会信用代码是否唯一
     *
     * @param keyValueBean
     */
    @Override
    public void checkUnitUnique(TextKeyValueBean keyValueBean) {
        super.checkUnitUnique(keyValueBean);
        String content = keyValueBean.getValue();
        switch (keyValueBean.getKey()) {
            case BaseInspectContract.INSPECTION_SITE:
                //更改后的名称如果和原名称一致 不需要检测是否唯一
                if (bean != null) {
                    if (!content.equals(bean.getName())) {
                        mPresenter.checkInspectionSiteNameUnique(getBaseBuilder().add("keyword", content).build(),
                                BaseInspectContract.INSPECTION_SITE);
                    } else {
                        isSiteNameUnque = true;
                    }
                } else {
                    //检查巡检点名称是否是唯一
                    mPresenter.checkInspectionSiteNameUnique(getBaseBuilder().add("keyword", content).build(),
                            BaseInspectContract.INSPECTION_SITE);
                }


                break;

            default:

                break;
        }
    }

    @Override
    protected void saveDraft() {
        if (getBaseAdapterData(true) != null) {
            Hawk.put(getHawkKey(), getBaseAdapterData(true).getInspectionSiteBean());
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
    }

    @Override
    protected void commitRequest(MultipartBody.Builder builder) {
        if (!isSiteNameUnque) {
            ToastUtils.toast(mContext, "巡检点名称已存在,不可重复添加");
            return;
        }
        commit(builder);
    }

    protected abstract void commit(MultipartBody.Builder builder);


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case BaseInspectContract.INSPECTION_SITE:
                BaseResult result = (BaseResult) o;
                if ("成功".equals(result.message)) {
                    isSiteNameUnque = true;
                } else {
                    ToastUtils.toast(mContext, "巡检点名称已存在");
                    isSiteNameUnque = false;
                }
                break;
            case AppHttpPath.MANUAL_ADD_INSP_SITE:
                ToastUtils.toast(mContext, "添加成功");
                if (Hawk.contains(getHawkKey())) {
                    Hawk.delete(getHawkKey());
                }
                finish();
                break;
            case AppHttpPath.SEARCH_ADD_INSP_SITE:
                ToastUtils.toast(mContext, "添加成功");
                finish();
                break;
            case AppHttpPath.APPLY_EDIT_INSPECTION_SITE_INFO:
                ToastUtils.toast(mContext, "提交成功");
                if (Hawk.contains(getHawkKey())) {
                    Hawk.delete(getHawkKey());
                }
                finish();
                break;
            default:
                break;
        }
    }

}
