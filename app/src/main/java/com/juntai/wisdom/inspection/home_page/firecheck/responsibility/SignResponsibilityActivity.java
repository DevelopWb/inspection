package com.juntai.wisdom.inspection.home_page.firecheck.responsibility;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.bean.firecheck.ResponsibilityBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  签署责任书
 * @date 2021/5/29 11:56
 */
public class SignResponsibilityActivity extends BaseInspectionActivity {

    private int unitId;
    private String titleName;
    private String content;

    @Override
    public void initData() {
        mCommitTv.setText("完成");
        titleName = getIntent().getStringExtra(BASE_STRING);
        setTitleName(titleName);
        content = getIntent().getStringExtra(BASE_STRING2);
        unitId = getIntent().getIntExtra(BASE_ID,0);
        ResponsibilityBean.DataBean dataBean = new ResponsibilityBean.DataBean();
        dataBean.setName(titleName);
        dataBean.setContent(content);
        adapter.setNewData(mPresenter.getResponsibilityData(dataBean,false));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getTitleName() {
        return titleName;
    }


    @Override
    protected void commitLogic(MultipartBody.Builder builder) {

        mPresenter.signResponsibility(builder.addFormDataPart("unitId",String.valueOf(unitId))
                .addFormDataPart("typeId","消防安全责任书".equals(titleName)?"1":"2")
        .addFormDataPart("content",content).build(),"");
    }

    @Override
    public void onSuccess(String tag, Object o) {
        ToastUtils.toast(mContext,"已提交");
       finish();
    }
}
