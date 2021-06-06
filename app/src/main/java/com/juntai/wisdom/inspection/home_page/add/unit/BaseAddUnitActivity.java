package com.juntai.wisdom.inspection.home_page.add.unit;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  单位
 * @date 2021/5/7 11:30
 */
public abstract class BaseAddUnitActivity extends BaseCommitFootViewActivity {

    private boolean isUnitNameUnque = false;//单位名称是否唯一
    private boolean isUnitUCCUnique = false;//社会信用代码是否唯一
    public UnitDetailBean.DataBean bean;
    private UnitDetailBean.DataBean savedUnitBean;
    public int   unitId ;

    @Override
    public void initData() {
        unSavedLogic();
        savedUnitBean = Hawk.get(getHawkKey());
        if (savedUnitBean != null) {
            new AlertDialog.Builder(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            initStatus(savedUnitBean);
                            adapter.setNewData(mPresenter.getUnitInfoData(savedUnitBean,false));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startLocation();
                }
            }).show();

        }

    }

    private void initStatus(UnitDetailBean.DataBean bean) {
        if (!TextUtils.isEmpty(bean.getName())) {
            isUnitNameUnque = true;
        }
        if (!TextUtils.isEmpty(bean.getUnifiedCreditCode())) {
            isUnitUCCUnique = true;
        }
    }

    /**
     * 获取key
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
                unitId = bean.getId();
                initStatus(bean);
            }
            adapter.setNewData(mPresenter.getUnitInfoData(bean,false));
        }
    }

    @Override
    public boolean requestLocation() {
        if (savedUnitBean != null && !TextUtils.isEmpty(savedUnitBean.getGpsAddress())) {
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
            case BaseInspectContract.INSPECTION_UNIT_NAME:
                //更改后的名称如果和原名称一致 不需要检测是否唯一
                if (bean != null) {
                    if (!content.equals(bean.getName())) {
                        mPresenter.checkUnitUnique(getBaseBuilder().add("keyword", content).build(),
                                BaseInspectContract.INSPECTION_UNIT_NAME);
                    } else {
                        isUnitNameUnque = true;
                    }
                } else {
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
                    } else {
                        isUnitUCCUnique = true;
                    }
                } else {
                    //检查社会信用代码是否是唯一
                    mPresenter.checkUnitUnique(getBaseBuilder().add("unifiedCreditCode", content).build(),
                            BaseInspectContract.INSPECTION_UNIT_UCC);
                }
                break;
            default:

                break;
        }
    }

    @Override
    protected void saveDraft() {
        //保存草稿
        if (getBaseAdapterData(true) != null) {
            Hawk.put(getHawkKey(), getBaseAdapterData(true).getUnitDataBean());
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
    }


    @Override
    protected void commitRequest(MultipartBody.Builder builder) {
        if (!isUnitNameUnque) {
            ToastUtils.toast(mContext, "单位名称已存在,不可重复添加");
            return;
        }
        if (!isUnitUCCUnique) {
            ToastUtils.toast(mContext, "社会信用代码已存在,不可重复添加");
            return;
        }
        commit(builder);

    }


    protected abstract void commit(MultipartBody.Builder builder);


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case BaseInspectContract.INSPECTION_UNIT_NAME:
                BaseResult result = (BaseResult) o;
                if ("成功".equals(result.message)) {
                    isUnitNameUnque = true;
                } else {
                    ToastUtils.toast(mContext, "单位名称已存在");
                    isUnitNameUnque = false;
                }
                break;
            case BaseInspectContract.INSPECTION_UNIT_UCC:
                BaseResult baseResult = (BaseResult) o;
                if ("成功".equals(baseResult.message)) {
                    isUnitUCCUnique = true;
                } else {
                    ToastUtils.toast(mContext, "社会信用代码已存在");
                    isUnitUCCUnique = false;
                }
                break;
            case AppHttpPath.MANUAL_ADD_UNIT:
                ToastUtils.toast(mContext,"添加成功");
                if (Hawk.contains(getHawkKey())) {
                    Hawk.delete(getHawkKey());
                }
                finish();
                break;
            case AppHttpPath.APPLY_EDIT_UNIT_INFO:
                ToastUtils.toast(mContext,"提交成功");
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
