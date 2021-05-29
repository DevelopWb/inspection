package com.juntai.wisdom.inspection.home_page.firecheck.responsibility;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.wisdom.inspection.bean.IdNameBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  责任书列表
 * @date 2021/5/29 9:11
 */
public class ResponsibilityActivity extends BaseInspectionActivity {

    private int unitId;

    @Override
    protected String getTitleName() {
        return "责任书列表";
    }

    @Override
    public void initData() {
        unitId = getIntent().getIntExtra(BASE_ID, 0);
        mPresenter.getResponsibilityList(mPresenter.getPublishMultipartBody().addFormDataPart("unitId",
                String.valueOf(unitId)).build(), "");


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem  item= (MultipleItem) adapter.getData().get(position);
                switch (item.getItemType()) {
                    case MultipleItem.ITEM_RESIBILITY:
                        IdNameBean.DataBean dataBean = (IdNameBean.DataBean) item.getObject();
                        if (dataBean.getId() > 0) {
                            //跳转到详情页面
                            startActivity(new Intent(mContext, ResponsibilityDetailActivity.class)
                                    .putExtra(BASE_ID,dataBean.getId()));
                        } else {
                            //跳转到  签责任书页
                            startActivityForResult(new Intent(mContext, SignResponsibilityActivity.class)
                                    .putExtra(BASE_STRING,dataBean.getName())
                                    .putExtra(BASE_ID,unitId)
                                    .putExtra(BASE_STRING2,dataBean.getContent()),BASE_REQUEST_RESULT);
                        }
                        break;
                    default:
                        break;
                }


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.getResponsibilityList(mPresenter.getPublishMultipartBody().addFormDataPart("unitId",
                String.valueOf(unitId)).build(), "");

    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    public void onSuccess(String tag, Object o) {

        IdNameBean idNameBean = (IdNameBean) o;
        if (idNameBean != null) {
            List<IdNameBean.DataBean> arrays = idNameBean.getData();
            if (arrays != null) {
                List<IdNameBean.DataBean> signings = new ArrayList<>();
                List<IdNameBean.DataBean> signeds = new ArrayList<>();
                for (IdNameBean.DataBean array : arrays) {
                    if (array.getId() > 0) {
                        //已经签过了
                        signeds.add(array);
                    } else {
                        signings.add(array);
                    }
                }
                List<MultipleItem> datas = new ArrayList<>();
                if (signings.size() > 0) {
                    datas.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "签署责任书"));
                    for (IdNameBean.DataBean signing : signings) {
                        datas.add(new MultipleItem(MultipleItem.ITEM_RESIBILITY, signing));
                    }
                }
                if (signeds.size() > 0) {
                    datas.add(new MultipleItem(MultipleItem.ITEM_TITILE_BIG, "已签责任书"));
                    for (IdNameBean.DataBean signed : signeds) {
                        datas.add(new MultipleItem(MultipleItem.ITEM_RESIBILITY, signed));

                    }

                }

                adapter.setNewData(datas);
            }
        }

    }
}
