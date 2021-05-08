package com.juntai.wisdom.inspection.home_page.add;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.unit.SearchedUnitsBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.securityCheck.EditSecurityInspectSiteActivity;

/**
 * @aouther tobato
 * @description 描述  添加单位
 * @date 2021/5/7 11:30
 */
public class AddUnitActivity extends BaseInspectionActivity {

    private boolean isUnitNameUnque = false;//单位名称是否唯一
    private boolean isUnitUCCUnique = false;//社会信用代码是否唯一
    private SearchedUnitsBean.DataBean.DatasBean bean;

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
    protected String getTitleName() {
        return "添加单位详情";
    }


    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_save_commit, null);
        TextView mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        mCommitBusinessTv.setOnClickListener(this);
        return view;
    }
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

                break;
            default:
                break;
        }
    }

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
