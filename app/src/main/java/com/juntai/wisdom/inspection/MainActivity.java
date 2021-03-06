package com.juntai.wisdom.inspection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.juntai.disabled.basecomponent.utils.ActionConfig;
import com.juntai.disabled.basecomponent.utils.DialogUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.service.LocateAndUpload;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.base.customview.CustomViewPager;
import com.juntai.wisdom.inspection.base.customview.MainPagerAdapter;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.entrance.LoginActivity;
import com.juntai.wisdom.inspection.home_page.HomePageFragment;
import com.juntai.wisdom.inspection.home_page.add.unit.AddUnitActivity;
import com.juntai.wisdom.inspection.home_page.add.importantor.AddImportantorActivity;
import com.juntai.wisdom.inspection.home_page.add.inspectionsite.AddInspectSiteActivity;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitInfoActivity;
import com.juntai.wisdom.inspection.home_page.securityInspect.SecurityInspectionSiteInfoActivity;
import com.juntai.wisdom.inspection.mine.MyCenterFragment;
import com.juntai.wisdom.inspection.utils.AppUtils;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAppActivity<MainPagePresent> implements ViewPager.OnPageChangeListener,
        View.OnClickListener, MainPageContract.IMainPageView {
    private MainPagerAdapter adapter;
    private LinearLayout mainLayout;
    private CustomViewPager mainViewpager;
    final static Handler mHandler = new Handler();
    List<LocationBean> cacheDatas = new ArrayList<>();//

    private TabLayout mainTablayout;
    private String[] title = new String[]{"??????", "??????", "??????"};
    private int[] tabDrawables = new int[]{R.drawable.home_index, R.drawable.home_add, R.drawable.home_mine};
    private SparseArray<Fragment> mFragments = new SparseArray<>();
    //
    CGBroadcastReceiver broadcastReceiver = new CGBroadcastReceiver();

    PopupWindow popupWindow;


    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mImmersionBar.reset().fitsSystemWindows(false).transparentStatusBar().init();
        mainViewpager = findViewById(R.id.main_viewpager);
        mainTablayout = findViewById(R.id.main_tablayout);
        mainLayout = findViewById(R.id.main_layout);
        mainViewpager.setScanScroll(false);
        mFragments.append(0, new HomePageFragment());//
        //        mFragments.append(1, new HandlerBusinessFragment());//
        mFragments.append(1, new MyCenterFragment());//??????
        //
        getToolbar().setVisibility(View.GONE);
        mBaseRootCol.setFitsSystemWindows(false);
        mainViewpager.setOffscreenPageLimit(4);
        initTab();
        //????????????
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionConfig.BROAD_LOGIN);
        intentFilter.addAction(ActionConfig.BROAD_CASE_DETAILS);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void initData() {
        update(false);
    }


    public void initTab() {
        adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(), title, tabDrawables,
                mFragments);
        mainViewpager.setAdapter(adapter);
        mainViewpager.setOffscreenPageLimit(title.length);
        /*viewpager???????????????????????????????????????*/
        mainViewpager.addOnPageChangeListener(this);
        for (int i = 0; i < title.length; i++) {
            TabLayout.Tab tab = mainTablayout.newTab();
            if (tab != null) {
                if (i == title.length - 1) {
                    tab.setCustomView(adapter.getTabView(i, true));
                } else {
                    tab.setCustomView(adapter.getTabView(i, false));
                }
                mainTablayout.addTab(tab);
            }
        }

        mainTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    //????????????
                    add(mainTablayout);
                } else if (tab.getPosition() == 0) {
                    mImmersionBar.reset().fitsSystemWindows(false).transparentStatusBar().init();
                    mainViewpager.setCurrentItem(tab.getPosition(), false);
                } else {
                    mImmersionBar.reset().fitsSystemWindows(false).statusBarDarkFont(true).init();
                    mainViewpager.setCurrentItem(tab.getPosition(), false);

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    //????????????
                    add(mainTablayout);
                }
            }
        });

        //        mainTablayout.newTab();
        /*viewpager?????????????????????*/
        mainViewpager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * ??????
     *
     * @param view
     */
    public void add(View view) {
        //        if (!MyApp.isLogin()) {
        //            MyApp.goLogin();
        //            return;
        //        }
        View viewPop = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.pop_add, null);
        Group   unitGroup = viewPop.findViewById(R.id.add_unit_g);
        Group   importantorGroup = viewPop.findViewById(R.id.add_importantor_g);
        if (2== UserInfoManager.getPostId()) {
            //??????????????????????????????????????????????????????
            unitGroup.setVisibility(View.GONE);
            importantorGroup.setVisibility(View.GONE);
        }else {
            unitGroup.setVisibility(View.VISIBLE);
            importantorGroup.setVisibility(View.VISIBLE);
        }
        //????????????
        view.setBackgroundColor(Color.WHITE);
        TextView shadowTv = viewPop.findViewById(R.id.shadow_tv);
        shadowTv.setOnClickListener(this);
        popupWindow = new PopupWindow(viewPop, ViewGroup.LayoutParams.MATCH_PARENT,
                MyApp.HEIGHT - mainTablayout.getLayoutParams().height - MyApp.statusBarH, true);
        //???????????????????????????
        popupWindow.showAtLocation(mainTablayout, Gravity.TOP, 0, 0);
        viewPop.findViewById(R.id.add_company_iv).setOnClickListener(v -> {
            startActivity(new Intent(mContext, AddUnitActivity.class));
            popupWindow.dismiss();
        });
        viewPop.findViewById(R.id.add_importantor_iv).setOnClickListener(v -> {
            startActivity(new Intent(mContext, AddImportantorActivity.class));
            popupWindow.dismiss();
        });
        viewPop.findViewById(R.id.add_site_iv).setOnClickListener(v -> {
            startActivity(new Intent(mContext, AddInspectSiteActivity.class));
            popupWindow.dismiss();
        });
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case MainPageContract.UPLOAD_HISTORY:
                if (cacheDatas != null) {
                    for (LocationBean locationBean : cacheDatas) {
                        MyApp.getDaoSession().getLocationBeanDao().delete(locationBean);
                    }
                }
                break;
            default:
                break;
        }
    }

    AlertDialog alertDialog;
    int id22;
    String content;
    int type;//???????????????1???????????????3????????????

    @Override
    public void onClick(View v) {

    }


    /**
     * ????????????
     */
    public class CGBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ActionConfig.BROAD_LOGIN.equals(intent.getAction())) {
                //????????????????????????
                String error = intent.getStringExtra("error");
                ToastUtils.info(MyApp.app, error);
                //                SPTools.saveString(mContext, "login", "");
                mHandler.removeCallbacks(runnable);
                mHandler.removeCallbacksAndMessages(null);
                startActivity(new Intent(mContext, LoginActivity.class));
                //????????????
                //                EventManager.sendStringMsg(ActionConfig.UN_READ_MESSAG_TAG);
            }
        }
    }

    @Override
    protected MainPagePresent createPresenter() {
        return new MainPagePresent();
    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {

    }

    @Override
    public boolean requestLocation() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //        if (MyApp.getUser().getData().getSettleStatus() == 2){
        //?????????????????????
        //            mHandler.postDelayed(runnable, 1000 * 62);//??????60???
        //        }
    }

    @Override
    protected void onDestroy() {
        Log.e("EEEEEEEEEE", " = MainActivity  onDestroy");
        stopService(new Intent(MainActivity.this, LocateAndUpload.class));
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        mHandler.removeCallbacks(runnable);
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        setAlertDialogHeightWidth( DialogUtil.getDialog(mContext)
                .setMessage("?????????????????????")
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApp.app.isFinish = true;
                        finish();
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //??????home???,????????????
                        //sendBroadcast(new Intent().setAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
                        // .putExtra("reason","homekey"));
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                    }
                }).show(),-1,0);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AppUtils.QR_SCAN_NOMAL && resultCode == RESULT_OK) {
            if (data != null) {
                String result = data.getStringExtra("result");
                Intent intent = new Intent();
                int id = 0;
                String str = result.substring(result.lastIndexOf("=") + 1, result.length());
                if ("null".equals(str)) {
                    ToastUtils.error(mContext,"???????????????");
                    return;
                }
                if (!TextUtils.isEmpty(result) && result.contains("=")) {
                    id = Integer.parseInt(str);
                    intent.putExtra(BaseInspectionInfoActivity.BASE_ID, id);
                }
                if (result.contains("xiaofang")) {
                    if (2!= UserInfoManager.getPostId()) {
                        intent.setClass(mContext, UnitInfoActivity.class);
                        startActivity(intent);
                    }else {
                    ToastUtils.warning(mContext,"?????????????????????????????????????????????");
                    }
                } else if (result.contains("zhian")) {
                    //????????????????????????
                    intent.setClass(mContext, SecurityInspectionSiteInfoActivity.class);
                    startActivity(intent);
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * ???????????????????????????
     */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //do something
            List<LocationBean> datas = null;
            try {
                datas = MyApp.getDaoSession().getLocationBeanDao().loadAll();
            } catch (Exception e) {
                e.printStackTrace();
                datas = new ArrayList<>();
            }
            if (datas.size() > 0 && datas.size() < 30) {
                cacheDatas.clear();
                cacheDatas.addAll(datas);
                mPresenter.uploadHistory(new Gson().toJson(datas), MainPageContract.UPLOAD_HISTORY);
            } else {
                MyApp.getDaoSession().getLocationBeanDao().deleteAll();
            }
            //??????62s????????????run??????
            mHandler.postDelayed(runnable, 1000 * 62);
        }
    };
}
