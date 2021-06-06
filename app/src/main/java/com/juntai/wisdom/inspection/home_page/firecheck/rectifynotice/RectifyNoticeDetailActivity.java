package com.juntai.wisdom.inspection.home_page.firecheck.rectifynotice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.bean.firecheck.RectifyNoticeBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitInfoActivity;
import com.juntai.wisdom.inspection.utils.ToolShare;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * @aouther tobato
 * @description 描述   整改通知单详情
 * @date 2021/5/28 15:12
 */
public class RectifyNoticeDetailActivity extends BaseInspectionActivity implements View.OnClickListener {

    private ImageView mSignNameIv;
    private ImageView mSealIv;
    /**
     * 分享微信
     */
    private TextView mShareWechatTv;
    /**
     * 下载Word版
     */
    private TextView mDownloadWordTv;
    private TextView mSealedTv;
    private String enterType;//"0"代表创建整改通知单后跳转过来  “1”代表整改通知单列表进入
    private RectifyNoticeBean.DataBean dataBean;

    @Override
    public void initData() {
        adapter.setDetail(true);
        adapter.addHeaderView(getHeadView());
        enterType = getIntent().getStringExtra(BASE_STRING);

        int noticeId = getIntent().getIntExtra(BASE_ID, 0);
        mPresenter.getRectifyNoticeDetail(getBaseBuilder().add("noticeId", String.valueOf(noticeId)).build(),
                AppHttpPath.GET_RECTIFY_NOTICE_DETAIL);
    }


    @Override
    protected String getTitleName() {
        return null;
    }

    @Override
    protected View getFootView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.retify_notice_footview, null);
        mSignNameIv = (ImageView) view.findViewById(R.id.sign_name_iv);
        mSealIv = (ImageView) view.findViewById(R.id.seal_iv);
        mShareWechatTv = (TextView) view.findViewById(R.id.share_wechat_tv);
        mSealedTv = (TextView) view.findViewById(R.id.sealed_time_tv);
        mShareWechatTv.setOnClickListener(this);
        mDownloadWordTv = (TextView) view.findViewById(R.id.download_word_tv);
        mDownloadWordTv.setOnClickListener(this);
        return view;
    }

    private View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rectify_notice_head, null);
        TextView titleTv = view.findViewById(R.id.single_text_tv);
        titleTv.setText(UserInfoManager.getDepartmentName());
        return view;
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        RectifyNoticeBean rectifyNoticeBean = (RectifyNoticeBean) o;
        if (rectifyNoticeBean != null) {
            dataBean = rectifyNoticeBean.getData();
            StringBuilder sb = new StringBuilder();
            sb.append("根据《中华人民共和国消防法》第五十三条的规定，我单位于")
                    .append(dataBean.getGmtCreate())
                    .append("对你单位/场所进行消防监督检查,发现存在下列")
                    .append(dataBean.getItem())
                    .append("项消防安全违法行为,\n现责令整改：");
            dataBean.setNoticeContent(sb.toString());
            adapter.setNewData(mPresenter.getRectifyNoticeData(dataBean));
            ImageLoadUtil.loadImageNoCache(mContext, UrlFormatUtil.getImageOriginalUrl(dataBean.getSignPhoto()),mSignNameIv);
            ImageLoadUtil.loadImageNoCache(mContext, UrlFormatUtil.getImageOriginalUrl(dataBean.getSeal()),mSealIv);
            mSealedTv.setText(dataBean.getGmtCreate());
        }
    }


    @Override
    public void onBackPressed() {
        if ("0".equals(enterType)) {
            startActivity(new Intent(mContext, UnitInfoActivity.class));
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.share_wechat_tv:
                //整改通知单 分享至微信
                ToolShare.shareForMob(mContext, "整改通知书", dataBean.getShareUrl(), dataBean.getUnitName(),
                        UrlFormatUtil.getImageOriginalUrl(dataBean.getSeal()),
                        new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                break;
            case R.id.download_word_tv:
                // TODO: 2021/5/28 整改通知单 下载word版
                ToastUtils.toast(mContext,"暂未开放");
                break;
        }
    }
}
