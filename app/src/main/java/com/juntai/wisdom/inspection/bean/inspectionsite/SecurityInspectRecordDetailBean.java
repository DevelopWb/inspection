package com.juntai.wisdom.inspection.bean.inspectionsite;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/15 16:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 16:54
 */
public class SecurityInspectRecordDetailBean extends BaseResult {


    /**
     * error : null
     * data : {"id":1,"securityId":1,"inspectTime":"2021-05-11","inspectName":"王进喜","unitLiable":"张三",
     * "liablePhone":"1665555555","typeName":"一切正常","remarks":"一切正常",
     * "photoOne":"/security_inspection/c38caec3095a4927b49e8405c55dbc72.jpeg",
     * "photoTwo":"/security_inspection/67de954455d44fc6ba4979458460a580.jpeg",
     * "photoThree":"/security_inspection/fc6b539af70945c8aec393628b8d188a.jpeg","photoFour":null,"photoFive":null,
     * "photoSix":null}
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
         * id : 1
         * securityId : 1
         * inspectTime : 2021-05-11
         * inspectName : 王进喜
         * unitLiable : 张三
         * liablePhone : 1665555555
         * typeName : 一切正常
         * remarks : 一切正常
         * photoOne : /security_inspection/c38caec3095a4927b49e8405c55dbc72.jpeg
         * photoTwo : /security_inspection/67de954455d44fc6ba4979458460a580.jpeg
         * photoThree : /security_inspection/fc6b539af70945c8aec393628b8d188a.jpeg
         * photoFour : null
         * photoFive : null
         * photoSix : null
         */

        private int id;
        private int securityId;
        private String inspectTime;
        private String inspectName;
        private String unitLiable;
        private String liablePhone;
        private String typeName;
        private int typeId;
        private String remarks;
        private String photoOne;
        private String photoTwo;
        private String photoThree;
        private String photoFour;
        private String photoFive;
        private String photoSix;

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSecurityId() {
            return securityId;
        }

        public void setSecurityId(int securityId) {
            this.securityId = securityId;
        }

        public String getInspectTime() {
            return inspectTime;
        }

        public void setInspectTime(String inspectTime) {
            this.inspectTime = inspectTime;
        }

        public String getInspectName() {
            return inspectName;
        }

        public void setInspectName(String inspectName) {
            this.inspectName = inspectName;
        }

        public String getUnitLiable() {
            return unitLiable;
        }

        public void setUnitLiable(String unitLiable) {
            this.unitLiable = unitLiable;
        }

        public String getLiablePhone() {
            return liablePhone;
        }

        public void setLiablePhone(String liablePhone) {
            this.liablePhone = liablePhone;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
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
    }
}
