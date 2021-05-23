package com.juntai.wisdom.inspection.home_page.baseinspect;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.CalendarUtil;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.federation.R;
import com.juntai.wisdom.inspection.base.selectPics.SelectPhotosFragment;
import com.juntai.wisdom.inspection.bean.HeadPicBean;
import com.juntai.wisdom.inspection.bean.ImportantTagBean;
import com.juntai.wisdom.inspection.bean.ItemFragmentBean;
import com.juntai.wisdom.inspection.bean.ItemSignBean;
import com.juntai.wisdom.inspection.bean.LocationBean;
import com.juntai.wisdom.inspection.bean.MultipleItem;
import com.juntai.wisdom.inspection.bean.RadioBean;
import com.juntai.wisdom.inspection.bean.TextKeyValueBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnQuailityFormBean;
import com.juntai.wisdom.inspection.bean.firecheck.UnQualifiedBean;
import com.juntai.wisdom.inspection.utils.AppUtils;
import com.juntai.wisdom.inspection.utils.StringTools;
import com.juntai.wisdom.inspection.utils.UrlFormatUtil;

import java.util.ArrayList;
import java.util.Date;
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
    private OnRadioCheckedCallBack radioCheckedCallBack;
    private CheckBox question1;
    private CheckBox question2;
    private CheckBox question3;
    private CheckBox question4;
    private CheckBox question5;
    private CheckBox question6;
    private CheckBox question7;
    private CheckBox question8;
    private CheckBox question9;
    private CheckBox question10;
    private CheckBox question11;
    private CheckBox question1Cd1;
    private CheckBox question1Cd2;
    private CheckBox question2Cd1;
    private CheckBox question2Cd2;
    private CheckBox question3Cd1;
    private CheckBox question3Cd2;
    private CheckBox question3Cd3;
    private CheckBox question4Cd1;
    private CheckBox question4Cd2;
    private CheckBox question4Cd3;
    private CheckBox question6Cd1;
    private CheckBox question6Cd2;
    private CheckBox question6Cd3;
    private CheckBox question8Cd1;
    private CheckBox question8Cd2;
    private CheckBox question8Cd3;
    private CheckBox question9Cd1;
    private CheckBox question9Cd2;
    private CheckBox question5Cd1;
    private CheckBox question5Cd2;
    private CheckBox question5Cd3;
    private CheckBox question5Cd4;
    private CheckBox question10Cd1;
    private CheckBox question10Cd2;
    private CheckBox question10Cd3;
    private CheckBox question10Cd4;
    private List<UnQualifiedBean> list;
    public String ITEM_HEAD_TAG1 = "第(";
    public String ITEM_HEAD_TAG2 = "场所于(";
    public String ITEM_FOOT_TAG1 = ")项";
    public String ITEM_FOOT_TAG2 = ")前改正";

    public void setRadioCheckedCallBack(OnRadioCheckedCallBack radioCheckedCallBack) {
        this.radioCheckedCallBack = radioCheckedCallBack;
    }

    public void setCheckEdittextValueFormatCallBack(OnCheckEdittextValueFormatCallBack checkEdittextValueFormatCallBack) {
        this.checkEdittextValueFormatCallBack = checkEdittextValueFormatCallBack;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
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
        addItemType(MultipleItem.ITEM_RADIO, R.layout.item_layout_type_radio);
        addItemType(MultipleItem.ITEM_EDIT, R.layout.item_layout_type_edit);
        addItemType(MultipleItem.ITEM_EDIT2, R.layout.item_layout_type_edit2);
        addItemType(MultipleItem.ITEM_SELECT, R.layout.item_layout_type_select);
        addItemType(MultipleItem.ITEM_SIGN, R.layout.item_layout_type_sign);
        addItemType(MultipleItem.ITEM_FRAGMENT, R.layout.item_layout_type_fragment);
        addItemType(MultipleItem.ITEM_FRAGMENT2, R.layout.item_layout_type_fragment2);
        addItemType(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, R.layout.item_layout_type_recyclerview);
        addItemType(MultipleItem.ITEM_LOCATION, R.layout.item_layout_location);
        addItemType(MultipleItem.ITEM_FIRE_CHECK_FORM, R.layout.item_fire_check);
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
                    if (headPicPath.contains(AppUtils.getAppName())) {
                        //本地照片
                        ImageLoadUtil.loadImageNoCache(mContext, headPicPath, headIv);
                    } else {
                        //网络照片
                        ImageLoadUtil.loadImageNoCache(mContext, UrlFormatUtil.getImageOriginalUrl(headPicPath),
                                headIv, R.mipmap.default_user_head_icon);
                    }

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
                if (!isDetail) {
                    helper.addOnClickListener(R.id.location_ll);
                }
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
                        .setPhotoDelateable(!isDetail)
                        .setMaxCount(fragmentBean.getmMaxCount())
                        .setShowTag(fragmentBean.isShowTag()).setOnPicLoadSuccessCallBack(new SelectPhotosFragment.OnPicLoadSuccessCallBack() {
                    @Override
                    public void loadSuccess(List<String> icons) {
                        ItemFragmentBean picBean = (ItemFragmentBean) fragment.getObject();
                        picBean.setFragmentPics(icons);
                    }
                });
                if (fragmentBean.getFragmentPics().size() > 0) {
                    fragment.setIcons(fragmentBean.getFragmentPics());
                }
                break;
            case MultipleItem.ITEM_RADIO:
                RadioBean radioBean = (RadioBean) item.getObject();
                RadioGroup radioGroup = helper.getView(R.id.item_radio_g);
                if (isDetail) {
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        radioGroup.getChildAt(i).setEnabled(false);
                    }
                } else {
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        radioGroup.getChildAt(i).setEnabled(true);
                    }
                }
                radioGroup.setTag(radioBean);
                RadioButton radioButton0 = helper.getView(R.id.radio_zero_rb);
                RadioButton radioButton1 = helper.getView(R.id.radio_first_rb);
                RadioButton radioButton2 = helper.getView(R.id.radio_second_rb);
                RadioButton radioButton3 = helper.getView(R.id.radio_third_rb);
                String[] values = radioBean.getValues();
                radioButton2.setVisibility(View.GONE);
                radioButton3.setVisibility(View.GONE);
                if (values != null) {
                    if (values.length > 1) {
                        radioButton0.setText(values[0]);
                        radioButton1.setText(values[1]);
                        if (values.length == 3) {
                            radioButton2.setVisibility(View.VISIBLE);
                            radioButton2.setText(values[2]);
                        }
                        if (values.length == 4) {
                            radioButton2.setVisibility(View.VISIBLE);
                            radioButton2.setText(values[2]);
                            radioButton3.setVisibility(View.VISIBLE);
                            radioButton3.setText(values[3]);
                        }
                    }

                } else {
                    radioButton0.setText("是");
                    radioButton1.setText("否");
                }
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (radioCheckedCallBack != null) {
                            radioCheckedCallBack.radioChecked(group, checkedId);
                        }
                        RadioBean radioBean = (RadioBean) group.getTag();
                        switch (checkedId) {
                            case R.id.radio_zero_rb:
                                radioBean.setDefaultSelectedIndex(0);
                                break;
                            case R.id.radio_first_rb:
                                radioBean.setDefaultSelectedIndex(1);
                                break;
                            case R.id.radio_second_rb:
                                radioBean.setDefaultSelectedIndex(2);
                                break;
                            case R.id.radio_third_rb:
                                radioBean.setDefaultSelectedIndex(3);
                                break;
                            default:
                                break;
                        }
                    }
                });
                int defaultIndex = radioBean.getDefaultSelectedIndex();

                switch (defaultIndex) {
                    case 0:
                        radioButton0.setChecked(true);
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        break;
                    case 1:
                        radioButton0.setChecked(false);
                        radioButton1.setChecked(true);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        break;
                    case 2:
                        radioButton0.setChecked(false);
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(true);
                        radioButton3.setChecked(false);
                        break;
                    case 3:
                        radioButton0.setChecked(false);
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(true);
                        break;
                    default:
                        radioButton0.setChecked(false);
                        radioButton1.setChecked(false);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        break;
                }

                break;

            case MultipleItem.ITEM_SIGN:
                ItemSignBean signBean = (ItemSignBean) item.getObject();
                if (signBean.isCanSign()) {
                    helper.addOnClickListener(R.id.sign_name_iv);
                }
                int gravity = signBean.getLayoutGravity();
                LinearLayout signLl = helper.getView(R.id.item_sign_ll);
                ImageView signIv = helper.getView(R.id.sign_name_iv);
                if (0 == gravity) {
                    helper.setGone(R.id.sign_tag, true);
                    signLl.setGravity(Gravity.LEFT);
                } else {
                    helper.setGone(R.id.sign_tag, false);
                    signLl.setGravity(Gravity.RIGHT);
                }
                helper.setText(R.id.sign_name_tv, signBean.getSignName());
                if (StringTools.isStringValueOk(signBean.getSignPicPath())) {
                    ImageLoadUtil.loadImage(mContext, UrlFormatUtil.getImageOriginalUrl(signBean.getSignPicPath()), signIv);
                }
                break;

            case MultipleItem.ITEM_FIRE_CHECK_FORM:
                UnQuailityFormBean formBean = (UnQuailityFormBean) item.getObject();
                helper.setText(R.id.notice_title_tv, formBean.getNoticeName());
                if (!TextUtils.isEmpty(formBean.getNoticeContent())) {
                    helper.setGone(R.id.notice_content_tv, true);
                    helper.setText(R.id.notice_content_tv, formBean.getNoticeContent());
                } else {
                    helper.setGone(R.id.notice_content_tv, false);
                }
                if (formBean.isHideSummarize()) {
                    helper.setGone(R.id.fire_check_summarize_tv, false);
                }else {
                    helper.setGone(R.id.fire_check_summarize_tv, true);
                }

                String json = formBean.getProblems();
                list = GsonTools.changeGsonToList(json, UnQualifiedBean.class);
                initCheckBoxes(helper, formBean);
                for (UnQualifiedBean unQualifiedBean : list) {
                    List<UnQualifiedBean.ChildBean> childBeanList = unQualifiedBean.getChild();
                    for (int i = 0; i < childBeanList.size(); i++) {
                        UnQualifiedBean.ChildBean childBean = childBeanList.get(i);
                        if (1 == childBean.getSelectStatus()) {
                            switch (i) {
                                case 0:
                                    switch (unQualifiedBean.getItemid()) {
                                        case 1:
                                            question1Cd1.setChecked(true);
                                            break;
                                        case 2:
                                            question2Cd1.setChecked(true);
                                            break;
                                        case 3:
                                            question3Cd1.setChecked(true);
                                            break;
                                        case 4:
                                            question4Cd1.setChecked(true);
                                            break;
                                        case 5:
                                            question5Cd1.setChecked(true);
                                            break;
                                        case 6:
                                            question6Cd1.setChecked(true);
                                            break;
                                        case 7:
                                            question7.setChecked(true);
                                            break;
                                        case 8:
                                            question8Cd1.setChecked(true);
                                            break;
                                        case 9:
                                            question9Cd1.setChecked(true);
                                            break;
                                        case 10:
                                            question10Cd1.setChecked(true);
                                            break;
                                        case 11:
                                            question11.setChecked(true);
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                case 1:
                                    switch (unQualifiedBean.getItemid()) {
                                        case 1:
                                            question1Cd2.setChecked(true);
                                            break;
                                        case 2:
                                            question2Cd2.setChecked(true);
                                            break;
                                        case 3:
                                            question3Cd2.setChecked(true);
                                            break;
                                        case 4:
                                            question4Cd2.setChecked(true);
                                            break;
                                        case 5:
                                            question5Cd2.setChecked(true);
                                            break;
                                        case 6:
                                            question6Cd2.setChecked(true);
                                            break;
                                        case 8:
                                            question8Cd2.setChecked(true);
                                            break;
                                        case 9:
                                            question9Cd2.setChecked(true);
                                            break;
                                        case 10:
                                            question10Cd2.setChecked(true);
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                case 2:
                                    switch (unQualifiedBean.getItemid()) {
                                        case 3:
                                            question3Cd3.setChecked(true);
                                            break;
                                        case 4:
                                            question4Cd3.setChecked(true);
                                            break;
                                        case 5:
                                            question5Cd3.setChecked(true);
                                            break;
                                        case 6:
                                            question6Cd3.setChecked(true);
                                            break;
                                        case 8:
                                            question8Cd3.setChecked(true);
                                            break;
                                        case 10:
                                            question10Cd3.setChecked(true);
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                case 3:
                                    switch (unQualifiedBean.getItemid()) {
                                        case 5:
                                            question5Cd4.setChecked(true);
                                            break;
                                        case 10:
                                            question10Cd4.setChecked(true);
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }


                }
                /**
                 * 两个editttext
                 */
                EditText question11ChildEt = helper.getView(R.id.question11_child_et);
                initEdittextFocuseStatus(question11ChildEt);
                question11ChildEt.setTag(formBean);
                addTextChange(question11ChildEt);
                question11ChildEt.setText(formBean.getOtherProblem());
                EditText remark = helper.getView(R.id.concrete_question_et);
                initEdittextFocuseStatus(remark);
                remark.setTag(formBean);
                addTextChange(remark);
                remark.setText(formBean.getConcreteProblems());

                /**
                 * 总结
                 */

                TextView summarizeTv = helper.getView(R.id.fire_check_summarize_tv);
                summarizeTv.setTag(formBean);
                String content = mContext.getString(R.string.fire_check_summarize);
                if (!TextUtils.isEmpty(formBean.getItemOne())) {
                    content = content.replaceFirst(content.substring(getFirstHeadIndex(content, ITEM_HEAD_TAG1),
                            getFirstFootIndex(content, ITEM_FOOT_TAG1)),
                            formBean.getItemOne());
                }
                if (!TextUtils.isEmpty(formBean.getItemOneTime())) {
                    content = content.replaceFirst(content.substring(getFirstHeadIndex(content, ITEM_HEAD_TAG2),
                            getFirstFootIndex(content, ITEM_FOOT_TAG2)), formBean.getItemOneTime());
                }
                if (!TextUtils.isEmpty(formBean.getItemTwo())) {
                    content = content.replaceFirst(content.substring(getLastHeadIndex(content, ITEM_HEAD_TAG1),
                            getLastFootIndex(content, ITEM_FOOT_TAG1)),
                            formBean.getItemTwo());
                }
                if (!TextUtils.isEmpty(formBean.getItemTwoTime())) {
                    content = content.replaceFirst(content.substring(getLastHeadIndex(content,
                            ITEM_HEAD_TAG2), getLastFootIndex(content,
                            ITEM_FOOT_TAG2)), formBean.getItemTwoTime());
                }
                initSpannableText(summarizeTv, content);


                break;
            default:
                break;
        }
    }

    private void initSpannableText(TextView textView, String content) {
        UnQuailityFormBean formBean = (UnQuailityFormBean) textView.getTag();
        CharSequence[] problemItems = getUnQualityItems();
        List<String> selectedProblemItems1 = new ArrayList<>();
        List<String> selectedProblemItems2 = new ArrayList<>();
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString spannableString = new SpannableString(content);
        //点击事件
        spannableString.setSpan(new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View widget) {
                                        if (isDetail) {
                                            return;
                                        }
                                        //第一个选择事项
                                        new AlertDialog.Builder(mContext)
                                                .setTitle("请选择事项")
                                                .setCancelable(false)
                                                .setMultiChoiceItems(problemItems, new boolean[]{false, false, false,
                                                                false, false, false,
                                                                false, false, false, false, false},
                                                        new DialogInterface.OnMultiChoiceClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which,
                                                                                boolean isChecked) {

                                                                if (isChecked) {
                                                                    selectedProblemItems1.add(problemItems[which].toString());
                                                                } else {
                                                                    selectedProblemItems1.remove(problemItems[which]);
                                                                }
                                                            }
                                                        }
                                                ).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                String selectedStr = selectedProblemItems1.toString();
                                                if (selectedProblemItems1.size() > 0) {
                                                    if (selectedStr.contains("[")) {
                                                        selectedStr = selectedStr.substring(1,
                                                                selectedStr.length() - 1);
                                                    }
                                                    String str =
                                                            content.replaceFirst(content.substring(getFirstHeadIndex(content, ITEM_HEAD_TAG1),
                                                                    getFirstFootIndex(content, ITEM_FOOT_TAG1)),
                                                                    selectedStr);
                                                    initSpannableText(textView, str);
                                                    formBean.setItemOne(selectedStr);
                                                } else {
                                                    initSpannableText(textView, content);
                                                }

                                            }
                                        }).show();
                                    }
                                }, getFirstHeadIndex(content, ITEM_HEAD_TAG1), getFirstFootIndex(content,
                ITEM_FOOT_TAG1),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //下划线
        spannableString.setSpan(new UnderlineSpan(), getFirstHeadIndex(content, ITEM_HEAD_TAG1),
                getFirstFootIndex(content, ITEM_FOOT_TAG1),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字颜色
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorAccent)),
                getFirstHeadIndex(content, ITEM_HEAD_TAG1),
                getFirstFootIndex(content, ITEM_FOOT_TAG1),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        //点击事件
        spannableString.setSpan(new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View widget) {
                                        if (isDetail) {
                                            return;
                                        }
                                        //第一个选择时间
                                        PickerManager.getInstance().showTimePickerView(mContext,
                                                PickerManager.getInstance().getTimeType("day"),
                                                "选择日期", new PickerManager.OnTimePickerTimeSelectedListener() {
                                                    @Override
                                                    public void onTimeSelect(Date date, View v) {
                                                        String str =
                                                                content.replaceFirst(content.substring(getFirstHeadIndex(content, ITEM_HEAD_TAG2),
                                                                        getFirstFootIndex(content, ITEM_FOOT_TAG2)),
                                                                        CalendarUtil.getTimeFromDate("yyyy-MM-dd",
                                                                                date));
                                                        initSpannableText(textView, str);
                                                        formBean.setItemOneTime(CalendarUtil.getTimeFromDate("yyyy-MM" +
                                                                "-dd", date));
                                                    }
                                                });
                                    }
                                }, getFirstHeadIndex(content, ITEM_HEAD_TAG2), getFirstFootIndex(content,
                ITEM_FOOT_TAG2),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //下划线
        spannableString.setSpan(new UnderlineSpan(), getFirstHeadIndex(content, ITEM_HEAD_TAG2),
                getFirstFootIndex(content, ITEM_FOOT_TAG2),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字颜色
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorAccent)),
                getFirstHeadIndex(content, ITEM_HEAD_TAG2),
                getFirstFootIndex(content, ITEM_FOOT_TAG2),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        //点击事件
        spannableString.setSpan(new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View widget) {
                                        if (isDetail) {
                                            return;
                                        }
                                        //第二个选择事项
                                        new AlertDialog.Builder(mContext)
                                                .setTitle("请选择事项")
                                                .setCancelable(false)
                                                .setMultiChoiceItems(problemItems, new boolean[]{false, false, false,
                                                                false, false, false,
                                                                false, false, false, false, false},
                                                        new DialogInterface.OnMultiChoiceClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which,
                                                                                boolean isChecked) {

                                                                if (isChecked) {
                                                                    selectedProblemItems2.add(problemItems[which].toString());
                                                                } else {
                                                                    selectedProblemItems2.remove(problemItems[which]);
                                                                }
                                                            }
                                                        }
                                                ).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                String selectedStr = selectedProblemItems2.toString();
                                                if (selectedProblemItems2.size() > 0) {
                                                    if (selectedStr.contains("[")) {
                                                        selectedStr = selectedStr.substring(1,
                                                                selectedStr.length() - 1);
                                                    }
                                                    String str =
                                                            content.replaceFirst(content.substring(getLastHeadIndex(content, ITEM_HEAD_TAG1), getLastFootIndex(content, ITEM_FOOT_TAG1)),
                                                                    selectedStr);
                                                    initSpannableText(textView, str);
                                                    formBean.setItemTwo(selectedStr);
                                                } else {
                                                    initSpannableText(textView, content);
                                                }

                                            }
                                        }).show();
                                    }
                                }, getLastHeadIndex(content, ITEM_HEAD_TAG1), getLastFootIndex(content, ITEM_FOOT_TAG1),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //下划线
        spannableString.setSpan(new UnderlineSpan(), getLastHeadIndex(content, ITEM_HEAD_TAG1),
                getLastFootIndex(content, ITEM_FOOT_TAG1),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字颜色
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorAccent)),
                getLastHeadIndex(content, ITEM_HEAD_TAG1), getLastFootIndex(content, ITEM_FOOT_TAG1),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        //点击事件
        spannableString.setSpan(new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View widget) {
                                        if (isDetail) {
                                            return;
                                        }
                                        //第二个选择时间
                                        PickerManager.getInstance().showTimePickerView(mContext,
                                                PickerManager.getInstance().getTimeType("day"),
                                                "选择日期", new PickerManager.OnTimePickerTimeSelectedListener() {
                                                    @Override
                                                    public void onTimeSelect(Date date, View v) {
                                                        String str =
                                                                content.replaceFirst(content.substring(getLastHeadIndex(content,
                                                                        ITEM_HEAD_TAG2), getLastFootIndex(content,
                                                                        ITEM_FOOT_TAG2)),
                                                                        CalendarUtil.getTimeFromDate("yyyy-MM-dd",
                                                                                date));
                                                        initSpannableText(textView, str);
                                                        formBean.setItemTwoTime(CalendarUtil.getTimeFromDate("yyyy-MM" +
                                                                "-dd", date));
                                                    }
                                                });
                                    }
                                }, getLastHeadIndex(content, ITEM_HEAD_TAG2), getLastFootIndex(content, ITEM_FOOT_TAG2),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //下划线
        spannableString.setSpan(new UnderlineSpan(), getLastHeadIndex(content, ITEM_HEAD_TAG2),
                getLastFootIndex(content, ITEM_FOOT_TAG2),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字颜色
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.colorAccent)),
                getLastHeadIndex(content, ITEM_HEAD_TAG2), getLastFootIndex(content, ITEM_FOOT_TAG2),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(spannableString);
        textView.setText(builder);
        // 添加这一行之后，指定区域文字点击事件才会生效
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private int getFirstHeadIndex(String content, String str) {
        return content.indexOf(str) + str.length();
    }

    private int getLastHeadIndex(String content, String str) {
        return content.lastIndexOf(str) + str.length();
    }

    private int getFirstFootIndex(String content, String str) {
        return content.indexOf(str);
    }

    private int getLastFootIndex(String content, String str) {
        return content.lastIndexOf(str);
    }

    /**
     * 获取残疾的种类
     *
     * @return
     */
    private CharSequence[] getUnQualityItems() {
        return new CharSequence[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    }

    /**
     * 初始化所有的box
     *
     * @param helper
     * @param formBean
     */
    private void initCheckBoxes(BaseViewHolder helper, UnQuailityFormBean formBean) {
        question1 = getCheckBox(helper, R.id.question1_cb, formBean);
        question2 = getCheckBox(helper, R.id.question2_cb, formBean);
        question3 = getCheckBox(helper, R.id.question3_cb, formBean);
        question4 = getCheckBox(helper, R.id.question4_cb, formBean);
        question5 = getCheckBox(helper, R.id.question5_cb, formBean);
        question6 = getCheckBox(helper, R.id.question6_cb, formBean);
        question7 = getCheckBox(helper, R.id.question7_cb, formBean);
        question8 = getCheckBox(helper, R.id.question8_cb, formBean);
        question9 = getCheckBox(helper, R.id.question9_cb, formBean);
        question10 = getCheckBox(helper, R.id.question10_cb, formBean);
        question11 = getCheckBox(helper, R.id.question11_cb, formBean);
        question1Cd1 = getCheckBox(helper, R.id.question1_child1_cb, formBean);
        question1Cd2 = getCheckBox(helper, R.id.question1_child2_cb, formBean);
        question9Cd1 = getCheckBox(helper, R.id.question9_child1_cb, formBean);
        question9Cd2 = getCheckBox(helper, R.id.question9_child2_cb, formBean);
        question2Cd1 = getCheckBox(helper, R.id.question2_child1_cb, formBean);
        question2Cd2 = getCheckBox(helper, R.id.question2_child2_cb, formBean);
        question3Cd1 = getCheckBox(helper, R.id.question3_child1_cb, formBean);
        question3Cd2 = getCheckBox(helper, R.id.question3_child2_cb, formBean);
        question3Cd3 = getCheckBox(helper, R.id.question3_child3_cb, formBean);
        question4Cd1 = getCheckBox(helper, R.id.question4_child1_cb, formBean);
        question4Cd2 = getCheckBox(helper, R.id.question4_child2_cb, formBean);
        question4Cd3 = getCheckBox(helper, R.id.question4_child3_cb, formBean);
        question6Cd1 = getCheckBox(helper, R.id.question6_child1_cb, formBean);
        question6Cd2 = getCheckBox(helper, R.id.question6_child2_cb, formBean);
        question6Cd3 = getCheckBox(helper, R.id.question6_child3_cb, formBean);
        question8Cd1 = getCheckBox(helper, R.id.question8_child1_cb, formBean);
        question8Cd2 = getCheckBox(helper, R.id.question8_child2_cb, formBean);
        question8Cd3 = getCheckBox(helper, R.id.question8_child3_cb, formBean);
        question5Cd1 = getCheckBox(helper, R.id.question5_child1_cb, formBean);
        question5Cd2 = getCheckBox(helper, R.id.question5_child2_cb, formBean);
        question5Cd3 = getCheckBox(helper, R.id.question5_child3_cb, formBean);
        question5Cd4 = getCheckBox(helper, R.id.question5_child4_cb, formBean);
        question10Cd1 = getCheckBox(helper, R.id.question10_child1_cb, formBean);
        question10Cd2 = getCheckBox(helper, R.id.question10_child2_cb, formBean);
        question10Cd3 = getCheckBox(helper, R.id.question10_child3_cb, formBean);
        question10Cd4 = getCheckBox(helper, R.id.question10_child4_cb, formBean);
        if (isDetail) {
            setCheckBoxClickStatus(false, question1, question2, question3, question4, question5, question6, question7,
                    question8, question9, question10, question11, question1Cd1, question1Cd2, question2Cd1,
                    question2Cd2, question3Cd1, question3Cd2,
                    question3Cd3, question4Cd1, question4Cd2, question4Cd3, question5Cd1, question5Cd2, question5Cd3,
                    question5Cd4, question6Cd1, question6Cd2
                    , question6Cd3, question8Cd1, question8Cd2, question8Cd3, question9Cd1, question9Cd2, question10Cd1,
                    question10Cd2, question10Cd3, question10Cd4);
        } else {
            setCheckBoxClickStatus(true, question1, question2, question3, question4, question5, question6, question7,
                    question8, question9, question10, question11, question1Cd1, question1Cd2, question2Cd1,
                    question2Cd2, question3Cd1, question3Cd2,
                    question3Cd3, question4Cd1, question4Cd2, question4Cd3, question5Cd1, question5Cd2, question5Cd3,
                    question5Cd4, question6Cd1, question6Cd2
                    , question6Cd3, question8Cd1, question8Cd2, question8Cd3, question9Cd1, question9Cd2, question10Cd1,
                    question10Cd2, question10Cd3, question10Cd4);
        }
    }

    /**
     * 更改checkbox状态
     *
     * @param clickable
     * @param checkBoxes
     */
    private void setCheckBoxClickStatus(boolean clickable, CheckBox... checkBoxes) {
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setClickable(clickable);
        }
    }

    private CheckBox getCheckBox(BaseViewHolder helper, int cbResId, UnQuailityFormBean formBean) {
        CheckBox questionCb = helper.getView(cbResId);
        questionCb.setTag(formBean);
        questionCb.setOnCheckedChangeListener(changeListener);
        return questionCb;
    }

    public CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            UnQuailityFormBean formBean = (UnQuailityFormBean) buttonView.getTag();
            switch (buttonView.getId()) {
                case R.id.question1_cb:
                    initPresentCheckBoxStatus(formBean, 0, isChecked, question1Cd1, question1Cd2);
                    break;
                case R.id.question2_cb:
                    initPresentCheckBoxStatus(formBean, 1, isChecked, question2Cd1, question2Cd2);
                    break;
                case R.id.question3_cb:
                    initPresentCheckBoxStatus(formBean, 2, isChecked, question3Cd1, question3Cd2, question3Cd3);
                    break;
                case R.id.question4_cb:
                    initPresentCheckBoxStatus(formBean, 3, isChecked, question4Cd1, question4Cd2, question4Cd3);
                    break;
                case R.id.question5_cb:
                    initPresentCheckBoxStatus(formBean, 4, isChecked, question5Cd1, question5Cd2, question5Cd3,
                            question5Cd4);
                    break;
                case R.id.question6_cb:
                    initPresentCheckBoxStatus(formBean, 5, isChecked, question6Cd1, question6Cd2, question6Cd3);
                    break;
                case R.id.question7_cb:
                    initPresentCheckBoxStatus(formBean, 6, isChecked);
                    break;
                case R.id.question8_cb:
                    initPresentCheckBoxStatus(formBean, 7, isChecked, question8Cd1, question8Cd2, question8Cd3);
                    break;
                case R.id.question9_cb:
                    initPresentCheckBoxStatus(formBean, 8, isChecked, question9Cd1, question9Cd2);
                    break;
                case R.id.question10_cb:
                    initPresentCheckBoxStatus(formBean, 9, isChecked, question10Cd1, question10Cd2, question10Cd3,
                            question10Cd4);
                    break;
                case R.id.question11_cb:
                    initPresentCheckBoxStatus(formBean, 10, isChecked);
                    break;
                case R.id.question1_child1_cb:
                    initChildCheckBoxStatus(formBean, 0, 0, isChecked, question1, question1Cd2);
                    break;
                case R.id.question1_child2_cb:
                    initChildCheckBoxStatus(formBean, 0, 1, isChecked, question1, question1Cd1);
                    break;
                case R.id.question2_child1_cb:
                    initChildCheckBoxStatus(formBean, 1, 0, isChecked, question2, question2Cd2);
                    break;
                case R.id.question2_child2_cb:
                    initChildCheckBoxStatus(formBean, 1, 1, isChecked, question2, question2Cd1);
                    break;
                case R.id.question3_child1_cb:
                    initChildCheckBoxStatus(formBean, 2, 0, isChecked, question3, question3Cd1, question3Cd2,
                            question3Cd3);
                    break;
                case R.id.question3_child2_cb:
                    initChildCheckBoxStatus(formBean, 2, 1, isChecked, question3, question3Cd1, question3Cd2,
                            question3Cd3);
                    break;
                case R.id.question3_child3_cb:
                    initChildCheckBoxStatus(formBean, 2, 2, isChecked, question3, question3Cd1, question3Cd2,
                            question3Cd3);
                    break;
                case R.id.question4_child1_cb:
                    initChildCheckBoxStatus(formBean, 3, 0, isChecked, question4, question4Cd1, question4Cd2,
                            question4Cd3);
                    break;
                case R.id.question4_child2_cb:
                    initChildCheckBoxStatus(formBean, 3, 1, isChecked, question4, question4Cd1, question4Cd2,
                            question4Cd3);
                    break;
                case R.id.question4_child3_cb:
                    initChildCheckBoxStatus(formBean, 3, 2, isChecked, question4, question4Cd1, question4Cd2,
                            question4Cd3);
                    break;

                case R.id.question5_child1_cb:
                    initChildCheckBoxStatus(formBean, 4, 0, isChecked, question5, question5Cd1, question5Cd2,
                            question5Cd3, question5Cd4);
                    break;
                case R.id.question5_child2_cb:
                    initChildCheckBoxStatus(formBean, 4, 1, isChecked, question5, question5Cd1, question5Cd2,
                            question5Cd3, question5Cd4);
                    break;
                case R.id.question5_child3_cb:
                    initChildCheckBoxStatus(formBean, 4, 2, isChecked, question5, question5Cd1, question5Cd2,
                            question5Cd3, question5Cd4);
                    break;
                case R.id.question5_child4_cb:
                    initChildCheckBoxStatus(formBean, 4, 3, isChecked, question5, question5Cd1, question5Cd2,
                            question5Cd3, question5Cd4);
                    break;

                case R.id.question6_child1_cb:
                    initChildCheckBoxStatus(formBean, 5, 0, isChecked, question6, question6Cd1, question6Cd2,
                            question6Cd3);
                    break;
                case R.id.question6_child2_cb:
                    initChildCheckBoxStatus(formBean, 5, 1, isChecked, question6, question6Cd1, question6Cd2,
                            question6Cd3);
                    break;
                case R.id.question6_child3_cb:
                    initChildCheckBoxStatus(formBean, 5, 2, isChecked, question6, question6Cd1, question6Cd2,
                            question6Cd3);
                    break;
                case R.id.question8_child1_cb:
                    initChildCheckBoxStatus(formBean, 7, 0, isChecked, question8, question8Cd1, question8Cd2,
                            question8Cd3);
                    break;
                case R.id.question8_child2_cb:
                    initChildCheckBoxStatus(formBean, 7, 1, isChecked, question8, question8Cd1, question8Cd2,
                            question8Cd3);
                    break;
                case R.id.question8_child3_cb:
                    initChildCheckBoxStatus(formBean, 7, 2, isChecked, question8, question8Cd1, question8Cd2,
                            question8Cd3);
                    break;
                case R.id.question9_child1_cb:
                    initChildCheckBoxStatus(formBean, 8, 0, isChecked, question9, question9Cd2);
                    break;
                case R.id.question9_child2_cb:
                    initChildCheckBoxStatus(formBean, 8, 1, isChecked, question9, question9Cd1);
                    break;

                case R.id.question10_child1_cb:
                    initChildCheckBoxStatus(formBean, 9, 0, isChecked, question10, question10Cd1, question10Cd2,
                            question10Cd3, question10Cd4);
                    break;
                case R.id.question10_child2_cb:
                    initChildCheckBoxStatus(formBean, 9, 1, isChecked, question10, question10Cd1, question10Cd2,
                            question10Cd3, question10Cd4);
                    break;
                case R.id.question10_child3_cb:
                    initChildCheckBoxStatus(formBean, 9, 2, isChecked, question10, question10Cd1, question10Cd2,
                            question10Cd3, question10Cd4);
                    break;
                case R.id.question10_child4_cb:
                    initChildCheckBoxStatus(formBean, 9, 3, isChecked, question10, question10Cd1, question10Cd2,
                            question10Cd3, question10Cd4);
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * 父控件点击后的逻辑
     *
     * @param isChecked
     */
    private void initPresentCheckBoxStatus(UnQuailityFormBean formBean, int presentIndex, boolean isChecked,
                                           CheckBox... childsBox) {
        if (6 == presentIndex || 10 == presentIndex) {
            //问题7
            list.get(presentIndex).getChild().get(0).setSelectStatus(isChecked ? 1 : 0);
            formBean.setProblems(GsonTools.createGsonString(list));
            return;
        }
        if (!isChecked) {
            //取消所有子项的选中状态
            for (CheckBox box : childsBox) {
                box.setChecked(false);
            }
        }
    }

    /**
     * 初始化子控件的状态
     *
     * @param isChecked
     * @param presentBox
     * @param childsBox  其他子child
     */
    private void initChildCheckBoxStatus(UnQuailityFormBean formBean, int itemPosition, int childPosition,
                                         boolean isChecked, CheckBox presentBox,
                                         CheckBox... childsBox) {
        list.get(itemPosition).getChild().get(childPosition).setSelectStatus(isChecked ? 1 : 0);
        formBean.setProblems(GsonTools.createGsonString(list));
        if (isChecked) {
            //子条目被选中  父控件状态更改
            presentBox.setChecked(true);
        } else {
            //false
            for (CheckBox box : childsBox) {
                //如果有一个还是选中状态  父控件就是选中状态
                if (box.isChecked()) {
                    return;
                }
            }
            presentBox.setChecked(false);
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
                    case R.id.question11_child_et:
                        UnQuailityFormBean formBean = (UnQuailityFormBean) editText.getTag();
                        formBean.setOtherProblem(s.toString().trim());
                        break;
                    case R.id.concrete_question_et:
                        UnQuailityFormBean unQuailityFormBean = (UnQuailityFormBean) editText.getTag();
                        unQuailityFormBean.setConcreteProblems(s.toString().trim());
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


    public interface OnRadioCheckedCallBack {
        void radioChecked(RadioGroup group, int checkedId);
    }


}
