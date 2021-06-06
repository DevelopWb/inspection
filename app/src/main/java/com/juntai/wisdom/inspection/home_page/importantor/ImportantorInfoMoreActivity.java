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

import okhttp3.MultipartBody;

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
        return "重点人员详情-更多";
    }


    @Override
    public void initView() {
        idDetail = true;
        super.initView();
        if (getIntent() != null) {
            dataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
        }
        mCommitTv.setText("申请修改");
    }

    @Override
    protected void commitLogic(MultipartBody.Builder builder) {
        //  重点人员更多信息  申请修改
        startActivity(new Intent(mContext, EditImportantorActivity.class).putExtra(PARCELABLE_KEY,dataBean));
    }
}
