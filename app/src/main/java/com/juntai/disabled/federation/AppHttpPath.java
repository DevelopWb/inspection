package com.juntai.disabled.federation;

public class AppHttpPath {

    //            public static final String BASE = "http://192.168.124.115:8080/disabledPersonsFederation/u/app";
    public static final String BASE = "https://wx.juntaikeji.com:19153/disabledPersonsFederation/u/app";
    /**
     * 登录
     */
    public static final String LOGIN = BASE + "/appLogin.shtml";

    /**
     * 修改人员定位位置（历史轨迹）上传人员得位置信息
     */
    public static final String USER_HISTORY_UPLOAD = BASE + "/addStaffHistoryLocation.shtml";

    /**
     * 检查更新
     */
//    public static final String APP_UPDATE = BASE + "/detectionAppVersions.shtml";
    public static final String APP_UPDATE = BASE + "";
}