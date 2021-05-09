package com.juntai.wisdom.inspection.home_page.add.unit;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.AppHttpPath;

import okhttp3.MultipartBody;

/**
 * @aouther tobato
 * @description 描述  手动添加单位  manual
 * @date 2021/5/9 11:44
 */

public class ManualAddUnitActivity extends BaseUnitActivity {

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
        return "添加单位";
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        if (AppHttpPath.MANUAL_ADD_UNIT.equals(tag)) {
            ToastUtils.toast(mContext,"添加成功");
            finish();
        }
    }
}
