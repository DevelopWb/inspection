package com.juntai.wisdom.inspection.home_page.importantor;

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
public class ImportantorInfoActivity extends BaseInspectionInfoActivity {


    @Override
    protected Object getBaseBean() {
        return null;
    }

    @Override
    protected List<ActionBean> getActionAdapterData() {
        List<ActionBean>  arrays = new ArrayList<>();
        arrays.add(new ActionBean(R.drawable.sp_filled_gray_lighter, BaseInspectContract.INSPECTION_VISIT_RECORD,
                R.mipmap.app_icon));
        return arrays;
    }

    @Override
    protected String getTitleName() {
        return "重点人员详情";
    }

    @Override
    protected String getStartWorkName() {
        return START_VISIT;
    }

    @Override
    protected void seeMoreInfo() {

    }

    @Override
    public void initData() {


    }
    @Override
    public void onSuccess(String tag, Object o) {

    }
}
