package com.juntai.wisdom.inspection.base;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.utils.DateUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Date;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  巡检的基类
 * @CreateDate: 2021/4/22 11:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 11:08
 */
public  abstract class BaseInspectionActivity<P extends BasePresenter> extends BaseAppActivity<P> {

    private BaseInspectionAdapter adapter;
    private RecyclerView mRecyclerview;
    private SmartRefreshLayout mSmartrefreshlayout;

    protected abstract String getTitleName();
    protected abstract List<MultipleItem> getAdapterData();
    protected abstract View getFootView();
    @Override
    public int getLayoutView() {
        return R.layout.recycleview_layout;
    }
    @Override
    public void initView() {
        setTitleName(getTitleName());
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mSmartrefreshlayout = (SmartRefreshLayout) findViewById(R.id.smartrefreshlayout);
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        adapter = new BaseInspectionAdapter(getAdapterData(), false);
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        if (getFootView() != null) {
            adapter.setFooterView(getFootView());
        }
        setAdapterClick();

    }

    private void setAdapterClick() {
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//
//
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                currentPosition = position;
//                MultipleItem multipleItem = (MultipleItem) adapter.getData().get(position);
//
//                switch (view.getId()) {
//                    case R.id.form_pic_src_iv:
//                        BusinessPicBean businessPicBean = (BusinessPicBean) multipleItem.getObject();
//                        if (BusinessContract.TABLE_TITLE_DISABLE_PIC_FRONT_SAMPLE.equals(businessPicBean.getPicName())
//                                || BusinessContract.TABLE_TITLE_DISABLE_PIC_BACK_SAMPLE.equals(businessPicBean.getPicName())) {
//                            //示例图片不可点击
//
//                        } else {
//                            choseImage(0, BaseBusinessActivity.this, 1);
//                        }
//                        break;
//                    case R.id.form_head_pic_iv:
//                        choseImage(0, BaseBusinessActivity.this, 1);
//                        break;
//
//                    case R.id.tool_pic_iv:
//                        //辅具详情
//                        selectBean = (BusinessTextValueBean) multipleItem.getObject();
//                        new AlertDialog.Builder(mContext).setView(getToolInfoView(selectBean.getDataBean())).show();
//                        break;
//
//                    case R.id.select_value_tv:
//                        mSelectTv = (TextView) adapter.getViewByPosition(mRecyclerview, position,
//                                R.id.select_value_tv);
//                        selectBean = (BusinessTextValueBean) multipleItem.getObject();
//                        switch (selectBean.getKey()) {
//                            case BusinessContract.TABLE_TITLE_NATION:
//                                mPresenter.getDisabledNation(BusinessContract.TABLE_TITLE_NATION);
//                                break;
//                            case BusinessContract.TABLE_TITLE_MARRIAGE:
//                                List<String> marrayStatus = getMarrayStatus();
//                                PickerManager.getInstance().showOptionPicker(mContext, marrayStatus,
//                                        new PickerManager.OnOptionPickerSelectedListener() {
//                                            @Override
//                                            public void onOptionsSelect(int options1, int option2, int options3,
//                                                                        View v) {
//                                                selectedMarrayStatus = options1;
//                                                mSelectTv.setText(marrayStatus.get(options1));
//                                                selectBean.setValue(marrayStatus.get(options1));
//                                            }
//                                        });
//                                break;
//                            case BusinessContract.TABLE_TITLE_EDUCATION_LEVEL:
//                                mPresenter.getDisabledEducation(BusinessContract.TABLE_TITLE_EDUCATION_LEVEL);
//                                break;
//                            case BusinessContract.TABLE_TITLE_DISABILITY_KINDS:
//                                //残疾类别
//                                mPresenter.getDisabledCategory(AppHttpPath.GET_DISABLED_TYPE);
//                                break;
//                            case BusinessContract.TABLE_TITLE_DISABILITY_LEVEL:
//                                //残疾等级
//                                mPresenter.getDisabledLevel(AppHttpPath.GET_DISABLED_LEVEL);
//                                break;
//                            case BusinessContract.TABLE_TITLE_HOPE_TRAIN_TYPE:
//                                //种类
//                                mPresenter.getTrainingIntention(AppHttpPath.GET_TRAIN_INTENT_TYPES);
//                                break;
//                            case BusinessContract.TABLE_TITLE_JOB_STATUS:
//                                //就业状况
//                                List<String> jobStatus = getWorkStatus();
//                                PickerManager.getInstance().showOptionPicker(mContext, jobStatus,
//                                        new PickerManager.OnOptionPickerSelectedListener() {
//                                            @Override
//                                            public void onOptionsSelect(int options1, int option2, int options3,
//                                                                        View v) {
//                                                jobStatusId = options1;
//                                                mSelectTv.setText(jobStatus.get(options1));
//                                                selectBean.setValue(jobStatus.get(options1));
//                                            }
//                                        });
//                                break;
//                            case BusinessContract.TABLE_TITLE_SELECT_ASSIST_TOOL:
//                                //辅具选择
//                                if (0 == categoryId) {
//                                    ToastUtils.toast(mContext, "请先选择残疾类别");
//                                    return;
//                                }
//                                mPresenter.getDisabledAIDS(categoryId, AppHttpPath.GET_DISABLED_AIDS);
//                                break;
//                            case BusinessContract.TABLE_TITLE_DELIVERY_METHOD:
//                                //配送方式
//                                List<String> methods = getDeliveryMode();
//                                PickerManager.getInstance().showOptionPicker(mContext, methods,
//                                        new PickerManager.OnOptionPickerSelectedListener() {
//                                            @Override
//                                            public void onOptionsSelect(int options1, int option2, int options3,
//                                                                        View v) {
//                                                methodsId = options1 + 1;
//                                                mSelectTv.setText(methods.get(options1));
//                                                selectBean.setValue(methods.get(options1));
//                                            }
//                                        });
//                                break;
//                            case BusinessContract.TABLE_TITLE_CHILD_IQ:
//                                //儿童发育商
//                                List<String> iQs = getChildIQs();
//                                PickerManager.getInstance().showOptionPicker(mContext, iQs,
//                                        new PickerManager.OnOptionPickerSelectedListener() {
//                                            @Override
//                                            public void onOptionsSelect(int options1, int option2, int options3,
//                                                                        View v) {
//                                                selectedIQId = options1 + 1;
//                                                mSelectTv.setText(iQs.get(options1));
//                                                selectBean.setValue(iQs.get(options1));
//                                            }
//                                        });
//                                break;
//                            case BusinessContract.TABLE_TITLE_BRAIN_PALSY_STYLE:
//                                //脑瘫类型
//                                List<String> brainBadTypes = getBrainBadTypes();
//                                PickerManager.getInstance().showOptionPicker(mContext, brainBadTypes,
//                                        new PickerManager.OnOptionPickerSelectedListener() {
//                                            @Override
//                                            public void onOptionsSelect(int options1, int option2, int options3,
//                                                                        View v) {
//                                                selectedBrainId = options1 + 1;
//                                                mSelectTv.setText(brainBadTypes.get(options1));
//                                                selectBean.setValue(brainBadTypes.get(options1));
//                                            }
//                                        });
//                                break;
//                            case BusinessContract.TABLE_TITLE_BIRTH:
//                                //出生年月
//                                PickerManager.getInstance().showTimePickerView(mContext, null, "出生年月",
//                                        new PickerManager.OnTimePickerTimeSelectedListener() {
//                                            @Override
//                                            public void onTimeSelect(Date date, View v) {
//                                                birthDay = DateUtil.getDateString(date, "yyyy年MM月dd日");
//                                                mSelectTv.setText(birthDay);
//                                                selectBean.setValue(birthDay);
//                                            }
//                                        });
//
//
//                                break;
//                            default:
//                                break;
//                        }
//                        break;
//                    default:
//                        break;
//                }
//
//            }
//        });
    }


}
