package com.juntai.wisdom.inspection.home_page.baseinspect;

import com.haibin.calendarview.BaseView;
import com.juntai.disabled.basecomponent.mvp.IView;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 16:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 16:41
 */
public interface BaseInspectContract {

    public static  String REMARK = "备注";
    public static  String INSPECTION_SITE = "巡检点";
    public static  String INSPECTION_ADDR = "巡检地址";
    public static  String INSPECTION_RESPONSIBLE = "安全责任人";
    public static  String INSPECTION_TEL = "联系电话";
    public static  String INSPECTION_SPARE_PERSON = "备用联系人";
    public static  String INSPECTION_SPARE_PERSON_TEL = "备用联系人电话";

    interface IInspectView extends IView {}

    interface IInspectPresent {}
}
