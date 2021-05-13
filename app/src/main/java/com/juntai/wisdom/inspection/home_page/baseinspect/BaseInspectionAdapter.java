package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.inspection.bean.HeadPicBean;
import com.juntai.wisdom.inspection.bean.ImportantTagBean;
import com.juntai.wisdom.inspection.bean.ItemFragmentBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.utils.StringTools;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/22 11:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/22 11:11
 */
public class BaseInspectionAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    private boolean isDetail = false;//是否是详情模式
    private FragmentManager mFragmentManager;
    private OnCheckEdittextValueFormatCallBack checkEdittextValueFormatCallBack;


    public void setCheckEdittextValueFormatCallBack(OnCheckEdittextValueFormatCallBack checkEdittextValueFormatCallBack) {
        this.checkEdittextValueFormatCallBack = checkEdittextValueFormatCallBack;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseInspectionAdapter(List<MultipleItem> data, boolean isDetail, FragmentManager mFragmentManager) {
        super(data);
        addItemType(MultipleItem.ITEM_HEAD_PIC, R.layout.item_layout_type_head_pic);
        addItemType(MultipleItem.ITEM_TITILE_BIG, R.layout.item_layout_type_title_big);
        addItemType(MultipleItem.ITEM_TITILE_SMALL, R.layout.item_layout_type_title_small);
        addItemType(MultipleItem.ITEM_EDIT, R.layout.item_layout_type_edit);
        addItemType(MultipleItem.ITEM_EDIT2, R.layout.item_layout_type_edit2);
        addItemType(MultipleItem.ITEM_SELECT, R.layout.item_layout_type_select);
        addItemType(MultipleItem.ITEM_FRAGMENT, R.layout.item_layout_type_fragment);
        addItemType(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, R.layout.item_layout_type_recyclerview);
        addItemType(MultipleItem.ITEM_LOCATION, R.layout.item_layout_location);
        this.isDetail = isDetail;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {

        switch (item.getItemType()) {
            case MultipleItem.ITEM_HEAD_PIC:
                if (!isDetail) {
                    helper.addOnClickListener(R.id.form_head_pic_iv);
                }
                HeadPicBean headPicBean = (HeadPicBean) item.getObject();
                ImageView headIv = helper.getView(R.id.form_head_pic_iv);
                String headPicPath = headPicBean.getPicPath();
                if (!TextUtils.isEmpty(headPicPath)) {
                    ImageLoadUtil.loadImageNoCache(mContext, headPicPath, headIv);
                } else {
                    ImageLoadUtil.loadImage(mContext, R.mipmap.two_inch_pic, headIv);
                }
                break;
            case MultipleItem.ITEM_TITILE_BIG:
                helper.setText(R.id.item_big_title_tv, (String) item.getObject());
                break;
            case MultipleItem.ITEM_TITILE_SMALL:
                ImportantTagBean importantTagBean = (ImportantTagBean) item.getObject();
                helper.setGone(R.id.important_tag_tv, importantTagBean.isImportant());
                helper.setText(R.id.item_small_title_tv, importantTagBean.getTitleName());
                break;
            case MultipleItem.ITEM_EDIT:
                TextKeyValueBean textValueEditBean = (TextKeyValueBean) item.getObject();
                EditText editText = helper.getView(R.id.edit_value_et);
                if (isDetail) {
                    editText.setClickable(false);
                    editText.setFocusable(false);
                } else {
                    editText.setClickable(true);
                    editText.setFocusable(true);
                }
                int editType = textValueEditBean.getType();
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) editText.getLayoutParams();
                if (0 == editType) {
                    lp.height = DisplayUtil.dp2px(mContext, 32);
                    editText.setGravity(Gravity.CENTER_VERTICAL);
                    editText.setSingleLine(true);
                } else {
                    lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    editText.setMinimumHeight(DisplayUtil.dp2px(mContext, 100));
                    editText.setGravity(Gravity.TOP);
                    editText.setSingleLine(false);
                }
                editText.setLayoutParams(lp);
                editText.setTag(textValueEditBean);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                        String str = s.toString().trim();
                        editBean.setValue(str);
                    }
                });
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                        if (!hasFocus) {
                            if (checkEdittextValueFormatCallBack != null) {
                                checkEdittextValueFormatCallBack.checkEdittextValueFormat(editBean);
                            }
                        }
                    }
                });
                editText.setHint(textValueEditBean.getHint());
                editText.setText(textValueEditBean.getValue());

                break;
            case MultipleItem.ITEM_EDIT2:
                TextKeyValueBean textValueEditBean2 = (TextKeyValueBean) item.getObject();
                EditText editText2 = helper.getView(R.id.value_et);
                initEdittextFocuseStatus(editText2);
                TextView textView2 = helper.getView(R.id.key_tv);
                editText2.setTag(textValueEditBean2);
                addTextChange(editText2);
                editText2.setText(textValueEditBean2.getValue());
                String editKeyTv = textValueEditBean2.getKey();

                if (editKeyTv.contains("F") || editKeyTv.contains("C")) {
                    //主要涉及聋儿童康复 与残疾儿童关系的地方 用于区分
                    editKeyTv = editKeyTv.substring(1, editKeyTv.length());
                }
                textView2.setText(editKeyTv);
                //                if (BusinessContract.TABLE_TITLE_CONTACT_MODE.equals(editKeyTv) || BusinessContract
                //                .TABLE_TITLE_PHONE.equals(editKeyTv)) {
                //                    editText2.setInputType(InputType.TYPE_CLASS_NUMBER);
                //                }
                break;
            case MultipleItem.ITEM_SELECT:
                TextKeyValueBean textValueSelectBean = (TextKeyValueBean) item.getObject();
                TextView textViewTv = helper.getView(R.id.select_value_tv);
                if (!isDetail) {
                    helper.addOnClickListener(R.id.select_value_tv);
                    helper.addOnClickListener(R.id.tool_pic_iv);
                }
                textViewTv.setTag(textValueSelectBean);
                TextKeyValueBean selectBean = (TextKeyValueBean) textViewTv.getTag();
                textViewTv.setHint(selectBean.getHint());
                textViewTv.setText(selectBean.getValue());
                break;
            case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                //recycleview

                List<TextKeyValueBean> arrays = (List<TextKeyValueBean>) item.getObject();
                RecyclerView recyclerView = helper.getView(R.id.item_normal_rv);
                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL
                        , false);
                TextKeyValueAdapter adapter = new TextKeyValueAdapter(R.layout.text_key_value_item);
                adapter.setNewData(arrays);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
                break;
            case MultipleItem.ITEM_LOCATION:
                LocationBean locationBean = (LocationBean) item.getObject();
                helper.addOnClickListener(R.id.location_ll);
                if (!TextUtils.isEmpty(locationBean.getAddress())) {
                    helper.setText(R.id.location_tv, locationBean.getAddress());
                }

                break;
            case MultipleItem.ITEM_FRAGMENT:
                ItemFragmentBean fragmentBean = (ItemFragmentBean) item.getObject();
                //上传材料时 多选照片
                SelectPhotosFragment fragment = (SelectPhotosFragment) mFragmentManager.findFragmentById(R.id.photo_fg);
                fragment.setObject(fragmentBean);
                fragment.setSpanCount(fragmentBean.getmSpanCount())
                        .setPhotoDelateable(fragmentBean.isDeleteable()).setMaxCount(fragmentBean.getmMaxCount())
                        .setShowTag(fragmentBean.isShowTag()).setOnPicLoadSuccessCallBack(new SelectPhotosFragment.OnPicLoadSuccessCallBack() {
                    @Override
                    public void loadSuccess(List<String> icons) {
                        ItemFragmentBean picBean = (ItemFragmentBean) fragment.getObject();
                        picBean.setFragmentPics(icons);
                    }
                });
                if (fragmentBean.getFragmentPics().size()>0) {
                    fragment.setIcons(fragmentBean.getFragmentPics());
                }
                break;
            default:
                break;
        }
    }

    private void initEdittextFocuseStatus(EditText editText) {
        if (isDetail) {
            editText.setClickable(false);
            editText.setFocusable(false);
        } else {
            editText.setClickable(true);
            editText.setFocusable(true);
        }
    }


    /**
     * 配置最大长度
     *
     * @param editText
     * @param i2
     */
    private void setMaxLength(EditText editText, int i2) {
        //手动设置maxLength为18
        InputFilter[] filters = {new InputFilter.LengthFilter(i2)};
        editText.setFilters(filters);
    }

    private void addTextChange(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                switch (editText.getId()) {
                    case R.id.value_et:
                        TextKeyValueBean editBean = (TextKeyValueBean) editText.getTag();
                        editBean.setValue(s.toString().trim());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 配置view的margin属性
     */
    private void setMargin(View view, int left, int top, int right, int bottom) {
        left = DisplayUtil.dp2px(mContext, left);
        top = DisplayUtil.dp2px(mContext, top);
        right = DisplayUtil.dp2px(mContext, right);
        bottom = DisplayUtil.dp2px(mContext, bottom);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(view.getLayoutParams());
        layoutParams.setMargins(left, top, right, bottom);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 控件失去焦点后  检测edittext控件输入内容的格式
     */
    interface OnCheckEdittextValueFormatCallBack {
        void checkEdittextValueFormat(TextKeyValueBean keyValueBean);
    }
}
