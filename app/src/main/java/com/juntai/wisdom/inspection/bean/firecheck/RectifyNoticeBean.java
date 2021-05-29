package com.juntai.wisdom.inspection.bean.firecheck;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/28 15:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/28 15:28
 */
public class RectifyNoticeBean  extends BaseResult {

    /**
     * error : null
     * data : {"noticeId":1,"departmentName":"兰山公安分局新桥派出所","unitName":"国家电网111","item":"1,3,2,4,11",
     * "otherProblem":"缺少灭火器仓库、过道、值班室等共缺少10只","concreteProblems":"问题备注","itemOne":"1,3","itemOneTime":"2021-05-29",
     * "itemTwo":"2,4,11","itemTwoTime":"2021-05-18","signPhoto":"/unit_picture/acb4c354ada14145b0a7509cef554108
     * .jpeg","seal":"/unit_picture/seal3.png","gmtCreate":"2021-05-17 09:29:57","shareUrl":"https://www.baidu.com",
     * "itemsJson":""}
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
         * noticeId : 1
         * departmentName : 兰山公安分局新桥派出所
         * unitName : 国家电网111
         * item : 1,3,2,4,11
         * otherProblem : 缺少灭火器仓库、过道、值班室等共缺少10只
         * concreteProblems : 问题备注
         * itemOne : 1,3
         * itemOneTime : 2021-05-29
         * itemTwo : 2,4,11
         * itemTwoTime : 2021-05-18
         * signPhoto : /unit_picture/acb4c354ada14145b0a7509cef554108.jpeg
         * seal : /unit_picture/seal3.png
         * gmtCreate : 2021-05-17 09:29:57
         * shareUrl : https://www.baidu.com
         *
         * itemsJson :
         */

        private int noticeId;
        private String departmentName;
        private String unitName;
        private String item;
        private String otherProblem;
        private String concreteProblems;
        private String itemOne;
        private String itemOneTime;
        private String itemTwo;
        private String itemTwoTime;
        private String signPhoto;
        private String seal;
        private String gmtCreate;
        private String shareUrl;
        private String itemsJson;
        private String noticeContent;// 通知内容
        public int getNoticeId() {
            return noticeId;
        }

        public String getNoticeContent() {
            return noticeContent == null ? "" : noticeContent;
        }

        public void setNoticeContent(String noticeContent) {
            this.noticeContent = noticeContent == null ? "" : noticeContent;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getOtherProblem() {
            return otherProblem;
        }

        public void setOtherProblem(String otherProblem) {
            this.otherProblem = otherProblem;
        }

        public String getConcreteProblems() {
            return concreteProblems;
        }

        public void setConcreteProblems(String concreteProblems) {
            this.concreteProblems = concreteProblems;
        }

        public String getItemOne() {
            return itemOne;
        }

        public void setItemOne(String itemOne) {
            this.itemOne = itemOne;
        }

        public String getItemOneTime() {
            return itemOneTime;
        }

        public void setItemOneTime(String itemOneTime) {
            this.itemOneTime = itemOneTime;
        }

        public String getItemTwo() {
            return itemTwo;
        }

        public void setItemTwo(String itemTwo) {
            this.itemTwo = itemTwo;
        }

        public String getItemTwoTime() {
            return itemTwoTime;
        }

        public void setItemTwoTime(String itemTwoTime) {
            this.itemTwoTime = itemTwoTime;
        }

        public String getSignPhoto() {
            return signPhoto;
        }

        public void setSignPhoto(String signPhoto) {
            this.signPhoto = signPhoto;
        }

        public String getSeal() {
            return seal;
        }

        public void setSeal(String seal) {
            this.seal = seal;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getItemsJson() {
            return itemsJson;
        }

        public void setItemsJson(String itemsJson) {
            this.itemsJson = itemsJson;
        }
    }
}
