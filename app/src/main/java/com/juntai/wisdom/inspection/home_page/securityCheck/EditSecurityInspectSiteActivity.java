package com.juntai.wisdom.inspection.home_page.securityCheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  编辑治安巡检点
 * @date 2021/4/22 16:58
 */
public class EditSecurityInspectSiteActivity extends BaseInspectionActivity {

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getEditSecurityInspectSiteInfo());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "编辑治安巡检点";
    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_save_commit, null);
        TextView mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        mCommitBusinessTv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.save_draft_tv:
                //保存草稿
                break;
            case R.id.commit_form_tv:
                startActivity(new Intent(mContext,EditSecurityInspectSiteActivity.class));
                break;
            default:
                break;
        }
    }
}
