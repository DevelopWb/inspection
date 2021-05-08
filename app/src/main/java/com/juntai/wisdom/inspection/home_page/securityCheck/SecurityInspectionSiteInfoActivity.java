package com.juntai.wisdom.inspection.home_page.securityCheck;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.bean.ActionBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.TextKeyValueAdapter;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  治安巡检信息
 * @date 2021/4/20 16:12
 */
public class SecurityInspectionSiteInfoActivity extends BaseInspectionInfoActivity {

    @Override
    protected String getTitleName() {
        return "治安巡检点详情";
    }

    @Override
    protected String getStartWorkName() {
        return START_INSPECT;
    }

    @Override
    public void initData() {

        actionsAdapter.setNewData(getActionAdapterData());

    }

    private List<ActionBean> getActionAdapterData() {
        List<ActionBean>  arrays = new ArrayList<>();
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter,BaseInspectContract.INSPECTION_SECURITY_RECORD,
                R.mipmap.app_icon));
        return arrays;
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
