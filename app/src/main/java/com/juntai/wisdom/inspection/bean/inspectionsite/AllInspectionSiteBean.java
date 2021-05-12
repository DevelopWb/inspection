package com.juntai.wisdom.inspection.bean.inspectionsite;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/12 11:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/12 11:59
 */
public class AllInspectionSiteBean extends BaseResult {

    /**
     * error : null
     * data : {"datas":[{"id":1,"name":"牛牛村委会","address":"兰山区大山路234号","coverPicture":"/security_inspection/inspection
     * .png","isAdd":0},{"id":2,"name":"牛牛村红星超市","address":"兰山区新桥街道567号",
     * "coverPicture":"/security_inspection/inspection.png","isAdd":0},{"id":3,"name":"牛油果牛牛幼儿园",
     * "address":"兰山区新桥街道577号","coverPicture":"/security_inspection/inspection.png","isAdd":0}],"total":3,
     * "listSize":3,"pageCount":1}
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
         * datas : [{"id":1,"name":"牛牛村委会","address":"兰山区大山路234号","coverPicture":"/security_inspection/inspection
         * .png","isAdd":0},{"id":2,"name":"牛牛村红星超市","address":"兰山区新桥街道567号",
         * "coverPicture":"/security_inspection/inspection.png","isAdd":0},{"id":3,"name":"牛油果牛牛幼儿园",
         * "address":"兰山区新桥街道577号","coverPicture":"/security_inspection/inspection.png","isAdd":0}]
         * total : 3
         * listSize : 3
         * pageCount : 1
         */

        private int total;
        private int listSize;
        private int pageCount;
        private List<InspectionSiteBean.DataBean> datas;

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

        public List<InspectionSiteBean.DataBean> getDatas() {
            return datas;
        }

        public void setDatas(List<InspectionSiteBean.DataBean> datas) {
            this.datas = datas;
        }

    }
}
