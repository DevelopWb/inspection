package com.juntai.wisdom.inspection;

public class AppHttpPath {

                public static final String BASE = "http://192.168.124.118:8080/inspection";
//    public static final String BASE = "https://wx.juntaikeji.com:19153/disabledPersonsFederation/u/app";
    public static final String BASE_IMAGE = "http://192.168.124.118:9595";
    /**
     * 登录
     */
    public static final String LOGIN = BASE + "/u/appConnector/login.shtml";

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
    public static final String GET_USER_INFO = BASE + "/u/appConnector/selectUserInfo.shtml";
    /**
     * 退出登录
     */
    public static final String LOGIN_OUT = BASE + "/u/appConnector/logout.shtml";




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
    public static final String SEARCH_UNIT_CHECK_STATUS = BASE + "/u/appConnector/searchSecurityFire.shtml";





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

}