package com.juntai.wisdom.inspection.bean.firecheck;

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

    public static class DataBean {
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
        private String postName;
        private int postId;
        private String remarks;

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
