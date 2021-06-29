package com.juntai.wisdom.inspection.home_page.firecheck.responsibility;

import android.telephony.CellSignalStrength;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.ResponseListBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.utils.CalendarUtil;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/6/29 8:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/29 8:42
 */
public abstract class BaseResponsibilityActivity extends BaseInspectionActivity implements BaseInspectionActivity.OnSignedCallBack {

    private TextView mPoliceStationNameTv;
    private TextView mSignNameTv;
    private ImageView mSignNameIv;
    private LinearLayout mSignLl;
    private TextView mPoliceManagerNameTv;
    private TextView mUnitNameTagTv;
    private TextView mUnitNameTv;
    private TextView mTelephoneTv;
    private TextView mDateTv;
    public TextView mCommitTv;

    public String signPath = null;
    public ResponseListBean.DataBean dataBean;
    public boolean isFireSafeResponsibility = false;
    private ImageView mPoliceManagerSealIv;
    private ImageView mPoliceSealIv;
    private TextView mPoliceStationTagTv;
    private TextView mUnitTagTv;


    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.sign_responsibility_footview, null);
        mPoliceStationNameTv = view.findViewById(R.id.police_station_name_tv);
        mSignNameTv = view.findViewById(R.id.sign_name_tv);
        mSignNameIv = view.findViewById(R.id.sign_name_iv);
        mPoliceManagerSealIv = view.findViewById(R.id.police_manager_seal_iv);
        mPoliceSealIv = view.findViewById(R.id.police_seal_iv);
        mSignLl = view.findViewById(R.id.sign_ll);
        mPoliceManagerNameTv = view.findViewById(R.id.police_manager_name_tv);
        mPoliceStationTagTv = view.findViewById(R.id.police_station_tag_tv);
        mUnitTagTv = view.findViewById(R.id.unit_name_tag_tv);
        mUnitNameTagTv = view.findViewById(R.id.unit_name_tag_tv);
        mUnitNameTv = view.findViewById(R.id.unit_name_tv);
        mTelephoneTv = view.findViewById(R.id.telephone_tv);
        mDateTv = view.findViewById(R.id.date_tv);
        mCommitTv = view.findViewById(R.id.commit_form_tv);
        mCommitTv.setOnClickListener(this);
        if (isDetail()) {
            mCommitTv.setVisibility(View.GONE);
        }else {
            mCommitTv.setVisibility(View.VISIBLE);
            mSignLl.setOnClickListener(this);
        }
        return view;
    }


    abstract  boolean isDetail();
    @Override
    public void initData() {
        dataBean = getIntent().getParcelableExtra(PARCELABLE_KEY);
        if (!isDetail()) {
            initfootlayoutviews(dataBean);
        }
    }

    public void initfootlayoutviews(ResponseListBean.DataBean dataBean) {
        if ("消防安全责任书".equals(dataBean.getName())) {
            isFireSafeResponsibility = true;
            mTelephoneTv.setVisibility(View.VISIBLE);
        } else {
            isFireSafeResponsibility = false;
            mTelephoneTv.setVisibility(View.GONE);
        }
        setOnSignedCallBack(this);
        mUnitNameTv.setText(dataBean.getUnitName());
        mTelephoneTv.setText("联系电话:"+dataBean.getLegalPhone());
        mPoliceStationTagTv.setText(isFireSafeResponsibility ? "单位:" : "甲方:" );
        mUnitTagTv.setText(isFireSafeResponsibility ? "单位名称:" : "乙方:" );
        mPoliceManagerNameTv.setText(isFireSafeResponsibility ? "所长:" : "派出所:" );
        mSignNameTv.setText(isFireSafeResponsibility ? "单位法人:" : "责任人:" );
        mPoliceStationNameTv.setText(UserInfoManager.getDepartmentName());

        if (isFireSafeResponsibility) {
            ImageLoadUtil.loadImageNoCache(mContext, UrlFormatUtil.getImageOriginalUrl(dataBean.getSeal()), mPoliceSealIv);
            ImageLoadUtil.loadImageNoCache(mContext, UrlFormatUtil.getImageOriginalUrl(dataBean.getNameSeal()), mPoliceManagerSealIv);
        }else {
            ImageLoadUtil.loadImageNoCache(mContext, UrlFormatUtil.getImageOriginalUrl(dataBean.getSeal()), mPoliceManagerSealIv);
        }
        if (isDetail()) {
            mDateTv.setText(dataBean.getGmtCreate());
            ImageLoadUtil.loadImageNoCache(mContext, UrlFormatUtil.getImageOriginalUrl(dataBean.getSignPic()), mSignNameIv);
        }else {
            mDateTv.setText(CalendarUtil.getCurrentTime("yyyy年MM月dd日"));
        }
    }

    @Override
    public void signed(String picPath) {
        signPath = picPath;
        ImageLoadUtil.loadImageNoCache(mContext, picPath, mSignNameIv);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

            case R.id.sign_ll:
                //签名
                showSignatureView();
                break;
            default:
                break;
        }
    }

}
