package com.juntai.wisdom.inspection.bean.importantor;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/13 9:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/13 9:26
 */
public class AllImportantorBean extends BaseResult {


    /**
     * error : null
     * data : {"datas":[{"id":1,"name":"张牛牛","idNumber":"371325199010247517",
     * "personnelPhoto":"/unit_picture/keyPersonnel.png","isAdd":2},{"id":2,"name":"郝牛牛",
     * "idNumber":"371312199712116931","personnelPhoto":"/unit_picture/keyPersonnel.png","isAdd":0},{"id":3,
     * "name":"李牛牛","idNumber":"37131219861105743X","personnelPhoto":"/unit_picture/keyPersonnel.png","isAdd":0}],
     * "total":3,"listSize":3,"pageCount":1}
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
         * datas : [{"id":1,"name":"张牛牛","idNumber":"371325199010247517","personnelPhoto":"/unit_picture/keyPersonnel
         * .png","isAdd":2},{"id":2,"name":"郝牛牛","idNumber":"371312199712116931",
         * "personnelPhoto":"/unit_picture/keyPersonnel.png","isAdd":0},{"id":3,"name":"李牛牛",
         * "idNumber":"37131219861105743X","personnelPhoto":"/unit_picture/keyPersonnel.png","isAdd":0}]
         * total : 3
         * listSize : 3
         * pageCount : 1
         */

        private int total;
        private int listSize;
        private int pageCount;
        private List<ImportantorBean.DataBean> datas;

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

        public List<ImportantorBean.DataBean> getDatas() {
            return datas;
        }

        public void setDatas(List<ImportantorBean.DataBean> datas) {
            this.datas = datas;
        }

    }
}
