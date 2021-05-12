package com.juntai.wisdom.inspection.bean.inspectionsite;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述  巡检点详情
 * @CreateDate: 2021/5/12 11:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/12 11:50
 */
public class InspectionSiteBean  extends BaseResult {


    /**
     * error : null
     * data : {"id":2,"name":"牛牛村红星超市","address":"兰山区新桥街道567号","personLiable":"王五","liablePhone":"16666666666",
     * "sparePerson":"马六","sparePhone":"18888888888","remarks":"这是搜索添加治安巡检点","gpsAddress":"临沂市兰山区大山路2314号",
     * "longitude":"118.296522","latitude":"35.3652553",
     * "coverPicture":"/security_inspection/894c68d6897e46428c046af41ab0d6f3.jpeg",
     * "photoTwo":"/security_inspection/d0644a571b2c452a9430b56221368753.jpeg",
     * "photoThree":"/security_inspection/2230ec1252a0430f9b301dd7232d4e4f.jpeg","photoFour":null,"photoFive":null,
     * "photoSix":null,"qrCode":"/unit_qr_code/9194772686dd4b1aba382cb4a12b0647.jpeg"}
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
         * id : 2
         * name : 牛牛村红星超市
         * address : 兰山区新桥街道567号
         * personLiable : 王五
         * liablePhone : 16666666666
         * sparePerson : 马六
         * sparePhone : 18888888888
         * remarks : 这是搜索添加治安巡检点
         * gpsAddress : 临沂市兰山区大山路2314号
         * longitude : 118.296522
         * latitude : 35.3652553
         * coverPicture : /security_inspection/894c68d6897e46428c046af41ab0d6f3.jpeg
         * photoTwo : /security_inspection/d0644a571b2c452a9430b56221368753.jpeg
         * photoThree : /security_inspection/2230ec1252a0430f9b301dd7232d4e4f.jpeg
         * photoFour : null
         * photoFive : null
         * photoSix : null
         * qrCode : /unit_qr_code/9194772686dd4b1aba382cb4a12b0647.jpeg
         */

        private int id;
        private String name;
        private String address;
        private String personLiable;
        private String liablePhone;
        private String sparePerson;
        private String sparePhone;
        private String remarks;
        private String gpsAddress;
        private String longitude;
        private String latitude;
        private String coverPicture;
        private String photoTwo;
        private String photoThree;
        private String photoFour;
        private String photoFive;
        private String photoSix;
        private String qrCode;
        private int isAdd;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPersonLiable() {
            return personLiable;
        }

        public void setPersonLiable(String personLiable) {
            this.personLiable = personLiable;
        }

        public String getLiablePhone() {
            return liablePhone;
        }

        public void setLiablePhone(String liablePhone) {
            this.liablePhone = liablePhone;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public String getCoverPicture() {
            return coverPicture;
        }

        public void setCoverPicture(String coverPicture) {
            this.coverPicture = coverPicture;
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

        public int getIsAdd() {
            return isAdd;
        }

        public void setIsAdd(int isAdd) {
            this.isAdd = isAdd;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.address);
            dest.writeString(this.personLiable);
            dest.writeString(this.liablePhone);
            dest.writeString(this.sparePerson);
            dest.writeString(this.sparePhone);
            dest.writeString(this.remarks);
            dest.writeString(this.gpsAddress);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
            dest.writeString(this.coverPicture);
            dest.writeString(this.photoTwo);
            dest.writeString(this.photoThree);
            dest.writeString(this.photoFour);
            dest.writeString(this.photoFive);
            dest.writeString(this.photoSix);
            dest.writeString(this.qrCode);
            dest.writeInt(this.isAdd);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.address = in.readString();
            this.personLiable = in.readString();
            this.liablePhone = in.readString();
            this.sparePerson = in.readString();
            this.sparePhone = in.readString();
            this.remarks = in.readString();
            this.gpsAddress = in.readString();
            this.longitude = in.readString();
            this.latitude = in.readString();
            this.coverPicture = in.readString();
            this.photoTwo = in.readString();
            this.photoThree = in.readString();
            this.photoFour = in.readString();
            this.photoFive = in.readString();
            this.photoSix = in.readString();
            this.qrCode = in.readString();
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
