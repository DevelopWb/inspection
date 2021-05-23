package com.juntai.wisdom.inspection.home_page.firecheck.check;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnQuailityFormBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnQualifiedBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.TextKeyValueAdapter;
import com.juntai.wisdom.inspection.utils.CalendarUtil;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.juntai.wisdom.inspection.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  开始消防检查
 * @date 2021/5/19 14:38
 */
public class StartCheckActivity extends BaseCommitFootViewActivity {

    private FireCheckBean.DataBean firecheckbean;
    private RecyclerView mFireCheckHeadRv;
    /**
     * 合格
     */
    private RadioButton mRadioQualifiedRb;
    /**
     * 不合格
     */
    private RadioButton mRadioUnqualifiedRb;
    private RadioGroup mItemRadioG;
    private TextKeyValueAdapter headAdapter;
    private UnitDetailBean.DataBean unitBean;

    @Override
    public void initData() {
        adapter.setHeaderView(getHeadView());
        if (getIntent() != null) {
            unitBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
            if (unitBean != null) {
                firecheckbean = new FireCheckBean.DataBean();
                firecheckbean.setInspectName(UserInfoManager.getUserNickName());
                firecheckbean.setInspectTime(CalendarUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                firecheckbean.setUnitLiable(unitBean.getPersonLiable());
                firecheckbean.setLiablePhone(unitBean.getLiablePhone());
                firecheckbean.setItemsJson(getString(R.string.unquaility_problems));
                headAdapter.setNewData(mPresenter.getStartFireCheckData(firecheckbean));
                adapter.setNewData(mPresenter.getFireCheckData(firecheckbean, true));
            }
        }
        FireCheckBean.DataBean savedRecordBean =
                Hawk.get(HawkProperty.ADD_FIRE_CHECK_RECORD_KEY + unitBean.getId());
        if (savedRecordBean != null) {
            new AlertDialog.Builder(mContext).setMessage("您上次还有未提交的草稿,是否进入草稿？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            firecheckbean = savedRecordBean;
                            adapter.setNewData(mPresenter.getFireCheckData(firecheckbean,
                                    1 == savedRecordBean.getQualified() ? true : false));
                            if (1 == savedRecordBean.getQualified()) {
                                mRadioQualifiedRb.setChecked(true);
                            } else {
                                mRadioUnqualifiedRb.setChecked(true);
                            }

                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            }).show();
        }

    }



    /**
     * 头布局
     *
     * @return
     */
    private View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fire_check_head_layout, null);
        mFireCheckHeadRv = (RecyclerView) view.findViewById(R.id.fire_check_head_rv);
        mRadioQualifiedRb = (RadioButton) view.findViewById(R.id.radio_qualified_rb);
        mRadioUnqualifiedRb = (RadioButton) view.findViewById(R.id.radio_unqualified_rb);
        mItemRadioG = (RadioGroup) view.findViewById(R.id.item_radio_g);
        headAdapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
        initRecyclerview(mFireCheckHeadRv, headAdapter, LinearLayoutManager.VERTICAL);
        mItemRadioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_qualified_rb:
                        //合格
                        adapter.setNewData(mPresenter.getFireCheckData(firecheckbean, true));
                        setCommitName("提交");
                        break;
                    case R.id.radio_unqualified_rb:
                        //不合格
                        adapter.setNewData(mPresenter.getFireCheckData(firecheckbean, false));
                        setCommitName("下一步");
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }

    @Override
    protected String getCommitTextValue() {
        return "提交";
    }

    @Override
    protected void commitRequest(MultipartBody.Builder builder) {

        if ("提交".equals(commitName)) {
            //检查没问题
            RequestBody body =
                    builder.addFormDataPart("unitId", String.valueOf(unitBean.getId())).addFormDataPart(
                            "inspectTime"
                            , firecheckbean.getInspectTime()).addFormDataPart("inspectName",
                            firecheckbean.getInspectName())
                            .addFormDataPart("unitLiable", firecheckbean.getUnitLiable()).addFormDataPart("liablePhone",
                            firecheckbean.getLiablePhone()).addFormDataPart("qualified", "1").build();

            mPresenter.addFireCheckRecord(body, "");
        } else {
            //检查有问题
        }

    }

    @Override
    protected void saveDraft() {
        //保存草稿
        if (getBaseAdapterData(true) != null) {
            FireCheckBean.DataBean dataBean =
                    getBaseAdapterData(true).getFireCheckBean();
            dataBean.setInspectTime(firecheckbean.getInspectTime());
            dataBean.setInspectName(firecheckbean.getInspectName());
            dataBean.setQualified("提交".equals(commitName) ? 1 : 2);
            dataBean.setUnitLiable(firecheckbean.getUnitLiable());
            dataBean.setLiablePhone(firecheckbean.getLiablePhone());
            Hawk.put(HawkProperty.ADD_FIRE_CHECK_RECORD_KEY + unitBean.getId(), dataBean);
            ToastUtils.toast(mContext, "草稿保存成功");
            finish();
        }
    }

    @Override
    protected String getTitleName() {
        return "开始检查";
    }

    @Override
    public void onSuccess(String tag, Object o) {
        ToastUtils.toast(mContext, "提交成功");
        finish();
    }
}
