package com.juntai.wisdom.inspection.bean.firecheck;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/31 10:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/31 10:41
 */
public class WorkerListBean extends BaseResult {


    /**
     * error : null
     * data : {"datas":[{"id":1,"name":"铁人王进喜","personnelPhoto":"/unit_staff_picture/af345966bcc449ebaee9a041644b9b38
     * .jpeg","idNumber":"371329199611071512","postName":"安全员"}],"total":1,"listSize":1,"pageCount":1}
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
         * datas : [{"id":1,"name":"铁人王进喜","personnelPhoto":"/unit_staff_picture/af345966bcc449ebaee9a041644b9b38
         * .jpeg","idNumber":"371329199611071512","postName":"安全员"}]
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

        public static class DatasBean {
            /**
             * id : 1
             * name : 铁人王进喜
             * personnelPhoto : /unit_staff_picture/af345966bcc449ebaee9a041644b9b38.jpeg
             * idNumber : 371329199611071512
             * postName : 安全员
             */

            private int id;
            private String name;
            private String personnelPhoto;
            private String idNumber;
            private String postName;

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

            public String getPostName() {
                return postName;
            }

            public void setPostName(String postName) {
                this.postName = postName;
            }
        }
    }
}
