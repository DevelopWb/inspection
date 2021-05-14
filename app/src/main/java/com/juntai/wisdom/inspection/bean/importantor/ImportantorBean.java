package com.juntai.wisdom.inspection.bean.importantor;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/12 16:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/12 16:48
 */
public class ImportantorBean extends BaseResult {


    /**
     * error : null
     * data : {"id":1,"version":0,"name":"张牛牛444","nickname":"大牛","gender":1,"policeName":"张三",
     * "personnelPhoto":"/key_personnel/keyPersonnel.png","idNumber":"sadafwagagag","address":"山东临沂嘿嘿嘿",
     * "checkTime":6,"keyStatus":"2","gpsAddress":"山东临沂河东区","longitude":"118.6515161","latitude":"35.1561561",
     * "unitAddress":"山东临沂河东九曲街道嘿嘿嘿","phone":"1374555566","otherPhone":"1588888888","sparePerson":"王五",
     * "sparePhone":"1586261556","treatment":"一切正常","remarks":"备注正常","typeList":[{"id":2,"name":"涉毒"},{"id":3,
     * "name":"诈骗"}]}
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
         * version : 0
         * name : 张牛牛444
         * nickname : 大牛
         * gender : 1
         * policeName : 张三
         * personnelPhoto : /key_personnel/keyPersonnel.png
         * idNumber : sadafwagagag
         * address : 山东临沂嘿嘿嘿
         * checkTime : 6
         * keyStatus : 2
         * gpsAddress : 山东临沂河东区
         * longitude : 118.6515161
         * latitude : 35.1561561
         * unitAddress : 山东临沂河东九曲街道嘿嘿嘿
         * phone : 1374555566
         * otherPhone : 1588888888
         * sparePerson : 王五
         * sparePhone : 1586261556
         * treatment : 一切正常
         * remarks : 备注正常
         * typeList : [{"id":2,"name":"涉毒"},{"id":3,"name":"诈骗"}]
         */
        private int id;//人员id

        private Integer version;//版本

        private String name;//人员姓名

        private String nickname;//人员昵称或曾用名

        private int gender;//性别（1男；2女）
        private String sexName;//性别（1男；2女）

        private String policeName;//管控警员

        private String personnelPhoto;//人员图片

        private String idNumber;//身份证号

        private String address;//现居地址

        private int checkTime;//每？天走访一次
        private String checkTimeName;//每？天走访一次

        private String typeId;//类型id，数组模式 [1,2]

        private String typeName;//犯罪类型

        private int keyStatus;//人员状态

        private String keyStatusName;//人员状态名称

        private String gpsAddress;//GPS定位地址

        private String longitude;//经度

        private String latitude;//纬度

        private String unitName;//现工作单位

        private String phone;//联系电话

        private String otherPhone;//其他联系方式（qq，微信，邮箱等）

        private String sparePerson;//备用联系人

        private String sparePhone;//备用联系电话

        private String treatment;//前期处理情况

        private String remarks;//备注
        private int isAdd;//0未添加

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name == null ? "" : name;
        }

        public String getNickname() {
            return nickname == null ? "" : nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname == null ? "" : nickname;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getPoliceName() {
            return policeName == null ? "" : policeName;
        }

        public void setPoliceName(String policeName) {
            this.policeName = policeName == null ? "" : policeName;
        }

        public String getPersonnelPhoto() {
            return personnelPhoto == null ? "" : personnelPhoto;
        }

        public void setPersonnelPhoto(String personnelPhoto) {
            this.personnelPhoto = personnelPhoto == null ? "" : personnelPhoto;
        }

        public String getCheckTimeName() {
            return checkTimeName == null ? "" : checkTimeName;
        }

        public void setCheckTimeName(String checkTimeName) {
            this.checkTimeName = checkTimeName == null ? "" : checkTimeName;
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

        public int getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(int checkTime) {
            this.checkTime = checkTime;
        }

        public String getTypeId() {
            return typeId == null ? "" : typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId == null ? "" : typeId;
        }

        public String getTypeName() {
            return typeName == null ? "" : typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName == null ? "" : typeName;
        }

        public String getSexName() {
            return sexName == null ? "" : sexName;
        }

        public void setSexName(String sexName) {
            this.sexName = sexName == null ? "" : sexName;
        }

        public int getKeyStatus() {
            return keyStatus;
        }

        public void setKeyStatus(int keyStatus) {
            this.keyStatus = keyStatus;
        }

        public String getKeyStatusName() {
            return keyStatusName == null ? "" : keyStatusName;
        }

        public void setKeyStatusName(String keyStatusName) {
            this.keyStatusName = keyStatusName == null ? "" : keyStatusName;
        }

        public String getGpsAddress() {
            return gpsAddress == null ? "" : gpsAddress;
        }

        public void setGpsAddress(String gpsAddress) {
            this.gpsAddress = gpsAddress == null ? "" : gpsAddress;
        }

        public String getLongitude() {
            return longitude == null ? "" : longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude == null ? "" : longitude;
        }

        public String getLatitude() {
            return latitude == null ? "" : latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude == null ? "" : latitude;
        }

        public String getUnitName() {
            return unitName == null ? "" : unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName == null ? "" : unitName;
        }

        public String getPhone() {
            return phone == null ? "" : phone;
        }

        public void setPhone(String phone) {
            this.phone = phone == null ? "" : phone;
        }

        public String getOtherPhone() {
            return otherPhone == null ? "" : otherPhone;
        }

        public void setOtherPhone(String otherPhone) {
            this.otherPhone = otherPhone == null ? "" : otherPhone;
        }

        public String getSparePerson() {
            return sparePerson == null ? "" : sparePerson;
        }

        public void setSparePerson(String sparePerson) {
            this.sparePerson = sparePerson == null ? "" : sparePerson;
        }

        public String getSparePhone() {
            return sparePhone == null ? "" : sparePhone;
        }

        public void setSparePhone(String sparePhone) {
            this.sparePhone = sparePhone == null ? "" : sparePhone;
        }

        public String getTreatment() {
            return treatment == null ? "" : treatment;
        }

        public void setTreatment(String treatment) {
            this.treatment = treatment == null ? "" : treatment;
        }

        public String getRemarks() {
            return remarks == null ? "" : remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks == null ? "" : remarks;
        }

        public int getIsAdd() {
            return isAdd;
        }

        public void setIsAdd(int isAdd) {
            this.isAdd = isAdd;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeValue(this.version);
            dest.writeString(this.name);
            dest.writeString(this.nickname);
            dest.writeInt(this.gender);
            dest.writeString(this.sexName);
            dest.writeString(this.policeName);
            dest.writeString(this.personnelPhoto);
            dest.writeString(this.idNumber);
            dest.writeString(this.address);
            dest.writeInt(this.checkTime);
            dest.writeString(this.checkTimeName);
            dest.writeString(this.typeId);
            dest.writeString(this.typeName);
            dest.writeInt(this.keyStatus);
            dest.writeString(this.keyStatusName);
            dest.writeString(this.gpsAddress);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
            dest.writeString(this.unitName);
            dest.writeString(this.phone);
            dest.writeString(this.otherPhone);
            dest.writeString(this.sparePerson);
            dest.writeString(this.sparePhone);
            dest.writeString(this.treatment);
            dest.writeString(this.remarks);
            dest.writeInt(this.isAdd);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.version = (Integer) in.readValue(Integer.class.getClassLoader());
            this.name = in.readString();
            this.nickname = in.readString();
            this.gender = in.readInt();
            this.sexName = in.readString();
            this.policeName = in.readString();
            this.personnelPhoto = in.readString();
            this.idNumber = in.readString();
            this.address = in.readString();
            this.checkTime = in.readInt();
            this.checkTimeName = in.readString();
            this.typeId = in.readString();
            this.typeName = in.readString();
            this.keyStatus = in.readInt();
            this.keyStatusName = in.readString();
            this.gpsAddress = in.readString();
            this.longitude = in.readString();
            this.latitude = in.readString();
            this.unitName = in.readString();
            this.phone = in.readString();
            this.otherPhone = in.readString();
            this.sparePerson = in.readString();
            this.sparePhone = in.readString();
            this.treatment = in.readString();
            this.remarks = in.readString();
            this.isAdd = in.readInt();
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
