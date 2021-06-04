package com.juntai.wisdom.inspection.home_page.search;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.AppHttpPath;
import com.juntai.wisdom.inspection.base.BaseAppActivity;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.search.SearchBean;
import com.juntai.wisdom.inspection.bean.search.SearchMoreBean;
import com.juntai.wisdom.inspection.bean.search.SearchResultBean;
import com.juntai.wisdom.inspection.home_page.HomePageContract;
import com.juntai.wisdom.inspection.home_page.HomePagePresent;
import com.juntai.wisdom.inspection.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.wisdom.inspection.home_page.firecheck.UnitInfoActivity;
import com.juntai.wisdom.inspection.home_page.importantor.ImportantorInfoActivity;
import com.juntai.wisdom.inspection.home_page.securityInspect.SecurityInspectionSiteInfoActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  搜索
 * @date 2021/6/4 15:11
 */
public class SearchActivity extends BaseAppActivity<HomePagePresent> implements HomePageContract.IHomePageView,
        View.OnClickListener, SearchFragment.OnSearchCallBack {

    private SearchFragment searchTopFragment;
    //tab条目中的内容
    // 0：监控1：分组
    public final static int INFO_TYPE_CAMERA = 1;//监控
    public final static int INFO_TYPE_GROUP = 2;//group
    private RecyclerView mSearchInfoRv;
    private SmartRefreshLayout mSearchRfl;
    private SearchAdapter searchAdapter;
    private String textContent;
    private int limit = 10;//默认条数
    private int insertPosition;//插入的位置

    @Override
    public int getLayoutView() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        setTitleName("搜索");
        searchTopFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_serach_top);
        mSearchInfoRv = (RecyclerView) findViewById(R.id.search_info_rv);
        mSearchRfl = (SmartRefreshLayout) findViewById(R.id.search_srl);
        mSearchRfl.setEnableLoadMore(false);
        searchAdapter = new SearchAdapter(null);
        initRecyclerview(mSearchInfoRv, searchAdapter, LinearLayoutManager.VERTICAL);
    }

    @Override
    public void initData() {
        mSearchRfl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.search(getBaseBuilder().add("keyword", textContent).build(),
                        AppHttpPath.SEARCH);
            }
        });
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem multipleItem = (MultipleItem) adapter.getItem(position);
                switch (multipleItem.getItemType()) {
                    case MultipleItem.ITEM_TITLE:
                        break;
                    case MultipleItem.ITEM_CONTENT:
                        SearchBean.DataBean.ListBean databean = (SearchBean.DataBean
                                .ListBean) multipleItem.getObject();
                        int id = databean.getId();
                        switch (databean.getResultType()) {
                            case 1:
                                //消防检查
                                startActivity(new Intent(mContext, UnitInfoActivity.class).putExtra(BaseInspectionInfoActivity.BASE_ID, id));
                                break;
                            case 2:
                                //重点人员
                                startActivity(new Intent(mContext, ImportantorInfoActivity.class).putExtra(BaseInspectionInfoActivity.BASE_ID, id));
                                break;
                            case 3:
                                //治安巡检
                                startActivity(new Intent(mContext, SecurityInspectionSiteInfoActivity.class).putExtra(BaseInspectionInfoActivity.BASE_ID,
                                       id));
                                break;
                            default:
                                break;
                        }
                        break;
                    case MultipleItem.ITEM_LOAD_MORE:
                        SearchMoreBean searchMoreBean = (SearchMoreBean) multipleItem.getObject();
                        String loadMoreMsg = searchMoreBean.getMsg();
                        if (getString(R.string.load_more).equals(loadMoreMsg)) {
                            insertPosition = position;
                            mPresenter.searchMore(getBaseBuilder().add("keyword", textContent).add("typeId",
                                    String.valueOf(searchMoreBean.getTypeId()))
                                            .add("startRow", String.valueOf(searchMoreBean.getCurrentOffset())).add(
                                            "pageSize", String.valueOf(limit)).build(),
                                    AppHttpPath.SEARCH_MORE);
                        }
                        break;
                    default:
                        break;
                }


            }
        });
        searchAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem multipleItem = (MultipleItem) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.item_navigation_tv:
                        SearchBean.DataBean.ListBean databean = (SearchBean.DataBean
                                .ListBean) multipleItem.getObject();
                        if (TextUtils.isEmpty(databean.getLatitude()) || TextUtils.isEmpty(databean.getLongitude())) {
                            ToastUtils.toast(mContext, "无法获取经纬度，不能导航");
                            return;
                        }
                        navigationLogic(new LatLng(Double.parseDouble(databean.getLatitude()),
                                Double.parseDouble(databean.getLongitude())), databean.getGpsAddress());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }


    @Override
    public void onSuccess(String tag, Object o) {
        mSearchRfl.finishRefresh();
        switch (tag) {
            case AppHttpPath.SEARCH:
                SearchBean searchBean = (SearchBean) o;
                if (searchBean != null) {
                    List<SearchBean.DataBean> item_des = searchBean.getData();
                    List<MultipleItem> multipleItems = new ArrayList<>();
                    if (item_des != null) {
                        for (SearchBean.DataBean item_de : item_des) {
                            List<SearchBean.DataBean.ListBean> itemMsgs = item_de.getList();
                            if (itemMsgs != null && itemMsgs.size() > 0) {
                                int resultType = item_de.getTypeId();
                                multipleItems.add(new MultipleItem(MultipleItem.ITEM_TITLE, item_de));
                                for (SearchBean.DataBean.ListBean itemMsg : itemMsgs) {
                                    itemMsg.setResultType(resultType);
                                    multipleItems.add(new MultipleItem(MultipleItem.ITEM_CONTENT, itemMsg));
                                }
                                if (itemMsgs.size() < 3) {
                                    //没有更多数据了
                                    multipleItems.add(new MultipleItem(MultipleItem.ITEM_LOAD_MORE,
                                            new SearchMoreBean(item_de.getTypeId(), 0,
                                                    getString(R.string.load_more_no_data))));
                                } else {
                                    if (item_de.getCount() != 3) {
                                        //总共3条数据
                                        multipleItems.add(new MultipleItem(MultipleItem.ITEM_LOAD_MORE,
                                                new SearchMoreBean(item_de.getTypeId(), 3,
                                                        getString(R.string.load_more))));
                                    }

                                }
                            }

                        }
                    }
                    if (multipleItems.size() < 1) {
                        searchAdapter.setEmptyView(getAdapterEmptyView("很遗憾，没搜出相关信息", 0));
                    }
                    searchAdapter.setNewData(multipleItems);

                }
                break;
            case AppHttpPath.SEARCH_MORE:
                SearchResultBean loadmoreBean = (SearchResultBean) o;
                if (loadmoreBean != null) {
                    SearchResultBean.DataBean dataBean = loadmoreBean.getData();
                    if (dataBean != null) {
                        List<SearchBean.DataBean.ListBean> list = dataBean.getDatas();
                        List<MultipleItem> multipleItems = new ArrayList<>();
                        for (SearchBean.DataBean.ListBean listBean : list) {
                            listBean.setResultType(dataBean.getTotal());
                            multipleItems.add(new MultipleItem(MultipleItem.ITEM_CONTENT, listBean));
                        }
                        if (list.size() < limit) {
                            //没有更多数据了
                            multipleItems.add(new MultipleItem(MultipleItem.ITEM_LOAD_MORE,
                                    new SearchMoreBean(dataBean.getTotal(), 0, getString(R.string.load_more_no_data))));
                        } else {
                            multipleItems.add(new MultipleItem(MultipleItem.ITEM_LOAD_MORE,
                                    new SearchMoreBean(dataBean.getTotal(), dataBean.getPageCount(),
                                            getString(R.string.load_more))));
                        }
                        searchAdapter.addData(insertPosition, multipleItems);
                        searchAdapter.remove(insertPosition + list.size() + 1);
                    }
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSearch(String textContent) {
        this.textContent = textContent;
        mPresenter.search(getBaseBuilder().add("keyword", textContent).build(),
                AppHttpPath.SEARCH);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        textContent = null;
    }

}
