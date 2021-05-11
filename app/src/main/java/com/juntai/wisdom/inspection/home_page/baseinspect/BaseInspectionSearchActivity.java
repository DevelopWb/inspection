package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.base.customview.CustomViewPager;
import com.juntai.wisdom.inspection.base.customview.MainPagerAdapter;
import com.juntai.wisdom.inspection.bean.IdNameBean;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/29 15:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/29 15:18
 */
public abstract class BaseInspectionSearchActivity extends BaseAppActivity<BaseInspectPresent> implements BaseInspectContract.IInspectView, View.OnClickListener {
    private SearchView mInspectSearchSv;
    /**
     * 盗窃
     */
    private TextView mSearchTypeTv;
    private LinearLayout mSearchTypeLl;
    private TabLayout mInspectTb;
    private CustomViewPager mInspectViewPager;
    private List<IdNameBean.DataBean> types = null;
    public int currentTypeId = 0;


    private OnTypeSelectedCallBack typeSelectedCallBack;

    public void  setTypeTypeSelectedCallBack(OnTypeSelectedCallBack typeSelectedCallBack) {
        this.typeSelectedCallBack = typeSelectedCallBack;
    }

    /**
     * 获取搜索的内容
     * @return
     */
    public String getKeyword() {
        return mInspectSearchSv.getQuery().toString().trim();
    }

    @Override
    protected BaseInspectPresent createPresenter() {
        return new BaseInspectPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_base_inspect_search;
    }

    @Override
    public void initView() {
        setTitleName(getTitleName());
        mInspectSearchSv = (SearchView) findViewById(R.id.inspect_search_sv);
        mSearchTypeTv = (TextView) findViewById(R.id.search_type_tv);
        mSearchTypeLl = (LinearLayout) findViewById(R.id.search_type_ll);
        mSearchTypeLl.setOnClickListener(this);
        mInspectTb = (TabLayout) findViewById(R.id.inspect_tb);
        mInspectViewPager = (CustomViewPager) findViewById(R.id.inspect_vp);
        initTab();
        mInspectSearchSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //刷新列表
                if (typeSelectedCallBack != null) {
                    typeSelectedCallBack.typeSelected();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void initTab() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(),
                getTabTitles(),
                getFragments());
        mInspectViewPager.setAdapter(adapter);
        mInspectViewPager.setOffscreenPageLimit(getTabTitles().length);
        /*viewpager切换监听，包含滑动点击两种*/
        mInspectViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        mInspectTb.setupWithViewPager(mInspectViewPager);
        /**
         * 添加自定义tab布局
         * */
        for (int i = 0; i < mInspectTb.getTabCount(); i++) {
            TabLayout.Tab tab = mInspectTb.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        /*viewpager切换默认第一个*/
        mInspectViewPager.setCurrentItem(0);
    }

    /**
     * 获取tab标签
     *
     * @return
     */
    protected abstract String[] getTabTitles();

    protected abstract SparseArray<Fragment> getFragments();

    protected abstract String getTitleName();


    public void setTypes(List<IdNameBean.DataBean> types) {
        this.types = types;
        if (types != null) {
            IdNameBean.DataBean dataBean = types.get(0);
            mSearchTypeTv.setText(dataBean.getName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.search_type_ll:
                //选择筛选范围
                if (types == null) {
                    return;
                }
                PickerManager.getInstance().showOptionPicker(mContext, types,
                        new PickerManager.OnOptionPickerSelectedListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        IdNameBean.DataBean dataBean = types.get(options1);
                        mSearchTypeTv.setText(dataBean.getName());
                        currentTypeId = dataBean.getId();
                        //刷新列表
                        if (typeSelectedCallBack != null) {
                            typeSelectedCallBack.typeSelected();
                        }
                    }
                });
                break;
        }
    }

    /**
     * 类型被选中后的回调
     */
   public interface OnTypeSelectedCallBack{

       void  typeSelected();

    }
}
