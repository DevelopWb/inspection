package com.juntai.wisdom.inspection.securityCheck;

import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.wisdom.inspection.base.BaseAppPresent;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 16:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 16:44
 */
public class SecurityPresent extends BaseAppPresent<IModel,SecurityContract.ISecurityView> implements SecurityContract.ISecurityPresent {
    @Override
    protected IModel createModel() {
        return null;
    }
}
