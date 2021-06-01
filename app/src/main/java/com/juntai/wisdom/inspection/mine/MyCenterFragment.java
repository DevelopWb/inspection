package com.juntai.wisdom.inspection.mine;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.disabled.basecomponent.utils.RxTask;
import com.juntai.disabled.basecomponent.utils.SPTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.BaseAppFragment;
import com.juntai.wisdom.inspection.bean.MyMenuBean;
import com.juntai.wisdom.inspection.bean.UserBean;
import com.juntai.wisdom.inspection.mine.myWorkerRecords.MyWorkerRecordActivity;
import com.juntai.wisdom.inspection.mine.myinfo.MyInformationActivity;
import com.juntai.wisdom.inspection.utils.AppUtils;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;
import com.juntai.wisdom.inspection.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/4/17 16:12
 */
public class MyCenterFragment extends BaseAppFragment<MyCenterPresent> implements MyCenterContract.ICenterView,
        View.OnClickListener {

    private UserBean userBean;
    MyMenuAdapter myMenuAdapter;
    private String headUrl = "";

    private TextView mStatusTopTitle;
    private ImageView mHeadImage;
    private TextView mNickname;
    /**
     * 18763739973
     */
    private TextView mTelNumber;
    private RecyclerView mMenuRecycler;
    /**
     * 退出账号
     */
    private TextView mLoginOut;
    private int imUnReadCount;
    private ConstraintLayout mInfoCl;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_center;
    }

    @Override
    protected void initView() {
        mStatusTopTitle = getView(R.id.status_top_title);
        mHeadImage = getView(R.id.headImage);
        mInfoCl = getView(R.id.my_info_cl);
        mInfoCl.setOnClickListener(this);
        mNickname = getView(R.id.nickname);
        mNickname.setAlpha(0.3f);
        mTelNumber = getView(R.id.tel_number);
        mTelNumber.setVisibility(View.GONE);
        mMenuRecycler = getView(R.id.menu_recycler);
        mLoginOut = getView(R.id.login_out);
        mLoginOut.setOnClickListener(this);
        myMenuAdapter = new MyMenuAdapter(R.layout.my_center_menu_item, mPresenter.getMenuBeans());
        getBaseActivity().initRecyclerview(mMenuRecycler, myMenuAdapter, LinearLayoutManager.VERTICAL);
        mMenuRecycler.setAdapter(myMenuAdapter);
        mStatusTopTitle.setText("个人中心");

        myMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //                if (!MyApp.isLogin()){
                //                    MyApp.goLogin();
                //                    return;
                //                }
                MyMenuBean myMenuBean = (MyMenuBean) adapter.getData().get(position);
                switch (myMenuBean.getName()) {
                    case MyCenterContract.MY_WORK_RECORD:
                        // 我的工作记录
                        startActivity(new Intent(mContext, MyWorkerRecordActivity.class));
                        break;
                    case MyCenterContract.MY_MSG:
                        // TODO: 2021/6/1 我的消息
                        //                        startActivity(new Intent(mContext,));
                        break;
                    case MyCenterContract.MY_MODIFY_PWD:
                        // 2021/6/1 修改密码
                        startActivity(new Intent(mContext, ModifyPwdActivity.class));
                        break;
                    case MyCenterContract.MY_ABOUT_US:
                        // 关于我们
                        startActivity(new Intent(mContext, AboutActivity.class));
                        break;
                    case MyCenterContract.MY_CLEAR_CACHE:
                        // 清理缓存
                        DialogUtil.getMessageDialog(mContext, "将清理掉应用本地的图片和视频缓存文件",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        RxScheduler.doTask(getBaseAppActivity(), new RxTask<String>() {
                                            @Override
                                            public String doOnIoThread() {
                                                FileCacheUtils.clearAll(mContext.getApplicationContext());
                                                return "清理成功";
                                            }

                                            @Override
                                            public void doOnUIThread(String s) {
                                                ToastUtils.success(mContext.getApplicationContext(), s);
                                            }
                                        });
                                    }
                                }).show();
                        break;
                    case MyCenterContract.MY_UPDATE:
                        //检查更新
                        getBaseAppActivity().update(true);
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.initList();
        mHeadImage.setImageResource(R.mipmap.default_user_head_icon);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserInfoManager.isLogin()) {
            mLoginOut.setVisibility(View.VISIBLE);
            mPresenter.getUserInfo(getBaseAppActivity().getBaseBuilder().build(), AppHttpPath.GET_USER_INFO);
        } else {
            mLoginOut.setVisibility(View.GONE);
        }
    }


    @Override
    protected void lazyLoad() {
    }

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }


    @Override
    public void onClick(View v) {
        if (!UserInfoManager.isLogin()) {
            getBaseAppActivity().goLogin();
            return;
        }
        switch (v.getId()) {
            case R.id.my_info_cl:
                //我的信息
                startActivity(new Intent(mContext, MyInformationActivity.class));

                break;
            case R.id.login_out:
                //退出登录
                DialogUtil.getMessageDialog(mContext, "是否退出登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.loginOut(getBaseAppActivity().getBaseBuilder().build(), AppHttpPath.LOGIN_OUT);
                        dialog.dismiss();
                    }
                }).show();
                break;
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPath.GET_USER_INFO:
                userBean = (UserBean) o;
                UserBean.DataBean dataBean = userBean.getData();
                if (dataBean != null) {
                    mLoginOut.setVisibility(View.VISIBLE);
                    mNickname.setText(dataBean.getNickname());
                    mNickname.setAlpha(0.8f);
                    mTelNumber.setText(userBean.getData().getAccount());
                    mTelNumber.setVisibility(View.VISIBLE);
                    if (!headUrl.equals(userBean.getData().getHeadPortrait())) {
                        headUrl = userBean.getData().getHeadPortrait();
                        ImageLoadUtil.loadCirImgNoCrash(mContext.getApplicationContext(),
                                UrlFormatUtil.getImageOriginalUrl(headUrl), mHeadImage,
                                R.mipmap.default_user_head_icon, R.mipmap.default_user_head_icon);
                    }
                    Hawk.put(AppUtils.SP_KEY_USER, userBean);
                }
                break;
            case AppHttpPath.LOGIN_OUT:
                ToastUtils.success(mContext, "退出成功");
                SPTools.saveString(mContext, "login", "");
                UserInfoManager.clearUserData();//清理数据
                //重置界面
                mNickname.setText("点击登录");
                mNickname.setAlpha(0.3f);
                mTelNumber.setVisibility(View.GONE);
                mLoginOut.setVisibility(View.GONE);
                mPresenter.initList();
                headUrl = "";
                mHeadImage.setImageResource(R.mipmap.default_user_head_icon);
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag, Object o) {
        ToastUtils.error(mContext, String.valueOf(o));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
