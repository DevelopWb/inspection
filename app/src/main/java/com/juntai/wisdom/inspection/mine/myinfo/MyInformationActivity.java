package com.juntai.wisdom.inspection.mine.myinfo;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.BaseRecyclerviewActivity;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.UserBean;
import com.juntai.wisdom.inspection.home_page.baseinspect.HeadCropActivity;
import com.juntai.wisdom.inspection.mine.MyCenterContract;
import com.juntai.wisdom.inspection.mine.MyCenterPresent;
import com.juntai.wisdom.inspection.utils.AppUtils;
import com.juntai.wisdom.inspection.utils.HawkProperty;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;
import com.orhanobut.hawk.Hawk;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  我的信息
 * @date 2021/6/1 16:36
 */
public class MyInformationActivity extends BaseRecyclerviewActivity<MyCenterPresent> implements MyCenterContract.ICenterView {


    private ImageView imageView;
    private TextView nicknameTv;

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }


    @Override
    protected void freshlayoutOnLoadMore() {

    }
    @Override
    protected void freshlayoutOnRefresh() {

    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        return new MyInfoAdapter(R.layout.item_myinfo);
    }

    /**
     * 添加头部
     */
    public View getHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.include_myinfo_head, null);
        LinearLayout headLayout = view.findViewById(R.id.myinfo_headLayout);
        imageView = view.findViewById(R.id.myinfo_headimage);
        nicknameTv = view.findViewById(R.id.myinfo_nickname);
        headLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseImage(0,MyInformationActivity.this,1);
            }
        });
        return view;
    }

    @Override
    protected void selectedPicsAndEmpressed(List<String> icons) {
        super.selectedPicsAndEmpressed(icons);
        if (icons.size() > 0) {
            String picPath = icons.get(0);
            ImageLoadUtil.loadCirImgNoCrash(getApplicationContext(), picPath,
                    imageView,
                    R.mipmap.default_user_head_icon, R.mipmap.default_user_head_icon);
            //调用修改头像的接口
            mPresenter.updateHeadPic(mPresenter.getPublishMultipartBody().addFormDataPart("file", "file",
                    RequestBody.create(MediaType.parse("file"),
                            new File(picPath))).build(),"");
        }
    }


    @Override
    public void initData() {
        setTitleName("我的信息");
        mSmartrefreshlayout.setEnableLoadMore(false);
        mSmartrefreshlayout.setEnableRefresh(false);
        adapter.setHeaderView(getHeadView());
        UserBean bean = Hawk.get(AppUtils.SP_KEY_USER);
        if (bean != null) {
            UserBean.DataBean userBean = bean.getData();
            List<TextKeyValueBean> beanList = new ArrayList<>();
            beanList.add(new TextKeyValueBean("账号", userBean.getAccount()));
            beanList.add(new TextKeyValueBean("昵称", userBean.getNickname()));
            beanList.add(new TextKeyValueBean("部门", userBean.getDepartmentName()));
            beanList.add(new TextKeyValueBean("职务", userBean.getPostName()));
            adapter.setNewData(beanList);
            nicknameTv.setText(userBean.getNickname());
            ImageLoadUtil.loadCirImgNoCrash(getApplicationContext(), UrlFormatUtil.getImageOriginalUrl(userBean.getHeadPortrait()),
                    imageView,
                    R.mipmap.default_user_head_icon, R.mipmap.default_user_head_icon);
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        ToastUtils.toast(mContext,"更改成功");
    }
}
