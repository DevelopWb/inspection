package com.juntai.wisdom.inspection.home_page.firecheck.check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.HawkProperty;
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

    @Override
    protected String getTitleName() {
        return null;
    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_commit, null);
        TextView commitTv = view.findViewById(R.id.commit_form_tv);
        commitTv.setOnClickListener(this);
        commitTv.setText("完成");
        return view;
    }

    @Override
    public void initData() {
        adapter.addHeaderView(getHeadView());
        if (getIntent() != null) {
            unitId = getIntent().getIntExtra(BASEID,0);
            firecheckbean = Hawk.get(HawkProperty.ADD_FIRE_CHECK_RECORD_KEY + unitId);
            firecheckbean.setNoticeName(firecheckbean.getUnitName());
//            StringBuilder sb = new StringBuilder();
//            sb.append("根据《中华人民共和国消防法》第五十三条的规定，我单位于");
//            sb.
//            sb.append("对你单位/场所进行消防监督检查,发现存在下列消防安全违法行为：");
            firecheckbean.setNoticeContent(firecheckbean.getNoticeContent());
            adapter.setDetail(true);
            adapter.setNewData(mPresenter.getFireCheckNoticeData(firecheckbean));
        }

    }

    private View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rectify_notice_head, null);
        TextView titleTv = view.findViewById(R.id.single_text_tv);
        titleTv.setText("派出所");
        return view;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.commit_form_tv:
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
                                .addFormDataPart("unitLiable", firecheckbean.getUnitLiable()).addFormDataPart("liablePhone",
                                firecheckbean.getLiablePhone()).addFormDataPart("qualified", "2").build();

                mPresenter.addFireCheckRecord(body, "");
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        BaseResult baseResult = (BaseResult) o;
        String  noticeDetailId = baseResult.message;
        // TODO: 2021/5/23 跳转到整改通知书详情
        ToastUtils.toast(mContext,noticeDetailId);
    }
}
