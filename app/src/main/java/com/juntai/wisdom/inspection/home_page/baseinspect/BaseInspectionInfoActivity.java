package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.home_page.securityCheck.SecurityInspectionSiteMoreInfoActivity;
import com.juntai.wisdom.inspection.home_page.securityCheck.StartSecurityInspectActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  巡检详情基类
 * @date 2021/4/20 16:12
 */
public abstract class BaseInspectionInfoActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView, View.OnClickListener {



    private RecyclerView mRecyclerview;
    private ImageView mQrCodeIv;
    /**
     * 导航
     */
    private TextView mNavigationTv;
    /**
     * 点击查看更多信息>>
     */
    private TextView mSeeMoreInfoTv;
    /**
     * 开始巡检
     */
    private TextView mStartWorkTv;
    private RecyclerView mActionsRv;
    public ActionsAdapter actionsAdapter;
    public TextKeyValueAdapter baseInfoAdapter;

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_base_inspection_info;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        baseInfoAdapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
        initRecyclerviewNoScroll(mRecyclerview, baseInfoAdapter, LinearLayoutManager.VERTICAL);
        baseInfoAdapter.setNewData(getData());
        addDivider(true, mRecyclerview, false, true);
        mQrCodeIv = (ImageView) findViewById(R.id.qr_code_iv);
        mQrCodeIv.setOnClickListener(this);
        mNavigationTv = (TextView) findViewById(R.id.navigation_tv);
        mNavigationTv.setOnClickListener(this);
        mSeeMoreInfoTv = (TextView) findViewById(R.id.see_more_info_tv);
        mSeeMoreInfoTv.setOnClickListener(this);
        mStartWorkTv = (TextView) findViewById(R.id.start_work_tv);
        mStartWorkTv.setText(getStartWorkName());
        mStartWorkTv.setOnClickListener(this);
        mActionsRv = (RecyclerView) findViewById(R.id.actions_rv);
        actionsAdapter = new ActionsAdapter(R.layout.item_actions);
        initRecyclerviewNoScroll(mActionsRv, actionsAdapter, LinearLayoutManager.VERTICAL);
    }

    protected abstract String getTitleName();

    protected abstract String getStartWorkName();

    public List<TextKeyValueBean> getData() {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("巡检点:", "暂无"));
        arrays.add(new TextKeyValueBean("巡检地址:", "暂无"));
        arrays.add(new TextKeyValueBean("安全责任人:", "暂无"));
        return arrays;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.qr_code_iv:
                //二维码
                break;
            case R.id.navigation_tv:
                //导航
                break;
            case R.id.see_more_info_tv:
                //查看更多信息
                startActivity(new Intent(mContext, SecurityInspectionSiteMoreInfoActivity.class));
                break;
            case R.id.start_work_tv:
                //开始巡检
                startActivity(new Intent(mContext, StartSecurityInspectActivity.class));
                break;
        }
    }

}
