package com.juntai.wisdom.inspection.home_page.firecheck.check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述 添加处罚信息
 * @date 2021/5/28 9:58
 */
public class AddPunishActivity extends BaseInspectionActivity implements BaseInspectContract.IInspectView {

    private int recordId;
    private int unitId;

    @Override
    public void initData() {
        mCommitTv.setText("提交");
        adapter.setNewData(mPresenter.getPunishInfo());
        recordId = getIntent().getIntExtra(BASE_ID2,0);
        unitId = getIntent().getIntExtra(BASE_ID,0);
    }


    @Override
    protected String getTitleName() {
        return "添加处罚信息";
    }

    @Override
    protected void commitLogic(MultipartBody.Builder builder) {
        mPresenter.addPunishInfo(builder.addFormDataPart("unitId",String.valueOf(unitId)).addFormDataPart("recordId",
                String.valueOf(recordId)).build(),"");
    }

    @Override
    public void onSuccess(String tag, Object o) {
        ToastUtils.toast(mContext,"提交成功");
        finish();
    }
}
