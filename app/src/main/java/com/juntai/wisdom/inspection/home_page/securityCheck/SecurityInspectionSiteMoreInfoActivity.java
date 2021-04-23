package com.juntai.wisdom.inspection.home_page.securityCheck;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.base.selectPics.SelectPhotosFragment;

/**
 * @aouther tobato
 * @description 描述  巡检点更多信息
 * @date 2021/4/21 10:55
 */

public class SecurityInspectionSiteMoreInfoActivity extends BaseInspectionActivity implements BaseInspectContract.IInspectView {

    private RecyclerView mRecyclerview;
    private SelectPhotosFragment selectPhotosFragment;


    @Override
    protected String getTitleName() {
        return "治安巡检点详情";
    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.footview_commit, null);
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
        super.initView();
        //        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        //        TextKeyValueAdapter adapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
        //        initRecyclerview(mRecyclerview, adapter, LinearLayoutManager.VERTICAL);
        //        adapter.setNewData(getData());
        //        addDivider(true, mRecyclerview, false, true);
        //        selectPhotosFragment = (SelectPhotosFragment) getSupportFragmentManager().findFragmentById(R.id
        //        .photo_fg);
        //        selectPhotosFragment.setSpanCount(3)
        //                .setPhotoDelateable(false);
    }

    @Override
    public void initData() {
        adapter.setNewData(mPresenter.getMoreInfoDetail());
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
                startActivity(new Intent(mContext,EditSecurityInspectSiteActivity.class));
                break;
            default:
                break;
        }
    }
}
