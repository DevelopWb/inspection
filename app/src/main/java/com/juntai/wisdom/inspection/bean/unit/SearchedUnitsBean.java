package com.juntai.wisdom.inspection.bean.unit;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  搜索到的单位
 * @CreateDate: 2021/5/7 10:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/7 10:11
 */
public class SearchedUnitsBean  extends BaseResult {


    /**
     * error : null
     * data : {"datas":[{"id":33,"userId":0,"name":"国家电网111","address":"中国北京111",
     * "coverPicture":"/unit_picture/af5979e41bcc4de8a58d513b3c186ddf.jpeg","state":1,"isAdd":1}],"total":1,
     * "listSize":1,"pageCount":1}
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
         * datas : [{"id":33,"userId":0,"name":"国家电网111","address":"中国北京111",
         * "coverPicture":"/unit_picture/af5979e41bcc4de8a58d513b3c186ddf.jpeg","state":1,"isAdd":1}]
         * total : 1
         * listSize : 1
         * pageCount : 1
         */

        private int total;
        private int listSize;
        private int pageCount;
        private List<DatasBean> datas;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getListSize() {
            return listSize;
        }

        public void setListSize(int listSize) {
            this.listSize = listSize;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean implements Parcelable {
            private int id;//单位id
            private int userId;//单位id

            private int version;//当前版本

            private String name;//单位名称

            private String typeName;//单位类型
            private int typeId;//单位类型

            private String address;//单位地址

            private String unifiedCreditCode;//统一社会编码

            private String legal;//法人

            private String legalPhone;//法人电话

            private String personLiable;//安全责任人

            private String liablePhone;//责任人电话

            private String sparePerson;//备用联系人

            private String sparePhone;//备用电话

            private String remarks;//备注

            private String gpsAddress;//GPS定位地址

            private String longitude;//经度

            private String latitude;//纬度

            private String coverPicture;//封面图

            private String photoTwo;//图片

            private String photoThree;//图片

            private String photoFour;//图片

            private String photoFive;//图片

            private String photoSix;//图片

            private String qrCode;//二维码图片

            private int state;//单位检查状态（1待检查；2整改中；3合格）
            private int isAdd;//0未添加
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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

            public int getVersion() {
                return version;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getTypeName() {
                return typeName == null ? "" : typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName == null ? "" : typeName;
            }

            public String getUnifiedCreditCode() {
                return unifiedCreditCode == null ? "" : unifiedCreditCode;
            }

            public void setUnifiedCreditCode(String unifiedCreditCode) {
                this.unifiedCreditCode = unifiedCreditCode == null ? "" : unifiedCreditCode;
            }

            public String getLegal() {
                return legal == null ? "" : legal;
            }

            public void setLegal(String legal) {
                this.legal = legal == null ? "" : legal;
            }

            public String getLegalPhone() {
                return legalPhone == null ? "" : legalPhone;
            }

            public void setLegalPhone(String legalPhone) {
                this.legalPhone = legalPhone == null ? "" : legalPhone;
            }

            public String getPersonLiable() {
                return personLiable == null ? "" : personLiable;
            }

            public void setPersonLiable(String personLiable) {
                this.personLiable = personLiable == null ? "" : personLiable;
            }

            public String getLiablePhone() {
                return liablePhone == null ? "" : liablePhone;
            }

            public void setLiablePhone(String liablePhone) {
                this.liablePhone = liablePhone == null ? "" : liablePhone;
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

            public String getRemarks() {
                return remarks == null ? "" : remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks == null ? "" : remarks;
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

            public String getQrCode() {
                return qrCode == null ? "" : qrCode;
            }

            public void setQrCode(String qrCode) {
                this.qrCode = qrCode == null ? "" : qrCode;
            }

            public String getCoverPicture() {
                return coverPicture;
            }

            public void setCoverPicture(String coverPicture) {
                this.coverPicture = coverPicture;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
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
                dest.writeInt(this.userId);
                dest.writeInt(this.version);
                dest.writeString(this.name);
                dest.writeString(this.typeName);
                dest.writeInt(this.typeId);
                dest.writeString(this.address);
                dest.writeString(this.unifiedCreditCode);
                dest.writeString(this.legal);
                dest.writeString(this.legalPhone);
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
                dest.writeInt(this.state);
                dest.writeInt(this.isAdd);
            }

            public DatasBean() {
            }

            protected DatasBean(Parcel in) {
                this.id = in.readInt();
                this.userId = in.readInt();
                this.version = in.readInt();
                this.name = in.readString();
                this.typeName = in.readString();
                this.typeId = in.readInt();
                this.address = in.readString();
                this.unifiedCreditCode = in.readString();
                this.legal = in.readString();
                this.legalPhone = in.readString();
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
                this.state = in.readInt();
                this.isAdd = in.readInt();
            }

            public static final Parcelable.Creator<DatasBean> CREATOR = new Parcelable.Creator<DatasBean>() {
                @Override
                public DatasBean createFromParcel(Parcel source) {
                    return new DatasBean(source);
                }

                @Override
                public DatasBean[] newArray(int size) {
                    return new DatasBean[size];
                }
            };
        }
    }
}
