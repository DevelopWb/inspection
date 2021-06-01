package com.juntai.wisdom.inspection.mine.myWorkerRecords;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.BaseRecyclerviewFragment;
import com.juntai.wisdom.inspection.bean.MyWorkRecordsBean;
import com.juntai.wisdom.inspection.bean.firecheck.SearchedUnitsBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseSearchActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitInfoActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitsAdapter;
import com.juntai.wisdom.inspection.home_page.importantor.ImportantorInfoActivity;
import com.juntai.wisdom.inspection.home_page.securityInspect.SecurityInspectionSiteInfoActivity;
import com.juntai.wisdom.inspection.mine.MyCenterContract;
import com.juntai.wisdom.inspection.mine.MyCenterPresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述   工作记录
 * @date 2021/6/1 15:58
 */
public class WorkRecordFragment extends BaseRecyclerviewFragment<MyCenterPresent> implements MyCenterContract.ICenterView {

    private String fragmentTag;


    @Override
    protected void lazyLoad() {
        fragmentTag = getArguments().getString(BaseInspectContract.TAB_TITLES);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyWorkRecordsBean.DataBean.DatasBean datasBean =
                        (MyWorkRecordsBean.DataBean.DatasBean) adapter.getData().get(position);
                //类型id（1消防检查；2治安巡检；3重点人员
                switch (datasBean.getTypeId()) {
                    case 1:
                        startActivity(new Intent(mContext, UnitInfoActivity.class).putExtra(BaseInspectionInfoActivity.BASE_ID,datasBean.getId()));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, SecurityInspectionSiteInfoActivity.class).putExtra(BaseInspectionInfoActivity.BASE_ID,
                                datasBean.getId()));
                        break;
                    case 3:
                        startActivity(new Intent(mContext, ImportantorInfoActivity.class).putExtra(BaseInspectionInfoActivity.BASE_ID,datasBean.getId()));

                        break;
                    default:
                        break;
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyWorkRecordsBean.DataBean.DatasBean datasBean =
                        (MyWorkRecordsBean.DataBean.DatasBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.item_navigation_tv:
                        String lat = datasBean.getLatitude();
                        String lng = datasBean.getLongitude();
                        if (TextUtils.isEmpty(lat) || TextUtils.isEmpty(lng)) {
                            ToastUtils.toast(mContext, "无法获取位置信息");
                            return;
                        }
                        getBaseAppActivity().navigationLogic(new LatLng(Double.parseDouble(lat),
                                Double.parseDouble(lng)), datasBean.getGpsAddress());
                        break;
                    default:
                        break;
                }
            }
        });
        mSmartrefreshlayout.setEnableLoadMore(false);
        getAdapterData();
    }

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }

    public static WorkRecordFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(BaseInspectContract.TAB_TITLES, type);
        WorkRecordFragment fragment = new WorkRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void freshlayoutOnLoadMore() {
        getAdapterData();
    }

    /**
     * 获取数据
     */
    private void getAdapterData() {

        mPresenter.myWorkRecords(getBaseAppActivity().getBaseBuilder().add("typeId", String.valueOf(getStatus())).build(), "");
    }

    @Override
    protected void freshlayoutOnRefresh() {
        getAdapterData();
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new WorkRecordAdapter(R.layout.check_item);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        MyWorkRecordsBean workRecordsBean = (MyWorkRecordsBean) o;
        if (workRecordsBean != null) {
            List<MyWorkRecordsBean.DataBean.DatasBean> arrays = workRecordsBean.getData().getDatas();
            adapter.setNewData(arrays);
        }


    }

    /**
     * 获取状态（类型id（0全部；1消防检查；2治安巡检；3重点人员））
     *
     * @return
     */
    private int getStatus() {

        int status = 0;
        switch (fragmentTag) {
            case BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL:
                status = 0;
                break;
            case BaseInspectContract.IMPORTANTOR_TAB_FIRE_CHECK:
                status = 1;
                break;
            case BaseInspectContract.IMPORTANTOR_TAB_INSPECTION:
                status = 2;
                break;
            case BaseInspectContract.IMPORTANTOR_TAB_IMPORTANTOR:
                status = 3;
                break;
            default:
                break;
        }
        return status;
    }
}
