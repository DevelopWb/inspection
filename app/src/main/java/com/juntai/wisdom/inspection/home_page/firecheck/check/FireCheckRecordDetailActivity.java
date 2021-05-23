package com.juntai.wisdom.inspection.home_page.firecheck.check;

import android.os.Bundle;
import android.view.View;

import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.inspection.bean.firecheck.FireCheckBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

/**
 * @aouther tobato
 * @description 描述  消防检查详情
 * @date 2021/5/21 14:46
 */
public class FireCheckRecordDetailActivity extends BaseInspectionActivity {

    @Override
    protected String getTitleName() {
        return "消防检查详情";
    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            int id = getIntent().getIntExtra(BASEID, 0);
            mPresenter.getFireCheckRecordDetail(getBaseBuilder().add("recordId", String.valueOf(id)).build(), "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idDetail = true;
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        FireCheckBean fireCheckBean = (FireCheckBean) o;
        if (fireCheckBean != null) {
            FireCheckBean.DataBean dataBean = fireCheckBean.getData();
            if (1 == dataBean.getQualified()) {
                //合格
                adapter.setNewData(mPresenter.getFireCheckedOkDetailData(dataBean));
            } else {
                //不合格

                dataBean.setNoticeName("问题及整改意见");
                dataBean.setNoticeContent(null);
                dataBean.setHideSummarize(true);
                if (0== dataBean.getPunishId()) {
                    //未添加处罚信息
                    getTitleRightTv().setText("添加处罚信息");
                    getTitleRightTv().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //todo  添加处罚信息
                            ToastUtils.toast(mContext,"添加处罚信息");
                        }
                    });
                }
                adapter.setNewData(mPresenter.getFireCheckedHasQuestionDetailData(dataBean,0== dataBean.getPunishId()
                        ?false:true));
            }


        }
    }
}
