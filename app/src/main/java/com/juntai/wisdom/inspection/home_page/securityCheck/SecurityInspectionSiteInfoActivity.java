package com.juntai.wisdom.inspection.home_page.securityCheck;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectPresent;
import com.juntai.wisdom.inspection.home_page.baseinspect.TextKeyValueAdapter;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  治安巡检信息
 * @date 2021/4/20 16:12
 */
public class SecurityInspectionSiteInfoActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView, View.OnClickListener {

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
    private ConstraintLayout mInspectionRecordCl;
    /**
     * 开始巡检
     */
    private TextView mStartInspectTv;

    @Override
    protected BaseInspectPresent createPresenter() {
        return null;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_security_inspection_info;
    }

    @Override
    public void initView() {
        setTitleName("治安巡检点详情");
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        TextKeyValueAdapter adapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        adapter.setNewData(getData());
        addDivider(true, mRecyclerview, false, true);
        mQrCodeIv = (ImageView) findViewById(R.id.qr_code_iv);
        mQrCodeIv.setOnClickListener(this);
        mNavigationTv = (TextView) findViewById(R.id.navigation_tv);
        mNavigationTv.setOnClickListener(this);
        mSeeMoreInfoTv = (TextView) findViewById(R.id.see_more_info_tv);
        mSeeMoreInfoTv.setOnClickListener(this);
        mInspectionRecordCl = (ConstraintLayout) findViewById(R.id.inspection_record_cl);
        mInspectionRecordCl.setOnClickListener(this);
        mStartInspectTv = (TextView) findViewById(R.id.start_inspect_tv);
        mStartInspectTv.setOnClickListener(this);
    }

    private List<TextKeyValueBean> getData() {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("巡检点:", "暂无"));
        arrays.add(new TextKeyValueBean("巡检地址:", "暂无"));
        arrays.add(new TextKeyValueBean("安全责任人:", "暂无"));
        return arrays;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

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
                startActivity(new Intent(mContext,SecurityInspectionSiteMoreInfoActivity.class));
                break;
            case R.id.inspection_record_cl:
                //巡检记录
                startActivity(new Intent(mContext,SecurityInspectRecordActivity.class));
                break;
            case R.id.start_inspect_tv:
                //开始巡检

                startActivity(new Intent(mContext,StartSecurityInspectActivity.class));
                break;
        }
    }
}
