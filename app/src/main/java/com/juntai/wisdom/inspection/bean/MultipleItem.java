package com.juntai.wisdom.inspection.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/4/15 10:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/15 10:08
 */
public class MultipleItem implements MultiItemEntity {
    public static final int ITEM_TITLE = 1;//类型1
    public static final int ITEM_CONTENT = 2;//类型2
    public static final int ITEM_LOAD_MORE = 3;//类型3
    public static final int ITEM_HEAD_PIC = 4;//类型3
    public static final int ITEM_TITILE_BIG = 5;//类型3
    public static final int ITEM_TITILE_SMALL = 6;//类型3
    public static final int ITEM_EDIT = 7;//类型3
    public static final int ITEM_SELECT = 8;//类型3
    public static final int ITEM_RADIO = 9;//类型3
    public static final int ITEM_PIC = 10;//类型3
    public static final int ITEM_SIGN = 11;//签字
    public static final int ITEM_NOTICE = 12;//提示
    public static final int ITEM_DATE = 13;//日期
    public static final int ITEM_NORMAL_RECYCLEVIEW = 14;//
    public static final int ITEM_EDIT2 = 15;//  key value 类型
    public static final int ITEM_FRAGMENT = 16;//  fragment
    public static final int ITEM_LOCATION = 17;//  定位
    public static final int ITEM_FIRE_CHECK_FORM = 18;//  消防检查表单
    public static final int ITEM_RESIBILITY = 19;//  责任书列表item
    public static final int ITEM_FRAGMENT2 = 20;//  fragment
    public static final int ITEM_TEXT = 21;//  文本展示

    private int itemType;
    private Object object;

    public MultipleItem(int itemType, Object object) {
        this.itemType = itemType;
        this.object = object;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
