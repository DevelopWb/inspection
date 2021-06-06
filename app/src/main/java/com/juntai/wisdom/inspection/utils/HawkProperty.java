package com.juntai.wisdom.inspection.utils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/2/27 10:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/27 10:59
 */
public class HawkProperty {

    public static String ADD_UNIT_KEY = "add_unit";//手动添加单位  这个key唯一  搜索添加的时候 需要绑定单位的id
    public static String EDIT_UNIT_KEY = "edit_unit";//编辑单位  这个key唯一  搜索添加的时候 需要绑定单位的id
    public static String ADD_INSPECRTION_SITE_KEY = "add_site";//添加巡检点  同上逻辑
    public static String EDIT_INSPECRTION_SITE_KEY = "edit_site";//编辑巡检点  同上逻辑
    public static String ADD_IMPORTANTOR_KEY = "add_importantor";//重点人员 同上逻辑
    public static String EDIT_IMPORTANTOR_KEY = "edit_importantor";//重点人员 同上逻辑
    public static String ADD_INSPECRTION_RECORD_KEY = "add_inspectionrecord";//添加巡检记录
    public static String ADD_FIRE_CHECK_RECORD_KEY = "add_firecheckrecord";//添加消防检查记录
    public static String ADD_IMPORTANTOR_VISIT_RECORD_KEY = "add_importantorvisit";//重点人员 上门
    public static String ADD_WORKER_KEY = "add_worker";//添加从业人员  需要和单位id绑定
    public static String EDIT_WORKER_KEY = "edit_worker";//编辑从业人员  需要和单位id、从业人员的id绑定
}
