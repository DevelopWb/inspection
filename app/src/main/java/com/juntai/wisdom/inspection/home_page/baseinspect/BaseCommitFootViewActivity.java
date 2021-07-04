package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.juntai.disabled.bdmap.act.LocateSelectionActivity;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.bean.BaseAdapterDataBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/13 10:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/13 10:29
 */
public abstract class BaseCommitFootViewActivity extends BaseInspectionActivity {

    private TextView mCommitBusinessTv;
    public String commitName;

    public void setCommitName(String commitName) {
        this.commitName = commitName == null ? "" : commitName;
        if (mCommitBusinessTv != null) {
            mCommitBusinessTv.setText(commitName);
        }

    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {
        super.onLocationReceived(bdLocation);
        if (bdLocation != null) {
            lat = bdLocation.getLatitude();
            lng = bdLocation.getLongitude();
            address = bdLocation.getAddrStr();
            notifyLocationItem();
        }

    }

    /**
     * 更新定位item
     */
    private void notifyLocationItem() {
        List<MultipleItem> arrays = adapter.getData();
        for (int i = 0; i < arrays.size(); i++) {
            MultipleItem array = arrays.get(i);
            if (MultipleItem.ITEM_LOCATION == array.getItemType()) {
                //定位
                LocationBean locationBean = (LocationBean) array.getObject();
                locationBean.setAddress(address);
                locationBean.setLatitude(String.valueOf(lat));
                locationBean.setLongitude(String.valueOf(lng));
                adapter.notifyItemChanged(i);
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LocateSelectionActivity.SELECT_ADDR && resultCode == RESULT_OK) {
            //地址选择
            lat = data.getDoubleExtra("lat", 0.0);
            lng = data.getDoubleExtra("lng", 0.0);
            address = data.getStringExtra("address");
            notifyLocationItem();
        }

    }


    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_save_commit, null);
        mCommitBusinessTv = view.findViewById(R.id.commit_form_tv);
        mCommitBusinessTv.setText(getCommitTextValue());
        commitName = getCommitTextValue();
        mCommitBusinessTv.setOnClickListener(this);
        TextView mSaveDraft = view.findViewById(R.id.save_draft_tv);
        mSaveDraft.setOnClickListener(this);
        return view;
    }

    protected abstract String getCommitTextValue();

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.save_draft_tv:
                //保存草稿
                saveDraft();


                break;
            case R.id.commit_form_tv:
                if ("下一步".equals(commitName)) {
                    //消防检查  检查出问题时 下一步的逻辑
                    next();
                    return;
                }
                BaseAdapterDataBean baseAdapterDataBean = getBaseAdapterData(false);
                if (baseAdapterDataBean == null) {
                    return;
                }
                MultipartBody.Builder builder = getBaseAdapterData(false).getBuilder();
                commitRequest(builder);


                break;
            default:
                break;
        }
    }

    /**
     * 下一步
     */
    public void next() {
    }

    /**
     * 提交请求
     *
     * @param builder
     */
    protected abstract void commitRequest(MultipartBody.Builder builder);

    protected abstract void saveDraft();


}
