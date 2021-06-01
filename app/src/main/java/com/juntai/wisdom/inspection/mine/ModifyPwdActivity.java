package com.juntai.wisdom.inspection.mine;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ActivityManagerTool;
import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.disabled.basecomponent.utils.PubUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.entrance.LoginActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.utils.StringTools;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述  找回密码/修改手机号
 * @date 2020/3/10 9:33
 */
public class ModifyPwdActivity extends BaseAppActivity<MyCenterPresent> implements MyCenterContract.ICenterView,
        View.OnClickListener {


    /**
     * 请输入原密码
     */
    private EditText mSetPwdOlderEt;
    /**
     * 请设置6-20位(字母数字下划线)密码
     */
    private EditText mRegistSetPwdEt;
    /**
     * 请再次确认密码
     */
    private EditText mRegistCheckPwdEt;
    /**
     * 提交
     */
    private TextView mCommitRequestTv;

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.modify_pwd;
    }

    @Override
    public void initView() {

        mSetPwdOlderEt = (EditText) findViewById(R.id.set_pwd_older_et);
        mRegistSetPwdEt = (EditText) findViewById(R.id.regist_set_pwd_et);
        mRegistCheckPwdEt = (EditText) findViewById(R.id.regist_check_pwd_et);
        mCommitRequestTv = (TextView) findViewById(R.id.commit_request_tv);
        mCommitRequestTv.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitleName("修改密码");
    }

    @Override
    public void onSuccess(String tag, Object o) {
        //修改成功后需要重新登录
        ToastUtils.toast(mContext,"修改成功");
        //将所有的activity销毁
        ActivityManagerTool.getInstance().finishApp();
        UserInfoManager.clearUserData();
        startActivity(new Intent(mContext, LoginActivity.class));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.commit_request_tv:
                //校验密码
                String pwdOlder = getTextViewValue(mSetPwdOlderEt);
                if (TextUtils.isEmpty(pwdOlder)) {
                    ToastUtils.toast(mContext, "请输入原密码");
                    return;
                }
                String pwd = getTextViewValue(mRegistSetPwdEt);
                if (!StringTools.isStringValueOk(pwd)) {
                    ToastUtils.toast(mContext, "请设置新密码");
                    return;
                } else {
                    if (!PubUtil.checkPwdMark(pwd)) {
                        ToastUtils.toast(mContext, "密码仅支持最少6位(字母数字组合)");
                        return;
                    } else {
                        //查看确认密码
                        if (!pwd.equals(getTextViewValue(mRegistCheckPwdEt))) {
                            ToastUtils.toast(mContext, "两次输入的密码不一致");
                            return;
                        }
                    }
                }
                //修改密码
                mPresenter.modifyPwd(getBaseBuilder().add("oldPassword", MD5.md5(String.format("%s#%s", UserInfoManager.getPhoneNumber(),
                        getTextViewValue(mSetPwdOlderEt))))
                        .add("newPassword", MD5.md5(String.format("%s#%s", UserInfoManager.getPhoneNumber(),
                                getTextViewValue(mRegistSetPwdEt)))).build(), "");
                break;
        }
    }

}
