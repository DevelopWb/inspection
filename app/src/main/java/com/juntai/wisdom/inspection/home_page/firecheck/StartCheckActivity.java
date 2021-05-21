package com.juntai.wisdom.inspection.home_page.firecheck;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.unit.FireCheckBean;
import com.juntai.wisdom.inspection.bean.unit.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseCommitFootViewActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.TextKeyValueAdapter;
import com.juntai.wisdom.inspection.utils.CalendarUtil;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

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

    @Override
    public void initData() {
        adapter.setHeaderView(getHeadView());
        if (getIntent() != null) {
            UnitDetailBean.DataBean dataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
            if (dataBean != null) {
                firecheckbean = new FireCheckBean.DataBean();
                firecheckbean.setUnitId(dataBean.getId());
                firecheckbean.setInspectName(UserInfoManager.getUserNickName());
                firecheckbean.setInspectTime(CalendarUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
                firecheckbean.setUnitLiable(dataBean.getPersonLiable());
                firecheckbean.setLiablePhone(dataBean.getLiablePhone());
                headAdapter.setNewData(mPresenter.getStartFireCheckData(firecheckbean));
                adapter.setNewData(mPresenter.getFireCheckData(firecheckbean, false, true));
            }
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
                        adapter.setNewData(mPresenter.getFireCheckData(firecheckbean, false, true));
                        setCommitName("提交");
                        break;
                    case R.id.radio_unqualified_rb:
                        //不合格
                        adapter.setNewData(mPresenter.getFireCheckData(firecheckbean, false, false));
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
                    builder.addFormDataPart("unitId", String.valueOf(firecheckbean.getUnitId())).addFormDataPart(
                            "inspectTime"
                            , firecheckbean.getInspectTime()).addFormDataPart("inspectName",
                            firecheckbean.getInspectName())
                            .addFormDataPart("unitLiable", firecheckbean.getUnitLiable()).addFormDataPart("liablePhone",
                            firecheckbean.getLiablePhone()).addFormDataPart("qualified", "1").build();

            mPresenter.addFireCheckRecord(body,"");
        } else {
            //检查有问题
        }

    }

    @Override
    protected void saveDraft() {

    }

    @Override
    protected String getTitleName() {
        return "开始检查";
    }

    @Override
    public void onSuccess(String tag, Object o) {
        ToastUtils.toast(mContext,"提交成功");
        finish();
    }
}
