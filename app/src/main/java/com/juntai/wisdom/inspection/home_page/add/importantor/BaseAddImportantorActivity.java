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
import com.juntai.wisdom.inspection.AppHttpPath;
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
    public int importantorId;

    @Override
    public void initData() {
        unSavedLogic();
        savedImportantorBean = Hawk.get(getHawkKey());
        if (savedImportantorBean != null) {
            new AlertDialog.Builder(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!TextUtils.isEmpty(savedImportantorBean.getName())) {
                                isIdUnque = true;
                            }
//                            if (!TextUtils.isEmpty(savedImportantorBean.getKeyStatusName())) {
//                                importantorStatusName = savedImportantorBean.getKeyStatusName();
//                                importantorStatusId = savedImportantorBean.getKeyStatus();
//                            }
                            adapter.setNewData(mPresenter.getImportantorData(savedImportantorBean));
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startLocation();
                }
            }).show();

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
                isIdUnque = true;
                importantorId =bean.getId();
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
            case BaseInspectContract.INSPECTION_ID_CARD:
                //更改后如果和原名称一致 不需要检测是否唯一
                if (bean != null) {
                    if (!content.equals(bean.getIdNumber())) {
                        mPresenter.checkImportantorIDUnique(getBaseBuilder().add("keyword", content).build(),
                                BaseInspectContract.INSPECTION_ID_CARD);
                    } else {
                        isIdUnque = true;
                    }
                } else {
                    //检查身份证号是否是唯一
                    mPresenter.checkImportantorIDUnique(getBaseBuilder().add("keyword", content).build(),
                            BaseInspectContract.INSPECTION_ID_CARD);
                }


                break;

            default:

                break;
        }
    }

    @Override
    protected void saveDraft() {
        if (getBaseAdapterData(true) != null) {
            Hawk.put(getHawkKey(), getBaseAdapterData(true).getImportantorBean());
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
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
            case BaseInspectContract.INSPECTION_ID_CARD:
                BaseResult result = (BaseResult) o;
                if ("成功".equals(result.message)) {
                    isIdUnque = true;
                } else {
                    ToastUtils.toast(mContext, "该身份证号码已存在");
                    isIdUnque = false;
                }
                break;
            case AppHttpPath.SEARCH_IMPORTANTOR_TO_ADD:
                ToastUtils.toast(mContext,"添加成功");
                if (Hawk.contains(getHawkKey())) {
                    Hawk.delete(getHawkKey());
                }
                finish();
                break;
            case AppHttpPath.EDIT_IMPORTANTOR:
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
