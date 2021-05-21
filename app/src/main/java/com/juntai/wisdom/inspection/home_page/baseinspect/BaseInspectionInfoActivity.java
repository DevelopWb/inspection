package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.content.Intent;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.bean.ActionBean;
import com.juntai.wisdom.inspection.bean.importantor.ImportantorBean;
import com.juntai.wisdom.inspection.bean.inspectionsite.InspectionSiteBean;
import com.juntai.wisdom.inspection.bean.unit.UnitDetailBean;
import com.juntai.wisdom.inspection.home_page.firecheck.FireCheckRecordListActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.StartCheckActivity;
import com.juntai.wisdom.inspection.home_page.importantor.StartVisitActivity;
import com.juntai.wisdom.inspection.home_page.importantor.VisitRecordListActivity;
import com.juntai.wisdom.inspection.home_page.securityInspect.SecurityInspectRecordListActivity;
import com.juntai.wisdom.inspection.home_page.securityInspect.StartSecurityInspectActivity;

import java.util.List;

/**
 * @aouther tobato
 * @description 描述  巡检详情基类
 * @date 2021/4/20 16:12
 */
public abstract class BaseInspectionInfoActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView, View.OnClickListener {


    public final  static String START_VISIT="开始走访";//
    public final static String START_CHECK="开始检查";//
    public final static String START_INSPECT="开始巡检";//
    public final static String BASE_ID="baseid";//
    public int  baseId ;

    private RecyclerView mRecyclerview;
    public ImageView mQrCodeIv;
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
    private ActionsAdapter actionsAdapter;
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
        if (getIntent() != null) {
            baseId = getIntent().getIntExtra(BASE_ID,0);
        }
        setTitleName(getTitleName());
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        baseInfoAdapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
        initRecyclerviewNoScroll(mRecyclerview, baseInfoAdapter, LinearLayoutManager.VERTICAL);
        addDivider(true, mRecyclerview, false, true);
        mQrCodeIv = (ImageView) findViewById(R.id.qr_code_iv);
        mQrCodeIv.setOnClickListener(this);
        mNavigationTv = (TextView) findViewById(R.id.navigation_tv);
        Group noQrcodeGp = (Group) findViewById(R.id.no_qr_code_gp);
        noQrcodeGp.setVisibility("重点人员详情".equals(getTitleName())?View.GONE:View.VISIBLE);
        mNavigationTv.setOnClickListener(this);
        mSeeMoreInfoTv = (TextView) findViewById(R.id.see_more_info_tv);
        mSeeMoreInfoTv.setOnClickListener(this);
        mStartWorkTv = (TextView) findViewById(R.id.start_work_tv);
        mStartWorkTv.setText(getStartWorkName());
        mStartWorkTv.setOnClickListener(this);
        mActionsRv = (RecyclerView) findViewById(R.id.actions_rv);
        actionsAdapter = new ActionsAdapter(R.layout.item_actions);
        initRecyclerviewNoScroll(mActionsRv, actionsAdapter, LinearLayoutManager.VERTICAL);
        actionsAdapter.setNewData(getActionAdapterData());
        actionsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActionBean  actionBean = (ActionBean) adapter.getData().get(position);
                switch (actionBean.getActionName()) {
                    case BaseInspectContract.INSPECTION_SECURITY_RECORD:
                        //治安巡检记录
                        startActivity(new Intent(mContext, SecurityInspectRecordListActivity.class).putExtra(BaseRecordActivity.ID,((InspectionSiteBean.DataBean)getBaseBean()).getId()));
                        break;
                    case BaseInspectContract.INSPECTION_VISIT_RECORD:
                        //走访记录
                        startActivity(new Intent(mContext, VisitRecordListActivity.class).putExtra(BaseRecordActivity.ID,((ImportantorBean.DataBean)getBaseBean()).getId()));
                        break;
                    case BaseInspectContract.INSPECTION_CHECK_RECORD:
                        //  单位详情里面的检查记录
                        startActivity(new Intent(mContext, FireCheckRecordListActivity.class).putExtra(BaseRecordActivity.ID,
                                ((UnitDetailBean.DataBean)getBaseBean()).getId()));
                        break;
                    case BaseInspectContract.INSPECTION_RESPONSIBILITY:
                        // TODO: 2021/5/18  单位详情里面的责任书
                        break;
                    case BaseInspectContract.INSPECTION_RECTIFY_NOTICE:
                        // TODO: 2021/5/18  单位详情里面的整改通知书

                        break;
                    case BaseInspectContract.INSPECTION_WORKER:
                        // TODO: 2021/5/18  单位详情里面的从业人员

                        break;
                    default:
                        break;
                }
            }
        });
    }

    protected abstract Object getBaseBean();

    protected abstract List<ActionBean> getActionAdapterData();

    protected abstract String getTitleName();

    protected abstract String getStartWorkName();




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.qr_code_iv:
                //二维码
                // TODO: 2021/5/18 信息详情中的二维码点击时间   长按也没做
                break;
            case R.id.navigation_tv:
                //导航
                navigationLogic();
                break;
            case R.id.see_more_info_tv:
                //查看更多信息
               seeMoreInfo();
                break;
            case R.id.start_work_tv:
                switch (getStartWorkName()) {
                    case START_CHECK:
                        // TODO: 2021/5/18 单位详情中的开始检查
                        startActivity(new Intent(mContext, StartCheckActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY,((UnitDetailBean.DataBean)getBaseBean())));
                        break;
                    case START_INSPECT:
                        //开始巡检
                        startActivity(new Intent(mContext, StartSecurityInspectActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY,((InspectionSiteBean.DataBean)getBaseBean())));
                        break;
                    case START_VISIT:
                        startActivity(new Intent(mContext, StartVisitActivity.class).putExtra(BaseInspectionActivity.PARCELABLE_KEY,((ImportantorBean.DataBean)getBaseBean())));
                        break;
                    default:
                        break;
                }

                break;
        }
    }


    protected abstract void navigationLogic();

    protected abstract void seeMoreInfo();

}
