package com.juntai.wisdom.inspection.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/9 9:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/9 9:47
 */
public class ItemFragmentBean  {


    private int mSpanCount = 3;//一行的个数，默认4
    private int mMaxCount = 9;//最大个数，默认9个
    private int minCount = 0;//最少数
    private boolean deleteable = true;//可删除
    private boolean isShowTag = false;//是否显示底部标记

    private List<String> fragmentPics;//选中的图片库

    public ItemFragmentBean(int mSpanCount, int mMaxCount, int minCount, boolean deleteable, boolean isShowTag,
                            List<String> fragmentPics) {
        this.mSpanCount = mSpanCount;
        this.mMaxCount = mMaxCount;
        this.minCount = minCount;
        this.deleteable = deleteable;
        this.isShowTag = isShowTag;
        this.fragmentPics = fragmentPics;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public List<String> getFragmentPics() {
        if (fragmentPics == null) {
            return new ArrayList<>();
        }
        return fragmentPics;
    }

    public void setFragmentPics(List<String> fragmentPics) {
        this.fragmentPics = fragmentPics;
    }

    public int getmSpanCount() {
        return mSpanCount;
    }

    public void setmSpanCount(int mSpanCount) {
        this.mSpanCount = mSpanCount;
    }

    public int getmMaxCount() {
        return mMaxCount;
    }

    public void setmMaxCount(int mMaxCount) {
        this.mMaxCount = mMaxCount;
    }

    public boolean isDeleteable() {
        return deleteable;
    }

    public void setDeleteable(boolean deleteable) {
        this.deleteable = deleteable;
    }

    public boolean isShowTag() {
        return isShowTag;
    }

    public void setShowTag(boolean showTag) {
        isShowTag = showTag;
    }
}
