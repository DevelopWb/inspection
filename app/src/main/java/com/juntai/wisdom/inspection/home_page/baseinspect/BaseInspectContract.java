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
    public static  String IMPORTANTOR_TAB_TITLES = "tab标题";
    public static  String IMPORTANTOR_TAB_TITLE_ALL = "全部";
    public static  String IMPORTANTOR_TAB_TITLE_TO_VISITE = "待走访";
    public static  String IMPORTANTOR_TAB_TITLE_VISITED = "已走访";
    public static  String IMPORTANTOR_TAB_TITLE_TO_CHECK = "待检查";
    public static  String IMPORTANTOR_TAB_TITLE_REPAIRING= "整改中";
    public static  String IMPORTANTOR_TAB_TITLE_IS_OK = "已合格";
    public static  String INSPECTION_SITE = "巡检点";
    public static  String INSPECTION_ADDR = "巡检地址";
    public static  String INSPECTION_RESPONSIBLE = "安全责任人";
    public static  String INSPECTION_TEL = "联系电话";
    public static  String INSPECTION_SPARE_PERSON = "备用联系人";
    public static  String INSPECTION_SPARE_PERSON_TEL = "备用联系人电话";
    public static  String INSPECTION_CHECK_TYPE = "检查类型";
    public static  String INSPECTION_CHECK_RECORD = "检查记录";
    public static  String INSPECTION_RESPONSIBILITY = "责任书";
    public static  String INSPECTION_RECTIFY_NOTICE = "整改通知书";
    public static  String INSPECTION_WORKER = "从业人员";
    public static  String INSPECTION_CHECK_PROBLEMS = "问题及巡查情况";
    public static  String INSPECTION_VISIT_RECORD  = "走访记录";
    public static  String INSPECTION_SECURITY_RECORD  = "治安巡检记录";

    interface IInspectView extends IView {}

    interface IInspectPresent {}
}
