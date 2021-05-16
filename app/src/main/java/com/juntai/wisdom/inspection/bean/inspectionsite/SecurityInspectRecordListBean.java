package com.juntai.wisdom.inspection.bean.inspectionsite;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/5/15 16:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/15 16:52
 */
public class SecurityInspectRecordListBean extends BaseResult {


    /**
     * error : null
     * data : {"datas":[{"id":1,"inspectName":"王进喜","inspectTime":"2021-05-11"},{"id":2,"inspectName":"李白",
     * "inspectTime":"2021-05-01"}],"total":2,"listSize":2,"pageCount":1}
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
         * datas : [{"id":1,"inspectName":"王进喜","inspectTime":"2021-05-11"},{"id":2,"inspectName":"李白",
         * "inspectTime":"2021-05-01"}]
         * total : 2
         * listSize : 2
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
             * inspectName : 王进喜
             * inspectTime : 2021-05-11
             */

            private int id;
            private String inspectName;
            private String inspectTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getInspectName() {
                return inspectName;
            }

            public void setInspectName(String inspectName) {
                this.inspectName = inspectName;
            }

            public String getInspectTime() {
                return inspectTime;
            }

            public void setInspectTime(String inspectTime) {
                this.inspectTime = inspectTime;
            }
        }
    }
}
