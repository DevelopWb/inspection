package com.juntai.wisdom.inspection.home_page.firecheck;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.firecheck.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  单位详情 更多
 * @date 2021/5/18 14:39
 */
public class UnitInfoMoreActivity extends BaseInspectionActivity implements BaseInspectContract.IInspectView {
    private UnitDetailBean.DataBean dataBean;
    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getUnitInfoData(dataBean,true));
    }

    @Override
    protected String getTitleName() {
        return "单位详情-更多";
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
    protected void commitLogic() {
        //申请修改
        startActivity(new Intent(mContext,EditUnitInfoActivity.class).putExtra(PARCELABLE_KEY,dataBean));
    }

}
