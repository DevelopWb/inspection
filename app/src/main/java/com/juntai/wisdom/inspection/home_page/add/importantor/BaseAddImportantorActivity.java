package com.juntai.wisdom.inspection.home_page.add.importantor;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  重点人员
 * @date 2021/5/7 11:30
 */
public abstract class BaseAddImportantorActivity extends BaseCommitFootViewActivity {

    private boolean isIdUnque = false;//身份证号是否唯一
    public ImportantorBean.DataBean bean;
    private ImportantorBean.DataBean savedImportantorBean;

    @Override
    public void initData() {
        savedImportantorBean = Hawk.get(HawkProperty.ADD_IMPORTANTOR_KEY);
        if (savedImportantorBean != null) {
            adapter.setNewData(mPresenter.getImportantorData(null));
            new AlertDialog.Builder(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!TextUtils.isEmpty(savedImportantorBean.getName())) {
                                isIdUnque = true;
                            }
                            adapter.setNewData(mPresenter.getImportantorData(savedImportantorBean));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startLocation();
                    Hawk.delete(HawkProperty.ADD_IMPORTANTOR_KEY);
                    unSavedLogic();
                }
            }).show();

        } else {
            unSavedLogic();

        }

    }

    /**
     * 未保存草稿的逻辑
     */
    private void unSavedLogic() {
        if (getIntent() != null) {
            bean = getIntent().getParcelableExtra(PARCELABLE_KEY);

            if (bean != null) {
                bean.setPersonnelPhoto(null);
                isIdUnque = true;
            }
            adapter.setNewData(mPresenter.getImportantorData(bean));
        }
    }

    @Override
    public boolean requestLocation() {
        if (savedImportantorBean != null && !TextUtils.isEmpty(savedImportantorBean.getGpsAddress())) {
            return false;
        }
        return true;
    }


    /**
     * 检测身份证号或者社会信用代码是否唯一
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
                        isIdUnque = true;
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

    }

    @Override
    protected void commitRequest(MultipartBody.Builder builder) {
        if (!isIdUnque) {
            ToastUtils.toast(mContext, "重点人员已存在,不可重复添加");
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
                    isIdUnque = true;
                } else {
                    ToastUtils.toast(mContext, "巡检点名称已存在");
                    isIdUnque = false;
                }
                break;
            default:
                break;
        }
    }

}
