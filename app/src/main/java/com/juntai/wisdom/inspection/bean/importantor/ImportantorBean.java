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

        private int id;
        private int version;
        private String name;
        private String nickname;
        private int gender;
        private String policeName;
        private String personnelPhoto;
        private String idNumber;
        private String address;
        private int checkTime;
        private String keyStatus;
        private String gpsAddress;
        private String longitude;
        private String latitude;
        private String unitName;
        private String phone;
        private String otherPhone;
        private String sparePerson;
        private String sparePhone;
        private String treatment;
        private String remarks;
        private List<TypeListBean> typeList;
        private int isAdd;//0未添加

        public int getIsAdd() {
            return isAdd;
        }

        public void setIsAdd(int isAdd) {
            this.isAdd = isAdd;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getPoliceName() {
            return policeName;
        }

        public void setPoliceName(String policeName) {
            this.policeName = policeName;
        }

        public String getPersonnelPhoto() {
            return personnelPhoto;
        }

        public void setPersonnelPhoto(String personnelPhoto) {
            this.personnelPhoto = personnelPhoto;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(int checkTime) {
            this.checkTime = checkTime;
        }

        public String getKeyStatus() {
            return keyStatus;
        }

        public void setKeyStatus(String keyStatus) {
            this.keyStatus = keyStatus;
        }

        public String getGpsAddress() {
            return gpsAddress;
        }

        public void setGpsAddress(String gpsAddress) {
            this.gpsAddress = gpsAddress;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOtherPhone() {
            return otherPhone;
        }

        public void setOtherPhone(String otherPhone) {
            this.otherPhone = otherPhone;
        }

        public String getSparePerson() {
            return sparePerson;
        }

        public void setSparePerson(String sparePerson) {
            this.sparePerson = sparePerson;
        }

        public String getSparePhone() {
            return sparePhone;
        }

        public void setSparePhone(String sparePhone) {
            this.sparePhone = sparePhone;
        }

        public String getTreatment() {
            return treatment;
        }

        public void setTreatment(String treatment) {
            this.treatment = treatment;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public List<TypeListBean> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<TypeListBean> typeList) {
            this.typeList = typeList;
        }

        public static class TypeListBean {
            /**
             * id : 2
             * name : 涉毒
             */

            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.version);
            dest.writeString(this.name);
            dest.writeString(this.nickname);
            dest.writeInt(this.gender);
            dest.writeString(this.policeName);
            dest.writeString(this.personnelPhoto);
            dest.writeString(this.idNumber);
            dest.writeString(this.address);
            dest.writeInt(this.checkTime);
            dest.writeString(this.keyStatus);
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
            dest.writeList(this.typeList);
            dest.writeInt(this.isAdd);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.version = in.readInt();
            this.name = in.readString();
            this.nickname = in.readString();
            this.gender = in.readInt();
            this.policeName = in.readString();
            this.personnelPhoto = in.readString();
            this.idNumber = in.readString();
            this.address = in.readString();
            this.checkTime = in.readInt();
            this.keyStatus = in.readString();
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
            this.typeList = new ArrayList<TypeListBean>();
            in.readList(this.typeList, TypeListBean.class.getClassLoader());
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
