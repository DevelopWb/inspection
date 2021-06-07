package com.juntai.wisdom.inspection.base.update;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.allenliu.versionchecklib.core.http.HttpParams;
import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.LogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.BaseRequestLocationActivity;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.MyApp;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.UpdateBean;
import com.juntai.wisdom.inspection.utils.AppUtils;

/**
 * Created by Ma
 * on 2019/12/24
 */
public abstract class UpdateActivity<P extends BasePresenter> extends BaseRequestLocationActivity<P> {

    boolean isForceUpdate = false;
    String version;

    /**
     * @param isWarn 是否提醒
     */
    public void update(boolean isWarn) {
        AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestMethod(HttpRequestMethod.POST)
                .setRequestUrl(AppHttpPath.APP_UPDATE)
                .setRequestParams(getHttpParams())
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(DownloadBuilder downloadBuilder, String result) {
                        //拿到服务器返回的数据，解析，拿到downloadUrl和一些其他的UI数据
                        LogUtil.d("更新" + result);

                        UpdateBean upgradeBean = GsonTools.changeGsonToBean(result, UpdateBean.class);
                        if (upgradeBean == null||upgradeBean.getData()==null) {
                            if (isWarn) {
                                ToastUtils.toast(mContext, "已是最新版本");
                            }
                            return null;
                        }
                        version = upgradeBean.getData().getVersionsName();
                        isForceUpdate = upgradeBean.getData().isConstraintUpdate();
                        if (AppUtils.getVersionCode(mContext) < upgradeBean.getData().getVersionsCode()) {
                            return UIData.create()
                                    .setTitle(upgradeBean.getData().getFileName())
                                    .setContent(upgradeBean.getData().getUpdateContent())
                                    .setDownloadUrl(AppHttpPath.BASE_IMAGE+upgradeBean.getData().getDownloadLink());
                        } else {
                            if (isWarn) {
                                ToastUtils.toast(mContext, "已是最新版本");
                            }
                            //如果是最新版本直接return null
                            return null;
                        }
                    }

                    @Override
                    public void onRequestVersionFailure(String message) {
                        LogUtil.d("更新" + message);
                    }
                })
                .setDownloadAPKPath(FileCacheUtils.getAppPath())//自定义下载路径
                .setApkName(AppUtils.getAppName())
                .setCustomVersionDialogListener((context, versionBundle) -> {
                    UpdateDialog updateDialog = new UpdateDialog(context, R.style.BaseDialog, R.layout.update_dialog);
                    //versionBundle 就是UIData，之前开发者传入的，在这里可以拿出UI数据并展示
                    TextView textView = updateDialog.findViewById(R.id.update_content);
                    textView.setText(versionBundle.getContent());
                    //强制更新，隐藏取消按钮
                    if (isForceUpdate) {
                        updateDialog.findViewById(R.id.versionchecklib_version_dialog_cancel).setVisibility(View.GONE);
                    }
                    return updateDialog;
                })
                .setForceUpdateListener(() -> {
                    //强制更新
                    if (isForceUpdate) {
                        MyApp.app.clearActivitys();
                    }
                    cancle();
                })
                .executeMission(mContext.getApplicationContext());
    }

    public static void cancle() {
        AllenVersionChecker.getInstance().cancelAllMission();
    }

    /**
     * 获取参数
     *
     * @return
     */
    private HttpParams getHttpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("typeId", MyApp.CHECK_UPDATE_TYPE);
        return httpParams;
    }
}
