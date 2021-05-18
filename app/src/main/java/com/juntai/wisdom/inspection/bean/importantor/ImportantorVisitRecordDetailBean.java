package com.juntai.wisdom.inspection.bean.importantor;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/16 16:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/16 16:36
 */
public class ImportantorVisitRecordDetailBean extends BaseResult {

    /**
     * error : null
     * data : {"id":1,"nickname":"顾启杭","checkTime":"2021-05-06","liable":"张三","liablePhone":"1866666666",
     * "inspectionName":"一切正常","remarks":"一切正常",
     * "photoOne":"/key_personnel_visit_record/054bfa46b5c5444a892d54729e8a5c7a.jpeg",
     * "photoTwo":"/key_personnel_visit_record/52b5b900b47d4b2985e6c705dcf309f1.jpeg",
     * "photoThree":"/key_personnel_visit_record/41591e4dfee44670b0c7c0d9160d7b0b.jpeg","photoFour":null,
     * "photoFive":null,"photoSix":null}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 1
         * nickname : 顾启杭
         * checkTime : 2021-05-06
         * liable : 张三
         * liablePhone : 1866666666
         * inspectionName : 一切正常
         * remarks : 一切正常
         * photoOne : /key_personnel_visit_record/054bfa46b5c5444a892d54729e8a5c7a.jpeg
         * photoTwo : /key_personnel_visit_record/52b5b900b47d4b2985e6c705dcf309f1.jpeg
         * photoThree : /key_personnel_visit_record/41591e4dfee44670b0c7c0d9160d7b0b.jpeg
         * photoFour : null
         * photoFive : null
         * photoSix : null
         */

        private int id;
        private String nickname;
        private String checkTime;
        private String liable;
        private String liablePhone;
        private String inspectionName;
        private int inspectionId;
        private String remarks;
        private String photoOne;
        private String photoTwo;
        private String photoThree;
        private String photoFour;
        private String photoFive;
        private String photoSix;

        public String getPhotoFour() {
            return photoFour == null ? "" : photoFour;
        }

        public String getPhotoFive() {
            return photoFive == null ? "" : photoFive;
        }

        public String getPhotoSix() {
            return photoSix == null ? "" : photoSix;
        }

        public int getId() {
            return id;
        }

        public int getInspectionId() {
            return inspectionId;
        }

        public void setInspectionId(int inspectionId) {
            this.inspectionId = inspectionId;
        }

        public void setPhotoFour(String photoFour) {
            this.photoFour = photoFour == null ? "" : photoFour;
        }

        public void setPhotoFive(String photoFive) {
            this.photoFive = photoFive == null ? "" : photoFive;
        }

        public void setPhotoSix(String photoSix) {
            this.photoSix = photoSix == null ? "" : photoSix;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getLiable() {
            return liable;
        }

        public void setLiable(String liable) {
            this.liable = liable;
        }

        public String getLiablePhone() {
            return liablePhone;
        }

        public void setLiablePhone(String liablePhone) {
            this.liablePhone = liablePhone;
        }

        public String getInspectionName() {
            return inspectionName;
        }

        public void setInspectionName(String inspectionName) {
            this.inspectionName = inspectionName;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getPhotoOne() {
            return photoOne;
        }

        public void setPhotoOne(String photoOne) {
            this.photoOne = photoOne;
        }

        public String getPhotoTwo() {
            return photoTwo;
        }

        public void setPhotoTwo(String photoTwo) {
            this.photoTwo = photoTwo;
        }

        public String getPhotoThree() {
            return photoThree;
        }

        public void setPhotoThree(String photoThree) {
            this.photoThree = photoThree;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.nickname);
            dest.writeString(this.checkTime);
            dest.writeString(this.liable);
            dest.writeString(this.liablePhone);
            dest.writeString(this.inspectionName);
            dest.writeInt(this.inspectionId);
            dest.writeString(this.remarks);
            dest.writeString(this.photoOne);
            dest.writeString(this.photoTwo);
            dest.writeString(this.photoThree);
            dest.writeString(this.photoFour);
            dest.writeString(this.photoFive);
            dest.writeString(this.photoSix);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.nickname = in.readString();
            this.checkTime = in.readString();
            this.liable = in.readString();
            this.liablePhone = in.readString();
            this.inspectionName = in.readString();
            this.inspectionId = in.readInt();
            this.remarks = in.readString();
            this.photoOne = in.readString();
            this.photoTwo = in.readString();
            this.photoThree = in.readString();
            this.photoFour = in.readString();
            this.photoFive = in.readString();
            this.photoSix = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
