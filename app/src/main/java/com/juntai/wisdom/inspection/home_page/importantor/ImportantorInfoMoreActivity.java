package com.juntai.wisdom.inspection.home_page.importantor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.securityInspect.EditSecurityInspectSiteActivity;

/**
 * @aouther tobato
 * @description 描述   重点人员 更多消息
 * @date 2021/5/16 15:46
 */
public class ImportantorInfoMoreActivity extends BaseInspectionActivity implements BaseInspectContract.IInspectView {
    private ImportantorBean.DataBean dataBean;
    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getImportantorData(dataBean));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return "重点人员详情";
    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_commit, null);
        TextView mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        mCommitBusinessTv.setText("申请修改");
        mCommitBusinessTv.setOnClickListener(this);
        return view;
    }

    @Override
    public void initView() {
        idDetail = true;
        super.initView();
        if (getIntent() != null) {
            dataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.commit_form_tv:
//                startActivity(new Intent(mContext, EditSecurityInspectSiteActivity.class));
                break;
            default:
                break;
        }
    }
}
