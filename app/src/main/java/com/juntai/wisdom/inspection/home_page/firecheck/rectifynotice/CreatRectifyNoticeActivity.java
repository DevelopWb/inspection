package com.juntai.wisdom.inspection.home_page.firecheck.rectifynotice;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.juntai.wisdom.inspection.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  生成整改通知书
 * @date 2021/5/23 11:13
 */
public class CreatRectifyNoticeActivity extends BaseInspectionActivity {

    private FireCheckBean.DataBean firecheckbean;
    private int unitId;
    private String unitName;

    @Override
    protected String getTitleName() {
        return null;
    }


    @Override
    public void initData() {
        adapter.addHeaderView(getHeadView());
        if (getIntent() != null) {
            unitId = getIntent().getIntExtra(BASE_ID, 0);
            unitName = getIntent().getStringExtra(BASE_STRING);
            firecheckbean = Hawk.get(HawkProperty.ADD_FIRE_CHECK_RECORD_KEY + unitId);
            firecheckbean.setNoticeName(firecheckbean.getUnitName());
            firecheckbean.setNoticeContent(firecheckbean.getNoticeContent());
            adapter.setDetail(true);
            adapter.setNewData(mPresenter.getFireCheckNoticeData(firecheckbean));
        }
        mCommitTv.setText("完成");
    }

    private View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rectify_notice_head, null);
        TextView titleTv = view.findViewById(R.id.single_text_tv);
        titleTv.setText(UserInfoManager.getDepartmentName());
        return view;
    }


    @Override
    protected void commitLogic() {
        //完成  提交检查记录  成功后跳转到生成整改通知书界面
        //检查有问题
        BaseAdapterDataBean baseAdapterDataBean = getBaseAdapterData(false);
        if (baseAdapterDataBean == null) {
            return;
        }
        MultipartBody.Builder builder = getBaseAdapterData(false).getBuilder();
        if (!TextUtils.isEmpty(firecheckbean.getPhotoOne())) {
            builder.addFormDataPart("pictureOne", "pictureOne",
                    RequestBody.create(MediaType.parse("file"),
                            new File(firecheckbean.getPhotoOne())));
        }
        if (!TextUtils.isEmpty(firecheckbean.getPhotoTwo())) {
            builder.addFormDataPart("pictureTwo", "pictureTwo",
                    RequestBody.create(MediaType.parse("file"),
                            new File(firecheckbean.getPhotoTwo())));
        }
        if (!TextUtils.isEmpty(firecheckbean.getPhotoThree())) {
            builder.addFormDataPart("pictureThree", "pictureThree",
                    RequestBody.create(MediaType.parse("file"),
                            new File(firecheckbean.getPhotoThree())));
        }
        if (!TextUtils.isEmpty(firecheckbean.getPhotoFour())) {
            builder.addFormDataPart("pictureFour", "pictureFour",
                    RequestBody.create(MediaType.parse("file"),
                            new File(firecheckbean.getPhotoFour())));
        }
        if (!TextUtils.isEmpty(firecheckbean.getPhotoFive())) {
            builder.addFormDataPart("pictureFive", "pictureFive",
                    RequestBody.create(MediaType.parse("file"),
                            new File(firecheckbean.getPhotoFive())));
        }
        if (!TextUtils.isEmpty(firecheckbean.getPhotoSix())) {
            builder.addFormDataPart("pictureSix", "pictureSix",
                    RequestBody.create(MediaType.parse("file"),
                            new File(firecheckbean.getPhotoSix())));
        }
        RequestBody body =
                builder.addFormDataPart("unitId", String.valueOf(unitId)).addFormDataPart(
                        "inspectTime"
                        , firecheckbean.getInspectTime()).addFormDataPart("inspectName",
                        firecheckbean.getInspectName())
                        .addFormDataPart("unitName",unitName)
                        .addFormDataPart("unitLiable", firecheckbean.getUnitLiable()).addFormDataPart(
                        "liablePhone",
                        firecheckbean.getLiablePhone()).addFormDataPart("qualified", "2").build();

        mPresenter.addFireCheckRecord(body, "");
    }

    @Override
    public void onSuccess(String tag, Object o) {
        BaseResult baseResult = (BaseResult) o;
        String noticeDetailId = baseResult.message;
        // 跳转到整改通知书详情
       startActivity(new Intent(mContext,RectifyNoticeDetailActivity.class).putExtra(BASE_ID,noticeDetailId));
    }
}
