package com.juntai.wisdom.inspection.home_page.add.unit;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  手动添加单位  manual
 * @date 2021/5/9 11:44
 */

public class ManualAddUnitActivity extends BaseAddUnitActivity {

    @Override
    protected String getCommitTextValue() {
        return "完成";
    }

    @Override
    protected void commit(MultipartBody.Builder builder) {
        mPresenter.manualAddUnit(builder.build(), AppHttpPath.MANUAL_ADD_UNIT);
    }

    @Override
    protected String getTitleName() {
        return ADD_UNIT;
    }

}
