package com.juntai.wisdom.inspection.base;

import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/6/3 8:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/3 8:38
 */
public abstract class BaseAppPresent<M extends IModel, V extends IView> extends BasePresenter<M,V>{

    /**
     * 获取builder
     * @return
     */
    public MultipartBody.Builder getPublishMultipartBody() {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("account", UserInfoManager.getUserAccount())
                .addFormDataPart("userId", String.valueOf(UserInfoManager.getUserId()))
                .addFormDataPart("departmentId", String.valueOf(UserInfoManager.getDepartmentId()))
                .addFormDataPart("departmentName",UserInfoManager.getDepartmentName())
                .addFormDataPart("token", UserInfoManager.getUserToken());
    }


}
