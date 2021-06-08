package com.juntai.wisdom.inspection.mine.myWorkerRecords;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;

import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.base.customview.CustomViewPager;
import com.juntai.wisdom.inspection.base.customview.MainPagerAdapter;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectContract;
import com.juntai.wisdom.inspection.mine.MyCenterContract;
import com.juntai.wisdom.inspection.mine.MyCenterPresent;
import com.juntai.wisdom.inspection.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述  我的工作记录
 * @date 2021/6/1 15:52
 */
public class MyWorkerRecordActivity extends BaseAppActivity<MyCenterPresent> implements MyCenterContract.ICenterView {

    private TabLayout mTabTb;
    private CustomViewPager mViewpageVp;

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.my_worker_record;
    }

    @Override
    public void initView() {
        setTitleName("我的工作记录");
        mTabTb = (TabLayout) findViewById(R.id.tab_tb);
        mViewpageVp = (CustomViewPager) findViewById(R.id.viewpage_vp);
    }

    @Override
    public void initData() {
        initTab();
    }

    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();

        if (2== UserInfoManager.getPostId()) {
            arrays.append(0, WorkRecordFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_INSPECTION));
        }else {
            arrays.append(0, WorkRecordFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL));
            arrays.append(1, WorkRecordFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_FIRE_CHECK));
            arrays.append(2, WorkRecordFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_INSPECTION));
            arrays.append(3, WorkRecordFragment.newInstance(BaseInspectContract.IMPORTANTOR_TAB_IMPORTANTOR));
        }

        return arrays;
    }

    protected String[] getTabTitles() {
        if (2== UserInfoManager.getPostId()) {
            return new String[]{ BaseInspectContract.IMPORTANTOR_TAB_INSPECTION};
        }
        return new String[]{BaseInspectContract.IMPORTANTOR_TAB_TITLE_ALL,
                BaseInspectContract.IMPORTANTOR_TAB_FIRE_CHECK,
                BaseInspectContract.IMPORTANTOR_TAB_INSPECTION, BaseInspectContract.IMPORTANTOR_TAB_IMPORTANTOR};
    }

    private void initTab() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(),
                getTabTitles(),
                getFragments());
        mViewpageVp.setAdapter(adapter);
        mViewpageVp.setOffscreenPageLimit(getTabTitles().length);
        /*viewpager切换监听，包含滑动点击两种*/
        mViewpageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mTabTb.setupWithViewPager(mViewpageVp);
        /**
         * 添加自定义tab布局
         * */
        for (int i = 0; i < mTabTb.getTabCount(); i++) {
            TabLayout.Tab tab = mTabTb.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        /*viewpager切换默认第一个*/
        mViewpageVp.setCurrentItem(0);
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
