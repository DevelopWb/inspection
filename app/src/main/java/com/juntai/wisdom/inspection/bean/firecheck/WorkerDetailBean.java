package com.juntai.wisdom.inspection.bean.firecheck;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/31 10:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/31 10:34
 */
public class WorkerDetailBean extends BaseResult {


    /**
     * error : null
     * data : {"id":1,"name":"铁人王进喜","gender":1,"nickname":"顾启杭",
     * "personnelPhoto":"/unit_staff_picture/af345966bcc449ebaee9a041644b9b38.jpeg","idNumber":"371329199611071512",
     * "address":"山东省临沂市河东区","phone":"18666666666","unitName":"国家电网111","postName":"安全员","remarks":"从业人员添加测试"}
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
         * name : 铁人王进喜
         * gender : 1
         * nickname : 顾启杭
         * personnelPhoto : /unit_staff_picture/af345966bcc449ebaee9a041644b9b38.jpeg
         * idNumber : 371329199611071512
         * address : 山东省临沂市河东区
         * phone : 18666666666
         * unitName : 国家电网111
         * postName : 安全员
         * remarks : 从业人员添加测试
         */

        private int id;
        private String name;
        private int gender;
        private String nickname;
        private String personnelPhoto;
        private String idNumber;
        private String address;
        private String phone;
        private String unitName;
        private int unitId;
        private String postName;
        private int postId;
        private String remarks;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name == null ? "" : name;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getNickname() {
            return nickname == null ? "" : nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname == null ? "" : nickname;
        }

        public String getPersonnelPhoto() {
            return personnelPhoto == null ? "" : personnelPhoto;
        }

        public void setPersonnelPhoto(String personnelPhoto) {
            this.personnelPhoto = personnelPhoto == null ? "" : personnelPhoto;
        }

        public String getIdNumber() {
            return idNumber == null ? "" : idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber == null ? "" : idNumber;
        }

        public String getAddress() {
            return address == null ? "" : address;
        }

        public void setAddress(String address) {
            this.address = address == null ? "" : address;
        }

        public String getPhone() {
            return phone == null ? "" : phone;
        }

        public void setPhone(String phone) {
            this.phone = phone == null ? "" : phone;
        }

        public String getUnitName() {
            return unitName == null ? "" : unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName == null ? "" : unitName;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public String getPostName() {
            return postName == null ? "" : postName;
        }

        public void setPostName(String postName) {
            this.postName = postName == null ? "" : postName;
        }

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public String getRemarks() {
            return remarks == null ? "" : remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks == null ? "" : remarks;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeInt(this.gender);
            dest.writeString(this.nickname);
            dest.writeString(this.personnelPhoto);
            dest.writeString(this.idNumber);
            dest.writeString(this.address);
            dest.writeString(this.phone);
            dest.writeString(this.unitName);
            dest.writeInt(this.unitId);
            dest.writeString(this.postName);
            dest.writeInt(this.postId);
            dest.writeString(this.remarks);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.gender = in.readInt();
            this.nickname = in.readString();
            this.personnelPhoto = in.readString();
            this.idNumber = in.readString();
            this.address = in.readString();
            this.phone = in.readString();
            this.unitName = in.readString();
            this.unitId = in.readInt();
            this.postName = in.readString();
            this.postId = in.readInt();
            this.remarks = in.readString();
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
