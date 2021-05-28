package com.juntai.wisdom.inspection.bean;


import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * 个人信息
 * Created by Ma
 * on 2019/4/27
 */
public class UserBean extends BaseResult {


    /**
     * error : null
     * data : {"userId":1,"account":"18669505929","password":null,"nickname":"顾启杭","headPortrait":"https://image
     * .juntaikeji.com/head_img/default.jpg","postId":4,"postName":"警员","departmentId":3,"frozenStatus":0,
     * "gmtCreate":"2021-04-20 09:18:31","token":"4J5WR55T2-2IGP5SVE2ZJQB6GCUFAV3-LE2LLPNK-0"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 1
         * account : 18669505929
         * password : null
         * nickname : 顾启杭
         * headPortrait : https://image.juntaikeji.com/head_img/default.jpg
         * postId : 4
         * postName : 警员
         * departmentId : 3
         * frozenStatus : 0
         * gmtCreate : 2021-04-20 09:18:31
         * token : 4J5WR55T2-2IGP5SVE2ZJQB6GCUFAV3-LE2LLPNK-0
         */

        private int userId;
        private String account;
        private String password;
        private String nickname;
        private String headPortrait;
        private int postId;
        private String postName;
        private int departmentId;
        private int frozenStatus;
        private String gmtCreate;
        private String departmentName;
        private String token;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password == null ? "" : password;
        }

        public void setPassword(String password) {
            this.password = password == null ? "" : password;
        }

        public String getDepartmentName() {
            return departmentName == null ? "" : departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName == null ? "" : departmentName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public int getFrozenStatus() {
            return frozenStatus;
        }

        public void setFrozenStatus(int frozenStatus) {
            this.frozenStatus = frozenStatus;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
