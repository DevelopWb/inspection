package com.juntai.wisdom.inspection.mine.mymsg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseRecyclerviewActivity;
import com.juntai.wisdom.inspection.bean.MyMsgBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitInfoActivity;
import com.juntai.wisdom.inspection.home_page.importantor.ImportantorInfoActivity;
import com.juntai.wisdom.inspection.mine.MyCenterContract;
import com.juntai.wisdom.inspection.mine.MyCenterPresent;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/6/3 11:28
 */
public class MyMessageActivity extends BaseRecyclerviewActivity<MyCenterPresent> implements MyCenterContract.ICenterView {

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }

    @Override
    public void initData() {
        setTitleName("我的消息");
        mSmartrefreshlayout.setEnableRefresh(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        mPresenter.getMyMsgs(mPresenter.getPublishMultipartBody().build(), "");
        addDivider(true, mRecyclerview, false, true);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyMsgBean.DataBean dataBean = (MyMsgBean.DataBean) adapter.getData().get(position);
                int msgId = dataBean.getId();
                //contentId  对应单位详情里面的unitId
                int contentId = dataBean.getContentId();
                switch (dataBean.getTypeId()) {
                    //类型id（1消防通知；2重点人员走访通知
                    case 1:
                        // 单位详情
                        startActivityForResult(new Intent(mContext, UnitInfoActivity.class)
                                        .putExtra(BaseInspectionInfoActivity.BASE_ID2, msgId)
                                        .putExtra(BaseInspectionInfoActivity.BASE_ID, contentId),
                                BASE_REQUEST_RESULT);
                        break;
                    case 2:
                        //跳转到重点人员详情
                        startActivityForResult(new Intent(mContext, ImportantorInfoActivity.class)
                                        .putExtra(BaseInspectionInfoActivity.BASE_ID2, msgId)
                                        .putExtra(BaseInspectionInfoActivity.BASE_ID, contentId)
                                                ,BASE_REQUEST_RESULT);

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
        mPresenter.getMyMsgs(mPresenter.getPublishMultipartBody().build(), "");
    }

    @Override
    protected void freshlayoutOnLoadMore() {

    }

    @Override
    protected void freshlayoutOnRefresh() {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new MyMsgAdapter(R.layout.item_mine_msg);
    }

    @Override
    public void onSuccess(String tag, Object o) {
        MyMsgBean myMsgBean = (MyMsgBean) o;
        if (myMsgBean != null) {
            List<MyMsgBean.DataBean> arrays = myMsgBean.getData();
            adapter.setNewData(arrays);
        }
    }
}
