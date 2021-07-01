package com.juntai.wisdom.inspection.bean.firecheck;

import com.juntai.disabled.basecomponent.base.BaseResult;

import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述  消防检查
 * @CreateDate: 2021/5/20 15:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/20 15:46
 */
public class FireCheckBean  extends BaseResult {


    /**
     * error : null
     * data : {"recordId":1,"inspectTime":"2021-05-17 09:29","inspectName":"王进喜","unitLiable":"蔡徐坤",
     * "liablePhone":"18669505555","time":"2021-05-29","otherProblem":"缺少灭火器仓库、过道、值班室等共缺少10只",
     * "concreteProblems":"问题备注","qualified":2,"photoOne":"/unit_picture/acb4c354ada14145b0a7509cef554108.jpeg",
     * "photoTwo":"/unit_picture/a314ba8bb62c4da3be8f5c62824840de.jpeg",
     * "photoThree":"/unit_picture/ee3af85bfb7c4211ba797b1e92235aaa.jpeg","photoFour":null,"photoFive":null,
     * "photoSix":null,"signPhoto":"/unit_picture/acb4c354ada14145b0a7509cef554108.jpeg","punishId":1,
     * "content":"根据《****条例》处罚金2000元","punishPhotoOne":"/unit_picture/acb4c354ada14145b0a7509cef554108.jpeg",
     * "punishPhotoTwo":"/unit_picture/a314ba8bb62c4da3be8f5c62824840de.jpeg",
     * "punishPhotoThree":"/unit_picture/ee3af85bfb7c4211ba797b1e92235aaa.jpeg","itemsJson":""}
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
         * recordId : 1
         * inspectTime : 2021-05-17 09:29
         * inspectName : 王进喜
         * unitLiable : 蔡徐坤
         * liablePhone : 18669505555
         * time : 2021-05-29
         * otherProblem : 缺少灭火器仓库、过道、值班室等共缺少10只
         * concreteProblems : 问题备注
         * qualified : 2
         * photoOne : /unit_picture/acb4c354ada14145b0a7509cef554108.jpeg
         * photoTwo : /unit_picture/a314ba8bb62c4da3be8f5c62824840de.jpeg
         * photoThree : /unit_picture/ee3af85bfb7c4211ba797b1e92235aaa.jpeg
         * photoFour : null
         * photoFive : null
         * photoSix : null
         * signPhoto : /unit_picture/acb4c354ada14145b0a7509cef554108.jpeg
         * punishId : 1
         * content : 根据《****条例》处罚金2000元
         * punishPhotoOne : /unit_picture/acb4c354ada14145b0a7509cef554108.jpeg
         * punishPhotoTwo : /unit_picture/a314ba8bb62c4da3be8f5c62824840de.jpeg
         * punishPhotoThree : /unit_picture/ee3af85bfb7c4211ba797b1e92235aaa.jpeg
         * itemsJson :
         */
        private int unitId;//单位id
        private int recordId;
        private String inspectTime;
        private String inspectName;
        private String unitLiable;
        private String liablePhone;
        private String time;
        private String timeOne;
        private String timeTwo;
        private String problemOne;
        private String problemTwo;
        private String otherProblem;
        private String remarks;
        private String remarksName;
        private int qualified;
        private String photoOne;
        private String photoTwo;
        private String photoThree;
        private String photoFour;
        private String photoFive;
        private String photoSix;
        private int punishId;
        private String content;
        private String punishPhotoOne;
        private String punishPhotoTwo;
        private String punishPhotoThree;
        private String itemsJson;
        private String itemOne;//问题类型1
        private String unitName;// 单位名称
        private String noticeName;// 通知书的名字
        private String noticeContent;// 通知内容

        private String itemOneTime;//整改到期时间

        private String itemTwo;//问题类型2

        private String itemTwoTime;//整改到期时间

        private String signPhoto;//签名照
        private boolean  hideSummarize = false;//隐藏总结文字

        public boolean isHideSummarize() {
            return hideSummarize;
        }

        public void setHideSummarize(boolean hideSummarize) {
            this.hideSummarize = hideSummarize;
        }

        public String getRemarksName() {
            return remarksName == null ? "" : remarksName;
        }

        public void setRemarksName(String remarksName) {
            this.remarksName = remarksName == null ? "" : remarksName;
        }

        private MultipartBody.Builder builder;

        public MultipartBody.Builder getBuilder() {
            return builder;
        }

        public void setBuilder(MultipartBody.Builder builder) {
            this.builder = builder;
        }

        public String getNoticeContent() {
            return noticeContent == null ? "" : noticeContent;
        }

        public void setNoticeContent(String noticeContent) {
            this.noticeContent = noticeContent == null ? "" : noticeContent;
        }

        public String getItemOne() {
            return itemOne == null ? "" : itemOne;
        }

        public String getNoticeName() {
            return noticeName == null ? "" : noticeName;
        }

        public void setNoticeName(String noticeName) {
            this.noticeName = noticeName == null ? "" : noticeName;
        }

        public String getUnitName() {
            return unitName == null ? "" : unitName;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName == null ? "" : unitName;
        }

        public void setItemOne(String itemOne) {
            this.itemOne = itemOne == null ? "" : itemOne;
        }

        public String getItemOneTime() {
            return itemOneTime == null ? "" : itemOneTime;
        }

        public void setItemOneTime(String itemOneTime) {
            this.itemOneTime = itemOneTime == null ? "" : itemOneTime;
        }

        public String getItemTwo() {
            return itemTwo == null ? "" : itemTwo;
        }

        public void setItemTwo(String itemTwo) {
            this.itemTwo = itemTwo == null ? "" : itemTwo;
        }

        public String getItemTwoTime() {
            return itemTwoTime == null ? "" : itemTwoTime;
        }

        public void setItemTwoTime(String itemTwoTime) {
            this.itemTwoTime = itemTwoTime == null ? "" : itemTwoTime;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public String getInspectTime() {
            return inspectTime == null ? "" : inspectTime;
        }

        public void setInspectTime(String inspectTime) {
            this.inspectTime = inspectTime == null ? "" : inspectTime;
        }

        public String getInspectName() {
            return inspectName == null ? "" : inspectName;
        }

        public void setInspectName(String inspectName) {
            this.inspectName = inspectName == null ? "" : inspectName;
        }

        public String getUnitLiable() {
            return unitLiable == null ? "" : unitLiable;
        }

        public void setUnitLiable(String unitLiable) {
            this.unitLiable = unitLiable == null ? "" : unitLiable;
        }

        public String getLiablePhone() {
            return liablePhone == null ? "" : liablePhone;
        }

        public void setLiablePhone(String liablePhone) {
            this.liablePhone = liablePhone == null ? "" : liablePhone;
        }

        public String getTime() {
            return time == null ? "" : time;
        }

        public void setTime(String time) {
            this.time = time == null ? "" : time;
        }

        public String getTimeOne() {
            return timeOne == null ? "" : timeOne;
        }

        public void setTimeOne(String timeOne) {
            this.timeOne = timeOne == null ? "" : timeOne;
        }

        public String getTimeTwo() {
            return timeTwo == null ? "" : timeTwo;
        }

        public void setTimeTwo(String timeTwo) {
            this.timeTwo = timeTwo == null ? "" : timeTwo;
        }

        public String getProblemOne() {
            return problemOne == null ? "" : problemOne;
        }

        public void setProblemOne(String problemOne) {
            this.problemOne = problemOne == null ? "" : problemOne;
        }

        public String getProblemTwo() {
            return problemTwo == null ? "" : problemTwo;
        }

        public void setProblemTwo(String problemTwo) {
            this.problemTwo = problemTwo == null ? "" : problemTwo;
        }

        public String getOtherProblem() {
            return otherProblem == null ? "" : otherProblem;
        }

        public void setOtherProblem(String otherProblem) {
            this.otherProblem = otherProblem == null ? "" : otherProblem;
        }

        public String getRemarks() {
            return remarks == null ? "" : remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks == null ? "" : remarks;
        }

        public int getQualified() {
            return qualified;
        }

        public void setQualified(int qualified) {
            this.qualified = qualified;
        }

        public String getPhotoOne() {
            return photoOne == null ? "" : photoOne;
        }

        public void setPhotoOne(String photoOne) {
            this.photoOne = photoOne == null ? "" : photoOne;
        }

        public String getPhotoTwo() {
            return photoTwo == null ? "" : photoTwo;
        }

        public void setPhotoTwo(String photoTwo) {
            this.photoTwo = photoTwo == null ? "" : photoTwo;
        }

        public String getPhotoThree() {
            return photoThree == null ? "" : photoThree;
        }

        public void setPhotoThree(String photoThree) {
            this.photoThree = photoThree == null ? "" : photoThree;
        }

        public String getPhotoFour() {
            return photoFour == null ? "" : photoFour;
        }

        public void setPhotoFour(String photoFour) {
            this.photoFour = photoFour == null ? "" : photoFour;
        }

        public String getPhotoFive() {
            return photoFive == null ? "" : photoFive;
        }

        public void setPhotoFive(String photoFive) {
            this.photoFive = photoFive == null ? "" : photoFive;
        }

        public String getPhotoSix() {
            return photoSix == null ? "" : photoSix;
        }

        public void setPhotoSix(String photoSix) {
            this.photoSix = photoSix == null ? "" : photoSix;
        }

        public String getSignPhoto() {
            return signPhoto == null ? "" : signPhoto;
        }

        public void setSignPhoto(String signPhoto) {
            this.signPhoto = signPhoto == null ? "" : signPhoto;
        }

        public int getPunishId() {
            return punishId;
        }

        public void setPunishId(int punishId) {
            this.punishId = punishId;
        }

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content == null ? "" : content;
        }

        public String getPunishPhotoOne() {
            return punishPhotoOne == null ? "" : punishPhotoOne;
        }

        public void setPunishPhotoOne(String punishPhotoOne) {
            this.punishPhotoOne = punishPhotoOne == null ? "" : punishPhotoOne;
        }

        public String getPunishPhotoTwo() {
            return punishPhotoTwo == null ? "" : punishPhotoTwo;
        }

        public void setPunishPhotoTwo(String punishPhotoTwo) {
            this.punishPhotoTwo = punishPhotoTwo == null ? "" : punishPhotoTwo;
        }

        public String getPunishPhotoThree() {
            return punishPhotoThree == null ? "" : punishPhotoThree;
        }

        public void setPunishPhotoThree(String punishPhotoThree) {
            this.punishPhotoThree = punishPhotoThree == null ? "" : punishPhotoThree;
        }

        public String getItemsJson() {
            return itemsJson == null ? "" : itemsJson;
        }

        public void setItemsJson(String itemsJson) {
            this.itemsJson = itemsJson == null ? "" : itemsJson;
        }
    }
}
