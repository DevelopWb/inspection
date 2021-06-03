package com.juntai.wisdom.inspection.mine;


import com.juntai.disabled.basecomponent.base.BaseObserver;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppNetModule;
import com.juntai.wisdom.inspection.base.BaseAppPresent;
import com.juntai.wisdom.inspection.bean.MyMenuBean;
import com.juntai.wisdom.inspection.bean.MyMsgBean;
import com.juntai.wisdom.inspection.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyCenterPresent extends BaseAppPresent<IModel, MyCenterContract.ICenterView> implements MyCenterContract.ICenterPresent {
    List<MyMenuBean> menuBeans = new ArrayList<>();
    private IView iView;

    public void setCallBack(IView iView) {
        this.iView = iView;
    }
    @Override
    protected IModel createModel() {
        return null;
    }




    @Override
    public void initList() {
        menuBeans.clear();
        menuBeans.add(new MyMenuBean(MyCenterContract.MY_WORK_RECORD, R.mipmap.my_work_record));
        menuBeans.add(new MyMenuBean(MyCenterContract.MY_MSG, R.mipmap.my_message));
        menuBeans.add(new MyMenuBean(MyCenterContract.MY_MODIFY_PWD, R.mipmap.my_modify_pwd));
        menuBeans.add(new MyMenuBean(MyCenterContract.MY_CLEAR_CACHE, R.mipmap.my_clear));
        menuBeans.add(new MyMenuBean(MyCenterContract.MY_UPDATE, R.mipmap.my_update));
        menuBeans.add(new MyMenuBean(MyCenterContract.MY_ABOUT_US, R.mipmap.my_about));

    }

    @Override
    public List<MyMenuBean> getMenuBeans(){
        return menuBeans;
    }



    public void getUserInfo(RequestBody requestBody,String tag){

        AppNetModule.createrRetrofit()
                .getUserInfo(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<UserBean>(getView()) {
                    @Override
                    public void onSuccess(UserBean o) {
                        if (getView() != null){
                            getView().onSuccess(tag,o);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (getView() != null){
                            getView().onError(tag,msg);
                        }
                    }
                });

    }

    /**
     * 退出登录
     * @param requestBody
     * @param tag
     */
    public void loginOut(RequestBody requestBody,String tag){

        AppNetModule.createrRetrofit()
                .loginOut(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null){
                            getView().onSuccess(tag,o);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (getView() != null){
                            getView().onError(tag,msg);
                        }
                    }
                });

    }
    /**
     * 修改密码
     * @param requestBody
     * @param tag
     */
    public void modifyPwd(RequestBody requestBody,String tag){

        AppNetModule.createrRetrofit()
                .modifyPwd(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null){
                            getView().onSuccess(tag,o);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (getView() != null){
                            getView().onError(tag,msg);
                        }
                    }
                });

    }
    /**
     * @param requestBody
     * @param tag
     */
    public void updateHeadPic(RequestBody requestBody,String tag){

        AppNetModule.createrRetrofit()
                .updateHeadPic(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null){
                            getView().onSuccess(tag,o);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (getView() != null){
                            getView().onError(tag,msg);
                        }
                    }
                });

    }
    /**
     * @param requestBody
     * @param tag
     */
    public void getUnreadMsg(RequestBody requestBody,String tag){

        AppNetModule.createrRetrofit()
                .getUnreadMsg(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null){
                            getView().onSuccess(tag,o);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (getView() != null){
                            getView().onError(tag,msg);
                        }
                    }
                });

    }
    /**
     * @param requestBody
     * @param tag
     */
    public void getMyMsgs(RequestBody requestBody,String tag){

        AppNetModule.createrRetrofit()
                .getMyMsgs(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<MyMsgBean>(getView()) {
                    @Override
                    public void onSuccess(MyMsgBean o) {
                        if (getView() != null){
                            getView().onSuccess(tag,o);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (getView() != null){
                            getView().onError(tag,msg);
                        }
                    }
                });

    }
    /**
     * 我的工作记录
     * @param requestBody
     * @param tag
     */
    public void myWorkRecords(RequestBody requestBody,String tag){

        AppNetModule.createrRetrofit()
                .myWorkRecords(requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new BaseObserver<BaseResult>(getView()) {
                    @Override
                    public void onSuccess(BaseResult o) {
                        if (getView() != null){
                            getView().onSuccess(tag,o);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (getView() != null){
                            getView().onError(tag,msg);
                        }
                    }
                });

    }

}
