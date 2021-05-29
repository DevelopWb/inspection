package com.juntai.wisdom.inspection;

public class AppHttpPath {

                public static final String BASE = "http://192.168.124.118:8080/inspection";
//    public static final String BASE = "https://wx.juntaikeji.com:19153/disabledPersonsFederation/u/app";
    public static final String BASE_IMAGE = "http://192.168.124.118:9595";
    public static final String BASE_IMAGE_THUM = "http://192.168.124.118:9595/thumbnail";
    /**
     * 登录
     */
    public static final String LOGIN = BASE + "/u/appUserStaff/login.shtml";

    /**
     * 修改人员定位位置（历史轨迹）上传人员得位置信息
     */
    public static final String USER_HISTORY_UPLOAD = BASE + "/addStaffHistoryLocation.shtml";

    /**
     * 检查更新
     */
//    public static final String APP_UPDATE = BASE + "/detectionAppVersions.shtml";
    public static final String APP_UPDATE = BASE + "";




    /*==============================================  个人中心  =============================================*/

    /**
     * 用户详情
     */
    public static final String GET_USER_INFO = BASE + "/u/appUserStaff/selectUserInfo.shtml";
    /**
     * 退出登录
     */
    public static final String LOGIN_OUT = BASE + "/u/appUserStaff/logout.shtml";




    /*==============================================  消防检查  =============================================*/
    /**
     * 单位类型
     */
    public static final String UNIT_TYPE = BASE + "/u/appConnector/selectUnitType.shtml";
    /**
     * 添加单位搜索
     */
    public static final String SEARCH_COMPANYS = BASE + "/u/appConnector/selectSearchUnit.shtml";
    /**
     * 搜索添加
     */
    public static final String SEARCH_ADD_UNIT = BASE + "/u/appConnector/searchInsertUnit.shtml";
    /**
     * 手动添加
     */
    public static final String MANUAL_ADD_UNIT = BASE + "/u/appConnector/insertUnit.shtml";
    /**
     * 查询单位  信用码 是否存在   唯一性
     */
    public static final String CHECK_UNIT_UNIQUE = BASE + "/u/appConnector/selectUnitName.shtml";

    /**
     * 治安消防模块首页搜索
     */
    public static final String SEARCH_UNIT_TO_CHECK = BASE + "/u/appConnector/searchSecurityFire.shtml";
    /**
     * 单位详情
     */
    public static final String GET_UNIT_INFO_DETAIL = BASE + "/u/appConnector/selectUnitInfo.shtml";

    /**
     * 申请修改单位
     */
    public static final String APPLY_EDIT_UNIT_INFO = BASE + "/u/appConnector/applyUnit.shtml";


    /**
     * 添加消防检查记录
     */
    public static final String ADD_FIRE_INSPECTION = BASE + "/u/appConnector/insertInspectionRecord.shtml";

    /**
     * 消防检查记录
     */
    public static final String GET_FIRE_INSPECTION_RECORDS = BASE + "/u/appConnector/selectInspectRecordList.shtml";
    /**
     * 整改通知书列表
     */
    public static final String GET_RECTIFY_NOTICE_LIST = BASE + "/u/appConnector/selectUnitNoticeList.shtml";
    /**
     * 整改通知书详情
     */
    public static final String GET_RECTIFY_NOTICE_DETAIL = BASE + "/u/appConnector/selectUnitNoticeInfo.shtml";
    /**
     * 添加处罚信息
     */
    public static final String ADD_PUNISH_INFO = BASE + "/u/appConnector/insertUnitPunish.shtml";

    /**
     * 消防检查记录详情
     */
    public static final String GET_FIRE_INSPECTION_RECORD_DETAIL = BASE + "/u/appConnector/selectInspectRecordInfo.shtml";
    /**
     * 责任书列表
     */
    public static final String GET_RESPONSIBILITY_LIST = BASE + "/u/appConnector/selectCheckResponsibilityList.shtml";
    /**
     * 签署责任书
     */
    public static final String SIGN_RESPONSIBILITY = BASE + "/u/appConnector/insertResponsibility.shtml";

    /**
     * 责任书详情
     */
    public static final String RESPONSIBILITY_DETAIL = BASE + "/u/appConnector/selectCheckResponsibilityInfo.shtml";



    /*==============================================  巡检点  =============================================*/
    /**
     * 治安巡检点详情
     */
    public static final String INSPECTION_SITE_DETAIL = BASE + "/u/appSecurity/selectSecurityPublicInfo.shtml";
    /**
     * 搜索所有的巡检点
     */
    public static final String SEARCH_INSPECTION_SITES_TO_ADD = BASE + "/u/appSecurity/selectSearchSecurityPublic.shtml";
    /**
     * 查询巡检点是否存在
     */
    public static final String CHECK_INSPECTION_SITE_UNIQUE = BASE + "/u/appSecurity/selectPointName.shtml";
    /**
     * 搜索添加治安巡检点
     */
    public static final String SEARCH_ADD_INSP_SITE = BASE + "/u/appSecurity/updateSecurityPublic.shtml";
    /**
     * 手动添加治安巡检点
     */
    public static final String MANUAL_ADD_INSP_SITE = BASE + "/u/appSecurity/insertSecurityPublic.shtml";


    /**
     * 搜索已经添加的巡检点
     */
    public static final String SEARCH_ADDED_INSPECTION_SITES = BASE + "/u/appSecurity/searchSecurityPublic.shtml";

    /**
     * 巡检记录列表
     */
    public static final String SECURITY_INSPECT_RECORDS = BASE + "/u/appSecurity/selectInspectionRecordList.shtml";

    /**
     * 巡检记录详情
     */
    public static final String SECURITY_INSPECT_RECORD_DETAIL = BASE + "/u/appSecurity/selectInspectionRecordInfo" +
            ".shtml";
    /**
     * 治安巡查问题及情况类型
     */
    public static final String SECURITY_INSPECT_QUESTION = BASE + "/u/appSecurity/selectInspectionRecordType.shtml";

    /**
     * 添加巡检记录
     */
    public static final String ADD_INSPECTION_RECORD = BASE + "/u/appSecurity/insertInspectionRecord.shtml";



    /**
     * 修改巡检点
     */
    public static final String APPLY_EDIT_INSPECTION_SITE_INFO = BASE + "/u/appSecurity/applySecurityPublic.shtml";





    /*==============================================  重点人员  =============================================*/

    /**
     * 重点人员详情
     */
    public static final String IMPORTANTOR_DETAIL = BASE + "/u/appKeyPersonnel/selectKeyPersonnelInfo.shtml";
    /**
     * 搜索所有的重点人员
     */
    public static final String SEARCH_IMPORTANTOR_TO_ADD = BASE + "/u/appKeyPersonnel/selectSearchKeyPersonnel.shtml";
    /**
     * 查询重点人员id是否存在
     */
    public static final String CHECK_IMPORTANTOR_UNIQUE = BASE + "/u/appKeyPersonnel/selectKeyPersonnelNumber.shtml";
    /**
     * 搜索添加重点人员
     */
    public static final String SEARCH_ADD_IMPORTANTOR = BASE + "/u/appKeyPersonnel/updateKeyPersonnel.shtml";
    /**
     * 手动添加重点人员
     */
    public static final String MANUAL_ADD_IMPORTANTOR = BASE + "/u/appKeyPersonnel/insertKeyPersonnel.shtml";
    /**
     * 人员类型
     */
    public static final String IMPORTANTOR_TYPES = BASE + "/u/appKeyPersonnel/selectKeyPersonnelType.shtml";
    /**
     * 人员状态
     */
    public static final String IMPORTANTOR_STATUS = BASE + "/u/appKeyPersonnel/selectKeyPersonnelState.shtml";

    /**
     * 重点人员模块首页搜索
     */
    public static final String SEARCH_ALL_ADDED_IMPORTANTOR = BASE + "/u/appKeyPersonnel/searchKeyPersonnel.shtml";
    /**
     * 走访问题及情况类型
     */
    public static final String VISIT_QUESTIONS = BASE + "/u/appKeyPersonnel/selectKeyPersonnelInspection.shtml";


    /**
     * 走访记录列表
     */
    public static final String VISIT_RECORD_LIST = BASE + "/u/appKeyPersonnel/selectKeyPersonnelRecordList.shtml";


    /**
     * 走访记录详情
     */
    public static final String VISIT_RECORD_DETAIL = BASE + "/u/appKeyPersonnel/selectKeyPersonnelRecordInfo.shtml";



    /**
     * 开始走访
     */
    public static final String START_VISIT = BASE + "/u/appKeyPersonnel/insertKeyPersonnelVisitRecord.shtml";




}