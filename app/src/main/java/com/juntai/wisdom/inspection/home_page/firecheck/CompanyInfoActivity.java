package com.juntai.wisdom.inspection.home_page.firecheck;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.ActionBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  治安巡检信息
 * @date 2021/4/20 16:12
 */
public class CompanyInfoActivity extends BaseInspectionInfoActivity {

    @Override
    protected String getTitleName() {
        return "单位详情";
    }

    @Override
    protected String getStartWorkName() {
        return "开始检查";
    }

    @Override
    public void initData() {

        actionsAdapter.setNewData(getActionAdapterData());

    }

    private List<ActionBean> getActionAdapterData() {
        List<ActionBean>  arrays = new ArrayList<>();
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter, BaseInspectContract.INSPECTION_CHECK_RECORD,
                R.mipmap.app_icon));
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter, BaseInspectContract.INSPECTION_RESPONSIBILITY,
                R.mipmap.app_icon));
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter, BaseInspectContract.INSPECTION_RECTIFY_NOTICE,
                R.mipmap.app_icon));
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter, BaseInspectContract.INSPECTION_WORKER,
                R.mipmap.app_icon));
        return arrays;
    }
    @Override
    public void onSuccess(String tag, Object o) {

    }
}
