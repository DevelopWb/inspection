package com.juntai.wisdom.inspection.bean;

import android.text.TextUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/21 9:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/21 9:35
 */
public class TextKeyValueBean {

    private String key;
    private String value;
    private String ids;//用于多选时 多个item的id
    private String hint;
    private String other;//多选时 存在其他 这个选项的时候
    private int type;//0代表高度固定的edittext  1代表高度不固定的edittext
    private boolean isImportant;//是否必填
    private boolean valueGravityToRight;//value靠右

    public TextKeyValueBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public TextKeyValueBean(String key, String value, String hint, int type, boolean isImportant) {
        this.key = key;
        this.value = value;
        this.hint = hint;
        this.type = type;
        this.isImportant = isImportant;
    }


    public TextKeyValueBean(String key, String value, String ids, String hint, int type, boolean isImportant
                            ,String other) {
        this.key = key;
        this.value = value;
        this.ids = ids;
        this.hint = hint;
        this.type = type;
        this.isImportant = isImportant;
        this.other = other;
    }

    public String getOther() {
        return other == null ? "" : other;
    }

    public void setOther(String other) {
        this.other = other == null ? "" : other;
    }

    public String getIds() {
        return ids == null ? "" : ids;
    }

    public void setIds(String ids) {
        this.ids = ids == null ? "" : ids;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key == null ? "" : key;
    }

    public String getValue() {
        return value == null ? "" : value;
    }

    public void setValue(String value) {
        this.value = value == null ? "" : value;
    }

    public String getHint() {
        return hint == null ? "" : hint;
    }

    public void setHint(String hint) {
        this.hint = hint == null ? "" : hint;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isValueGravityToRight() {
        return valueGravityToRight;
    }

    public void setValueGravityToRight(boolean valueGravityToRight) {
        this.valueGravityToRight = valueGravityToRight;
    }
}
