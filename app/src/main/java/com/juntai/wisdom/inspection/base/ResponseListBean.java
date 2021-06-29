package com.juntai.wisdom.inspection.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/6/28 10:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/28 10:04
 */
public class ResponseListBean extends BaseResult {


    /**
     * error : null
     * data : [{"id":0,"name":"消防安全责任书","content":"消防安全责任书为认真做好消防安全工作，落实消防安全责任制","seal":"/unit_picture/seal3.png","logo":"/unit_picture/qrCodeLogo3.png","nameSeal":"/unit_picture/nameSeal3.png","unitName":"临沂市兰山区玉存轮胎维修服务部","legal":"李玉存","legalPhone":"13792978552"},{"id":0,"name":"治安管理责任书","content":"临沂市公安局兰山分局治安管理责任书","seal":"/unit_picture/seal3.png","logo":"/unit_picture/qrCodeLogo3.png","nameSeal":"/unit_picture/nameSeal3.png","unitName":"临沂市兰山区玉存轮胎维修服务部","legal":"李玉存","legalPhone":"13792978552"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 0
         * name : 消防安全责任书
         * content : 消防安全责任书为认真做好消防安全工作，落实消防安全责任制
         * seal : /unit_picture/seal3.png
         * logo : /unit_picture/qrCodeLogo3.png
         * nameSeal : /unit_picture/nameSeal3.png
         * unitName : 临沂市兰山区玉存轮胎维修服务部
         * legal : 李玉存
         * legalPhone : 13792978552
         */

        private int id;
        private String name;
        private String content;
        private String seal;
        private String logo;
        private String nameSeal;
        private String unitName;
        private String legal;
        private String legalPhone;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSeal() {
            return seal;
        }

        public void setSeal(String seal) {
            this.seal = seal;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getNameSeal() {
            return nameSeal;
        }

        public void setNameSeal(String nameSeal) {
            this.nameSeal = nameSeal;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getLegal() {
            return legal;
        }

        public void setLegal(String legal) {
            this.legal = legal;
        }

        public String getLegalPhone() {
            return legalPhone;
        }

        public void setLegalPhone(String legalPhone) {
            this.legalPhone = legalPhone;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.content);
            dest.writeString(this.seal);
            dest.writeString(this.logo);
            dest.writeString(this.nameSeal);
            dest.writeString(this.unitName);
            dest.writeString(this.legal);
            dest.writeString(this.legalPhone);
        }

        public void readFromParcel(Parcel source) {
            this.id = source.readInt();
            this.name = source.readString();
            this.content = source.readString();
            this.seal = source.readString();
            this.logo = source.readString();
            this.nameSeal = source.readString();
            this.unitName = source.readString();
            this.legal = source.readString();
            this.legalPhone = source.readString();
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.content = in.readString();
            this.seal = in.readString();
            this.logo = in.readString();
            this.nameSeal = in.readString();
            this.unitName = in.readString();
            this.legal = in.readString();
            this.legalPhone = in.readString();
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
