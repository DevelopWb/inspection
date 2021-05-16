package com.juntai.wisdom.inspection.utils;

import com.juntai.wisdom.inspection.bean.UserBean;
import com.orhanobut.hawk.Hawk;

/**
 * @Author: tobato
 * @Description: 作用描述  用户信息管理类
 * @CreateDate: 2020/12/19 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/19 14:04
 */
public class UserInfoManager {
    public static String QQ_ID = null;//qqid
    public static String WECHAT_ID = null;//wechatid
    public static String OTHER_NICK_NAME = null;//第三方昵称

    /**
     * 获取账号的状态  0 代表游客登录 1代表手机号登录 2代表第三方登录（未绑定手机号）
     *
     * @return
     */
    public static int getAccountStatus() {
        int status = -1;
        if (isLogin()) {
            String phoneNum = getUser().getData().getAccount();
            if ("未绑定".equals(phoneNum)) {
                status = 2;
            } else {
                status = 1;
            }
        } else {
            status = 0;
        }
        return status;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserBean getUser() {
        return Hawk.get(AppUtils.SP_KEY_USER);
    }

    /**
     * 判定用户是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        if (getUser() == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 退出登录清理缓存配置
     */
    public static void clearUserData() {
        Hawk.delete(AppUtils.SP_KEY_USER);
        Hawk.delete(AppUtils.SP_KEY_TOKEN);
        Hawk.delete(AppUtils.SP_KEY_UNREAD_COUNT);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static String getPhoneNumber() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getAccount() : "";
    }
    /**
     * 获取部门id
     *
     * @return
     */
    public static int getDepartmentId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getDepartmentId() : 0;
    }
    /**
     * 获取账户
     *
     * @return
     */
    public static String getUserAccount() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getAccount() : "";
    }
    /**
     * 获取账户
     *
     * @return
     */
    public static String getUserNickName() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getNickname() : "";
    }
    /**
     * 获取usertoken
     *
     * @return
     */
    public static String getUserToken() {
        return Hawk.get(AppUtils.SP_KEY_TOKEN);
    }

    /**
     * 获取getUserId
     *
     * @return
     */
    public static int getUserId() {
        return getUser() != null && getUser().getData() != null ? getUser().getData().getUserId() : -1;
    }

//    /**
//     * 获取getUserId
//     *
//     * @return
//     */
//    public static int getRealNameStatus() {
//        return getUser() != null && getUser().getData() != null ? getUser().getData().getRealNameStatus() : 0;
//    }
//    /**
//     * 获取用户信息
//     *
//     * @return
//     */
//    public static String getScore() {
//        return getUser() != null && getUser().getData() != null ? String.valueOf(getUser().getData().getScore()) : "";
//    }


}
