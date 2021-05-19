package com.juntai.wisdom.inspection.home_page.securityInspect;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.base.selectPics.SelectPhotosFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  巡检点更多信息
 * @date 2021/4/21 10:55
 */

public class SecurityInspectionSiteMoreInfoActivity extends BaseInspectionActivity implements BaseInspectContract.IInspectView {

    private RecyclerView mRecyclerview;
    private SelectPhotosFragment selectPhotosFragment;
    private InspectionSiteBean.DataBean dataBean;


    @Override
    protected String getTitleName() {
        return "治安巡检点详情";
    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_commit, null);
        TextView mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        mCommitBusinessTv.setText("申请修改");
        mCommitBusinessTv.setOnClickListener(this);
        return view;
    }

    //    @Override
    //    public int getLayoutView() {
    //        return R.layout.activity_security_more_info;
    //    }

    @Override
    public void initView() {
        idDetail = true;
        super.initView();
        if (getIntent() != null) {
            dataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
        }
//        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
//        TextKeyValueAdapter textAdapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
//        initRecyclerview(mRecyclerview, textAdapter, LinearLayoutManager.VERTICAL);
//        textAdapter.setNewData(getTextListData(dataBean));
//        addDivider(true, mRecyclerview, false, true);
//        selectPhotosFragment = (SelectPhotosFragment) getSupportFragmentManager().findFragmentById(R.id
//                .photo_fg);
//        selectPhotosFragment.setSpanCount(3)
//                .setPhotoDelateable(false);
    }

    private List<TextKeyValueBean> getTextListData(InspectionSiteBean.DataBean dataBean) {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("巡检点:",dataBean.getName()));
        arrays.add(new TextKeyValueBean("巡检地址:", dataBean.getAddress()));
        arrays.add(new TextKeyValueBean("安全责任人:", dataBean.getPersonLiable()));
        arrays.add(new TextKeyValueBean("联系电话:", dataBean.getLiablePhone()));
        arrays.add(new TextKeyValueBean("备用联系人:", dataBean.getSparePerson()));
        arrays.add(new TextKeyValueBean("联系电话:", dataBean.getSparePhone()));
        arrays.add(new TextKeyValueBean("备注:", dataBean.getRemarks()));
        return arrays;
    }

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getInspectionSiteInfoData(dataBean,true));

    }


    @Override
    public void onSuccess(String tag, Object o) {
        //        selectPhotosFragment.setMaxCount(images.size());
        //        selectPhotosFragment.setIcons(images);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.commit_form_tv:
                startActivity(new Intent(mContext, EditSecurityInspectSiteActivity.class).putExtra(PARCELABLE_KEY,dataBean));
                break;
            default:
                break;
        }
    }
}
