package com.juntai.wisdom.inspection.bean;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/6/3 11:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/3 11:24
 */
public class MyMsgBean extends BaseResult {


    /**
     * error : null
     * data : [{"id":9,"typeId":1,"contentId":33,"name":"国家电网111","gmtCreate":"2021-06-01"},{"id":11,"typeId":1,
     * "contentId":38,"name":"牛牛木业有限公司","gmtCreate":"2021-06-01"},{"id":13,"typeId":1,"contentId":40,"name":"测试公司",
     * "gmtCreate":"2021-06-01"},{"id":15,"typeId":2,"contentId":1,"name":"张牛牛444","gmtCreate":"2021-06-01"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 9
         * typeId : 1
         * contentId : 33
         * name : 国家电网111
         * gmtCreate : 2021-06-01
         */

        private int id;
        private int typeId;
        private int contentId;
        private String name;
        private String gmtCreate;
        private int isRead;//是否已读（0未读；1已读）
        public int getId() {
            return id;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getContentId() {
            return contentId;
        }

        public void setContentId(int contentId) {
            this.contentId = contentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
    }
}
