package com.juntai.wisdom.inspection.securityCheck;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.base.TextKeyValueAdapter;
import com.juntai.wisdom.inspection.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  巡检点更多信息
 * @date 2021/4/21 10:55
 */

public class SecurityInspectionSiteMoreInfoActivity extends BaseAppActivity<SecurityPresent> implements SecurityContract.ISecurityView,SelectPhotosFragment.OnPhotoItemClick {

    private RecyclerView mRecyclerview;
    private SelectPhotosFragment selectPhotosFragment;

    @Override
    protected SecurityPresent createPresenter() {
        return null;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_security_more_info;
    }

    @Override
    public void initView() {
        setTitleName("治安巡检点详情");
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        TextKeyValueAdapter adapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        adapter.setNewData(getData());
        addDivider(true, mRecyclerview, false, true);
        selectPhotosFragment = (SelectPhotosFragment) getSupportFragmentManager().findFragmentById(R.id.photo_fg);
        selectPhotosFragment.setSpanCount(3)
                .setPhotoDelateable(false);
    }
    private List<TextKeyValueBean> getData() {
        List<TextKeyValueBean> arrays = new ArrayList<>();
        arrays.add(new TextKeyValueBean("巡检点:", "暂无"));
        arrays.add(new TextKeyValueBean("巡检地址:", "暂无"));
        arrays.add(new TextKeyValueBean("安全责任人:", "暂无"));
        arrays.add(new TextKeyValueBean("联系电话:", "暂无"));
        arrays.add(new TextKeyValueBean("备用联系人:", "暂无"));
        arrays.add(new TextKeyValueBean("联系电话:", "暂无"));
        return arrays;
    }
    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {
//        selectPhotosFragment.setMaxCount(images.size());
//        selectPhotosFragment.setIcons(images);
    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }
}
