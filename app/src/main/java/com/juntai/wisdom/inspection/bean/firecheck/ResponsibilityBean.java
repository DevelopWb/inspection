package com.juntai.wisdom.inspection.bean.firecheck;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述  责任书
 * @CreateDate: 2021/5/29 14:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/29 14:27
 */
public class ResponsibilityBean  extends BaseResult {


    /**
     * error : null
     * data : {"id":5,"name":"消防安全责任书","content":"为贯彻\u201c谁主管、谁负责\u201d的消防安全职责制。",
     * "signPhoto":"/letter_of_responsibility/d2ec3cd653504cd4bb110bcc711e2baf.jpeg",
     * "photoOne":"/letter_of_responsibility/679c1d904da141e290757094d3b944e9.jpeg",
     * "photoTwo":"/letter_of_responsibility/8d48a0b9f0d340459017c90534d566b4.jpeg",
     * "photoThree":"/letter_of_responsibility/f2a59fded6b94b79825254efbcb88b95.jpeg","gmtCreate":"2021-05-15"}
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
         * id : 5
         * name : 消防安全责任书
         * content : 为贯彻“谁主管、谁负责”的消防安全职责制。
         * signPhoto : /letter_of_responsibility/d2ec3cd653504cd4bb110bcc711e2baf.jpeg
         * photoOne : /letter_of_responsibility/679c1d904da141e290757094d3b944e9.jpeg
         * photoTwo : /letter_of_responsibility/8d48a0b9f0d340459017c90534d566b4.jpeg
         * photoThree : /letter_of_responsibility/f2a59fded6b94b79825254efbcb88b95.jpeg
         * gmtCreate : 2021-05-15
         */

        private int id;
        private String name;
        private String content;
        private String signPhoto;
        private String photoOne;
        private String photoTwo;
        private String photoThree;
        private String gmtCreate;

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

        public String getSignPhoto() {
            return signPhoto;
        }

        public void setSignPhoto(String signPhoto) {
            this.signPhoto = signPhoto;
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

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
    }
}
